package com.amazon.alexa.biloba.view.confirmation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.routing.RouteArgumentKeys;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.utils.SpanBuilder;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public class ConfirmationViewModel implements Parcelable {
    private static final String SPACE = " ";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    private String bodyText;
    private Context context;
    @Inject
    Lazy<EnvironmentService> environmentService;
    private String headlineText;
    private String hintText;
    private String hyperlinkClickMetric;
    private String hyperlinkUrl;
    private int iconColor;
    private String iconContentDescription;
    private int iconResId;
    @VisibleForTesting
    ClickableSpan inlineClickableHyperlink;
    private String inlineHyperlinkText;
    private String inlinePrefixText;
    private String inlineSuffixText;
    private boolean isSuccess;
    private String linkRoute;
    private String linkText;
    private String okButtonClickMetric;
    private String okButtonRoute;
    private boolean okButtonRouteClearBackstack;
    private String okButtonText;
    private Bundle routingBundle;
    @Inject
    Lazy<RoutingService> routingService;
    private String titleText;
    private String ttcfEventName;
    private String viewMetric;
    private static final String TAG = ConfirmationViewModel.class.getSimpleName();
    public static final Parcelable.Creator<ConfirmationViewModel> CREATOR = new Parcelable.Creator<ConfirmationViewModel>() { // from class: com.amazon.alexa.biloba.view.confirmation.ConfirmationViewModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ConfirmationViewModel mo937createFromParcel(Parcel parcel) {
            return new ConfirmationViewModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ConfirmationViewModel[] mo938newArray(int i) {
            return new ConfirmationViewModel[i];
        }
    };

    public ConfirmationViewModel(Context context) {
        this.inlineClickableHyperlink = new ClickableSpan() { // from class: com.amazon.alexa.biloba.view.confirmation.ConfirmationViewModel.2
            @Override // android.text.style.ClickableSpan
            @SuppressLint({"VisibleForTests"})
            public void onClick(@NotNull View view) {
                if (ConfirmationViewModel.this.hyperlinkClickMetric != null) {
                    ConfirmationViewModel confirmationViewModel = ConfirmationViewModel.this;
                    confirmationViewModel.recordClickMetric(confirmationViewModel.hyperlinkClickMetric, MetricsConstants.CLICK_EVENT);
                }
                AndroidUtils.startWebview(ConfirmationViewModel.this.hyperlinkUrl, ConfirmationViewModel.this.context);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(ThemeUtil.getColorFromAttribute(ConfirmationViewModel.this.context, R.attr.mosaicAction10));
                textPaint.setUnderlineText(false);
            }
        };
        BilobaDependencies.inject(this);
        this.context = context;
        this.routingBundle = new Bundle();
        this.isSuccess = false;
    }

    private RoutingService.RoutingBuilder getConfirmationRoutingBuilder() {
        if (this.routingService.mo358get() != null) {
            return this.routingService.mo358get().route(Routes.BILOBA_CONFIRMATION).withAll(this.routingBundle).with(RouteArgumentKeys.PARCEL, this);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordClickMetric(@NonNull String str, @NonNull String str2) {
        Lazy<BilobaMetricsService> lazy = this.bilobaMetricsService;
        if (lazy != null) {
            lazy.mo358get().recordUserClick(str, str2);
        }
    }

    private void recordViewMetric(@NonNull String str, @NonNull String str2) {
        Lazy<BilobaMetricsService> lazy = this.bilobaMetricsService;
        if (lazy != null) {
            lazy.mo358get().recordUserView(str, str2);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getBodyText() {
        return this.bodyText;
    }

    public String getHeadlineText() {
        return this.headlineText;
    }

    public String getHintText() {
        return this.hintText;
    }

    public String getHyperlinkClickMetric() {
        return this.hyperlinkClickMetric;
    }

    public int getIconColor() {
        return this.iconColor;
    }

    public String getIconContentDescription() {
        return this.iconContentDescription;
    }

    public int getIconResId() {
        return this.iconResId;
    }

    public SpannableStringBuilder getInlineHyperlinkText() {
        if (this.inlinePrefixText == null && this.inlineHyperlinkText == null && this.inlineSuffixText == null) {
            return null;
        }
        SpanBuilder spanBuilder = new SpanBuilder();
        if (this.inlinePrefixText != null) {
            spanBuilder.append(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.inlinePrefixText, " "), new CharacterStyle[0]);
        }
        if (this.inlineHyperlinkText != null) {
            spanBuilder.append(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.inlineHyperlinkText, " "), this.inlineClickableHyperlink);
        }
        if (this.inlineSuffixText != null) {
            spanBuilder.append(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.inlineSuffixText, " "), new CharacterStyle[0]);
        }
        return spanBuilder.getValue();
    }

    public boolean getIsSuccess() {
        return this.isSuccess;
    }

    public String getLinkRoute() {
        return this.linkRoute;
    }

    public String getLinkText() {
        return this.linkText;
    }

    public String getOkButtonClickMetric() {
        return this.okButtonClickMetric;
    }

    public String getOkButtonRoute() {
        return this.okButtonRoute;
    }

    public String getOkButtonText() {
        return this.okButtonText;
    }

    public Bundle getRoutingBundle() {
        return this.routingBundle;
    }

    public String getTitleText() {
        return this.titleText;
    }

    public String getTtcfEventName() {
        return this.ttcfEventName;
    }

    public String getViewMetric() {
        return this.viewMetric;
    }

    @VisibleForTesting
    void handleNativeRoutes() {
        if (this.okButtonRouteClearBackstack) {
            if (this.okButtonRoute.equals(RouteName.HOME)) {
                AndroidUtils.popRouteBackstackForBiloba(this.routingService.mo358get());
                this.routingService.mo358get().route(RouteName.HOME).navigate();
                return;
            } else if (this.routingService.mo358get().popFromBackStack(this.okButtonRoute)) {
                return;
            } else {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("The desired route: \"");
                outline107.append(this.okButtonRoute);
                outline107.append("\" is not in the backstack.");
                LogUtils.d(str, outline107.toString());
                AndroidUtils.popRouteBackstackForBiloba(this.routingService.mo358get());
                this.routingService.mo358get().route("biloba").navigate();
                return;
            }
        }
        this.routingService.mo358get().route(this.okButtonRoute).withAll(this.routingBundle).addToBackStack().navigate();
    }

    public void onTapLink() {
        if (this.routingService.mo358get() == null) {
            LogUtils.e(TAG, "Routing service is null");
        } else if (this.linkRoute != null) {
            this.routingService.mo358get().route(this.linkRoute).withAll(this.routingBundle).navigateReplaceTop();
        } else {
            LogUtils.e(TAG, "Link route not defined");
        }
    }

    public void onTapOkButton() {
        String str = this.okButtonClickMetric;
        if (str != null) {
            recordClickMetric(str, MetricsConstants.CLICK_EVENT);
        }
        if (this.routingService.mo358get() == null) {
            LogUtils.e(TAG, "Routing service is null");
        } else if (this.okButtonRoute != null) {
            handleNativeRoutes();
        } else {
            LogUtils.e(TAG, "OK button route not defined");
        }
    }

    public ConfirmationViewModel setBodyText(@NonNull String str) {
        this.bodyText = str;
        return this;
    }

    public ConfirmationViewModel setHeadlineText(@NonNull String str) {
        this.headlineText = str;
        return this;
    }

    public ConfirmationViewModel setHintText(@NonNull String str) {
        this.hintText = str;
        return this;
    }

    public ConfirmationViewModel setIconColor(int i) {
        this.iconColor = i;
        return this;
    }

    public ConfirmationViewModel setIconContentDescription(String str) {
        this.iconContentDescription = str;
        return this;
    }

    public ConfirmationViewModel setIconResId(int i) {
        this.iconResId = i;
        return this;
    }

    public ConfirmationViewModel setInlineHyperlinkText(String str, String str2, String str3, String str4) {
        this.inlinePrefixText = str;
        this.inlineHyperlinkText = str2;
        this.inlineSuffixText = str3;
        this.hyperlinkUrl = str4;
        return this;
    }

    public ConfirmationViewModel setIsSuccess(@NonNull boolean z) {
        this.isSuccess = z;
        return this;
    }

    public ConfirmationViewModel setLinkRoute(@NonNull String str) {
        this.linkRoute = str;
        return this;
    }

    public ConfirmationViewModel setLinkText(@NonNull String str) {
        this.linkText = str;
        return this;
    }

    public ConfirmationViewModel setMetrics(String str, String str2, String str3) {
        this.okButtonClickMetric = str;
        this.hyperlinkClickMetric = str2;
        this.viewMetric = str3;
        return this;
    }

    public ConfirmationViewModel setOkButtonRoute(@NonNull String str) {
        return setOkButtonRoute(str, false);
    }

    public ConfirmationViewModel setOkButtonText(@NonNull String str) {
        this.okButtonText = str;
        return this;
    }

    public ConfirmationViewModel setRoutingBundle(@NonNull Bundle bundle) {
        this.routingBundle = bundle;
        return this;
    }

    public ConfirmationViewModel setTitleText(@NonNull String str) {
        this.titleText = str;
        return this;
    }

    public ConfirmationViewModel setTtcfEventName(String str) {
        this.ttcfEventName = str;
        return this;
    }

    public void show() {
        String str = this.viewMetric;
        if (str != null) {
            recordViewMetric(str, MetricsConstants.ENTER_EVENT);
        }
        RoutingService.RoutingBuilder confirmationRoutingBuilder = getConfirmationRoutingBuilder();
        if (confirmationRoutingBuilder != null) {
            confirmationRoutingBuilder.navigateReplaceTop();
        }
    }

    public void stopRecordingTTCF() {
        this.bilobaMetricsService.mo358get().stopRecordingTTCF(getTtcfEventName());
    }

    public ConfirmationViewModel with(@NonNull String str, String str2) {
        this.routingBundle.putString(str, str2);
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.titleText);
        parcel.writeInt(this.iconResId);
        parcel.writeInt(this.iconColor);
        parcel.writeString(this.iconContentDescription);
        parcel.writeString(this.headlineText);
        parcel.writeString(this.bodyText);
        parcel.writeString(this.linkText);
        parcel.writeString(this.linkRoute);
        parcel.writeString(this.hintText);
        parcel.writeString(this.okButtonText);
        parcel.writeString(this.okButtonRoute);
        parcel.writeBundle(this.routingBundle);
        parcel.writeInt(this.isSuccess ? 1 : 0);
    }

    public ConfirmationViewModel setOkButtonRoute(@NonNull String str, boolean z) {
        this.okButtonRoute = str;
        this.okButtonRouteClearBackstack = z;
        return this;
    }

    public ConfirmationViewModel with(@NonNull String str, int i) {
        this.routingBundle.putInt(str, i);
        return this;
    }

    public ConfirmationViewModel with(@NonNull String str, Bundle bundle) {
        this.routingBundle.putBundle(str, bundle);
        return this;
    }

    @VisibleForTesting
    ConfirmationViewModel(Lazy<RoutingService> lazy, Context context, Lazy<EnvironmentService> lazy2, Lazy<BilobaMetricsService> lazy3) {
        this.inlineClickableHyperlink = new ClickableSpan() { // from class: com.amazon.alexa.biloba.view.confirmation.ConfirmationViewModel.2
            @Override // android.text.style.ClickableSpan
            @SuppressLint({"VisibleForTests"})
            public void onClick(@NotNull View view) {
                if (ConfirmationViewModel.this.hyperlinkClickMetric != null) {
                    ConfirmationViewModel confirmationViewModel = ConfirmationViewModel.this;
                    confirmationViewModel.recordClickMetric(confirmationViewModel.hyperlinkClickMetric, MetricsConstants.CLICK_EVENT);
                }
                AndroidUtils.startWebview(ConfirmationViewModel.this.hyperlinkUrl, ConfirmationViewModel.this.context);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(ThemeUtil.getColorFromAttribute(ConfirmationViewModel.this.context, R.attr.mosaicAction10));
                textPaint.setUnderlineText(false);
            }
        };
        this.routingService = lazy;
        this.context = context;
        this.environmentService = lazy2;
        this.bilobaMetricsService = lazy3;
        this.routingBundle = new Bundle();
        this.isSuccess = false;
    }

    protected ConfirmationViewModel(Parcel parcel) {
        this.inlineClickableHyperlink = new ClickableSpan() { // from class: com.amazon.alexa.biloba.view.confirmation.ConfirmationViewModel.2
            @Override // android.text.style.ClickableSpan
            @SuppressLint({"VisibleForTests"})
            public void onClick(@NotNull View view) {
                if (ConfirmationViewModel.this.hyperlinkClickMetric != null) {
                    ConfirmationViewModel confirmationViewModel = ConfirmationViewModel.this;
                    confirmationViewModel.recordClickMetric(confirmationViewModel.hyperlinkClickMetric, MetricsConstants.CLICK_EVENT);
                }
                AndroidUtils.startWebview(ConfirmationViewModel.this.hyperlinkUrl, ConfirmationViewModel.this.context);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(ThemeUtil.getColorFromAttribute(ConfirmationViewModel.this.context, R.attr.mosaicAction10));
                textPaint.setUnderlineText(false);
            }
        };
        this.titleText = parcel.readString();
        this.iconResId = parcel.readInt();
        this.iconColor = parcel.readInt();
        this.iconContentDescription = parcel.readString();
        this.headlineText = parcel.readString();
        this.bodyText = parcel.readString();
        this.linkText = parcel.readString();
        this.linkRoute = parcel.readString();
        this.hintText = parcel.readString();
        this.okButtonText = parcel.readString();
        this.okButtonRoute = parcel.readString();
        this.routingBundle = parcel.readBundle();
        this.isSuccess = parcel.readInt() != 1 ? false : true;
        this.ttcfEventName = parcel.readString();
    }
}
