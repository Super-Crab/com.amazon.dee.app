package com.reactnativecommunity.viewpager;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class ReactViewPager extends ViewPager {
    private final EventDispatcher mEventDispatcher;
    private boolean mIsCurrentItemFromJs;
    private boolean mScrollEnabled;
    private final Runnable measureAndLayout;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class Adapter extends PagerAdapter {
        private boolean mIsViewPagerInIntentionallyInconsistentState;
        private final List<View> mViews;

        private Adapter() {
            this.mViews = new ArrayList();
            this.mIsViewPagerInIntentionallyInconsistentState = false;
        }

        void addView(View view, int i) {
            this.mViews.add(i, view);
            notifyDataSetChanged();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.mViews.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object obj) {
            if (this.mIsViewPagerInIntentionallyInconsistentState || !this.mViews.contains(obj)) {
                return -2;
            }
            return this.mViews.indexOf(obj);
        }

        View getViewAt(int i) {
            return this.mViews.get(i);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view = this.mViews.get(i);
            viewGroup.addView(view, 0, ReactViewPager.this.generateDefaultLayoutParams());
            ReactViewPager reactViewPager = ReactViewPager.this;
            reactViewPager.post(reactViewPager.measureAndLayout);
            return view;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        void removeAllViewsFromAdapter(ViewPager viewPager) {
            this.mViews.clear();
            viewPager.removeAllViews();
            this.mIsViewPagerInIntentionallyInconsistentState = true;
        }

        void removeViewAt(int i) {
            this.mViews.remove(i);
            notifyDataSetChanged();
        }

        void setViews(List<View> list) {
            this.mViews.clear();
            this.mViews.addAll(list);
            notifyDataSetChanged();
            this.mIsViewPagerInIntentionallyInconsistentState = false;
        }
    }

    /* loaded from: classes3.dex */
    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        private PageChangeListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            String str;
            if (i == 0) {
                str = "idle";
            } else if (i == 1) {
                str = "dragging";
            } else if (i != 2) {
                throw new IllegalStateException("Unsupported pageScrollState");
            } else {
                str = "settling";
            }
            ReactViewPager.this.mEventDispatcher.dispatchEvent(new PageScrollStateChangedEvent(ReactViewPager.this.getId(), str));
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            ReactViewPager.this.mEventDispatcher.dispatchEvent(new PageScrollEvent(ReactViewPager.this.getId(), i, f));
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            if (!ReactViewPager.this.mIsCurrentItemFromJs) {
                ReactViewPager.this.mEventDispatcher.dispatchEvent(new PageSelectedEvent(ReactViewPager.this.getId(), i));
            }
        }
    }

    public ReactViewPager(ReactContext reactContext) {
        super(reactContext);
        this.mScrollEnabled = true;
        this.measureAndLayout = new Runnable() { // from class: com.reactnativecommunity.viewpager.ReactViewPager.1
            @Override // java.lang.Runnable
            public void run() {
                ReactViewPager reactViewPager = ReactViewPager.this;
                reactViewPager.measure(View.MeasureSpec.makeMeasureSpec(reactViewPager.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactViewPager.this.getHeight(), 1073741824));
                ReactViewPager reactViewPager2 = ReactViewPager.this;
                reactViewPager2.layout(reactViewPager2.getLeft(), ReactViewPager.this.getTop(), ReactViewPager.this.getRight(), ReactViewPager.this.getBottom());
            }
        };
        this.mEventDispatcher = ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).mo6949getEventDispatcher();
        this.mIsCurrentItemFromJs = false;
        setOnPageChangeListener(new PageChangeListener());
        setAdapter(new Adapter());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addViewToAdapter(View view, int i) {
        mo10138getAdapter().addView(view, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getViewCountInAdapter() {
        return mo10138getAdapter().getCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View getViewFromAdapter(int i) {
        return mo10138getAdapter().getViewAt(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        requestLayout();
        post(this.measureAndLayout);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        try {
            if (super.onInterceptTouchEvent(motionEvent)) {
                NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
                return true;
            }
        } catch (IllegalArgumentException e) {
            FLog.w(ReactConstants.TAG, "Error intercepting touch event.", e);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        try {
            return super.onTouchEvent(motionEvent);
        } catch (IllegalArgumentException e) {
            FLog.w(ReactConstants.TAG, "Error handling touch event.", e);
            return false;
        }
    }

    public void removeAllViewsFromAdapter() {
        mo10138getAdapter().removeAllViewsFromAdapter(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeViewFromAdapter(int i) {
        mo10138getAdapter().removeViewAt(i);
    }

    public void setCurrentItemFromJs(int i, boolean z) {
        this.mIsCurrentItemFromJs = true;
        setCurrentItem(i, z);
        this.mEventDispatcher.dispatchEvent(new PageSelectedEvent(getId(), i));
        this.mIsCurrentItemFromJs = false;
    }

    public void setScrollEnabled(boolean z) {
        this.mScrollEnabled = z;
    }

    public void setViews(List<View> list) {
        mo10138getAdapter().setViews(list);
    }

    @Override // androidx.viewpager.widget.ViewPager
    /* renamed from: getAdapter  reason: collision with other method in class */
    public Adapter mo10138getAdapter() {
        return (Adapter) super.mo10138getAdapter();
    }
}
