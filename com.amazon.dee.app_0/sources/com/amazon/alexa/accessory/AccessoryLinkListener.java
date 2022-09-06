package com.amazon.alexa.accessory;
/* loaded from: classes.dex */
public interface AccessoryLinkListener {
    void onAccessoryLinkFailed(Accessory accessory, Throwable th);

    void onAccessoryLinked(Accessory accessory);

    void onAccessoryUnlinkFailed(Accessory accessory, Throwable th);

    void onAccessoryUnlinked(Accessory accessory);
}
