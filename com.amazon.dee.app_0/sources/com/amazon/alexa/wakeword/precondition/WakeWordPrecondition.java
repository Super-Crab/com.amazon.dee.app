package com.amazon.alexa.wakeword.precondition;
/* loaded from: classes11.dex */
public interface WakeWordPrecondition {

    /* loaded from: classes11.dex */
    public interface ChangeListener {
        void onPreconditionStateChanged(boolean z);
    }

    boolean isWakeWordAllowed();

    void subscribe(ChangeListener changeListener);

    void unsubscribe(ChangeListener changeListener);
}
