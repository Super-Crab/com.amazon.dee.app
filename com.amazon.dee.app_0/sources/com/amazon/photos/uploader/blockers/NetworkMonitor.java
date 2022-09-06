package com.amazon.photos.uploader.blockers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.amazon.alexa.smarthomecameras.routing.CamerasRouteParameter;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.photos.uploader.internal.OpenForTesting;
import com.amazon.photos.uploader.internal.utils.SystemUtil;
import com.amazon.photos.uploader.log.UploadLogger;
import com.amazon.photos.uploader.observables.UploadSummary;
import com.amazon.photos.uploader.observables.UploadSummaryKt;
import com.amazon.photos.uploader.observables.UploadSummaryObserver;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.aspectj.lang.JoinPoint;
import org.bouncycastle.i18n.ErrorBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NetworkMonitor.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u0000 -2\u00020\u0001:\u0003-./B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0015J\u0006\u0010!\u001a\u00020\u001fJ\u000e\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$J\u0010\u0010%\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010)\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020'H\u0016J\u0006\u0010*\u001a\u00020\u001fJ\b\u0010+\u001a\u00020\u001fH\u0002J\b\u0010,\u001a\u00020\u001fH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0018\u00010\u0012R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\b\u0018\u00010\u0019R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u00060"}, d2 = {"Lcom/amazon/photos/uploader/blockers/NetworkMonitor;", "Lcom/amazon/photos/uploader/observables/UploadSummaryObserver;", "context", "Landroid/content/Context;", "systemUtil", "Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "(Landroid/content/Context;Lcom/amazon/photos/uploader/internal/utils/SystemUtil;Lcom/amazon/photos/uploader/log/UploadLogger;)V", "getContext", "()Landroid/content/Context;", "isRegistered", "", JoinPoint.SYNCHRONIZATION_LOCK, "", "getLogger", "()Lcom/amazon/photos/uploader/log/UploadLogger;", "networkCallback", "Lcom/amazon/photos/uploader/blockers/NetworkMonitor$NetworkCallback;", "networkListeners", "", "Lcom/amazon/photos/uploader/blockers/NetworkListener;", "getNetworkListeners", "()Ljava/util/List;", "networkStateBroadcastReceiver", "Lcom/amazon/photos/uploader/blockers/NetworkMonitor$NetworkStateBroadcastReceiver;", "started", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getSystemUtil", "()Lcom/amazon/photos/uploader/internal/utils/SystemUtil;", "addListener", "", "networkListener", "notifyNetworkLoss", "notifyOnNetworkChange", "networkState", "Lcom/amazon/photos/uploader/blockers/NetworkState;", "onChanged", ErrorBundle.SUMMARY_ENTRY, "Lcom/amazon/photos/uploader/observables/UploadSummary;", "onUploaderStarted", "onUploaderStopped", "registerNetworkCallback", "toggleCallback", "unregisterNetworkCallback", "Companion", "NetworkCallback", "NetworkStateBroadcastReceiver", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class NetworkMonitor implements UploadSummaryObserver {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "NetworkBlockerMonitor";
    @NotNull
    private final Context context;
    private boolean isRegistered;
    private final Object lock;
    @NotNull
    private final UploadLogger logger;
    private NetworkCallback networkCallback;
    @NotNull
    private final List<NetworkListener> networkListeners;
    private NetworkStateBroadcastReceiver networkStateBroadcastReceiver;
    private AtomicBoolean started;
    @NotNull
    private final SystemUtil systemUtil;

    /* compiled from: NetworkMonitor.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/blockers/NetworkMonitor$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: NetworkMonitor.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/photos/uploader/blockers/NetworkMonitor$NetworkCallback;", "Landroid/net/ConnectivityManager$NetworkCallback;", "(Lcom/amazon/photos/uploader/blockers/NetworkMonitor;)V", "onCapabilitiesChanged", "", "network", "Landroid/net/Network;", CamerasRouteParameter.CAPABILITIES, "Landroid/net/NetworkCapabilities;", "onLost", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class NetworkCallback extends ConnectivityManager.NetworkCallback {
        public NetworkCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(@NotNull Network network, @NotNull NetworkCapabilities capabilities) {
            Intrinsics.checkParameterIsNotNull(network, "network");
            Intrinsics.checkParameterIsNotNull(capabilities, "capabilities");
            NetworkMonitor.this.notifyOnNetworkChange(new NetworkState(NetworkMonitor.this.getSystemUtil()));
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(@NotNull Network network) {
            Intrinsics.checkParameterIsNotNull(network, "network");
            NetworkMonitor.this.notifyNetworkLoss();
        }
    }

    /* compiled from: NetworkMonitor.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/photos/uploader/blockers/NetworkMonitor$NetworkStateBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/amazon/photos/uploader/blockers/NetworkMonitor;)V", "onReceive", "", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class NetworkStateBroadcastReceiver extends BroadcastReceiver {
        public NetworkStateBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(@Nullable Context context, @Nullable Intent intent) {
            String action;
            if (intent == null || (action = intent.getAction()) == null) {
                return;
            }
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new NetworkMonitor$NetworkStateBroadcastReceiver$onReceive$$inlined$let$lambda$1(action, null, this), 3, null);
        }
    }

    public NetworkMonitor(@NotNull Context context, @NotNull SystemUtil systemUtil, @NotNull UploadLogger logger) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.context = context;
        this.systemUtil = systemUtil;
        this.logger = logger;
        this.lock = new Object();
        this.started = new AtomicBoolean(false);
        this.networkListeners = new ArrayList();
        registerNetworkCallback();
        this.isRegistered = true;
        this.started.set(true);
    }

    private final void toggleCallback() {
        synchronized (this.lock) {
            if (!this.isRegistered) {
                registerNetworkCallback();
                this.isRegistered = true;
                this.logger.i$AndroidPhotosUploader_release(TAG, "Network callback enabled");
            } else if (this.isRegistered) {
                unregisterNetworkCallback();
                this.isRegistered = false;
                this.logger.i$AndroidPhotosUploader_release(TAG, "Network callback disabled");
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void unregisterNetworkCallback() {
        NetworkStateBroadcastReceiver networkStateBroadcastReceiver = this.networkStateBroadcastReceiver;
        if (networkStateBroadcastReceiver != null) {
            this.context.unregisterReceiver(networkStateBroadcastReceiver);
        }
        NetworkCallback networkCallback = this.networkCallback;
        if (networkCallback != null) {
            this.systemUtil.getConnectivityManager().unregisterNetworkCallback(networkCallback);
        }
    }

    public final synchronized void addListener(@NotNull NetworkListener networkListener) {
        Intrinsics.checkParameterIsNotNull(networkListener, "networkListener");
        this.networkListeners.add(networkListener);
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @NotNull
    public final UploadLogger getLogger() {
        return this.logger;
    }

    @NotNull
    public final List<NetworkListener> getNetworkListeners() {
        return this.networkListeners;
    }

    @NotNull
    public final SystemUtil getSystemUtil() {
        return this.systemUtil;
    }

    public final synchronized void notifyNetworkLoss() {
        for (NetworkListener networkListener : this.networkListeners) {
            networkListener.onNetworkLoss();
        }
    }

    public final synchronized void notifyOnNetworkChange(@NotNull NetworkState networkState) {
        Intrinsics.checkParameterIsNotNull(networkState, "networkState");
        for (NetworkListener networkListener : this.networkListeners) {
            networkListener.onNetworkChange(networkState);
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadSummaryObserver
    public void onChanged(@NotNull UploadSummary summary) {
        Intrinsics.checkParameterIsNotNull(summary, "summary");
        if (this.started.get() && UploadSummaryKt.hasUploadFinished(summary)) {
            toggleCallback();
            this.started.set(false);
        } else if (this.started.getAndSet(true)) {
        } else {
            toggleCallback();
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadSummaryObserver
    public void onUploaderStarted(@NotNull UploadSummary summary) {
        Intrinsics.checkParameterIsNotNull(summary, "summary");
    }

    @Override // com.amazon.photos.uploader.observables.UploadSummaryObserver
    public void onUploaderStopped(@NotNull UploadSummary summary) {
        Intrinsics.checkParameterIsNotNull(summary, "summary");
    }

    public final void registerNetworkCallback() {
        if (this.systemUtil.getOsVersionCode() > 23) {
            NetworkCallback networkCallback = new NetworkCallback();
            this.systemUtil.getConnectivityManager().registerNetworkCallback(this.systemUtil.getNetworkRequestBuilder().build(), networkCallback);
            this.networkCallback = networkCallback;
            return;
        }
        NetworkStateBroadcastReceiver networkStateBroadcastReceiver = new NetworkStateBroadcastReceiver();
        this.context.registerReceiver(networkStateBroadcastReceiver, new IntentFilter(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION));
        this.networkStateBroadcastReceiver = networkStateBroadcastReceiver;
    }
}
