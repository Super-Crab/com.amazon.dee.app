package com.amazon.alexa.redesign.view.templates.voxIngressTemplate;

import android.animation.Animator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.airbnb.lottie.LottieAnimationView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.entity.AlertBannerModel;
import com.amazon.alexa.redesign.entity.templates.VoxIngressTemplateModel;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder;
import com.amazon.alexa.redesign.view.templates.TemplateHelperUtil;
import com.amazon.alexa.voice.tta.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class VoxIngressTemplateViewHolder extends BaseCardViewHolder<VoxIngressTemplateViewItem> {
    private final TextView alertBannerPlaceholder;
    private final LottieAnimationView animationView;
    private final Context context;
    private final View fosSpaceAboveOffline;
    private final View fosSpaceAboveOnline;
    private final View fosVersionWrapper;
    private final View headerAndAlertBannerPlaceholder;
    private final TextView hintView;
    private final View marginTopIfOnline;
    private final HomeContract.HomeMetricsRecorder metricsRecorder;
    private final HomeContract.Presenter presenter;
    private final View refreshPillPlaceholder;
    private final ImageView voxButton;
    private final View voxWrapper;

    /* loaded from: classes10.dex */
    private abstract class VoxAnimationListener implements Animation.AnimationListener {
        private VoxAnimationListener() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    public VoxIngressTemplateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, Context context, HomeContract.Presenter presenter, HomeContract.HomeMetricsRecorder homeMetricsRecorder) {
        super(layoutInflater, viewGroup, layoutInflater.inflate(R.layout.amahc_vox_card, viewGroup, false), presenter, context);
        this.context = context;
        this.presenter = presenter;
        this.metricsRecorder = homeMetricsRecorder;
        this.voxButton = (ImageView) this.itemView.findViewById(R.id.vox_button);
        this.hintView = (TextView) this.itemView.findViewById(R.id.vox_hint);
        this.animationView = (LottieAnimationView) this.itemView.findViewById(R.id.vox_animation);
        this.voxWrapper = this.itemView.findViewById(R.id.vox_wrapper);
        this.alertBannerPlaceholder = (TextView) this.itemView.findViewById(R.id.banner_text_placeholder);
        this.headerAndAlertBannerPlaceholder = this.itemView.findViewById(R.id.header_and_alert_banner_placeholder);
        this.marginTopIfOnline = this.itemView.findViewById(R.id.margin_top_if_online);
        this.refreshPillPlaceholder = this.itemView.findViewById(R.id.refresh_pill_placeholder);
        this.fosVersionWrapper = this.itemView.findViewById(R.id.fire_os_version_wrapper);
        this.fosSpaceAboveOnline = this.itemView.findViewById(R.id.online_fireos_space_above);
        this.fosSpaceAboveOffline = this.itemView.findViewById(R.id.offline_fireos_space_above);
        if (Constants.AutomationConstants.isQABuild) {
            this.hintView.setContentDescription("vox_hint");
        }
        initDebugMenu();
        styleHint();
        styleAlertBannerPlaceHolder();
    }

    private void bindAlertBanner(AlertBannerModel alertBannerModel, boolean z) {
        if (alertBannerModel == null && !z) {
            this.marginTopIfOnline.setVisibility(0);
            this.headerAndAlertBannerPlaceholder.setVisibility(8);
            return;
        }
        this.marginTopIfOnline.setVisibility(8);
        this.headerAndAlertBannerPlaceholder.setVisibility(4);
        if (!z) {
            this.alertBannerPlaceholder.setText(alertBannerModel.getAlertMessage());
        } else {
            this.alertBannerPlaceholder.setText(R.string.amahc_no_connection);
        }
    }

    private void bindRefreshPillPlaceholder(VoxIngressTemplateModel voxIngressTemplateModel) {
        if (voxIngressTemplateModel != null && voxIngressTemplateModel.isShowingRefreshPill()) {
            this.refreshPillPlaceholder.setVisibility(4);
        } else {
            this.refreshPillPlaceholder.setVisibility(8);
        }
    }

    private void bindVoxModel(VoxIngressTemplateViewItem voxIngressTemplateViewItem, boolean z) {
        final VoxIngressTemplateModel mo2390getModel = voxIngressTemplateViewItem.mo2390getModel();
        if (z) {
            this.itemView.setAlpha(0.3f);
            this.voxButton.setOnClickListener(null);
            this.voxButton.setEnabled(false);
        } else {
            this.itemView.setAlpha(1.0f);
            this.voxButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.templates.voxIngressTemplate.-$$Lambda$VoxIngressTemplateViewHolder$0HB-eidM6C_53wTfKgzP55-vv_w
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VoxIngressTemplateViewHolder.this.lambda$bindVoxModel$0$VoxIngressTemplateViewHolder(mo2390getModel, view);
                }
            });
            this.voxButton.setEnabled(true);
        }
        String string = this.context.getResources().getString(mo2390getModel.getHintResId());
        if (Constants.AutomationConstants.isQABuild) {
            this.voxButton.setContentDescription("vox_button");
        } else {
            this.voxButton.setContentDescription(string);
        }
        this.hintView.setText(string);
        TemplateHelperUtil.scaleTextViewWithFontFireOS(this.hintView, this.context, R.integer.amahc_fireos_font_scaling_vox_card_hint);
    }

    public static Map<String, Object> buildVoxClickParams(long j, String str) {
        HashMap outline133 = GeneratedOutlineSupport1.outline133("referer", str);
        outline133.put(Constants.IntentParameters.START_TIMESTAMP, Long.valueOf(j));
        return outline133;
    }

    private void handleFireOS(boolean z) {
        styleFosIcon();
        this.hintView.setVisibility(8);
        this.voxWrapper.setVisibility(8);
        this.fosVersionWrapper.setVisibility(0);
        if (z) {
            this.fosSpaceAboveOffline.setVisibility(0);
            this.fosSpaceAboveOnline.setVisibility(8);
        } else {
            this.fosSpaceAboveOffline.setVisibility(8);
            this.fosSpaceAboveOnline.setVisibility(0);
        }
        this.itemView.setAlpha(1.0f);
        this.itemView.setImportantForAccessibility(4);
    }

    private void handleNotFireOS() {
        this.fosVersionWrapper.setVisibility(8);
        this.hintView.setVisibility(0);
        this.voxWrapper.setVisibility(0);
    }

    private void initDebugMenu() {
        this.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.amazon.alexa.redesign.view.templates.voxIngressTemplate.-$$Lambda$VoxIngressTemplateViewHolder$k9E8OQPJu3cF6n9LfM44CJC4Ixs
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return VoxIngressTemplateViewHolder.this.lambda$initDebugMenu$1$VoxIngressTemplateViewHolder(view);
            }
        });
    }

    private void startVoxAnimation(VoxIngressTemplateViewItem voxIngressTemplateViewItem) {
        VoxIngressTemplateModel mo2390getModel = voxIngressTemplateViewItem.mo2390getModel();
        if (mo2390getModel == null || !mo2390getModel.isShouldAnimate() || !BaseCardViewHolder.shouldAnimate(mo2390getModel)) {
            return;
        }
        this.animationView.setVisibility(0);
        Animation loadAnimation = AnimationUtils.loadAnimation(this.context, R.anim.amahc_vox_ingress_scale);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.context, R.anim.amahc_vox_ingress_hint_fade);
        this.voxButton.setLayerType(2, null);
        this.hintView.setLayerType(2, null);
        this.animationView.setLayerType(2, null);
        this.animationView.addAnimatorListener(new Animator.AnimatorListener() { // from class: com.amazon.alexa.redesign.view.templates.voxIngressTemplate.VoxIngressTemplateViewHolder.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                VoxIngressTemplateViewHolder.this.animationView.cancelAnimation();
                VoxIngressTemplateViewHolder.this.animationView.setVisibility(4);
                VoxIngressTemplateViewHolder.this.animationView.setLayerType(0, null);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        loadAnimation2.setAnimationListener(new VoxAnimationListener() { // from class: com.amazon.alexa.redesign.view.templates.voxIngressTemplate.VoxIngressTemplateViewHolder.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                VoxIngressTemplateViewHolder.this.hintView.setVisibility(0);
                VoxIngressTemplateViewHolder.this.hintView.setLayerType(0, null);
                VoxIngressTemplateViewHolder.this.presenter.publishTWAAvailablityRequest();
            }
        });
        loadAnimation.setAnimationListener(new VoxAnimationListener() { // from class: com.amazon.alexa.redesign.view.templates.voxIngressTemplate.VoxIngressTemplateViewHolder.3
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                VoxIngressTemplateViewHolder.this.voxButton.setVisibility(0);
                VoxIngressTemplateViewHolder.this.animationView.setVisibility(0);
                VoxIngressTemplateViewHolder.this.animationView.playAnimation();
                VoxIngressTemplateViewHolder.this.voxButton.setLayerType(0, null);
            }
        });
        this.voxButton.startAnimation(loadAnimation);
        this.hintView.startAnimation(loadAnimation2);
    }

    private void styleAlertBannerPlaceHolder() {
        ((ImageView) this.headerAndAlertBannerPlaceholder.findViewById(R.id.header_placeholder_image)).setColorFilter(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction40));
        this.alertBannerPlaceholder.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
        this.alertBannerPlaceholder.setTypeface(FontResolver.getFont(this.context, Font.AMAZON_EMBER_REGULAR));
    }

    private void styleFosIcon() {
        this.fosVersionWrapper.findViewById(R.id.fire_os_vox_icon_bg).setBackgroundResource(R.drawable.amahc_fire_os_vox_button);
        ((ImageView) this.fosVersionWrapper.findViewById(R.id.fire_os_vox_icon)).setColorFilter(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction10));
    }

    private void styleHint() {
        this.hintView.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction10));
        ((RelativeLayout.LayoutParams) this.hintView.getLayoutParams()).bottomMargin = (int) (this.context.getResources().getInteger(R.integer.amahc_vox_spacing_bottom) * this.context.getResources().getDisplayMetrics().density);
        this.hintView.setTypeface(FontResolver.getFont(this.context, Font.AMAZON_EMBER_REGULAR));
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void detach() {
        this.animationView.cancelAnimation();
        this.animationView.setVisibility(4);
        this.animationView.setProgress(0.0f);
        if (!TemplateHelperUtil.isFireOS()) {
            this.itemView.setAlpha(0.0f);
        }
    }

    public /* synthetic */ void lambda$bindVoxModel$0$VoxIngressTemplateViewHolder(VoxIngressTemplateModel voxIngressTemplateModel, View view) {
        voxIngressTemplateModel.getOnVoxClickAction().execute(buildVoxClickParams(System.currentTimeMillis(), "jasperhomevoxcard"));
        this.metricsRecorder.recordClickEvent(voxIngressTemplateModel.getTopLevelMetricsObject());
    }

    public /* synthetic */ boolean lambda$initDebugMenu$1$VoxIngressTemplateViewHolder(View view) {
        this.presenter.activateDebugMenu(this.context);
        return false;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.BaseCardViewHolder
    public void bind(@NonNull VoxIngressTemplateViewItem voxIngressTemplateViewItem, int i, boolean z) {
        boolean isOfflineMode = voxIngressTemplateViewItem.mo2390getModel().isOfflineMode();
        bindAlertBanner(voxIngressTemplateViewItem.getOutageModel(), isOfflineMode);
        bindRefreshPillPlaceholder(voxIngressTemplateViewItem.mo2390getModel());
        if (TemplateHelperUtil.isFireOS()) {
            handleFireOS(isOfflineMode);
            return;
        }
        handleNotFireOS();
        bindVoxModel(voxIngressTemplateViewItem, isOfflineMode);
        startVoxAnimation(voxIngressTemplateViewItem);
    }
}
