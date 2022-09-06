package com.amazon.alexa.accessory.persistence.device;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.persistence.DatabaseContract;
import com.amazon.alexa.accessory.persistence.device.v2.DeviceGroupContract;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public interface DeviceContract extends DatabaseContract {

    /* loaded from: classes.dex */
    public static final class Device implements BaseColumns {
        public static final String COLUMN_CONDITION = "condition";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_IDENTIFIER = "identifier";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SERIAL_NUMBER = "serial_number";
        public static final String COLUMN_TRANSPORT = "transport";
        public static final String COLUMN_TYPE = "type";
        public static final int CONDITION_ACTIVE = 0;
        public static final int CONDITION_DISABLED = 1;
        public static final int PREV_COLUMN_INDEX_CONDITION = 6;
        public static final int PREV_COLUMN_INDEX_ID = 0;
        public static final int PREV_COLUMN_INDEX_IDENTIFIER = 4;
        public static final int PREV_COLUMN_INDEX_NAME = 1;
        public static final int PREV_COLUMN_INDEX_SERIAL_NUMBER = 2;
        public static final int PREV_COLUMN_INDEX_TRANSPORT = 5;
        public static final int PREV_COLUMN_INDEX_TYPE = 3;
        public static final String TABLE_NAME = "devices";
        public static final int TRANSPORT_GATT = 0;
        public static final int TRANSPORT_RFCOMM = 1;
        private final int condition;
        private final long id;
        private final String identifier;
        private final String name;
        private final String serialNumber;
        private final int transport;
        private final String type;
        public static final String[] COLUMNS = {"_id", "name", "serial_number", "type", "identifier", "transport", "condition"};
        public static final int COLUMN_INDEX_ID = DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_ID;
        public static final int COLUMN_INDEX_NAME = DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_NAME;
        public static final int COLUMN_INDEX_SERIAL_NUMBER = DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_SERIAL_NUMBER;
        public static final int COLUMN_INDEX_TYPE = DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_TYPE;
        public static final int COLUMN_INDEX_IDENTIFIER = DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_IDENTIFIER;
        public static final int COLUMN_INDEX_TRANSPORT = DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_TRANSPORT;
        public static final int COLUMN_INDEX_STANDBY_TIMEOUT = DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_STANDBY_TIMEOUT;

        /* loaded from: classes.dex */
        public static final class Builder {
            int condition;
            long id;
            String identifier;
            boolean isTransportSet;
            String name;
            String serialNumber;
            int transport;
            String type;

            public Builder() {
                this.id = -1L;
                this.condition = 0;
            }

            public Device build() {
                Preconditions.notNull(this.identifier, "identifier");
                Preconditions.precondition(this.isTransportSet, "Transport wasn't set!");
                Preconditions.in(this.transport, "transport", 0, 1);
                Preconditions.in(this.condition, "condition", 0, 1);
                return new Device(this);
            }

            public Builder condition(int i) {
                this.condition = i;
                return this;
            }

            public Builder id(long j) {
                this.id = j;
                return this;
            }

            public Builder identifier(String str) {
                this.identifier = str;
                return this;
            }

            public Builder name(String str) {
                this.name = str;
                return this;
            }

            public Builder serialNumber(String str) {
                this.serialNumber = str;
                return this;
            }

            public Builder transport(int i) {
                this.transport = i;
                this.isTransportSet = true;
                return this;
            }

            public Builder type(String str) {
                this.type = str;
                return this;
            }

            public Builder condition(String str) {
                return condition(DeviceGroup.Condition.valueOf(str).ordinal());
            }

            public Builder transport(String str) {
                return transport(AccessoryTransport.Type.valueOf(str).ordinal());
            }

            public Builder(Device device) {
                Preconditions.notNull(device, "device");
                this.id = device.getId();
                this.name = device.getName();
                this.serialNumber = device.getSerialNumber();
                this.type = device.getType();
                this.identifier = device.getIdentifier();
                this.transport = device.getTransport();
                this.condition = device.getCondition();
                this.isTransportSet = true;
            }
        }

        Device(Builder builder) {
            this.id = builder.id;
            this.name = builder.name;
            this.serialNumber = builder.serialNumber;
            this.type = builder.type;
            this.identifier = builder.identifier;
            this.transport = builder.transport;
            this.condition = builder.condition;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static Device convertDeviceGroupToDevice(DeviceGroup deviceGroup) {
            Preconditions.notNull(deviceGroup, "deviceGroup");
            Builder transport = new Builder().id(deviceGroup.getId()).identifier(deviceGroup.getIdentifier()).condition(deviceGroup.getCondition(System.currentTimeMillis()).ordinal()).transport(deviceGroup.getTransportType().ordinal());
            com.amazon.alexa.accessory.repositories.device.v2.Device device = deviceGroup.getDevice();
            if (device != null) {
                transport.name(device.getName()).serialNumber(device.getSerialNumber()).type(device.getType());
            }
            return transport.build();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static List<Device> convertDeviceGroupsToDevices(List<DeviceGroup> list) {
            ArrayList arrayList = new ArrayList();
            for (DeviceGroup deviceGroup : list) {
                arrayList.add(convertDeviceGroupToDevice(deviceGroup));
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static DeviceGroup convertDeviceToDeviceGroup(Device device) {
            DeviceGroup.Builder conditionActive = new DeviceGroup.Builder().id(device.id).identifier(device.identifier).transport(AccessoryTransport.Type.values()[device.transport]).setConditionActive();
            if (device.getName() != null && device.getType() != null && device.getSerialNumber() != null) {
                conditionActive.devices(Collections.singletonList(new Device.Builder().name(device.name).serialNumber(device.serialNumber).type(device.type).deviceId(0).color(0).build()));
            }
            return conditionActive.build();
        }

        public static Device map(Cursor cursor) {
            Preconditions.notNull(cursor, "cursor");
            Preconditions.notNegative(cursor.getPosition(), ViewProps.POSITION);
            Preconditions.precondition(cursor.getPosition() < cursor.getCount(), ViewProps.POSITION);
            return new Builder().id(cursor.getLong(COLUMN_INDEX_ID)).name(cursor.getString(COLUMN_INDEX_NAME)).serialNumber(cursor.getString(COLUMN_INDEX_SERIAL_NUMBER)).type(cursor.getString(COLUMN_INDEX_TYPE)).identifier(cursor.getString(COLUMN_INDEX_IDENTIFIER)).transport(cursor.getString(COLUMN_INDEX_TRANSPORT)).condition(0).build();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Device.class != obj.getClass()) {
                return false;
            }
            Device device = (Device) obj;
            if (this.id != device.id || this.transport != device.transport || this.condition != device.condition) {
                return false;
            }
            String str = this.name;
            if (str == null ? device.name != null : !str.equals(device.name)) {
                return false;
            }
            String str2 = this.serialNumber;
            if (str2 == null ? device.serialNumber != null : !str2.equals(device.serialNumber)) {
                return false;
            }
            String str3 = this.type;
            if (str3 == null ? device.type != null : !str3.equals(device.type)) {
                return false;
            }
            String str4 = this.identifier;
            String str5 = device.identifier;
            return str4 != null ? str4.equals(str5) : str5 == null;
        }

        public int getCondition() {
            return this.condition;
        }

        public long getId() {
            return this.id;
        }

        public String getIdentifier() {
            return this.identifier;
        }

        public String getName() {
            return this.name;
        }

        public String getSerialNumber() {
            return this.serialNumber;
        }

        public int getTransport() {
            return this.transport;
        }

        public String getType() {
            return this.type;
        }

        public int hashCode() {
            long j = this.id;
            int i = ((int) (j ^ (j >>> 32))) * 31;
            String str = this.name;
            int i2 = 0;
            int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.serialNumber;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.type;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.identifier;
            if (str4 != null) {
                i2 = str4.hashCode();
            }
            return ((((hashCode3 + i2) * 31) + this.transport) * 31) + this.condition;
        }

        public ContentValues toContentValues() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", this.name);
            contentValues.put("serial_number", this.serialNumber);
            contentValues.put("type", this.type);
            contentValues.put("identifier", this.identifier);
            contentValues.put("transport", Integer.valueOf(this.transport));
            contentValues.put("condition", Integer.valueOf(this.condition));
            return contentValues;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Device{id=");
            outline107.append(this.id);
            outline107.append(", name=");
            outline107.append(this.name);
            outline107.append(", sn=");
            outline107.append(this.serialNumber);
            outline107.append(", type=");
            outline107.append(this.type);
            outline107.append(", identifier='");
            GeneratedOutlineSupport1.outline176(outline107, this.identifier, Chars.QUOTE, ", transport=");
            outline107.append(this.transport);
            outline107.append(", condition=");
            return GeneratedOutlineSupport1.outline85(outline107, this.condition, JsonReaderKt.END_OBJ);
        }
    }
}
