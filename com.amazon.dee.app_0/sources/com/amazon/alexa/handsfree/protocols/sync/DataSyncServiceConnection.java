package com.amazon.alexa.handsfree.protocols.sync;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import java.lang.ref.WeakReference;
/* loaded from: classes8.dex */
public class DataSyncServiceConnection implements ServiceConnection {
    private final WeakReference<DataSyncServiceConnectionListener> mDataSyncServiceConnectionListenerRef;
    private Messenger mHostMessenger;
    private boolean mIsServiceBound;

    public DataSyncServiceConnection(@NonNull DataSyncServiceConnectionListener dataSyncServiceConnectionListener) {
        this.mDataSyncServiceConnectionListenerRef = new WeakReference<>(dataSyncServiceConnectionListener);
    }

    private CallbackErrorMetadata getCallbackErrorMetadata(@NonNull ComponentName componentName, @NonNull String str) {
        return new CallbackErrorMetadata(String.format(DataSyncConstants.ERROR_MESSAGE_DATA_SEND, componentName.getPackageName(), str));
    }

    public boolean isServiceBound() {
        return this.mIsServiceBound;
    }

    @Override // android.content.ServiceConnection
    public void onBindingDied(ComponentName componentName) {
        if (this.mDataSyncServiceConnectionListenerRef.get() != null) {
            this.mDataSyncServiceConnectionListenerRef.get().onBindingFailure(componentName, "Binding died");
        }
    }

    @Override // android.content.ServiceConnection
    public void onNullBinding(ComponentName componentName) {
        if (this.mDataSyncServiceConnectionListenerRef.get() != null) {
            this.mDataSyncServiceConnectionListenerRef.get().onBindingFailure(componentName, "Null binding");
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.mHostMessenger = new Messenger(iBinder);
        this.mIsServiceBound = true;
        if (this.mDataSyncServiceConnectionListenerRef.get() != null) {
            this.mDataSyncServiceConnectionListenerRef.get().onBindingSuccess(componentName);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.mHostMessenger = null;
        this.mIsServiceBound = false;
    }

    public void send(@NonNull Message message, @NonNull ComponentName componentName, @NonNull ResultCallback<ComponentName> resultCallback) {
        try {
            this.mHostMessenger.send(message);
            resultCallback.onResult(componentName);
        } catch (RemoteException e) {
            resultCallback.onError(getCallbackErrorMetadata(componentName, e.getMessage()));
        } catch (NullPointerException e2) {
            resultCallback.onError(getCallbackErrorMetadata(componentName, e2.getMessage()));
        }
    }

    @VisibleForTesting
    DataSyncServiceConnection(@Nullable Messenger messenger, @NonNull DataSyncServiceConnectionListener dataSyncServiceConnectionListener) {
        this.mHostMessenger = messenger;
        this.mDataSyncServiceConnectionListenerRef = new WeakReference<>(dataSyncServiceConnectionListener);
    }
}
