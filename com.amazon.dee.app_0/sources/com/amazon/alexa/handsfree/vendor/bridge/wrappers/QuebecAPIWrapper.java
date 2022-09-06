package com.amazon.alexa.handsfree.vendor.bridge.wrappers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceStateCallback;
import com.amazon.alexa.handsfree.quebec.QuebecAPIConstants;
import com.amazon.alexa.handsfree.settings.contract.SettingsContract;
import com.amazon.alexa.handsfree.settings.quebec.QuebecSettingsModule;
import com.amazon.alexa.handsfree.settings.quebec.alexaservice.QuebecAlexaAudioProviderService;
import com.amazon.alexa.handsfree.settings.quebec.connection.QuebecWakeWordSettingsServiceConnection;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.QuebecUVRModule;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper;
/* loaded from: classes8.dex */
public class QuebecAPIWrapper implements VendorAPIWrapper {
    private final QuebecSettingsModule mQuebecSettingsModule;
    private final QuebecUVRModule mQuebecUVRModule;

    public QuebecAPIWrapper() {
        this(QuebecSettingsModule.INSTANCE, QuebecUVRModule.getInstance());
    }

    @Override // com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper
    @NonNull
    public Intent getAudioProviderServiceIntent(@NonNull Context context) {
        return new Intent(context, QuebecAlexaAudioProviderService.class);
    }

    @Override // com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper
    @NonNull
    public Intent getPermissionIntent() {
        Intent intent = new Intent(QuebecAPIConstants.QUEBEC_PERMISSIONS_INTENT_ACTION);
        intent.setPackage("com.quicinc.voice.activation");
        intent.setComponent(new ComponentName("com.quicinc.voice.activation", QuebecAPIConstants.QUEBEC_PERMISSIONS_CLASS_NAME));
        return intent;
    }

    @Override // com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper
    @NonNull
    public SettingsContract getSettingsContract() {
        return this.mQuebecSettingsModule.getSettingsContract();
    }

    @Override // com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper
    @NonNull
    public UVRContract getUVRContract(@NonNull Context context) {
        return this.mQuebecUVRModule.getUVRContract(context);
    }

    @Override // com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper
    @NonNull
    public WakeWordSettingsServiceConnection getWakeWordSettingsServiceConnection(@NonNull WakeWordSettingsServiceStateCallback wakeWordSettingsServiceStateCallback, @NonNull Context context) {
        return new QuebecWakeWordSettingsServiceConnection(wakeWordSettingsServiceStateCallback, context);
    }

    public QuebecAPIWrapper(@NonNull QuebecSettingsModule quebecSettingsModule, @NonNull QuebecUVRModule quebecUVRModule) {
        this.mQuebecSettingsModule = quebecSettingsModule;
        this.mQuebecUVRModule = quebecUVRModule;
    }
}
