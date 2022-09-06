package com.amazon.alexa.handsfree.protocols.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class DataSyncService extends Service {
    @Inject
    DataSyncIncomingMessageHandler mDataSyncIncomingMessageHandler;
    private Messenger mMessenger;

    public DataSyncService() {
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(@NonNull Intent intent) {
        if (intent.getPackage() == null) {
            return null;
        }
        return this.mMessenger.getBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(getApplicationContext(), FalcoProtocolComponent.class)).inject(this);
        this.mMessenger = new Messenger(this.mDataSyncIncomingMessageHandler);
    }

    @VisibleForTesting
    DataSyncService(@NonNull DataSyncIncomingMessageHandler dataSyncIncomingMessageHandler, @NonNull Messenger messenger) {
        this.mDataSyncIncomingMessageHandler = dataSyncIncomingMessageHandler;
        this.mMessenger = messenger;
    }
}
