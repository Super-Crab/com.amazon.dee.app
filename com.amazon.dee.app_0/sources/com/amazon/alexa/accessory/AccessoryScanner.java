package com.amazon.alexa.accessory;
/* loaded from: classes.dex */
public interface AccessoryScanner {
    void cancelScan(AccessoryScannerListener accessoryScannerListener);

    boolean isScanning(AccessoryScannerListener accessoryScannerListener);

    boolean startScan(AccessoryScannerListener accessoryScannerListener);
}
