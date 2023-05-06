package com.interbanking.interbanking.model.service;

import com.interbanking.interbanking.model.entity.MemberCompanies;
import java.util.Date;
import java.util.List;

public interface IMemberCompanies {
    MemberCompanies save(MemberCompanies memberCompanies);

    List<MemberCompanies> findByLastMonth(Date date);
}
