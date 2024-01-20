package com.sber.demo.sberdemoproject;

import com.sber.demo.sberdemoproject.internal.entity.phoneservice.items.PhoneItem;
import com.sber.demo.sberdemoproject.internal.entity.phoneservice.requests.AddItemRequest;
import com.sber.demo.sberdemoproject.internal.entity.phoneservice.requests.RemoveItemRequest;
import com.sber.demo.sberdemoproject.internal.entity.phoneservice.requests.UpdateItemRequest;
import com.sber.demo.sberdemoproject.internal.repository.phoneservice.PhoneRepository;
import com.sber.demo.sberdemoproject.internal.usecase.phoneservice.PhoneUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//        classes = SberDemoProjectApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
@DataJpaTest
public class UseCaseTests {
    @Autowired
    private PhoneRepository repository;
    @Autowired
    private PhoneUseCase phoneUseCase;

    @TestConfiguration
    static class TestConfig {

        @Bean
        public PhoneUseCase phoneUseCase(PhoneRepository repository) {
            return new PhoneUseCase(repository);
        }
    }

    @Test
    public void testFindAll() {
        phoneUseCase.addItem(new AddItemRequest("AppleTest", "Iphone 1", "Description of Item 1.", 128L, 1, 10));
        phoneUseCase.addItem(new AddItemRequest("SomeManufacturer", "MegaPhone13", "Description of Item 2", 512L, 5, 1));
        phoneUseCase.addItem(new AddItemRequest("AnotherManufacturer", "phone", "Description of Item 3.", 12L, 5, 150));

        List<PhoneItem> actualItems = phoneUseCase.findAll();

        assertEquals(actualItems.size(), 3);
    }

    @Test
    public void testAddItem() {
        PhoneItem savedItem = new PhoneItem(1L, "AppleTest", "Iphone 1", "Description of Item 1.", 128L, 1, 10);

        AddItemRequest request = new AddItemRequest(savedItem.getManufacturer(), savedItem.getName(), savedItem.getDescription(),
                savedItem.getStorageSpaceGb(), savedItem.getQuantity(), savedItem.getPrice());

        PhoneItem result = phoneUseCase.addItem(request);

        assertEquals(result, savedItem);
    }

    @Test
    public void testFindById() {
        Long idToFind = 1L;

        PhoneItem expectedItem = new PhoneItem(idToFind,
                "AppleTest",
                "Iphone 1",
                "Description of Item 1.",
                128L, 1, 10);

        AddItemRequest request = new AddItemRequest(expectedItem.getManufacturer(), expectedItem.getName(), expectedItem.getDescription(),
                expectedItem.getStorageSpaceGb(), expectedItem.getQuantity(), expectedItem.getPrice());

        PhoneItem addedItem = phoneUseCase.addItem(request);

        PhoneItem actualItem = phoneUseCase.findById(addedItem.getId());

        assertEquals(addedItem, actualItem);
    }

    @Test
    public void testUpdateItem() {
        PhoneItem itemToAdd = new PhoneItem(1L,
                "AppleTest",
                "Iphone 1",
                "Description of Item 1.",
                128L, 1, 10);

        AddItemRequest request = new AddItemRequest(itemToAdd.getManufacturer(), itemToAdd.getName(), itemToAdd.getDescription(),
                itemToAdd.getStorageSpaceGb(), itemToAdd.getQuantity(), itemToAdd.getPrice());

        PhoneItem addedItem = phoneUseCase.addItem(request);

        addedItem.setName("Updated item 1");

        UpdateItemRequest updateRequest = new UpdateItemRequest(addedItem.getId(), addedItem.getManufacturer(), addedItem.getName(), addedItem.getDescription(),
                addedItem.getStorageSpaceGb(), addedItem.getQuantity(), addedItem.getPrice());

        phoneUseCase.updateItem(updateRequest);

        PhoneItem result = phoneUseCase.findById(addedItem.getId());

        assertEquals(addedItem.getId(), result.getId());
        assertNotEquals(itemToAdd.getName(), result.getName());
        assertEquals(itemToAdd.getDescription(), result.getDescription());
        assertEquals(itemToAdd.getQuantity(), result.getQuantity());
        assertEquals(itemToAdd.getPrice(), result.getPrice());
    }

    @Test
    public void testRemoveById() {
        PhoneItem itemToAdd = new PhoneItem(1L,
                "AppleTest",
                "Iphone 1",
                "Description of Item 1.",
                128L, 1, 10);

        AddItemRequest request = new AddItemRequest(itemToAdd.getManufacturer(), itemToAdd.getName(), itemToAdd.getDescription(),
                itemToAdd.getStorageSpaceGb(), itemToAdd.getQuantity(), itemToAdd.getPrice());

        PhoneItem addedItem = phoneUseCase.addItem(request);

        int prevSize = phoneUseCase.findAll().size();
        assertNotEquals(0, prevSize);

        Long id = addedItem.getId();

        RemoveItemRequest removeItemRequest = new RemoveItemRequest(id);

        phoneUseCase.removeItem(removeItemRequest);

        assertEquals(prevSize - 1, phoneUseCase.findAll().size());
    }
}
