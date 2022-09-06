package com.amazon.deecomms.common.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.amazon.deecomms.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
/* loaded from: classes12.dex */
public class CustomHeightBottomSheetDialog extends BottomSheetDialog implements DialogInterface.OnShowListener {
    private BottomSheetBehavior<?> bottomSheetBehavior;

    public CustomHeightBottomSheetDialog(@NonNull Context context, @StyleRes int i) {
        super(context, i);
    }

    private int getBottomSheetHeight() {
        return (int) (TypedValue.applyDimension(1, getContext().getResources().getConfiguration().screenHeightDp, Resources.getSystem().getDisplayMetrics()) - getContext().getResources().getDimensionPixelSize(R.dimen.bottom_sheet_height));
    }

    public BottomSheetBehavior<?> getBottomSheetBehavior() {
        return this.bottomSheetBehavior;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.bottomsheet.BottomSheetDialog, androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int bottomSheetHeight = getBottomSheetHeight();
        if (bottomSheetHeight <= 0) {
            bottomSheetHeight = -1;
        }
        getWindow().setGravity(80);
        getWindow().setLayout(-1, bottomSheetHeight);
        setOnShowListener(this);
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialogInterface) {
        final FrameLayout frameLayout = (FrameLayout) findViewById(com.google.android.material.R.id.design_bottom_sheet);
        this.bottomSheetBehavior = new NoDragBottomSheetBehavior();
        ((CoordinatorLayout.LayoutParams) frameLayout.getLayoutParams()).setBehavior(this.bottomSheetBehavior);
        frameLayout.requestLayout();
        this.bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { // from class: com.amazon.deecomms.common.ui.CustomHeightBottomSheetDialog.1
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(@NonNull View view, float f) {
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(@NonNull View view, int i) {
                if (i == 5) {
                    CustomHeightBottomSheetDialog.this.dismiss();
                } else if (i != 3) {
                } else {
                    frameLayout.requestLayout();
                    frameLayout.invalidate();
                }
            }
        });
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.deecomms.common.ui.CustomHeightBottomSheetDialog.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                frameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                CustomHeightBottomSheetDialog.this.bottomSheetBehavior.setHideable(false);
                CustomHeightBottomSheetDialog.this.bottomSheetBehavior.setState(3);
            }
        });
    }

    /* loaded from: classes12.dex */
    private class NoDragBottomSheetBehavior<V extends View> extends BottomSheetBehavior<V> {
        public NoDragBottomSheetBehavior() {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr) {
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i) {
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view) {
        }

        @Override // com.google.android.material.bottomsheet.BottomSheetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public NoDragBottomSheetBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }
}
