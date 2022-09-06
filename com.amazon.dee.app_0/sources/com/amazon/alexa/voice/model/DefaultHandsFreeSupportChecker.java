package com.amazon.alexa.voice.model;

import android.content.Context;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.voice.wakeword.AbiCompatibilityInterface;
/* loaded from: classes11.dex */
public class DefaultHandsFreeSupportChecker implements HandsFreeSupportChecker {
    private final AbiCompatibilityInterface abiCompatibilityInterface;
    private final boolean isAmpdHandsFreeDevice;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultHandsFreeSupportChecker(Context context, AbiCompatibilityInterface abiCompatibilityInterface) {
        this.isAmpdHandsFreeDevice = AMPDInformationProvider.getInstance(context).isAMPDDevice();
        this.abiCompatibilityInterface = abiCompatibilityInterface;
    }

    @Override // com.amazon.alexa.voice.model.HandsFreeSupportChecker
    public boolean isHandsfreeSupported() {
        return !this.isAmpdHandsFreeDevice && this.abiCompatibilityInterface.isCompatible();
    }
}
