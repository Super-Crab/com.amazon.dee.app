package com.amazon.whisperjoin.common.sharedtypes.devices;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public abstract class DeviceFilter {
    public BeaconType mBeaconType = BeaconType.OOBE;

    /* loaded from: classes13.dex */
    public enum BeaconType {
        OOBE(AlexaMetricsConstants.MetricsComponents.OOBE),
        DISTRESS("Distress"),
        ALL("All");
        
        public static final String BEACON_TYPE_KEY = "beacon_type";
        private final String name;

        BeaconType(String str) {
            this.name = str;
        }

        public static BeaconType fromInt(int i) {
            return values()[i];
        }

        public int toInt() {
            return ordinal();
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }
    }

    /* loaded from: classes13.dex */
    public enum FilterResult {
        ACCEPT,
        REJECT
    }

    /* loaded from: classes13.dex */
    public enum FilterType {
        FILTER_ACCEPT_ALL_DEVICES("Accept all devices"),
        FILTER_REJECT_ALL_DEVICES("Reject all devices"),
        FILTER_BY_DEVICE_IDENTITY("Filter by device identity"),
        FILTER_BY_PRODUCT_INDEX("Filter by product index"),
        FILTER_BY_DISTRESS_STATE("Filter by distress state");
        
        public static final String FILTER_TYPE_KEY = "filter_type";
        public static final String FILTER_VALUE_KEY = "filter_value";
        private final String name;

        FilterType(String str) {
            this.name = str;
        }

        public static FilterType fromInt(int i) {
            return values()[i];
        }

        public static String[] getDeviceFilterNameArray() {
            String[] strArr = new String[values().length];
            for (int i = 0; i < values().length; i++) {
                strArr[i] = values()[i].toString();
            }
            return strArr;
        }

        public int toInt() {
            return ordinal();
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.name;
        }
    }

    public abstract FilterResult filter(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails);

    public BeaconType getBeaconType() {
        return this.mBeaconType;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BeaconType{");
        outline107.append(this.mBeaconType.toString());
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public void writeToBundle(Bundle bundle) {
        bundle.putInt(BeaconType.BEACON_TYPE_KEY, this.mBeaconType.toInt());
    }

    public void writeToSharedPreferences(SharedPreferences.Editor editor) {
        editor.putInt(BeaconType.BEACON_TYPE_KEY, this.mBeaconType.toInt());
    }
}
