package com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection;
/* loaded from: classes8.dex */
final class WakeWordRecognitionUpdateSettingV2 extends WakeWordProviderSettings<Void> {
    private static final String TAG = "WakeWordRecognitionUpdateSettingV2";
    private final boolean mEnabled;
    private final WakeWordSettingsServiceConnection mWakeWordSettingsServiceConnection;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WakeWordRecognitionUpdateSettingV2(boolean z, @NonNull ResultCallback<Void> resultCallback, @Nullable WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
        super(resultCallback);
        this.mEnabled = z;
        this.mWakeWordSettingsServiceConnection = wakeWordSettingsServiceConnection;
    }

    @VisibleForTesting
    void setWakeWordRecognitionEnabled() throws RemoteException {
        try {
            try {
                this.mWakeWordSettingsServiceConnection.setWakeWordRecognitionEnabled(this.mEnabled, getCallback());
            } catch (Exception e) {
                Log.e(TAG, "setWakeWordRecognitionEnabled Exception ", e, new Object[0]);
                throw new RemoteException();
            }
        } finally {
            this.mWakeWordSettingsServiceConnection.endConnection();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype.WakeWordProviderSettings
    /* renamed from: apply  reason: collision with other method in class */
    public Void mo1548apply() throws RemoteException {
        setWakeWordRecognitionEnabled();
        return null;
    }
}
