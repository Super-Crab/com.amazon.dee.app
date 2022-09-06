package org.reactnative.camera.tasks;

import com.google.zxing.Result;
/* loaded from: classes5.dex */
public interface BarCodeScannerAsyncTaskDelegate {
    void onBarCodeRead(Result result, int i, int i2, byte[] bArr);

    void onBarCodeScanningTaskCompleted();
}
