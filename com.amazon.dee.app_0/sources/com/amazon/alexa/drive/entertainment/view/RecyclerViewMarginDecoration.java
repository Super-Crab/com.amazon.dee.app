package com.amazon.alexa.drive.entertainment.view;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes7.dex */
public class RecyclerViewMarginDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "RecyclerViewMarginDecoration";
    private Drawable mDivider;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        if (this.mDivider != null) {
            int childCount = recyclerView.getChildCount();
            for (int i = 1; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                int top = childAt.getTop() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) childAt.getLayoutParams())).topMargin;
                this.mDivider.setBounds(paddingLeft, top, width, this.mDivider.getIntrinsicHeight() + top);
                this.mDivider.draw(canvas);
            }
        }
    }

    public void setDrawable(@NonNull Drawable drawable) {
        if (drawable == null) {
            Log.e(TAG, "Drawable cannot be null");
        }
        this.mDivider = drawable;
    }
}
