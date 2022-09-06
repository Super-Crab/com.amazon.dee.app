package com.amazon.alexa.voice.handsfree.settings.providers;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordServiceConnectionProviderContract;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceStateCallback;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper;
/* loaded from: classes11.dex */
public class WakeWordServiceConnectionProvider implements WakeWordServiceConnectionProviderContract {
    private static final String TAG = "WakeWordServiceConnectionProvider";
    private VendorAPIWrapper mVendorAPIWrapper;

    public WakeWordServiceConnectionProvider(@NonNull VendorAPIWrapper vendorAPIWrapper) {
        this.mVendorAPIWrapper = vendorAPIWrapper;
    }

    @Override // com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordServiceConnectionProviderContract
    public WakeWordSettingsServiceConnection getWakeWordSettingsServiceConnection(@NonNull WakeWordSettingsServiceStateCallback wakeWordSettingsServiceStateCallback, @NonNull Context context) {
        return this.mVendorAPIWrapper.getWakeWordSettingsServiceConnection(wakeWordSettingsServiceStateCallback, context);
    }
}
