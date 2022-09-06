package com.amazon.dee.app.services.tcomm;

import amazon.communication.CommunicationFactory;
import amazon.communication.GatewayConnectivity;
import amazon.communication.TCommServiceDownException;
import android.content.Context;
/* loaded from: classes12.dex */
public class GatewayConnectivityFactory {
    private static final String TAG = "GatewayConnectivityFactory";

    /* JADX INFO: Access modifiers changed from: package-private */
    public GatewayConnectivity createGatewayConnectivity(Context context) {
        try {
            return CommunicationFactory.getGatewayConnectivity(context);
        } catch (TCommServiceDownException e) {
            e.getMessage();
            return null;
        }
    }
}
