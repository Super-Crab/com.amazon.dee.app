package com.amazon.communication;

import amazon.communication.identity.DeviceIdentity;
import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.ServiceIdentity;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public class ChannelRestrictor {
    private static final DPLogger log = new DPLogger();
    private Map<Integer, List<EndpointIdentity>> mChannelRestrictionMap;

    public ChannelRestrictor(Map<Integer, List<EndpointIdentity>> map) {
        this.mChannelRestrictionMap = map;
        if (this.mChannelRestrictionMap == null) {
            this.mChannelRestrictionMap = new HashMap();
        }
    }

    public boolean isAuthorized(int i, EndpointIdentity endpointIdentity) {
        List<EndpointIdentity> list = this.mChannelRestrictionMap.get(Integer.valueOf(i));
        if (list == null) {
            return true;
        }
        if (endpointIdentity instanceof ServiceIdentity) {
            String serviceName = ((ServiceIdentity) endpointIdentity).getServiceName();
            for (EndpointIdentity endpointIdentity2 : list) {
                FailFast.expectTrue(endpointIdentity2 instanceof ServiceIdentity);
                if (((ServiceIdentity) endpointIdentity2).getServiceName().equals(serviceName)) {
                    log.verbose("isAuthorized", "sender authorized to send on channel", "sender", EndpointIdentity.logSafe(endpointIdentity), "channel", Integer.valueOf(i));
                    return true;
                }
            }
            log.info("isAuthorized", "unauthorized service on restricted channel", "source", EndpointIdentity.logSafe(endpointIdentity), "sourceService", serviceName, "channel", Integer.valueOf(i));
            return false;
        } else if (endpointIdentity instanceof DeviceIdentity) {
            log.info("isAuthorized", "unauthorized device on restricted channel", "source", EndpointIdentity.logSafe(endpointIdentity), "channel", Integer.valueOf(i));
            return false;
        } else {
            log.info("isAuthorized", "unauthorized source type on restricted channel", "source", EndpointIdentity.logSafe(endpointIdentity), "channel", Integer.valueOf(i));
            return false;
        }
    }
}
