package edu.festu.ivankuznetsov.springsamplebo94xpri.entity
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "tea_boba")
class TeaBobaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tea_boba")
    var id: Long? = null,

    @Column(name = "quantity")
    var quantity: Int? = null,

    @ManyToOne
    @JoinColumn(name = "tea_id")
    var tea: TeaEntity? = null,

    @ManyToOne
    @JoinColumn(name = "boba_id")
    var boba: BobaEntity? = null,


    @Column(name = "order_created")
    var orderCreated: Boolean = false,

    var price: Double? = null, // поле для цены


    @OneToMany(mappedBy = "tea_boba")
    var orderContents: MutableList<OrderContentEntity> = mutableListOf(),


    @ManyToMany
    @JoinTable(
        name="order_content",
        joinColumns = [JoinColumn(name="tea_boba_id")],
        inverseJoinColumns = [JoinColumn(name="order_id")]
    )
    var orders: List<OrdersEntity> = mutableListOf(),


    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserInfo = UserInfo()
)