package com.amazon.alexa.accessory.persistence.device;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import com.amazon.alexa.accessory.internal.util.DeviceDatabaseUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.persistence.DatabaseQuery;
import com.amazon.alexa.accessory.persistence.ObservableDatabase;
import com.amazon.alexa.accessory.persistence.device.DeviceContract;
import com.amazon.alexa.accessory.persistence.device.scheme.DeviceScheme;
import com.amazon.alexa.accessory.persistence.device.v2.DeviceGroupContract;
import com.amazon.alexa.accessory.repositories.device.DeviceSupplier;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
/* loaded from: classes.dex */
public final class DeviceDatabase extends ObservableDatabase implements DeviceGroupContract, DeviceSupplier, DeviceSupplierV2 {
    private final DatabaseQuery allDeviceGroupsQuery;
    private final SQLiteOpenHelper helper;

    public DeviceDatabase(Context context) {
        Preconditions.notNull(context, "context");
        this.helper = new DeviceOpenHelper(context, new DeviceScheme());
        if (Logger.shouldLog(Logger.Level.VERBOSE)) {
            Cursor rawQuery = this.helper.getReadableDatabase().rawQuery("SELECT * from device_groups_v3", null);
            try {
                Logger.v("DeviceDatabase.ctor: device_groups:");
                Logger.v("DeviceDatabase.ctor: count = " + rawQuery.getCount());
                Logger.v("DeviceDatabase.ctor: cursor = " + DatabaseUtils.dumpCursorToString(rawQuery));
                rawQuery.close();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (rawQuery != null) {
                        try {
                            rawQuery.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        this.allDeviceGroupsQuery = new DatabaseQuery(this.helper, DeviceGroupContract.Tables.DEVICE_GROUPS_V3, DeviceGroupContract.DeviceGroupV3Columns.COLUMNS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$hasDeviceGroup$2(DatabaseQuery databaseQuery) throws Throwable {
        Cursor run = databaseQuery.run();
        try {
            boolean z = true;
            if (run.getCount() != 1) {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            run.close();
            return valueOf;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (run != null) {
                    try {
                        run.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceSupplier
    public Single<DeviceContract.Device> addDevice(DeviceContract.Device device) {
        return addDeviceGroup(DeviceContract.Device.convertDeviceToDeviceGroup(device)).map($$Lambda$PS0SfmfT7V_YwcpMNbpo_zXOiw.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    public Single<DeviceGroup> addDeviceGroup(final DeviceGroup deviceGroup) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceDatabase trying to add \"");
        outline107.append(deviceGroup.getIdentifier());
        outline107.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        Logger.d(outline107.toString(), new RuntimeException("stack trace"));
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.accessory.persistence.device.-$$Lambda$DeviceDatabase$GaXcK_L--XKdc8W3YY7ccZxzRS0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DeviceDatabase.this.lambda$addDeviceGroup$0$DeviceDatabase(deviceGroup);
            }
        }).subscribeOn(Schedulers.io()).compose(triggerSingle(DeviceGroupContract.Tables.DEVICE_GROUPS_V3));
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceSupplier
    public Single<DeviceContract.Device> getDevice(String str) {
        return getDeviceGroup(str).map($$Lambda$PS0SfmfT7V_YwcpMNbpo_zXOiw.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    public Single<DeviceGroup> getDeviceGroup(String str) {
        Logger.d("DeviceDatabase trying to get \"" + str + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        return createQuery(new DatabaseQuery(this.helper, DeviceGroupContract.Tables.DEVICE_GROUPS_V3, DeviceGroupContract.DeviceGroupV3Columns.COLUMNS, "identifier=?", new String[]{str})).mapToOne($$Lambda$tsKFn3xC9lYSuzxsE8FwV0ijX64.INSTANCE).firstOrError();
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceSupplier
    public Observable<List<DeviceContract.Device>> getDevices() {
        return queryDeviceGroups().map($$Lambda$gn44N1o8KyLA88B_bpD0yqSixGE.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceSupplier
    public Single<Boolean> hasDevice(String str) {
        return hasDeviceGroup(str);
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    public Single<Boolean> hasDeviceGroup(String str) {
        return createQuery(new DatabaseQuery(this.helper, DeviceGroupContract.Tables.DEVICE_GROUPS_V3, new String[]{"_id"}, "identifier=?", new String[]{str})).map($$Lambda$DeviceDatabase$E42la9myN9BgLuhkCtQz94e0cQ.INSTANCE).firstOrError();
    }

    public /* synthetic */ DeviceGroup lambda$addDeviceGroup$0$DeviceDatabase(DeviceGroup deviceGroup) throws Exception {
        long insertOrThrow = this.helper.getWritableDatabase().insertOrThrow(DeviceGroupContract.Tables.DEVICE_GROUPS_V3, null, DeviceDatabaseUtils.deviceGroupV3ToContentValues(deviceGroup));
        if (insertOrThrow != -1) {
            return new DeviceGroup.Builder(deviceGroup).id(insertOrThrow).build();
        }
        throw new IllegalStateException("Failed to insert a device into database!");
    }

    public /* synthetic */ Object lambda$removeDeviceGroup$1$DeviceDatabase(long j) throws Exception {
        int delete = this.helper.getWritableDatabase().delete(DeviceGroupContract.Tables.DEVICE_GROUPS_V3, "_id=?", new String[]{String.valueOf(j)});
        if (delete != 0) {
            return Integer.valueOf(delete);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("There is no row with id ", j));
    }

    public /* synthetic */ Object lambda$updateDeviceGroup$3$DeviceDatabase(ContentValues contentValues, DeviceGroup deviceGroup) throws Exception {
        int update = this.helper.getWritableDatabase().update(DeviceGroupContract.Tables.DEVICE_GROUPS_V3, contentValues, "_id=?", new String[]{String.valueOf(deviceGroup.getId())});
        if (update == 1) {
            return Integer.valueOf(update);
        }
        throw new IllegalStateException(String.format(Locale.US, "DeviceDatabase update did not affect exactly 1 row when updating a device. Rows affected: %d. DeviceGroup: %s", Integer.valueOf(update), deviceGroup));
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    public Observable<List<DeviceGroup>> queryDeviceGroups() {
        return createQuery(this.allDeviceGroupsQuery).mapToList($$Lambda$tsKFn3xC9lYSuzxsE8FwV0ijX64.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceSupplier
    public Completable removeDevice(long j) {
        return removeDeviceGroup(j);
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    public Completable removeDeviceGroup(final long j) {
        Logger.w(GeneratedOutlineSupport1.outline57("DeviceDatabase trying to delete \"", j, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED), new RuntimeException("Stack trace captured for debugging"));
        return Completable.fromCallable(new Callable() { // from class: com.amazon.alexa.accessory.persistence.device.-$$Lambda$DeviceDatabase$2WGKU6J1FvSWrKri9PIXa2y2gho
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DeviceDatabase.this.lambda$removeDeviceGroup$1$DeviceDatabase(j);
            }
        }).subscribeOn(Schedulers.io()).compose(triggerCompletable(DeviceGroupContract.Tables.DEVICE_GROUPS_V3));
    }

    @Override // com.amazon.alexa.accessory.repositories.device.DeviceSupplier
    public Completable updateDevice(DeviceContract.Device device) {
        return updateDeviceGroup(DeviceContract.Device.convertDeviceToDeviceGroup(device));
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    public Completable updateDeviceGroup(final DeviceGroup deviceGroup) {
        final ContentValues deviceGroupV3ToContentValues = DeviceDatabaseUtils.deviceGroupV3ToContentValues(deviceGroup);
        Logger.d("DeviceDatabase trying to update a row with \"" + deviceGroupV3ToContentValues + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        return Completable.fromCallable(new Callable() { // from class: com.amazon.alexa.accessory.persistence.device.-$$Lambda$DeviceDatabase$jxQzRAuSB_6AKV0QeX_78XvQqAQ
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DeviceDatabase.this.lambda$updateDeviceGroup$3$DeviceDatabase(deviceGroupV3ToContentValues, deviceGroup);
            }
        }).subscribeOn(Schedulers.io()).compose(triggerCompletable(DeviceGroupContract.Tables.DEVICE_GROUPS_V3));
    }

    @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2
    public Completable removeDeviceGroup(DeviceGroup deviceGroup) {
        return removeDeviceGroup(deviceGroup.getId());
    }
}
