package com.amazon.alexa;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.TTH;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.metrics.core.NetworkStateProvider;
import com.amazon.alexa.gmj;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.amazon.alexa.utils.device.AlexaHandsFreeDeviceInformation;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.devicesetupservice.reporting.Radio;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.inject.Singleton;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.http2.StreamResetException;
/* compiled from: ConnectivityAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class qxC extends ConnectivityManager.NetworkCallback implements NetworkStateProvider {
    public static final String zZm = "qxC";
    public final Context BIo;
    public Network HvC;
    public final AlexaClientEventBus JTe;
    public final ExecutorService LPk;
    public final NetworkRequest Mlj;
    public final ConnectivityManager Qle;
    public volatile boolean dMe;
    public final WifiManager jiA;
    public final Dtt lOf;
    public Future<?> uzr;
    public final Szi yPL;
    public final Lazy<RZN> zQM;
    public final TelephonyManager zyO;
    public final DYu zzR;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ConnectivityAuthority.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class BIo {
        public final int BIo;
        public final String zZm;

        @VisibleForTesting
        public BIo(String str, int i) {
            this.zZm = str;
            this.BIo = i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.zZm);
            sb.append(RealTimeTextConstants.COLON_SPACE);
            return GeneratedOutlineSupport1.outline86(sb, this.BIo, " dBm");
        }
    }

    /* compiled from: ConnectivityAuthority.java */
    /* loaded from: classes.dex */
    private class zZm implements Callable<Void> {
        public final boolean BIo;
        public final Network jiA;
        public final DialogRequestIdentifier zQM;
        public final Future<Boolean> zZm;
        public final RrI zyO;

        public zZm(boolean z, DialogRequestIdentifier dialogRequestIdentifier, Network network) {
            this.zZm = qxC.this.LPk.submit((Callable) qxC.this.zQM.mo358get());
            this.BIo = z;
            this.zQM = dialogRequestIdentifier;
            this.zyO = RrI.zZm(dialogRequestIdentifier);
            this.jiA = network;
        }

        @Override // java.util.concurrent.Callable
        public Void call() throws Exception {
            qxC.this.JTe.zyO(YQk.zZm(this.zyO));
            if (!qxC.this.zZm(this.zZm)) {
                qxC.this.JTe.zyO(ycT.BIo());
                if (qxC.this.Qle()) {
                    if (qxC.this.yPL.zZm()) {
                        qxC.this.JTe.zyO(YHu.BIo());
                        if (this.BIo) {
                            qxC.this.JTe.zyO(cXw.zZm(qxC.this.zzR.zQM(this.zyO)));
                        }
                    } else if (this.BIo) {
                        qxC.this.JTe.zyO(TTH.zZm("No internet connectivity for the connected network", TTH.zZm.NO_NETWORK_CONNECTIVITY, this.zQM));
                        zZm();
                    }
                } else if (this.BIo) {
                    qxC.this.JTe.zyO(TTH.zZm("Not connected to any network", TTH.zZm.NO_NETWORK_CONNECTIVITY, this.zQM));
                    zZm();
                }
            } else {
                Log.i(qxC.zZm, "Network connectivity confirmed");
                if (this.BIo) {
                    qxC.this.JTe.zyO(new smv());
                }
            }
            qxC.this.JTe.zyO(AhI.zZm(this.zyO));
            return null;
        }

        public final void zZm() {
            boolean z;
            Network BIo = qxC.this.BIo();
            boolean zZm = qxC.this.zZm(this.jiA, BIo);
            if (BIo != null && qxC.this.Qle.getNetworkCapabilities(BIo) != null) {
                qxC qxc = qxC.this;
                if (qxc.BIo(qxc.Qle.getNetworkCapabilities(BIo)) < 192) {
                    z = true;
                    if (BIo != null && qxC.this.Qle.getNetworkCapabilities(BIo) != null && !zZm) {
                        qxC.this.JTe.zyO(new XWd(qxC.this.Qle.getNetworkCapabilities(BIo).getLinkUpstreamBandwidthKbps()));
                    }
                    qxC.this.JTe.zyO(new dCD(zZm, z, this.zQM));
                }
            }
            z = false;
            if (BIo != null) {
                qxC.this.JTe.zyO(new XWd(qxC.this.Qle.getNetworkCapabilities(BIo).getLinkUpstreamBandwidthKbps()));
            }
            qxC.this.JTe.zyO(new dCD(zZm, z, this.zQM));
        }
    }

    public qxC(Context context, Lazy<RZN> lazy, WifiManager wifiManager, TelephonyManager telephonyManager, ConnectivityManager connectivityManager, AlexaClientEventBus alexaClientEventBus, Szi szi, gSO gso, AlexaHandsFreeDeviceInformation alexaHandsFreeDeviceInformation, DYu dYu, Dtt dtt) {
        ExecutorService newCachedThreadPool = ManagedExecutorFactory.newCachedThreadPool("connectivity-authority", 2);
        this.BIo = context;
        this.zQM = lazy;
        this.jiA = wifiManager;
        this.zyO = telephonyManager;
        this.Qle = connectivityManager;
        this.JTe = alexaClientEventBus;
        this.yPL = szi;
        this.LPk = newCachedThreadPool;
        this.Mlj = new NetworkRequest.Builder().addTransportType(0).addTransportType(1).build();
        this.zzR = dYu;
        this.HvC = null;
        this.lOf = dtt;
    }

    public void LPk() {
        Log.i(zZm, "Unregistering network changed callback");
        this.Qle.unregisterNetworkCallback(this);
        ManagedExecutorFactory.shutdown("connectivity-authority");
    }

    @Override // com.amazon.alexa.client.metrics.core.NetworkStateProvider
    @NonNull
    public String getNetworkType() {
        NetworkCapabilities networkCapabilities;
        if (Build.VERSION.SDK_INT >= 28) {
            Network activeNetwork = this.Qle.getActiveNetwork();
            return (activeNetwork == null || !jiA() || (networkCapabilities = this.Qle.getNetworkCapabilities(activeNetwork)) == null) ? NetworkService.NETWORK_NOT_CONNECTED : zZm(networkCapabilities);
        }
        NetworkInfo activeNetworkInfo = this.Qle.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return NetworkService.NETWORK_NOT_CONNECTED;
        }
        String typeName = activeNetworkInfo.getTypeName();
        String subtypeName = activeNetworkInfo.getSubtypeName();
        return (subtypeName == null || subtypeName.length() <= 0) ? typeName : GeneratedOutlineSupport1.outline75(typeName, " ", subtypeName);
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a3, code lost:
        if (r1 != 6) goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0075  */
    @Override // com.amazon.alexa.client.metrics.core.NetworkStateProvider
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getSignalStrength() {
        /*
            r8 = this;
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            int r1 = android.os.Build.VERSION.SDK_INT
            java.lang.String r2 = "WIFI"
            r3 = 1
            r4 = 28
            if (r1 < r4) goto L89
            android.net.ConnectivityManager r1 = r8.Qle
            android.net.Network r1 = r1.getActiveNetwork()
            if (r1 == 0) goto Lcc
            boolean r4 = r8.jiA()
            if (r4 == 0) goto Lcc
            android.net.ConnectivityManager r4 = r8.Qle
            android.net.NetworkCapabilities r1 = r4.getNetworkCapabilities(r1)
            java.lang.String r1 = r8.zZm(r1)
            r4 = -1
            int r5 = r1.hashCode()
            r6 = -851952246(0xffffffffcd383d8a, float:-1.93190048E8)
            r7 = 2
            if (r5 == r6) goto L50
            r6 = 83519902(0x4fa699e, float:5.887171E-36)
            if (r5 == r6) goto L46
            r6 = 809949500(0x3046d93c, float:7.234069E-10)
            if (r5 == r6) goto L3c
            goto L5a
        L3c:
            java.lang.String r5 = "Wi-Fi Aware"
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L5a
            r1 = r7
            goto L5b
        L46:
            java.lang.String r5 = "Wi-Fi"
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L5a
            r1 = r3
            goto L5b
        L50:
            java.lang.String r5 = "Cellular"
            boolean r1 = r1.equals(r5)
            if (r1 == 0) goto L5a
            r1 = 0
            goto L5b
        L5a:
            r1 = r4
        L5b:
            if (r1 == 0) goto L75
            if (r1 == r3) goto L62
            if (r1 == r7) goto L62
            goto Lcc
        L62:
            com.amazon.alexa.qxC$BIo r1 = new com.amazon.alexa.qxC$BIo
            android.net.wifi.WifiManager r3 = r8.jiA
            android.net.wifi.WifiInfo r3 = r3.getConnectionInfo()
            int r3 = r3.getRssi()
            r1.<init>(r2, r3)
            r0.add(r1)
            goto Lcc
        L75:
            boolean r1 = r8.JTe()
            if (r1 == 0) goto Lcc
            android.telephony.TelephonyManager r1 = r8.zyO
            java.util.List r1 = r1.getAllCellInfo()
            java.util.List r1 = r8.zZm(r1)
            r0.addAll(r1)
            goto Lcc
        L89:
            android.net.ConnectivityManager r1 = r8.Qle
            android.net.NetworkInfo r1 = r1.getActiveNetworkInfo()
            if (r1 == 0) goto Lcc
            boolean r4 = r1.isConnected()
            if (r4 == 0) goto Lcc
            int r1 = r1.getType()
            if (r1 == 0) goto Lb9
            if (r1 == r3) goto La6
            r3 = 4
            if (r1 == r3) goto Lb9
            r3 = 6
            if (r1 == r3) goto La6
            goto Lcc
        La6:
            com.amazon.alexa.qxC$BIo r1 = new com.amazon.alexa.qxC$BIo
            android.net.wifi.WifiManager r3 = r8.jiA
            android.net.wifi.WifiInfo r3 = r3.getConnectionInfo()
            int r3 = r3.getRssi()
            r1.<init>(r2, r3)
            r0.add(r1)
            goto Lcc
        Lb9:
            boolean r1 = r8.JTe()
            if (r1 == 0) goto Lcc
            android.telephony.TelephonyManager r1 = r8.zyO
            java.util.List r1 = r1.getAllCellInfo()
            java.util.List r1 = r8.zZm(r1)
            r0.addAll(r1)
        Lcc:
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.qxC.getSignalStrength():java.lang.String");
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onAvailable(Network network) {
        super.onAvailable(network);
        Log.i(zZm, "onAvailable: The network is available");
        zZm();
        this.HvC = network;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        Log.i(zZm, "onCapabilitiesChanged: The network capabilities have changed.");
        zZm();
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(Network network) {
        super.onLost(network);
        Log.i(zZm, "onLost: The network is lost");
        zZm();
        this.HvC = null;
    }

    public static boolean zZm(@Nullable Exception exc) {
        if (exc == null) {
            return false;
        }
        Throwable cause = exc.getCause();
        if (cause == null) {
            cause = exc;
        }
        return (exc instanceof SocketTimeoutException) || (exc instanceof StreamResetException) || (exc instanceof ConnectException) || (exc instanceof SSLHandshakeException) || (exc instanceof SSLPeerUnverifiedException) || (exc instanceof UnknownHostException) || (exc instanceof gmj.zQM) || (cause instanceof SocketTimeoutException) || (cause instanceof StreamResetException) || (cause instanceof gmj.zQM);
    }

    public Network BIo() {
        int i = Build.VERSION.SDK_INT;
        return this.Qle.getActiveNetwork();
    }

    @VisibleForTesting
    public boolean JTe() {
        return this.lOf.zZm(this.BIo, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    public final boolean Qle() {
        boolean z = true;
        if (Build.VERSION.SDK_INT >= 28) {
            Network activeNetwork = this.Qle.getActiveNetwork();
            if (activeNetwork == null) {
                Log.i(zZm, "No active network detected");
            }
            if (activeNetwork == null || !jiA()) {
                z = false;
            }
            this.dMe = z;
        } else {
            NetworkInfo activeNetworkInfo = this.Qle.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                Log.i(zZm, "No active network detected");
            }
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                z = false;
            }
            this.dMe = z;
        }
        return this.dMe;
    }

    @RequiresApi(api = 28)
    public final boolean jiA() {
        NetworkCapabilities networkCapabilities;
        Network activeNetwork = this.Qle.getActiveNetwork();
        if (activeNetwork != null && (networkCapabilities = this.Qle.getNetworkCapabilities(activeNetwork)) != null && networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(16)) {
            return networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(3) || networkCapabilities.hasTransport(2);
        }
        return false;
    }

    public boolean zQM() {
        if (!this.dMe) {
            return Qle();
        }
        return true;
    }

    public void zyO() {
        Log.i(zZm, "Registering network changed callback");
        this.Qle.registerNetworkCallback(this.Mlj, this);
    }

    public int BIo(NetworkCapabilities networkCapabilities) {
        return networkCapabilities.getLinkUpstreamBandwidthKbps();
    }

    public void zZm(boolean z, DialogRequestIdentifier dialogRequestIdentifier, Network network) {
        Future<?> future = this.uzr;
        if (future == null || future.isDone()) {
            this.uzr = this.LPk.submit(new zZm(z, dialogRequestIdentifier, network));
        }
    }

    public final void zZm() {
        boolean z = this.dMe;
        Qle();
        if (this.dMe != z) {
            if (this.dMe) {
                this.JTe.zyO(new DqD());
            } else {
                this.JTe.zyO(new cfI());
            }
        }
    }

    @VisibleForTesting
    public List<BIo> zZm(@Nullable List<CellInfo> list) {
        LinkedList linkedList = new LinkedList();
        if (list == null) {
            return linkedList;
        }
        for (CellInfo cellInfo : list) {
            if (cellInfo.isRegistered()) {
                if (cellInfo instanceof CellInfoGsm) {
                    linkedList.add(zZm("GSM", ((CellInfoGsm) cellInfo).getCellSignalStrength()));
                } else if (cellInfo instanceof CellInfoLte) {
                    linkedList.add(zZm("LTE", ((CellInfoLte) cellInfo).getCellSignalStrength()));
                } else if (cellInfo instanceof CellInfoCdma) {
                    linkedList.add(zZm("CDMA", ((CellInfoCdma) cellInfo).getCellSignalStrength()));
                } else if (cellInfo instanceof CellInfoWcdma) {
                    linkedList.add(zZm("WCDMA", ((CellInfoWcdma) cellInfo).getCellSignalStrength()));
                }
            }
        }
        return linkedList;
    }

    public final BIo zZm(String str, CellSignalStrength cellSignalStrength) {
        return new BIo(str, cellSignalStrength.getDbm());
    }

    public final String zZm(NetworkCapabilities networkCapabilities) {
        return networkCapabilities.hasTransport(0) ? "Cellular" : networkCapabilities.hasTransport(1) ? Radio.WIFI : networkCapabilities.hasTransport(2) ? "Bluetooth" : networkCapabilities.hasTransport(3) ? "Ethernet" : networkCapabilities.hasTransport(4) ? "VPN" : networkCapabilities.hasTransport(5) ? "Wi-Fi Aware" : networkCapabilities.hasTransport(6) ? "LoWPAN" : NetworkService.NETWORK_NOT_CONNECTED;
    }

    public boolean zZm(Network network, Network network2) {
        return !Objects.equals(network, network2);
    }

    public boolean zZm(Future<Boolean> future) {
        try {
            return future.get().booleanValue();
        } catch (InterruptedException | ExecutionException unused) {
            Log.i(zZm, "Failed to send ping to check network connectivity");
            return false;
        }
    }
}
