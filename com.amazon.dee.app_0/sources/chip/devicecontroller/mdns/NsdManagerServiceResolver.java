package chip.devicecontroller.mdns;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class NsdManagerServiceResolver implements ServiceResolver {
    private static final long RESOLVE_SERVICE_TIMEOUT = 30000;
    private static final String TAG = "NsdManagerServiceResolver";
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private WifiManager.MulticastLock multicastLock;
    private final NsdManager nsdManager;

    public NsdManagerServiceResolver(Context context) {
        this.nsdManager = (NsdManager) context.getSystemService("servicediscovery");
        this.multicastLock = ((WifiManager) context.getSystemService("wifi")).createMulticastLock("chipMulticastLock");
        this.multicastLock.setReferenceCounted(true);
    }

    @Override // chip.devicecontroller.mdns.ServiceResolver
    public void resolve(final String str, final String str2, final long j, final long j2, final ChipMdnsCallback chipMdnsCallback) {
        this.multicastLock.acquire();
        NsdServiceInfo nsdServiceInfo = new NsdServiceInfo();
        nsdServiceInfo.setServiceName(str);
        nsdServiceInfo.setServiceType(str2);
        String str3 = "Starting service resolution for '" + str + "'";
        final Runnable runnable = new Runnable() { // from class: chip.devicecontroller.mdns.NsdManagerServiceResolver.1
            @Override // java.lang.Runnable
            public void run() {
                if (NsdManagerServiceResolver.this.multicastLock.isHeld()) {
                    NsdManagerServiceResolver.this.multicastLock.release();
                }
            }
        };
        this.nsdManager.resolveService(nsdServiceInfo, new NsdManager.ResolveListener() { // from class: chip.devicecontroller.mdns.NsdManagerServiceResolver.2
            @Override // android.net.nsd.NsdManager.ResolveListener
            public void onResolveFailed(NsdServiceInfo nsdServiceInfo2, int i) {
                String str4 = NsdManagerServiceResolver.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to resolve service '");
                outline107.append(nsdServiceInfo2.getServiceName());
                outline107.append("': ");
                outline107.append(i);
                Log.w(str4, outline107.toString());
                chipMdnsCallback.handleServiceResolve(str, str2, null, 0, j, j2);
                if (NsdManagerServiceResolver.this.multicastLock.isHeld()) {
                    NsdManagerServiceResolver.this.multicastLock.release();
                }
                NsdManagerServiceResolver.this.mainThreadHandler.removeCallbacks(runnable);
            }

            @Override // android.net.nsd.NsdManager.ResolveListener
            public void onServiceResolved(NsdServiceInfo nsdServiceInfo2) {
                String str4 = NsdManagerServiceResolver.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Resolved service '");
                outline107.append(nsdServiceInfo2.getServiceName());
                outline107.append("' to ");
                outline107.append(nsdServiceInfo2.getHost());
                Log.i(str4, outline107.toString());
                chipMdnsCallback.handleServiceResolve(str, str2, nsdServiceInfo2.getHost().getHostAddress(), nsdServiceInfo2.getPort(), j, j2);
                if (NsdManagerServiceResolver.this.multicastLock.isHeld()) {
                    NsdManagerServiceResolver.this.multicastLock.release();
                }
                NsdManagerServiceResolver.this.mainThreadHandler.removeCallbacks(runnable);
            }
        });
        this.mainThreadHandler.postDelayed(runnable, 30000L);
    }
}
