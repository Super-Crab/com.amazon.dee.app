package com.amazon.communication.devicetodevice;

import amazon.communication.Message;
import amazon.communication.identity.EndpointIdentity;
import android.content.Context;
import android.content.Intent;
import com.amazon.communication.MessageEnvelope;
import com.amazon.communication.ProtocolException;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.MAPAccountManager;
/* loaded from: classes12.dex */
public class IntentLaunchingD2DNotificationRouter implements D2DNotificationRouter {
    private static final String INTENT_ACTION = "amazon.communication.devicetodevice.NOTIFICATION";
    private static final String ORIGINAL_CHANNEL_EXTRA = "amazon.communication.devicetodevice.NOTIFICATION_ORIGINAL_CHANNEL";
    private static final String PAYLOAD_EXTRA = "amazon.communication.devicetodevice.NOTIFICATION_PAYLOAD";
    private static final String SOURCE_APP_EXTRA = "amazon.communication.devicetodevice.NOTIFICATION_SOURCE_APPLICATION";
    private static final String SOURCE_IDENTITY_EXTRA = "amazon.communication.devicetodevice.NOTIFICATION_ENDPOINT_IDENTITY";
    private static final String TARGET_APP_EXTRA = "amazon.communication.devicetodevice.NOTIFICATION_TARGET_APPLICATION";
    private static final DPLogger log = new DPLogger("TComm.IntentLaunchingD2DNotificationRouter");
    private final Context mContext;

    public IntentLaunchingD2DNotificationRouter(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.communication.devicetodevice.D2DNotificationRouter
    public void routeMessageAsNotification(EndpointIdentity endpointIdentity, String str, Message message, String str2, int i) {
        Intent intent = new Intent();
        intent.setAction(str2 + "." + INTENT_ACTION);
        intent.putExtra(SOURCE_IDENTITY_EXTRA, endpointIdentity.toString());
        intent.putExtra(SOURCE_APP_EXTRA, str);
        intent.putExtra(TARGET_APP_EXTRA, str2);
        intent.putExtra(ORIGINAL_CHANNEL_EXTRA, i);
        intent.putExtra(PAYLOAD_EXTRA, MessageEnvelope.createInstance(message));
        log.debug("routeMessageAsNotification", "going to send intent", MAPAccountManager.KEY_INTENT, intent);
        this.mContext.sendBroadcast(intent);
        log.debug("routeMessageAsNotification", "done sending intent", new Object[0]);
    }

    @Override // com.amazon.communication.devicetodevice.D2DNotificationRouter
    public void routeNotificationMessage(D2DMessage d2DMessage) throws ProtocolException {
        log.debug("routeNotificationMessage", "routing D2D notification", new Object[0]);
        EndpointIdentity endpointIdentity = d2DMessage.origin;
        if (endpointIdentity != null) {
            String str = d2DMessage.originApp;
            if (str != null) {
                String str2 = d2DMessage.destinationApp;
                if (str2 != null) {
                    Intent intent = new Intent();
                    intent.setAction(str2 + "." + INTENT_ACTION);
                    intent.putExtra(SOURCE_IDENTITY_EXTRA, endpointIdentity.toString());
                    intent.putExtra(SOURCE_APP_EXTRA, str);
                    intent.putExtra(TARGET_APP_EXTRA, str2);
                    intent.putExtra(PAYLOAD_EXTRA, MessageEnvelope.createInstance(d2DMessage.message));
                    log.debug("routeNotificationMessage", "going to send intent", MAPAccountManager.KEY_INTENT, intent);
                    this.mContext.sendBroadcast(intent);
                    log.debug("routeNotificationMessage", "done sending intent", new Object[0]);
                    return;
                }
                throw new ProtocolException("destination app on D2D message was null");
            }
            throw new ProtocolException("origin app on D2D message was null");
        }
        throw new ProtocolException("identity on D2D message was null");
    }
}
