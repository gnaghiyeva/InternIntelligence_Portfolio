package org.example.internintelligence_portfolio.services.impl;

import org.example.internintelligence_portfolio.dtos.contactdtos.ContactCreateDto;
import org.example.internintelligence_portfolio.dtos.contactdtos.ContactDto;
import org.example.internintelligence_portfolio.dtos.contactdtos.ContactUpdateDto;
import org.example.internintelligence_portfolio.models.Contact;
import org.example.internintelligence_portfolio.payloads.ApiResponse;
import org.example.internintelligence_portfolio.repositories.ContactRepository;
import org.example.internintelligence_portfolio.services.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse createContact(ContactCreateDto contactCreateDto) {
        Contact contact = modelMapper.map(contactCreateDto, Contact.class);
        contactRepository.save(contact);
        return new ApiResponse(true, "Contact created");
    }

    @Override
    public List<ContactDto> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        List<ContactDto> contactDtos = contacts.stream().map(contact -> modelMapper.map(contact, ContactDto.class)).collect(Collectors.toList());
        return contactDtos;
    }

    @Override
    public ContactDto getContactById(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Contact not found with id: " + id));
        ContactDto contactDto = modelMapper.map(contact, ContactDto.class);
        return contactDto;
    }

    @Override
    public ApiResponse updateContactById(Long id, ContactUpdateDto contactUpdateDto) {
       Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Contact not found with id: " + id));
       if(contactUpdateDto.getFullName() != null || !contactUpdateDto.getFullName().isEmpty()){
           contact.setFullName(contactUpdateDto.getFullName());
       }
        if(contactUpdateDto.getEmail() != null || !contactUpdateDto.getEmail().isEmpty()){
            contact.setEmail(contactUpdateDto.getEmail());
        }
        if(contactUpdateDto.getMessage() != null || !contactUpdateDto.getMessage().isEmpty()){
            contact.setMessage(contactUpdateDto.getMessage());
        }

        contactRepository.save(contact);
        return new ApiResponse(true, "Contact updated");
    }

    @Override
    public ApiResponse deleteContact(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Contact not found with id: " + id));
        contactRepository.delete(contact);
        return new ApiResponse(true, "Contact deleted");
    }
}
