package com.sber.demo.sberdemoproject;

import com.sber.demo.sberdemoproject.internal.entity.phoneservice.exceptions.ValidationException;
import com.sber.demo.sberdemoproject.internal.validation.PhoneItemValidation;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class PhoneItemValidationTests {

    @Test
    public void testValidPhoneItem() {
        int price = 100;
        int quantity = 5;
        long storageSpace = 256;

        boolean result = PhoneItemValidation.validatePhoneItem(price, quantity, storageSpace);

        assertTrue(result);
    }

    @Test
    public void testInvalidPrice() {
        int price = -50;
        int quantity = 5;
        long storageSpace = 256;

        assertThrows(ValidationException.class, () -> PhoneItemValidation.validatePhoneItem(price, quantity, storageSpace));
    }

    @Test
    public void testInvalidStorageSpace() {
        int price = 100;
        int quantity = 5;
        long storageSpace = -256;

        assertThrows(ValidationException.class, () -> PhoneItemValidation.validatePhoneItem(price, quantity, storageSpace));
    }

    @Test
    public void testInvalidQuantity() {
        int price = 100;
        int quantity = -5;
        long storageSpace = 256;

        assertThrows(ValidationException.class, () -> PhoneItemValidation.validatePhoneItem(price, quantity, storageSpace));
    }
}
