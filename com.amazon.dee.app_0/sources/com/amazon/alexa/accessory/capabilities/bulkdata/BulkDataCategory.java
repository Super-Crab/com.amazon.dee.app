package com.amazon.alexa.accessory.capabilities.bulkdata;

import com.amazon.alexa.accessory.capabilities.bulkdata.exceptions.InvalidBulkDataRequestException;
/* loaded from: classes.dex */
public enum BulkDataCategory {
    AUDIO(1),
    SCHEDULE(2),
    DUTY_CYCLE(3),
    DEVICE_SETTINGS(4);
    
    private final int categoryValue;

    BulkDataCategory(int i) {
        this.categoryValue = i;
    }

    public static BulkDataCategory fromValue(int i) {
        BulkDataCategory[] values;
        for (BulkDataCategory bulkDataCategory : values()) {
            if (bulkDataCategory.value() == i) {
                return bulkDataCategory;
            }
        }
        throw new InvalidBulkDataRequestException(String.format("%d is not a valid value for any existing bulk data categories", Integer.valueOf(i)));
    }

    public static boolean isValidCategory(int i) {
        for (BulkDataCategory bulkDataCategory : values()) {
            if (bulkDataCategory.value() == i) {
                return true;
            }
        }
        return false;
    }

    public int value() {
        return this.categoryValue;
    }
}
