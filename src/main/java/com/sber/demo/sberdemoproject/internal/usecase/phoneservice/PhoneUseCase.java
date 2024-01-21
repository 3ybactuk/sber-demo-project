package com.sber.demo.sberdemoproject.internal.usecase.phoneservice;

import com.sber.demo.sberdemoproject.internal.entity.phoneservice.exceptions.ValidationException;
import com.sber.demo.sberdemoproject.internal.entity.phoneservice.items.PhoneItem;
import com.sber.demo.sberdemoproject.internal.entity.phoneservice.requests.AddItemRequest;
import com.sber.demo.sberdemoproject.internal.entity.phoneservice.requests.RemoveItemRequest;
import com.sber.demo.sberdemoproject.internal.entity.phoneservice.requests.UpdateItemRequest;
import com.sber.demo.sberdemoproject.internal.repository.phoneservice.PhoneRepository;
import com.sber.demo.sberdemoproject.internal.repository.phoneservice.entities.DBPhoneItem;
import com.sber.demo.sberdemoproject.internal.validation.PhoneItemValidation;
import com.sber.demo.sberdemoproject.utils.modelmapper.ModelMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PhoneUseCase {
    private final PhoneRepository repository;

    public PhoneUseCase(PhoneRepository repository) {
        this.repository = repository;
    }

    /**
     * Получает список всех PhoneItem-ов.
     *
     * @return Список всех PhoneItem-ов.
     */
    public List<PhoneItem> findAll() {
        log.info("UseCase findAll: listing all items");

        return ModelMapperUtils.mapList(repository.findAll(), PhoneItem.class);
    }

    /**
     * Получает элемент PhoneItem по его ID.
     *
     * @param id ID PhoneItem.
     * @return PhoneItem, если найден, или null, если не найден.
     */
    public PhoneItem findById(Long id) {
        log.info("UseCase findById: finding item with id {}", id);

        Optional<DBPhoneItem> itemOptional = repository.findById(id);

        if (itemOptional.isPresent()) {
            log.info("UseCase findById: found item {}", itemOptional.get());
            return ModelMapperUtils.map(itemOptional.get(), PhoneItem.class);
        }

        log.info("UseCase findById: couldn't find item with id {}", id);
        return null;
    }

    /**
     * Добавляет новый PhoneItem.
     *
     * @param request AddItemRequest REST request
     * @return Добавленный PhoneItem.
     */
    public PhoneItem addItem(@RequestBody AddItemRequest request) throws ValidationException {
        log.info("UseCase addItem: adding item with request {}", request);

        try {
            PhoneItemValidation.validatePhoneItem(request.getPrice(), request.getQuantity(), request.getStorageSpaceGb());
        } catch (ValidationException e) {
            log.error("UseCase addItem: validation error can't add item: {}", e.getMessage());
            throw e;
        }

        PhoneItem item = new PhoneItem();
        item.setName(request.getName());
        item.setManufacturer(request.getManufacturer());
        item.setDescription(request.getDescription());
        item.setStorageSpaceGb(request.getStorageSpaceGb());
        item.setQuantity(request.getQuantity());
        item.setPrice(request.getPrice());

        log.info("UseCase addItem: adding item {}", item);

        DBPhoneItem itemToAdd = ModelMapperUtils.map(item, DBPhoneItem.class);
        return ModelMapperUtils.map(repository.save(itemToAdd), PhoneItem.class);
    }

    /**
     * Удаляет элемент PhoneItem из базы данных по его ID, если такого ID нет, то ничего не происходит.
     *
     * @param request RemoveItemRequest REST request
     */
    public void removeItem(@RequestBody RemoveItemRequest request) {
        Long id = request.getId();
        log.info("UseCase: removing item with id {}", id);
        repository.deleteById(id);
    }

    /**
     * Обновляет существующий элемент PhoneItem в базе данных по id, либо добавляет новый.
     *
     * @param request UpdateItemRequest REST request
     * @return Обновленный элемент PhoneItem.
     */
    public PhoneItem updateItem(@RequestBody UpdateItemRequest request) {
        log.info("UseCase updateItem: updating item with request {}", request);

        try {
            PhoneItemValidation.validatePhoneItem(request.getPrice(), request.getQuantity(), request.getStorageSpaceGb());
        } catch (ValidationException e) {
            log.error("UseCase updateItem: validation error can't add item: {}", e.getMessage());
            throw e;
        }

        PhoneItem phoneItem = new PhoneItem();
        phoneItem.setId(request.getId());
        phoneItem.setName(request.getName());
        phoneItem.setManufacturer(request.getManufacturer());
        phoneItem.setDescription(request.getDescription());
        phoneItem.setStorageSpaceGb(request.getStorageSpaceGb());
        phoneItem.setQuantity(request.getQuantity());
        phoneItem.setPrice(request.getPrice());

        log.info("UseCase updateItem: updating item {}", phoneItem);

        DBPhoneItem itemToUpdate = ModelMapperUtils.map(phoneItem, DBPhoneItem.class);
        return ModelMapperUtils.map(repository.save(itemToUpdate), PhoneItem.class);
    }
}
