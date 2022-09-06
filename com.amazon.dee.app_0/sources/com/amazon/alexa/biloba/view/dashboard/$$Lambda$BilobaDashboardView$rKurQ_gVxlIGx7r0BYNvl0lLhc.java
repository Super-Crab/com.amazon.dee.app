package com.amazon.alexa.biloba.view.dashboard;

import androidx.lifecycle.Observer;
import java.util.List;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardView$rKurQ_gVxlIGx7r0BYNvl0-lLhc  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$BilobaDashboardView$rKurQ_gVxlIGx7r0BYNvl0lLhc implements Observer {
    private final /* synthetic */ BilobaDashboardView f$0;

    public /* synthetic */ $$Lambda$BilobaDashboardView$rKurQ_gVxlIGx7r0BYNvl0lLhc(BilobaDashboardView bilobaDashboardView) {
        this.f$0 = bilobaDashboardView;
    }

    @Override // androidx.lifecycle.Observer
    public final void onChanged(Object obj) {
        this.f$0.updateListView((List) obj);
    }
}
