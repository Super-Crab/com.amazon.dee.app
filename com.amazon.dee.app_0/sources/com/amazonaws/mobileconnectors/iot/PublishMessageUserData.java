package com.amazonaws.mobileconnectors.iot;
/* loaded from: classes13.dex */
class PublishMessageUserData {
    private AWSIotMqttMessageDeliveryCallback userCallback;
    private Object userData;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PublishMessageUserData(AWSIotMqttMessageDeliveryCallback aWSIotMqttMessageDeliveryCallback, Object obj) {
        this.userCallback = aWSIotMqttMessageDeliveryCallback;
        this.userData = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AWSIotMqttMessageDeliveryCallback getUserCallback() {
        return this.userCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getUserData() {
        return this.userData;
    }
}
