package org.example.internintelligence_portfolio.controllers.maincontroller;

import org.example.internintelligence_portfolio.dtos.contactdtos.ContactCreateDto;
import org.example.internintelligence_portfolio.dtos.contactdtos.ContactDto;
import org.example.internintelligence_portfolio.dtos.contactdtos.ContactUpdateDto;
import org.example.internintelligence_portfolio.dtos.projectdtos.ProjectCreateDto;
import org.example.internintelligence_portfolio.dtos.projectdtos.ProjectDto;
import org.example.internintelligence_portfolio.dtos.projectdtos.ProjectUpdateDto;
import org.example.internintelligence_portfolio.payloads.ApiResponse;
import org.example.internintelligence_portfolio.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> createContact(@ModelAttribute ContactCreateDto contactCreateDto) {
        ApiResponse response = contactService.createContact(contactCreateDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> getContacts() {
        List<ContactDto> response = contactService.getAllContacts();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(value = "/id")
    public ContactDto getContactById(@RequestParam Long id) {
        ContactDto contactDetail = contactService.getContactById(id);
        return contactDetail;
    }

    @PutMapping(value = "/id",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<ApiResponse> updateContact(@RequestParam Long id, @ModelAttribute ContactUpdateDto contactUpdateDto) {
        ApiResponse response = contactService.updateContactById(id, contactUpdateDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/id")
    public ResponseEntity<ApiResponse> deleteProject(@RequestParam Long id) {
        ApiResponse response = contactService.deleteContact(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
