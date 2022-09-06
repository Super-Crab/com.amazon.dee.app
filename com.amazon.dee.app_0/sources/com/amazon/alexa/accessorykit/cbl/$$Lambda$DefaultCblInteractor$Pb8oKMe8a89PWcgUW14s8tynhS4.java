package com.amazon.alexa.accessorykit.cbl;

import com.amazon.alexa.accessory.protocol.Cbl;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.cbl.-$$Lambda$DefaultCblInteractor$Pb8oKMe8a89PWcgUW14s8tynhS4  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$DefaultCblInteractor$Pb8oKMe8a89PWcgUW14s8tynhS4 implements Function {
    public static final /* synthetic */ $$Lambda$DefaultCblInteractor$Pb8oKMe8a89PWcgUW14s8tynhS4 INSTANCE = new $$Lambda$DefaultCblInteractor$Pb8oKMe8a89PWcgUW14s8tynhS4();

    private /* synthetic */ $$Lambda$DefaultCblInteractor$Pb8oKMe8a89PWcgUW14s8tynhS4() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        Boolean valueOf;
        Cbl.CblLoginState cblLoginState = (Cbl.CblLoginState) obj;
        valueOf = Boolean.valueOf(r1.getStateValue() == 2);
        return valueOf;
    }
}
