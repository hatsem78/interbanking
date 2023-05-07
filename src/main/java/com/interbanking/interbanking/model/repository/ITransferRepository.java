package com.interbanking.interbanking.model.repository;

import com.interbanking.interbanking.model.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITransferRepository extends JpaRepository<Transfer, Long> {

    Optional<Transfer> findByTransferDateAndCompanyId(Date transferDate, Long companyId);
    Optional<Transfer> findByTransferDate(Date transferDate);

    @Query(value="SELECT t " +
            " FROM Transfer t " +
            " WHERE MONTH(t.transferDate) = MONTH(:date) AND YEAR(t.transferDate) = YEAR(:date)"
    )
    List<Transfer> findByLastMonth(@Param("date") Date date);




}
