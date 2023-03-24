package com.example.demo;

import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.util.concurrent.ExecutionError;

@RestController
public class CRUDController {

    public CRUDService crudService;

    public CRUDController(CRUDService crudService) {
        this.crudService = crudService;
    }

    @PostMapping("/create")
    public String createCRUD(@RequestBody crud crud) throws InterruptedException, ExecutionError, ExecutionException {
        return crudService.createCRUD(crud, crud.getPhone());
    }

    @GetMapping("/get")
    public crud getCRUD(@RequestParam String phone)
            throws InterruptedException, ExecutionError, ExecutionException {
        return crudService.getCRUD(phone);
    }

    @PutMapping("/update")
    public String updateCRUD(@RequestBody crud crud) throws InterruptedException, ExecutionError, ExecutionException {
        return crudService.updateCRUD(crud, crud.getPhone());
    }

    @PutMapping("/delete")
    public String deleteCRUD(@RequestParam String phone) throws InterruptedException, ExecutionError {
        return crudService.deleteCRUD(phone);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }
}
