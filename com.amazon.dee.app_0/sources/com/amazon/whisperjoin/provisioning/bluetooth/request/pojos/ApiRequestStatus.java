package com.amazon.whisperjoin.provisioning.bluetooth.request.pojos;

import com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable;
/* loaded from: classes13.dex */
public class ApiRequestStatus implements Validatable {
    public final String msg;
    public final int status;

    public ApiRequestStatus(int i, String str) {
        this.status = i;
        this.msg = str;
    }

    @Override // com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable
    public void validate() {
    }
}
