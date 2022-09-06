package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics;
import com.amazon.alexa.biloba.utils.LogUtils;
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
/* loaded from: classes6.dex */
public final class BilobaDependencies {
    private static final String TAG = "BilobaDependencies";
    private static BilobaComponent bilobaComponent;

    private BilobaDependencies() {
    }

    public static synchronized void initialize() {
        synchronized (BilobaDependencies.class) {
            if (bilobaComponent == null) {
                LogUtils.d(TAG, "initializing dagger component");
                bilobaComponent = DaggerBilobaComponent.builder().applicationModule(new ApplicationModule()).build();
            }
        }
    }

    public static synchronized void inject(BilobaViewWithMetrics bilobaViewWithMetrics) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(bilobaViewWithMetrics);
        }
    }

    public static synchronized void inject(StartupView startupView) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(startupView);
        }
    }

    public static synchronized void inject(WebviewView webviewView) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(webviewView);
        }
    }

    public static synchronized void inject(BilobaDashboardView bilobaDashboardView) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(bilobaDashboardView);
        }
    }

    public static synchronized void inject(BilobaDashboardViewModel bilobaDashboardViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(bilobaDashboardViewModel);
        }
    }

    public static synchronized void inject(GettingStartedViewV3 gettingStartedViewV3) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(gettingStartedViewV3);
        }
    }

    public static synchronized void inject(AlertsListViewModel alertsListViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(alertsListViewModel);
        }
    }

    public static synchronized void inject(AlertSettingsViewModel alertSettingsViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(alertSettingsViewModel);
        }
    }

    public static synchronized void inject(RecentActivityListViewModel recentActivityListViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(recentActivityListViewModel);
        }
    }

    public static synchronized void inject(AlertSettingsView alertSettingsView) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(alertSettingsView);
        }
    }

    public static synchronized void inject(TipsViewModel tipsViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(tipsViewModel);
        }
    }

    public static synchronized void inject(ProfileSettingsViewModel profileSettingsViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(profileSettingsViewModel);
        }
    }

    public static synchronized void inject(ConfirmationViewModel confirmationViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(confirmationViewModel);
        }
    }

    public static synchronized void inject(MembershipViewModel membershipViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(membershipViewModel);
        }
    }

    public static synchronized void inject(StartupViewModel startupViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(startupViewModel);
        }
    }

    public static synchronized void inject(EmergencyContactViewModel emergencyContactViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(emergencyContactViewModel);
        }
    }

    public static synchronized void inject(EmergencyViewModel emergencyViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(emergencyViewModel);
        }
    }

    public static synchronized void inject(CommsSetupViewModel commsSetupViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(commsSetupViewModel);
        }
    }

    public static synchronized void inject(RootViewControllerFactory rootViewControllerFactory) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(rootViewControllerFactory);
        }
    }

    public static synchronized void inject(TimeZonePickerViewModel timeZonePickerViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(timeZonePickerViewModel);
        }
    }

    public static synchronized void inject(GettingStartedViewModelV3 gettingStartedViewModelV3) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(gettingStartedViewModelV3);
        }
    }

    public static synchronized void inject(InfoModalViewModel infoModalViewModel) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(infoModalViewModel);
        }
    }

    public static synchronized void inject(InfoModalView infoModalView) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(infoModalView);
        }
    }

    public static synchronized void inject(ErrorView errorView) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(errorView);
        }
    }

    public static synchronized void inject(EmergencyView emergencyView) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(emergencyView);
        }
    }

    public static synchronized void inject(DashboardBottomSheet dashboardBottomSheet) {
        synchronized (BilobaDependencies.class) {
            bilobaComponent.bilobaViewComponent().create().inject(dashboardBottomSheet);
        }
    }
}
