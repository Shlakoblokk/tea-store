package edu.festu.ivankuznetsov.springsamplebo94xpri.repository

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BaseRepository: JpaRepository<BaseEntity, Int>