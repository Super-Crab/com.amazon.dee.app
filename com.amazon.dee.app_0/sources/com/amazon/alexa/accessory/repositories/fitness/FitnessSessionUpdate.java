package com.amazon.alexa.accessory.repositories.fitness;

import com.amazon.alexa.accessory.internal.util.Preconditions;
/* loaded from: classes6.dex */
public final class FitnessSessionUpdate {
    private final FitnessSession fitnessSession;
    private final OnUpdateProcessedListener onUpdateProcessedListener;
    private final Origin origin;
    private boolean updateProcessed;
    private final Object[] updateProcessedLock;

    /* loaded from: classes6.dex */
    public interface OnUpdateProcessedListener {
        void onFailure(Throwable th);

        void onSuccess();
    }

    /* loaded from: classes6.dex */
    public enum Origin {
        ACCESSORY,
        APPLICATION
    }

    public FitnessSessionUpdate(Origin origin, FitnessSession fitnessSession, OnUpdateProcessedListener onUpdateProcessedListener) {
        Preconditions.notNull(origin, "origin");
        Preconditions.notNull(fitnessSession, "fitnessSession");
        Preconditions.notNull(onUpdateProcessedListener, "onUpdateProcessedListener");
        this.origin = origin;
        this.fitnessSession = fitnessSession;
        this.onUpdateProcessedListener = onUpdateProcessedListener;
        this.updateProcessedLock = new Object[0];
    }

    public FitnessSession getFitnessSession() {
        return this.fitnessSession;
    }

    public Origin getOrigin() {
        return this.origin;
    }

    public boolean isMarked() {
        boolean z;
        synchronized (this.updateProcessedLock) {
            z = this.updateProcessed;
        }
        return z;
    }

    public void markFailed(Throwable th) {
        synchronized (this.updateProcessedLock) {
            if (!this.updateProcessed) {
                this.updateProcessed = true;
                this.onUpdateProcessedListener.onFailure(th);
            }
        }
    }

    public void markSuccessful() {
        synchronized (this.updateProcessedLock) {
            if (!this.updateProcessed) {
                this.updateProcessed = true;
                this.onUpdateProcessedListener.onSuccess();
            }
        }
    }
}
