package com.amazon.alexa.accessorykit.interprocess;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$DefaultMessageNotificationStateMonitor$MessageNotificationStatusReceiver$MessageObserver$fP_qNXKdh1g23yfMuxqFgl9HA8s  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$DefaultMessageNotificationStateMonitor$MessageNotificationStatusReceiver$MessageObserver$fP_qNXKdh1g23yfMuxqFgl9HA8s implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultMessageNotificationStateMonitor$MessageNotificationStatusReceiver$MessageObserver$fP_qNXKdh1g23yfMuxqFgl9HA8s INSTANCE = new $$Lambda$DefaultMessageNotificationStateMonitor$MessageNotificationStatusReceiver$MessageObserver$fP_qNXKdh1g23yfMuxqFgl9HA8s();

    private /* synthetic */ $$Lambda$DefaultMessageNotificationStateMonitor$MessageNotificationStatusReceiver$MessageObserver$fP_qNXKdh1g23yfMuxqFgl9HA8s() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Error executing onChange", (Throwable) obj);
    }
}
