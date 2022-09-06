package com.amazon.alexa.alertsca;

import com.amazon.alexa.alertsca.events.AlertCleanUpEvent;
import com.amazon.alexa.alertsca.events.AlertNotificationEvent;
import com.amazon.alexa.alertsca.events.AlertScheduledEvent;
import com.amazon.alexa.alertsca.events.AlertStartedEvent;
import com.amazon.alexa.alertsca.events.AlertStoppedEvent;
import com.amazon.alexa.alertsca.events.AlertUnscheduledEvent;
import com.amazon.alexa.alertsca.events.NetworkConnectivityEvent;
import com.amazon.alexa.alertsca.notification.AlexaAlertsNotificationManager;
import java.util.HashMap;
import java.util.Map;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.meta.SimpleSubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;
import org.greenrobot.eventbus.meta.SubscriberMethodInfo;
/* loaded from: classes6.dex */
public class AlexaEventBusIndex implements SubscriberInfoIndex {
    private static final Map<Class<?>, SubscriberInfo> SUBSCRIBER_INDEX = new HashMap();

    static {
        putIndex(new SimpleSubscriberInfo(AlertsAuthority.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", AlertStartedEvent.class), new SubscriberMethodInfo("on", AlertStoppedEvent.class)}));
        putIndex(new SimpleSubscriberInfo(ServiceForegroundWatcher.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", AlertNotificationEvent.class), new SubscriberMethodInfo("on", AlertStoppedEvent.class), new SubscriberMethodInfo("on", AlertUnscheduledEvent.class)}));
        putIndex(new SimpleSubscriberInfo(AlertInteraction.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", AlertUnscheduledEvent.class), new SubscriberMethodInfo("on", NetworkConnectivityEvent.class, ThreadMode.POSTING, 0, true)}));
        putIndex(new SimpleSubscriberInfo(AlertsCapabilityAgentService.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", AlertScheduledEvent.class), new SubscriberMethodInfo("on", AlertUnscheduledEvent.class)}));
        putIndex(new SimpleSubscriberInfo(AlexaAlertsNotificationManager.class, true, new SubscriberMethodInfo[]{new SubscriberMethodInfo("on", AlertScheduledEvent.class), new SubscriberMethodInfo("on", AlertUnscheduledEvent.class), new SubscriberMethodInfo("on", AlertStartedEvent.class), new SubscriberMethodInfo("on", AlertStoppedEvent.class), new SubscriberMethodInfo("on", AlertCleanUpEvent.class)}));
    }

    private static void putIndex(SubscriberInfo subscriberInfo) {
        SUBSCRIBER_INDEX.put(subscriberInfo.getSubscriberClass(), subscriberInfo);
    }

    @Override // org.greenrobot.eventbus.meta.SubscriberInfoIndex
    public SubscriberInfo getSubscriberInfo(Class<?> cls) {
        SubscriberInfo subscriberInfo = SUBSCRIBER_INDEX.get(cls);
        if (subscriberInfo != null) {
            return subscriberInfo;
        }
        return null;
    }
}
