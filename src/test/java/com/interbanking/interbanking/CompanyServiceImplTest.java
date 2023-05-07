package com.interbanking.interbanking;


import com.interbanking.interbanking.model.entity.Company;
import com.interbanking.interbanking.model.service.CompanyServiceImpl;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CompanyServiceImplTest {

    @MockBean
    private CompanyServiceImpl companyService;

    @Before
    public void setUp() {
        this.companyService = Mockito.mock(CompanyServiceImpl.class);
    }

    @Test
    public void whenAccountSave() {

        when(companyService.save(any(Company.class))).then(new Answer<Company>(){
            Long secuencia = 8L;
            @Override
            public Company answer(InvocationOnMock invocation) {
                Company company = invocation.getArgument(0);
                company.setId(secuencia++);
                return company;
            }
        });

        Company company = new Company(
                "20297453124",
                "prueba",
                new Date()
        );
        company.setId(1L);

        Company companySave = companyService.save(company);

        assertEquals(company, companySave);
    }

    @Test
    public void companyFindById(){

        Company company = new Company(
                "2029745312",
                "prueba",
                new Date()
        );
        company.setId(1L);

        when(companyService.findById(company.getId())).thenReturn(Optional.of(company));

        Optional<Company> found = companyService.findById(company.getId());

        AssertionsForInterfaceTypes.assertThat(found).isEqualTo(Optional.of(company));

    }

    @Test
    public void companyNotFindById(){

        /*Account account = new Account(EAccount.DEBIT_ACCOUNT);
        account.setId(1);*/

        Company company = new Company(
                "2029745312",
                "prueba",
                new Date()
        );
        company.setId(1L);

        Optional<Company> found = companyService.findById(company.getId());

        assertFalse(found.isPresent());

    }

}
