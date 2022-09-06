package com.amazon.alexa.voice.ui.metrics;
/* loaded from: classes11.dex */
public class CardInteractionTracker {
    boolean interacted;

    public void notifyInteracted() {
        this.interacted = true;
    }

    public boolean wasInteracted() {
        return this.interacted;
    }
}
