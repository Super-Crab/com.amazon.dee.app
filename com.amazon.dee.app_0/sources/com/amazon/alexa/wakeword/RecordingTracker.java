package com.amazon.alexa.wakeword;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition;
/* loaded from: classes11.dex */
public class RecordingTracker {
    private final Precondition wakeWordPrecondition = new Precondition();
    @VisibleForTesting
    protected volatile State recordingState = State.IDLE;

    /* loaded from: classes11.dex */
    public class Precondition extends InternalWakeWordPrecondition {
        public Precondition() {
        }

        @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition
        public boolean isWakeWordAllowed() {
            return State.IDLE.equals(RecordingTracker.this.recordingState);
        }

        @Override // com.amazon.alexa.wakeword.precondition.BaseWakeWordPrecondition
        public void notifyStateChanged() {
            super.notifyStateChanged();
        }

        @Override // com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition
        public void teardown() {
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    enum State {
        IDLE,
        CREATED,
        ACTIVE
    }

    public Precondition getWakeWordPrecondition() {
        return this.wakeWordPrecondition;
    }

    public void onCapturerCreated() {
        this.recordingState = State.CREATED;
        this.wakeWordPrecondition.notifyStateChanged();
    }

    public void onCapturingFinished() {
        this.recordingState = State.IDLE;
        this.wakeWordPrecondition.notifyStateChanged();
    }

    public void onCapturingStarted() {
        this.recordingState = State.ACTIVE;
        this.wakeWordPrecondition.notifyStateChanged();
    }
}
