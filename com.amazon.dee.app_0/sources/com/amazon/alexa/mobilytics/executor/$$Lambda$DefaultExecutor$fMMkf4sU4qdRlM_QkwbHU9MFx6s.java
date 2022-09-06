package com.amazon.alexa.mobilytics.executor;

import com.amazon.alexa.mobilytics.connector.ConnectorExecutor;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.mobilytics.executor.-$$Lambda$DefaultExecutor$fMMkf4sU4qdRlM_QkwbHU9MFx6s  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$DefaultExecutor$fMMkf4sU4qdRlM_QkwbHU9MFx6s implements Action1 {
    public static final /* synthetic */ $$Lambda$DefaultExecutor$fMMkf4sU4qdRlM_QkwbHU9MFx6s INSTANCE = new $$Lambda$DefaultExecutor$fMMkf4sU4qdRlM_QkwbHU9MFx6s();

    private /* synthetic */ $$Lambda$DefaultExecutor$fMMkf4sU4qdRlM_QkwbHU9MFx6s() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        ((ConnectorExecutor) obj).executeOnFinalize();
    }
}
