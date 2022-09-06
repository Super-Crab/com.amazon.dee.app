package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import android.content.Context;
import com.amazon.whisperbridge.ble.BleManager;
import com.amazon.whisperjoin.common.sharedtypes.utility.EncodingHelpers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class BLETransportBuilder {
    private Context mContext = null;
    private BleManager mBleManager = null;
    private EncodingHelpers mEncodingHelpers = null;
    private long mOperationTimeout = -1;
    private TimeUnit mOperationTimeoutUnit = null;
    private long mOperationRetryCount = -1;
    private ExecutorService mCommandExecutor = null;
    private ExecutorService mRetryExecutor = null;

    public BLETransport createBLETransport() {
        Context context = this.mContext;
        if (context != null) {
            BleManager bleManager = this.mBleManager;
            if (bleManager != null) {
                EncodingHelpers encodingHelpers = this.mEncodingHelpers;
                if (encodingHelpers != null) {
                    ExecutorService executorService = this.mCommandExecutor;
                    if (executorService != null) {
                        ExecutorService executorService2 = this.mRetryExecutor;
                        if (executorService2 != null) {
                            long j = this.mOperationTimeout;
                            if (j > 0) {
                                TimeUnit timeUnit = this.mOperationTimeoutUnit;
                                if (timeUnit != null) {
                                    long j2 = this.mOperationRetryCount;
                                    if (j2 >= 0) {
                                        return new BLETransport(context, bleManager, encodingHelpers, j, timeUnit, j2, executorService, executorService2);
                                    }
                                    throw new IllegalArgumentException("Retry count can not be less than 0");
                                }
                                throw new IllegalArgumentException("OperationTimeoutUnits can not be null");
                            }
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("OperationTimeout must be greater than 0, was: ");
                            outline107.append(this.mOperationTimeout);
                            throw new IllegalArgumentException(outline107.toString());
                        }
                        throw new IllegalArgumentException("retryExecutor can not be null");
                    }
                    throw new IllegalArgumentException("commandExecutor can not be null");
                }
                throw new IllegalArgumentException("encoding helper can not be null");
            }
            throw new IllegalArgumentException("bleManager can not be null");
        }
        throw new IllegalArgumentException("context can not be null");
    }

    public BLETransportBuilder setBleManager(BleManager bleManager) {
        this.mBleManager = bleManager;
        return this;
    }

    public BLETransportBuilder setCommandExecutor(ExecutorService executorService) {
        this.mCommandExecutor = executorService;
        return this;
    }

    public BLETransportBuilder setContext(Context context) {
        this.mContext = context;
        return this;
    }

    public BLETransportBuilder setEncodingHelpers(EncodingHelpers encodingHelpers) {
        this.mEncodingHelpers = encodingHelpers;
        return this;
    }

    public BLETransportBuilder setOperationRetryCount(long j) {
        this.mOperationRetryCount = j;
        return this;
    }

    public BLETransportBuilder setOperationTimeout(long j) {
        this.mOperationTimeout = j;
        return this;
    }

    public BLETransportBuilder setOperationTimeoutUnit(TimeUnit timeUnit) {
        this.mOperationTimeoutUnit = timeUnit;
        return this;
    }

    public BLETransportBuilder setRetryExecutor(ExecutorService executorService) {
        this.mRetryExecutor = executorService;
        return this;
    }
}
