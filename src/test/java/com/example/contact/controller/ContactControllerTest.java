package com.example.contact.controller;

import com.example.contact.model.entity.Contact;
import com.example.contact.repository.ContactRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;
    @Autowired
    ContactRepository contactRepository;

    private String getContractUrl() {
        return "http://localhost:"+ port +"/api/";
    }

    @Test
    public void testCRUDOperations(){
        // Insert  3 inputs
        Contact newEntry = new Contact("name1", "010-1111-1111", "name1@gmail.com");
        contactRepository.save(newEntry);
        Contact newEntry2 = new Contact("name2", "010-1111-1111", "name2@gmail.com");
        contactRepository.save(newEntry2);
        Contact newEntry3 = new Contact("name3", "010-1111-1111", "name3@gmail.com");
        contactRepository.save(newEntry3);

        // 1. Read Entry "api/get",  "username=name1"
        ResponseEntity<Contact> g_response = restTemplate.getForEntity(getContractUrl()+"get/name1", Contact.class);
        Assertions.assertEquals(g_response.getStatusCode(), HttpStatus.OK);


        // 2. Update Entry "api/update/name1"
        Contact updateEntry = new Contact("name1","010-1234-5678","name1@gmail.com");
        restTemplate.put(getContractUrl()+"update/name1",updateEntry);

        ResponseEntity<Contact> u_response = restTemplate.getForEntity(getContractUrl()+"get/name1", Contact.class);
        Assertions.assertEquals(u_response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(u_response.getBody().getPhoneNumber(), updateEntry.getPhoneNumber());

        // 3. Delete Entry
        restTemplate.delete(getContractUrl()+"delete/name1");
        ResponseEntity<Contact> d_response = restTemplate.getForEntity(getContractUrl()+"get/name1", Contact.class);
        Assertions.assertEquals(d_response.getStatusCode(), HttpStatus.NOT_FOUND);

        restTemplate.delete(getContractUrl()+"delete/name2");
        ResponseEntity<Contact> d2_response = restTemplate.getForEntity(getContractUrl()+"get/name1", Contact.class);
        Assertions.assertEquals(d2_response.getStatusCode(), HttpStatus.NOT_FOUND);

        // 4. Create Entry
        ResponseEntity<Contact> c_response = restTemplate.postForEntity(getContractUrl()+"create", newEntry, Contact.class);
        Assertions.assertEquals(c_response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Contact> c2_response = restTemplate.postForEntity(getContractUrl()+"create", newEntry, Contact.class);
        Assertions.assertEquals(c2_response.getStatusCode(), HttpStatus.CONFLICT);

    }

}
