package com.amazon.device.messaging;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.util.Log;
import com.amazon.device.messaging.ADMConstants;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public abstract class ADMMessageHandlerBase extends IntentService {
    private static final Object[] LOCK = new Object[0];
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static PowerManager.WakeLock sWakeLock;

    /* JADX INFO: Access modifiers changed from: protected */
    @FireOsSdk
    public ADMMessageHandlerBase(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void acquireWakeLock(Context context) {
        synchronized (LOCK) {
            if (sWakeLock == null) {
                sWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, String.format("ADM in %s", context.getPackageName()));
            }
            sWakeLock.acquire();
        }
    }

    private void handleNewRegistrationID(Intent intent) {
        onRegistered(intent.getStringExtra(ADMConstants.LowLevel.EXTRA_REGISTRATION_ID));
    }

    private void handleRegistrationChange(Intent intent) {
        if (intent.hasExtra("error")) {
            String stringExtra = intent.getStringExtra("error");
            if (intent.hasExtra("error_description")) {
                Log.e("ADM", String.format("ADM Error %s - %s", stringExtra, intent.getStringExtra("error_description")));
            }
            handleRegistrationError(stringExtra);
        } else if (intent.hasExtra(ADMConstants.LowLevel.EXTRA_UNREGISTERED)) {
            handleUnregister(intent);
        } else if (intent.hasExtra(ADMConstants.LowLevel.EXTRA_REGISTRATION_ID)) {
            handleNewRegistrationID(intent);
        } else {
            handleRegistrationError(ADMConstants.ERROR_SERVICE_NOT_AVAILABLE);
        }
    }

    private void handleRegistrationError(String str) {
        onRegistrationError(str);
    }

    private void handleUnregister(Intent intent) {
        onUnregistered(intent.getStringExtra(ADMConstants.LowLevel.EXTRA_OLD_REGISTRATION_ID));
    }

    private static void releaseWakeLock() {
        sHandler.postDelayed(new Runnable() { // from class: com.amazon.device.messaging.ADMMessageHandlerBase.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (ADMMessageHandlerBase.LOCK) {
                    if (ADMMessageHandlerBase.sWakeLock != null && ADMMessageHandlerBase.sWakeLock.isHeld()) {
                        ADMMessageHandlerBase.sWakeLock.release();
                    }
                }
            }
        }, TimeUnit.MILLISECONDS.convert(10L, TimeUnit.SECONDS));
    }

    @Override // android.app.IntentService
    @FireOsSdk
    protected final void onHandleIntent(Intent intent) {
        try {
            try {
                if (ADMConstants.LowLevel.ACTION_APP_REGISTRATION_EVENT.equals(intent.getAction())) {
                    handleRegistrationChange(intent);
                } else if (ADMConstants.LowLevel.ACTION_RECEIVE_ADM_MESSAGE.equals(intent.getAction())) {
                    onMessage(intent);
                }
            } catch (NullPointerException unused) {
                Log.i("ADM", "ADM message handler invoked with null intent");
            }
        } finally {
            releaseWakeLock();
        }
    }

    @FireOsSdk
    protected abstract void onMessage(Intent intent);

    @FireOsSdk
    protected abstract void onRegistered(String str);

    @FireOsSdk
    protected abstract void onRegistrationError(String str);

    @FireOsSdk
    protected abstract void onUnregistered(String str);
}
