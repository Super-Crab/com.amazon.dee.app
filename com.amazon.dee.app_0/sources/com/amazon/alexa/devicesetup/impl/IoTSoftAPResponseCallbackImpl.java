package com.amazon.alexa.devicesetup.impl;

import com.amazon.alexa.devicesetup.util.EventBusUtil;
import com.amazon.dee.sdk.iotsoftap.IoTSoftApResponseCallback;
/* loaded from: classes7.dex */
public class IoTSoftAPResponseCallbackImpl implements IoTSoftApResponseCallback {
    @Override // com.amazon.dee.sdk.iotsoftap.IoTSoftApResponseCallback
    public void error(String str) {
        EventBusUtil.instance().sendMessageToEventBus(str);
    }

    @Override // com.amazon.dee.sdk.iotsoftap.IoTSoftApResponseCallback
    public void success(String str) {
        EventBusUtil.instance().sendMessageToEventBus(str);
    }

    @Override // com.amazon.dee.sdk.iotsoftap.IoTSoftApResponseCallback
    public void error(String str, String str2) {
        EventBusUtil.instance().sendMessageToEventBus(str, str2);
    }

    @Override // com.amazon.dee.sdk.iotsoftap.IoTSoftApResponseCallback
    public void success(String str, String str2) {
        EventBusUtil.instance().sendMessageToEventBus(str, str2);
    }
}
