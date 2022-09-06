package com.amazon.alexa.wakeword;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.wakeword.precondition.WakeWordPrecondition;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes11.dex */
public class WakeWordArbitration {
    private static final String TAG = "WakeWordArbitration";
    private final Set<WakeWordPrecondition> preconditions = new HashSet();
    private final Set<ArbitrationListener> arbitrationListeners = new HashSet();
    private final PreconditionChangesListener preconditionChangesListener = new PreconditionChangesListener();
    private final Object lock = new Object();

    /* loaded from: classes11.dex */
    public interface ArbitrationListener {
        void startDetectingWakeWord();

        void stopDetectingWakeWord();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class PreconditionChangesListener implements WakeWordPrecondition.ChangeListener {
        private PreconditionChangesListener() {
        }

        @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition.ChangeListener
        public void onPreconditionStateChanged(boolean z) {
            Log.i(WakeWordArbitration.TAG, "precondition state changed");
            synchronized (WakeWordArbitration.this.lock) {
                WakeWordArbitration.this.updateWakeWordDetectionState();
            }
        }
    }

    private void clearPreconditions() {
        for (WakeWordPrecondition wakeWordPrecondition : this.preconditions) {
            wakeWordPrecondition.unsubscribe(this.preconditionChangesListener);
        }
        this.preconditions.clear();
    }

    private boolean isWakeWordDetectionAllowed() {
        if (this.preconditions.isEmpty()) {
            return false;
        }
        for (WakeWordPrecondition wakeWordPrecondition : this.preconditions) {
            if (!wakeWordPrecondition.isWakeWordAllowed()) {
                String str = TAG;
                Log.i(str, "precondition: " + wakeWordPrecondition + " is not met");
                return false;
            }
        }
        return true;
    }

    public void addArbitrationListener(ArbitrationListener arbitrationListener) {
        Preconditions.notNull(arbitrationListener, "arbitration listener is null");
        synchronized (this.lock) {
            this.arbitrationListeners.add(arbitrationListener);
        }
    }

    public void addPrecondition(WakeWordPrecondition wakeWordPrecondition, boolean z) {
        Preconditions.notNull(wakeWordPrecondition, "precondition is null");
        String str = TAG;
        Log.i(str, "add new wake word precondition: " + wakeWordPrecondition);
        synchronized (this.lock) {
            this.preconditions.add(wakeWordPrecondition);
            wakeWordPrecondition.subscribe(this.preconditionChangesListener);
            if (z) {
                updateWakeWordDetectionState();
            }
        }
    }

    public void addPreconditions(WakeWordPrecondition... wakeWordPreconditionArr) {
        synchronized (this.lock) {
            for (WakeWordPrecondition wakeWordPrecondition : wakeWordPreconditionArr) {
                addPrecondition(wakeWordPrecondition, false);
            }
            updateWakeWordDetectionState();
        }
    }

    public void removeArbitrationListener(ArbitrationListener arbitrationListener) {
        synchronized (this.lock) {
            this.arbitrationListeners.remove(arbitrationListener);
        }
    }

    public void removePreconditions(WakeWordPrecondition... wakeWordPreconditionArr) {
        synchronized (this.lock) {
            for (WakeWordPrecondition wakeWordPrecondition : wakeWordPreconditionArr) {
                if (this.preconditions.contains(wakeWordPrecondition)) {
                    Log.i(TAG, "remove wake word precondition: " + wakeWordPrecondition);
                    this.preconditions.remove(wakeWordPrecondition);
                    wakeWordPrecondition.unsubscribe(this.preconditionChangesListener);
                }
            }
            updateWakeWordDetectionState();
        }
    }

    public void teardown() {
        synchronized (this.lock) {
            clearPreconditions();
            this.arbitrationListeners.clear();
        }
    }

    @VisibleForTesting
    void updateWakeWordDetectionState() {
        Log.i(TAG, "updateWakeWordDetectionState");
        boolean isWakeWordDetectionAllowed = isWakeWordDetectionAllowed();
        GeneratedOutlineSupport1.outline173("shouldBeDetecting: ", isWakeWordDetectionAllowed, TAG);
        for (ArbitrationListener arbitrationListener : this.arbitrationListeners) {
            if (isWakeWordDetectionAllowed) {
                arbitrationListener.startDetectingWakeWord();
            } else {
                arbitrationListener.stopDetectingWakeWord();
            }
        }
    }
}
