package com.example.contact.service;

import com.example.contact.model.entity.Contact;
import com.example.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    public Contact getContactByUsername(String username) {
        return contactRepository.findById(username).
                orElseThrow(() -> new RuntimeException("username not found"));
    }

    public Contact createContact(Contact contact) {
        Optional<Contact> contactOpt = contactRepository.findById(contact.getUsername());
        if(!contactOpt.isPresent()) {
            return contactRepository.save(contact);
        } else {
            throw new RuntimeException("username not found");
        }
    }

    public Contact updateContact(String username, Contact updateContact) {
        Optional<Contact> contactOpt = contactRepository.findById(username);
        if(contactOpt.isPresent()) {
            return contactRepository.save(updateContact);
        } else {
            throw new RuntimeException("username not found");
        }
    }

    public void deleteContact(String username) {
        Optional<Contact> contactOpt = contactRepository.findById(username);
        if(contactOpt.isPresent()) {
            contactRepository.delete(contactOpt.get());
        } else {
            throw new RuntimeException("username not found");
        }
    }
}
