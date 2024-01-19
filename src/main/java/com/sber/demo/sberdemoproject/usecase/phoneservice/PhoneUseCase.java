package com.sber.demo.sberdemoproject.usecase.phoneservice;

import com.sber.demo.sberdemoproject.entity.phoneservice.items.PhoneItem;
import com.sber.demo.sberdemoproject.entity.phoneservice.requests.AddItemRequest;
import com.sber.demo.sberdemoproject.repository.phoneservice.PhoneRepository;
import com.sber.demo.sberdemoproject.repository.phoneservice.entities.DBPhoneItem;
import com.sber.demo.sberdemoproject.utils.modelmapper.ModelMapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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
        return ModelMapperUtils.mapList(repository.findAll(), PhoneItem.class);
    }

    /**
     * Получает элемент PhoneItem по его ID.
     *
     * @param id ID PhoneItem.
     * @return PhoneItem, если найден, или null, если не найден.
     */
    public PhoneItem findById(Long id) {
        Optional<DBPhoneItem> itemOptional = repository.findById(id);

        if (itemOptional.isPresent()) {
            return ModelMapperUtils.map(itemOptional.get(), PhoneItem.class);
        }

        return null;
    }

    /**
     * Добавляет новый PhoneItem.
     *
     * @return Добавленный PhoneItem.
     */
    public PhoneItem addItem(@RequestBody AddItemRequest request) {
        PhoneItem item = new PhoneItem();
        item.setName(request.getName());
        item.setManufacturer(request.getManufacturer());
        item.setDescription(request.getDescription());
        item.setStorageSpaceGb(request.getStorageSpaceGb());
        item.setQuantity(request.getQuantity());
        item.setPrice(request.getPrice());

        DBPhoneItem itemToAdd = ModelMapperUtils.map(item, DBPhoneItem.class);
        return ModelMapperUtils.map(repository.save(itemToAdd), PhoneItem.class);
    }

    /**
     * Удаляет элемент PhoneItem из базы данных по его ID, если такого ID нет, то ничего не происходит.
     *
     * @param id ID удаляемого элемента PhoneItem.
     */
    public void removeById(Long id) {
        repository.deleteById(id);
    }

}
