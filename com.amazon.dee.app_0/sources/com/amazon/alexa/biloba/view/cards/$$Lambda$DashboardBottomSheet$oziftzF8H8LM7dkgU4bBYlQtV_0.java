package com.amazon.alexa.biloba.view.cards;

import android.content.DialogInterface;
import android.widget.FrameLayout;
import com.google.android.material.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.biloba.view.cards.-$$Lambda$DashboardBottomSheet$oziftzF8H8LM7dkgU4bBYlQtV_0  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$DashboardBottomSheet$oziftzF8H8LM7dkgU4bBYlQtV_0 implements DialogInterface.OnShowListener {
    public static final /* synthetic */ $$Lambda$DashboardBottomSheet$oziftzF8H8LM7dkgU4bBYlQtV_0 INSTANCE = new $$Lambda$DashboardBottomSheet$oziftzF8H8LM7dkgU4bBYlQtV_0();

    private /* synthetic */ $$Lambda$DashboardBottomSheet$oziftzF8H8LM7dkgU4bBYlQtV_0() {
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        BottomSheetBehavior.from((FrameLayout) ((BottomSheetDialog) dialogInterface).findViewById(R.id.design_bottom_sheet)).setState(3);
    }
}
