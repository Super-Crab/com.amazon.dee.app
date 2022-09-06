package com.amazon.deecomms.common.util;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
@SuppressLint({"ParcelCreator"})
/* loaded from: classes12.dex */
public class ResultReceiverWrapper extends ResultReceiver {
    public static final int RESULT_FAILED = 1;
    public static final int RESULT_OK = 0;
    private Receiver mReceiver;

    /* loaded from: classes12.dex */
    public interface Receiver {
        void onReceiveResult(int i, Bundle bundle);
    }

    public ResultReceiverWrapper(Handler handler) {
        super(handler);
    }

    @Override // android.os.ResultReceiver
    protected void onReceiveResult(int i, Bundle bundle) {
        Receiver receiver = this.mReceiver;
        if (receiver != null) {
            receiver.onReceiveResult(i, bundle);
        }
    }

    public void setReceiver(Receiver receiver) {
        this.mReceiver = receiver;
    }
}
