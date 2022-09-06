package com.amazon.alexa.voiceui.accessibility;

import android.view.View;
/* loaded from: classes11.dex */
public class DefaultAccessibilityDelegate implements AccessibilityDelegate {
    private final View view;

    public DefaultAccessibilityDelegate(View view) {
        this.view = view;
    }

    @Override // com.amazon.alexa.voiceui.accessibility.AccessibilityDelegate
    public void disableAccessibility() {
        this.view.post(new Runnable() { // from class: com.amazon.alexa.voiceui.accessibility.DefaultAccessibilityDelegate.2
            @Override // java.lang.Runnable
            public void run() {
                DefaultAccessibilityDelegate.this.view.setImportantForAccessibility(4);
            }
        });
    }

    @Override // com.amazon.alexa.voiceui.accessibility.AccessibilityDelegate
    public void enableAccessibility() {
        this.view.post(new Runnable() { // from class: com.amazon.alexa.voiceui.accessibility.DefaultAccessibilityDelegate.1
            @Override // java.lang.Runnable
            public void run() {
                DefaultAccessibilityDelegate.this.view.setImportantForAccessibility(2);
            }
        });
    }
}
