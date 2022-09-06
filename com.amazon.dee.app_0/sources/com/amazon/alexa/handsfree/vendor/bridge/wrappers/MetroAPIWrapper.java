package com.amazon.alexa.handsfree.vendor.bridge.wrappers;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceStateCallback;
import com.amazon.alexa.handsfree.settings.contract.SettingsContract;
import com.amazon.alexa.handsfree.settings.metro.MetroSettingsModule;
import com.amazon.alexa.handsfree.settings.metro.alexaservice.MetroAlexaAudioProviderService;
import com.amazon.alexa.handsfree.settings.metro.connection.MetroWakeWordSettingsServiceConnection;
import com.amazon.alexa.handsfree.uservoicerecognition.metro.MetroUVRModule;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper;
import com.magiear.handsfree.util.Common;
/* loaded from: classes8.dex */
public class MetroAPIWrapper implements VendorAPIWrapper {
    private MetroSettingsModule mMetroSettingsModule;
    private MetroUVRModule mMetroUVRModule;

    public MetroAPIWrapper() {
        this(MetroSettingsModule.INSTANCE, MetroUVRModule.getInstance());
    }

    @Override // com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper
    @NonNull
    public Intent getAudioProviderServiceIntent(@NonNull Context context) {
        return new Intent(context, MetroAlexaAudioProviderService.class);
    }

    @Override // com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper
    @NonNull
    public Intent getPermissionIntent() {
        Intent intent = new Intent(Common.INTENT_ACTION_PERMISSION_REQUEST);
        intent.setPackage(Common.HANDSFREE_ASSISTANT_PACKAGE_NAME);
        return intent;
    }

    @Override // com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper
    @NonNull
    public SettingsContract getSettingsContract() {
        return this.mMetroSettingsModule.getSettingsContract();
    }

    @Override // com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper
    @NonNull
    public UVRContract getUVRContract(@NonNull Context context) {
        return this.mMetroUVRModule.getUVRContract(context);
    }

    @Override // com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper
    @NonNull
    public WakeWordSettingsServiceConnection getWakeWordSettingsServiceConnection(@NonNull WakeWordSettingsServiceStateCallback wakeWordSettingsServiceStateCallback, @NonNull Context context) {
        return new MetroWakeWordSettingsServiceConnection(wakeWordSettingsServiceStateCallback, context);
    }

    public MetroAPIWrapper(@NonNull MetroSettingsModule metroSettingsModule, @NonNull MetroUVRModule metroUVRModule) {
        this.mMetroSettingsModule = metroSettingsModule;
        this.mMetroUVRModule = metroUVRModule;
    }
}
