package com.amazon.whisperbridge.ble.command;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public class BleCommandExecutor {
    private final ExecutorService mCommandExecutor = Executors.newSingleThreadExecutor();

    public <T> Future<T> executeCommand(BleCommand<T> bleCommand) {
        return this.mCommandExecutor.submit(bleCommand);
    }
}
