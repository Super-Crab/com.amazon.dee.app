package com.amazon.alexa.dnssd;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import com.amazon.alexa.dnssd.exceptions.StartDiscoveryException;
import com.amazon.alexa.dnssd.exceptions.WifiNotConnectedException;
import com.amazon.alexa.dnssd.util.Metrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class RxNsd {
    public static final String TAG = "RxNsd";
    private final Metrics metrics;
    private final WifiManager.MulticastLock multicastLock;
    private final NsdManager nsdManager;
    private final WifiManager wifiManager;

    /* loaded from: classes7.dex */
    public class DiscoveryListener implements NsdManager.DiscoveryListener {
        final ObservableEmitter<NsdServiceInfo> emitter;

        public DiscoveryListener(ObservableEmitter<NsdServiceInfo> observableEmitter) {
            this.emitter = observableEmitter;
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onDiscoveryStarted(String str) {
            GeneratedOutlineSupport1.outline163("Started discovery: ", str, RxNsd.TAG);
            RxNsd.this.metrics.recordCount(RxNsd.TAG, "discovery_listener_start_success");
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onDiscoveryStopped(String str) {
            GeneratedOutlineSupport1.outline163("Stopped discovery: ", str, RxNsd.TAG);
            this.emitter.onComplete();
            RxNsd.this.metrics.recordCount(RxNsd.TAG, "discovery_listener_stop_success");
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onServiceFound(NsdServiceInfo nsdServiceInfo) {
            Log.i(RxNsd.TAG, String.format("Discovered %s. Attempting to resolve...", nsdServiceInfo.toString()));
            RxNsd.this.nsdManager.resolveService(nsdServiceInfo, new ResolveListener(this.emitter));
            RxNsd.this.metrics.recordCount(RxNsd.TAG, "discovery_listener_service_found");
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onServiceLost(NsdServiceInfo nsdServiceInfo) {
            Log.i(RxNsd.TAG, String.format("Lost %s", nsdServiceInfo.toString()));
            RxNsd.this.metrics.recordCount(RxNsd.TAG, "discovery_listener_service_lost");
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onStartDiscoveryFailed(String str, int i) {
            Log.i(RxNsd.TAG, String.format("Failed to start discovery: (%s, %d)", str, Integer.valueOf(i)));
            this.emitter.onError(new StartDiscoveryException());
            RxNsd.this.metrics.recordCount(RxNsd.TAG, "discovery_listener_start_fail");
        }

        @Override // android.net.nsd.NsdManager.DiscoveryListener
        public void onStopDiscoveryFailed(String str, int i) {
            Log.w(RxNsd.TAG, String.format("Failed to stop discovery: (%s, %d)", str, Integer.valueOf(i)));
            this.emitter.onComplete();
            RxNsd.this.metrics.recordCount(RxNsd.TAG, "discovery_listener_stop_fail");
        }
    }

    /* loaded from: classes7.dex */
    public class ResolveListener implements NsdManager.ResolveListener {
        final ObservableEmitter<NsdServiceInfo> emitter;

        public ResolveListener(ObservableEmitter<NsdServiceInfo> observableEmitter) {
            this.emitter = observableEmitter;
        }

        @Override // android.net.nsd.NsdManager.ResolveListener
        public void onResolveFailed(NsdServiceInfo nsdServiceInfo, int i) {
            Log.i(RxNsd.TAG, String.format("Failed to resolve: (%s, %d)", nsdServiceInfo.toString(), Integer.valueOf(i)));
            RxNsd.this.metrics.recordCount(RxNsd.TAG, "resolve_listener_fail");
        }

        @Override // android.net.nsd.NsdManager.ResolveListener
        public void onServiceResolved(NsdServiceInfo nsdServiceInfo) {
            String str = RxNsd.TAG;
            Log.i(str, "Service resolved: " + nsdServiceInfo);
            this.emitter.onNext(nsdServiceInfo);
            RxNsd.this.metrics.recordCount(RxNsd.TAG, "resolve_listener_success");
        }
    }

    @Inject
    public RxNsd(Metrics metrics, NsdManager nsdManager, WifiManager wifiManager, WifiManager.MulticastLock multicastLock) {
        this.metrics = metrics;
        this.nsdManager = nsdManager;
        this.wifiManager = wifiManager;
        this.multicastLock = multicastLock;
    }

    public Observable<NsdServiceInfo> discover(final String str, final int i) {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.alexa.dnssd.-$$Lambda$RxNsd$UtYim0UK5t_mT57VJrVW-QQT5zk
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                RxNsd.this.lambda$discover$1$RxNsd(str, i, observableEmitter);
            }
        });
    }

    public /* synthetic */ void lambda$discover$1$RxNsd(String str, int i, ObservableEmitter observableEmitter) throws Throwable {
        try {
            if (this.wifiManager.getConnectionInfo().getNetworkId() == -1) {
                observableEmitter.onError(new WifiNotConnectedException());
                synchronized (this.multicastLock) {
                    if (this.multicastLock.isHeld()) {
                        this.multicastLock.release();
                    }
                }
                this.metrics.recordCount(TAG, "discover_success", 0);
                return;
            }
            synchronized (this.multicastLock) {
                this.multicastLock.acquire();
            }
            final DiscoveryListener discoveryListener = new DiscoveryListener(observableEmitter);
            this.nsdManager.discoverServices(str, 1, discoveryListener);
            Observable.timer(i, TimeUnit.MILLISECONDS).subscribe(new Consumer() { // from class: com.amazon.alexa.dnssd.-$$Lambda$RxNsd$844wBDmHxCLRDrsaf5IKbJGLBPM
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    RxNsd.this.lambda$null$0$RxNsd(discoveryListener, (Long) obj);
                }
            });
            synchronized (this.multicastLock) {
                if (this.multicastLock.isHeld()) {
                    this.multicastLock.release();
                }
            }
            this.metrics.recordCount(TAG, "discover_success", 1);
        } catch (Throwable th) {
            try {
                observableEmitter.onError(th);
                synchronized (this.multicastLock) {
                    if (this.multicastLock.isHeld()) {
                        this.multicastLock.release();
                    }
                    this.metrics.recordCount(TAG, "discover_success", 0);
                }
            } catch (Throwable th2) {
                synchronized (this.multicastLock) {
                    if (this.multicastLock.isHeld()) {
                        this.multicastLock.release();
                    }
                    this.metrics.recordCount(TAG, "discover_success", 0);
                    throw th2;
                }
            }
        }
    }

    public /* synthetic */ void lambda$null$0$RxNsd(NsdManager.DiscoveryListener discoveryListener, Long l) throws Throwable {
        this.nsdManager.stopServiceDiscovery(discoveryListener);
    }
}
