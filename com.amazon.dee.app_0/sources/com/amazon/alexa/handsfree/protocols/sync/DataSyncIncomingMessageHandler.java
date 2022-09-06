package com.amazon.alexa.handsfree.protocols.sync;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import java.util.List;
/* loaded from: classes8.dex */
public class DataSyncIncomingMessageHandler extends Handler {
    private final List<DataSyncReceiver> mDataSyncReceiverList;

    public DataSyncIncomingMessageHandler(@NonNull List<DataSyncReceiver> list) {
        this.mDataSyncReceiverList = list;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        for (DataSyncReceiver dataSyncReceiver : this.mDataSyncReceiverList) {
            dataSyncReceiver.onMessageReceived(message);
        }
    }
}
