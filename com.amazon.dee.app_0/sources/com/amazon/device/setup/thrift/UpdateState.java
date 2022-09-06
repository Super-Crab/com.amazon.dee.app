package com.amazon.device.setup.thrift;

import org.apache.thrift.orig.TEnum;
/* loaded from: classes12.dex */
public enum UpdateState implements TEnum {
    Pending(0),
    Downloading(1),
    Downloaded(2),
    Installing(3),
    Installed(4),
    Complete(5),
    Failed(6),
    Deduped(7),
    DownloadPaused(8),
    QueuedForWifi(9),
    InsufficientSpaceForDownload(10),
    InsufficientSpaceForInstall(11);
    
    private final int value;

    UpdateState(int i) {
        this.value = i;
    }

    public static UpdateState findByValue(int i) {
        switch (i) {
            case 0:
                return Pending;
            case 1:
                return Downloading;
            case 2:
                return Downloaded;
            case 3:
                return Installing;
            case 4:
                return Installed;
            case 5:
                return Complete;
            case 6:
                return Failed;
            case 7:
                return Deduped;
            case 8:
                return DownloadPaused;
            case 9:
                return QueuedForWifi;
            case 10:
                return InsufficientSpaceForDownload;
            case 11:
                return InsufficientSpaceForInstall;
            default:
                return null;
        }
    }

    @Override // org.apache.thrift.orig.TEnum
    public int getValue() {
        return this.value;
    }
}
