package com.amazon.alexa.handsfree.settings.client;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.settings.client.settings.AlexaAudioProviderSetting;
/* loaded from: classes8.dex */
public class AlexaAudioServiceConnectionFactory {
    private final AlexaAudioProviderServiceSecurityHelper mAlexaAudioProviderSecurityHelper;
    private Context mContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaAudioServiceConnectionFactory(@NonNull Context context) {
        this(context, new AlexaAudioProviderServiceSecurityHelper(context));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaAudioServiceConnection getAlexaAudioServiceConnection(@NonNull AlexaAudioProviderSetting alexaAudioProviderSetting) {
        return getAlexaAudioServiceConnection(this.mAlexaAudioProviderSecurityHelper, alexaAudioProviderSetting);
    }

    @VisibleForTesting
    AlexaAudioServiceConnectionFactory(@NonNull Context context, @NonNull AlexaAudioProviderServiceSecurityHelper alexaAudioProviderServiceSecurityHelper) {
        this.mContext = context;
        this.mAlexaAudioProviderSecurityHelper = alexaAudioProviderServiceSecurityHelper;
    }

    @VisibleForTesting
    AlexaAudioServiceConnection getAlexaAudioServiceConnection(@NonNull AlexaAudioProviderServiceSecurityHelper alexaAudioProviderServiceSecurityHelper, @NonNull AlexaAudioProviderSetting alexaAudioProviderSetting) {
        return new AlexaAudioServiceConnection(this.mContext, alexaAudioProviderServiceSecurityHelper, alexaAudioProviderSetting);
    }
}
