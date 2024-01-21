package com.sber.demo.sberdemoproject.internal.validation;

import com.sber.demo.sberdemoproject.internal.entity.phoneservice.exceptions.ValidationException;

public class PhoneItemValidation {
    public static boolean validatePhoneItem(int price, int quantity, long storageSpace) throws ValidationException {
        if (price < 0) {
            throw new ValidationException("item can't have negative price");
        } else if (storageSpace < 0) {
            throw new ValidationException("item can't have negative storage space");
        } else if (quantity < 0) {
            throw new ValidationException("item can't have negative quantity");
        }

        return true;
    }
}
