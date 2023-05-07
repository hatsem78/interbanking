package com.interbanking.interbanking.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EAccount name;

    public Account() {
    }

    public Account(EAccount eAccount) {
        this.name = eAccount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EAccount getName() {
        return name;
    }

    public void setName(EAccount name) {
        this.name = name;
    }
}
