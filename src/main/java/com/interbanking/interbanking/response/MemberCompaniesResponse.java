package com.interbanking.interbanking.response;

import java.util.Date;
import java.util.stream.LongStream;

public class MemberCompaniesResponse {

    private String memberDate;
    private LongStream companyId;

    private String companyName;

    public MemberCompaniesResponse(
            String memberDate,
            LongStream companyId,
            String companyName
    ) {
        this.memberDate = memberDate;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public String getMemberDate() {
        return memberDate;
    }

    public void setMemberDate(String memberDate) {
        this.memberDate = memberDate;
    }

    public LongStream getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongStream companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
