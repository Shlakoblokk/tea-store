package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.OrderContentEntity

interface OrderContentService {

    fun save(orderContent: OrderContentEntity): OrderContentEntity

    fun getAll():MutableList<OrderContentEntity>
}