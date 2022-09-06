package com.amazon.whisperjoin.provisioning.bluetooth.request.pojos;

import com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable;
/* loaded from: classes13.dex */
public class GetListIndexRequest implements Validatable {
    public final int listIndex;

    public GetListIndexRequest(int i) {
        this.listIndex = i;
    }

    @Override // com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable
    public void validate() {
        if (this.listIndex >= 0) {
            return;
        }
        throw new IllegalArgumentException("Invalid list-index request; index must be non-negative.");
    }
}
