package com.interbanking.interbanking.model.repository;

import com.interbanking.interbanking.model.entity.MemberCompanies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IMemberCompaniesRepository extends JpaRepository<MemberCompanies, Long> {
    @Query(value="SELECT mc " +
            " FROM MemberCompanies mc" +
            " WHERE MONTH(mc.memberDate) = MONTH(:date) AND YEAR(mc.memberDate) = YEAR(:date)"
    )
    List<MemberCompanies> findByLastMonth(@Param("date") Date date);
}
