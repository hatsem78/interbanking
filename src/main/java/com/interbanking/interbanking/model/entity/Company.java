package com.interbanking.interbanking.model.entity;


import com.interbanking.interbanking.utils.decorator.ValidCuil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(
        name = "company",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "cuit"),
        }
)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 13)
    @NotBlank
    @ValidCuil
    private String cuit;
    @NotBlank
    @Column(name = "business_name")
    private String businessName;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Column(name = "accession_date")
    @Temporal(TemporalType.DATE)
    private Date accessionDate;

    public Company() {
    }

    public Company(
            String cuit,
            String businessName,
            Date accessionDate
    ) {
        this.cuit = cuit;
        this.businessName = businessName;
        this.accessionDate = accessionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getAccessionDate() {
        return accessionDate;
    }

    public void setAccessionDate(Date accessionDate) {
        this.accessionDate = accessionDate;
    }
}
