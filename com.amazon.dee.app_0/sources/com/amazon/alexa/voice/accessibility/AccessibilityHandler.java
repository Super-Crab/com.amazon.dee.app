package com.amazon.alexa.voice.accessibility;

import com.amazon.alexa.voice.ui.speech.SpeechController;
import com.amazon.alexa.voice.ui.util.BaseProperty;
/* loaded from: classes11.dex */
public final class AccessibilityHandler {
    private final AccessibilityDelegate accessibilityDelegate;
    private BaseProperty.OnChangedListener onChangedListener = new BaseProperty.OnChangedListener() { // from class: com.amazon.alexa.voice.accessibility.-$$Lambda$AccessibilityHandler$WJp91Vj2xuZW8NmO252TIPbPZi0
        @Override // com.amazon.alexa.voice.ui.util.BaseProperty.OnChangedListener
        public final void onPropertyChanged() {
            AccessibilityHandler.this.setCurrentAccessibility();
        }
    };
    private final SpeechController speechController;

    public AccessibilityHandler(SpeechController speechController, AccessibilityDelegate accessibilityDelegate) {
        this.speechController = speechController;
        this.accessibilityDelegate = accessibilityDelegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentAccessibility() {
        if (!this.speechController.isListening().get() && !this.speechController.isSpeaking().get() && !this.speechController.isProcessing().get()) {
            this.accessibilityDelegate.enableAccessibility();
        } else {
            this.accessibilityDelegate.disableAccessibility();
        }
    }

    public boolean canEnableAccessibility() {
        return !this.speechController.isListening().get() && !this.speechController.isProcessing().get() && !this.speechController.isSpeaking().get();
    }

    public void startControllingAccessibility() {
        stopControllingAccessibility();
        this.speechController.isListening().addOnChangedListener(this.onChangedListener);
        this.speechController.isProcessing().addOnChangedListener(this.onChangedListener);
        this.speechController.isSpeaking().addOnChangedListener(this.onChangedListener);
    }

    public void stopControllingAccessibility() {
        this.speechController.isSpeaking().removeOnChangedListener(this.onChangedListener);
        this.speechController.isProcessing().removeOnChangedListener(this.onChangedListener);
        this.speechController.isListening().removeOnChangedListener(this.onChangedListener);
    }
}
