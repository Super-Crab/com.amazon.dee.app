package com.amazon.alexa.accessoryclient.client.accessories;

import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.kota.KotaDownloader;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient;
import com.amazon.alexa.accessoryclient.client.AlexaAccessoryClient;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.dee.app.metrics.MetricsConstants;
import io.reactivex.rxjava3.core.Completable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
/* compiled from: Accessories.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\u001fJ\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u001cH&J\b\u0010\u001d\u001a\u00020\u001cH&J\u0010\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH&¨\u0006 "}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/Accessories;", "", "createRequestEnableBluetooth", "Landroid/content/Intent;", "createRequestIgnoreBatteryOptimizations", "getAccessorySession", "Lcom/amazon/alexa/accessoryclient/client/accessories/AccessorySession;", "identifier", "", "getCompanionDeviceModule", "Lcom/amazon/alexa/accessoryclient/client/accessories/CompanionDeviceModule;", "getDeviceAccountSupplier", "Lcom/amazon/alexa/accessoryclient/client/accessories/DeviceAccountSupplier;", "getDeviceSupplier", "Lcom/amazon/alexa/accessory/repositories/device/v2/DeviceSupplierV2;", "getKotaDownloader", "Lcom/amazon/alexa/accessory/kota/KotaDownloader;", "getRegistrationSupplier", "Lcom/amazon/alexa/accessoryclient/client/accessories/RegistrationSupplier;", "getScanner", "Lcom/amazon/alexa/accessoryclient/client/accessories/AccessoryScanner;", "getSessionSupplier", "Lcom/amazon/alexa/accessoryclient/client/accessories/SessionSupplier;", "linkAccessory", "Lio/reactivex/rxjava3/core/Completable;", ModelTransformer.KEY_ACCESSORY, "Lcom/amazon/alexa/accessory/Accessory;", "shouldRequestEnableBluetooth", "", "shouldRequestIgnoreBatteryOptimizations", "unlinkAccessory", "Shared", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface Accessories {

    /* compiled from: Accessories.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/Accessories$Shared;", "", "()V", "clientHasBeenCreated", "", JoinPoint.SYNCHRONIZATION_LOCK, "Ljava/lang/Object;", "sharedClient", "Lcom/amazon/alexa/accessoryclient/client/AlexaAccessoryClient;", MetricsConstants.Method.CACHE_GET, "Lcom/amazon/alexa/accessoryclient/client/accessories/Accessories;", "context", "Landroid/content/Context;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Shared {
        private static boolean clientHasBeenCreated;
        private static AlexaAccessoryClient sharedClient;
        public static final Shared INSTANCE = new Shared();
        private static final Object lock = new Object();

        private Shared() {
        }

        @NotNull
        public final Accessories get(@NotNull Context context) {
            Accessories accessories;
            Intrinsics.checkParameterIsNotNull(context, "context");
            synchronized (lock) {
                if (!clientHasBeenCreated) {
                    clientHasBeenCreated = true;
                    sharedClient = new AlexaAccessoryClient(context, null, null, 6, null);
                    AlexaAccessoryClient alexaAccessoryClient = sharedClient;
                    if (alexaAccessoryClient == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("sharedClient");
                    }
                    AbstractAlexaAccessoryClient.initialize$default(alexaAccessoryClient, 0L, 1, null);
                }
                AlexaAccessoryClient alexaAccessoryClient2 = sharedClient;
                if (alexaAccessoryClient2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sharedClient");
                }
                accessories = alexaAccessoryClient2.getAccessories();
            }
            return accessories;
        }
    }

    @NotNull
    Intent createRequestEnableBluetooth();

    @NotNull
    Intent createRequestIgnoreBatteryOptimizations();

    @NotNull
    AccessorySession getAccessorySession(@NotNull String str);

    @NotNull
    CompanionDeviceModule getCompanionDeviceModule();

    @NotNull
    DeviceAccountSupplier getDeviceAccountSupplier();

    @NotNull
    DeviceSupplierV2 getDeviceSupplier();

    @NotNull
    KotaDownloader getKotaDownloader();

    @NotNull
    RegistrationSupplier getRegistrationSupplier();

    @NotNull
    AccessoryScanner getScanner();

    @NotNull
    SessionSupplier getSessionSupplier();

    @NotNull
    Completable linkAccessory(@NotNull Accessory accessory);

    boolean shouldRequestEnableBluetooth();

    boolean shouldRequestIgnoreBatteryOptimizations();

    @NotNull
    Completable unlinkAccessory(@NotNull Accessory accessory);
}
