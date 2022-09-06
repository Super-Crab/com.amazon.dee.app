package com.amazon.deecomms.nativemodules;

import com.amazon.deecomms.notifications.util.NotificationUtils;
import com.amazon.deecomms.util.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.nativemodules.-$$Lambda$NotificationFilterBridge$VxVkRxma_fYUscBBWS5HqCcr6UQ  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$NotificationFilterBridge$VxVkRxma_fYUscBBWS5HqCcr6UQ implements Consumer {
    public static final /* synthetic */ $$Lambda$NotificationFilterBridge$VxVkRxma_fYUscBBWS5HqCcr6UQ INSTANCE = new $$Lambda$NotificationFilterBridge$VxVkRxma_fYUscBBWS5HqCcr6UQ();

    private /* synthetic */ $$Lambda$NotificationFilterBridge$VxVkRxma_fYUscBBWS5HqCcr6UQ() {
    }

    @Override // com.amazon.deecomms.util.Consumer
    public final void accept(Object obj) {
        NotificationUtils.removeNotificationSuppression((String) obj);
    }
}
