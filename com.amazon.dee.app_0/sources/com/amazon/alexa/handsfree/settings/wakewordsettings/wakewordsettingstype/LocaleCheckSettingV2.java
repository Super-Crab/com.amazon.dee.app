package com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection;
/* loaded from: classes8.dex */
final class LocaleCheckSettingV2 extends WakeWordProviderSettings<String> {
    private static final String TAG = "LocaleCheckSettingV2";
    private final WakeWordSettingsServiceConnection mWakeWordSettingsServiceConnection;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocaleCheckSettingV2(@NonNull ResultCallback<String> resultCallback, @Nullable WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
        super(resultCallback);
        this.mWakeWordSettingsServiceConnection = wakeWordSettingsServiceConnection;
    }

    @VisibleForTesting
    String getLocale() throws RemoteException {
        try {
            try {
                String locale = this.mWakeWordSettingsServiceConnection.getLocale();
                Log.d(TAG, "Got currentLocale = " + locale);
                return locale;
            } catch (Exception e) {
                Log.e(TAG, "Exception in getLocale ", e, new Object[0]);
                throw new RemoteException();
            }
        } finally {
            this.mWakeWordSettingsServiceConnection.endConnection();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype.WakeWordProviderSettings
    /* renamed from: apply  reason: avoid collision after fix types in other method */
    public String mo1548apply() throws RemoteException {
        return getLocale();
    }
}
