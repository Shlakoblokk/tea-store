package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BobaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.BobaRepository
import org.springframework.stereotype.Service

@Service
class BobaServiceImpl(private val bobaRepository: BobaRepository,): BobaService {

    override fun getAll():MutableList<BobaEntity> {
        return bobaRepository.findAll()
    }
    override fun save(boba: BobaEntity) {
        bobaRepository.save(boba)
    }
    override fun getById(id_boba: Int): BobaEntity {
        return bobaRepository.findById(id_boba).orElseThrow()
    }

    override fun delete(boba: BobaEntity) {
        bobaRepository.delete(boba)
    }

    override fun getAllBobaOptions(): List<BobaEntity> {
        return bobaRepository.findAll()
    }

}