package edu.festu.ivankuznetsov.springsamplebo94xpri.repository

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.OrderContentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderContentRepository : JpaRepository<OrderContentEntity, Long> {
}