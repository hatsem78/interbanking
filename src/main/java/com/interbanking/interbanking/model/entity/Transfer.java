package com.interbanking.interbanking.model.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    private Double amount;

    @Column(name = "transfer_date")
    @Temporal(TemporalType.DATE)
    private Date transferDate;

    @OneToMany(mappedBy="transfer")
    @Column(name = "company_id")
    private Set<Company> companyId;

    /*importe
    cuenta(debito, credito)
    fecha
            id_empresa*/


}
