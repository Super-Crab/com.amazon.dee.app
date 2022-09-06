package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.persistence.FileBackedJsonRxMapStore;
import com.amazon.alexa.accessory.persistence.RxMapStore;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public class FileBackedMobilyticsDeviceSupplier implements MobilyticsDeviceSupplier {
    private static final String CURRENT_MOBILYTICS_DEVICE_KEY = "currentMobilyticsDevice";
    @VisibleForTesting
    static final String MOBILYTICS_DEVICE_STORE_FILE_PATH = "/accessories/mobilyticsDeviceStore.json";
    private static final String TAG = "FileBackedMobilyticsDeviceSupplier:";
    private static RxMapStore<String, AccessoryMobilyticsDevice> mostRecentMobilyticsDeviceStore;
    private final Context context;

    public FileBackedMobilyticsDeviceSupplier(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Single<AccessoryMobilyticsDevice> commitMobityticsDevice(@NonNull Context context, @NonNull AccessoryMobilyticsDevice accessoryMobilyticsDevice) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(accessoryMobilyticsDevice, "accessoryMobilyticsDevice");
        return getStore(context).put(CURRENT_MOBILYTICS_DEVICE_KEY, accessoryMobilyticsDevice);
    }

    private static synchronized RxMapStore<String, AccessoryMobilyticsDevice> getStore(Context context) {
        RxMapStore<String, AccessoryMobilyticsDevice> rxMapStore;
        synchronized (FileBackedMobilyticsDeviceSupplier.class) {
            Preconditions.notNull(context, "context");
            if (mostRecentMobilyticsDeviceStore == null) {
                mostRecentMobilyticsDeviceStore = new FileBackedJsonRxMapStore(new File(context.getFilesDir(), MOBILYTICS_DEVICE_STORE_FILE_PATH), AccessoryMobilyticsDevice.FACTORY, "mobilyticsDeviceStore", "mobilyticsDeviceSets", "mobilyticsDevice");
            }
            rxMapStore = mostRecentMobilyticsDeviceStore;
        }
        return rxMapStore;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AccessoryMobilyticsDevice lambda$queryMobilyticsDevice$0(Map map) throws Throwable {
        Set set = (Set) map.get(CURRENT_MOBILYTICS_DEVICE_KEY);
        if (set == null) {
            return AccessoryMobilyticsDevice.UNKNOWN;
        }
        Iterator it2 = set.iterator();
        if (!it2.hasNext()) {
            return AccessoryMobilyticsDevice.UNKNOWN;
        }
        return (AccessoryMobilyticsDevice) it2.next();
    }

    @VisibleForTesting
    static void resetStoreForTest() {
        mostRecentMobilyticsDeviceStore = null;
    }

    @Override // com.amazon.alexa.accessorykit.interprocess.mobilytics.MobilyticsDeviceSupplier
    public Observable<AccessoryMobilyticsDevice> queryMobilyticsDevice() {
        return getStore(this.context).queryValues().map($$Lambda$FileBackedMobilyticsDeviceSupplier$jgJdrhq3dUnQ4xSs8HQwfWzaJaE.INSTANCE).onErrorReturn($$Lambda$FileBackedMobilyticsDeviceSupplier$E91n6iW56c1HmiE2GiBa3SzlxE.INSTANCE);
    }
}
