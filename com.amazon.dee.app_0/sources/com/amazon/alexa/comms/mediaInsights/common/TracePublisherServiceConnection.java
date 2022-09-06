package com.amazon.alexa.comms.mediaInsights.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.comms.mediaInsights.common.impl.BindingIntentProvider;
import com.amazon.alexa.comms.mediaInsights.common.impl.LoggingUtils;
import java.util.Locale;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class TracePublisherServiceConnection implements ServiceConnection {
    public static final String UNIQUE_IDENTIFIER = "uniqueIdentifier";
    private IBinder binder;
    @NonNull
    private BindingIntentProvider bindingIntentProvider;
    @NonNull
    private Context context;
    private volatile boolean isServiceConnectionCreated = false;
    private TracePublisherServiceConnectionListener listener;
    @NonNull
    private final String uniqueIdentifier;

    public TracePublisherServiceConnection(@NonNull Context context, @NonNull BindingIntentProvider bindingIntentProvider, @NonNull String str) {
        if (context != null) {
            if (bindingIntentProvider == null) {
                throw new IllegalArgumentException("bindingIntentProvider is null");
            }
            if (str != null) {
                this.context = context;
                this.bindingIntentProvider = bindingIntentProvider;
                this.uniqueIdentifier = str;
                return;
            }
            throw new IllegalArgumentException("uniqueIdentifier is null");
        }
        throw new IllegalArgumentException("context is null");
    }

    public void close() {
        this.context.unbindService(this);
    }

    public synchronized boolean connect() {
        if (!this.isServiceConnectionCreated) {
            Intent provideBindingIntent = this.bindingIntentProvider.provideBindingIntent(this.context);
            provideBindingIntent.putExtra(UNIQUE_IDENTIFIER, this.uniqueIdentifier);
            Log.i(LoggingUtils.MEDIA_INSIGHTS_TAG, String.format(Locale.US, "uniqueIdentifier %s was put into the intent extra", this.uniqueIdentifier));
            if (this.context.bindService(provideBindingIntent, this, 1)) {
                this.isServiceConnectionCreated = true;
            } else {
                Log.e(LoggingUtils.MEDIA_INSIGHTS_TAG, "Service failed to bind");
            }
        }
        return this.isServiceConnectionCreated;
    }

    public Messenger getMessenger() {
        if (!this.isServiceConnectionCreated) {
            connect();
        }
        IBinder iBinder = this.binder;
        if (iBinder != null) {
            return new Messenger(iBinder);
        }
        return null;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        String.format("%s : TracePublisherServiceConnection Service connected", componentName);
        this.binder = iBinder;
        TracePublisherServiceConnectionListener tracePublisherServiceConnectionListener = this.listener;
        if (tracePublisherServiceConnectionListener != null) {
            tracePublisherServiceConnectionListener.onServiceConnected();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        String.format("%s : TracePublisherServiceConnection Service Disconnected", componentName);
        this.binder = null;
    }

    public void setListener(TracePublisherServiceConnectionListener tracePublisherServiceConnectionListener) {
        this.listener = tracePublisherServiceConnectionListener;
    }
}
