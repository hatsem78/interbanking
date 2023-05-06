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

    @OneToMany(mappedBy = "member_companies")
    @Column(name = "company_id")
    private Set<Company> companyId;

    @Column(name = "member_date")
    @Temporal(TemporalType.DATE)
    private Date memberDate;

    public MemberCompanies(
            Set<Company> companyId,
            Date memberDate
    ) {
        this.companyId = companyId;
        this.memberDate = memberDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Company> getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Set<Company> companyId) {
        this.companyId = companyId;
    }

    public Date getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(Date memberDate) {
        this.memberDate = memberDate;
    }
}
