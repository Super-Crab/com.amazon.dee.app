package com.amazon.device.messaging;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.amazon.device.messaging.development.ADMManifest;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public abstract class ADMMessageReceiver extends BroadcastReceiver {
    private int mJobId;
    private boolean mServiceClassIsJob;
    private String mServiceClassName;

    @FireOsSdk
    protected ADMMessageReceiver() {
        this.mServiceClassName = null;
        this.mServiceClassIsJob = false;
    }

    @Override // android.content.BroadcastReceiver
    @FireOsSdk
    public final void onReceive(Context context, Intent intent) {
        String packageName = context.getPackageName();
        if (this.mServiceClassName == null) {
            this.mServiceClassName = GeneratedOutlineSupport1.outline72(packageName, ADMManifest.DEFAULT_MESSAGE_HANDLER_CLASS_NAME);
            try {
                context.getClassLoader().loadClass(this.mServiceClassName);
            } catch (ClassNotFoundException unused) {
                throw new IllegalStateException(String.format("%s constructor specifies the ADM default message handler name %s, but that class does not exist.", getClass().getName(), this.mServiceClassName));
            }
        }
        ComponentName componentName = new ComponentName(packageName, this.mServiceClassName);
        intent.setComponent(componentName);
        if (this.mServiceClassIsJob) {
            JobIntentService.enqueueWork(context, componentName, this.mJobId, intent);
        } else {
            ADMMessageHandlerBase.acquireWakeLock(context);
            context.startService(intent);
        }
        setResultCode(-1);
    }

    @FireOsSdk
    protected void registerIntentServiceClass(Class<? extends ADMMessageHandlerBase> cls) {
        this.mServiceClassName = cls.getName();
        this.mServiceClassIsJob = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @FireOsSdk
    public void registerJobServiceClass(Class<? extends ADMMessageHandlerJobBase> cls, int i) {
        this.mServiceClassName = cls.getName();
        this.mServiceClassIsJob = true;
        this.mJobId = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @FireOsSdk
    public ADMMessageReceiver(Class<? extends ADMMessageHandlerBase> cls) {
        this.mServiceClassName = cls.getName();
        this.mServiceClassIsJob = false;
    }

    @FireOsSdk
    protected ADMMessageReceiver(Class<? extends ADMMessageHandlerJobBase> cls, int i) {
        this.mServiceClassName = cls.getName();
        this.mServiceClassIsJob = true;
        this.mJobId = i;
    }
}
