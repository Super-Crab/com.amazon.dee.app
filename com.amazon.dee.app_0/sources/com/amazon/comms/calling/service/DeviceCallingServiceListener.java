package com.amazon.comms.calling.service;

import com.amazon.comms.calling.service.DeviceCallingService;
/* loaded from: classes11.dex */
public interface DeviceCallingServiceListener {
    void configureCall(Call call);

    void onAuthTokenExpiring(DeviceCallingService deviceCallingService, long j);

    void onCallAdded(Call call);

    void onCallRemoved(Call call);

    void onError(String str, ErrorCode errorCode, int i, String str2);

    void onStateChanged(DeviceCallingService deviceCallingService, DeviceCallingService.State state);
}
