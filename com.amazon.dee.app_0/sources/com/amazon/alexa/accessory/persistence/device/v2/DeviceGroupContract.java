package com.amazon.alexa.accessory.persistence.device.v2;

import android.provider.BaseColumns;
import com.amazon.alexa.accessory.persistence.DatabaseContract;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public interface DeviceGroupContract extends DatabaseContract {
    public static final int VERSION = 3;

    @Deprecated
    /* loaded from: classes.dex */
    public interface DeviceGroupColumns extends BaseColumns {
        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_CONDITION = "condition";
        public static final String COLUMN_DEVICE_ID = "device_id";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_IDENTIFIER = "identifier";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SERIAL_NUMBER = "serial_number";
        public static final String COLUMN_TRANSPORT = "transport";
        public static final String COLUMN_TYPE = "type";
        public static final List<String> COLUMNS = Collections.unmodifiableList(Arrays.asList("_id", "identifier", "transport", "condition", "device_id", "name", "serial_number", "type", "color"));
        public static final int COLUMN_INDEX_ID = COLUMNS.indexOf("_id");
        public static final int COLUMN_INDEX_IDENTIFIER = COLUMNS.indexOf("identifier");
        public static final int COLUMN_INDEX_TRANSPORT = COLUMNS.indexOf("transport");
        public static final int COLUMN_INDEX_CONDITION = COLUMNS.indexOf("condition");
        public static final int COLUMN_INDEX_DEVICE_ID = COLUMNS.indexOf("device_id");
        public static final int COLUMN_INDEX_NAME = COLUMNS.indexOf("name");
        public static final int COLUMN_INDEX_SERIAL_NUMBER = COLUMNS.indexOf("serial_number");
        public static final int COLUMN_INDEX_TYPE = COLUMNS.indexOf("type");
        public static final int COLUMN_INDEX_COLOR = COLUMNS.indexOf("color");
    }

    /* loaded from: classes.dex */
    public interface DeviceGroupV3Columns extends BaseColumns {
        public static final String COLUMN_COLOR = "color";
        public static final String COLUMN_DEVICE_ID = "device_id";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_IDENTIFIER = "identifier";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SERIAL_NUMBER = "serial_number";
        public static final String COLUMN_TRANSPORT = "transport";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_STANDBY_TIMEOUT = "standby_timeout";
        public static final String COLUMN_LAST_KNOWN_STANDBY_REASON = "last_known_standby_reason";
        public static final List<String> COLUMNS = Collections.unmodifiableList(Arrays.asList("_id", "identifier", "transport", "device_id", "name", "serial_number", "type", "color", COLUMN_STANDBY_TIMEOUT, COLUMN_LAST_KNOWN_STANDBY_REASON));
        public static final int COLUMN_INDEX_ID = COLUMNS.indexOf("_id");
        public static final int COLUMN_INDEX_IDENTIFIER = COLUMNS.indexOf("identifier");
        public static final int COLUMN_INDEX_TRANSPORT = COLUMNS.indexOf("transport");
        public static final int COLUMN_INDEX_DEVICE_ID = COLUMNS.indexOf("device_id");
        public static final int COLUMN_INDEX_NAME = COLUMNS.indexOf("name");
        public static final int COLUMN_INDEX_SERIAL_NUMBER = COLUMNS.indexOf("serial_number");
        public static final int COLUMN_INDEX_TYPE = COLUMNS.indexOf("type");
        public static final int COLUMN_INDEX_COLOR = COLUMNS.indexOf("color");
        public static final int COLUMN_INDEX_STANDBY_TIMEOUT = COLUMNS.indexOf(COLUMN_STANDBY_TIMEOUT);
        public static final int COLUMN_INDEX_STANDBY_REASON = COLUMNS.indexOf(COLUMN_LAST_KNOWN_STANDBY_REASON);
    }

    /* loaded from: classes.dex */
    public interface Tables {
        @Deprecated
        public static final String DEVICE_GROUPS = "device_groups";
        public static final String DEVICE_GROUPS_V3 = "device_groups_v3";
    }
}
