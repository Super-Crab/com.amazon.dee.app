package com.amazon.comms.calling.service;

import android.app.Service;
import android.os.IBinder;
/* loaded from: classes11.dex */
public interface ICallingApiBinder extends IBinder {
    DeviceCallingService getCallingApi();

    Service getService();
}
