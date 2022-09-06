package com.amazon.alexa.voice.ui;

import com.amazon.alexa.voice.model.VoiceService;
/* loaded from: classes11.dex */
public class VoiceActivityLifecycleHandlerForPushButton implements VoiceActivityLifecycleHandler {
    private final VoiceService voiceService;

    public VoiceActivityLifecycleHandlerForPushButton(VoiceService voiceService) {
        this.voiceService = voiceService;
    }

    @Override // com.amazon.alexa.voice.ui.VoiceActivityLifecycleHandler
    public void afterSuperOnDestroy() {
        this.voiceService.doNotBackground(false);
        this.voiceService.onBackground();
    }

    @Override // com.amazon.alexa.voice.ui.VoiceActivityLifecycleHandler
    public void beforeSuperOnStop() {
        this.voiceService.doNotBackground(true);
    }
}
