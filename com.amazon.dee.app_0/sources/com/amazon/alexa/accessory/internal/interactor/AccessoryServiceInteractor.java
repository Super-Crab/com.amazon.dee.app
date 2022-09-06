package com.amazon.alexa.accessory.internal.interactor;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryService;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.Interactor;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
/* loaded from: classes.dex */
public class AccessoryServiceInteractor implements Interactor {
    private final Context context;
    private final FeatureChecker featureChecker;
    private final AccessorySessionListener sessionListener;
    private final SessionSupplier sessionSupplier;

    /* loaded from: classes.dex */
    private class SessionListener extends AccessorySessionListener {
        private SessionListener() {
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionConnected(Accessory accessory) {
            if (!AccessoryServiceInteractor.this.featureChecker.hasAccess(AccessoryFeature.ACCESSORY_SERVICE_DEPRECATION) && AccessoryServiceInteractor.this.getConnectedSessionsCount() == 1) {
                int i = Build.VERSION.SDK_INT;
                AccessoryServiceInteractor.this.context.startForegroundService(new Intent(AccessoryServiceInteractor.this.context, AccessoryService.class));
            }
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionReleased(Accessory accessory) {
            if (!AccessoryServiceInteractor.this.featureChecker.hasAccess(AccessoryFeature.ACCESSORY_SERVICE_DEPRECATION) && AccessoryServiceInteractor.this.getConnectedSessionsCount() == 0) {
                AccessoryServiceInteractor.this.context.stopService(new Intent(AccessoryServiceInteractor.this.context, AccessoryService.class));
            }
        }
    }

    public AccessoryServiceInteractor(SessionSupplier sessionSupplier, Context context, FeatureChecker featureChecker) {
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(context, "context");
        this.sessionSupplier = sessionSupplier;
        this.context = context;
        this.sessionListener = new SessionListener();
        this.featureChecker = featureChecker;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getConnectedSessionsCount() {
        int i = 0;
        for (AccessorySession accessorySession : this.sessionSupplier.getActiveSessions()) {
            if (accessorySession.isConnected()) {
                i++;
            }
        }
        return i;
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void activate() {
        this.sessionSupplier.addSessionListener(this.sessionListener);
    }

    @Override // com.amazon.alexa.accessory.Interactor
    public void deactivate() {
        this.sessionSupplier.removeSessionListener(this.sessionListener);
    }
}
