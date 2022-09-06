package com.amazon.deecomms.common.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes12.dex */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private final int[] ATTRS = {16843284};
    private Drawable divider;

    public DividerItemDecoration(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(this.ATTRS);
        this.divider = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
    }

    protected boolean canDrawDivider(int i) {
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            if (canDrawDivider(recyclerView.getChildAdapterPosition(childAt))) {
                int bottom = childAt.getBottom() + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) childAt.getLayoutParams())).bottomMargin;
                this.divider.setBounds(paddingLeft, bottom, width, this.divider.getIntrinsicHeight() + bottom);
                this.divider.draw(canvas);
            }
        }
    }

    public DividerItemDecoration(Context context, int i) {
        this.divider = ContextCompat.getDrawable(context, i);
    }
}
