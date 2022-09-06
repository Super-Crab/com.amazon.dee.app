package com.amazon.alexa.accessory.speech;

import com.amazon.alexa.accessory.repositories.device.DeviceFeature;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryUserSpeechProvider$m3FnnNXvPETGOxutzmZrIbErops  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoryUserSpeechProvider$m3FnnNXvPETGOxutzmZrIbErops implements Predicate {
    public static final /* synthetic */ $$Lambda$AccessoryUserSpeechProvider$m3FnnNXvPETGOxutzmZrIbErops INSTANCE = new $$Lambda$AccessoryUserSpeechProvider$m3FnnNXvPETGOxutzmZrIbErops();

    private /* synthetic */ $$Lambda$AccessoryUserSpeechProvider$m3FnnNXvPETGOxutzmZrIbErops() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return AccessoryUserSpeechProvider.lambda$checkDeviceFeaturesAndRegisterAudioPlaybackStatusListener$3((DeviceFeature) obj);
    }
}
