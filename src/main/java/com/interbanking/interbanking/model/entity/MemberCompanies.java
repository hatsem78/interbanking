package com.interbanking.interbanking.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "member_companies")
public class MemberCompanies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="member_companies")
    @Column(name = "company_id")
    private Set<Company> companyId;

    @Column(name = "member_date")
    @Temporal(TemporalType.DATE)
    private Date memberDate;
}
