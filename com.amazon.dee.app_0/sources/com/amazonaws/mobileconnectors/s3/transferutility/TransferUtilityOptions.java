package com.amazonaws.mobileconnectors.s3.transferutility;

import java.io.Serializable;
/* loaded from: classes13.dex */
public class TransferUtilityOptions implements Serializable {
    private static final int MILLIS_IN_MINUTE = 60000;
    private static final long serialVersionUID = 1;
    private TransferNetworkConnectionType transferNetworkConnectionType;
    @Deprecated
    private long transferServiceCheckTimeInterval;
    private int transferThreadPoolSize;

    public TransferUtilityOptions() {
        this.transferServiceCheckTimeInterval = getDefaultCheckTimeInterval();
        this.transferThreadPoolSize = getDefaultThreadPoolSize();
        this.transferNetworkConnectionType = getDefaultTransferNetworkConnectionType();
    }

    @Deprecated
    static long getDefaultCheckTimeInterval() {
        return 60000L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getDefaultThreadPoolSize() {
        return (Runtime.getRuntime().availableProcessors() + 1) * 2;
    }

    static TransferNetworkConnectionType getDefaultTransferNetworkConnectionType() {
        return TransferNetworkConnectionType.ANY;
    }

    public TransferNetworkConnectionType getTransferNetworkConnectionType() {
        return this.transferNetworkConnectionType;
    }

    @Deprecated
    public long getTransferServiceCheckTimeInterval() {
        return this.transferServiceCheckTimeInterval;
    }

    public int getTransferThreadPoolSize() {
        return this.transferThreadPoolSize;
    }

    @Deprecated
    public void setTransferServiceCheckTimeInterval(long j) {
    }

    public void setTransferThreadPoolSize(int i) {
        if (i < 0) {
            this.transferThreadPoolSize = getDefaultThreadPoolSize();
        } else {
            this.transferThreadPoolSize = i;
        }
    }

    public TransferUtilityOptions(int i, TransferNetworkConnectionType transferNetworkConnectionType) {
        this.transferServiceCheckTimeInterval = getDefaultCheckTimeInterval();
        this.transferThreadPoolSize = i;
        this.transferNetworkConnectionType = transferNetworkConnectionType;
    }
}
