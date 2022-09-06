package com.amazon.deecomms.calling.util;

import android.annotation.TargetApi;
import android.view.DisplayCutout;
import android.view.View;
import com.amazon.deecomms.util.IBuildVersionProvider;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class DisplayCutoutCalculator {
    private IBuildVersionProvider mBuildVersionProvider;

    @Inject
    public DisplayCutoutCalculator(IBuildVersionProvider iBuildVersionProvider) {
        this.mBuildVersionProvider = iBuildVersionProvider;
    }

    @TargetApi(28)
    public void adjust(final View view) {
        if (this.mBuildVersionProvider.getCurrentBuildVersion() >= 28) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.amazon.deecomms.calling.util.DisplayCutoutCalculator.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    int i9;
                    DisplayCutout displayCutout = view2.getRootWindowInsets().getDisplayCutout();
                    if (displayCutout != null) {
                        i9 = displayCutout.getSafeInsetTop();
                        view.removeOnLayoutChangeListener(this);
                    } else {
                        i9 = 0;
                    }
                    view2.setPadding(view2.getPaddingLeft(), i9, view2.getPaddingRight(), view2.getPaddingBottom());
                }
            });
        }
    }
}
