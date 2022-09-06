package com.amazon.alexa.accessory.speechapi.csm;

import amazon.speech.simclient.settings.SettingsClient;
import amazon.speech.simclient.settings.SettingsStatusCallback;
import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
/* compiled from: CsmConnectionCallback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u0000 #2\u00020\u0001:\u0001#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0006J\u0016\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0006H\u0002J\u0010\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u001bH\u0002J\u0006\u0010 \u001a\u00020\u0014J\u000e\u0010!\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rJ\u0006\u0010\"\u001a\u00020\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/CsmConnectionCallback;", "", "settingsClient", "Lamazon/speech/simclient/settings/SettingsClient;", "(Lamazon/speech/simclient/settings/SettingsClient;)V", "alexaConnectedStatus", "", "connectionCallback", "Lamazon/speech/simclient/settings/SettingsStatusCallback;", "getConnectionCallback", "()Lamazon/speech/simclient/settings/SettingsStatusCallback;", "connectionListeners", "Ljava/util/ArrayList;", "Lcom/amazon/alexa/accessory/speechapi/listeners/ConnectionListener;", "Lkotlin/collections/ArrayList;", "isConnectionListenerRegistered", JoinPoint.SYNCHRONIZATION_LOCK, "mainHandler", "Landroid/os/Handler;", "addAndNotifyConnectionListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "shouldNotify", "notifyAlexaConnectionFailedReason", "connectingFailedReason", "Lcom/amazon/alexa/accessory/speechapi/listeners/ConnectionListener$ConnectingFailedReason;", "message", "", "notifyConnectionListeners", "isConnected", "recordAlexaConnectionFailedReason", "metricName", "registerCSMConnectionCallback", "removeConnectionListener", "unregisterCSMConnectionCallback", "Companion", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmConnectionCallback {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "CsmConnectionCallback:";
    private boolean alexaConnectedStatus;
    @NotNull
    private final SettingsStatusCallback connectionCallback;
    private final ArrayList<ConnectionListener> connectionListeners;
    private boolean isConnectionListenerRegistered;
    private final Object lock;
    private final Handler mainHandler;
    private final SettingsClient settingsClient;

    /* compiled from: CsmConnectionCallback.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/CsmConnectionCallback$Companion;", "", "()V", "TAG", "", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CsmConnectionCallback(@NotNull SettingsClient settingsClient) {
        Intrinsics.checkParameterIsNotNull(settingsClient, "settingsClient");
        this.settingsClient = settingsClient;
        this.lock = new Object();
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.connectionListeners = new ArrayList<>();
        this.connectionCallback = new CsmConnectionCallback$connectionCallback$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyConnectionListeners(boolean z) {
        List<ConnectionListener> mutableList;
        synchronized (this.lock) {
            mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) this.connectionListeners);
            for (ConnectionListener connectionListener : mutableList) {
                if (z) {
                    connectionListener.onConnected();
                } else {
                    connectionListener.onDisconnected();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void recordAlexaConnectionFailedReason(String str) {
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(str, "alexa_accessories", 1.0d, null);
    }

    public final void addAndNotifyConnectionListener(@NotNull ConnectionListener listener, boolean z) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Logger.d("CsmConnectionCallback: addAndNotifyConnectionListener shouldNotify: " + z);
        if (z && this.alexaConnectedStatus) {
            listener.onConnected();
        }
        synchronized (this.lock) {
            this.connectionListeners.add(listener);
        }
    }

    @NotNull
    public final SettingsStatusCallback getConnectionCallback() {
        return this.connectionCallback;
    }

    public final void notifyAlexaConnectionFailedReason(@NotNull ConnectionListener.ConnectingFailedReason connectingFailedReason, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(connectingFailedReason, "connectingFailedReason");
        Intrinsics.checkParameterIsNotNull(message, "message");
        synchronized (this.lock) {
            recordAlexaConnectionFailedReason("CsmAlexaConnectionFailedReason:" + connectingFailedReason.name());
            Iterator<ConnectionListener> it2 = this.connectionListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onConnectingFailed(connectingFailedReason, message);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void registerCSMConnectionCallback() {
        Logger.d("CsmConnectionCallback: registerCSMConnectionCallback");
        if (!this.isConnectionListenerRegistered) {
            this.isConnectionListenerRegistered = true;
            this.settingsClient.registerCallback("connected", this.connectionCallback, true);
        }
    }

    public final void removeConnectionListener(@NotNull ConnectionListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Logger.d("CsmConnectionCallback: removeConnectionListener");
        synchronized (this.lock) {
            this.connectionListeners.remove(listener);
        }
    }

    public final void unregisterCSMConnectionCallback() {
        Logger.d("CsmConnectionCallback: unregisterCSMConnectionCallback");
        if (this.isConnectionListenerRegistered) {
            this.isConnectionListenerRegistered = false;
            this.settingsClient.unregisterCallback("connected", this.connectionCallback);
        }
        this.alexaConnectedStatus = false;
        notifyConnectionListeners(false);
        synchronized (this.lock) {
            this.connectionListeners.clear();
            Unit unit = Unit.INSTANCE;
        }
    }
}
