package com.bugsnag.android;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import androidx.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ConnectivityCompat.kt */
@RequiresApi(24)
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001:\u0001\u0016B8\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012)\u0010\u0004\u001a%\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u0011\u001a\u00020\u0006H\u0016J\b\u0010\u0012\u001a\u00020\nH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\nH\u0016R\u0014\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00060\u0010R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/bugsnag/android/ConnectivityApi24;", "Lcom/bugsnag/android/Connectivity;", "cm", "Landroid/net/ConnectivityManager;", "callback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "connected", "", "Lcom/bugsnag/android/NetworkChangeCallback;", "(Landroid/net/ConnectivityManager;Lkotlin/jvm/functions/Function1;)V", "activeNetwork", "Landroid/net/Network;", "networkCallback", "Lcom/bugsnag/android/ConnectivityApi24$ConnectivityTrackerCallback;", "hasNetworkConnection", "registerForNetworkChanges", "retrieveNetworkAccessState", "", "unregisterForNetworkChanges", "ConnectivityTrackerCallback", "bugsnag-android-core_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ConnectivityApi24 implements Connectivity {
    @JvmField
    @Nullable
    public volatile Network activeNetwork;
    private final ConnectivityManager cm;
    private final ConnectivityTrackerCallback networkCallback;

    /* compiled from: ConnectivityCompat.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B0\u0012)\u0010\u0002\u001a%\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003j\u0004\u0018\u0001`\t¢\u0006\u0002\u0010\nJ\u0012\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\bH\u0016R1\u0010\u0002\u001a%\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003j\u0004\u0018\u0001`\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/bugsnag/android/ConnectivityApi24$ConnectivityTrackerCallback;", "Landroid/net/ConnectivityManager$NetworkCallback;", "cb", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "connected", "", "Lcom/bugsnag/android/NetworkChangeCallback;", "(Lcom/bugsnag/android/ConnectivityApi24;Lkotlin/jvm/functions/Function1;)V", "onAvailable", "network", "Landroid/net/Network;", "onUnavailable", "bugsnag-android-core_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes.dex */
    private final class ConnectivityTrackerCallback extends ConnectivityManager.NetworkCallback {
        private final Function1<Boolean, Unit> cb;

        /* JADX WARN: Multi-variable type inference failed */
        public ConnectivityTrackerCallback(@Nullable Function1<? super Boolean, Unit> function1) {
            this.cb = function1;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(@Nullable Network network) {
            super.onAvailable(network);
            ConnectivityApi24.this.activeNetwork = network;
            Function1<Boolean, Unit> function1 = this.cb;
            if (function1 != null) {
                function1.mo12165invoke(true);
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onUnavailable() {
            super.onUnavailable();
            ConnectivityApi24.this.activeNetwork = null;
            Function1<Boolean, Unit> function1 = this.cb;
            if (function1 != null) {
                function1.mo12165invoke(false);
            }
        }
    }

    public ConnectivityApi24(@NotNull ConnectivityManager cm, @Nullable Function1<? super Boolean, Unit> function1) {
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(cm, "cm");
        this.cm = cm;
        this.networkCallback = new ConnectivityTrackerCallback(function1);
    }

    @Override // com.bugsnag.android.Connectivity
    public boolean hasNetworkConnection() {
        return this.activeNetwork != null;
    }

    @Override // com.bugsnag.android.Connectivity
    public void registerForNetworkChanges() {
        this.cm.registerDefaultNetworkCallback(this.networkCallback);
    }

    @Override // com.bugsnag.android.Connectivity
    @NotNull
    public String retrieveNetworkAccessState() {
        Network activeNetwork = this.cm.getActiveNetwork();
        NetworkCapabilities networkCapabilities = activeNetwork != null ? this.cm.getNetworkCapabilities(activeNetwork) : null;
        return networkCapabilities == null ? "none" : networkCapabilities.hasTransport(1) ? "wifi" : networkCapabilities.hasTransport(3) ? "ethernet" : networkCapabilities.hasTransport(0) ? "cellular" : "unknown";
    }

    @Override // com.bugsnag.android.Connectivity
    public void unregisterForNetworkChanges() {
        this.cm.unregisterNetworkCallback(this.networkCallback);
    }
}
