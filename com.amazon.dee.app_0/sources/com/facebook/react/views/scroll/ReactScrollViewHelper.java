package com.facebook.react.views.scroll;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class ReactScrollViewHelper {
    public static final String AUTO = "auto";
    public static final long MOMENTUM_DELAY = 20;
    public static final String OVER_SCROLL_ALWAYS = "always";
    public static final String OVER_SCROLL_NEVER = "never";
    private static List<ScrollListener> sScrollListeners = new ArrayList();
    private static int SMOOTH_SCROLL_DURATION = 250;
    private static boolean mSmoothScrollDurationInitialized = false;

    /* loaded from: classes2.dex */
    private static class OverScrollerDurationGetter extends OverScroller {
        private int mScrollAnimationDuration;

        OverScrollerDurationGetter(Context context) {
            super(context);
            this.mScrollAnimationDuration = 250;
        }

        public int getScrollAnimationDuration() {
            super.startScroll(0, 0, 0, 0);
            return this.mScrollAnimationDuration;
        }

        @Override // android.widget.OverScroller
        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            this.mScrollAnimationDuration = i5;
        }
    }

    /* loaded from: classes2.dex */
    public interface ScrollListener {
        void onLayout(ViewGroup viewGroup);

        void onScroll(ViewGroup viewGroup, ScrollEventType scrollEventType, float f, float f2);
    }

    public static void addScrollListener(ScrollListener scrollListener) {
        sScrollListeners.add(scrollListener);
    }

    public static void emitLayoutEvent(ViewGroup viewGroup) {
        for (ScrollListener scrollListener : sScrollListeners) {
            scrollListener.onLayout(viewGroup);
        }
    }

    public static void emitScrollBeginDragEvent(ViewGroup viewGroup) {
        emitScrollEvent(viewGroup, ScrollEventType.BEGIN_DRAG);
    }

    public static void emitScrollEndDragEvent(ViewGroup viewGroup, float f, float f2) {
        emitScrollEvent(viewGroup, ScrollEventType.END_DRAG, f, f2);
    }

    public static void emitScrollEvent(ViewGroup viewGroup, float f, float f2) {
        emitScrollEvent(viewGroup, ScrollEventType.SCROLL, f, f2);
    }

    public static void emitScrollMomentumBeginEvent(ViewGroup viewGroup, int i, int i2) {
        emitScrollEvent(viewGroup, ScrollEventType.MOMENTUM_BEGIN, i, i2);
    }

    public static void emitScrollMomentumEndEvent(ViewGroup viewGroup) {
        emitScrollEvent(viewGroup, ScrollEventType.MOMENTUM_END);
    }

    public static int getDefaultScrollAnimationDuration(Context context) {
        if (!mSmoothScrollDurationInitialized) {
            mSmoothScrollDurationInitialized = true;
            try {
                SMOOTH_SCROLL_DURATION = new OverScrollerDurationGetter(context).getScrollAnimationDuration();
            } catch (Throwable unused) {
            }
        }
        return SMOOTH_SCROLL_DURATION;
    }

    public static int parseOverScrollMode(String str) {
        if (str == null || str.equals("auto")) {
            return 1;
        }
        if (str.equals(OVER_SCROLL_ALWAYS)) {
            return 0;
        }
        if (!str.equals(OVER_SCROLL_NEVER)) {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("wrong overScrollMode: ", str));
        }
        return 2;
    }

    public static void removeScrollListener(ScrollListener scrollListener) {
        sScrollListeners.remove(scrollListener);
    }

    private static void emitScrollEvent(ViewGroup viewGroup, ScrollEventType scrollEventType) {
        emitScrollEvent(viewGroup, scrollEventType, 0.0f, 0.0f);
    }

    private static void emitScrollEvent(ViewGroup viewGroup, ScrollEventType scrollEventType, float f, float f2) {
        View childAt = viewGroup.getChildAt(0);
        if (childAt == null) {
            return;
        }
        for (ScrollListener scrollListener : sScrollListeners) {
            scrollListener.onScroll(viewGroup, scrollEventType, f, f2);
        }
        UIManagerHelper.getEventDispatcherForReactTag((ReactContext) viewGroup.getContext(), viewGroup.getId()).dispatchEvent(ScrollEvent.obtain(viewGroup.getId(), scrollEventType, viewGroup.getScrollX(), viewGroup.getScrollY(), f, f2, childAt.getWidth(), childAt.getHeight(), viewGroup.getWidth(), viewGroup.getHeight()));
    }
}
