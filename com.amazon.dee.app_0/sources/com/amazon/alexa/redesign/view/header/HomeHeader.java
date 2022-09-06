package com.amazon.alexa.redesign.view.header;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.data.registry.NativeFeatureRegistry;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.growth.CoachMarkFactory;
import com.amazon.alexa.growth.coachmark.CoachMark;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.navigation.menu.constants.FeatureConstants;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.api.Header;
import com.amazon.alexa.redesign.header.HeaderNavigationDelegate;
import com.amazon.alexa.redesign.repository.VoxIngressRepository;
import com.amazon.alexa.redesign.utils.Constants;
import com.amazon.alexa.redesign.view.templates.TemplateHelperUtil;
import com.amazon.alexa.redesign.view.templates.voxIngressTemplate.VoxIngressTemplateViewHolder;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.alexa.voice.model.VoiceService;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes10.dex */
public class HomeHeader implements Header {
    private static final String TAG = "HomeHeader";
    private FontTextView alertBannerTextView;
    private Context context;
    private FeatureServiceV2 featureService;
    private HeaderNavigationDelegate headerNavigationDelegate;
    private ImageView headerVoxIcon;
    private RelativeLayout headerVoxIconTextWrapper;
    private FontTextView headerVoxText;
    private RelativeLayout homeHeader;
    private HomeContract.Presenter presenter;
    private RoutingService routingService;
    private CoachMark twaCoachMark;
    private ImageView twaKeyboard;
    private VoiceService voiceService;
    private VoxIngressRepository voxIngressRepository;
    private boolean isTWAEnabledFromEB = false;
    private CoachMarkFactory coachMarkFactory = (CoachMarkFactory) GeneratedOutlineSupport1.outline20(CoachMarkFactory.class);

    public HomeHeader(View view, Context context, HeaderNavigationDelegate headerNavigationDelegate, VoiceService voiceService, HomeContract.Presenter presenter, VoxIngressRepository voxIngressRepository, FeatureServiceV2 featureServiceV2, RoutingService routingService, boolean z) {
        this.context = context;
        this.headerNavigationDelegate = headerNavigationDelegate;
        this.voiceService = voiceService;
        this.presenter = presenter;
        this.voxIngressRepository = voxIngressRepository;
        this.featureService = featureServiceV2;
        this.routingService = routingService;
        init(view, z);
    }

    private void changeAlphaForOffline() {
        if (this.headerVoxIconTextWrapper.getAlpha() > 0.0f) {
            this.headerVoxIconTextWrapper.setAlpha(0.3f);
        } else if (!isTWAEnabled()) {
        } else {
            this.twaKeyboard.setAlpha(0.3f);
        }
    }

    private void changeAlphaForOnline() {
        if (this.headerVoxIconTextWrapper.getAlpha() > 0.0f) {
            this.headerVoxIconTextWrapper.setAlpha(1.0f);
        } else if (!isTWAEnabled()) {
        } else {
            this.twaKeyboard.setAlpha(1.0f);
        }
    }

    private void init(View view, boolean z) {
        initHomeHeader(view);
        initRightSideOfHeader();
        lambda$rerenderLeftHeader$11$HomeHeader(z);
        initAlertBanner();
        initTwaRequest();
    }

