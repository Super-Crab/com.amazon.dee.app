package com.amazon.dee.app.databinding;

import androidx.databinding.BindingAdapter;
import androidx.drawerlayout.widget.DrawerLayout;
/* loaded from: classes12.dex */
public final class DrawerLayoutAdapter {
    private DrawerLayoutAdapter() {
    }

    @BindingAdapter({"locked"})
    public static void setDrawerLockMode(DrawerLayout drawerLayout, boolean z) {
        drawerLayout.setDrawerLockMode(z ? 1 : 0);
    }
}
