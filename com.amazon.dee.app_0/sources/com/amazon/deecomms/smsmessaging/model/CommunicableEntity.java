package com.amazon.deecomms.smsmessaging.model;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public class CommunicableEntity {
    public String address;
    public EntityFormat addressType;

    /* loaded from: classes12.dex */
    public enum EntityFormat {
        PhoneNumberAddress,
        EmailAddress,
        skype,
        a2a
    }

    public CommunicableEntity(@NonNull String str, @NonNull EntityFormat entityFormat) {
        this.address = str;
        this.addressType = entityFormat;
    }
}
