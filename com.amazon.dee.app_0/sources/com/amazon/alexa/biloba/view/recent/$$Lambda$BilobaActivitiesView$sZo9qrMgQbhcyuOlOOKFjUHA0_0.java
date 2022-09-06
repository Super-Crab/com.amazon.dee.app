package com.amazon.alexa.biloba.view.recent;

import androidx.lifecycle.Observer;
import java.util.List;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.biloba.view.recent.-$$Lambda$BilobaActivitiesView$sZo9qrMgQbhcyuOlOOKFjUHA0_0  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$BilobaActivitiesView$sZo9qrMgQbhcyuOlOOKFjUHA0_0 implements Observer {
    private final /* synthetic */ BilobaActivitiesView f$0;

    public /* synthetic */ $$Lambda$BilobaActivitiesView$sZo9qrMgQbhcyuOlOOKFjUHA0_0(BilobaActivitiesView bilobaActivitiesView) {
        this.f$0 = bilobaActivitiesView;
    }

    @Override // androidx.lifecycle.Observer
    public final void onChanged(Object obj) {
        this.f$0.updateListView((List) obj);
    }
}
