package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics;
import com.amazon.alexa.biloba.view.RootViewControllerFactory;
import com.amazon.alexa.biloba.view.account.MembershipViewModel;
import com.amazon.alexa.biloba.view.account.ProfileSettingsViewModel;
import com.amazon.alexa.biloba.view.account.timeZonePicker.TimeZonePickerViewModel;
import com.amazon.alexa.biloba.view.alerts.AlertsListViewModel;
import com.amazon.alexa.biloba.view.alerts.edit.AlertSettingsView;
import com.amazon.alexa.biloba.view.alerts.edit.AlertSettingsViewModel;
import com.amazon.alexa.biloba.view.cards.DashboardBottomSheet;
import com.amazon.alexa.biloba.view.comms.CommsSetupViewModel;
import com.amazon.alexa.biloba.view.comms.EmergencyContactViewModel;
import com.amazon.alexa.biloba.view.comms.EmergencyView;
import com.amazon.alexa.biloba.view.comms.EmergencyViewModel;
import com.amazon.alexa.biloba.view.confirmation.ConfirmationViewModel;
import com.amazon.alexa.biloba.view.dashboard.BilobaDashboardView;
import com.amazon.alexa.biloba.view.dashboard.BilobaDashboardViewModel;
import com.amazon.alexa.biloba.view.gettingStartedV3.GettingStartedViewModelV3;
import com.amazon.alexa.biloba.view.gettingStartedV3.GettingStartedViewV3;
import com.amazon.alexa.biloba.view.infoModal.InfoModalView;
import com.amazon.alexa.biloba.view.infoModal.InfoModalViewModel;
import com.amazon.alexa.biloba.view.recent.RecentActivityListViewModel;
import com.amazon.alexa.biloba.view.startup.StartupView;
import com.amazon.alexa.biloba.view.startup.StartupViewModel;
import com.amazon.alexa.biloba.view.tips.TipsViewModel;
import com.amazon.alexa.biloba.view.webview.WebviewView;
import com.amazon.alexa.mosaic.view.ErrorView;
import dagger.Subcomponent;
@Subcomponent
@BilobaScope
/* loaded from: classes6.dex */
public interface BilobaViewComponent {

    @Subcomponent.Builder
    /* loaded from: classes6.dex */
    public interface Factory {
        BilobaViewComponent create();
    }

    void inject(BilobaViewWithMetrics bilobaViewWithMetrics);

    void inject(RootViewControllerFactory rootViewControllerFactory);

    void inject(MembershipViewModel membershipViewModel);

    void inject(ProfileSettingsViewModel profileSettingsViewModel);

    void inject(TimeZonePickerViewModel timeZonePickerViewModel);

    void inject(AlertsListViewModel alertsListViewModel);

    void inject(AlertSettingsView alertSettingsView);

    void inject(AlertSettingsViewModel alertSettingsViewModel);

    void inject(DashboardBottomSheet dashboardBottomSheet);

    void inject(CommsSetupViewModel commsSetupViewModel);

    void inject(EmergencyContactViewModel emergencyContactViewModel);

    void inject(EmergencyView emergencyView);

    void inject(EmergencyViewModel emergencyViewModel);

    void inject(ConfirmationViewModel confirmationViewModel);

    void inject(BilobaDashboardView bilobaDashboardView);

    void inject(BilobaDashboardViewModel bilobaDashboardViewModel);

    void inject(GettingStartedViewModelV3 gettingStartedViewModelV3);

    void inject(GettingStartedViewV3 gettingStartedViewV3);

    void inject(InfoModalView infoModalView);

    void inject(InfoModalViewModel infoModalViewModel);

    void inject(RecentActivityListViewModel recentActivityListViewModel);

    void inject(StartupView startupView);

    void inject(StartupViewModel startupViewModel);

    void inject(TipsViewModel tipsViewModel);

    void inject(WebviewView webviewView);

    void inject(ErrorView errorView);
}
