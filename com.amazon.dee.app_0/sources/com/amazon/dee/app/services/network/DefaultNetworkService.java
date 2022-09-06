package com.amazon.dee.app.services.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.reactnativecommunity.netinfo.BroadcastReceiverConnectivityReceiver;
import rx.Observable;
import rx.subjects.BehaviorSubject;
/* loaded from: classes12.dex */
public final class DefaultNetworkService implements NetworkService {
    private static final String TAG = Log.tag(DefaultNetworkService.class);
    private ConnectivityReceiver connectivityReceiver;
    private final Context context;
    private final BehaviorSubject<Boolean> subject;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public final class ConnectivityReceiver extends BroadcastReceiver {
        boolean initialUpdate = true;

        ConnectivityReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!this.initialUpdate) {
                DefaultNetworkService.this.subject.onNext(Boolean.valueOf(DefaultNetworkService.this.isConnected()));
            } else {
                this.initialUpdate = false;
            }
        }
    }

    public DefaultNetworkService(@NonNull Context context) {
        this(context, BehaviorSubject.create());
    }

    private void registerConnectivityReceiver() {
        if (this.connectivityReceiver == null) {
            IntentFilter outline10 = GeneratedOutlineSupport1.outline10(BroadcastReceiverConnectivityReceiver.CONNECTIVITY_ACTION);
            this.connectivityReceiver = new ConnectivityReceiver();
            this.context.registerReceiver(this.connectivityReceiver, outline10);
        }
    }

    @Override // com.amazon.alexa.protocols.network.NetworkService
    @NonNull
    public String getNetworkType() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return NetworkService.NETWORK_NOT_CONNECTED;
        }
        String typeName = activeNetworkInfo.getTypeName();
        String subtypeName = activeNetworkInfo.getSubtypeName();
        return (subtypeName == null || subtypeName.length() <= 0) ? typeName : GeneratedOutlineSupport1.outline75(typeName, " ", subtypeName);
    }

    @Override // com.amazon.alexa.protocols.network.NetworkService
    public boolean isConnected() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        String.format("isConnected: %s", Boolean.valueOf(z));
        return z;
    }

    @Override // com.amazon.alexa.protocols.network.NetworkService
    @NonNull
    public Observable<Boolean> onConnectivityChanged() {
        return this.subject.distinctUntilChanged();
    }

    @VisibleForTesting
    DefaultNetworkService(@NonNull Context context, @NonNull BehaviorSubject behaviorSubject) {
        this.context = context;
        this.subject = behaviorSubject;
        this.connectivityReceiver = null;
        registerConnectivityReceiver();
    }
}
