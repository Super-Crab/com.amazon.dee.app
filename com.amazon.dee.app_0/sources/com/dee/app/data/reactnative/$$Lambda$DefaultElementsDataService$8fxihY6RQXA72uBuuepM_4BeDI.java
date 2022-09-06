package com.dee.app.data.reactnative;

import com.dee.app.data.reactnative.DefaultElementsDataService;
import rx.functions.Func1;
/* compiled from: lambda */
/* renamed from: com.dee.app.data.reactnative.-$$Lambda$DefaultElementsDataService$8fxihY6RQXA-72uBuuepM_4BeDI  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$DefaultElementsDataService$8fxihY6RQXA72uBuuepM_4BeDI implements Func1 {
    public static final /* synthetic */ $$Lambda$DefaultElementsDataService$8fxihY6RQXA72uBuuepM_4BeDI INSTANCE = new $$Lambda$DefaultElementsDataService$8fxihY6RQXA72uBuuepM_4BeDI();

    private /* synthetic */ $$Lambda$DefaultElementsDataService$8fxihY6RQXA72uBuuepM_4BeDI() {
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public final Object mo13102call(Object obj) {
        ElementsDataResponse response;
        response = ((DefaultElementsDataService.ResponseWrapper) obj).getResponse();
        return response;
    }
}
