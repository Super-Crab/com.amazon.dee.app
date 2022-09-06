package com.amazon.alexa.accessorykit.findmy;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$DTAYLvW9XEHRH6Vm3YzQ9SeduME  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FindMyAccessoryManager$DTAYLvW9XEHRH6Vm3YzQ9SeduME implements Consumer {
    public static final /* synthetic */ $$Lambda$FindMyAccessoryManager$DTAYLvW9XEHRH6Vm3YzQ9SeduME INSTANCE = new $$Lambda$FindMyAccessoryManager$DTAYLvW9XEHRH6Vm3YzQ9SeduME();

    private /* synthetic */ $$Lambda$FindMyAccessoryManager$DTAYLvW9XEHRH6Vm3YzQ9SeduME() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s: monitorConnectStatusForSession error querying deviceInformation", (Throwable) obj, FindMyAccessoryManager.TAG);
    }
}
