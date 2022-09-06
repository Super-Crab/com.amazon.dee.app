package com.amazon.deecomms.accessories.monitors;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.monitor.MessageNotificationStateMonitor;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public class AlexaMessageNotificationMonitor implements MessageNotificationStateMonitor {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AlexaMessageNotificationMonitor.class);
    private static final Set<MessageNotificationStateMonitor.Observer> observers = new HashSet();
    private final Context mContext;

    public AlexaMessageNotificationMonitor(@NonNull Context context) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.mContext = context;
    }

    public static void onMessageNotificationStatusChanged(boolean z) {
        HashSet<MessageNotificationStateMonitor.Observer> hashSet;
        GeneratedOutlineSupport.outline5("Accessory message notification status changed to ", z, LOG);
        synchronized (observers) {
            hashSet = new HashSet(observers);
        }
        for (MessageNotificationStateMonitor.Observer observer : hashSet) {
            observer.onUnreadMessageStatusChanged(z);
        }
    }

    @Override // com.amazon.alexa.accessory.monitor.MessageNotificationStateMonitor
    public void addObserver(MessageNotificationStateMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        synchronized (observers) {
            observers.add(observer);
        }
    }

    @Override // com.amazon.alexa.accessory.monitor.MessageNotificationStateMonitor
    public boolean isUnreadMessageAvailable() {
        LOG.i("Inside isUnreadMessageAvailable");
        return CommsDaggerWrapper.getComponent().getConversationMessageTracker().hasUnreadMessages();
    }

    @Override // com.amazon.alexa.accessory.monitor.MessageNotificationStateMonitor
    public void removeObserver(MessageNotificationStateMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        synchronized (observers) {
            observers.remove(observer);
        }
    }
}
