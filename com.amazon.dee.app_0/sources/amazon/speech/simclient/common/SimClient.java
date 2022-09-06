package amazon.speech.simclient.common;

import android.content.Context;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class SimClient {
    private static final String TAG = "SimClient";
    private final Map<SimClientServiceHandle, BaseClient> mRegisteredClients = new HashMap();

    /* loaded from: classes.dex */
    public static class ServiceNotAvailableException extends Exception {
        public ServiceNotAvailableException() {
            super("This service is not available on this device");
        }
    }

    public synchronized <T extends BaseClient> T get(SimClientServiceHandle<T> simClientServiceHandle) {
        return (T) this.mRegisteredClients.get(simClientServiceHandle);
    }

    public synchronized <T extends BaseClient> void registerClient(Context context, SimClientServiceHandle<T> simClientServiceHandle) throws ServiceNotAvailableException {
        if (this.mRegisteredClients.containsKey(simClientServiceHandle)) {
            Log.w(TAG, "Client already registered, ignoring registration request");
            return;
        }
        if (context.getPackageManager() != null && !simClientServiceHandle.serviceExists(context)) {
            throw new ServiceNotAvailableException();
        }
        this.mRegisteredClients.put(simClientServiceHandle, simClientServiceHandle.mo50createClient(context));
    }

    public synchronized void teardown() {
        for (BaseClient baseClient : this.mRegisteredClients.values()) {
            baseClient.teardown();
        }
        this.mRegisteredClients.clear();
    }
}
