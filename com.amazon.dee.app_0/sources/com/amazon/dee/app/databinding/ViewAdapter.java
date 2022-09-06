package com.amazon.dee.app.databinding;

import android.view.View;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
@BindingMethods({@BindingMethod(attribute = "android:onClick", method = "setOnClickListener", type = View.class)})
/* loaded from: classes12.dex */
public final class ViewAdapter {
    private ViewAdapter() {
    }

    @BindingAdapter({"fadeVisibility"})
    public static void fadeVisibility(View view, int i) {
        if (view.getVisibility() != i) {
            if (i == 0) {
                view.setAlpha(0.0f);
                view.animate().alpha(1.0f).start();
            }
            view.setVisibility(i);
        }
    }
}
