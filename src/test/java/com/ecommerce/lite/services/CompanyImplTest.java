package com.ecommerce.lite.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ecommerce.lite.entities.Company;
import com.ecommerce.lite.repositories.ICompanyRepository;
import com.ecommerce.lite.service.impl.CompanyServiceImpl;

public class CompanyImplTest {
	@InjectMocks
    private CompanyServiceImpl companyServiceImpl;

    @Mock
    private ICompanyRepository iCompanyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCompany_success() throws Exception {
        Company company = new Company();
        company.setNit(1);
        when(iCompanyRepository.findById(company.getNit())).thenReturn(Optional.empty());
        when(iCompanyRepository.save(company)).thenReturn(company);
        Company result = companyServiceImpl.createCompany(company);
        assertNotNull(result);
        verify(iCompanyRepository, times(1)).findById(company.getNit());
        verify(iCompanyRepository, times(1)).save(company);
    }

    @Test
    void createCompany_alreadyExists() {
        Company company = new Company();
        company.setNit(1);
        when(iCompanyRepository.findById(company.getNit())).thenReturn(Optional.of(company));

        Exception exception = assertThrows(Exception.class, () -> companyServiceImpl.createCompany(company));
        assertTrue(exception.getMessage().contains("Company already exist"));
        verify(iCompanyRepository, times(1)).findById(company.getNit());
        verify(iCompanyRepository, never()).save(company);
    }

    @Test
    void createCompany_exceptionWhileSaving() {
        Company company = new Company();
        company.setNit(1);
        when(iCompanyRepository.findById(company.getNit())).thenReturn(Optional.empty());
        when(iCompanyRepository.save(company)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> companyServiceImpl.createCompany(company));
        assertTrue(exception.getMessage().contains("Error saving company"));
        verify(iCompanyRepository, times(1)).findById(company.getNit());
        verify(iCompanyRepository, times(1)).save(company);
    }

    @Test
    void deleteCompany_success() throws Exception {
        Integer companyId = 1;
        Company company = new Company();
        when(iCompanyRepository.findById(companyId)).thenReturn(Optional.of(company));

        companyServiceImpl.deleteCompany(companyId);

        verify(iCompanyRepository, times(1)).findById(companyId);
        verify(iCompanyRepository, times(1)).deleteById(companyId);
    }

    @Test
    void deleteCompany_notFound() {
        Integer companyId = 1;
        when(iCompanyRepository.findById(companyId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> companyServiceImpl.deleteCompany(companyId));
        assertTrue(exception.getMessage().contains("Company not found"));
        verify(iCompanyRepository, times(1)).findById(companyId);
        verify(iCompanyRepository, never()).deleteById(companyId);
    }

    @Test
    void deleteCompany_exceptionWhileDeleting() {
        Integer companyId = 1;
        when(iCompanyRepository.findById(companyId)).thenReturn(Optional.of(new Company()));
        doThrow(new RuntimeException("Database error")).when(iCompanyRepository).deleteById(companyId);

        Exception exception = assertThrows(Exception.class, () -> companyServiceImpl.deleteCompany(companyId));
        assertTrue(exception.getMessage().contains("Error deleting company"));
        verify(iCompanyRepository, times(1)).deleteById(companyId);
    }

    @Test
    void editCompany_success() throws Exception {
        Company company = new Company();
        company.setNit(1);
        when(iCompanyRepository.findById(company.getNit())).thenReturn(Optional.of(company));

        companyServiceImpl.editCompany(company);

        verify(iCompanyRepository, times(1)).findById(company.getNit());
        verify(iCompanyRepository, times(1)).save(company);
    }

    @Test
    void editCompany_notFound() {
        Company company = new Company();
        company.setNit(1);
        when(iCompanyRepository.findById(company.getNit())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> companyServiceImpl.editCompany(company));
        assertEquals("Company not found", exception.getMessage());
        verify(iCompanyRepository, times(1)).findById(company.getNit());
        verify(iCompanyRepository, never()).save(company);
    }

    @Test
    void getAllCompanies_success() {
        List<Company> companyList = new ArrayList<>();
        companyList.add(new Company());
        when(iCompanyRepository.findAll()).thenReturn(companyList);

        List<Company> result = companyServiceImpl.getAllCompanies();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(iCompanyRepository, times(1)).findAll();
    }
}
