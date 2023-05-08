package com.interbanking.interbanking;

import com.interbanking.interbanking.model.entity.Company;
import com.interbanking.interbanking.model.entity.MemberCompanies;
import com.interbanking.interbanking.model.service.CompanyServiceImpl;
import com.interbanking.interbanking.model.service.MemberCompaniesServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static com.interbanking.interbanking.utils.SiteLocale.getLastMonth;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
public class MemberCompaniesServiceImplTest {

    @MockBean
    private CompanyServiceImpl companyService;

    @MockBean
    private MemberCompaniesServiceImpl memberCompaniesService;

    @Before
    public void setUp() {
        this.companyService = Mockito.mock(CompanyServiceImpl.class);
        this.memberCompaniesService = Mockito.mock(MemberCompaniesServiceImpl.class);
    }
    @Test
    public void saveMemberCompanies() {

        Date date = new Date();
        when(companyService.save(any(Company.class))).then(new Answer<Company>() {
            Long secuencia = 8L;

            @Override
            public Company answer(InvocationOnMock invocation) {
                Company company = invocation.getArgument(0);
                company.setId(secuencia++);
                return company;
            }
        });

        Company company = new Company(
                "2029745312",
                "prueba",
                new Date()
        );
        company.setId(1L);

        Set<Company> setCompany = Collections.singleton(companyService.save(company));


        when(memberCompaniesService.save(any(MemberCompanies.class))).then(new Answer<MemberCompanies>() {
            Long secuencia = 8L;

            @Override
            public MemberCompanies answer(InvocationOnMock invocation) {
                MemberCompanies memberCompanies = invocation.getArgument(0);
                memberCompanies.setId(secuencia++);
                return memberCompanies;
            }
        });

        MemberCompanies memberCompanies = new MemberCompanies(
                setCompany,
                date
        );

        MemberCompanies result = memberCompaniesService.save(memberCompanies);

        assertEquals(result, memberCompanies);

    }
    @Test
    public void findByLastMonthTest() {

        Company company = new Company(
                "2029745312",
                "prueba",
                new Date()
        );
        company.setId(1L);

        Company company1 = new Company(
                "20297453121",
                "prueba1",
                new Date()
        );
        company.setId(2L);

        MemberCompanies memberCompanies = new MemberCompanies(
                Collections.singleton(company),
                getLastMonth(1).getTime()
        );

        MemberCompanies memberCompanies1 = new MemberCompanies(
                Collections.singleton(company1),
                getLastMonth(1).getTime()
        );

        List<MemberCompanies> memberCompaniesList = new ArrayList<>();

        memberCompaniesList.add(memberCompanies);
        memberCompaniesList.add(memberCompanies1);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);

        when(memberCompaniesService.findByLastMonth(getLastMonth(1).getTime())).thenReturn(memberCompaniesList);

        List<MemberCompanies> result = memberCompaniesService.findByLastMonth(cal.getTime());

        assertEquals(result, memberCompaniesList);


    }


}