    private void initAlertBanner() {
        this.alertBannerTextView = (FontTextView) this.homeHeader.findViewById(R.id.banner_text);
        View findViewById = this.homeHeader.findViewById(R.id.banner_notification);
        this.alertBannerTextView.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
        findViewById.setBackgroundColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral50));
        TemplateHelperUtil.scaleTextViewWithFontFireOS(this.alertBannerTextView, this.context, R.integer.amahc_fireos_font_scaling_extra_small_text);
    }

    private void initDiscover() {
        ImageView imageView = (ImageView) this.homeHeader.findViewById(R.id.home_header_discover);
        imageView.setVisibility(0);
        imageView.setColorFilter(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.header.-$$Lambda$HomeHeader$PJcmgN8_x_wKFfBZDZpw2_rESvI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeHeader.this.lambda$initDiscover$2$HomeHeader(view);
            }
        });
        if (Constants.AutomationConstants.isQABuild) {
            imageView.setContentDescription("discover_header");
        } else {
            imageView.setContentDescription(this.context.getResources().getString(R.string.amahc_discover_talkback));
        }
    }

    private void initHeaderVoxIcon(boolean z) {
        this.headerVoxIcon = (ImageView) this.homeHeader.findViewById(R.id.home_header_alexa_icon);
        this.headerVoxIcon.setColorFilter(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction40));
        if (Constants.AutomationConstants.isQABuild) {
            this.headerVoxIcon.setContentDescription("vox_logo_header");
        } else {
            this.headerVoxIcon.setContentDescription(this.context.getResources().getString(R.string.amahc_alexa_button_talkback));
        }
        if (z) {
            this.headerVoxIcon.setOnClickListener(null);
        } else {
            this.headerVoxIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.header.-$$Lambda$HomeHeader$uRBpJWuqlladOzYERxaLgDY8i_s
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeHeader.this.lambda$initHeaderVoxIcon$6$HomeHeader(view);
                }
            });
        }
    }

    private void initHeaderVoxIconTextWrapper() {
        this.headerVoxIconTextWrapper = (RelativeLayout) this.homeHeader.findViewById(R.id.home_header_alexa_wrapper);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.headerVoxIconTextWrapper.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.addRule(0, R.id.home_header_help_feedback);
            this.headerVoxIconTextWrapper.setLayoutParams(layoutParams);
        }
    }

    private void initHeaderVoxText(boolean z, boolean z2) {
        if (z) {
            this.headerVoxText = (FontTextView) this.homeHeader.findViewById(R.id.home_header_alexa_hint_twa);
            this.homeHeader.findViewById(R.id.home_header_alexa_hint).setVisibility(8);
        } else {
            this.headerVoxText = (FontTextView) this.homeHeader.findViewById(R.id.home_header_alexa_hint);
            this.homeHeader.findViewById(R.id.home_header_rounded_button).setVisibility(8);
            if (Constants.AutomationConstants.isQABuild) {
                this.headerVoxText.setContentDescription("vox_logo_header_text");
            }
        }
        this.headerVoxText.setTextColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral20));
        TemplateHelperUtil.scaleTextViewWithFontFireOS(this.headerVoxText, this.context, R.integer.amahc_fireos_font_scaling_vox_card_hint);
        if (z2) {
            this.headerVoxText.setOnClickListener(null);
        } else {
            this.headerVoxText.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.header.-$$Lambda$HomeHeader$psbiSa9j7MYnbTxVKEHS6BlXNRQ
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeHeader.this.lambda$initHeaderVoxText$7$HomeHeader(view);
                }
            });
        }
    }

    private void initHelpAndFeedback() {
        ImageView imageView = (ImageView) this.homeHeader.findViewById(R.id.home_header_help_feedback);
        imageView.setVisibility(0);
        imageView.setColorFilter(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.header.-$$Lambda$HomeHeader$WlgSsPb6cOwyfUZAbiglc8AqQc8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeHeader.this.lambda$initHelpAndFeedback$4$HomeHeader(view);
            }
        });
        if (Constants.AutomationConstants.isQABuild) {
            imageView.setContentDescription("help_feedback_header");
        } else {
            imageView.setContentDescription(this.context.getResources().getString(R.string.amahc_help_and_feedback_talkback));
        }
    }

    private void initHomeHeader(View view) {
        this.homeHeader = (RelativeLayout) view.findViewById(R.id.home_header);
        this.homeHeader.setOnTouchListener($$Lambda$HomeHeader$TVxXitudwm7J73vfGsJjAjLfC_0.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initLeftSideOfHeader */
    public void lambda$rerenderLeftHeader$11$HomeHeader(boolean z) {
        boolean isTWAEnabled = isTWAEnabled();
        initTWAKeyboard(isTWAEnabled, z);
        initHeaderVoxIcon(z);
        initHeaderVoxText(isTWAEnabled, z);
        initTWARoundedButton(isTWAEnabled);
        initHeaderVoxIconTextWrapper();
        setHeaderVoxText(isTWAEnabled);
        initAlertBanner();
    }

    private void initRightSideOfHeader() {
        String treatmentAndRecordTrigger = this.featureService.getTreatmentAndRecordTrigger(NativeFeatureRegistry.ALEXA_CH_HOME_TOP_RIGHT_INGRESS, "C_DEFAULT");
        if (treatmentAndRecordTrigger.equals("T1")) {
            initDiscover();
        } else if (treatmentAndRecordTrigger.equals(FeatureConstants.Treatments.T2)) {
            initSettings();
        } else {
            initHelpAndFeedback();
        }
    }

    private void initSettings() {
        ImageView imageView = (ImageView) this.homeHeader.findViewById(R.id.home_header_settings);
        imageView.setVisibility(0);
        imageView.setColorFilter(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.header.-$$Lambda$HomeHeader$dhKa98W63Dbn9nLLnEwZ9CYWT7w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeHeader.this.lambda$initSettings$3$HomeHeader(view);
            }
        });
        if (Constants.AutomationConstants.isQABuild) {
            imageView.setContentDescription("settings_header");
        } else {
            imageView.setContentDescription(this.context.getResources().getString(R.string.amahc_settings_talkback));
        }
    }

    private void initTWAKeyboard(boolean z, boolean z2) {
        this.twaKeyboard = (ImageView) this.homeHeader.findViewById(R.id.home_header_twa_keyboard);
        if (z) {
            this.twaKeyboard.setVisibility(0);
            if (z2) {
                this.twaKeyboard.setOnClickListener(null);
            } else {
                this.twaKeyboard.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.header.-$$Lambda$HomeHeader$75K-1c4xUD3uCoE42jHKh-k6T4s
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HomeHeader.this.lambda$initTWAKeyboard$5$HomeHeader(view);
                    }
                });
                showTWACoachMark();
            }
            this.twaKeyboard.setColorFilter(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral10));
            if (Constants.AutomationConstants.isQABuild) {
                this.twaKeyboard.setContentDescription("twa_keyboard");
                return;
            }
            this.twaKeyboard.setContentDescription(this.context.getResources().getString(R.string.amahc_twa_hint));
            return;
        }
        this.twaKeyboard.setVisibility(8);
    }

    private void initTWARoundedButton(boolean z) {
        if (z) {
            this.homeHeader.findViewById(R.id.home_header_rounded_button).setVisibility(0);
            this.headerVoxText.setBackgroundColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicNeutral40));
            return;
        }
        this.homeHeader.findViewById(R.id.home_header_rounded_button).setVisibility(8);
    }

    private void initTwaRequest() {
        Handler handler = new Handler();
        int i = 0;
        while (i < 10) {
            i++;
            handler.postDelayed(new Runnable() { // from class: com.amazon.alexa.redesign.view.header.-$$Lambda$HomeHeader$bOlZBX-87eIQ4d_De5sqpGB_Hbc
                @Override // java.lang.Runnable
                public final void run() {
                    HomeHeader.this.lambda$initTwaRequest$0$HomeHeader();
                }
            }, i * 1000);
        }
    }

    private boolean isTWAEnabled() {
        return this.isTWAEnabledFromEB && !TemplateHelperUtil.isFireOS();
    }

    static /* synthetic */ boolean lambda$initHomeHeader$1(View view, MotionEvent motionEvent) {
        return true;
    }

    private void onHeaderVoxClick() {
        if (this.headerVoxIconTextWrapper.getAlpha() > 0.0f) {
            this.presenter.onHeaderVoxClick(VoxIngressTemplateViewHolder.buildVoxClickParams(System.currentTimeMillis(), "jasperhomeheader"));
        }
    }

    private void onTWAClicked(String str) {
        if (str.equals(Constants.REFERER_VOX_CARD_TWA_KEYBOARD) && this.headerVoxIconTextWrapper.getAlpha() > 0.0f) {
            str = Constants.REFERER_HEADER_TWA;
        }
        if ((!str.equals(Constants.REFERER_HEADER_TWA) || this.headerVoxIconTextWrapper.getAlpha() <= 0.0f) && !str.equals(Constants.REFERER_VOX_CARD_TWA_KEYBOARD)) {
            return;
        }
        this.presenter.onTWAClick(VoxIngressTemplateViewHolder.buildVoxClickParams(System.currentTimeMillis(), str), this.voxIngressRepository.getTWAAction());
    }

    private void onTWAKeyboardClicked() {
        if (this.headerVoxIconTextWrapper.getAlpha() > 0.0f) {
            onHeaderVoxClick();
        } else {
            onTWAClicked(Constants.REFERER_VOX_CARD_TWA_KEYBOARD);
        }
    }

    private void route(String str) {
        try {
            this.routingService.stopLoadingTimeout();
            this.routingService.navigate(str);
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline115("Route: ", str, " is invalid "), TAG);
        }
    }

    private void setHeaderVoxText(boolean z) {
        if (z) {
            this.headerVoxText.setText(R.string.amahc_twa_hint);
        } else {
            this.headerVoxText.setText(this.voxIngressRepository.getVoxHintStringId(this.voiceService));
        }
    }

    private void showTWACoachMark() {
        if (this.twaCoachMark != null) {
            return;
        }
        View findViewById = this.homeHeader.findViewById(R.id.home_header_twa_keyboard);
        if (findViewById != null && this.coachMarkFactory != null) {
            this.twaCoachMark = this.coachMarkFactory.getCoachMark(findViewById, this.context.getResources().getResourceEntryName(R.id.amahc_header_twa_keyboard_coachmark));
            this.twaCoachMark.setText(this.context.getResources().getString(R.string.amahc_twa_coachmark));
            this.twaCoachMark.show();
            return;
        }
        Log.w(TAG, "Unable to mount TWA CoachMark, anchorView or coachMarkFactory are null");
    }

    @Override // com.amazon.alexa.redesign.api.Header
    public void convertToOffline() {
        this.alertBannerTextView.setText(R.string.amahc_no_connection);
        this.alertBannerTextView.setVisibility(0);
        this.headerVoxText.setOnClickListener(null);
        this.headerVoxIcon.setOnClickListener(null);
        if (isTWAEnabled()) {
            this.twaKeyboard.setOnClickListener(null);
        }
        changeAlphaForOffline();
    }

    @Override // com.amazon.alexa.redesign.api.Header
    public void convertToOnline() {
        this.alertBannerTextView.setVisibility(8);
        this.headerVoxText.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.header.-$$Lambda$HomeHeader$cmhWL1KDrXC_PZlQRyqSXiSkK-I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeHeader.this.lambda$convertToOnline$8$HomeHeader(view);
            }
        });
        this.headerVoxIcon.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.header.-$$Lambda$HomeHeader$pP-zq_jn-5iZOpzzNKAzFOCBoB0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeHeader.this.lambda$convertToOnline$9$HomeHeader(view);
            }
        });
        if (isTWAEnabled()) {
            this.twaKeyboard.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.header.-$$Lambda$HomeHeader$7N2FRixpRk2D8ELEa4vD1roUE40
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeHeader.this.lambda$convertToOnline$10$HomeHeader(view);
                }
            });
            showTWACoachMark();
        }
        changeAlphaForOnline();
    }

    @Override // com.amazon.alexa.redesign.api.Header
    public int getHeight() {
        return this.homeHeader.getHeight();
    }

    public /* synthetic */ void lambda$convertToOnline$10$HomeHeader(View view) {
        onTWAKeyboardClicked();
    }

    public /* synthetic */ void lambda$convertToOnline$8$HomeHeader(View view) {
        if (isTWAEnabled()) {
            onTWAClicked(Constants.REFERER_HEADER_TWA);
        } else {
            onHeaderVoxClick();
        }
    }

    public /* synthetic */ void lambda$convertToOnline$9$HomeHeader(View view) {
        onHeaderVoxClick();
    }

    public /* synthetic */ void lambda$initDiscover$2$HomeHeader(View view) {
        route(RouteName.ELEMENTS_A2S_FRONT_PAGE);
    }

    public /* synthetic */ void lambda$initHeaderVoxIcon$6$HomeHeader(View view) {
        onHeaderVoxClick();
    }

    public /* synthetic */ void lambda$initHeaderVoxText$7$HomeHeader(View view) {
        if (isTWAEnabled()) {
            onTWAClicked(Constants.REFERER_HEADER_TWA);
        } else {
            onHeaderVoxClick();
        }
    }

    public /* synthetic */ void lambda$initHelpAndFeedback$4$HomeHeader(View view) {
        this.headerNavigationDelegate.onHelpAndFeedbackClicked();
    }

    public /* synthetic */ void lambda$initSettings$3$HomeHeader(View view) {
        route(RouteName.ELEMENTS_SETTINGS);
    }

    public /* synthetic */ void lambda$initTWAKeyboard$5$HomeHeader(View view) {
        onTWAKeyboardClicked();
    }

    public /* synthetic */ void lambda$initTwaRequest$0$HomeHeader() {
        this.presenter.publishTWAAvailablityRequest();
    }

    @Override // com.amazon.alexa.redesign.api.Header
    public void rerenderLeftHeader(boolean z, final boolean z2) {
        this.isTWAEnabledFromEB = z;
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.redesign.view.header.-$$Lambda$HomeHeader$NG9LwDzQANnLkzO4EZ-ryBluXi8
            @Override // java.lang.Runnable
            public final void run() {
                HomeHeader.this.lambda$rerenderLeftHeader$11$HomeHeader(z2);
            }
        });
    }

    @Override // com.amazon.alexa.redesign.api.Header
    public void setOutageAlert(@Nullable String str) {
        if (str != null) {
            this.alertBannerTextView.setText(str);
            this.alertBannerTextView.setVisibility(0);
            return;
        }
        this.alertBannerTextView.setVisibility(8);
    }
}
