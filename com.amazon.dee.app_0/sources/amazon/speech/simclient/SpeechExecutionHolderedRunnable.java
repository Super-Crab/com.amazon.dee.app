package amazon.speech.simclient;
/* loaded from: classes.dex */
public abstract class SpeechExecutionHolderedRunnable implements Runnable {
    private SpeechExecutionHolder mSpeechExecutionHolder;
    SpeechExecutionHolderedRunnableState mState = SpeechExecutionHolderedRunnableState.UNSET;
    private final Object mSpeechExecutionHolderLock = new Object();

    public SpeechExecutionHolder getSpeechExecutionHolder() {
        SpeechExecutionHolder speechExecutionHolder;
        synchronized (this.mSpeechExecutionHolderLock) {
            if (this.mState == SpeechExecutionHolderedRunnableState.SET) {
                this.mState = SpeechExecutionHolderedRunnableState.QUERIED;
                speechExecutionHolder = this.mSpeechExecutionHolder;
            } else {
                throw new IllegalStateException("Called getSpeechExecution holder from state " + this.mState + ". State must be SpeechExecutionHolderedRunnableState.SET");
            }
        }
        return speechExecutionHolder;
    }

    public void setSpeechExecutionHolder(SpeechExecutionHolder speechExecutionHolder) {
        if (speechExecutionHolder != null) {
            synchronized (this.mSpeechExecutionHolderLock) {
                if (this.mState == SpeechExecutionHolderedRunnableState.UNSET) {
                    this.mSpeechExecutionHolder = speechExecutionHolder;
                    this.mState = SpeechExecutionHolderedRunnableState.SET;
                } else {
                    throw new IllegalStateException("Called setSpeechExecution holder from state " + this.mState + ". State must be SpeechExecutionHolderedRunnableState.UNSET");
                }
            }
            return;
        }
        throw new IllegalArgumentException("Holder cannot be null.");
    }
}
