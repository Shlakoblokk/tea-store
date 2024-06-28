package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BobaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.BobaRepository
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.TeaBobaRepository
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.TeaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TeaServiceImpl(private val teaRepository: TeaRepository,
                     private val bobaRepository: BobaRepository,
                     private val teaBobaRepository: TeaBobaRepository,
                     private val teaBobaService: TeaBobaService
): TeaService {

     override fun getAll():MutableList<TeaEntity> {
        return teaRepository.findAll()
    }

     override fun save(tea: TeaEntity) {
        teaRepository.save(tea)
    }


     override fun getById(teaId: Long): TeaEntity {
        return teaRepository.findById(teaId).orElseThrow()
    }

     override fun delete(tea: TeaEntity) {
        teaRepository.delete(tea)
    }

    @Transactional
    override fun updateTeaAndTeaBoba(tea: TeaEntity) {
        val existingTea = tea.teaId?.let { teaRepository.findById(it).orElse(null) }

        if (existingTea != null) {

            existingTea.name = tea.name
            existingTea.description = tea.description
            existingTea.price = tea.price
            existingTea.base = tea.base


            teaRepository.save(existingTea)

            // Обновите цену чая в tea_boba
            val teaBobas = teaBobaRepository.findAllByTea(existingTea)

            teaBobas.forEach { teaBoba ->
                teaBobaRepository.save(teaBoba)
            }
        }
    }


}






