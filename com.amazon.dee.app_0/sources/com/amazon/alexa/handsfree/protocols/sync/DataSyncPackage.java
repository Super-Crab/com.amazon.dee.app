package com.amazon.alexa.handsfree.protocols.sync;

import android.content.ComponentName;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
/* loaded from: classes8.dex */
public class DataSyncPackage {
    private final ComponentName mComponentName;
    private DataSyncServiceConnection mDataSyncServiceConnection;
    private Message mMessage;
    private ResultCallback<ComponentName> mResultCallback;

    public DataSyncPackage(@NonNull ComponentName componentName) {
        this.mComponentName = componentName;
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    public DataSyncServiceConnection getDataSyncServiceConnection() {
        return this.mDataSyncServiceConnection;
    }

    public Message getMessage() {
        return this.mMessage;
    }

    public ResultCallback<ComponentName> getResultCallback() {
        return this.mResultCallback;
    }

    public void setDataSyncServiceConnection(@Nullable DataSyncServiceConnection dataSyncServiceConnection) {
        this.mDataSyncServiceConnection = dataSyncServiceConnection;
    }

    public void setMessage(@Nullable Message message) {
        this.mMessage = message;
    }

    public void setResultCallback(@Nullable ResultCallback<ComponentName> resultCallback) {
        this.mResultCallback = resultCallback;
    }
}
