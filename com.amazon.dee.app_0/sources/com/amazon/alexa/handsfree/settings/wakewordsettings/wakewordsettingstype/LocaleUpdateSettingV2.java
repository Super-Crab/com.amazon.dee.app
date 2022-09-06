package com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection;
/* loaded from: classes8.dex */
final class LocaleUpdateSettingV2 extends WakeWordProviderSettings<Void> {
    private static final String TAG = "LocaleUpdateSettingV2";
    private final String mLocale;
    private final WakeWordSettingsServiceConnection mWakeWordSettingsServiceConnection;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocaleUpdateSettingV2(@NonNull String str, @NonNull ResultCallback<Void> resultCallback, @Nullable WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
        super(resultCallback);
        this.mLocale = str;
        this.mWakeWordSettingsServiceConnection = wakeWordSettingsServiceConnection;
    }

    @VisibleForTesting
    void setLocale() throws RemoteException {
        try {
            try {
                this.mWakeWordSettingsServiceConnection.setLocale(this.mLocale, getCallback());
            } catch (Exception e) {
                Log.i(TAG, "Exception in setLocale ", e);
                throw new RemoteException();
            }
        } finally {
            this.mWakeWordSettingsServiceConnection.endConnection();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype.WakeWordProviderSettings
    /* renamed from: apply  reason: avoid collision after fix types in other method */
    public Void mo1548apply() throws RemoteException {
        setLocale();
        return null;
    }
}
