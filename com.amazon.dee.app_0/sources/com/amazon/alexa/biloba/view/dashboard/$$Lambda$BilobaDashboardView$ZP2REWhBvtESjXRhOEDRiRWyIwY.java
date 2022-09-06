package com.amazon.alexa.biloba.view.dashboard;

import androidx.lifecycle.Observer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardView$ZP2REWhBvtESjXRhOEDRiRWyIwY  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$BilobaDashboardView$ZP2REWhBvtESjXRhOEDRiRWyIwY implements Observer {
    private final /* synthetic */ BilobaDashboardView f$0;

    public /* synthetic */ $$Lambda$BilobaDashboardView$ZP2REWhBvtESjXRhOEDRiRWyIwY(BilobaDashboardView bilobaDashboardView) {
        this.f$0 = bilobaDashboardView;
    }

    @Override // androidx.lifecycle.Observer
    public final void onChanged(Object obj) {
        this.f$0.clearTTCFOnError((Throwable) obj);
    }
}
