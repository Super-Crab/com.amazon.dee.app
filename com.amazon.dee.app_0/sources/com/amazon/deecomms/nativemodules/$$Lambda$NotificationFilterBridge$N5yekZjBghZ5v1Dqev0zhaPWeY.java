package com.amazon.deecomms.nativemodules;

import com.amazon.deecomms.notifications.util.NotificationUtils;
import com.amazon.deecomms.util.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.nativemodules.-$$Lambda$NotificationFilterBridge$N5yekZjBghZ5v1Dqev0zhaP-WeY  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$NotificationFilterBridge$N5yekZjBghZ5v1Dqev0zhaPWeY implements Consumer {
    public static final /* synthetic */ $$Lambda$NotificationFilterBridge$N5yekZjBghZ5v1Dqev0zhaPWeY INSTANCE = new $$Lambda$NotificationFilterBridge$N5yekZjBghZ5v1Dqev0zhaPWeY();

    private /* synthetic */ $$Lambda$NotificationFilterBridge$N5yekZjBghZ5v1Dqev0zhaPWeY() {
    }

    @Override // com.amazon.deecomms.util.Consumer
    public final void accept(Object obj) {
        NotificationUtils.suppressNotification((String) obj);
    }
}
