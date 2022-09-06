package com.facebook.react.views.scroll;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.uimanager.FabricViewStateManager;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import com.facebook.react.views.view.ReactViewBackgroundManager;
import java.lang.reflect.Field;
import java.util.List;
/* loaded from: classes2.dex */
public class ReactScrollView extends ScrollView implements ReactClippingViewGroup, ViewGroup.OnHierarchyChangeListener, View.OnLayoutChangeListener, FabricViewStateManager.HasFabricViewStateManager {
    private static final String CONTENT_OFFSET_LEFT = "contentOffsetLeft";
    private static final String CONTENT_OFFSET_TOP = "contentOffsetTop";
    private static final int UNSET_CONTENT_OFFSET = -1;
    @Nullable
    private static Field sScrollerField = null;
    private static boolean sTriedToGetScrollerField = false;
    private boolean mActivelyScrolling;
    @Nullable
    private Rect mClippingRect;
    @Nullable
    private View mContentView;
    private float mDecelerationRate;
    private boolean mDisableIntervalMomentum;
    private boolean mDragging;
    @Nullable
    private Drawable mEndBackground;
    private int mEndFillColor;
    private final FabricViewStateManager mFabricViewStateManager;
    private int mFinalAnimatedPositionScrollX;
    private int mFinalAnimatedPositionScrollY;
    @Nullable
    private FpsListener mFpsListener;
    private int mLastStateUpdateScrollX;
    private int mLastStateUpdateScrollY;
    private final OnScrollDispatchHelper mOnScrollDispatchHelper;
    @Nullable
    private String mOverflow;
    private boolean mPagingEnabled;
    @Nullable
    private Runnable mPostTouchRunnable;
    private ReactViewBackgroundManager mReactBackgroundManager;
    private final Rect mRect;
    private boolean mRemoveClippedSubviews;
    @Nullable
    private ValueAnimator mScrollAnimator;
    private boolean mScrollEnabled;
    @Nullable
    private String mScrollPerfTag;
    @Nullable
    private final OverScroller mScroller;
    private boolean mSendMomentumEvents;
    private int mSnapInterval;
    @Nullable
    private List<Integer> mSnapOffsets;
    private boolean mSnapToEnd;
    private boolean mSnapToStart;
    private final VelocityHelper mVelocityHelper;
    private int pendingContentOffsetX;
    private int pendingContentOffsetY;

