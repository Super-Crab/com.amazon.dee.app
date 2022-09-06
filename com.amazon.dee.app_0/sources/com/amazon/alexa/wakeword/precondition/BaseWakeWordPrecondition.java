package com.amazon.alexa.wakeword.precondition;

import com.amazon.alexa.wakeword.precondition.WakeWordPrecondition;
/* loaded from: classes11.dex */
public abstract class BaseWakeWordPrecondition implements WakeWordPrecondition {
    private WakeWordPrecondition.ChangeListener changeListener;

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyStateChanged() {
        WakeWordPrecondition.ChangeListener changeListener = this.changeListener;
        if (changeListener != null) {
            changeListener.onPreconditionStateChanged(isWakeWordAllowed());
        }
    }

    @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition
    public void subscribe(WakeWordPrecondition.ChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition
    public void unsubscribe(WakeWordPrecondition.ChangeListener changeListener) {
        if (this.changeListener.equals(changeListener)) {
            this.changeListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifyStateChanged(boolean z) {
        WakeWordPrecondition.ChangeListener changeListener = this.changeListener;
        if (changeListener != null) {
            changeListener.onPreconditionStateChanged(z);
        }
    }
}
