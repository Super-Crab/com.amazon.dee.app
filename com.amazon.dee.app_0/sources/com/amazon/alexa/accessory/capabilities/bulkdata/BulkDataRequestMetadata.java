package com.amazon.alexa.accessory.capabilities.bulkdata;

import com.amazon.alexa.accessory.capabilities.bulkdata.exceptions.InvalidBulkDataRequestException;
/* loaded from: classes.dex */
public class BulkDataRequestMetadata {
    private int blockStartIndex;
    private BulkDataCategory category;

    public BulkDataRequestMetadata(BulkDataCategory bulkDataCategory) {
        this.category = bulkDataCategory;
        this.blockStartIndex = 0;
    }

    public int getBlockStartIndex() {
        return this.blockStartIndex;
    }

    public int getCategory() {
        return this.category.value();
    }

    public BulkDataRequestMetadata(BulkDataCategory bulkDataCategory, int i) {
        this.category = bulkDataCategory;
        if (i >= 0) {
            this.blockStartIndex = i;
            return;
        }
        throw new InvalidBulkDataRequestException(String.format("blockStartIndex (%d) cannot be less than 0!", Integer.valueOf(i)));
    }
}
