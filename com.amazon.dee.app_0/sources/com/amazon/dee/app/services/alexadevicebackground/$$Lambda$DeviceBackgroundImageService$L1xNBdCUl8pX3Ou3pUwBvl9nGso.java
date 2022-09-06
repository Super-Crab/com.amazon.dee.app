package com.amazon.dee.app.services.alexadevicebackground;

import rx.Observable;
import rx.functions.Func1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.services.alexadevicebackground.-$$Lambda$DeviceBackgroundImageService$L1xNBdCUl8pX3Ou3pUwBvl9nGso  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$DeviceBackgroundImageService$L1xNBdCUl8pX3Ou3pUwBvl9nGso implements Func1 {
    public static final /* synthetic */ $$Lambda$DeviceBackgroundImageService$L1xNBdCUl8pX3Ou3pUwBvl9nGso INSTANCE = new $$Lambda$DeviceBackgroundImageService$L1xNBdCUl8pX3Ou3pUwBvl9nGso();

    private /* synthetic */ $$Lambda$DeviceBackgroundImageService$L1xNBdCUl8pX3Ou3pUwBvl9nGso() {
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public final Object mo13102call(Object obj) {
        Observable flatMap;
        flatMap = r1.zipWith(Observable.range(1, 11), $$Lambda$DeviceBackgroundImageService$MhZs2qQUZK_nm2zobEJukVMbOc4.INSTANCE).flatMap(new Func1() { // from class: com.amazon.dee.app.services.alexadevicebackground.-$$Lambda$DeviceBackgroundImageService$xg6r97blOZddHltAm1rLynJCxVs
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj2) {
                return DeviceBackgroundImageService.lambda$null$2(Observable.this, (Integer) obj2);
            }
        });
        return flatMap;
    }
}
