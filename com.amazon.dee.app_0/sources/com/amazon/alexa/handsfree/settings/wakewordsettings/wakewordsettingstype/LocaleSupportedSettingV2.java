package com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection;
import java.util.List;
/* loaded from: classes8.dex */
final class LocaleSupportedSettingV2 extends WakeWordProviderSettings<List<String>> {
    private static final String TAG = "LocaleSupportedSettingV2";
    private final WakeWordSettingsServiceConnection mWakeWordSettingsServiceConnection;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocaleSupportedSettingV2(@NonNull ResultCallback<List<String>> resultCallback, @Nullable WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
        super(resultCallback);
        this.mWakeWordSettingsServiceConnection = wakeWordSettingsServiceConnection;
    }

    @VisibleForTesting
    List<String> getSupportedLocales() throws RemoteException {
        try {
            try {
                return this.mWakeWordSettingsServiceConnection.getSupportedLocales();
            } catch (Exception e) {
                Log.e(TAG, "Exception in getSupportedLocales ", e, new Object[0]);
                throw new RemoteException();
            }
        } finally {
            this.mWakeWordSettingsServiceConnection.endConnection();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype.WakeWordProviderSettings
    /* renamed from: apply  reason: avoid collision after fix types in other method */
    public List<String> mo1548apply() throws RemoteException {
        return getSupportedLocales();
    }
}
