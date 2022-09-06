package com.amazon.alexa.biloba.view.startup;

import android.content.Context;
import android.os.Bundle;
import android.text.Annotation;
import android.text.SpannedString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.StartupViewBinding;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.routing.DeferredRoutingHelper;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.utils.BilobaRouteUtil;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.utils.UrlHelper;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.biloba.view.confirmation.ConfirmationViewModel;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.mosaic.view.ViewUtils;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import java.util.Date;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class StartupView extends BilobaViewController {
    public static final String EMPTY_STRING = "";
    public static final String PASSCODE_VERIFIED_BOOL = "passcode_verified";
    private static final String TAG = "StartupView";
    private Context context;
    @Inject
    Lazy<DeferredRoutingHelper> deferredRoutingHelper;
    private MobilyticsMetricsTimer passcodeCheckTimer;
    @Inject
    Lazy<RoutingService> routingService;
    private MobilyticsMetricsTimer startupInitializationTimer;
    private StartupViewModel startupViewModel;
    @Inject
    Lazy<UrlHelper> urlHelper;
    Observer<CareActor> startupViewObserver = new Observer() { // from class: com.amazon.alexa.biloba.view.startup.-$$Lambda$StartupView$Zq_ZWzeN4M15JSTtvZMYhlX5Hpo
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            StartupView.this.lambda$new$0$StartupView((CareActor) obj);
        }
    };
    Observer<Boolean> careGiverObserver = new Observer() { // from class: com.amazon.alexa.biloba.view.startup.-$$Lambda$StartupView$myMQYDdNCDQIebEkRpjrTG3aDaE
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            StartupView.this.lambda$new$1$StartupView((Boolean) obj);
        }
    };
    Observer<Integer> passcodeObserver = new Observer() { // from class: com.amazon.alexa.biloba.view.startup.-$$Lambda$StartupView$WqJfceeuzrBKAJDNUiGoB0htvmc
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            StartupView.this.lambda$new$2$StartupView((Integer) obj);
        }
    };
    private final long startupViewBuiltStartTimestamp = new Date().getTime();

    public StartupView(Context context, Bundle bundle) {
        BilobaDependencies.inject(this);
        this.context = context;
        this.startupViewModel = new StartupViewModel();
        this.startupViewModel.setPasscodeAuthorizedByRouteArg(bundle.getBoolean(PASSCODE_VERIFIED_BOOL, false));
    }

    public void clearTTCFOnError(Throwable th) {
        if (th != null) {
            this.startupViewModel.resetTTCFTimers();
        }
    }

    private void handleProfilePinError() {
        LogUtils.w(TAG, "ProfilePIN error.");
        recordOccurrence(MetricsConstants.OperationalMetrics.PROFILE_PIN_ERROR, true);
        new ConfirmationViewModel(this.context).setIconResId(R.drawable.ic_alexa).setIconColor(ThemeUtil.getColorFromAttribute(this.context, R.attr.mosaicAction20)).setHeadlineText(this.context.getString(R.string.error_pin_headline)).setBodyText(this.context.getString(R.string.error_pin_body)).setIsSuccess(true).show();
    }

    private void maybeRouteToBiloba() {
        if (!this.startupViewModel.isReadyToRouteToBiloba()) {
            return;
        }
        CareActor value = this.startupViewModel.getCareContact().getValue();
        if (this.deferredRoutingHelper.mo358get().getDeferredRouteContext() != null) {
            this.startupViewModel.navigateToDeferredRoute();
        } else if (value == null) {
            if (this.startupViewModel.shouldShowLoneCr()) {
                showLoneCrModal();
                return;
            }
            BilobaRouteUtil.routeTo(this.routingService.mo358get(), Routes.BILOBA_GETTING_STARTED_V3);
            this.deferredRoutingHelper.mo358get().clearDeferredRouteContext();
        } else {
            BilobaRouteUtil.forceRouteTo(this.routingService.mo358get(), Routes.BILOBA_DASHBOARD);
        }
    }

    private void showLoneCrModal() {
        LogUtils.w(TAG, "Lone CR.");
        SpannedString spannedString = (SpannedString) this.context.getText(R.string.lone_cr_description_inline);
        Annotation[] annotationArr = (Annotation[]) spannedString.getSpans(0, spannedString.length(), Annotation.class);
        new ConfirmationViewModel(this.context).setHeadlineText(this.context.getString(R.string.lone_cr_headline)).setBodyText(this.context.getString(R.string.lone_cr_body)).setOkButtonText(this.context.getString(R.string.lone_cr_button)).setOkButtonRoute(this.startupViewModel.isMultiCGEnabled() ? Routes.BILOBA_INVITE_CARE_GIVER : Routes.BILOBA_LONE_CR_CREATE_RELATIONSHIP).setInlineHyperlinkText((annotationArr == null || annotationArr.length <= 0) ? "" : spannedString.subSequence(0, spannedString.getSpanStart(annotationArr[0]) - 1).toString(), (annotationArr == null || annotationArr.length <= 0) ? "" : spannedString.subSequence(spannedString.getSpanStart(annotationArr[0]), spannedString.getSpanEnd(annotationArr[0])).toString(), "", this.urlHelper.mo358get().getEditEmergencyAddressUrl(this.context.getResources().getConfiguration())).setMetrics(MetricsConstants.UserInteractionMetrics.LONE_CR_INVITE_BUTTON, MetricsConstants.UserInteractionMetrics.LONE_CR_MEMBERSHIPS_LINK, MetricsConstants.UserInteractionMetrics.LONE_CR_VIEW).setIsSuccess(true).setTtcfEventName(MetricsConstants.TTCFMetrics.BILOBA_LONE_CR).show();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getResources().getString(R.string.main_title);
    }

    public /* synthetic */ void lambda$new$0$StartupView(CareActor careActor) {
        this.startupViewModel.notifyReceivedCareContact();
        maybeRouteToBiloba();
    }

    public /* synthetic */ void lambda$new$1$StartupView(Boolean bool) {
        this.startupViewModel.notifyReceivedCRCGStatus();
        this.startupViewModel.isCareGiver().removeObserver(this.careGiverObserver);
        if (bool == Boolean.TRUE) {
            LogUtils.d(TAG, "This is a care giver's account. Checking passcode status.");
            this.passcodeCheckTimer = this.startupViewModel.startTimer("PasscodeCheck.Time", new Date().getTime());
            this.startupViewModel.getPasscodeStatus().observe((LifecycleOwner) this.context, this.passcodeObserver);
            this.startupViewModel.requestPasscodeStatus();
            return;
        }
        LogUtils.d(TAG, "This is not a care giver's account");
        maybeRouteToBiloba();
    }

    public /* synthetic */ void lambda$new$2$StartupView(Integer num) {
        if (num.intValue() == 4) {
            MobilyticsMetricsTimer mobilyticsMetricsTimer = this.passcodeCheckTimer;
            if (mobilyticsMetricsTimer != null) {
                this.startupViewModel.finishTimer(mobilyticsMetricsTimer);
            }
            maybeRouteToBiloba();
        } else if (num.intValue() == 3) {
            BilobaRouteUtil.routeToUsingRouteMatch(this.routingService.mo358get(), Routes.PASSCODE_VERIFICATION_ROUTE);
            this.startupViewModel.resetTTCFTimers();
            this.passcodeCheckTimer = null;
        } else if (num.intValue() == 2) {
            BilobaRouteUtil.routeToUsingRouteMatch(this.routingService.mo358get(), Routes.PASSCODE_SETUP_ROUTE);
            recordClickMetric(MetricsConstants.UserInteractionMetrics.PROFILE_PIN_SETUP, "");
            this.startupViewModel.resetTTCFTimers();
            this.passcodeCheckTimer = null;
        } else if (num.intValue() != 1) {
        } else {
            handleProfilePinError();
            this.startupViewModel.resetTTCFTimers();
            this.passcodeCheckTimer = null;
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.startupInitializationTimer = this.startupViewModel.startTimer("StartupViewBuilt.Time", this.startupViewBuiltStartTimestamp);
        StartupViewBinding startupViewBinding = (StartupViewBinding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context)), R.layout.startup_view, viewGroup, false);
        startupViewBinding.setLifecycleOwner((LifecycleOwner) viewGroup.getContext());
        startupViewBinding.setViewmodel(this.startupViewModel);
        View root = startupViewBinding.getRoot();
        registerViewAttributes((LinearLayout) root.findViewById(R.id.no_connection_banner), this.startupViewModel);
        MobilyticsMetricsTimer mobilyticsMetricsTimer = this.startupInitializationTimer;
        if (mobilyticsMetricsTimer != null) {
            this.startupViewModel.finishTimer(mobilyticsMetricsTimer);
        }
        return root;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric("StartupView", "");
        this.startupViewModel.create(null);
        subscribeToNetworkChange();
        this.startupViewModel.clear();
        this.startupViewModel.getCareContact().observe((LifecycleOwner) this.context, this.startupViewObserver);
        this.startupViewModel.isCareGiver().observe((LifecycleOwner) this.context, this.careGiverObserver);
        this.startupViewModel.getError().observe((LifecycleOwner) this.context, new $$Lambda$StartupView$KvT7pV4OJOzlpQ485hbzkreTihc(this));
        this.startupViewModel.sendRequest();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
        this.startupViewModel.getCareContact().removeObserver(this.startupViewObserver);
        this.startupViewModel.getPasscodeStatus().removeObserver(this.passcodeObserver);
        this.startupViewModel.isCareGiver().removeObserver(this.careGiverObserver);
        this.startupViewModel.getError().removeObserver(new $$Lambda$StartupView$KvT7pV4OJOzlpQ485hbzkreTihc(this));
        this.startupViewModel.destroy();
    }
}
