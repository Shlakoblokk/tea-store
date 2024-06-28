package edu.festu.ivankuznetsov.springsamplebo94xpri.entity

import javax.persistence.*
import javax.xml.crypto.Data


@Entity
class UserInfo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id_user: Int? = null,
    var email: String? = null,
    var name: String? = null,
    var password: String? = null,
    var roles: String? = "ROLE_USER",


    @OneToMany(mappedBy = "user")
    var orders: List<OrdersEntity> = mutableListOf(),

    @OneToMany(mappedBy = "user")
    var tea_boba: List<OrdersEntity> = mutableListOf()

)