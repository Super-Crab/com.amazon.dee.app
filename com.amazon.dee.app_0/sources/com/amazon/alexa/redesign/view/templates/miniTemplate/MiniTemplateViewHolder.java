package com.amazon.alexa.redesign.view.templates.miniTemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.templates.MiniCardTemplateModel;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.view.ViewTypeFactory;
import com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder;
import com.amazon.alexa.redesign.view.templates.TemplateHelperUtil;
import com.amazon.alexa.redesign.view.templates.TemplateResizer;
import java.util.Objects;
/* loaded from: classes10.dex */
public class MiniTemplateViewHolder extends BaseCardViewHolder<MiniTemplateViewItem> {
    private final Context context;
    private final Animation fadeIn;
    private final Animation pulse;
    private final Animation pulseDelayed;
    private final Animation.AnimationListener pulseDelayedListener;
    private final Animation.AnimationListener pulseListener;
    private final View pulseOverlay;
    private boolean shouldTriggerCacheLoad;
    private final Animation slideUp;
    private final Animation.AnimationListener slideUpListener;
    private final TemplateResizer templateResizer;

    public MiniTemplateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, @NonNull HomeMetricsRecorder homeMetricsRecorder, @NonNull ViewTypeFactory viewTypeFactory, @NonNull HomeContract.Presenter presenter) {
        super(layoutInflater, viewGroup, layoutInflater.inflate(R.layout.amahc_mini_template, viewGroup, false), viewTypeFactory, homeMetricsRecorder, presenter, new int[]{R.id.slot0, R.id.slot1}, context);
        this.context = context;
        applyBackgroundColorToView(getCardForegroundView(), R.attr.mosaicBackground);
        this.templateResizer = new TemplateResizer() { // from class: com.amazon.alexa.redesign.view.templates.miniTemplate.MiniTemplateViewHolder.1
            @Override // com.amazon.alexa.redesign.view.templates.TemplateResizer
            public void resizeTemplate(Context context2, int i) {
                TemplateHelperUtil.scaleWithFont(((BaseCardViewHolder) MiniTemplateViewHolder.this).cardView, context2, i);
                TemplateHelperUtil.scaleWithFont(MiniTemplateViewHolder.this.getCardForegroundView(), context2, i);
                TemplateHelperUtil.scaleWithFont(MiniTemplateViewHolder.this.getCardBackgroundView(), context2, i);
                TemplateHelperUtil.scaleWithFont(MiniTemplateViewHolder.this.itemView, context2, i);
            }

            @Override // com.amazon.alexa.redesign.view.templates.TemplateResizer
            public void resizeTemplateToWrapContent(Context context2) {
                TemplateHelperUtil.setHeightToWrapContent(((BaseCardViewHolder) MiniTemplateViewHolder.this).cardView);
                TemplateHelperUtil.setHeightToWrapContent(MiniTemplateViewHolder.this.getCardForegroundView());
                TemplateHelperUtil.setHeightToWrapContent(MiniTemplateViewHolder.this.getCardBackgroundView());
                TemplateHelperUtil.setHeightToWrapContent(MiniTemplateViewHolder.this.itemView);
            }

            @Override // com.amazon.alexa.redesign.view.templates.TemplateResizer
            public void setTopAndBottomMarginOfSlot(Context context2, int i, int i2) {
                TemplateHelperUtil.setTopAndBottomMargin((View) ((BaseCardViewHolder) MiniTemplateViewHolder.this).slots.get(i), context2, i2);
            }
        };
        this.pulseOverlay = this.itemView.findViewById(R.id.pulse_overlay);
        this.slideUp = AnimationUtils.loadAnimation(context, R.anim.amahc_contents_slide_up);
        this.pulseDelayed = AnimationUtils.loadAnimation(context, R.anim.amahc_skeleton_pulse_delayed);
        this.pulse = AnimationUtils.loadAnimation(context, R.anim.amahc_skeleton_pulse);
        this.fadeIn = AnimationUtils.loadAnimation(context, R.anim.amahc_contents_fade_in);
        this.slideUpListener = getSlideUpListener();
        this.pulseDelayedListener = getPulseListener();
        this.pulseListener = getPulseListener();
    }

    private void clearSkeletonAnimation() {
        this.slideUp.setAnimationListener(null);
        this.pulseDelayed.setAnimationListener(null);
        this.pulse.setAnimationListener(null);
        this.pulseOverlay.setVisibility(8);
        this.pulseOverlay.clearAnimation();
        this.itemView.clearAnimation();
        this.pulseDelayed.cancel();
        this.pulse.cancel();
        this.slideUp.cancel();
        this.fadeIn.cancel();
    }

    private Animation.AnimationListener getPulseListener() {
        return new Animation.AnimationListener() { // from class: com.amazon.alexa.redesign.view.templates.miniTemplate.MiniTemplateViewHolder.3
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                MiniTemplateViewHolder.this.pulseOverlay.startAnimation(MiniTemplateViewHolder.this.pulse);
                MiniTemplateViewHolder.this.pulseOverlay.setVisibility(0);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        };
    }

    private Animation.AnimationListener getSlideUpListener() {
        return new Animation.AnimationListener() { // from class: com.amazon.alexa.redesign.view.templates.miniTemplate.MiniTemplateViewHolder.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                MiniTemplateViewHolder.this.pulseOverlay.startAnimation(MiniTemplateViewHolder.this.pulseDelayed);
                MiniTemplateViewHolder.this.pulseOverlay.setVisibility(0);
                if (MiniTemplateViewHolder.this.shouldTriggerCacheLoad) {
                    ((BaseCardViewHolder) MiniTemplateViewHolder.this).homePresenter.displayCachedCardsAndFetchServerCards();
                    ((BaseCardViewHolder) MiniTemplateViewHolder.this).homePresenter.onSkeletonSlideUpFinished();
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        };
    }

    private void resizeTemplateIfSkeleton(MiniTemplateViewItem miniTemplateViewItem) {
        MiniCardTemplateModel mo2390getModel = miniTemplateViewItem.mo2390getModel();
        if (mo2390getModel != null && mo2390getModel.isSkeleton()) {
            this.templateResizer.setTopAndBottomMarginOfSlot(this.context, 0, R.integer.amahc_mini_template_zero_line_margin);
            this.templateResizer.resizeTemplate(this.context, R.integer.amahc_mini_template_height);
            return;
        }
        this.templateResizer.setTopAndBottomMarginOfSlot(this.context, 0, R.integer.amahc_mini_template_three_line_margin);
        this.templateResizer.resizeTemplateToWrapContent(this.context);
    }

    private boolean shouldContentSlideUp(MiniCardTemplateModel miniCardTemplateModel) {
        String viewUpdateType = miniCardTemplateModel.getViewUpdateType();
        return Objects.equals(viewUpdateType, Constants.VIEW_UPDATE_TYPE_PULLREFRESH) || Objects.equals(viewUpdateType, Constants.VIEW_UPDATE_TYPE_REFRESH_BUTTON) || (Objects.equals(viewUpdateType, Constants.VIEW_UPDATE_TYPE_CACHE) && !miniCardTemplateModel.isColdStart());
    }

    private void startAnimationIfSkeleton(MiniTemplateViewItem miniTemplateViewItem) {
        MiniCardTemplateModel mo2390getModel = miniTemplateViewItem.mo2390getModel();
        if (mo2390getModel == null || !BaseCardViewHolder.shouldAnimate(mo2390getModel)) {
            return;
        }
        if (mo2390getModel.isSkeleton()) {
            this.slideUp.reset();
            this.pulse.reset();
            this.pulseDelayed.reset();
            this.slideUp.setAnimationListener(this.slideUpListener);
            this.pulseDelayed.setAnimationListener(this.pulseDelayedListener);
            this.pulse.setAnimationListener(this.pulseListener);
            this.itemView.startAnimation(this.slideUp);
        } else if (mo2390getModel.isColdStart() && Objects.equals(mo2390getModel.getViewUpdateType(), Constants.VIEW_UPDATE_TYPE_CACHE)) {
            this.fadeIn.reset();
            for (LinearLayout linearLayout : this.slots) {
                linearLayout.startAnimation(this.fadeIn);
            }
        } else if (shouldContentSlideUp(mo2390getModel)) {
            this.slideUp.reset();
            this.slideUp.setAnimationListener(null);
            this.itemView.startAnimation(this.slideUp);
        }
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void detach() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void bind(MiniTemplateViewItem miniTemplateViewItem, int i, boolean z) {
        super.bind((MiniTemplateViewHolder) miniTemplateViewItem, i, z);
        resizeTemplateIfSkeleton(miniTemplateViewItem);
        clearSkeletonAnimation();
        startAnimationIfSkeleton(miniTemplateViewItem);
        this.shouldTriggerCacheLoad = miniTemplateViewItem.mo2390getModel() != null && miniTemplateViewItem.mo2390getModel().isShouldTriggerCacheLoad();
    }
}
