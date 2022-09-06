package com.facebook.react.modules.core;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.core.ChoreographerCompat;
import java.util.ArrayDeque;
/* loaded from: classes2.dex */
public class ReactChoreographer {
    private static ReactChoreographer sInstance;
    @Nullable
    private volatile ChoreographerCompat mChoreographer;
    private final Object mCallbackQueuesLock = new Object();
    private int mTotalCallbacks = 0;
    private boolean mHasPostedCallback = false;
    private final ReactChoreographerDispatcher mReactChoreographerDispatcher = new ReactChoreographerDispatcher();
    @GuardedBy("mCallbackQueuesLock")
    private final ArrayDeque<ChoreographerCompat.FrameCallback>[] mCallbackQueues = new ArrayDeque[CallbackType.values().length];

    /* loaded from: classes2.dex */
    public enum CallbackType {
        PERF_MARKERS(0),
        DISPATCH_UI(1),
        NATIVE_ANIMATED_MODULE(2),
        TIMERS_EVENTS(3),
        IDLE_EVENT(4);
        
        private final int mOrder;

        CallbackType(int i) {
            this.mOrder = i;
        }

        int getOrder() {
            return this.mOrder;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ReactChoreographerDispatcher extends ChoreographerCompat.FrameCallback {
        private ReactChoreographerDispatcher() {
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j) {
            synchronized (ReactChoreographer.this.mCallbackQueuesLock) {
                ReactChoreographer.this.mHasPostedCallback = false;
                for (int i = 0; i < ReactChoreographer.this.mCallbackQueues.length; i++) {
                    ArrayDeque arrayDeque = ReactChoreographer.this.mCallbackQueues[i];
                    int size = arrayDeque.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ChoreographerCompat.FrameCallback frameCallback = (ChoreographerCompat.FrameCallback) arrayDeque.pollFirst();
                        if (frameCallback != null) {
                            frameCallback.doFrame(j);
                            ReactChoreographer.access$610(ReactChoreographer.this);
                        } else {
                            FLog.e(ReactConstants.TAG, "Tried to execute non-existent frame callback");
                        }
                    }
                }
                ReactChoreographer.this.maybeRemoveFrameCallback();
            }
        }
    }

    private ReactChoreographer() {
        int i = 0;
        while (true) {
            ArrayDeque<ChoreographerCompat.FrameCallback>[] arrayDequeArr = this.mCallbackQueues;
            if (i < arrayDequeArr.length) {
                arrayDequeArr[i] = new ArrayDeque<>();
                i++;
            } else {
                initializeChoreographer(null);
                return;
            }
        }
    }

    static /* synthetic */ int access$610(ReactChoreographer reactChoreographer) {
        int i = reactChoreographer.mTotalCallbacks;
        reactChoreographer.mTotalCallbacks = i - 1;
        return i;
    }

    public static ReactChoreographer getInstance() {
        Assertions.assertNotNull(sInstance, "ReactChoreographer needs to be initialized.");
        return sInstance;
    }

    public static void initialize() {
        if (sInstance == null) {
            sInstance = new ReactChoreographer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeRemoveFrameCallback() {
        Assertions.assertCondition(this.mTotalCallbacks >= 0);
        if (this.mTotalCallbacks != 0 || !this.mHasPostedCallback) {
            return;
        }
        if (this.mChoreographer != null) {
            this.mChoreographer.removeFrameCallback(this.mReactChoreographerDispatcher);
        }
        this.mHasPostedCallback = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postFrameCallbackOnChoreographer() {
        this.mChoreographer.postFrameCallback(this.mReactChoreographerDispatcher);
        this.mHasPostedCallback = true;
    }

    public void initializeChoreographer(@Nullable final Runnable runnable) {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.core.ReactChoreographer.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (ReactChoreographer.class) {
                    if (ReactChoreographer.this.mChoreographer == null) {
                        ReactChoreographer.this.mChoreographer = ChoreographerCompat.getInstance();
                    }
                }
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
    }

    public void postFrameCallback(CallbackType callbackType, ChoreographerCompat.FrameCallback frameCallback) {
        synchronized (this.mCallbackQueuesLock) {
            this.mCallbackQueues[callbackType.getOrder()].addLast(frameCallback);
            boolean z = true;
            this.mTotalCallbacks++;
            if (this.mTotalCallbacks <= 0) {
                z = false;
            }
            Assertions.assertCondition(z);
            if (!this.mHasPostedCallback) {
                if (this.mChoreographer == null) {
                    initializeChoreographer(new Runnable() { // from class: com.facebook.react.modules.core.ReactChoreographer.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ReactChoreographer.this.postFrameCallbackOnChoreographer();
                        }
                    });
                } else {
                    postFrameCallbackOnChoreographer();
                }
            }
        }
    }

    public void removeFrameCallback(CallbackType callbackType, ChoreographerCompat.FrameCallback frameCallback) {
        synchronized (this.mCallbackQueuesLock) {
            if (this.mCallbackQueues[callbackType.getOrder()].removeFirstOccurrence(frameCallback)) {
                this.mTotalCallbacks--;
                maybeRemoveFrameCallback();
            } else {
                FLog.e(ReactConstants.TAG, "Tried to remove non-existent frame callback");
            }
        }
    }
}
