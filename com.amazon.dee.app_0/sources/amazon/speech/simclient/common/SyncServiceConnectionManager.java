package amazon.speech.simclient.common;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
/* loaded from: classes.dex */
public class SyncServiceConnectionManager extends ServiceConnectionManager {
    @Override // amazon.speech.simclient.common.ServiceConnectionManager
    protected void performBind(Context context, ServiceConnection serviceConnection, Intent intent, String str) {
        bindComplete(ServiceConnectionManager.bindCommon(context, serviceConnection, intent, str));
    }

    @Override // amazon.speech.simclient.common.ServiceConnectionManager
    protected void performUnbind(Context context, ServiceConnection serviceConnection) {
        ServiceConnectionManager.unbindCommon(context, serviceConnection);
        unbindComplete();
    }
}
