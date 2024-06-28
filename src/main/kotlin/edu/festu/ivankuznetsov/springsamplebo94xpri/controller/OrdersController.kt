package edu.festu.ivankuznetsov.springsamplebo94xpri.controller

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.OrdersEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.UserInfo
import edu.festu.ivankuznetsov.springsamplebo94xpri.model.Order
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.OrdersRepository
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.UserInfoRepository
import edu.festu.ivankuznetsov.springsamplebo94xpri.service.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDateTime



@Controller
class OrdersController(private val ordersService: OrdersService,
                       private val teaBobaService: TeaBobaService,
    private val ordersRepository: OrdersRepository,
    private val userInfoRepository: UserInfoRepository,
    private val userInfoService: UserInfoService,
    private val orderContentService: OrderContentService

)

{

    @GetMapping("/orders")
    fun getOrdersForCurrentUser(model: Model): String {
        val orders = ordersService.getOrdersForCurrentUser()
        model.addAttribute("orders", orders.map { Order(it) })
        model["order_contetn"]=orderContentService.getAll()
        return "orders"
    }


    @PostMapping("/tea_boba/createOrder")
    @PreAuthorize("hasRole('USER')")
    fun createOrder(@RequestParam("selectedTeaBobaIds") selectedTeaBobaIds: List<Long>, model: Model): String {
        // Получите текущего пользователя
        val currentUser = getCurrentUser()
        val orderDateTime = LocalDateTime.now()



        // Проверяем, сохранен ли пользователь
        if (currentUser.id_user == null) {
            // Если пользователь не сохранен, сначала сохраняем его в базе данных
            userInfoRepository.save(currentUser)
        }
        // Создаем заказ
        val orders = ordersService.createOrder(selectedTeaBobaIds, currentUser, orderDateTime)
        ordersRepository.save(orders)

        // Помечаем чай с шариками как "orderCreated = true"
        selectedTeaBobaIds.forEach { teaBobaId ->
            val teaBoba = teaBobaService.getTeaBobaById(teaBobaId)
            teaBoba.orderCreated = true
            teaBobaService.save(teaBoba)
        }

        for (teaBobaId in selectedTeaBobaIds) {
            teaBobaService.updateTeaBobaPrice(teaBobaId, 0.0)  // Обновите 0.0 на необходимое значение
        }
        return "redirect:/orders"
    }
    private fun getCurrentUser(): UserInfo {
        val authentication = SecurityContextHolder.getContext().authentication
        val userInfoDetails = authentication.principal as UserInfoDetails
        return userInfoDetails.userInfo
    }


    @GetMapping("/user")
    fun userProfile(model: Model): String {

        if (!model.containsAttribute("user")) {
            val currentUser = getCurrentUser()
            model.addAttribute("user", currentUser)
        }

        return "userProfile"
    }


    @PostMapping("/user")
    fun updateProfile(@ModelAttribute("user") user: UserInfo): String {
        // Обновление информации о пользователе в базе данных
        userInfoService.updateUserInfo(user)
        return "userProfile"
    }


    @PostMapping("/user/delete")
    fun deleteUser(model: Model): String {

        val currentUser = getCurrentUser()

        userInfoService.deleteUser(currentUser.id_user!!.toLong())

        // Выход пользователя из системы
        SecurityContextHolder.clearContext()

        // Перенаправление пользователя на страницу выхода (или другую страницу)
        return "redirect:/login"
    }


    @PostMapping("/addNewUser")
    fun addNewUser(
        @RequestParam("email") email: String?,
        @RequestParam("name") name: String?,
        @RequestParam("password") password: String?
    ): String? {
        val userInfo = UserInfo(email = email, name = name, password = password)
        userInfoService.addUser(userInfo)

        val authentication: Authentication = UsernamePasswordAuthenticationToken(userInfo, null, emptyList())
        SecurityContextHolder.getContext().authentication = authentication

        return "redirect:/login"
    }
}