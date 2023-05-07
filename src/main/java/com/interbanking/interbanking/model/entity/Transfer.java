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

    @ManyToMany
    @JoinTable(
            name="transfer_company",
            joinColumns = @JoinColumn( name="transfer_id"),
            inverseJoinColumns = @JoinColumn( name="company_id")
    )
    private Set<Company> companyId;

    public Transfer() {
    }

    public Transfer(
            Account account,
            Double amount,
            Date transferDate,
            Set<Company> companyId
    ) {
        this.account = account;
        this.amount = amount;
        this.transferDate = transferDate;
        this.companyId = companyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public Set<Company> getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Set<Company> companyId) {
        this.companyId = companyId;
    }
}
