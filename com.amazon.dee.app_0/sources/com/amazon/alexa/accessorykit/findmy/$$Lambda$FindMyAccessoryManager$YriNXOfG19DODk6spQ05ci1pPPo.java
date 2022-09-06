package com.amazon.alexa.accessorykit.findmy;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$YriNXOfG19DODk6spQ05ci1pPPo  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FindMyAccessoryManager$YriNXOfG19DODk6spQ05ci1pPPo implements Consumer {
    public static final /* synthetic */ $$Lambda$FindMyAccessoryManager$YriNXOfG19DODk6spQ05ci1pPPo INSTANCE = new $$Lambda$FindMyAccessoryManager$YriNXOfG19DODk6spQ05ci1pPPo();

    private /* synthetic */ $$Lambda$FindMyAccessoryManager$YriNXOfG19DODk6spQ05ci1pPPo() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s: monitorConnectStatusForSession error querying deviceInformation for first event", (Throwable) obj, FindMyAccessoryManager.TAG);
    }
}
