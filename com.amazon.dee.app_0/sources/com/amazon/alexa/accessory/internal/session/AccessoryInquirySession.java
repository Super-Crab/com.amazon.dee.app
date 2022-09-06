package com.amazon.alexa.accessory.internal.session;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryInquiryRecord;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes.dex */
public interface AccessoryInquirySession {

    /* loaded from: classes.dex */
    public interface Factory {
        AccessoryInquirySession create(Accessory accessory);
    }

    void connect();

    Single<AccessoryInquiryRecord> queryInquiryRecord();

    void release();
}
