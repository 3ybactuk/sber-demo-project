package com.sber.demo.sberdemoproject.controller.phoneservice;

import com.sber.demo.sberdemoproject.entity.phoneservice.items.PhoneItem;
import com.sber.demo.sberdemoproject.entity.phoneservice.requests.AddItemRequest;
import com.sber.demo.sberdemoproject.usecase.phoneservice.PhoneUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneController {
    private final PhoneUseCase phoneUseCase;

    @Autowired
    public PhoneController(PhoneUseCase phoneUseCase) {
        this.phoneUseCase = phoneUseCase;
    }

    @GetMapping("/phone_items/list")
    public ResponseEntity<List<PhoneItem>> getAllItems() {
        List<PhoneItem> merchItemList = phoneUseCase.findAll();

        return ResponseEntity.ok(merchItemList);
    }

    @GetMapping("/phone_items/get/{id}")
    public ResponseEntity<PhoneItem> getItem(@PathVariable Long id) {
        PhoneItem phoneItem = phoneUseCase.findById(id);

        return ResponseEntity.ok(phoneItem);
    }

    @PostMapping("/phone_items/add")
    public ResponseEntity<PhoneItem> addItem(@RequestBody AddItemRequest request) {
        PhoneItem phoneItem = phoneUseCase.addItem(request);

        return ResponseEntity.ok(phoneItem);
    }
}
