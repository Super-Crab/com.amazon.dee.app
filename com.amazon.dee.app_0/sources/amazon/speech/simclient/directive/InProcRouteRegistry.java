package amazon.speech.simclient.directive;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class InProcRouteRegistry {
    private static final String TAG = GeneratedOutlineSupport1.outline39(InProcRouteRegistry.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private static final InProcRouteRegistry gInstance = new InProcRouteRegistry();
    private final Map<String, IntentHandler> mServiceRoutes = new HashMap();

    /* loaded from: classes.dex */
    public interface IntentHandler {
        void handle(Intent intent);
    }

    private InProcRouteRegistry() {
    }

    public static InProcRouteRegistry getInstance() {
        return gInstance;
    }

    public synchronized boolean handleServiceIntent(Intent intent) {
        if (intent == null) {
            return false;
        }
        ComponentName component = intent.getComponent();
        if (component == null) {
            return false;
        }
        String className = component.getClassName();
        if (className == null) {
            return false;
        }
        IntentHandler intentHandler = this.mServiceRoutes.get(className);
        if (intentHandler == null) {
            String str = "No InProc ServiceRoute for " + className;
            return false;
        }
        Log.i(TAG, String.format("Handling intent using InProcServiceRoute: %s -> %s", className, intentHandler));
        intentHandler.handle(intent);
        return true;
    }

    public synchronized void registerServiceRoute(String str, IntentHandler intentHandler) {
        Log.i(TAG, String.format("Registering InProcServiceRoute: %s -> %s", str, intentHandler));
        this.mServiceRoutes.put(str, intentHandler);
    }

    public synchronized void unregisterServiceRoute(String str) {
        Log.i(TAG, String.format("Unregistering InProcServiceRoute for %s", str));
        this.mServiceRoutes.remove(str);
    }
}
