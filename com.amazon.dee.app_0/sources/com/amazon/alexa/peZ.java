package com.amazon.alexa;

import com.amazon.alexa.api.AlexaPreferences;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.feature.consumer.api.FeatureFlagListener;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: AlexaDevicePreferences.java */
@Singleton
/* loaded from: classes.dex */
public class peZ {
    public static final String zZm = "peZ";
    public final Lazy<PersistentStorage> BIo;
    public boolean jiA;
    public final Lazy<gSO> zQM;
    public final AMPDInformationProvider zyO;

    @Inject
    public peZ(@Named("AlexaDevicePreferences") Lazy<PersistentStorage> lazy, Lazy<gSO> lazy2, AMPDInformationProvider aMPDInformationProvider) {
        this.BIo = lazy;
        this.zQM = lazy2;
        this.zyO = aMPDInformationProvider;
        this.zQM.mo358get().zZm(Feature.ALEXA_HANDS_FREE_FEATURE_GATING_BLOCK_SENSITIVE_REQUEST, new FeatureFlagListener() { // from class: com.amazon.alexa.-$$Lambda$peZ$iX1kRRaFmbTChi72xQqKgvzmNWE
            @Override // com.amazon.alexa.feature.consumer.api.FeatureFlagListener
            public final void onFeatureServiceReady(boolean z) {
                peZ.this.zZm(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void zZm(boolean z) {
        this.jiA = z;
    }

    public synchronized void zZm(AlexaPreferences alexaPreferences) {
        this.BIo.mo358get().edit().set("displayOverLockscreenWithVerifiedVoice", alexaPreferences.preferDisplayOverLockscreenWithVerifiedVoice()).set("displayOverLockscreenWithVerifiedVoiceValue", alexaPreferences.preferDisplayOverLockscreenWithVerifiedVoiceValue()).commitSynchronously();
    }

    public synchronized AlexaPreferences zZm() {
        AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue showOnLockscreenWithVerifiedVoiceValue;
        AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue[] values;
        PersistentStorage mo358get = this.BIo.mo358get();
        String string = mo358get.getString("displayOverLockscreenWithVerifiedVoiceValue");
        boolean z = mo358get.getBoolean("displayOverLockscreenWithVerifiedVoice", false);
        showOnLockscreenWithVerifiedVoiceValue = AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_PERSONAL;
        if (string == null) {
            if (!(this.jiA && this.zyO.isBSR())) {
                string = z ? AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_NONE.name() : AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue.BLOCK_ALL.name();
            }
        }
        for (AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue showOnLockscreenWithVerifiedVoiceValue2 : AlexaPreferences.ShowOnLockscreenWithVerifiedVoiceValue.values()) {
            if (showOnLockscreenWithVerifiedVoiceValue2.toString().equals(string)) {
                showOnLockscreenWithVerifiedVoiceValue = showOnLockscreenWithVerifiedVoiceValue2;
            }
        }
        Log.i(zZm, "getPreferences setting " + showOnLockscreenWithVerifiedVoiceValue);
        return AlexaPreferences.builder().setPreferDisplayOverLockscreenWithVerifiedVoice(showOnLockscreenWithVerifiedVoiceValue).build();
    }
}
