package com.amazon.alexa.accessory.frames.connectivity;

import android.content.Context;
import android.os.Looper;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.frames.provider.AccessoryUtil;
import com.amazon.alexa.accessory.frames.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public final class ConnectivityManager {
    private static final String TAG = "ConnectivityManager";
    private static final String ZION_DEVICE_TYPE = "A3IYPH06PH1HRA";
    private static ConnectivityManager connectivityManager;
    private Accessories clientAccessories;
    private final CompositeDisposable compositeDisposable;

    private ConnectivityManager(Context context, Accessories accessories) {
        Log.d(TAG, "Construct ConnectivityModule");
        this.clientAccessories = accessories;
        this.compositeDisposable = new CompositeDisposable();
    }

    private void addConnectivityListener() {
        Log.d(TAG, "addListener");
        clientConnectionObservable();
    }

    private void clientConnectionObservable() {
        this.compositeDisposable.add(this.clientAccessories.getSessionSupplier().observeSessionConnected().observeOn(AndroidSchedulers.mainThread()).subscribe($$Lambda$ConnectivityManager$HL3c98YNz9x3zrXdgqb_0gy366U.INSTANCE, $$Lambda$ConnectivityManager$z81bJTAqKmpftrWqh1LWeMdyfIM.INSTANCE));
    }

    private void initActiveAccessories() {
        this.clientAccessories.getSessionSupplier().getActiveAccessories().observeOn(AndroidSchedulers.mainThread()).subscribe($$Lambda$ConnectivityManager$c7ullj8tMt0b1WwvNyyoZSQFLp4.INSTANCE, $$Lambda$ConnectivityManager$1fuR_Lb7wFzB48eXxDHzVqFxhE4.INSTANCE);
    }

    public static synchronized void initConnectivityModule(Context context, Accessories accessories) throws IllegalArgumentException {
        synchronized (ConnectivityManager.class) {
            Log.d(TAG, "initConnectivityModule called");
            if (connectivityManager == null) {
                Log.d(TAG, "initConnectivityModule - First time init");
                if (context != null) {
                    connectivityManager = new ConnectivityManager(context, accessories);
                    connectivityManager.addConnectivityListener();
                } else {
                    Log.d(TAG, "initConnectivityModule - Context is null, throw exception");
                    throw new IllegalArgumentException("Cannot initialize ConnectivityModule with a null Context.");
                }
            }
            connectivityManager.initActiveAccessories();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$clientConnectionObservable$4(Accessory accessory) throws Throwable {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryClient: onAccessorySessionConnected: ");
        outline107.append(accessory.getName());
        outline107.append(" --- ");
        outline107.append(accessory.getAddress());
        Log.d(str, outline107.toString());
        onDeviceConnection(accessory, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initActiveAccessories$0(List list) throws Throwable {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            Accessory accessory = (Accessory) it2.next();
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryClient: initActiveAccessories -- ");
            outline107.append(accessory.getAddress());
            Log.d(str, outline107.toString());
            onDeviceConnection(accessory, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initActiveAccessories$1(Throwable th) throws Throwable {
        String str = TAG;
        Log.e(str, "initActiveAccessories -- onError " + th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onDeviceConnection$2(boolean z, Accessory accessory, Device.DeviceInformation deviceInformation) throws Throwable {
        String deviceType = deviceInformation.getDeviceType();
        String str = TAG;
        Log.d(str, "AccessoryClient: updateDeviceConnectivity - deviceType " + deviceType);
        if (!deviceType.toUpperCase().equals("A3IYPH06PH1HRA") || !z) {
            return;
        }
        AccessoryUtil.subscribeToAccessoryStateChanges(accessory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onDeviceConnection$3(Throwable th) throws Throwable {
        String str = TAG;
        Log.d(str, "AccessoryClient: updateDeviceConnectivity - onError - " + th);
    }

    static void onDeviceConnection(final Accessory accessory, final boolean z) {
        preconditionMainThread();
        DependencyProvider.getClientAccessories().getAccessorySession(accessory.getAddress()).getDeviceRepository().queryDeviceInformationSet().firstOrError().map($$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w.INSTANCE).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.frames.connectivity.-$$Lambda$ConnectivityManager$nMHNKocazxGSBs_44ZQkj5gvcww
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ConnectivityManager.lambda$onDeviceConnection$2(z, accessory, (Device.DeviceInformation) obj);
            }
        }, $$Lambda$ConnectivityManager$UzAYt4EGPm0GGEegHoRlWmkcE.INSTANCE);
    }

    private static void preconditionMainThread() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("Current thread must be a main thread");
        Log.e(TAG, "Throwing ", illegalStateException);
        throw illegalStateException;
    }
}
