package com.amazon.alexa.api;

import android.content.ComponentName;
import android.content.Context;
import android.os.ConditionVariable;
import android.os.IBinder;
import androidx.annotation.NonNull;
import com.amazon.comms.config.util.DeviceConfigConstants;
/* loaded from: classes6.dex */
public class ExternalCapabilityAgentConnection extends ManagedConnection<CapabilityAgentMessageSender> {
    public final ConditionVariable BIo;
    public final CapabilityAgentConnectionMessageSenderFactory zQM;

    public ExternalCapabilityAgentConnection(Context context, ComponentName componentName, CapabilityAgentConnectionMessageSenderFactory capabilityAgentConnectionMessageSenderFactory) {
        super(context, componentName);
        this.zQM = capabilityAgentConnectionMessageSenderFactory;
        this.BIo = new ConditionVariable(false);
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void connect() {
        if (!super.isConnected()) {
            this.BIo.close();
            super.connect();
            this.BIo.block(DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS);
        }
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    /* renamed from: createServiceInterface */
    public Releasable mo811createServiceInterface(@NonNull IBinder iBinder) {
        CapabilityAgentConnectionMessageSenderFactory capabilityAgentConnectionMessageSenderFactory = this.zQM;
        return new CapabilityAgentMessageSender(iBinder, capabilityAgentConnectionMessageSenderFactory.zZm.zZm(zZm()));
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void disconnect() {
        super.disconnect();
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public boolean isConnected() {
        return super.isConnected();
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void onConnectedToService() {
        this.BIo.open();
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void onDisconnectedFromService() {
        this.BIo.open();
    }

    public ComponentName zZm() {
        return this.zZm;
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    /* renamed from: get */
    public CapabilityAgentMessageSender mo838get() {
        return (CapabilityAgentMessageSender) super.mo838get();
    }
}
