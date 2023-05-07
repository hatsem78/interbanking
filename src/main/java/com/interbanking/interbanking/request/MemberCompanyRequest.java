package com.interbanking.interbanking.request;

import com.sun.istack.NotNull;

public class MemberCompanyRequest {

    @NotNull
    private Long companyId;

    public MemberCompanyRequest() {
    }

    public MemberCompanyRequest(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
