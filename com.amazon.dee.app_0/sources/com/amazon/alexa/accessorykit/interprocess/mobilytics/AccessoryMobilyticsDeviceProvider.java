package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.mobilytics.identity.MobilyticsDevice;
import com.amazon.alexa.mobilytics.identity.MobilyticsDeviceProvider;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public class AccessoryMobilyticsDeviceProvider implements MobilyticsDeviceProvider {
    private static final String TAG = "AccessoryMobilyticsDeviceProvider:";
    private final Object lock;
    private final MobilyticsDeviceSupplier mobilyticsDeviceSupplier;
    private final List<MobilyticsDeviceProvider.Listener> listeners = new ArrayList();
    private MobilyticsDevice currentMobilyticsDevice = AccessoryMobilyticsDevice.UNKNOWN;
    private boolean active = false;

    public AccessoryMobilyticsDeviceProvider(MobilyticsDeviceSupplier mobilyticsDeviceSupplier) {
        Preconditions.notNull(mobilyticsDeviceSupplier, "mobilyticsDeviceSupplier");
        this.lock = new Object();
        this.mobilyticsDeviceSupplier = mobilyticsDeviceSupplier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void commitMobilyticsDevice(MobilyticsDevice mobilyticsDevice) {
        synchronized (this.lock) {
            this.currentMobilyticsDevice = mobilyticsDevice;
            for (MobilyticsDeviceProvider.Listener listener : this.listeners) {
                listener.onDeviceDetailChange(mobilyticsDevice);
            }
        }
    }

    public AccessoryMobilyticsDeviceProvider activate() {
        synchronized (this.lock) {
            if (this.active) {
                return this;
            }
            this.active = true;
            Logger.d("%s activate()", TAG);
            this.mobilyticsDeviceSupplier.queryMobilyticsDevice().subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$AccessoryMobilyticsDeviceProvider$v5IvwYm1L_nJOPdjz6SilHDZAgA
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    AccessoryMobilyticsDeviceProvider.this.commitMobilyticsDevice((AccessoryMobilyticsDevice) obj);
                }
            }, $$Lambda$AccessoryMobilyticsDeviceProvider$GhF3dDDs039bCCB8mWvQSH7AA.INSTANCE);
            return this;
        }
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsDeviceProvider
    public void addListener(@NonNull MobilyticsDeviceProvider.Listener listener) {
        synchronized (this.lock) {
            this.listeners.add(listener);
        }
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsDeviceProvider
    public MobilyticsDevice device() {
        MobilyticsDevice mobilyticsDevice;
        synchronized (this.lock) {
            mobilyticsDevice = this.currentMobilyticsDevice;
        }
        return mobilyticsDevice;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsDeviceProvider
    public void removeListener(@NonNull MobilyticsDeviceProvider.Listener listener) {
        synchronized (this.lock) {
            this.listeners.remove(listener);
        }
    }
}
