package com.amazon.alexa.biloba.databinding;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;
import com.amazon.alexa.biloba.view.gettingStartedV3.GettingStartedViewV3;
/* loaded from: classes6.dex */
public final class ConstraintLayoutAdapter {
    private ConstraintLayoutAdapter() {
    }

    @BindingAdapter({"android:layout_width"})
    public static void setWidth(ConstraintLayout constraintLayout, boolean z) {
        GettingStartedViewV3.handleLandscapeWidth(constraintLayout, z, constraintLayout.getContext());
    }
}
