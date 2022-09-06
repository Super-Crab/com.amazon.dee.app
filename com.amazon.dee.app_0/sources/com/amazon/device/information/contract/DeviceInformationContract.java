package com.amazon.device.information.contract;

import android.net.Uri;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes12.dex */
public final class DeviceInformationContract {
    public static final String AUTHORITY = "com.amazon.device.information.provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://com.amazon.device.information.provider");
    public static final String PACKAGE_NAME = "com.amazon.device.information.provider";

    /* loaded from: classes12.dex */
    public static final class DeviceInfo implements DeviceInfoColumns {
        public static final List<String> COLUMN_LIST;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.amazon.device.information.provider.device_info";
        public static final String CONTENT_DIRECTORY = "device_info";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DeviceInformationContract.AUTHORITY_URI, CONTENT_DIRECTORY);

        static {
            ArrayList arrayList = new ArrayList();
            arrayList.add("device_type");
            arrayList.add("dsn");
            COLUMN_LIST = Collections.unmodifiableList(arrayList);
        }

        private DeviceInfo() {
        }
    }

    /* loaded from: classes12.dex */
    public interface DeviceInfoColumns {
        public static final String DEVICE_TYPE = "device_type";
        public static final String DSN = "dsn";
    }

    private DeviceInformationContract() {
    }
}
