package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$AccessoriesFactory$mLjoEC1CG7pvlBTtm7V_zy30yYE  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$AccessoriesFactory$mLjoEC1CG7pvlBTtm7V_zy30yYE implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoriesFactory$mLjoEC1CG7pvlBTtm7V_zy30yYE INSTANCE = new $$Lambda$AccessoriesFactory$mLjoEC1CG7pvlBTtm7V_zy30yYE();

    private /* synthetic */ $$Lambda$AccessoriesFactory$mLjoEC1CG7pvlBTtm7V_zy30yYE() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Error observing users to initialize interactor.", (Throwable) obj);
    }
}
