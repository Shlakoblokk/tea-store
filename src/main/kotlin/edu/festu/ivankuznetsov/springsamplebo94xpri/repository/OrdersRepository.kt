package edu.festu.ivankuznetsov.springsamplebo94xpri.repository

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.OrdersEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface OrdersRepository: JpaRepository<OrdersEntity, Int>{
    fun findByUserName(username: String): List<OrdersEntity>
}