package com.facebook.react.views.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.animation.Animation;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.configuration.DefaultRecordChecker;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.touch.OnInterceptTouchEventListener;
import com.facebook.react.touch.ReactHitSlopView;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ReactZIndexedViewGroup;
import com.facebook.react.uimanager.RootView;
import com.facebook.react.uimanager.RootViewUtil;
import com.facebook.react.uimanager.ViewGroupDrawingOrderHelper;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.views.view.ReactViewBackgroundDrawable;
import com.facebook.yoga.YogaConstants;
@TargetApi(21)
/* loaded from: classes2.dex */
public class ReactViewGroup extends ViewGroup implements ReactInterceptingViewGroup, ReactClippingViewGroup, ReactPointerEventsView, ReactHitSlopView, ReactZIndexedViewGroup {
    private static final int ARRAY_CAPACITY_INCREMENT = 12;
    private static final int DEFAULT_BACKGROUND_COLOR = 0;
    private static final ViewGroup.LayoutParams sDefaultLayoutParam = new ViewGroup.LayoutParams(0, 0);
    private static final Rect sHelperRect = new Rect();
    @Nullable
    private View[] mAllChildren;
    private int mAllChildrenCount;
    private float mBackfaceOpacity;
    private String mBackfaceVisibility;
    @Nullable
    private ChildrenLayoutChangeListener mChildrenLayoutChangeListener;
    @Nullable
    private Rect mClippingRect;
    private final ViewGroupDrawingOrderHelper mDrawingOrderHelper;
    @Nullable
    private Rect mHitSlopRect;
    private int mLayoutDirection;
    private boolean mNeedsOffscreenAlphaCompositing;
    @Nullable
    private OnInterceptTouchEventListener mOnInterceptTouchEventListener;
    @Nullable
    private String mOverflow;
    @Nullable
    private Path mPath;
    private PointerEvents mPointerEvents;
    @Nullable
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    private boolean mRemoveClippedSubviews;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class ChildrenLayoutChangeListener implements View.OnLayoutChangeListener {
        private final ReactViewGroup mParent;

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (this.mParent.getRemoveClippedSubviews()) {
                this.mParent.updateSubviewClipStatus(view);
            }
        }

