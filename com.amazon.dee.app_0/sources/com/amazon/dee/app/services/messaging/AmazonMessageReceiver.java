package com.amazon.dee.app.services.messaging;

import com.amazon.dee.app.jobs.StaticJobIDProvider;
import com.amazon.device.messaging.ADMMessageReceiver;
/* loaded from: classes12.dex */
public class AmazonMessageReceiver extends ADMMessageReceiver {
    private static final int JOB_ID = StaticJobIDProvider.getJobIdentification(AmazonMessageReceiver.class);

    public AmazonMessageReceiver() {
        super(AmazonMessageHandler.class);
        if (supportsLatestADMService()) {
            registerJobServiceClass(PhoenixMessageHandler.class, JOB_ID);
        }
    }

    private boolean supportsLatestADMService() {
        try {
            Class.forName("com.amazon.device.messaging.ADMMessageHandlerJobBase");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
