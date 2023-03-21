package com.example.contact.controller;

import com.example.contact.model.entity.Contact;
import com.example.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ContactController {

    @Autowired
    private ContactService contractService;

    @GetMapping("/all")
    public Iterable<Contact> getAllContact() {
        return contractService.getAllContact();
    }

    @PostMapping("/create")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        try{
            Contact newContact = contractService.createContact(contact);
            return ResponseEntity.ok(newContact);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Contact> deleteContact(@PathVariable String username) {
        try{
            contractService.deleteContact(username);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<Contact> getContactByUsername(@PathVariable String username) {
        try{
            Contact contact = contractService.getContactByUsername(username);
            return ResponseEntity.ok(contact);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<Contact> updateContact(@PathVariable String username,
                                                @RequestBody Contact contact) {
        try{
            Contact updateContact = contractService.updateContact(username, contact);
            return ResponseEntity.ok(updateContact);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
