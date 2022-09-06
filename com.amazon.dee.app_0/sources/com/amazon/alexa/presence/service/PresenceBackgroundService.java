package com.amazon.alexa.presence.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.amazon.alexa.presence.bleconn.identity.IdentityController;
import com.amazon.alexa.presence.bleconn.service.BleConnIdentityComponent;
import com.amazon.alexa.presence.logging.PresenceSlf4jAndroidLoggerFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.slf4j.impl.AlexaSlf4jAndroidLoggerFactory;
/* loaded from: classes9.dex */
public class PresenceBackgroundService extends IntentService {
    private static final String APP_PACKAGE_NAME = "com.amazon.dee.app";
    private static final String INTENT_REFRESH_IDENTITY_ACTION = "com.amazon.alexa.intent.action.PRESENCE_REFRESH_IDENTITY";
    private static final String SERVICE_NAME = "PresenceBackgroundService";
    private static final String TAG = PresenceBackgroundService.class.getName();
    private IdentityController identityController;

    static {
        AlexaSlf4jAndroidLoggerFactory.subscribe(PresenceSlf4jAndroidLoggerFactory.DEFAULT);
    }

    public PresenceBackgroundService() {
        super(SERVICE_NAME);
    }

    public static Intent forceRefreshIdentityIntent(Context context) {
        Intent refreshIdentityIntent = refreshIdentityIntent(context);
        refreshIdentityIntent.putExtra(PresenceJobService.ACTION_REFRESH_FLUSH_KEY, true);
        return refreshIdentityIntent;
    }

    public static Intent refreshIdentityIntent(Context context) {
        Intent intent = new Intent(context, PresenceBackgroundService.class);
        intent.setAction("com.amazon.alexa.intent.action.PRESENCE_REFRESH_IDENTITY");
        intent.setPackage("com.amazon.dee.app");
        return intent;
    }

    @Override // android.app.IntentService, android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.identityController = new BleConnIdentityComponent(getApplicationContext()).getIdentityController();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received action: ");
        outline107.append(intent.getAction());
        outline107.toString();
        if (!"com.amazon.alexa.intent.action.PRESENCE_REFRESH_IDENTITY".equals(intent.getAction())) {
            return;
        }
        if (intent.getBooleanExtra(PresenceJobService.ACTION_REFRESH_FLUSH_KEY, false)) {
            this.identityController.refresh(true);
        } else {
            this.identityController.refresh(false);
        }
    }
}
