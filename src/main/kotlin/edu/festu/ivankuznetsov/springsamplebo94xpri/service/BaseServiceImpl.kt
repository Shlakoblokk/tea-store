package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BaseEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BobaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.BaseRepository
import org.springframework.stereotype.Service

@Service
class BaseServiceImpl(private val baseRepository: BaseRepository): BaseService {

    override fun getAll():MutableList<BaseEntity> {
        return baseRepository.findAll()
    }
    override fun save(base: BaseEntity) {
        baseRepository.save(base)
    }
    override fun getById(id_base: Int): BaseEntity {
        return baseRepository.findById(id_base).orElseThrow()
    }

    override fun delete(base: BaseEntity) {
        baseRepository.delete(base)
    }

}