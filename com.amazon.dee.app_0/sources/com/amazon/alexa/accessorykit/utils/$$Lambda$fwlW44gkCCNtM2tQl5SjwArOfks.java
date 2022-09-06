package com.amazon.alexa.accessorykit.utils;

import com.amazon.alexa.accessory.User;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.utils.-$$Lambda$fwlW44gkCCNtM2tQl5SjwArOfks  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$fwlW44gkCCNtM2tQl5SjwArOfks implements Function {
    public static final /* synthetic */ $$Lambda$fwlW44gkCCNtM2tQl5SjwArOfks INSTANCE = new $$Lambda$fwlW44gkCCNtM2tQl5SjwArOfks();

    private /* synthetic */ $$Lambda$fwlW44gkCCNtM2tQl5SjwArOfks() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return ((User) obj).getAccessToken();
    }
}
