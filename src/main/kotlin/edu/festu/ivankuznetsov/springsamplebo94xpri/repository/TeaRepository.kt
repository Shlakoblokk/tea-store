package edu.festu.ivankuznetsov.springsamplebo94xpri.repository

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BaseEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BobaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TeaRepository: JpaRepository<TeaEntity, Long>