package amazon.speech.simclient;

import amazon.speech.model.Event;
import amazon.speech.model.EventEnvelope;
import amazon.speech.model.IEventCallback;
import amazon.speech.model.Payload;
import android.content.Context;
import android.os.RemoteException;
import android.os.Trace;
import android.util.Log;
import com.amazon.metrics.MetricsUtil;
/* loaded from: classes.dex */
public class SimSmartHomeClient extends SimClient {
    private static final String TAG = "SimSmartHomeClient";

    SimSmartHomeClient(Context context, IConnectionListener iConnectionListener, MetricsUtil metricsUtil) {
        super(context, iConnectionListener, metricsUtil);
    }

    public static boolean createClient(Context context, IConnectionListener iConnectionListener) {
        return createClient(context, iConnectionListener, MetricsUtil.getInstance());
    }

    public int sendEvent(String str, String str2, Payload payload, String str3, IEventCallback iEventCallback) {
        return sendEvent(new Event(str, str2, payload), iEventCallback, str3);
    }

    static boolean createClient(Context context, IConnectionListener iConnectionListener, MetricsUtil metricsUtil) {
        if (iConnectionListener != null) {
            return context.bindService(SimClient.getBindIntent(context), new SimSmartHomeClient(context, iConnectionListener, metricsUtil).mServiceConnection, 1);
        }
        throw new IllegalArgumentException("Listener cannot be null");
    }

    public int sendEvent(Event event, IEventCallback iEventCallback, String str) {
        if (str != null) {
            try {
                if (this.mService == null) {
                    return SimError.SIM_CONNECTION_UNAVAILABLE;
                }
                try {
                    Trace.beginSection("sendEvent");
                    EventEnvelope eventEnvelope = new EventEnvelope(event);
                    eventEnvelope.getExtra().putString(EventEnvelope.EVENT_URI_KEY, str);
                    this.mService.sendEventEnvelope(eventEnvelope, iEventCallback);
                    Trace.endSection();
                    return 0;
                } catch (RemoteException e) {
                    Log.e(TAG, "RemoteException on sendEvent", e);
                    recordCommFailureMetric("RemoteException on sendEvent: " + e.toString());
                    Trace.endSection();
                    return SimError.SIM_COMMUNICATION_FAILURE;
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        throw new IllegalArgumentException();
    }
}
