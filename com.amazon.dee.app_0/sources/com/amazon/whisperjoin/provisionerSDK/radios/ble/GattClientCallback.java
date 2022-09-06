package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import com.amazon.whisperbridge.Transport;
import com.amazon.whisperbridge.ble.BleGattClient;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.provisionerSDK.radios.error.GattConnectionError;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class GattClientCallback implements BleGattClient.Callback {
    private static String TAG = "com.amazon.whisperjoin.provisionerSDK.radios.ble.GattClientCallback";
    private final FutureWithSettableError mCallbackPromise;
    private final Object mCallbackSynchronizer;
    private final Transport.DeviceConnectionStateChangedListener mConnectionStateChangedListener;
    private final Executor mExecutor;
    private boolean mHasConnected;
    private final Callable<Void> mOnConnected;

    /* loaded from: classes13.dex */
    private static class FutureWithSettableError extends FutureTask<Void> {
        private Exception mFailureCause;

        public FutureWithSettableError(Callable callable) {
            super(callable);
            this.mFailureCause = null;
        }

        public void setFailureCause(Exception exc) {
            this.mFailureCause = exc;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.util.concurrent.FutureTask
        public void set(Void r2) {
            Exception exc = this.mFailureCause;
            if (exc != null) {
                setException(exc);
            } else {
                super.set((FutureWithSettableError) r2);
            }
        }
    }

    public GattClientCallback(Callable<Void> callable, Object obj, Transport.DeviceConnectionStateChangedListener deviceConnectionStateChangedListener) {
        this(callable, obj, deviceConnectionStateChangedListener, null);
    }

    public FutureTask<Void> getFuture() {
        return this.mCallbackPromise;
    }

    @Override // com.amazon.whisperbridge.ble.BleGattClient.Callback
    public void onConnectionStateChanged(BleGattClient bleGattClient, final int i, final int i2) {
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.whisperjoin.provisionerSDK.radios.ble.GattClientCallback.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (GattClientCallback.this.mCallbackSynchronizer) {
                    WJLog.d(GattClientCallback.TAG, String.format(Locale.ENGLISH, "onConnectionStateChanged status: %d, newState: %d", Integer.valueOf(i), Integer.valueOf(i2)));
                    try {
                        if (i2 == 2) {
                            GattClientCallback.this.mHasConnected = true;
                            GattClientCallback.this.mOnConnected.call();
                            GattClientCallback.this.mCallbackPromise.run();
                            GattClientCallback.this.mConnectionStateChangedListener.onConnect();
                            WJLog.v(GattClientCallback.TAG, "client connected");
                        } else if (i2 == 0) {
                            if (i == 0) {
                                WJLog.d(GattClientCallback.TAG, "Successfully Disconnection");
                                GattClientCallback.this.mConnectionStateChangedListener.onDisconnect();
                            } else if (GattClientCallback.this.mHasConnected) {
                                WJLog.e(GattClientCallback.TAG, "Unexpected Disconnection");
                                GattClientCallback.this.mConnectionStateChangedListener.onConnectionFailure(i);
                            } else {
                                WJLog.d(GattClientCallback.TAG, "Triggering failure cause to be run");
                                GattClientCallback.this.mCallbackPromise.setFailureCause(new GattConnectionError(i));
                                GattClientCallback.this.mCallbackPromise.run();
                                WJLog.w(GattClientCallback.TAG, "Haven't connected yet, wait until retry attempts");
                            }
                            WJLog.e(GattClientCallback.TAG, "client disconnected");
                        }
                    } catch (Exception e) {
                        WJLog.e(GattClientCallback.TAG, "error while trying to change connection state", e);
                        GattClientCallback.this.mCallbackPromise.setFailureCause(e);
                        GattClientCallback.this.mCallbackPromise.run();
                    }
                }
            }
        });
    }

    GattClientCallback(Callable<Void> callable, Object obj, Transport.DeviceConnectionStateChangedListener deviceConnectionStateChangedListener, Executor executor) {
        this.mHasConnected = false;
        if (callable != null) {
            if (obj == null) {
                throw new IllegalArgumentException("callbackSynchronizer can not be null");
            }
            if (deviceConnectionStateChangedListener != null) {
                executor = executor == null ? Executors.newSingleThreadExecutor() : executor;
                this.mOnConnected = callable;
                this.mCallbackSynchronizer = obj;
                this.mConnectionStateChangedListener = deviceConnectionStateChangedListener;
                this.mExecutor = executor;
                this.mCallbackPromise = new FutureWithSettableError(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.radios.ble.GattClientCallback.1
                    @Override // java.util.concurrent.Callable
                    public Void call() {
                        WJLog.v(GattClientCallback.TAG, "connection promise fulfilled");
                        return null;
                    }
                });
                return;
            }
            throw new IllegalArgumentException("deviceEventCallback can not be null");
        }
        throw new IllegalArgumentException("onConnected can not be null");
    }
}
