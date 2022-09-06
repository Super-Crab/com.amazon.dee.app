package com.amazon.alexa;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.utils.validation.Preconditions;
import com.google.auto.value.AutoValue;
/* compiled from: AttachmentTimeoutsConfiguration.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class ojb {
    public static ojb zZm(ClientConfiguration clientConfiguration, boolean z) {
        Long networkTotalWriteTimeout;
        Preconditions.notNull(clientConfiguration, "config is null");
        if (z) {
            networkTotalWriteTimeout = clientConfiguration.getFirstTurnNetworkTotalWriteTimeout();
        } else {
            networkTotalWriteTimeout = clientConfiguration.getNetworkTotalWriteTimeout();
        }
        return new RzL(networkTotalWriteTimeout.longValue(), clientConfiguration.getNetworkWriteBytesTimeout().longValue(), clientConfiguration.getMaxUtteranceDuration().longValue());
    }

    public abstract long zZm();
}
