package org.example.internintelligence_portfolio.services;

import org.example.internintelligence_portfolio.dtos.contactdtos.ContactCreateDto;
import org.example.internintelligence_portfolio.dtos.contactdtos.ContactDto;
import org.example.internintelligence_portfolio.dtos.contactdtos.ContactUpdateDto;
import org.example.internintelligence_portfolio.payloads.ApiResponse;

import java.util.List;

public interface ContactService {
    ApiResponse createContact(ContactCreateDto contactCreateDto);
    List<ContactDto> getAllContacts();
    ApiResponse getContactById(Long id);
    ApiResponse updateContactById(Long id, ContactUpdateDto contactUpdateDto);
    ApiResponse deleteContact(Long id);
}
