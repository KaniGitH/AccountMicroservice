package com.account.accountservice.controller;

import com.account.accountservice.entity.Account;
import com.account.accountservice.entity.Customer;
import com.account.accountservice.response.ResponseHandler;
import com.account.accountservice.service.AccountService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService service;
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/addAccount")
    public ResponseEntity<Object> addAccount(@RequestBody Account account) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<JsonNode> result = restTemplate.exchange("http://localhost:8081/customerById/" + account.getCustomerId(), HttpMethod.GET, entity, JsonNode.class);
        JsonNode json = result.getBody();

        JsonNode customer = json.get("data");
        if (customer.size() == 0) {
            return ResponseHandler.generateResponse("Customer not exist, so cont to add Account!", HttpStatus.NOT_FOUND, account);

        } else {
            // save account details
            return ResponseHandler.generateResponse("Account added successfully!", HttpStatus.OK, service.saveAccount(account));
        }
    }

    @PostMapping("/addAccounts")
    public ResponseEntity<Object> addAccounts(@RequestBody List<Account> accounts) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        List<Account> tempAccounts = accounts;

        for (Account account : accounts) {
            ResponseEntity<JsonNode> result = restTemplate.exchange("http://localhost:8081/customerById/" + account.getCustomerId(), HttpMethod.GET, entity, JsonNode.class);
            JsonNode json = result.getBody();

            JsonNode customer = json.get("data");
            if (customer.size() == 0) {
                accounts.remove(account);
            }
        }

        if (accounts.isEmpty()) {
            return ResponseHandler.generateResponse("Customer not exist, so cont to add Account!", HttpStatus.NOT_FOUND, tempAccounts);
        } else {
            // save accounts details
            return ResponseHandler.generateResponse("Accounts added successfully!", HttpStatus.OK, service.saveAccounts(accounts));
        }
    }

    @GetMapping("/accounts")
    public List<Account> findAllAccount() {
        // get all accounts details
        return service.getAccounts();
    }

    @GetMapping("/accountById/{id}")
    public ResponseEntity<Object> findAccountById(@PathVariable String id) {

        try {
            int nId = Integer.parseInt(id);

            // get account by Account id
            Account account = service.getAccountById(nId);
            if (account == null) {
                return ResponseHandler.generateResponse("Given Account id - " + nId + " not exist!", HttpStatus.NOT_FOUND, null);
            } else {
                return ResponseHandler.generateResponse("Given Account id - " + nId + " exist!", HttpStatus.OK, account);
            }

        } catch (NumberFormatException e) {
            return ResponseHandler.generateResponse("Please enter valid Account Id in number format!", HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/accountByCustomerId/{customerId}")
    public ResponseEntity<Object> findAccountByCustomerId(@PathVariable String customerId) {
        try {
            int nId = Integer.parseInt(customerId);

            // get accounts by Customer id
            List<Account> accounts = service.getAccountByCustomerId(nId);
            if (accounts.size() == 0) {
                return ResponseHandler.generateResponse("Given Customer id - " + nId + " does not have account!", HttpStatus.NOT_FOUND, null);
            } else {
                return ResponseHandler.generateResponse("Given Customer id - " + nId + " having accounts!", HttpStatus.OK, accounts);
            }

        } catch (NumberFormatException e) {
            return ResponseHandler.generateResponse("Please enter valid Customer Id in number format!", HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/accountByName/{accountName}")
    public List<Account> getAccountByAccountHolderName(@PathVariable String accountName) {
        // get Account by account holder name
        return service.getAccountByAccountHolderName(accountName);
    }

    @PutMapping("/updateAccount")
    public Account updateAccount(@RequestBody Account account) {
        // update Account details
        return service.updateAccount(account);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<Object> deleteAccountById(@PathVariable String id) {

        try {
            int nId = Integer.parseInt(id);

            // get account by Account id
            Account account = service.getAccountById(nId);
            if (account == null) {
                return ResponseHandler.generateResponse("Given Account id - " + nId + " not exist!", HttpStatus.NOT_FOUND, null);
            } else {
                // delete Account
                return ResponseHandler.generateResponse(service.deleteAccountById(nId), HttpStatus.OK, account);
            }

        } catch (NumberFormatException e) {
            return ResponseHandler.generateResponse("Please enter valid Account Id in number format!", HttpStatus.BAD_REQUEST, null);
        }

    }
//
//    @RequestMapping(value = "/template/customers")
//    public Customer[] getCustomers() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//        return restTemplate.exchange("http://localhost:8081/customers", HttpMethod.GET, entity, Customer[].class).getBody();
//    }
//
//    @RequestMapping(value = "/template/customerById/{id}")
//    public Customer getCustomer(@PathVariable int id) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//        return restTemplate.exchange("http://localhost:8081/customerById/" + id, HttpMethod.GET, entity, Customer.class).getBody();
//    }

}

