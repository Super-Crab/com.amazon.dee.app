package com.amazon.alexa.handsfree.vendor.bridge;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceStateCallback;
import com.amazon.alexa.handsfree.settings.contract.SettingsContract;
/* loaded from: classes8.dex */
public interface VendorAPIWrapper {
    @NonNull
    Intent getAudioProviderServiceIntent(Context context);

    @NonNull
    Intent getPermissionIntent();

    @NonNull
    SettingsContract getSettingsContract();

    @NonNull
    UVRContract getUVRContract(@NonNull Context context);

    @NonNull
    WakeWordSettingsServiceConnection getWakeWordSettingsServiceConnection(WakeWordSettingsServiceStateCallback wakeWordSettingsServiceStateCallback, Context context);
}
