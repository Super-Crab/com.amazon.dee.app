package com.amazon.alexa.location.provider.interactor.event;

import com.amazon.alexa.location.provider.util.Metrics;
import io.reactivex.rxjava3.functions.Action;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.provider.interactor.event.-$$Lambda$LocationEventInteractor$rP3JC7TYdio2qGzMZWJBd-Mlcsw  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$LocationEventInteractor$rP3JC7TYdio2qGzMZWJBdMlcsw implements Action {
    public static final /* synthetic */ $$Lambda$LocationEventInteractor$rP3JC7TYdio2qGzMZWJBdMlcsw INSTANCE = new $$Lambda$LocationEventInteractor$rP3JC7TYdio2qGzMZWJBdMlcsw();

    private /* synthetic */ $$Lambda$LocationEventInteractor$rP3JC7TYdio2qGzMZWJBdMlcsw() {
    }

    @Override // io.reactivex.rxjava3.functions.Action
    public final void run() {
        Metrics.recordSuccess(LocationEventInteractor.METRIC_LOCATION_REPORT_SUCCESS, true, LocationEventInteractor.SUB_COMPONENT_LOCATION_EVENT_INTERACTOR);
    }
}
