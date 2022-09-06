package com.amazon.alexa.accessorykit.interprocess.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
/* loaded from: classes6.dex */
public final class AccessoryNetworkService {
    private static final String TAG = "AccessoryNetworkService:";
    private final ConnectivityManager connectivityManager;
    private NetworkConnectivityReceiver connectivityReceiver;
    private final Context context;
    private final PublishSubject<Boolean> subject;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public final class NetworkConnectivityReceiver extends BroadcastReceiver {
        NetworkConnectivityReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Logger.d("%s received Connectivity intent: %s", AccessoryNetworkService.TAG, intent.toString());
            AccessoryNetworkService.this.subject.onNext(Boolean.valueOf(AccessoryNetworkService.this.isConnected()));
        }
    }

    public AccessoryNetworkService(@NonNull Context context) {
        this(context, PublishSubject.create());
    }

    private void registerConnectivityReceiver() {
        IntentFilter outline10 = GeneratedOutlineSupport1.outline10(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION);
        this.connectivityReceiver = new NetworkConnectivityReceiver();
        this.context.registerReceiver(this.connectivityReceiver, outline10);
    }

    public boolean isConnected() {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        Logger.d("%s isConnected: %s", TAG, Boolean.valueOf(z));
        return z;
    }

    @NonNull
    public Observable<Boolean> onConnectivityChanged() {
        return this.subject.distinctUntilChanged();
    }

    @VisibleForTesting
    AccessoryNetworkService(@NonNull Context context, @NonNull PublishSubject<Boolean> publishSubject) {
        this.context = context;
        this.subject = publishSubject;
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        registerConnectivityReceiver();
    }
}
