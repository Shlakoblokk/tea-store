package edu.festu.ivankuznetsov.springsamplebo94xpri.model

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.OrdersEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaBobaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.UserInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.*

class Order(ordersEntity: OrdersEntity){

    var id_orders: Int?

    var orderDateTime: String

    //@Column(name = "total_price")
    //var totalPrice: Double = 0.0,
    var totalPrice: Double


    var tea_boba: List<TeaBobaEntity>

    var user: UserInfo
    init {
        id_orders = ordersEntity.id_orders
        orderDateTime = ordersEntity.orderDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
        totalPrice = ordersEntity.totalPrice
        tea_boba = ordersEntity.tea_boba
        user = ordersEntity.user
    }
}