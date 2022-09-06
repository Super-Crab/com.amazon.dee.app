package com.amazon.alexa.drive.smart.device.contract;

import com.amazon.alexa.drive.smart.device.SmartDeviceInteractor;
import com.amazon.alexa.drive.smart.device.data.SmartDevice;
import java.util.List;
/* loaded from: classes7.dex */
public interface SmartDeviceContract {

    /* loaded from: classes7.dex */
    public interface CardClickListener {
        void onClick(SmartDevice smartDevice);
    }

    /* loaded from: classes7.dex */
    public interface DeviceUpdateListener {
        void onUpdate(List<SmartDevice> list);
    }

    /* loaded from: classes7.dex */
    public interface Interactor {

        /* loaded from: classes7.dex */
        public interface UpdateDevice {
            void onUpdate();
        }

        SmartDeviceInteractor.HomeCardData getHomeCardData();

        void setUpdateDeviceListener(UpdateDevice updateDevice);

        void switchDeviceLockState(SmartDevice smartDevice);
    }

    /* loaded from: classes7.dex */
    public interface Presenter {
        void addSmartDeviceChangeListener(DeviceUpdateListener deviceUpdateListener);

        void getSmartDeviceInfo();

        void removeSmartDeviceChangeListener(DeviceUpdateListener deviceUpdateListener);
    }

    /* loaded from: classes7.dex */
    public interface ServerResponseListener {
        void onResponse(String str, String str2);
    }
}
