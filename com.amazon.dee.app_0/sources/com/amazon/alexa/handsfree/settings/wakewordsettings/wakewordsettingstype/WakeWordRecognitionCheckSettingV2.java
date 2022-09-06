package com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.protocols.wakewordsettings.connection.WakeWordSettingsServiceConnection;
/* loaded from: classes8.dex */
final class WakeWordRecognitionCheckSettingV2 extends WakeWordProviderSettings<Boolean> {
    private static final String TAG = "WakeWordRecognitionCheckSettingV2";
    private final WakeWordSettingsServiceConnection mWakeWordSettingsServiceConnection;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WakeWordRecognitionCheckSettingV2(@NonNull ResultCallback<Boolean> resultCallback, @Nullable WakeWordSettingsServiceConnection wakeWordSettingsServiceConnection) {
        super(resultCallback);
        this.mWakeWordSettingsServiceConnection = wakeWordSettingsServiceConnection;
    }

    @VisibleForTesting
    Boolean isWakeWordRecognitionEnabled() throws RemoteException {
        try {
            try {
                return Boolean.valueOf(this.mWakeWordSettingsServiceConnection.isWakeWordRecognitionEnabled());
            } catch (Exception e) {
                Log.e(TAG, "Exception invoking isWakeWordRecognitionEnabled ", e, new Object[0]);
                throw new RemoteException("RemoteException invoking isWakeWordRecognitionEnabled");
            }
        } finally {
            this.mWakeWordSettingsServiceConnection.endConnection();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.handsfree.settings.wakewordsettings.wakewordsettingstype.WakeWordProviderSettings
    /* renamed from: apply */
    public Boolean mo1548apply() throws RemoteException {
        return isWakeWordRecognitionEnabled();
    }
}