    public ReactScrollView(ReactContext reactContext) {
        this(reactContext, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disableFpsListener() {
        if (isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.disable(this.mScrollPerfTag);
        }
    }

    private void enableFpsListener() {
        if (isScrollPerfLoggingEnabled()) {
            Assertions.assertNotNull(this.mFpsListener);
            Assertions.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.enable(this.mScrollPerfTag);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flingAndSnap(int i) {
        int floor;
        int min;
        int i2;
        int i3;
        int i4;
        if (getChildCount() <= 0) {
            return;
        }
        if (this.mSnapInterval == 0 && this.mSnapOffsets == null) {
            smoothScrollAndSnap(i);
            return;
        }
        int maxScrollY = getMaxScrollY();
        int predictFinalScrollPosition = predictFinalScrollPosition(i);
        if (this.mDisableIntervalMomentum) {
            predictFinalScrollPosition = getScrollY();
        }
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        List<Integer> list = this.mSnapOffsets;
        int i5 = 0;
        if (list != null) {
            int intValue = list.get(0).intValue();
            int intValue2 = ((Integer) GeneratedOutlineSupport1.outline25(this.mSnapOffsets, 1)).intValue();
            int i6 = maxScrollY;
            int i7 = 0;
            for (int i8 = 0; i8 < this.mSnapOffsets.size(); i8++) {
                int intValue3 = this.mSnapOffsets.get(i8).intValue();
                if (intValue3 <= predictFinalScrollPosition && predictFinalScrollPosition - intValue3 < predictFinalScrollPosition - i7) {
                    i7 = intValue3;
                }
                if (intValue3 >= predictFinalScrollPosition && intValue3 - predictFinalScrollPosition < i6 - predictFinalScrollPosition) {
                    i6 = intValue3;
                }
            }
            i2 = intValue2;
            min = i6;
            i3 = intValue;
            floor = i7;
        } else {
            double snapInterval = getSnapInterval();
            double d = predictFinalScrollPosition / snapInterval;
            floor = (int) (Math.floor(d) * snapInterval);
            min = Math.min((int) (Math.ceil(d) * snapInterval), maxScrollY);
            i2 = maxScrollY;
            i3 = 0;
        }
        int i9 = predictFinalScrollPosition - floor;
        int i10 = min - predictFinalScrollPosition;
        int i11 = i9 < i10 ? floor : min;
        if (!this.mSnapToEnd && predictFinalScrollPosition >= i2) {
            if (getScrollY() < i2) {
                i4 = i;
                predictFinalScrollPosition = i2;
            }
            i4 = i;
        } else if (!this.mSnapToStart && predictFinalScrollPosition <= i3) {
            if (getScrollY() > i3) {
                i4 = i;
                predictFinalScrollPosition = i3;
            }
            i4 = i;
        } else if (i > 0) {
            i4 = i + ((int) (i10 * 10.0d));
            predictFinalScrollPosition = min;
        } else if (i < 0) {
            int i12 = floor;
            i4 = i - ((int) (i9 * 10.0d));
            predictFinalScrollPosition = i12;
        } else {
            i4 = i;
            predictFinalScrollPosition = i11;
        }
        int min2 = Math.min(Math.max(0, predictFinalScrollPosition), maxScrollY);
        OverScroller overScroller = this.mScroller;
        if (overScroller != null) {
            this.mActivelyScrolling = true;
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            if (i4 == 0) {
                i4 = min2 - getScrollY();
            }
            int i13 = i4;
            if (min2 == 0 || min2 == maxScrollY) {
                i5 = height / 2;
            }
            overScroller.fling(scrollX, scrollY, 0, i13, 0, 0, min2, min2, 0, i5);
            postInvalidateOnAnimation();
            return;
        }
        reactSmoothScrollTo(getScrollX(), min2);
    }

    private int getMaxScrollY() {
        return Math.max(0, this.mContentView.getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
    }

    @Nullable
    private OverScroller getOverScrollerFromParent() {
        if (!sTriedToGetScrollerField) {
            sTriedToGetScrollerField = true;
            try {
                sScrollerField = ScrollView.class.getDeclaredField("mScroller");
                sScrollerField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                FLog.w(ReactConstants.TAG, "Failed to get mScroller field for ScrollView! This app will exhibit the bounce-back scrolling bug :(");
            }
        }
        Field field = sScrollerField;
        OverScroller overScroller = null;
        if (field != null) {
            try {
                Object obj = field.get(this);
                if (obj instanceof OverScroller) {
                    overScroller = (OverScroller) obj;
                } else {
                    FLog.w(ReactConstants.TAG, "Failed to cast mScroller field in ScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :(");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to get mScroller from ScrollView!", e);
            }
        }
        return overScroller;
    }

    private int getPostAnimationScrollX() {
        ValueAnimator valueAnimator = this.mScrollAnimator;
        return (valueAnimator == null || !valueAnimator.isRunning()) ? getScrollX() : this.mFinalAnimatedPositionScrollX;
    }

    private int getPostAnimationScrollY() {
        ValueAnimator valueAnimator = this.mScrollAnimator;
        return (valueAnimator == null || !valueAnimator.isRunning()) ? getScrollY() : this.mFinalAnimatedPositionScrollY;
    }

    private int getSnapInterval() {
        int i = this.mSnapInterval;
        return i != 0 ? i : getHeight();
    }

    private void handlePostTouchScrolling(int i, int i2) {
        if (this.mPostTouchRunnable != null) {
            return;
        }
        if (this.mSendMomentumEvents) {
            enableFpsListener();
            ReactScrollViewHelper.emitScrollMomentumBeginEvent(this, i, i2);
        }
        this.mActivelyScrolling = false;
        this.mPostTouchRunnable = new Runnable() { // from class: com.facebook.react.views.scroll.ReactScrollView.1
            private boolean mSnappingToPage = false;
            private boolean mRunning = true;
            private int mStableFrames = 0;

            @Override // java.lang.Runnable
            public void run() {
                if (ReactScrollView.this.mActivelyScrolling) {
                    ReactScrollView.this.mActivelyScrolling = false;
                    this.mStableFrames = 0;
                    this.mRunning = true;
                } else {
                    ReactScrollView.this.updateStateOnScroll();
                    this.mStableFrames++;
                    this.mRunning = this.mStableFrames < 3;
                    if (!ReactScrollView.this.mPagingEnabled || this.mSnappingToPage) {
                        if (ReactScrollView.this.mSendMomentumEvents) {
                            ReactScrollViewHelper.emitScrollMomentumEndEvent(ReactScrollView.this);
                        }
                        ReactScrollView.this.disableFpsListener();
                    } else {
                        this.mSnappingToPage = true;
                        ReactScrollView.this.flingAndSnap(0);
                        ViewCompat.postOnAnimationDelayed(ReactScrollView.this, this, 20L);
                    }
                }
                if (!this.mRunning) {
                    ReactScrollView.this.mPostTouchRunnable = null;
                } else {
                    ViewCompat.postOnAnimationDelayed(ReactScrollView.this, this, 20L);
                }
            }
        };
        ViewCompat.postOnAnimationDelayed(this, this.mPostTouchRunnable, 20L);
    }

    private boolean isScrollPerfLoggingEnabled() {
        String str;
        return (this.mFpsListener == null || (str = this.mScrollPerfTag) == null || str.isEmpty()) ? false : true;
    }

    private int predictFinalScrollPosition(int i) {
        OverScroller overScroller = new OverScroller(getContext());
        overScroller.setFriction(1.0f - this.mDecelerationRate);
        overScroller.fling(getPostAnimationScrollX(), getPostAnimationScrollY(), 0, i, 0, 0, 0, getMaxScrollY(), 0, ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
        return overScroller.getFinalY();
    }

    private void scrollToChild(View view) {
        Rect rect = new Rect();
        view.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(view, rect);
        int computeScrollDeltaToGetChildRectOnScreen = computeScrollDeltaToGetChildRectOnScreen(rect);
        if (computeScrollDeltaToGetChildRectOnScreen != 0) {
            scrollBy(0, computeScrollDeltaToGetChildRectOnScreen);
        }
    }

    private void setPendingContentOffsets(int i, int i2) {
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getWidth() != 0 && childAt.getHeight() != 0) {
            this.pendingContentOffsetX = -1;
            this.pendingContentOffsetY = -1;
            return;
        }
        this.pendingContentOffsetX = i;
        this.pendingContentOffsetY = i2;
    }

    private void smoothScrollAndSnap(int i) {
        double snapInterval = getSnapInterval();
        double postAnimationScrollY = getPostAnimationScrollY();
        double d = postAnimationScrollY / snapInterval;
        int floor = (int) Math.floor(d);
        int ceil = (int) Math.ceil(d);
        int round = (int) Math.round(d);
        int round2 = (int) Math.round(predictFinalScrollPosition(i) / snapInterval);
        if (i > 0 && ceil == floor) {
            ceil++;
        } else if (i < 0 && floor == ceil) {
            floor--;
        }
        if (i > 0 && round < ceil && round2 > floor) {
            round = ceil;
        } else if (i < 0 && round > floor && round2 < ceil) {
            round = floor;
        }
        double d2 = round * snapInterval;
        if (d2 != postAnimationScrollY) {
            this.mActivelyScrolling = true;
            reactSmoothScrollTo(getScrollX(), (int) d2);
        }
    }

    private void updateStateOnScroll(final int i, final int i2) {
        if (i == this.mLastStateUpdateScrollX && i2 == this.mLastStateUpdateScrollY) {
            return;
        }
        this.mLastStateUpdateScrollX = i;
        this.mLastStateUpdateScrollY = i2;
        this.mFabricViewStateManager.setState(new FabricViewStateManager.StateUpdateCallback() { // from class: com.facebook.react.views.scroll.ReactScrollView.4
            @Override // com.facebook.react.uimanager.FabricViewStateManager.StateUpdateCallback
            public WritableMap getStateUpdate() {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putDouble(ReactScrollView.CONTENT_OFFSET_LEFT, PixelUtil.toDIPFromPixel(i));
                writableNativeMap.putDouble(ReactScrollView.CONTENT_OFFSET_TOP, PixelUtil.toDIPFromPixel(i2));
                return writableNativeMap;
            }
        });
    }

    @Override // android.widget.ScrollView, android.view.View
    public void draw(Canvas canvas) {
        boolean z = false;
        if (this.mEndFillColor != 0) {
            View childAt = getChildAt(0);
            if (this.mEndBackground != null && childAt != null && childAt.getBottom() < getHeight()) {
                this.mEndBackground.setBounds(0, childAt.getBottom(), getWidth(), getHeight());
                this.mEndBackground.draw(canvas);
            }
        }
        getDrawingRect(this.mRect);
        String str = this.mOverflow;
        if (str.hashCode() != 466743410 || !str.equals("visible")) {
            z = true;
        }
        if (z) {
            canvas.clipRect(this.mRect);
        }
        super.draw(canvas);
    }

    @Override // android.widget.ScrollView
    public boolean executeKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (this.mScrollEnabled || !(keyCode == 19 || keyCode == 20)) {
            return super.executeKeyEvent(keyEvent);
        }
        return false;
    }

    public void flashScrollIndicators() {
        awakenScrollBars();
    }

    @Override // android.widget.ScrollView
    public void fling(int i) {
        float signum = Math.signum(this.mOnScrollDispatchHelper.getYFlingVelocity());
        if (signum == 0.0f) {
            signum = Math.signum(i);
        }
        int abs = (int) (Math.abs(i) * signum);
        if (this.mPagingEnabled) {
            flingAndSnap(abs);
        } else if (this.mScroller != null) {
            this.mScroller.fling(getScrollX(), getScrollY(), 0, abs, 0, 0, 0, Integer.MAX_VALUE, 0, ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            super.fling(abs);
        }
        handlePostTouchScrolling(0, abs);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean getChildVisibleRect(View view, Rect rect, Point point) {
        if (ReactFeatureFlags.clipChildRectsIfOverflowIsHidden) {
            return ReactClippingViewGroupHelper.getChildVisibleRectHelper(view, rect, point, this, this.mOverflow);
        }
        return super.getChildVisibleRect(view, rect, point);
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void getClippingRect(Rect rect) {
        rect.set((Rect) Assertions.assertNotNull(this.mClippingRect));
    }

    @Override // com.facebook.react.uimanager.FabricViewStateManager.HasFabricViewStateManager
    public FabricViewStateManager getFabricViewStateManager() {
        return this.mFabricViewStateManager;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public void onChildViewAdded(View view, View view2) {
        this.mContentView = view2;
        this.mContentView.addOnLayoutChangeListener(this);
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public void onChildViewRemoved(View view, View view2) {
        this.mContentView.removeOnLayoutChangeListener(this);
        this.mContentView = null;
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        try {
            if (super.onInterceptTouchEvent(motionEvent)) {
                NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
                ReactScrollViewHelper.emitScrollBeginDragEvent(this);
                this.mDragging = true;
                enableFpsListener();
                return true;
            }
        } catch (IllegalArgumentException e) {
            FLog.w(ReactConstants.TAG, "Error intercepting touch event.", e);
        }
        return false;
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = this.pendingContentOffsetX;
        if (i5 == -1) {
            i5 = getScrollX();
        }
        int i6 = this.pendingContentOffsetY;
        if (i6 == -1) {
            i6 = getScrollY();
        }
        reactScrollTo(i5, i6);
        ReactScrollViewHelper.emitLayoutEvent(this);
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.mContentView == null) {
            return;
        }
        int scrollY = getScrollY();
        int maxScrollY = getMaxScrollY();
        if (scrollY <= maxScrollY) {
            return;
        }
        reactScrollTo(getScrollX(), maxScrollY);
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        int maxScrollY;
        OverScroller overScroller = this.mScroller;
        if (overScroller != null && this.mContentView != null && !overScroller.isFinished() && this.mScroller.getCurrY() != this.mScroller.getFinalY() && i2 >= (maxScrollY = getMaxScrollY())) {
            this.mScroller.abortAnimation();
            i2 = maxScrollY;
        }
        super.onOverScrolled(i, i2, z, z2);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.mActivelyScrolling = true;
        if (this.mOnScrollDispatchHelper.onScrollChanged(i, i2)) {
            if (this.mRemoveClippedSubviews) {
                updateClippingRect();
            }
            ReactScrollViewHelper.emitScrollEvent(this, this.mOnScrollDispatchHelper.getXFlingVelocity(), this.mOnScrollDispatchHelper.getYFlingVelocity());
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        this.mVelocityHelper.calculateVelocity(motionEvent);
        if ((motionEvent.getAction() & 255) == 1 && this.mDragging) {
            updateStateOnScroll();
            float xVelocity = this.mVelocityHelper.getXVelocity();
            float yVelocity = this.mVelocityHelper.getYVelocity();
            ReactScrollViewHelper.emitScrollEndDragEvent(this, xVelocity, yVelocity);
            this.mDragging = false;
            handlePostTouchScrolling(Math.round(xVelocity), Math.round(yVelocity));
        }
        return super.onTouchEvent(motionEvent);
    }

    public void reactScrollTo(int i, int i2) {
        scrollTo(i, i2);
        updateStateOnScroll(i, i2);
        setPendingContentOffsets(i, i2);
    }

    public void reactSmoothScrollTo(int i, int i2) {
        ValueAnimator valueAnimator = this.mScrollAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.mFinalAnimatedPositionScrollX = i;
        this.mFinalAnimatedPositionScrollY = i2;
        this.mScrollAnimator = ObjectAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofInt("scrollX", getScrollX(), i), PropertyValuesHolder.ofInt("scrollY", getScrollY(), i2));
        this.mScrollAnimator.setDuration(ReactScrollViewHelper.getDefaultScrollAnimationDuration(getContext()));
        this.mScrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.facebook.react.views.scroll.ReactScrollView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                ReactScrollView.this.scrollTo(((Integer) valueAnimator2.getAnimatedValue("scrollX")).intValue(), ((Integer) valueAnimator2.getAnimatedValue("scrollY")).intValue());
            }
        });
        this.mScrollAnimator.addListener(new Animator.AnimatorListener() { // from class: com.facebook.react.views.scroll.ReactScrollView.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ReactScrollView.this.mFinalAnimatedPositionScrollX = -1;
                ReactScrollView.this.mFinalAnimatedPositionScrollY = -1;
                ReactScrollView.this.mScrollAnimator = null;
                ReactScrollView.this.updateStateOnScroll();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        this.mScrollAnimator.start();
        updateStateOnScroll(i, i2);
        setPendingContentOffsets(i, i2);
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (view2 != null) {
            scrollToChild(view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mReactBackgroundManager.setBackgroundColor(i);
    }

    public void setBorderColor(int i, float f, float f2) {
        this.mReactBackgroundManager.setBorderColor(i, f, f2);
    }

    public void setBorderRadius(float f) {
        this.mReactBackgroundManager.setBorderRadius(f);
    }

    public void setBorderStyle(@Nullable String str) {
        this.mReactBackgroundManager.setBorderStyle(str);
    }

    public void setBorderWidth(int i, float f) {
        this.mReactBackgroundManager.setBorderWidth(i, f);
    }

    public void setDecelerationRate(float f) {
        this.mDecelerationRate = f;
        OverScroller overScroller = this.mScroller;
        if (overScroller != null) {
            overScroller.setFriction(1.0f - this.mDecelerationRate);
        }
    }

    public void setDisableIntervalMomentum(boolean z) {
        this.mDisableIntervalMomentum = z;
    }

    public void setEndFillColor(int i) {
        if (i != this.mEndFillColor) {
            this.mEndFillColor = i;
            this.mEndBackground = new ColorDrawable(this.mEndFillColor);
        }
    }

    public void setOverflow(String str) {
        this.mOverflow = str;
        invalidate();
    }

    public void setPagingEnabled(boolean z) {
        this.mPagingEnabled = z;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void setRemoveClippedSubviews(boolean z) {
        if (z && this.mClippingRect == null) {
            this.mClippingRect = new Rect();
        }
        this.mRemoveClippedSubviews = z;
        updateClippingRect();
    }

    public void setScrollEnabled(boolean z) {
        this.mScrollEnabled = z;
    }

    public void setScrollPerfTag(@Nullable String str) {
        this.mScrollPerfTag = str;
    }

    public void setSendMomentumEvents(boolean z) {
        this.mSendMomentumEvents = z;
    }

    public void setSnapInterval(int i) {
        this.mSnapInterval = i;
    }

    public void setSnapOffsets(List<Integer> list) {
        this.mSnapOffsets = list;
    }

    public void setSnapToEnd(boolean z) {
        this.mSnapToEnd = z;
    }

    public void setSnapToStart(boolean z) {
        this.mSnapToStart = z;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void updateClippingRect() {
        if (!this.mRemoveClippedSubviews) {
            return;
        }
        Assertions.assertNotNull(this.mClippingRect);
        ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
        View childAt = getChildAt(0);
        if (!(childAt instanceof ReactClippingViewGroup)) {
            return;
        }
        ((ReactClippingViewGroup) childAt).updateClippingRect();
    }

    public ReactScrollView(ReactContext reactContext, @Nullable FpsListener fpsListener) {
        super(reactContext);
        this.mOnScrollDispatchHelper = new OnScrollDispatchHelper();
        this.mVelocityHelper = new VelocityHelper();
        this.mRect = new Rect();
        this.mOverflow = "hidden";
        this.mPagingEnabled = false;
        this.mScrollEnabled = true;
        this.mFpsListener = null;
        this.mEndFillColor = 0;
        this.mDisableIntervalMomentum = false;
        this.mSnapInterval = 0;
        this.mDecelerationRate = 0.985f;
        this.mSnapToStart = true;
        this.mSnapToEnd = true;
        this.pendingContentOffsetX = -1;
        this.pendingContentOffsetY = -1;
        this.mFabricViewStateManager = new FabricViewStateManager();
        this.mLastStateUpdateScrollX = -1;
        this.mLastStateUpdateScrollY = -1;
        this.mFpsListener = fpsListener;
        this.mReactBackgroundManager = new ReactViewBackgroundManager(this);
        this.mScroller = getOverScrollerFromParent();
        setOnHierarchyChangeListener(this);
        setScrollBarStyle(33554432);
    }

    public void setBorderRadius(float f, int i) {
        this.mReactBackgroundManager.setBorderRadius(f, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStateOnScroll() {
        updateStateOnScroll(getScrollX(), getScrollY());
    }
}
