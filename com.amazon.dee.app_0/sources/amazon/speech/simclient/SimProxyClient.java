package amazon.speech.simclient;

import android.content.Context;
import android.os.RemoteException;
import android.os.Trace;
import com.amazon.metrics.MetricsUtil;
/* loaded from: classes.dex */
public class SimProxyClient extends SimClient {
    private static final String TAG = "SimProxyClient";

    protected SimProxyClient(Context context, IConnectionListener iConnectionListener) {
        this(context, iConnectionListener, MetricsUtil.getInstance());
    }

    public static boolean createClient(Context context, IConnectionListener iConnectionListener) {
        return createClient(context, iConnectionListener, MetricsUtil.getInstance());
    }

    public int cancelSpeechDialogue() {
        if (this.mService == null) {
            return SimError.SIM_CONNECTION_UNAVAILABLE;
        }
        try {
            Trace.beginSection("cancelCurrentSpeechDialogue");
            this.mService.cancelSpeechDialogue(null);
            Trace.endSection();
            return 0;
        } catch (RemoteException unused) {
            Trace.endSection();
            return SimError.SIM_COMMUNICATION_FAILURE;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public int routeDirective(String str) {
        if (this.mService == null) {
            return SimError.SIM_CONNECTION_UNAVAILABLE;
        }
        try {
            Trace.beginSection("routeDirective");
            this.mService.routeProxyDirective(str);
            Trace.endSection();
            return 0;
        } catch (RemoteException unused) {
            Trace.endSection();
            return SimError.SIM_COMMUNICATION_FAILURE;
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    SimProxyClient(Context context, IConnectionListener iConnectionListener, MetricsUtil metricsUtil) {
        super(context, iConnectionListener, metricsUtil);
    }

    static boolean createClient(Context context, IConnectionListener iConnectionListener, MetricsUtil metricsUtil) {
        if (iConnectionListener != null) {
            return context.bindService(SimClient.getBindIntent(context), new SimProxyClient(context, iConnectionListener, metricsUtil).mServiceConnection, 1);
        }
        throw new IllegalArgumentException("Listener cannot be null");
    }
}
