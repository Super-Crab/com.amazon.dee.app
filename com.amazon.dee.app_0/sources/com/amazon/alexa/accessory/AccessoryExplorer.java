package com.amazon.alexa.accessory;

import com.amazon.alexa.accessory.internal.bluetooth.SdpRepository;
/* loaded from: classes.dex */
public interface AccessoryExplorer {

    /* loaded from: classes.dex */
    public interface Observer {
        void onKnownAccessoryFound(Accessory accessory);

        void onKnownAccessoryLost(Accessory accessory);

        void onUnknownAccessoryFound(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord);

        void onUnknownAccessoryLost(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord);
    }

    void cancel(Observer observer);

    void discover(Observer observer);

    AccessoryAttributes getAccessoryAttributes();

    SdpRepository getSdpRepository();
}
