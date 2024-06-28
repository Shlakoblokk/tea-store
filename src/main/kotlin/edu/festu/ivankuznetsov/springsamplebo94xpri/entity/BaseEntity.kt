package edu.festu.ivankuznetsov.springsamplebo94xpri.entity

import javax.persistence.*

@Entity
@Table(name = "base")
class BaseEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id_base: Int = 0,
    @Column
    var base_description: String = "",

    @OneToMany(mappedBy="base")
    //var TeaHave: List<TeaEntity> = mutableListOf()
    var tea: List<TeaEntity> = mutableListOf()
)