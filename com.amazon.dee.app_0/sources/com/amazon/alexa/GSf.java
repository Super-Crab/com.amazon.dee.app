package com.amazon.alexa;

import com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: UserSpeechArbitrationWakeWordPrecondition.java */
/* loaded from: classes.dex */
public class GSf extends InternalWakeWordPrecondition {
    public final AtomicBoolean zZm = new AtomicBoolean(true);

    @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition
    public boolean isWakeWordAllowed() {
        return this.zZm.get();
    }

    @Override // com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition
    public void teardown() {
    }

    public void zZm(boolean z) {
        if (this.zZm.getAndSet(z) != z) {
            notifyStateChanged();
        }
    }
}
