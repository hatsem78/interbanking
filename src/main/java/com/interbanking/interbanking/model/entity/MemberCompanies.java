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

    @ManyToMany
    @JoinTable(
            name="member_companies_company",
            joinColumns = @JoinColumn( name="member_companies_id"),
            inverseJoinColumns = @JoinColumn( name="company_id")
    )
    private Set<Company> company;

    @Column(name = "member_date")
    @Temporal(TemporalType.DATE)
    private Date memberDate;

    public MemberCompanies() {
    }

    public MemberCompanies(
            Set<Company> companyId,
            Date memberDate
    ) {
        this.company = companyId;
        this.memberDate = memberDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Company> getCompany() {
        return company;
    }

    public void setCompanyId(Set<Company> companyId) {
        this.company = company;
    }

    public Date getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(Date memberDate) {
        this.memberDate = memberDate;
    }
}
