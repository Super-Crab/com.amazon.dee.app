package com.amazon.alexa.voice.model;

import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver;
import com.amazon.alexa.voice.model.VoiceService;
/* loaded from: classes11.dex */
class AppLifecycleHandlerForVoiceService implements ApplicationLifecycleObserver {
    private final VoiceService voiceService;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AppLifecycleHandlerForVoiceService(VoiceService voiceService) {
        this.voiceService = voiceService;
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
    public void onStart() {
        this.voiceService.onForeground(VoiceService.RecordPermission.NOT_REQUIRED);
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
    public void onStop() {
        this.voiceService.onBackground();
    }
}