        private ChildrenLayoutChangeListener(ReactViewGroup reactViewGroup) {
            this.mParent = reactViewGroup;
        }
    }

    public ReactViewGroup(Context context) {
        super(context);
        this.mRemoveClippedSubviews = false;
        this.mAllChildren = null;
        this.mPointerEvents = PointerEvents.AUTO;
        this.mNeedsOffscreenAlphaCompositing = false;
        this.mBackfaceOpacity = 1.0f;
        this.mBackfaceVisibility = "visible";
        setClipChildren(false);
        this.mDrawingOrderHelper = new ViewGroupDrawingOrderHelper(this);
    }

    private void addInArray(View view, int i) {
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        int i2 = this.mAllChildrenCount;
        int length = viewArr.length;
        if (i == i2) {
            if (length == i2) {
                this.mAllChildren = new View[length + 12];
                System.arraycopy(viewArr, 0, this.mAllChildren, 0, length);
                viewArr = this.mAllChildren;
            }
            int i3 = this.mAllChildrenCount;
            this.mAllChildrenCount = i3 + 1;
            viewArr[i3] = view;
        } else if (i < i2) {
            if (length == i2) {
                this.mAllChildren = new View[length + 12];
                System.arraycopy(viewArr, 0, this.mAllChildren, 0, i);
                System.arraycopy(viewArr, i, this.mAllChildren, i + 1, i2 - i);
                viewArr = this.mAllChildren;
            } else {
                System.arraycopy(viewArr, i, viewArr, i + 1, i2 - i);
            }
            viewArr[i] = view;
            this.mAllChildrenCount++;
        } else {
            throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline53("index=", i, " count=", i2));
        }
    }

    private boolean customDrawOrderDisabled() {
        if (getId() != -1 && ViewUtil.getUIManagerType(getId()) == 2) {
            return ReactFeatureFlags.disableCustomDrawOrderFabric;
        }
        return false;
    }

    private void dispatchOverflowDraw(Canvas canvas) {
        float f;
        float f2;
        boolean z;
        float f3;
        float f4;
        String str = this.mOverflow;
        if (str != null) {
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1217487446) {
                if (hashCode == 466743410 && str.equals("visible")) {
                    c = 0;
                }
            } else if (str.equals("hidden")) {
                c = 1;
            }
            if (c == 0) {
                Path path = this.mPath;
                if (path == null) {
                    return;
                }
                path.rewind();
            } else if (c != 1) {
            } else {
                float width = getWidth();
                float height = getHeight();
                ReactViewBackgroundDrawable reactViewBackgroundDrawable = this.mReactBackgroundDrawable;
                if (reactViewBackgroundDrawable != null) {
                    RectF directionAwareBorderInsets = reactViewBackgroundDrawable.getDirectionAwareBorderInsets();
                    if (directionAwareBorderInsets.top > 0.0f || directionAwareBorderInsets.left > 0.0f || directionAwareBorderInsets.bottom > 0.0f || directionAwareBorderInsets.right > 0.0f) {
                        f = directionAwareBorderInsets.left + 0.0f;
                        f2 = directionAwareBorderInsets.top + 0.0f;
                        width -= directionAwareBorderInsets.right;
                        height -= directionAwareBorderInsets.bottom;
                    } else {
                        f = 0.0f;
                        f2 = 0.0f;
                    }
                    float fullBorderRadius = this.mReactBackgroundDrawable.getFullBorderRadius();
                    float borderRadiusOrDefaultTo = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo(fullBorderRadius, ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_LEFT);
                    float borderRadiusOrDefaultTo2 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo(fullBorderRadius, ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_RIGHT);
                    float borderRadiusOrDefaultTo3 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo(fullBorderRadius, ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_LEFT);
                    float borderRadiusOrDefaultTo4 = this.mReactBackgroundDrawable.getBorderRadiusOrDefaultTo(fullBorderRadius, ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_RIGHT);
                    boolean z2 = this.mLayoutDirection == 1;
                    float borderRadius = this.mReactBackgroundDrawable.getBorderRadius(ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_START);
                    float borderRadius2 = this.mReactBackgroundDrawable.getBorderRadius(ReactViewBackgroundDrawable.BorderRadiusLocation.TOP_END);
                    float borderRadius3 = this.mReactBackgroundDrawable.getBorderRadius(ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_START);
                    float f5 = borderRadiusOrDefaultTo4;
                    float borderRadius4 = this.mReactBackgroundDrawable.getBorderRadius(ReactViewBackgroundDrawable.BorderRadiusLocation.BOTTOM_END);
                    float f6 = borderRadiusOrDefaultTo;
                    if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(getContext())) {
                        if (!YogaConstants.isUndefined(borderRadius)) {
                            f6 = borderRadius;
                        }
                        if (YogaConstants.isUndefined(borderRadius2)) {
                            borderRadius2 = borderRadiusOrDefaultTo2;
                        }
                        if (YogaConstants.isUndefined(borderRadius3)) {
                            borderRadius3 = borderRadiusOrDefaultTo3;
                        }
                        if (!YogaConstants.isUndefined(borderRadius4)) {
                            f5 = borderRadius4;
                        }
                        float f7 = z2 ? borderRadius2 : f6;
                        if (!z2) {
                            f6 = borderRadius2;
                        }
                        float f8 = z2 ? f5 : borderRadius3;
                        borderRadius4 = z2 ? borderRadius3 : f5;
                        f4 = 0.0f;
                        float f9 = f7;
                        f3 = f8;
                        borderRadius2 = f6;
                        f6 = f9;
                    } else {
                        float f10 = z2 ? borderRadius2 : borderRadius;
                        if (z2) {
                            borderRadius2 = borderRadius;
                        }
                        f3 = z2 ? borderRadius4 : borderRadius3;
                        if (z2) {
                            borderRadius4 = borderRadius3;
                        }
                        if (!YogaConstants.isUndefined(f10)) {
                            f6 = f10;
                        }
                        if (YogaConstants.isUndefined(borderRadius2)) {
                            borderRadius2 = borderRadiusOrDefaultTo2;
                        }
                        if (YogaConstants.isUndefined(f3)) {
                            f3 = borderRadiusOrDefaultTo3;
                        }
                        if (YogaConstants.isUndefined(borderRadius4)) {
                            borderRadius4 = f5;
                        }
                        f4 = 0.0f;
                    }
                    if (f6 > f4 || borderRadius2 > f4 || borderRadius4 > f4 || f3 > f4) {
                        if (this.mPath == null) {
                            this.mPath = new Path();
                        }
                        this.mPath.rewind();
                        z = true;
                        this.mPath.addRoundRect(new RectF(f, f2, width, height), new float[]{Math.max(f6 - directionAwareBorderInsets.left, 0.0f), Math.max(f6 - directionAwareBorderInsets.top, 0.0f), Math.max(borderRadius2 - directionAwareBorderInsets.right, 0.0f), Math.max(borderRadius2 - directionAwareBorderInsets.top, 0.0f), Math.max(borderRadius4 - directionAwareBorderInsets.right, 0.0f), Math.max(borderRadius4 - directionAwareBorderInsets.bottom, 0.0f), Math.max(f3 - directionAwareBorderInsets.left, 0.0f), Math.max(f3 - directionAwareBorderInsets.bottom, 0.0f)}, Path.Direction.CW);
                        canvas.clipPath(this.mPath);
                    } else {
                        z = false;
                    }
                } else {
                    f = 0.0f;
                    f2 = 0.0f;
                    z = false;
                }
                if (z) {
                    return;
                }
                canvas.clipRect(new RectF(f, f2, width, height));
            }
        }
    }

    private ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable(getContext());
            Drawable background = getBackground();
            updateBackgroundDrawable(null);
            if (background == null) {
                updateBackgroundDrawable(this.mReactBackgroundDrawable);
            } else {
                updateBackgroundDrawable(new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, background}));
            }
            this.mLayoutDirection = I18nUtil.getInstance().isRTL(getContext()) ? 1 : 0;
            this.mReactBackgroundDrawable.setResolvedLayoutDirection(this.mLayoutDirection);
        }
        return this.mReactBackgroundDrawable;
    }

    private int indexOfChildInAllChildren(View view) {
        int i = this.mAllChildrenCount;
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        for (int i2 = 0; i2 < i; i2++) {
            if (viewArr[i2] == view) {
                return i2;
            }
        }
        return -1;
    }

    private void removeFromArray(int i) {
        View[] viewArr = (View[]) Assertions.assertNotNull(this.mAllChildren);
        int i2 = this.mAllChildrenCount;
        if (i == i2 - 1) {
            int i3 = i2 - 1;
            this.mAllChildrenCount = i3;
            viewArr[i3] = null;
        } else if (i >= 0 && i < i2) {
            System.arraycopy(viewArr, i + 1, viewArr, i, (i2 - i) - 1);
            int i4 = this.mAllChildrenCount - 1;
            this.mAllChildrenCount = i4;
            viewArr[i4] = null;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    private void updateBackgroundDrawable(Drawable drawable) {
        super.setBackground(drawable);
    }

    private void updateClippingToRect(Rect rect) {
        Assertions.assertNotNull(this.mAllChildren);
        int i = 0;
        for (int i2 = 0; i2 < this.mAllChildrenCount; i2++) {
            updateSubviewClipStatus(rect, i2, i);
            if (this.mAllChildren[i2].getParent() == null) {
                i++;
            }
        }
    }

    private void updateSubviewClipStatus(Rect rect, int i, int i2) {
        UiThreadUtil.assertOnUiThread();
        View view = ((View[]) Assertions.assertNotNull(this.mAllChildren))[i];
        sHelperRect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        Rect rect2 = sHelperRect;
        boolean intersects = rect.intersects(rect2.left, rect2.top, rect2.right, rect2.bottom);
        Animation animation = view.getAnimation();
        boolean z = true;
        boolean z2 = animation != null && !animation.hasEnded();
        if (!intersects && view.getParent() != null && !z2) {
            super.removeViewsInLayout(i - i2, 1);
        } else if (intersects && view.getParent() == null) {
            super.addViewInLayout(view, i - i2, sDefaultLayoutParam, true);
            invalidate();
        } else if (!intersects) {
            z = false;
        }
        if (!z || !(view instanceof ReactClippingViewGroup)) {
            return;
        }
        ReactClippingViewGroup reactClippingViewGroup = (ReactClippingViewGroup) view;
        if (!reactClippingViewGroup.getRemoveClippedSubviews()) {
            return;
        }
        reactClippingViewGroup.updateClippingRect();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (!customDrawOrderDisabled()) {
            this.mDrawingOrderHelper.handleAddView(view);
            setChildrenDrawingOrderEnabled(this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder());
        } else {
            setChildrenDrawingOrderEnabled(false);
        }
        super.addView(view, i, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addViewWithSubviewClippingEnabled(View view, int i) {
        addViewWithSubviewClippingEnabled(view, i, sDefaultLayoutParam);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        int childCount = getChildCount();
        try {
            dispatchOverflowDraw(canvas);
            super.dispatchDraw(canvas);
        } catch (NullPointerException | StackOverflowError e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception thrown when executing ReactViewGroup.dispatchDraw method on ReactViewGroup[");
            outline107.append(getId());
            outline107.append("]");
            FLog.e(ReactConstants.TAG, outline107.toString(), e);
            int childCount2 = getChildCount();
            int i = 0;
            FLog.e(ReactConstants.TAG, "Initial Child Count: %d / final: %d", Integer.valueOf(childCount), Integer.valueOf(childCount2));
            FLog.e(ReactConstants.TAG, "Child List:");
            while (true) {
                int i2 = -1337;
                if (i >= childCount2) {
                    break;
                }
                View childAt = getChildAt(i);
                StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Child #", i, RealTimeTextConstants.COLON_SPACE);
                if (childAt != null) {
                    i2 = childAt.getId();
                }
                outline109.append(i2);
                outline109.append(" - ");
                outline109.append(childAt != null ? childAt.toString() : DefaultRecordChecker.Regex.EMPTY);
                FLog.e(ReactConstants.TAG, outline109.toString());
                i++;
            }
            FLog.e(ReactConstants.TAG, "Ancestor List:");
            for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
                ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                StringBuilder outline1092 = GeneratedOutlineSupport1.outline109("Ancestor[", viewGroup != null ? viewGroup.getId() : -1337, "]: ");
                outline1092.append(parent.toString());
                FLog.e(ReactConstants.TAG, outline1092.toString());
            }
            RootView rootView = RootViewUtil.getRootView(this);
            if (rootView != null) {
                rootView.handleException(e);
            } else if (getContext() instanceof ReactContext) {
                ((ReactContext) getContext()).handleException(new IllegalViewOperationException("StackOverflowException", this, e));
            } else {
                throw e;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(23)
    public void dispatchProvideStructure(ViewStructure viewStructure) {
        try {
            super.dispatchProvideStructure(viewStructure);
        } catch (NullPointerException e) {
            FLog.e(ReactConstants.TAG, "NullPointerException when executing dispatchProvideStructure", e);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSetPressed(boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getAllChildrenCount() {
        return this.mAllChildrenCount;
    }

    @VisibleForTesting
    public int getBackgroundColor() {
        if (getBackground() != null) {
            return ((ReactViewBackgroundDrawable) getBackground()).getColor();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View getChildAtWithSubviewClippingEnabled(int i) {
        return ((View[]) Assertions.assertNotNull(this.mAllChildren))[i];
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i, int i2) {
        return this.mDrawingOrderHelper.getChildDrawingOrder(i, i2);
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
        rect.set(this.mClippingRect);
    }

    @Override // com.facebook.react.touch.ReactHitSlopView
    @Nullable
    public Rect getHitSlopRect() {
        return this.mHitSlopRect;
    }

    @Nullable
    public String getOverflow() {
        return this.mOverflow;
    }

    @Override // com.facebook.react.uimanager.ReactPointerEventsView
    public PointerEvents getPointerEvents() {
        return this.mPointerEvents;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }

    @Override // com.facebook.react.uimanager.ReactZIndexedViewGroup
    public int getZIndexMappedChildIndex(int i) {
        return this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder() ? this.mDrawingOrderHelper.getChildDrawingOrder(getChildCount(), i) : i;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return this.mNeedsOffscreenAlphaCompositing;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        PointerEvents pointerEvents;
        OnInterceptTouchEventListener onInterceptTouchEventListener = this.mOnInterceptTouchEventListener;
        if ((onInterceptTouchEventListener != null && onInterceptTouchEventListener.onInterceptTouchEvent(this, motionEvent)) || (pointerEvents = this.mPointerEvents) == PointerEvents.NONE || pointerEvents == PointerEvents.BOX_ONLY) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        MeasureSpecAssertions.assertExplicitMeasureSpec(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        ReactViewBackgroundDrawable reactViewBackgroundDrawable = this.mReactBackgroundDrawable;
        if (reactViewBackgroundDrawable != null) {
            reactViewBackgroundDrawable.setResolvedLayoutDirection(this.mLayoutDirection);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        PointerEvents pointerEvents = this.mPointerEvents;
        return (pointerEvents == PointerEvents.NONE || pointerEvents == PointerEvents.BOX_NONE) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeAllViewsWithSubviewClippingEnabled() {
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mAllChildren);
        for (int i = 0; i < this.mAllChildrenCount; i++) {
            this.mAllChildren[i].removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        }
        removeAllViewsInLayout();
        this.mAllChildrenCount = 0;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        UiThreadUtil.assertOnUiThread();
        if (!customDrawOrderDisabled()) {
            this.mDrawingOrderHelper.handleRemoveView(view);
            setChildrenDrawingOrderEnabled(this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder());
        } else {
            setChildrenDrawingOrderEnabled(false);
        }
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i) {
        UiThreadUtil.assertOnUiThread();
        if (!customDrawOrderDisabled()) {
            this.mDrawingOrderHelper.handleRemoveView(getChildAt(i));
            setChildrenDrawingOrderEnabled(this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder());
        } else {
            setChildrenDrawingOrderEnabled(false);
        }
        super.removeViewAt(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeViewWithSubviewClippingEnabled(View view) {
        UiThreadUtil.assertOnUiThread();
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        view.removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        int indexOfChildInAllChildren = indexOfChildInAllChildren(view);
        if (this.mAllChildren[indexOfChildInAllChildren].getParent() != null) {
            int i = 0;
            for (int i2 = 0; i2 < indexOfChildInAllChildren; i2++) {
                if (this.mAllChildren[i2].getParent() == null) {
                    i++;
                }
            }
            super.removeViewsInLayout(indexOfChildInAllChildren - i, 1);
        }
        removeFromArray(indexOfChildInAllChildren);
    }

    @Override // android.view.View, android.view.ViewParent
    @SuppressLint({"MissingSuperCall"})
    public void requestLayout() {
    }

    public void setBackfaceVisibility(String str) {
        this.mBackfaceVisibility = str;
        setBackfaceVisibilityDependantOpacity();
    }

    public void setBackfaceVisibilityDependantOpacity() {
        if (this.mBackfaceVisibility.equals("visible")) {
            setAlpha(this.mBackfaceOpacity);
            return;
        }
        float rotationX = getRotationX();
        float rotationY = getRotationY();
        if (rotationX >= -90.0f && rotationX < 90.0f && rotationY >= -90.0f && rotationY < 90.0f) {
            setAlpha(this.mBackfaceOpacity);
        } else {
            setAlpha(0.0f);
        }
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        throw new UnsupportedOperationException("This method is not supported for ReactViewGroup instances");
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (i == 0 && this.mReactBackgroundDrawable == null) {
            return;
        }
        getOrCreateReactViewBackground().setColor(i);
    }

    public void setBorderColor(int i, float f, float f2) {
        getOrCreateReactViewBackground().setBorderColor(i, f, f2);
    }

    public void setBorderRadius(float f) {
        getOrCreateReactViewBackground().setRadius(f);
    }

    public void setBorderStyle(@Nullable String str) {
        getOrCreateReactViewBackground().setBorderStyle(str);
    }

    public void setBorderWidth(int i, float f) {
        getOrCreateReactViewBackground().setBorderWidth(i, f);
    }

    public void setHitSlopRect(@Nullable Rect rect) {
        this.mHitSlopRect = rect;
    }

    public void setNeedsOffscreenAlphaCompositing(boolean z) {
        this.mNeedsOffscreenAlphaCompositing = z;
    }

    @Override // com.facebook.react.touch.ReactInterceptingViewGroup
    public void setOnInterceptTouchEventListener(OnInterceptTouchEventListener onInterceptTouchEventListener) {
        this.mOnInterceptTouchEventListener = onInterceptTouchEventListener;
    }

    public void setOpacityIfPossible(float f) {
        this.mBackfaceOpacity = f;
        setBackfaceVisibilityDependantOpacity();
    }

    public void setOverflow(String str) {
        this.mOverflow = str;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPointerEvents(PointerEvents pointerEvents) {
        this.mPointerEvents = pointerEvents;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void setRemoveClippedSubviews(boolean z) {
        if (z == this.mRemoveClippedSubviews) {
            return;
        }
        this.mRemoveClippedSubviews = z;
        if (z) {
            this.mClippingRect = new Rect();
            ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
            this.mAllChildrenCount = getChildCount();
            this.mAllChildren = new View[Math.max(12, this.mAllChildrenCount)];
            this.mChildrenLayoutChangeListener = new ChildrenLayoutChangeListener();
            for (int i = 0; i < this.mAllChildrenCount; i++) {
                View childAt = getChildAt(i);
                this.mAllChildren[i] = childAt;
                childAt.addOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
            }
            updateClippingRect();
            return;
        }
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        Assertions.assertNotNull(this.mChildrenLayoutChangeListener);
        for (int i2 = 0; i2 < this.mAllChildrenCount; i2++) {
            this.mAllChildren[i2].removeOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
        }
        getDrawingRect(this.mClippingRect);
        updateClippingToRect(this.mClippingRect);
        this.mAllChildren = null;
        this.mClippingRect = null;
        this.mAllChildrenCount = 0;
        this.mChildrenLayoutChangeListener = null;
    }

    public void setTranslucentBackgroundDrawable(@Nullable Drawable drawable) {
        updateBackgroundDrawable(null);
        ReactViewBackgroundDrawable reactViewBackgroundDrawable = this.mReactBackgroundDrawable;
        if (reactViewBackgroundDrawable != null && drawable != null) {
            updateBackgroundDrawable(new LayerDrawable(new Drawable[]{reactViewBackgroundDrawable, drawable}));
        } else if (drawable == null) {
        } else {
            updateBackgroundDrawable(drawable);
        }
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void updateClippingRect() {
        if (!this.mRemoveClippedSubviews) {
            return;
        }
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
        updateClippingToRect(this.mClippingRect);
    }

    @Override // com.facebook.react.uimanager.ReactZIndexedViewGroup
    public void updateDrawingOrder() {
        if (customDrawOrderDisabled()) {
            return;
        }
        this.mDrawingOrderHelper.update();
        setChildrenDrawingOrderEnabled(this.mDrawingOrderHelper.shouldEnableCustomDrawingOrder());
        invalidate();
    }

    void addViewWithSubviewClippingEnabled(View view, int i, ViewGroup.LayoutParams layoutParams) {
        Assertions.assertCondition(this.mRemoveClippedSubviews);
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        addInArray(view, i);
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (this.mAllChildren[i3].getParent() == null) {
                i2++;
            }
        }
        updateSubviewClipStatus(this.mClippingRect, i, i2);
        view.addOnLayoutChangeListener(this.mChildrenLayoutChangeListener);
    }

    public void setBorderRadius(float f, int i) {
        getOrCreateReactViewBackground().setRadius(f, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSubviewClipStatus(View view) {
        if (!this.mRemoveClippedSubviews || getParent() == null) {
            return;
        }
        Assertions.assertNotNull(this.mClippingRect);
        Assertions.assertNotNull(this.mAllChildren);
        sHelperRect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        Rect rect = this.mClippingRect;
        Rect rect2 = sHelperRect;
        if (rect.intersects(rect2.left, rect2.top, rect2.right, rect2.bottom) == (view.getParent() != null)) {
            return;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.mAllChildrenCount; i2++) {
            View[] viewArr = this.mAllChildren;
            if (viewArr[i2] == view) {
                updateSubviewClipStatus(this.mClippingRect, i2, i);
                return;
            }
            if (viewArr[i2].getParent() == null) {
                i++;
            }
        }
    }
}
