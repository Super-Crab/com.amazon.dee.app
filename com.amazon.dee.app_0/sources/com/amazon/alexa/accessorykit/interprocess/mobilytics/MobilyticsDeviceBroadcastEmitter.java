package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.concurrent.Callable;
import org.json.JSONException;
/* loaded from: classes6.dex */
public class MobilyticsDeviceBroadcastEmitter {
    private static final String TAG = "MobilyticsDeviceBroadcastEmitter:";
    private boolean active = false;
    private final Context context;
    private final DeviceDataStore deviceDataStore;
    private final Object lock;
    private final UserSupplier userSupplier;

    public MobilyticsDeviceBroadcastEmitter(@NonNull Context context, @NonNull UserSupplier userSupplier, @NonNull DeviceDataStore deviceDataStore) {
        Preconditions.notNull(context, "context");
        Preconditions.notNull(userSupplier, "userSupplier");
        Preconditions.notNull(deviceDataStore, "deviceDataStore");
        this.context = context;
        this.userSupplier = userSupplier;
        this.deviceDataStore = deviceDataStore;
        this.lock = new Object();
    }

    private static Intent createDeviceChangedIntent(@NonNull AccessoryMobilyticsDevice accessoryMobilyticsDevice, @NonNull Context context) throws JSONException {
        return new Intent(MobilyticsDeviceBroadcastReceiver.MOBILYTICS_DEVICE_CHANGED_BASE_INTENT).setClass(context, MobilyticsDeviceBroadcastReceiver.class).putExtra("mobilyticsDevicePayload", accessoryMobilyticsDevice.toJsonObject().toString());
    }

    private Observable<AccessoryMobilyticsDevice> getMobilyticsDeviceFromDeviceDataStore(@NonNull final DeviceDataStore deviceDataStore) {
        return Observable.fromCallable(new Callable() { // from class: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$MobilyticsDeviceBroadcastEmitter$A-YSzgXC2qG4Vq1n0DDelNvANc8
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MobilyticsDeviceBroadcastEmitter.lambda$getMobilyticsDeviceFromDeviceDataStore$2(DeviceDataStore.this);
            }
        }).subscribeOn(Schedulers.io());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AccessoryMobilyticsDevice lambda$getMobilyticsDeviceFromDeviceDataStore$2(DeviceDataStore deviceDataStore) throws Exception {
        try {
            String value = deviceDataStore.getValue("DeviceType");
            if (TextUtils.isEmpty(value)) {
                value = "Unknown";
            }
            String value2 = deviceDataStore.getValue(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
            if (TextUtils.isEmpty(value2)) {
                value2 = "Unknown";
            }
            return new AccessoryMobilyticsDevice(value, value2);
        } catch (DeviceDataStoreException e) {
            Logger.e("%s Exception while getting device information from mobilytics", e, TAG);
            return AccessoryMobilyticsDevice.UNKNOWN;
        }
    }

    @SuppressLint({"CheckResult"})
    public MobilyticsDeviceBroadcastEmitter activate() {
        synchronized (this.lock) {
            if (this.active) {
                return this;
            }
            this.active = true;
            Logger.d("%s activate()", TAG);
            this.userSupplier.queryUser().concatMap(new Function() { // from class: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$MobilyticsDeviceBroadcastEmitter$oVFGW6VM1HbvCeWX-ONFQgz5E64
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return MobilyticsDeviceBroadcastEmitter.this.lambda$activate$0$MobilyticsDeviceBroadcastEmitter((User) obj);
                }
            }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$MobilyticsDeviceBroadcastEmitter$l1GvJeFR3wvLz9NL1bKNEm5TRUI
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    MobilyticsDeviceBroadcastEmitter.this.lambda$activate$1$MobilyticsDeviceBroadcastEmitter((AccessoryMobilyticsDevice) obj);
                }
            });
            return this;
        }
    }

    public /* synthetic */ ObservableSource lambda$activate$0$MobilyticsDeviceBroadcastEmitter(User user) throws Throwable {
        return getMobilyticsDeviceFromDeviceDataStore(this.deviceDataStore);
    }

    public /* synthetic */ void lambda$activate$1$MobilyticsDeviceBroadcastEmitter(AccessoryMobilyticsDevice accessoryMobilyticsDevice) throws Throwable {
        Logger.d("%s Emitting current Device: %s", TAG, accessoryMobilyticsDevice);
        Context context = this.context;
        context.sendBroadcast(createDeviceChangedIntent(accessoryMobilyticsDevice, context), "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION");
    }
}
