package com.amazon.alexa.navigation.menu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public class ExpandableMenuItem extends MenuItem {
    private static final String ALPHA = "alpha";
    @VisibleForTesting
    static final int ANIMATION_DURATION = 300;
    private static final String SCALE_Y = "scaleY";
    private static final String TAG = "ExpandableMenuItem";
    @VisibleForTesting
    ObjectAnimator alpha;
    @VisibleForTesting
    AnimatorSet animatorSet;
    @VisibleForTesting
    Map<String, Object> closedCustomEntries;
    private MetricsComponents closedMetricsComponents;
    @VisibleForTesting
    Map<String, Object> customEntries;
    @VisibleForTesting
    boolean expanded;
    private List<MenuItem> menuItemList;
    private MetricsComponents metricsComponents;
    private Provider<Mobilytics> mobilyticsProvider;
    @VisibleForTesting
    ObjectAnimator scaleY;
    @VisibleForTesting
    ValueAnimator slideAnimator;

    public ExpandableMenuItem(int i, int i2, boolean z, @NonNull MetricsComponents metricsComponents, @Nullable MetricsComponents metricsComponents2, @NonNull TestId testId, Provider<Mobilytics> provider) {
        super(i, i2, z, testId);
        this.menuItemList = null;
        this.expanded = false;
        this.mobilyticsProvider = provider;
        if (metricsComponents2 != null) {
            this.closedMetricsComponents = metricsComponents2;
            this.closedCustomEntries = new HashMap();
            this.closedCustomEntries.put("subComponent", metricsComponents2.subComponent);
        }
        this.metricsComponents = metricsComponents;
        this.customEntries = new HashMap();
        this.customEntries.put("subComponent", metricsComponents.subComponent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$animateView$0(View view, ValueAnimator valueAnimator) {
        view.getLayoutParams().height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        view.requestLayout();
    }

    private void sendMetrics() {
        String str;
        MetricsComponents metricsComponents = this.closedMetricsComponents;
        if (metricsComponents != null && !this.expanded) {
            str = metricsComponents.metricName;
        } else {
            str = this.metricsComponents.metricName;
        }
        this.mobilyticsProvider.mo10268get().recordUserInteractionEvent(this.mobilyticsProvider.mo10268get().createUserInteractionEvent(str, "click", "RightMenu", "subComponent", "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
    }

    private void setVisibilityWithAnimation(View view) {
        view.measure(-2, -2);
        int measuredHeight = view.getMeasuredHeight();
        animateView(view, this.expanded ? 0.0f : 1.0f, this.expanded ? 1.0f : 0.0f, this.expanded ? 0 : measuredHeight, this.expanded ? measuredHeight : 0, this.expanded ? 0.0f : 1.0f, this.expanded ? 1.0f : 0.0f);
    }

    private void setVisibilityWithoutAnimation(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = this.expanded ? -2 : 0;
        view.setLayoutParams(layoutParams);
        view.setScaleY(this.expanded ? 1.0f : 0.0f);
    }

    private void updateExpandableResources(View view) {
        ImageView imageView = (ImageView) view.findViewById(getTestId().iconTestId);
        if (imageView != null) {
            imageView.setImageResource(this.expanded ? R.drawable.ic_caret_up : R.drawable.ic_caret_down);
        }
        TextView textView = (TextView) view.findViewById(getTestId().textTestId);
        if (textView != null) {
            textView.setText(this.expanded ? R.string.more_see_less : R.string.more_see_more);
        }
    }

    @VisibleForTesting
    void animateView(final View view, float f, float f2, int i, int i2, float f3, float f4) {
        this.slideAnimator = ValueAnimator.ofInt(i, i2).setDuration(300L);
        this.scaleY = ObjectAnimator.ofFloat(view, "scaleY", f, f2).setDuration(300L);
        this.alpha = ObjectAnimator.ofFloat(view, "alpha", f3, f4).setDuration(300L);
        this.slideAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.amazon.alexa.navigation.menu.-$$Lambda$ExpandableMenuItem$Fd0gdVCavKC6f15lzMz6EfGIGzs
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ExpandableMenuItem.lambda$animateView$0(view, valueAnimator);
            }
        });
        this.scaleY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.amazon.alexa.navigation.menu.-$$Lambda$ExpandableMenuItem$rkDMppk7kPwwNdfiJMyVUrQNRnM
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setScaleY(Float.parseFloat(valueAnimator.getAnimatedValue().toString()));
            }
        });
        this.alpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.amazon.alexa.navigation.menu.-$$Lambda$ExpandableMenuItem$XxGY1o6ewmTxJLfO_tEUZxQ9VDE
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setAlpha(Float.parseFloat(valueAnimator.getAnimatedValue().toString()));
            }
        });
        this.animatorSet = new AnimatorSet();
        this.animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        this.animatorSet.playTogether(this.scaleY, this.slideAnimator, this.alpha);
        this.animatorSet.start();
    }

    @Override // com.amazon.alexa.navigation.menu.MenuItem
    public int getMenuItemLayout() {
        return R.layout.navigation_menu_expandable_item;
    }

    public List<MenuItem> getMenuItemList() {
        List<MenuItem> list = this.menuItemList;
        return list == null ? new ArrayList() : list;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    @Override // com.amazon.alexa.navigation.menu.MenuItem
    public void onClick(View view) {
        this.expanded = !this.expanded;
        updateExpandable((ViewGroup) view.getParent(), true);
        sendMetrics();
    }

    public void setMenuItemList(List<MenuItem> list) {
        this.menuItemList = list;
    }

    public void updateExpandable(View view, boolean z) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.hidden_item_container);
        if (linearLayout != null) {
            if (z) {
                setVisibilityWithAnimation(linearLayout);
            } else {
                setVisibilityWithoutAnimation(linearLayout);
            }
            updateExpandableResources(view);
        }
    }
}
