package edu.festu.ivankuznetsov.springsamplebo94xpri.entity

import javax.persistence.*

@Entity
@Table(name = "boba")
class BobaEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id_boba: Int = 0,
    @Column
    var boba_description: String = "",

    var price: Double = 0.0,

    @OneToMany(mappedBy = "boba")
    var teaBobas: MutableList<TeaBobaEntity> = mutableListOf()

   /* @ManyToMany(mappedBy = "boba")
    var tea: List<TeaEntity> = mutableListOf(),*/
)