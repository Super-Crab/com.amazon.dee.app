package com.amazon.alexa.voice.ui.player.accessibility;

import android.view.View;
/* loaded from: classes11.dex */
public class DefaultAccessibilityDelegate implements AccessibilityDelegate {
    private final View view;

    public DefaultAccessibilityDelegate(View view) {
        this.view = view;
    }

    @Override // com.amazon.alexa.voice.ui.player.accessibility.AccessibilityDelegate
    public void disableAccessibility() {
        this.view.setImportantForAccessibility(4);
    }

    @Override // com.amazon.alexa.voice.ui.player.accessibility.AccessibilityDelegate
    public void enableAccessibility() {
        this.view.setImportantForAccessibility(2);
    }
}
