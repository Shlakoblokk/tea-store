package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.*
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.OrdersRepository
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.TeaBobaRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class OrdersServiceImpl(private val ordersRepository: OrdersRepository,
                        private val teaBobaRepository: TeaBobaRepository,
                        private val teaBobaService: TeaBobaService,
                        private val orderContentService: OrderContentService
): OrdersService {

    override fun getAll():MutableList<OrdersEntity> {
        return ordersRepository.findAll()
    }
    /*override fun save(orders: OrdersEntity) {
        ordersRepository.save(orders)
    }*/

    override fun save(orders: OrdersEntity): OrdersEntity {
        return ordersRepository.save(orders)
    }



    override fun getById(id_orders: Int): OrdersEntity {
        return ordersRepository.findById(id_orders).orElseThrow()
    }

    override fun delete(orders: OrdersEntity) {
        ordersRepository.delete(orders)
    }


    override fun getOrdersForCurrentUser(): List<OrdersEntity> {
        val authentication = SecurityContextHolder.getContext().authentication
        val currentUser = (authentication.principal as UserDetails).username

        // Здесь вызываем метод findByUserName для получения заказов текущего пользователя
        return ordersRepository.findByUserName(currentUser)
    }



    override fun createOrder(teaBobaIds: List<Long>, user: UserInfo, orderDateTime: LocalDateTime): OrdersEntity {
        // Создайте новый заказ
       // val orders = OrdersEntity()
        //val savedOrder = ordersRepository.save(orders)

        val totalPrice = calculateTotalPrice(teaBobaIds) // Рассчитайте общую цену

        // Создайте новый заказ
        val orders = OrdersEntity(user = user, totalPrice = totalPrice)
        val savedOrder = ordersRepository.save(orders)


        //val orders = OrdersEntity(user = user)
       // val savedOrder = ordersRepository.save(orders)

        // Обновите чай с шариками, связывая его с заказом и создавая содержимое заказа
        for (teaBobaId in teaBobaIds) {
            val tea_boba = teaBobaService.getTeaBobaById(teaBobaId)

            // Создайте содержимое заказа
            val orderContent = OrderContentEntity(
                id = OrderContentKey(teaBobaId = tea_boba.id!!, orderId = savedOrder.id_orders!!),
                tea_boba = tea_boba,
                orders = savedOrder,

                price = tea_boba.price ?: 0.0
            )

            // Сохраните содержимое заказа в репозитории
            orderContentService.save(orderContent)

        }

        return savedOrder
    }

    private fun calculateTotalPrice(teaBobaIds: List<Long>): Double {
        var totalPrice = 0.0

        // Получаем объекты TeaBobaEntity по идентификаторам
        val teaBobaEntities = teaBobaIds.map { teaBobaService.getTeaBobaById(it) }

        // Суммируем цены каждого объекта TeaBobaEntity
        for (teaBobaEntity in teaBobaEntities) {
            totalPrice += teaBobaEntity.price ?: 0.0 // Предположим, что в TeaBobaEntity есть поле price
        }

        return totalPrice
    }

}