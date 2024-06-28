package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.OrderContentEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.OrderContentRepository
import org.springframework.stereotype.Service

@Service
class OrderContentServiceImpl(
    private val orderContentRepository: OrderContentRepository
) : OrderContentService {

    override fun save(orderContent: OrderContentEntity): OrderContentEntity {
        return orderContentRepository.save(orderContent)
    }

    override fun getAll():MutableList<OrderContentEntity> {
        return orderContentRepository.findAll()
    }


}