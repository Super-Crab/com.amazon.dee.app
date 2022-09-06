package com.amazon.alexa.accessoryclient.client.accessories;

import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.kota.KotaDownloader;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessoryclient.common.query.ApiIdentifier;
import com.amazon.alexa.accessoryclient.common.query.request.AccessoryRequest;
import com.amazon.alexa.accessoryclient.common.rxipc.RxIPCClient;
import com.amazon.alexa.accessorykit.ModelTransformer;
import io.reactivex.rxjava3.core.Completable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClientAccessories.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020/H\u0016J\u0010\u00101\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/accessories/ClientAccessories;", "Lcom/amazon/alexa/accessoryclient/client/accessories/Accessories;", "context", "Landroid/content/Context;", "rxIPCClient", "Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;", "(Landroid/content/Context;Lcom/amazon/alexa/accessoryclient/common/rxipc/RxIPCClient;)V", "clientAccessoryScanner", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientAccessoryScanner;", "clientCompanionDeviceModule", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientCompanionDeviceModule;", "clientDeviceAccountSupplier", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientDeviceAccountSupplier;", "clientKotaDownloader", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientKotaDownloader;", "clientRegistrationSupplier", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientRegistrationSupplier;", "clientSessionSupplier", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientSessionSupplier;", "deviceSupplier", "Lcom/amazon/alexa/accessoryclient/client/accessories/ClientDeviceSupplier;", "createRequestEnableBluetooth", "Landroid/content/Intent;", "createRequestIgnoreBatteryOptimizations", "getAccessorySession", "Lcom/amazon/alexa/accessoryclient/client/accessories/AccessorySession;", "identifier", "", "getCompanionDeviceModule", "Lcom/amazon/alexa/accessoryclient/client/accessories/CompanionDeviceModule;", "getDeviceAccountSupplier", "Lcom/amazon/alexa/accessoryclient/client/accessories/DeviceAccountSupplier;", "getDeviceSupplier", "Lcom/amazon/alexa/accessory/repositories/device/v2/DeviceSupplierV2;", "getKotaDownloader", "Lcom/amazon/alexa/accessory/kota/KotaDownloader;", "getRegistrationSupplier", "Lcom/amazon/alexa/accessoryclient/client/accessories/RegistrationSupplier;", "getScanner", "Lcom/amazon/alexa/accessoryclient/client/accessories/AccessoryScanner;", "getSessionSupplier", "Lcom/amazon/alexa/accessoryclient/client/accessories/SessionSupplier;", "linkAccessory", "Lio/reactivex/rxjava3/core/Completable;", ModelTransformer.KEY_ACCESSORY, "Lcom/amazon/alexa/accessory/Accessory;", "shouldRequestEnableBluetooth", "", "shouldRequestIgnoreBatteryOptimizations", "unlinkAccessory", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ClientAccessories implements Accessories {
    private final ClientAccessoryScanner clientAccessoryScanner;
    private final ClientCompanionDeviceModule clientCompanionDeviceModule;
    private final ClientDeviceAccountSupplier clientDeviceAccountSupplier;
    private final ClientKotaDownloader clientKotaDownloader;
    private final ClientRegistrationSupplier clientRegistrationSupplier;
    private final ClientSessionSupplier clientSessionSupplier;
    private final Context context;
    private final ClientDeviceSupplier deviceSupplier;
    private final RxIPCClient rxIPCClient;

    public ClientAccessories(@NotNull Context context, @NotNull RxIPCClient rxIPCClient) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(rxIPCClient, "rxIPCClient");
        this.context = context;
        this.rxIPCClient = rxIPCClient;
        this.clientSessionSupplier = new ClientSessionSupplier(this.rxIPCClient);
        this.deviceSupplier = new ClientDeviceSupplier(this.rxIPCClient);
        this.clientAccessoryScanner = new ClientAccessoryScanner(this.rxIPCClient);
        this.clientRegistrationSupplier = new ClientRegistrationSupplier(this.rxIPCClient);
        this.clientCompanionDeviceModule = new ClientCompanionDeviceModule(this.rxIPCClient);
        this.clientKotaDownloader = new ClientKotaDownloader(this.rxIPCClient);
        this.clientDeviceAccountSupplier = new ClientDeviceAccountSupplier(this.rxIPCClient);
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    @NotNull
    public Intent createRequestEnableBluetooth() {
        Intent createRequestEnableBluetoothStatic = com.amazon.alexa.accessory.Accessories.createRequestEnableBluetoothStatic();
        Intrinsics.checkExpressionValueIsNotNull(createRequestEnableBluetoothStatic, "com.amazon.alexa.accesso…stEnableBluetoothStatic()");
        return createRequestEnableBluetoothStatic;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    @NotNull
    public Intent createRequestIgnoreBatteryOptimizations() {
        Intent createRequestIgnoreBatteryOptimizations = com.amazon.alexa.accessory.Accessories.createRequestIgnoreBatteryOptimizations(this.context);
        Intrinsics.checkExpressionValueIsNotNull(createRequestIgnoreBatteryOptimizations, "com.amazon.alexa.accesso…eryOptimizations(context)");
        return createRequestIgnoreBatteryOptimizations;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    @NotNull
    public AccessorySession getAccessorySession(@NotNull String identifier) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        return new ClientAccessorySession(identifier, this.rxIPCClient);
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    @NotNull
    public CompanionDeviceModule getCompanionDeviceModule() {
        return this.clientCompanionDeviceModule;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    @NotNull
    public DeviceAccountSupplier getDeviceAccountSupplier() {
        return this.clientDeviceAccountSupplier;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    @NotNull
    public DeviceSupplierV2 getDeviceSupplier() {
        return this.deviceSupplier;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    @NotNull
    public KotaDownloader getKotaDownloader() {
        return this.clientKotaDownloader;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    @NotNull
    public RegistrationSupplier getRegistrationSupplier() {
        return this.clientRegistrationSupplier;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    @NotNull
    public AccessoryScanner getScanner() {
        return this.clientAccessoryScanner;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    @NotNull
    public SessionSupplier getSessionSupplier() {
        return this.clientSessionSupplier;
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    @NotNull
    public Completable linkAccessory(@NotNull Accessory accessory) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        return this.rxIPCClient.createCompletable(ApiIdentifier.LINK_ACCESSORY, new AccessoryRequest(accessory));
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    public boolean shouldRequestEnableBluetooth() {
        return com.amazon.alexa.accessory.Accessories.shouldRequestEnableBluetooth(this.context);
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    public boolean shouldRequestIgnoreBatteryOptimizations() {
        return com.amazon.alexa.accessory.Accessories.shouldRequestIgnoreBatteryOptimizations(this.context);
    }

    @Override // com.amazon.alexa.accessoryclient.client.accessories.Accessories
    @NotNull
    public Completable unlinkAccessory(@NotNull Accessory accessory) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        return this.rxIPCClient.createCompletable(ApiIdentifier.UNLINK_ACCESSORY, new AccessoryRequest(accessory));
    }
}
