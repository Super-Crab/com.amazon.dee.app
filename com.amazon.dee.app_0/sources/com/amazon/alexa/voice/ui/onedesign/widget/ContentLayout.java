package com.amazon.alexa.voice.ui.onedesign.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import com.amazon.alexa.voice.ui.onedesign.util.DisplayUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public final class ContentLayout extends FrameLayout {
    private static final float COLLAPSE_DRAG_FACTOR = 0.8f;
    private static final float CONTENT_HEIGHT_DISMISS_FACTOR = 3.0f;
    private static final long MAX_ANIMATION_DURATION = 450;
    static final Property<ViewOffsetHelper, Integer> OFFSET_Y = new Property<ViewOffsetHelper, Integer>(Integer.class, "topAndBottomOffset") { // from class: com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.2
        @Override // android.util.Property
        public Integer get(ViewOffsetHelper viewOffsetHelper) {
            return Integer.valueOf(viewOffsetHelper.getTopAndBottomOffset());
        }

        @Override // android.util.Property
        public void set(ViewOffsetHelper viewOffsetHelper, Integer num) {
            viewOffsetHelper.setTopAndBottomOffset(num.intValue(), true);
        }
    };
    private Paint backgroundPaint;
    private boolean collapsed;
    private View content;
    private int contentBottom;
    private ViewDragHelper contentDragHelper;
    private int contentExpandedTop;
    private ViewOffsetHelper contentOffsetHelper;
    private int dismissOffset;
    private float dismissThreshold;
    private boolean drawShadow;
    private float minFlingVelocity;
    private int nestedScrollInitialTop;
    private boolean nestedScrolling;
    private OnContentListener onContentListener;
    private boolean settling;
    private Drawable shadowDrawable;
    private boolean sticky;
    private int stickyContentHeight;

    /* loaded from: classes11.dex */
    final class ContentDragCallbacks extends ViewDragHelper.Callback {
        ContentDragCallbacks() {
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            return ContentLayout.this.content.getLeft();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i, int i2) {
            return Math.min(Math.max(i, ContentLayout.this.contentExpandedTop), ContentLayout.this.sticky ? ContentLayout.this.contentBottom - ContentLayout.this.stickyContentHeight : ContentLayout.this.contentBottom);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewVerticalDragRange(View view) {
            int i = ContentLayout.this.contentExpandedTop - ContentLayout.this.contentBottom;
            return ContentLayout.this.sticky ? i - ContentLayout.this.stickyContentHeight : i;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            ContentLayout.this.contentOffsetHelper.resyncOffsets();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f, float f2) {
            boolean z = true;
            boolean z2 = f2 >= ContentLayout.this.minFlingVelocity || ContentLayout.this.contentOffsetHelper.getTopAndBottomOffset() == ContentLayout.this.getCollapsedOffset();
            int collapsedOffset = z2 ? ContentLayout.this.getCollapsedOffset() : 0;
            ContentLayout contentLayout = ContentLayout.this;
            int topAndBottomOffset = contentLayout.contentOffsetHelper.getTopAndBottomOffset();
            if (ContentLayout.this.sticky || !z2) {
                z = false;
            }
            contentLayout.animateSettle(topAndBottomOffset, collapsedOffset, f2, z);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i) {
            return view == ContentLayout.this.content;
        }
    }

    /* loaded from: classes11.dex */
    public interface OnContentListener {
        void onCollapsed();

        void onDismiss();

        void onDragged(int i, int i2, int i3, boolean z, boolean z2);

        void onExpanded();
    }

    /* loaded from: classes11.dex */
    public static class SimpleOnContentListener implements OnContentListener {
        @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
        public void onCollapsed() {
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
        public void onDismiss() {
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
        public void onDragged(int i, int i2, int i3, boolean z, boolean z2) {
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
        public void onExpanded() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public final class ViewOffsetHelper {
        private int layoutTop;
        private int offsetTop;
        private final View view;

        ViewOffsetHelper(View view) {
            this.view = view;
        }

        private void updateOffsets() {
            View view = this.view;
            ViewCompat.offsetTopAndBottom(view, this.offsetTop - (view.getTop() - this.layoutTop));
            ContentLayout.this.invalidate(this.view.getLeft(), this.layoutTop, this.view.getRight(), this.view.getTop());
        }

        public int getTopAndBottomOffset() {
            return this.offsetTop;
        }

        public void offsetTopAndBottom(int i) {
            int i2 = 0;
            int min = Math.min(Math.max(this.offsetTop + i, 0), ContentLayout.this.getCollapsedOffset());
            if (this.offsetTop == min) {
                return;
            }
            this.offsetTop = min;
            updateOffsets();
            boolean z = i > 0;
            if (ContentLayout.this.onContentListener == null) {
                return;
            }
            OnContentListener onContentListener = ContentLayout.this.onContentListener;
            int i3 = this.offsetTop;
            if (z) {
                i2 = ContentLayout.this.getCollapsedOffset();
            }
            onContentListener.onDragged(i3, i2, this.view.getHeight(), z, false);
        }

        public void onViewLayout() {
            this.layoutTop = this.view.getTop();
        }

        public void resyncOffsets() {
            int top = this.view.getTop() - this.layoutTop;
            int i = 0;
            boolean z = top > this.offsetTop;
            this.offsetTop = top;
            ContentLayout.this.invalidate(this.view.getLeft(), this.layoutTop, this.view.getRight(), this.view.getTop());
            if (ContentLayout.this.onContentListener != null) {
                OnContentListener onContentListener = ContentLayout.this.onContentListener;
                int i2 = this.offsetTop;
                if (z) {
                    i = ContentLayout.this.getCollapsedOffset();
                }
                onContentListener.onDragged(i2, i, this.view.getHeight(), z, false);
            }
        }

        public void setTopAndBottomOffset(int i, boolean z) {
            int i2 = 0;
            int min = Math.min(Math.max(i, 0), ContentLayout.this.getCollapsedOffset());
            boolean z2 = i > this.offsetTop;
            this.offsetTop = min;
            updateOffsets();
            if (ContentLayout.this.onContentListener != null) {
                OnContentListener onContentListener = ContentLayout.this.onContentListener;
                int i3 = this.offsetTop;
                if (z2) {
                    i2 = ContentLayout.this.getCollapsedOffset();
                }
                onContentListener.onDragged(i3, i2, this.view.getHeight() - ContentLayout.this.stickyContentHeight, z2, z);
            }
        }
    }

    public ContentLayout(Context context) {
        this(context, null, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animateSettle(int i, int i2, float f, final boolean z) {
        if (this.settling) {
            return;
        }
        boolean z2 = false;
        if (this.contentOffsetHelper.getTopAndBottomOffset() == i2) {
            if (!z && i2 >= getCollapsedOffset()) {
                z2 = true;
            }
            this.collapsed = z2;
            if (this.onContentListener == null) {
                return;
            }
            if (i2 < getCollapsedOffset()) {
                this.onContentListener.onExpanded();
                return;
            } else if (z) {
                this.onContentListener.onDismiss();
                return;
            } else {
                this.onContentListener.onCollapsed();
                return;
            }
        }
        final boolean z3 = i2 == getCollapsedOffset();
        long computeSettleDuration = computeSettleDuration(f, z3);
        this.settling = true;
        this.collapsed = !z && z3;
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this.contentOffsetHelper, OFFSET_Y, i, i2);
        ofInt.setDuration(computeSettleDuration);
        ofInt.setInterpolator(getSettleInterpolator(z3, f));
        ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ContentLayout.this.settling = false;
                if (ContentLayout.this.onContentListener == null) {
                    return;
                }
                if (!z3) {
                    ContentLayout.this.onContentListener.onExpanded();
                } else if (z) {
                    ContentLayout.this.onContentListener.onDismiss();
                } else {
                    ContentLayout.this.onContentListener.onCollapsed();
                }
            }
        });
        ofInt.start();
    }

    private long computeSettleDuration(float f, boolean z) {
        int top;
        int i;
        float max = Math.max(this.minFlingVelocity, Math.abs(f));
        if (z) {
            top = this.contentBottom - this.content.getTop();
            if (this.sticky) {
                i = this.stickyContentHeight;
            }
            return Math.max(Math.min((top * 1000) / max, (long) MAX_ANIMATION_DURATION), 0L);
        }
        top = this.content.getTop();
        i = this.contentExpandedTop;
        top -= i;
        return Math.max(Math.min((top * 1000) / max, (long) MAX_ANIMATION_DURATION), 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCollapsedOffset() {
        return this.sticky ? this.dismissOffset - this.stickyContentHeight : this.dismissOffset;
    }

    private TimeInterpolator getSettleInterpolator(boolean z, float f) {
        if (f != 0.0f) {
            if (z) {
                return new LinearInterpolator();
            }
            return new LinearOutSlowInInterpolator();
        } else if (z) {
            return new FastOutLinearInInterpolator();
        } else {
            return new FastOutSlowInInterpolator();
        }
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        setWillNotDraw(false);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.OdContentLayout, 0, 0);
        try {
            this.drawShadow = obtainStyledAttributes.getBoolean(R.styleable.OdContentLayout_drawShadowOnDrag, false);
            obtainStyledAttributes.recycle();
            setImportantForAccessibility(2);
            this.minFlingVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
            this.shadowDrawable = ContextCompat.getDrawable(context, R.drawable.voice_ui_od_content_layout_shadow);
            this.stickyContentHeight = DisplayUtils.transformDpiToPx(context, 56);
            this.backgroundPaint = new Paint();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private boolean isDraggableViewUnder(int i, int i2) {
        return getVisibility() == 0 && this.contentDragHelper.isViewUnder(this, i, i2);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.content == null) {
            this.content = view;
            this.content.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.amazon.alexa.voice.ui.onedesign.widget.-$$Lambda$ContentLayout$cQVEUietq7qs1lwSRU9E84QocKk
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                    ContentLayout.this.lambda$addView$0$ContentLayout(view2, i2, i3, i4, i5, i6, i7, i8, i9);
                }
            });
            this.contentOffsetHelper = new ViewOffsetHelper(this.content);
            if (layoutParams instanceof FrameLayout.LayoutParams) {
                ((FrameLayout.LayoutParams) layoutParams).gravity = 81;
            }
            super.addView(view, i, layoutParams);
            return;
        }
        throw new UnsupportedOperationException("ContentLayout must only have 1 child view");
    }

    public void collapse() {
        animateSettle(this.contentOffsetHelper.getTopAndBottomOffset(), getCollapsedOffset(), 0.0f, !this.sticky);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.contentDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void dismiss() {
        animateSettle(this.contentOffsetHelper.getTopAndBottomOffset(), this.dismissOffset, 0.0f, true);
    }

    public void expand() {
        animateSettle(this.contentOffsetHelper.getTopAndBottomOffset(), 0, 0.0f, false);
    }

    public int getStickyContentHeight() {
        return this.stickyContentHeight;
    }

    public boolean isCollapsed() {
        return this.collapsed;
    }

    public boolean isExpanded() {
        return !this.collapsed;
    }

    public boolean isSticky() {
        return this.sticky;
    }

    public /* synthetic */ void lambda$addView$0$ContentLayout(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.contentBottom = i4;
        this.contentExpandedTop = i2;
        this.dismissOffset = i4 - i2;
        this.dismissThreshold = this.dismissOffset / 3.0f;
        this.contentOffsetHelper.onViewLayout();
        this.contentOffsetHelper.setTopAndBottomOffset((!this.sticky || !this.collapsed) ? 0 : getCollapsedOffset(), false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.contentDragHelper = ViewDragHelper.create(this, new ContentDragCallbacks());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        if (this.content == null || (i = this.contentBottom) <= 0 || !this.drawShadow) {
            return;
        }
        if (this.sticky) {
            i -= this.stickyContentHeight;
        }
        this.backgroundPaint.setColor(Color.argb(((i - this.content.getTop()) * 230) / i, 21, 0, 32));
        canvas.drawRect(0.0f, 0.0f, canvas.getWidth(), this.content.getTop(), this.backgroundPaint);
        this.shadowDrawable.setBounds(this.content.getLeft(), this.content.getTop() - this.shadowDrawable.getIntrinsicHeight(), this.content.getRight(), this.content.getTop());
        this.shadowDrawable.draw(canvas);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.nestedScrolling) {
            return false;
        }
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked != 3 && actionMasked != 1) {
            return isDraggableViewUnder((int) motionEvent.getX(), (int) motionEvent.getY()) && this.contentDragHelper.shouldInterceptTouchEvent(motionEvent);
        }
        this.contentDragHelper.cancel();
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (f2 <= (-this.minFlingVelocity) && !view.canScrollVertically(-1) && this.contentOffsetHelper.getTopAndBottomOffset() > this.dismissThreshold) {
            animateSettle(this.contentOffsetHelper.getTopAndBottomOffset(), getCollapsedOffset(), f2, !this.sticky);
            return true;
        }
        if (f2 > 0.0f && !isExpanded()) {
            animateSettle(this.contentOffsetHelper.getTopAndBottomOffset(), 0, f2, false);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        int top;
        if (i2 <= 0 || (top = this.content.getTop() - this.contentExpandedTop) <= 0) {
            return;
        }
        int min = Math.min(top, i2);
        this.contentOffsetHelper.offsetTopAndBottom(-min);
        iArr[1] = min;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        if (i4 < 0) {
            this.contentOffsetHelper.offsetTopAndBottom(-i4);
        }
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        this.collapsed = bundle.getBoolean("collapsed", this.collapsed);
        this.sticky = bundle.getBoolean("sticky", this.sticky);
        this.stickyContentHeight = bundle.getInt("stickyContentHeight", this.stickyContentHeight);
        super.onRestoreInstanceState(bundle.getParcelable("super"));
        this.contentOffsetHelper.setTopAndBottomOffset((!this.sticky || !this.collapsed) ? 0 : getCollapsedOffset(), false);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("super", super.onSaveInstanceState());
        bundle.putBoolean("collapsed", this.collapsed);
        bundle.putBoolean("sticky", this.sticky);
        bundle.putInt("stickyContentHeight", this.stickyContentHeight);
        return bundle;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) != 0) {
            this.nestedScrolling = true;
            this.nestedScrollInitialTop = this.content.getTop();
            return true;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        this.nestedScrolling = false;
        if (this.settling || this.content.getTop() == this.nestedScrollInitialTop) {
            return;
        }
        float topAndBottomOffset = this.contentOffsetHelper.getTopAndBottomOffset() / getCollapsedOffset();
        if (this.sticky && topAndBottomOffset >= COLLAPSE_DRAG_FACTOR) {
            collapse();
        } else {
            expand();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.contentDragHelper.processTouchEvent(motionEvent);
        return this.contentDragHelper.getCapturedView() != null || super.onTouchEvent(motionEvent);
    }

    public void removeOnContentListener() {
        this.onContentListener = null;
    }

    public void setCollapsed(boolean z) {
        if (this.collapsed == z) {
            return;
        }
        this.sticky = true;
        this.collapsed = z;
        this.contentOffsetHelper.setTopAndBottomOffset(z ? getCollapsedOffset() : 0, false);
    }

    public void setOnContentListener(OnContentListener onContentListener) {
        this.onContentListener = onContentListener;
    }

    public void setSticky(boolean z) {
        this.sticky = z;
    }

    public void setStickyContentHeight(int i) {
        this.stickyContentHeight = i;
    }

    public ContentLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context, attributeSet);
    }
}
