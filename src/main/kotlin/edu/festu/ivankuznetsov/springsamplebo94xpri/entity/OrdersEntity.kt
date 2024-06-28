package edu.festu.ivankuznetsov.springsamplebo94xpri.entity

import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "orders")
class OrdersEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id_orders: Int? = null,

    @Column(name = "order_date_time")
    var orderDateTime: LocalDateTime = LocalDateTime.now(),

    //@Column(name = "total_price")
    //var totalPrice: Double = 0.0,

    @Column(name = "total_price")
    var totalPrice: Double = 0.0,


    @ManyToMany(mappedBy = "orders")
    var tea_boba: List<TeaBobaEntity> = mutableListOf(),


    @ManyToOne()
    @JoinColumn(name = "user_id")
    var user: UserInfo = UserInfo()


)