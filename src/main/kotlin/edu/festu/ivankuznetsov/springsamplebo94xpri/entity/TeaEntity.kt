package edu.festu.ivankuznetsov.springsamplebo94xpri.entity

import javax.persistence.*

@Entity
@Table(name = "tea")
 class TeaEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tea", nullable = false)
    var teaId: Long? = null,
    @Column
    var name: String? = null,
    @Column
    var description: String? = null,


    var price: Double = 0.0,

    @ManyToOne()
    @JoinColumn(
        name="base_id",
        nullable=false,
        updatable=false)
    var base: BaseEntity = BaseEntity(),
    //var base: List<BaseEntity> = mutableListOf(),

    @OneToMany(mappedBy = "tea")
    var teaBoba: MutableList<TeaBobaEntity> = mutableListOf()


    /*@ManyToMany
    @JoinTable(
        name="tea_boba",
        joinColumns = [JoinColumn(name="tea_id")],
        inverseJoinColumns = [JoinColumn(name="boba_id")]
    )
    var boba: List<BobaEntity> = mutableListOf(),*/
 )










