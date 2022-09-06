package com.github.florent37.viewtooltip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.StringRes;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
/* loaded from: classes2.dex */
public class ViewTooltip {
    private View rootView;
    private Runnable showRunnable;
    private String testId;
    private final TooltipView tooltip_view;
    private final View view;

    /* renamed from: com.github.florent37.viewtooltip.ViewTooltip$4  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$github$florent37$viewtooltip$ViewTooltip$ALIGN = new int[ALIGN.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$github$florent37$viewtooltip$ViewTooltip$Position;

        static {
            try {
                $SwitchMap$com$github$florent37$viewtooltip$ViewTooltip$ALIGN[ALIGN.END.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$github$florent37$viewtooltip$ViewTooltip$ALIGN[ALIGN.CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$github$florent37$viewtooltip$ViewTooltip$Position = new int[Position.values().length];
            try {
                $SwitchMap$com$github$florent37$viewtooltip$ViewTooltip$Position[Position.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$github$florent37$viewtooltip$ViewTooltip$Position[Position.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$github$florent37$viewtooltip$ViewTooltip$Position[Position.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$github$florent37$viewtooltip$ViewTooltip$Position[Position.RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public enum ALIGN {
        START,
        CENTER,
        END
    }

    /* loaded from: classes2.dex */
    public interface ListenerDisplay {
        void onDisplay(View view);
    }

    /* loaded from: classes2.dex */
    public interface ListenerHide {
        void onHide(View view);
    }

    /* loaded from: classes2.dex */
    public enum Position {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM
    }

    /* loaded from: classes2.dex */
    public interface TooltipAnimation {
        void animateEnter(View view, Animator.AnimatorListener animatorListener);

        void animateExit(View view, Animator.AnimatorListener animatorListener);
    }

    private ViewTooltip(MyContext myContext, View view) {
        this.testId = null;
        this.view = view;
        this.tooltip_view = new TooltipView(myContext.getContext());
        initializeAccessibilityDelegate();
        initializeOnScrollChangeListener();
    }

    private void cleanUpRunnable() {
        Runnable runnable;
        View view = this.view;
        if (view == null || (runnable = this.showRunnable) == null) {
            return;
        }
        view.removeCallbacks(runnable);
    }

    private NestedScrollView findScrollParent(View view) {
        if (view.getParent() == null || !(view.getParent() instanceof View)) {
            return null;
        }
        if (view.getParent() instanceof NestedScrollView) {
            return (NestedScrollView) view.getParent();
        }
        return findScrollParent((View) view.getParent());
    }

