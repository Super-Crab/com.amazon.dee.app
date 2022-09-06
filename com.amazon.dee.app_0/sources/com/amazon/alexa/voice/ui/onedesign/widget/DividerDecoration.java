package com.amazon.alexa.voice.ui.onedesign.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes11.dex */
public final class DividerDecoration extends RecyclerView.ItemDecoration {
    private final DividerDecision decision;
    private final int heightDp;
    private final Paint paint;

    /* loaded from: classes11.dex */
    public interface DividerDecision {
        boolean shouldDrawDivider(int i);
    }

    public DividerDecoration(Context context, int i, DividerDecision dividerDecision) {
        this(new Paint(), (int) TypedValue.applyDimension(1, 1.0f, context.getResources().getDisplayMetrics()), ContextCompat.getColor(context, i), dividerDecision);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.decision.shouldDrawDivider(recyclerView.getChildAdapterPosition(view))) {
            rect.set(0, 0, 0, this.heightDp);
        } else {
            rect.setEmpty();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View childAt = recyclerView.getChildAt(i);
            if (this.decision.shouldDrawDivider(recyclerView.getChildAdapterPosition(childAt))) {
                canvas.drawRect(childAt.getLeft(), childAt.getBottom(), childAt.getRight(), childAt.getBottom() + this.heightDp, this.paint);
            }
        }
    }

    @VisibleForTesting
    public DividerDecoration(Paint paint, int i, int i2, DividerDecision dividerDecision) {
        this.paint = paint;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(i2);
        this.heightDp = i;
        this.decision = dividerDecision;
    }
}
