package com.facebook.react.bridge.queue;

import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.util.Pair;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.queue.MessageQueueThreadSpec;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.futures.SimpleSettableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
/* loaded from: classes2.dex */
public class MessageQueueThreadImpl implements MessageQueueThread {
    private final String mAssertionErrorMessage;
    private final MessageQueueThreadHandler mHandler;
    private volatile boolean mIsFinished;
    private final Looper mLooper;
    private final String mName;
    private MessageQueueThreadPerfStats mPerfStats;

    /* renamed from: com.facebook.react.bridge.queue.MessageQueueThreadImpl$5  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$queue$MessageQueueThreadSpec$ThreadType = new int[MessageQueueThreadSpec.ThreadType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$bridge$queue$MessageQueueThreadSpec$ThreadType[MessageQueueThreadSpec.ThreadType.MAIN_UI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$queue$MessageQueueThreadSpec$ThreadType[MessageQueueThreadSpec.ThreadType.NEW_BACKGROUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private MessageQueueThreadImpl(String str, Looper looper, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        this(str, looper, queueThreadExceptionHandler, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assignToPerfStats(MessageQueueThreadPerfStats messageQueueThreadPerfStats, long j, long j2) {
        messageQueueThreadPerfStats.wallTime = j;
        messageQueueThreadPerfStats.cpuTime = j2;
    }

    public static MessageQueueThreadImpl create(MessageQueueThreadSpec messageQueueThreadSpec, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        int ordinal = messageQueueThreadSpec.getThreadType().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return startNewBackgroundThread(messageQueueThreadSpec.getName(), messageQueueThreadSpec.getStackSize(), queueThreadExceptionHandler);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown thread type: ");
            outline107.append(messageQueueThreadSpec.getThreadType());
            throw new RuntimeException(outline107.toString());
        }
        return createForMainThread(messageQueueThreadSpec.getName(), queueThreadExceptionHandler);
    }

    private static MessageQueueThreadImpl createForMainThread(String str, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        MessageQueueThreadImpl messageQueueThreadImpl = new MessageQueueThreadImpl(str, Looper.getMainLooper(), queueThreadExceptionHandler);
        if (UiThreadUtil.isOnUiThread()) {
            Process.setThreadPriority(-4);
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.bridge.queue.MessageQueueThreadImpl.3
                @Override // java.lang.Runnable
                public void run() {
                    Process.setThreadPriority(-4);
                }
            });
        }
        return messageQueueThreadImpl;
    }

    private static MessageQueueThreadImpl startNewBackgroundThread(String str, long j, QueueThreadExceptionHandler queueThreadExceptionHandler) {
        final SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
        new Thread(null, new Runnable() { // from class: com.facebook.react.bridge.queue.MessageQueueThreadImpl.4
            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(-4);
                Looper.prepare();
                MessageQueueThreadPerfStats messageQueueThreadPerfStats = new MessageQueueThreadPerfStats();
                MessageQueueThreadImpl.assignToPerfStats(messageQueueThreadPerfStats, SystemClock.uptimeMillis(), SystemClock.currentThreadTimeMillis());
                SimpleSettableFuture.this.set(new Pair(Looper.myLooper(), messageQueueThreadPerfStats));
                Looper.loop();
            }
        }, GeneratedOutlineSupport1.outline72("mqt_", str), j).start();
        Pair pair = (Pair) simpleSettableFuture.getOrThrow();
        return new MessageQueueThreadImpl(str, (Looper) pair.first, queueThreadExceptionHandler, (MessageQueueThreadPerfStats) pair.second);
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    public void assertIsOnThread() {
        SoftAssertions.assertCondition(isOnThread(), this.mAssertionErrorMessage);
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    public <T> Future<T> callOnQueue(final Callable<T> callable) {
        final SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
        runOnQueue(new Runnable() { // from class: com.facebook.react.bridge.queue.MessageQueueThreadImpl.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    simpleSettableFuture.set(callable.call());
                } catch (Exception e) {
                    simpleSettableFuture.setException(e);
                }
            }
        });
        return simpleSettableFuture;
    }

    public Looper getLooper() {
        return this.mLooper;
    }

    public String getName() {
        return this.mName;
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    public MessageQueueThreadPerfStats getPerfStats() {
        return this.mPerfStats;
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    public boolean isOnThread() {
        return this.mLooper.getThread() == Thread.currentThread();
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    public void quitSynchronous() {
        this.mIsFinished = true;
        this.mLooper.quit();
        if (this.mLooper.getThread() != Thread.currentThread()) {
            try {
                this.mLooper.getThread().join();
            } catch (InterruptedException unused) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Got interrupted waiting to join thread ");
                outline107.append(this.mName);
                throw new RuntimeException(outline107.toString());
            }
        }
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    public void resetPerfStats() {
        assignToPerfStats(this.mPerfStats, -1L, -1L);
        runOnQueue(new Runnable() { // from class: com.facebook.react.bridge.queue.MessageQueueThreadImpl.2
            @Override // java.lang.Runnable
            public void run() {
                MessageQueueThreadImpl.assignToPerfStats(MessageQueueThreadImpl.this.mPerfStats, SystemClock.uptimeMillis(), SystemClock.currentThreadTimeMillis());
            }
        });
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    public void runOnQueue(Runnable runnable) {
        if (this.mIsFinished) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Tried to enqueue runnable on already finished thread: '");
            outline107.append(getName());
            outline107.append("... dropping Runnable.");
            FLog.w(ReactConstants.TAG, outline107.toString());
        }
        this.mHandler.post(runnable);
    }

    private MessageQueueThreadImpl(String str, Looper looper, QueueThreadExceptionHandler queueThreadExceptionHandler, MessageQueueThreadPerfStats messageQueueThreadPerfStats) {
        this.mIsFinished = false;
        this.mName = str;
        this.mLooper = looper;
        this.mHandler = new MessageQueueThreadHandler(looper, queueThreadExceptionHandler);
        this.mPerfStats = messageQueueThreadPerfStats;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected to be called from the '");
        outline107.append(getName());
        outline107.append("' thread!");
        this.mAssertionErrorMessage = outline107.toString();
    }

    @Override // com.facebook.react.bridge.queue.MessageQueueThread
    public void assertIsOnThread(String str) {
        boolean isOnThread = isOnThread();
        SoftAssertions.assertCondition(isOnThread, this.mAssertionErrorMessage + " " + str);
    }
}
