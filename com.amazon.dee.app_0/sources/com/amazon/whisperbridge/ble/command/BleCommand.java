package com.amazon.whisperbridge.ble.command;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
/* loaded from: classes13.dex */
public abstract class BleCommand<T> implements Callable<T> {
    public static final int COMMAND_TIMED_OUT = -2;
    public static final int COMMAND_TIMEOUT_SECONDS = 10;
    public static final int FAILED_TO_INITIATE_COMMAND = -1;
    protected T mResult;
    protected final CountDownLatch mResultLatch = new CountDownLatch(1);

    public BleCommand(T t) {
        this.mResult = t;
    }

    public void update(T t) {
        this.mResult = t;
        this.mResultLatch.countDown();
    }
}
