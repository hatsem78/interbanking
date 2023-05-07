package com.interbanking.interbanking.model.service;

import com.interbanking.interbanking.model.entity.Transfer;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITransfer {
    Transfer save(Transfer transfer);
    Optional<Transfer> findByDateToCompanyId(Date date, Long companyId);
    Optional<Transfer> findByDate(Date date);
    List<Transfer> findByLastMonth(Date date);

    List<Transfer> findByAll();


}
