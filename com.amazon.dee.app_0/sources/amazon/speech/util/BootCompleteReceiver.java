package amazon.speech.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/* loaded from: classes.dex */
public class BootCompleteReceiver<T> extends BroadcastReceiver {
    private static final boolean DEBUG = false;
    private static final String TAG = BootCompleteReceiver.class.getName();
    private final Class<? extends T> mServiceClass;

    public BootCompleteReceiver(Class<? extends T> cls) {
        this.mServiceClass = cls;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context != null && intent != null) {
            if (!intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
                return;
            }
            context.startService(new Intent(context, this.mServiceClass));
            return;
        }
        Log.e(TAG, "Invalid onReceive parameters, aborting");
    }
}
