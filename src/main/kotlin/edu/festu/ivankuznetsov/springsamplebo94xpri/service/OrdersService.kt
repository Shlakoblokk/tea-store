package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BobaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.OrdersEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaBobaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.UserInfo
import java.time.LocalDateTime

interface OrdersService {
    fun getAll():MutableList<OrdersEntity>
    //fun save(orders: OrdersEntity)
    fun save(orders: OrdersEntity): OrdersEntity

    fun getById(id_orders: Int): OrdersEntity
    fun delete(orders: OrdersEntity)


    fun createOrder(teaBobaIds: List<Long>, user: UserInfo, orderDateTime: LocalDateTime): OrdersEntity
    fun getOrdersForCurrentUser(): List<OrdersEntity>

}