package edu.festu.ivankuznetsov.springsamplebo94xpri.entity
import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "order_content")
class OrderContentEntity(
    @EmbeddedId
    var id: OrderContentKey,

    val price: Double,

    @ManyToOne
    @MapsId("teaBobaId")
    @JoinColumn(name = "tea_boba_id")
    var tea_boba: TeaBobaEntity,

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    var orders: OrdersEntity,


)

@Embeddable
class OrderContentKey(
    @Column(name = "tea_boba_id")
    var teaBobaId: Long? = null,

    @Column(name = "order_id")
    var orderId: Int? = null

) : Serializable
