package com.amazon.alexa.drive.main.v2;

import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.drive.main.v2.-$$Lambda$DriveModeMainActivityViewModel$ASJIwGg7Ph1bFuG2qoMijCN-bpA  reason: invalid class name */
/* loaded from: classes7.dex */
public final /* synthetic */ class $$Lambda$DriveModeMainActivityViewModel$ASJIwGg7Ph1bFuG2qoMijCNbpA implements BottomNavigationView.OnNavigationItemSelectedListener {
    private final /* synthetic */ DriveModeMainActivityViewModel f$0;

    public /* synthetic */ $$Lambda$DriveModeMainActivityViewModel$ASJIwGg7Ph1bFuG2qoMijCNbpA(DriveModeMainActivityViewModel driveModeMainActivityViewModel) {
        this.f$0 = driveModeMainActivityViewModel;
    }

    @Override // com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
    public final boolean onNavigationItemSelected(MenuItem menuItem) {
        boolean onTabSelection;
        onTabSelection = this.f$0.onTabSelection(menuItem);
        return onTabSelection;
    }
}