    private static Activity getActivityContext(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    private void initializeAccessibilityDelegate() {
        ViewCompat.setAccessibilityDelegate(this.tooltip_view, new AccessibilityDelegateCompat() { // from class: com.github.florent37.viewtooltip.ViewTooltip.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                if (ViewTooltip.this.testId != null) {
                    accessibilityNodeInfoCompat.setViewIdResourceName(ViewTooltip.this.testId);
                }
            }
        });
    }

    private void initializeOnScrollChangeListener() {
        NestedScrollView findScrollParent = findScrollParent(this.view);
        if (findScrollParent != null) {
            findScrollParent.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: com.github.florent37.viewtooltip.ViewTooltip.2
                @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
                public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                    ViewTooltip.this.tooltip_view.setTranslationY(ViewTooltip.this.tooltip_view.getTranslationY() - (i2 - i4));
                }
            });
        }
    }

    public static ViewTooltip on(View view) {
        return new ViewTooltip(new MyContext(getActivityContext(view.getContext())), view);
    }

    public ViewTooltip align(ALIGN align) {
        this.tooltip_view.setAlign(align);
        return this;
    }

    public ViewTooltip animation(TooltipAnimation tooltipAnimation) {
        this.tooltip_view.setTooltipAnimation(tooltipAnimation);
        return this;
    }

    public ViewTooltip arrowHeight(int i) {
        this.tooltip_view.setArrowHeight(i);
        return this;
    }

    public ViewTooltip arrowSourceMargin(int i) {
        this.tooltip_view.setArrowSourceMargin(i);
        return this;
    }

    public ViewTooltip arrowTargetMargin(int i) {
        this.tooltip_view.setArrowTargetMargin(i);
        return this;
    }

    public ViewTooltip arrowWidth(int i) {
        this.tooltip_view.setArrowWidth(i);
        return this;
    }

    public ViewTooltip autoHide(boolean z, long j) {
        this.tooltip_view.setAutoHide(z);
        this.tooltip_view.setDuration(j);
        return this;
    }

    public ViewTooltip border(int i, float f) {
        Paint paint = new Paint(1);
        paint.setColor(i);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(f);
        this.tooltip_view.setBorderPaint(paint);
        return this;
    }

    public ViewTooltip clickToHide(boolean z) {
        this.tooltip_view.setClickToHide(z);
        return this;
    }

    public void close() {
        cleanUpRunnable();
        this.tooltip_view.close();
    }

    public void closeNow() {
        cleanUpRunnable();
        this.tooltip_view.closeNow();
    }

    public ViewTooltip color(int i) {
        this.tooltip_view.setColor(i);
        return this;
    }

    public ViewTooltip corner(int i) {
        this.tooltip_view.setCorner(i);
        return this;
    }

    public ViewTooltip customView(View view) {
        this.tooltip_view.setCustomView(view);
        return this;
    }

    public ViewTooltip distanceWithArrow(int i) {
        this.tooltip_view.setDistanceWithArrow(i);
        return this;
    }

    public ViewTooltip distanceWithView(int i) {
        this.tooltip_view.setDistanceWithView(i);
        return this;
    }

    public ViewTooltip duration(long j) {
        this.tooltip_view.setDuration(j);
        return this;
    }

    public ViewTooltip margin(int i, int i2, int i3, int i4) {
        this.tooltip_view.setMargin(i, i2, i3, i4);
        return this;
    }

    public ViewTooltip onDisplay(ListenerDisplay listenerDisplay) {
        this.tooltip_view.setListenerDisplay(listenerDisplay);
        return this;
    }

    public ViewTooltip onHide(ListenerHide listenerHide) {
        this.tooltip_view.setListenerHide(listenerHide);
        return this;
    }

    public ViewTooltip padding(int i, int i2, int i3, int i4) {
        this.tooltip_view.paddingTop = i2;
        this.tooltip_view.paddingBottom = i4;
        this.tooltip_view.paddingLeft = i;
        this.tooltip_view.paddingRight = i3;
        return this;
    }

    public ViewTooltip position(Position position) {
        this.tooltip_view.setPosition(position);
        return this;
    }

    public ViewTooltip setTextGravity(int i) {
        this.tooltip_view.setTextGravity(i);
        return this;
    }

    public ViewTooltip shadowColor(@ColorInt int i) {
        this.tooltip_view.setShadowColor(i);
        return this;
    }

    public TooltipView show() {
        Context context = this.tooltip_view.getContext();
        if (context != null && (context instanceof Activity)) {
            View view = this.rootView;
            final ViewGroup viewGroup = view != null ? (ViewGroup) view : (ViewGroup) ((Activity) context).getWindow().getDecorView();
            this.showRunnable = new Runnable() { // from class: com.github.florent37.viewtooltip.ViewTooltip.3
                @Override // java.lang.Runnable
                public void run() {
                    final Rect rect = new Rect();
                    ViewTooltip.this.view.getGlobalVisibleRect(rect);
                    Rect rect2 = new Rect();
                    Point point = new Point();
                    viewGroup.getGlobalVisibleRect(rect2, point);
                    int[] iArr = new int[2];
                    ViewTooltip.this.view.getLocationOnScreen(iArr);
                    rect.left = iArr[0];
                    int i = rect.top;
                    int i2 = point.y;
                    rect.top = i - i2;
                    rect.bottom -= i2;
                    int i3 = rect.left;
                    int i4 = point.x;
                    rect.left = i3 - i4;
                    rect.right -= i4;
                    viewGroup.addView(ViewTooltip.this.tooltip_view, -2, -2);
                    ViewTooltip.this.tooltip_view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.github.florent37.viewtooltip.ViewTooltip.3.1
                        @Override // android.view.ViewTreeObserver.OnPreDrawListener
                        public boolean onPreDraw() {
                            ViewTooltip.this.tooltip_view.setup(rect, viewGroup.getWidth());
                            ViewTooltip.this.tooltip_view.getViewTreeObserver().removeOnPreDrawListener(this);
                            return false;
                        }
                    });
                }
            };
            this.view.postDelayed(this.showRunnable, 100L);
        }
        return this.tooltip_view;
    }

    public ViewTooltip testId(String str) {
        this.testId = str;
        return this;
    }

    public ViewTooltip text(String str) {
        this.tooltip_view.setText(str);
        return this;
    }

    public ViewTooltip textColor(int i) {
        this.tooltip_view.setTextColor(i);
        return this;
    }

    public ViewTooltip textSize(int i, float f) {
        this.tooltip_view.setTextSize(i, f);
        return this;
    }

    public ViewTooltip textTypeFace(Typeface typeface) {
        this.tooltip_view.setTextTypeFace(typeface);
        return this;
    }

    public ViewTooltip withShadow(boolean z) {
        this.tooltip_view.setWithShadow(z);
        return this;
    }

    /* loaded from: classes2.dex */
    public static class FadeTooltipAnimation implements TooltipAnimation {
        private long fadeDuration;

        public FadeTooltipAnimation() {
            this.fadeDuration = 400L;
        }

        @Override // com.github.florent37.viewtooltip.ViewTooltip.TooltipAnimation
        public void animateEnter(View view, Animator.AnimatorListener animatorListener) {
            view.setAlpha(0.0f);
            view.animate().alpha(1.0f).setDuration(this.fadeDuration).setListener(animatorListener);
        }

        @Override // com.github.florent37.viewtooltip.ViewTooltip.TooltipAnimation
        public void animateExit(View view, Animator.AnimatorListener animatorListener) {
            view.animate().alpha(0.0f).setDuration(this.fadeDuration).setListener(animatorListener);
        }

        public FadeTooltipAnimation(long j) {
            this.fadeDuration = 400L;
            this.fadeDuration = j;
        }
    }

    /* loaded from: classes2.dex */
    public static class MyContext {
        private Activity activity;
        private Context context;
        private Fragment fragment;

        public MyContext(Activity activity) {
            this.activity = activity;
        }

        public Activity getActivity() {
            Activity activity = this.activity;
            return activity != null ? activity : this.fragment.getActivity();
        }

        public Context getContext() {
            Activity activity = this.activity;
            return activity != null ? activity : this.fragment.getActivity();
        }

        public Window getWindow() {
            Activity activity = this.activity;
            if (activity != null) {
                return activity.getWindow();
            }
            Fragment fragment = this.fragment;
            if (fragment instanceof DialogFragment) {
                return ((DialogFragment) fragment).mo3821getDialog().getWindow();
            }
            return fragment.getActivity().getWindow();
        }

        public MyContext(Fragment fragment) {
            this.fragment = fragment;
        }

        public MyContext(Context context) {
            this.context = context;
        }
    }

    public static ViewTooltip on(Fragment fragment, View view) {
        return new ViewTooltip(new MyContext(fragment), view);
    }

    public ViewTooltip color(Paint paint) {
        this.tooltip_view.setPaint(paint);
        return this;
    }

    public ViewTooltip customView(int i) {
        this.tooltip_view.setCustomView(((Activity) this.view.getContext()).findViewById(i));
        return this;
    }

    public ViewTooltip text(@StringRes int i) {
        this.tooltip_view.setText(i);
        return this;
    }

    /* loaded from: classes2.dex */
    public static class TooltipView extends FrameLayout {
        private static final int MARGIN_SCREEN_BORDER_TOOLTIP = 30;
        private ALIGN align;
        private int arrowHeight;
        private int arrowSourceMargin;
        private int arrowTargetMargin;
        private int arrowWidth;
        private boolean autoHide;
        private Paint borderPaint;
        private Paint bubblePaint;
        private Path bubblePath;
        protected View childView;
        private boolean clickToHide;
        private int color;
        private int corner;
        private int distanceWithArrow;
        private int distanceWithView;
        private long duration;
        private ListenerDisplay listenerDisplay;
        private ListenerHide listenerHide;
        private int marginBottom;
        private int marginLeft;
        private int marginRight;
        private int marginTop;
        private int paddingBottom;
        private int paddingLeft;
        private int paddingRight;
        private int paddingTop;
        private Position position;
        private int shadowColor;
        final float shadowPadding;
        final float shadowWidth;
        final float shadowYShift;
        private TooltipAnimation tooltipAnimation;
        private Rect viewRect;

        public TooltipView(Context context) {
            super(context);
            this.arrowHeight = 15;
            this.arrowWidth = 15;
            this.arrowSourceMargin = 0;
            this.arrowTargetMargin = 0;
            this.color = Color.parseColor("#1F7C82");
            this.position = Position.BOTTOM;
            this.align = ALIGN.CENTER;
            this.autoHide = true;
            this.duration = 4000L;
            this.tooltipAnimation = new FadeTooltipAnimation();
            this.corner = 30;
            this.paddingTop = 20;
            this.paddingBottom = 30;
            this.paddingRight = 30;
            this.paddingLeft = 30;
            this.marginTop = 0;
            this.marginBottom = 0;
            this.marginRight = 0;
            this.marginLeft = 0;
            this.shadowWidth = 5.0f;
            this.shadowPadding = 5.0f;
            this.shadowYShift = 3.0f;
            this.distanceWithView = 0;
            this.distanceWithArrow = 0;
            this.shadowColor = Color.parseColor("#aaaaaa");
            setWillNotDraw(false);
            this.childView = new TextView(context);
            ((TextView) this.childView).setTextColor(-1);
            addView(this.childView, -2, -2);
            this.childView.setPadding(0, 0, 0, 0);
            this.bubblePaint = new Paint(1);
            this.bubblePaint.setColor(this.color);
            this.bubblePaint.setStyle(Paint.Style.FILL);
            this.borderPaint = null;
            setLayerType(1, this.bubblePaint);
            setWithShadow(true);
        }

        private void adjustAlignOffset(Rect rect, int i) {
            Position position = this.position;
            if (position == Position.LEFT || position == Position.RIGHT) {
                return;
            }
            ALIGN align = this.align;
            int i2 = 0;
            if (align == ALIGN.START) {
                i2 = Math.max(((getWidth() / 2) - this.distanceWithArrow) - this.marginLeft, 0);
            } else if (align == ALIGN.END) {
                i2 = -Math.max(((getWidth() / 2) - this.distanceWithArrow) - this.marginRight, 0);
            }
            rect.left += i2;
            rect.right += i2;
            setAlign(ALIGN.CENTER);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void adjustSizeOnChange(Rect rect, int i) {
            Position position = this.position;
            if (position == Position.TOP || position == Position.BOTTOM) {
                return;
            }
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            int width = getWidth();
            int paddingRight = getPaddingRight() + getPaddingLeft() + this.childView.getWidth() + this.marginLeft + this.marginRight;
            if ((this.position != Position.RIGHT || rect.right + paddingRight > i) && (this.position != Position.LEFT || paddingRight > rect.left)) {
                return;
            }
            if (width > paddingRight) {
                width = paddingRight;
            }
            layoutParams.width = width;
            setLayoutParams(layoutParams);
        }

        /* JADX WARN: Removed duplicated region for block: B:68:0x018a  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x01b0  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x01d7  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x01fe  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private android.graphics.Path drawBubble(android.graphics.RectF r19, float r20, float r21, float r22, float r23) {
            /*
                Method dump skipped, instructions count: 542
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.github.florent37.viewtooltip.ViewTooltip.TooltipView.drawBubble(android.graphics.RectF, float, float, float, float):android.graphics.Path");
        }

        private int getAlignOffset(int i, int i2) {
            int ordinal = this.align.ordinal();
            if (ordinal != 1) {
                if (ordinal == 2) {
                    return i2 - i;
                }
                return 0;
            }
            return (i2 - i) / 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onSetup(Rect rect) {
            setupPosition(rect);
            RectF rectF = new RectF(5.0f, 5.0f, getWidth() - 10.0f, getHeight() - 10.0f);
            int i = this.corner;
            this.bubblePath = drawBubble(rectF, i, i, i, i);
            startEnterAnimation();
            handleAutoRemove();
        }

        public boolean adjustSize(Rect rect, int i) {
            getGlobalVisibleRect(new Rect());
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            boolean z = true;
            if (this.position == Position.LEFT) {
                int width = getWidth();
                int i2 = rect.left;
                if (width > i2) {
                    layoutParams.width = (i2 - 30) - this.distanceWithView;
                    setLayoutParams(layoutParams);
                    postInvalidate();
                    return z;
                }
            }
            if (this.position == Position.RIGHT) {
                if (getWidth() + rect.right > i) {
                    layoutParams.width = ((i - rect.right) - 30) - this.distanceWithView;
                    setLayoutParams(layoutParams);
                    postInvalidate();
                    return z;
                }
            }
            Position position = this.position;
            if (position == Position.TOP || position == Position.BOTTOM) {
                adjustAlignOffset(rect, i);
                int i3 = rect.left;
                int i4 = rect.right;
                float f = i;
                if ((getWidth() / 2.0f) + rect.centerX() > f) {
                    float width2 = ((getWidth() / 2.0f) + rect.centerX()) - f;
                    i3 = (int) (i3 - width2);
                    i4 = (int) (i4 - width2);
                    setAlign(ALIGN.CENTER);
                } else if (rect.centerX() - (getWidth() / 2.0f) < 0.0f) {
                    float f2 = -(rect.centerX() - (getWidth() / 2.0f));
                    i3 = (int) (i3 + f2);
                    i4 = (int) (i4 + f2);
                    setAlign(ALIGN.CENTER);
                } else {
                    z = false;
                }
                rect.left = i3;
                rect.right = i4;
            } else {
                z = false;
            }
            setLayoutParams(layoutParams);
            postInvalidate();
            return z;
        }

        public void close() {
            remove();
        }

        public void closeNow() {
            removeNow();
        }

        public int getArrowHeight() {
            return this.arrowHeight;
        }

        public int getArrowSourceMargin() {
            return this.arrowSourceMargin;
        }

        public int getArrowTargetMargin() {
            return this.arrowTargetMargin;
        }

        public int getArrowWidth() {
            return this.arrowWidth;
        }

        protected void handleAutoRemove() {
            if (this.clickToHide) {
                setOnClickListener(new View.OnClickListener() { // from class: com.github.florent37.viewtooltip.ViewTooltip.TooltipView.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (TooltipView.this.clickToHide) {
                            TooltipView.this.remove();
                        }
                    }
                });
            }
            if (this.autoHide) {
                postDelayed(new Runnable() { // from class: com.github.florent37.viewtooltip.ViewTooltip.TooltipView.4
                    @Override // java.lang.Runnable
                    public void run() {
                        TooltipView.this.remove();
                    }
                }, this.duration);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            createAccessibilityNodeInfo();
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Path path = this.bubblePath;
            if (path != null) {
                canvas.drawPath(path, this.bubblePaint);
                Paint paint = this.borderPaint;
                if (paint == null) {
                    return;
                }
                canvas.drawPath(this.bubblePath, paint);
            }
        }

        @Override // android.view.View
        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            RectF rectF = new RectF(5.0f, 5.0f, i - 10.0f, i2 - 10.0f);
            int i5 = this.corner;
            this.bubblePath = drawBubble(rectF, i5, i5, i5, i5);
        }

        public void remove() {
            startExitAnimation(new AnimatorListenerAdapter() { // from class: com.github.florent37.viewtooltip.ViewTooltip.TooltipView.5
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    TooltipView.this.removeNow();
                }
            });
        }

        public void removeNow() {
            if (getParent() != null) {
                ((ViewGroup) getParent()).removeView(this);
            }
        }

        public void setAlign(ALIGN align) {
            this.align = align;
            postInvalidate();
        }

        public void setArrowHeight(int i) {
            this.arrowHeight = i;
            postInvalidate();
        }

        public void setArrowSourceMargin(int i) {
            this.arrowSourceMargin = i;
            postInvalidate();
        }

        public void setArrowTargetMargin(int i) {
            this.arrowTargetMargin = i;
            postInvalidate();
        }

        public void setArrowWidth(int i) {
            this.arrowWidth = i;
            postInvalidate();
        }

        public void setAutoHide(boolean z) {
            this.autoHide = z;
        }

        public void setBorderPaint(Paint paint) {
            this.borderPaint = paint;
            postInvalidate();
        }

        public void setClickToHide(boolean z) {
            this.clickToHide = z;
        }

        public void setColor(int i) {
            this.color = i;
            this.bubblePaint.setColor(i);
            postInvalidate();
        }

        public void setCorner(int i) {
            this.corner = i;
        }

        public void setCustomView(View view) {
            removeView(this.childView);
            this.childView = view;
            addView(this.childView, -2, -2);
        }

        public void setDistanceWithArrow(int i) {
            this.distanceWithArrow = i;
            postInvalidate();
        }

        public void setDistanceWithView(int i) {
            this.distanceWithView = i;
        }

        public void setDuration(long j) {
            this.duration = j;
        }

        public void setListenerDisplay(ListenerDisplay listenerDisplay) {
            this.listenerDisplay = listenerDisplay;
        }

        public void setListenerHide(ListenerHide listenerHide) {
            this.listenerHide = listenerHide;
        }

        public void setMargin(int i, int i2, int i3, int i4) {
            this.marginLeft = i;
            this.marginTop = i2;
            this.marginRight = i3;
            this.marginBottom = i2;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(this.childView.getPaddingLeft() + i, this.childView.getPaddingTop() + i2, this.childView.getPaddingRight() + i3, this.childView.getPaddingBottom() + i4);
            this.childView.setLayoutParams(layoutParams);
            postInvalidate();
        }

        public void setPaint(Paint paint) {
            this.bubblePaint = paint;
            setLayerType(1, paint);
            postInvalidate();
        }

        public void setPosition(Position position) {
            this.position = position;
            int ordinal = position.ordinal();
            if (ordinal == 0) {
                setPadding(this.paddingLeft, this.paddingTop, this.paddingRight + this.arrowHeight, this.paddingBottom);
            } else if (ordinal == 1) {
                setPadding(this.paddingLeft + this.arrowHeight, this.paddingTop, this.paddingRight, this.paddingBottom);
            } else if (ordinal == 2) {
                setPadding(this.paddingLeft, this.paddingTop, this.paddingRight, this.paddingBottom + this.arrowHeight);
            } else if (ordinal == 3) {
                setPadding(this.paddingLeft, this.paddingTop + this.arrowHeight, this.paddingRight, this.paddingBottom);
            }
            postInvalidate();
        }

        public void setShadowColor(int i) {
            this.shadowColor = i;
            postInvalidate();
        }

        public void setText(String str) {
            View view = this.childView;
            if (view instanceof TextView) {
                ((TextView) view).setText(Html.fromHtml(str));
            }
            postInvalidate();
        }

        public void setTextColor(int i) {
            View view = this.childView;
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(i);
            }
            postInvalidate();
        }

        public void setTextGravity(int i) {
            View view = this.childView;
            if (view instanceof TextView) {
                ((TextView) view).setGravity(i);
            }
            postInvalidate();
        }

        public void setTextSize(int i, float f) {
            View view = this.childView;
            if (view instanceof TextView) {
                ((TextView) view).setTextSize(i, f);
            }
            postInvalidate();
        }

        public void setTextTypeFace(Typeface typeface) {
            View view = this.childView;
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(typeface);
            }
            postInvalidate();
        }

        public void setTooltipAnimation(TooltipAnimation tooltipAnimation) {
            this.tooltipAnimation = tooltipAnimation;
        }

        public void setWithShadow(boolean z) {
            if (z) {
                this.bubblePaint.setShadowLayer(5.0f, 0.0f, 3.0f, this.shadowColor);
            } else {
                this.bubblePaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            }
        }

        public void setup(Rect rect, final int i) {
            this.viewRect = new Rect(rect);
            final Rect rect2 = new Rect(rect);
            if (!adjustSize(rect2, i)) {
                onSetup(rect2);
            } else {
                getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.github.florent37.viewtooltip.ViewTooltip.TooltipView.6
                    @Override // android.view.ViewTreeObserver.OnPreDrawListener
                    public boolean onPreDraw() {
                        TooltipView.this.adjustSizeOnChange(rect2, i);
                        TooltipView.this.onSetup(rect2);
                        TooltipView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                        return false;
                    }
                });
            }
        }

        public void setupPosition(Rect rect) {
            int i;
            int i2;
            int alignOffset;
            Position position = this.position;
            if (position != Position.LEFT && position != Position.RIGHT) {
                if (position == Position.BOTTOM) {
                    alignOffset = rect.bottom + this.distanceWithView;
                } else {
                    alignOffset = (rect.top - getHeight()) - this.distanceWithView;
                }
                i2 = rect.left + getAlignOffset(getWidth(), rect.width());
            } else {
                if (this.position == Position.LEFT) {
                    i = (rect.left - getWidth()) - this.distanceWithView;
                } else {
                    i = rect.right + this.distanceWithView;
                }
                i2 = i;
                alignOffset = rect.top + getAlignOffset(getHeight(), rect.height());
            }
            setX(i2);
            setY(alignOffset);
        }

        protected void startEnterAnimation() {
            this.tooltipAnimation.animateEnter(this, new AnimatorListenerAdapter() { // from class: com.github.florent37.viewtooltip.ViewTooltip.TooltipView.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    if (TooltipView.this.listenerDisplay != null) {
                        TooltipView.this.listenerDisplay.onDisplay(TooltipView.this);
                    }
                }
            });
        }

        protected void startExitAnimation(final Animator.AnimatorListener animatorListener) {
            this.tooltipAnimation.animateExit(this, new AnimatorListenerAdapter() { // from class: com.github.florent37.viewtooltip.ViewTooltip.TooltipView.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    animatorListener.onAnimationEnd(animator);
                    if (TooltipView.this.listenerHide != null) {
                        TooltipView.this.listenerHide.onHide(TooltipView.this);
                    }
                }
            });
        }

        public void setText(int i) {
            View view = this.childView;
            if (view instanceof TextView) {
                ((TextView) view).setText(i);
            }
            postInvalidate();
        }
    }

    public static ViewTooltip on(Activity activity, View view) {
        return new ViewTooltip(new MyContext(getActivityContext(activity)), view);
    }

    public static ViewTooltip on(Activity activity, View view, View view2) {
        return new ViewTooltip(new MyContext(getActivityContext(activity)), view, view2);
    }

    private ViewTooltip(MyContext myContext, View view, View view2) {
        this.testId = null;
        this.rootView = view;
        this.view = view2;
        this.tooltip_view = new TooltipView(myContext.getContext());
        initializeAccessibilityDelegate();
        initializeOnScrollChangeListener();
    }

    private ViewTooltip(View view) {
        this(new MyContext(getActivityContext(view.getContext())), view);
    }
}
