package com.amazon.alexa.biloba.view.dashboard;

import androidx.lifecycle.Observer;
import java.util.List;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.biloba.view.dashboard.-$$Lambda$BilobaDashboardView$YfaclD3wm8fzRL2DWklzBKatHJs  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$BilobaDashboardView$YfaclD3wm8fzRL2DWklzBKatHJs implements Observer {
    private final /* synthetic */ BilobaDashboardView f$0;

    public /* synthetic */ $$Lambda$BilobaDashboardView$YfaclD3wm8fzRL2DWklzBKatHJs(BilobaDashboardView bilobaDashboardView) {
        this.f$0 = bilobaDashboardView;
    }

    @Override // androidx.lifecycle.Observer
    public final void onChanged(Object obj) {
        this.f$0.updateCards((List) obj);
    }
}
