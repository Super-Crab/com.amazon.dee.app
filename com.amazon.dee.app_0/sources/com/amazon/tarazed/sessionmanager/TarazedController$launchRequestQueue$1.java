package com.amazon.tarazed.sessionmanager;

import com.amazon.tarazed.core.notification.type.TarazedLaunchRequest;
import java.util.Comparator;
import kotlin.Metadata;
/* compiled from: TarazedController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "left", "Lcom/amazon/tarazed/core/notification/type/TarazedLaunchRequest;", "kotlin.jvm.PlatformType", "right", "compare"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class TarazedController$launchRequestQueue$1<T> implements Comparator<E> {
    public static final TarazedController$launchRequestQueue$1 INSTANCE = new TarazedController$launchRequestQueue$1();

    TarazedController$launchRequestQueue$1() {
    }

    @Override // java.util.Comparator
    public final int compare(TarazedLaunchRequest tarazedLaunchRequest, TarazedLaunchRequest tarazedLaunchRequest2) {
        return (tarazedLaunchRequest2.getNotificationTime() > tarazedLaunchRequest.getNotificationTime() ? 1 : (tarazedLaunchRequest2.getNotificationTime() == tarazedLaunchRequest.getNotificationTime() ? 0 : -1));
    }
}
