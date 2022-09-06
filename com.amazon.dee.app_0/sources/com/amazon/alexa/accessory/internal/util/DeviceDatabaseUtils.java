package com.amazon.alexa.accessory.internal.util;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.persistence.device.v2.DeviceGroupContract;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class DeviceDatabaseUtils {
    public static final String DELIMITER = "\t";
    static final String SUBSTITUTION_FOR_DELIMITER = " ";

    private DeviceDatabaseUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static DeviceGroup contentValuesToDeviceGroup(ContentValues contentValues) {
        long longValue = contentValues.getAsLong("_id").longValue();
        String asString = contentValues.getAsString("identifier");
        AccessoryTransport.Type valueOf = AccessoryTransport.Type.valueOf(contentValues.getAsString("transport"));
        DeviceGroup.Builder conditionStandbyTimeout = DeviceGroup.newBuilder().id(longValue).identifier(asString).transport(valueOf).setConditionStandbyTimeout(contentValues.getAsLong(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_STANDBY_TIMEOUT).longValue(), DeviceGroup.StandbyReason.valueOf(contentValues.getAsString(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_LAST_KNOWN_STANDBY_REASON)));
        List<String> splitDeviceGroupValue = splitDeviceGroupValue(contentValues.getAsString("device_id"));
        List<String> splitDeviceGroupValue2 = splitDeviceGroupValue(contentValues.getAsString("name"));
        List<String> splitDeviceGroupValue3 = splitDeviceGroupValue(contentValues.getAsString("serial_number"));
        List<String> splitDeviceGroupValue4 = splitDeviceGroupValue(contentValues.getAsString("type"));
        List<String> splitDeviceGroupValue5 = splitDeviceGroupValue(contentValues.getAsString("color"));
        int size = splitDeviceGroupValue.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(Device.newBuilder().deviceId(Integer.valueOf(splitDeviceGroupValue.get(i))).name(splitDeviceGroupValue2.get(i)).serialNumber(splitDeviceGroupValue3.get(i)).type(splitDeviceGroupValue4.get(i)).color(Integer.valueOf(splitDeviceGroupValue5.get(i))).build());
        }
        conditionStandbyTimeout.devices(arrayList);
        return conditionStandbyTimeout.build();
    }

    public static ContentValues deviceGroupToContentValues(DeviceGroup deviceGroup) {
        ContentValues contentValues = new ContentValues();
        if (deviceGroup.getId() != -1) {
            contentValues.put("_id", Long.valueOf(deviceGroup.getId()));
        }
        contentValues.put("identifier", deviceGroup.getIdentifier());
        contentValues.put("transport", deviceGroup.getTransportType().name());
        contentValues.put("condition", DeviceGroup.Condition.ACTIVE.name());
        if (!deviceGroup.getDevices().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            StringBuilder sb4 = new StringBuilder();
            StringBuilder sb5 = new StringBuilder();
            for (Device device : deviceGroup.getDevices()) {
                if (sb.length() > 0) {
                    sb.append(DELIMITER);
                    sb2.append(DELIMITER);
                    sb3.append(DELIMITER);
                    sb4.append(DELIMITER);
                    sb5.append(DELIMITER);
                }
                sb.append(device.getName());
                sb2.append(device.getSerialNumber());
                sb3.append(device.getType());
                sb4.append(device.getDeviceId());
                sb5.append(device.getColor());
            }
            contentValues.put("name", sb.toString());
            contentValues.put("serial_number", sb2.toString());
            contentValues.put("type", sb3.toString());
            contentValues.put("device_id", sb4.toString());
            contentValues.put("color", sb5.toString());
        } else {
            contentValues.put("name", (String) null);
            contentValues.put("serial_number", (String) null);
            contentValues.put("type", (String) null);
            contentValues.put("device_id", (String) null);
            contentValues.put("color", (String) null);
        }
        return contentValues;
    }

    public static ContentValues deviceGroupV3ToContentValues(DeviceGroup deviceGroup) {
        ContentValues contentValues = new ContentValues();
        if (deviceGroup.getId() != -1) {
            contentValues.put("_id", Long.valueOf(deviceGroup.getId()));
        }
        contentValues.put("identifier", deviceGroup.getIdentifier());
        contentValues.put("transport", deviceGroup.getTransportType().name());
        contentValues.put(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_STANDBY_TIMEOUT, Long.valueOf(deviceGroup.getStandbyExpirationTimestamp()));
        contentValues.put(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_LAST_KNOWN_STANDBY_REASON, deviceGroup.getStandbyReason().name());
        if (!deviceGroup.getDevices().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            StringBuilder sb3 = new StringBuilder();
            StringBuilder sb4 = new StringBuilder();
            StringBuilder sb5 = new StringBuilder();
            for (Device device : deviceGroup.getDevices()) {
                if (sb.length() > 0) {
                    sb.append(DELIMITER);
                    sb2.append(DELIMITER);
                    sb3.append(DELIMITER);
                    sb4.append(DELIMITER);
                    sb5.append(DELIMITER);
                }
                sb.append(device.getName());
                sb2.append(device.getSerialNumber());
                sb3.append(device.getType());
                sb4.append(device.getDeviceId());
                sb5.append(device.getColor());
            }
            contentValues.put("name", sb.toString());
            contentValues.put("serial_number", sb2.toString());
            contentValues.put("type", sb3.toString());
            contentValues.put("device_id", sb4.toString());
            contentValues.put("color", sb5.toString());
        } else {
            contentValues.put("name", (String) null);
            contentValues.put("serial_number", (String) null);
            contentValues.put("type", (String) null);
            contentValues.put("device_id", (String) null);
            contentValues.put("color", (String) null);
        }
        return contentValues;
    }

    public static DeviceGroup mapToDeviceGroup(Cursor cursor) {
        Preconditions.notNull(cursor, "deviceGroupCursor");
        Preconditions.notNegative(cursor.getPosition(), ViewProps.POSITION);
        Preconditions.precondition(cursor.getPosition() < cursor.getCount(), ViewProps.POSITION);
        return new DeviceGroup.Builder().id(cursor.getLong(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_ID)).identifier(cursor.getString(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_IDENTIFIER)).transport(AccessoryTransport.Type.valueOf(cursor.getString(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_TRANSPORT))).setConditionStandbyTimeout(cursor.getLong(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_STANDBY_TIMEOUT), DeviceGroup.StandbyReason.valueOf(cursor.getString(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_STANDBY_REASON))).devices(mapToDevices(cursor)).build();
    }

    private static List<Device> mapToDevices(Cursor cursor) {
        Preconditions.notNull(cursor, "cursor");
        Preconditions.notNegative(cursor.getPosition(), ViewProps.POSITION);
        boolean z = true;
        Preconditions.precondition(cursor.getPosition() < cursor.getCount(), ViewProps.POSITION);
        List<String> splitDeviceGroupValue = splitDeviceGroupValue(cursor.getString(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_NAME));
        List<String> splitDeviceGroupValue2 = splitDeviceGroupValue(cursor.getString(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_SERIAL_NUMBER));
        List<String> splitDeviceGroupValue3 = splitDeviceGroupValue(cursor.getString(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_TYPE));
        List<String> splitDeviceGroupValue4 = splitDeviceGroupValue(cursor.getString(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_DEVICE_ID));
        List<String> splitDeviceGroupValue5 = splitDeviceGroupValue(cursor.getString(DeviceGroupContract.DeviceGroupV3Columns.COLUMN_INDEX_COLOR));
        int size = splitDeviceGroupValue.size();
        Preconditions.precondition(splitDeviceGroupValue2.size() == size, "size");
        Preconditions.precondition(splitDeviceGroupValue3.size() == size, "size");
        Preconditions.precondition(splitDeviceGroupValue4.size() == size, "size");
        if (splitDeviceGroupValue5.size() != size) {
            z = false;
        }
        Preconditions.precondition(z, "size");
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new Device.Builder().name(splitDeviceGroupValue.get(i)).serialNumber(splitDeviceGroupValue2.get(i)).type(splitDeviceGroupValue3.get(i)).deviceId(Integer.valueOf(Integer.parseInt(splitDeviceGroupValue4.get(i)))).color(Integer.valueOf(Integer.parseInt(splitDeviceGroupValue5.get(i)))).build());
        }
        return arrayList;
    }

    public static String sanitize(String str) {
        return str.replace(DELIMITER, " ");
    }

    public static List<String> splitDeviceGroupValue(@Nullable String str) {
        if (str != null) {
            if (str.length() == 0) {
                return Collections.singletonList("");
            }
            ArrayList arrayList = new ArrayList(Arrays.asList(str.split(DELIMITER)));
            int length = str.length() - str.replace(DELIMITER, "").length();
            for (int size = arrayList.size(); size <= length; size++) {
                arrayList.add("");
            }
            return arrayList;
        }
        return Collections.emptyList();
    }
}
