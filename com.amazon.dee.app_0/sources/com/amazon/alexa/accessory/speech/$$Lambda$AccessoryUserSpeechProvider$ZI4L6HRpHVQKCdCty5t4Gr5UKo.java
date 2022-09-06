package com.amazon.alexa.accessory.speech;

import com.amazon.alexa.accessory.repositories.device.DeviceFeatures;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryUserSpeechProvider$ZI4L6HRpHVQKCd-Cty5t4Gr5UKo  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryUserSpeechProvider$ZI4L6HRpHVQKCdCty5t4Gr5UKo implements Function {
    public static final /* synthetic */ $$Lambda$AccessoryUserSpeechProvider$ZI4L6HRpHVQKCdCty5t4Gr5UKo INSTANCE = new $$Lambda$AccessoryUserSpeechProvider$ZI4L6HRpHVQKCdCty5t4Gr5UKo();

    private /* synthetic */ $$Lambda$AccessoryUserSpeechProvider$ZI4L6HRpHVQKCdCty5t4Gr5UKo() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return AccessoryUserSpeechProvider.lambda$checkDeviceFeaturesAndRegisterAudioPlaybackStatusListener$2((DeviceFeatures) obj);
    }
}
