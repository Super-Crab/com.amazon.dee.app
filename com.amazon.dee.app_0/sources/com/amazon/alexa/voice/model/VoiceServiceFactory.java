package com.amazon.alexa.voice.model;

import com.amazon.alexa.voice.dagger.VoiceDependencies;
import com.amazon.regulator.Component;
/* loaded from: classes11.dex */
public final class VoiceServiceFactory {
    private VoiceServiceFactory() {
    }

    public static VoiceService create(Component component) {
        VoiceDependencies.initialize(component);
        return VoiceDependencies.voiceService();
    }
}
