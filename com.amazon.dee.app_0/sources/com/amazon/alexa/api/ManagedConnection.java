package com.amazon.alexa.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.api.ManagedServiceConnection;
import com.amazon.alexa.api.Releasable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public abstract class ManagedConnection<T extends Releasable> extends ManagedServiceConnection<T> {
    public final ComponentName zZm;

    public ManagedConnection(Context context, ComponentName componentName) {
        super(context);
        Preconditions.notNull(componentName, "The provided ComponentName was null.");
        this.zZm = componentName;
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void deregisterListener(ManagedServiceConnection.ConnectionListener connectionListener) {
        super.deregisterListener(connectionListener);
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public ExtendedClient getClient() {
        return super.getClient();
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public Intent getConnectionIntent(ComponentName componentName, boolean z) {
        ExtendedClient client = super.getClient();
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("EXTRA_EXTENDED_CLIENT", client.getBundle());
        intent.putExtra("EXTRA_CLIENT", client.asClient());
        return intent;
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public ComponentName getServiceComponentName() {
        return this.zZm;
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public boolean isServiceComponentNameResolved() {
        return true;
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void registerListener(ManagedServiceConnection.ConnectionListener connectionListener) {
        super.registerListener(connectionListener);
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void release() {
        super.release();
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void resolveServiceComponentName() {
    }
}
