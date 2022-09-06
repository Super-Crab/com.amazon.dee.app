package com.amazon.dee.app.services.tcomm;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.Publisher;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.dee.app.BuildConfig;
import com.amazon.dee.app.framework.AlexaApplication;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.core.tcomm.AbstractTCommService;
/* loaded from: classes12.dex */
public class TCommService extends AbstractTCommService {
    private static final String TAG = Log.tag(TCommService.class);
    private static volatile boolean receivedTCommIntent = false;
    private static volatile boolean startRequested = false;
    private Publisher publisher;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initialize(Context context) {
        try {
            AbstractTCommService.start(TCommService.class, context);
        } catch (IllegalStateException unused) {
            Log.e(TAG, "illegal state occurred. won't start TComm");
        }
    }

    public static boolean isConnected() {
        return AbstractTCommService.isConnected();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void receivedTCommIntent(Context context) {
        receivedTCommIntent = true;
        if (startRequested) {
            start(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void start(Context context) {
        DeviceInformation deviceInformation = (DeviceInformation) ComponentRegistry.getInstance().getLazy(DeviceInformation.class).mo10268get();
        if (!receivedTCommIntent && !deviceInformation.isFireOS()) {
            startRequested = true;
        } else {
            initialize(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void stop(Context context) {
        AbstractTCommService.stop(TCommService.class, context);
    }

    @Override // com.amazon.dee.core.tcomm.AbstractTCommService
    public String getBuildConfigStage() {
        return BuildConfig.STAGE;
    }

    @Override // com.amazon.dee.core.tcomm.AbstractTCommService
    public synchronized Publisher getPublisher() {
        if (this.publisher == null) {
            this.publisher = ((AlexaApplication) getApplication()).getComponent().eventBus().getPublisher(false);
        }
        return this.publisher;
    }
}
