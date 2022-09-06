package com.amazon.whisperjoin.deviceprovisioningservice.device;

import com.amazon.alexa.fitness.metrics.MetricsOperation;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.DeviceDetailsProtoData;
import io.reactivex.rxjava3.core.Single;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public abstract class Provisionee {

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes13.dex */
    public enum DeviceOperation {
        CONNECT(MetricsOperation.CONNECT),
        START_PROVISIONING("Start Provisioning"),
        COMPLETE_PROVISIONING("Complete Provisioning"),
        GET_DEVICE_DETAILS("Get Device Details"),
        GET_VISIBLE_NETWORKS("Get Visible Networks"),
        INITIATE_WIFI_SCAN("Initiate Wifi Scan"),
        SAVE_NETWORK("Save Networks"),
        SET_REGISTRATION_TOKEN("Save Registration Token"),
        SAVE_CONFIG_MAP("Save Configuration Map"),
        GET_CONNECTION_INFO("Get Connection Info"),
        GET_REGISTRATION_STATUS("Get Registration Status");
        
        private final String mName;

        DeviceOperation(String str) {
            this.mName = str;
        }

        public String getOpName() {
            return this.mName;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mName;
        }
    }

    public abstract Single<DeviceDetailsProtoData> getDeviceDetails();
}
