package com.amazon.alexa.drive.landing;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes7.dex */
public class LandingPageRecyclerViewMarginDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "LandingPageRecyclerViewMarginDecoration";
    private Drawable mDivider;

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int childCount;
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        if (this.mDivider == null || (childCount = recyclerView.getChildCount()) <= 0) {
            return;
        }
        View childAt = recyclerView.getChildAt(0);
        int top = childAt.getTop() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) childAt.getLayoutParams())).topMargin;
        this.mDivider.setBounds(paddingLeft, top, width, this.mDivider.getIntrinsicHeight() + top);
        this.mDivider.draw(canvas);
        for (int i = 0; i < childCount; i++) {
            View childAt2 = recyclerView.getChildAt(i);
            int bottom = childAt2.getBottom() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) childAt2.getLayoutParams())).bottomMargin;
            int intrinsicHeight = this.mDivider.getIntrinsicHeight() + bottom;
            this.mDivider.setBounds(paddingLeft, bottom, width, intrinsicHeight);
            if (recyclerView.getAdapter() != null && recyclerView.getChildAdapterPosition(childAt2) == recyclerView.getAdapter().getItemCount() - 1 && recyclerView.getBottom() < intrinsicHeight) {
                recyclerView.setPadding(recyclerView.getPaddingLeft(), recyclerView.getPaddingTop(), recyclerView.getPaddingRight(), intrinsicHeight - recyclerView.getBottom());
            }
            this.mDivider.draw(canvas);
        }
    }

    public void setDrawable(@NonNull Drawable drawable) {
        if (drawable == null) {
            Log.e(TAG, "Drawable cannot be null");
        }
        this.mDivider = drawable;
    }
}
