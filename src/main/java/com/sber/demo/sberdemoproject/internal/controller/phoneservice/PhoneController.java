package com.sber.demo.sberdemoproject.internal.controller.phoneservice;

import com.sber.demo.sberdemoproject.internal.entity.phoneservice.exceptions.ValidationException;
import com.sber.demo.sberdemoproject.internal.entity.phoneservice.items.PhoneItem;
import com.sber.demo.sberdemoproject.internal.entity.phoneservice.requests.AddItemRequest;
import com.sber.demo.sberdemoproject.internal.entity.phoneservice.requests.RemoveItemRequest;
import com.sber.demo.sberdemoproject.internal.entity.phoneservice.requests.UpdateItemRequest;
import com.sber.demo.sberdemoproject.internal.usecase.phoneservice.PhoneUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PhoneController {
    private final PhoneUseCase phoneUseCase;

    @Autowired
    public PhoneController(PhoneUseCase phoneUseCase) {
        this.phoneUseCase = phoneUseCase;
    }

    @GetMapping("/phone_items/list")
    public ResponseEntity<List<PhoneItem>> getAllItems() {
        log.info("Controller getAllItems: list all items called");

        List<PhoneItem> merchItemList = phoneUseCase.findAll();

        return ResponseEntity.ok(merchItemList);
    }

    @GetMapping("/phone_items/get/{id}")
    public ResponseEntity<PhoneItem> getItem(@PathVariable Long id) {
        log.info("Controller getItem: get item with id {} called", id);

        PhoneItem phoneItem = phoneUseCase.findById(id);

        return ResponseEntity.ok(phoneItem);
    }

    @PostMapping("/phone_items/add")
    public ResponseEntity<PhoneItem> addItem(@RequestBody AddItemRequest request) {
        log.info("Controller addItem: add item called with request {}", request);

        try {
            PhoneItem phoneItem = phoneUseCase.addItem(request);

            log.info("Controller addItem: item added {}", phoneItem);

            return ResponseEntity.ok(phoneItem);
        } catch (ValidationException e) {
            log.error("Controller addItem: couldn't add item due to validation error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            log.error("Controller addItem: couldn't add item {}", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping("/phone_items/remove")
    public ResponseEntity<PhoneItem> removeItem(@RequestBody RemoveItemRequest request) {
        log.info("Controller removeItem: remove item called with request {}", request);

        phoneUseCase.removeItem(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/phone_items/update")
    public ResponseEntity<PhoneItem> updateItem(@RequestBody UpdateItemRequest request) {
        log.info("Controller updateItem: update item called with request {}", request);

        try {
            PhoneItem phoneItem = phoneUseCase.updateItem(request);

            log.info("Controller updateItem: item updated {}", phoneItem);

            return ResponseEntity.ok(phoneItem);
        } catch (ValidationException e) {
            log.error("Controller updateItem: couldn't update item due to validation error: {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            log.error("Controller updateItem: couldn't update item {}", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
