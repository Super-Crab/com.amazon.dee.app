package com.amazon.alexa.accessorykit.inputevents;

import com.amazon.alexa.accessory.internal.util.MultiDeviceUtils;
import io.reactivex.rxjava3.functions.Function;
import java.util.Set;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.inputevents.-$$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w implements Function {
    public static final /* synthetic */ $$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w INSTANCE = new $$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w();

    private /* synthetic */ $$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return MultiDeviceUtils.getDeviceInformationWithHighestDeviceId((Set) obj);
    }
}
