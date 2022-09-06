package com.amazon.alexa.voiceui.accessibility;

import com.amazon.alexa.voice.ui.speech.AttentionSystemContract;
import com.amazon.alexa.voice.ui.util.AlexaState;
import com.amazon.alexa.voice.ui.util.BaseProperty;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class AccessibilityHandler {
    private final AccessibilityDelegate accessibilityDelegate;
    private final AttentionSystemContract attentionSystemContract;
    private final BaseProperty.OnChangedListener onChangedListener = new PropertyChangedListener();

    /* loaded from: classes11.dex */
    private class PropertyChangedListener implements BaseProperty.OnChangedListener {
        private PropertyChangedListener() {
        }

        @Override // com.amazon.alexa.voice.ui.util.BaseProperty.OnChangedListener
        public void onPropertyChanged() {
            AccessibilityHandler.this.setCurrentAccessibility();
        }
    }

    @Inject
    public AccessibilityHandler(AttentionSystemContract attentionSystemContract, AccessibilityDelegate accessibilityDelegate) {
        this.attentionSystemContract = attentionSystemContract;
        this.accessibilityDelegate = accessibilityDelegate;
    }

    private boolean isAlexaActive() {
        AlexaState alexaState = this.attentionSystemContract.alexaState().get();
        return alexaState == AlexaState.LISTENING || alexaState == AlexaState.SPEAKING || alexaState == AlexaState.THINKING;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentAccessibility() {
        if (canEnableAccessibility()) {
            this.accessibilityDelegate.enableAccessibility();
        } else {
            this.accessibilityDelegate.disableAccessibility();
        }
    }

    public boolean canEnableAccessibility() {
        return !isAlexaActive();
    }

    public void startControllingAccessibility() {
        stopControllingAccessibility();
        this.attentionSystemContract.alexaState().addOnChangedListener(this.onChangedListener);
    }

    public void stopControllingAccessibility() {
        this.attentionSystemContract.alexaState().removeOnChangedListener(this.onChangedListener);
    }
}
