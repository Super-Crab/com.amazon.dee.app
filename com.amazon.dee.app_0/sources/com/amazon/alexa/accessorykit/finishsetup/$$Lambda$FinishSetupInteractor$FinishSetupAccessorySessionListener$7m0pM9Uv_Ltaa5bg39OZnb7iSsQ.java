package com.amazon.alexa.accessorykit.finishsetup;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.finishsetup.-$$Lambda$FinishSetupInteractor$FinishSetupAccessorySessionListener$7m0pM9Uv_Ltaa5bg39OZnb7iSsQ  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FinishSetupInteractor$FinishSetupAccessorySessionListener$7m0pM9Uv_Ltaa5bg39OZnb7iSsQ implements Consumer {
    public static final /* synthetic */ $$Lambda$FinishSetupInteractor$FinishSetupAccessorySessionListener$7m0pM9Uv_Ltaa5bg39OZnb7iSsQ INSTANCE = new $$Lambda$FinishSetupInteractor$FinishSetupAccessorySessionListener$7m0pM9Uv_Ltaa5bg39OZnb7iSsQ();

    private /* synthetic */ $$Lambda$FinishSetupInteractor$FinishSetupAccessorySessionListener$7m0pM9Uv_Ltaa5bg39OZnb7iSsQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Unable to dismiss views on blocklisted session connect.", (Throwable) obj);
    }
}
