package com.amazon.alexa.handsfree.ui.utils;

import android.os.Parcel;
import android.os.ResultReceiver;
import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public class ResultReceiverWrapper {
    private ResultReceiverWrapper() {
    }

    public static ResultReceiver getReceiverOf(@NonNull ResultReceiver resultReceiver) {
        Parcel obtain = Parcel.obtain();
        resultReceiver.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        ResultReceiver resultReceiver2 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return resultReceiver2;
    }
}
