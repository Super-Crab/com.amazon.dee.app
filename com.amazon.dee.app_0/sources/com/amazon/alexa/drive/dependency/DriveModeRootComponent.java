package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.comms.view.CommsLandingPageViewController;
import com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageViewController;
import com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageViewControllerV2;
import com.amazon.alexa.drive.entertainment.view.NowPlayingScreenViewController;
import com.amazon.alexa.drive.landing.LandingPageViewController;
import com.amazon.alexa.drive.landing.LandingPageViewControllerV2;
import com.amazon.alexa.drive.main.v2.DriveModeMainActivityViewModel;
import com.amazon.alexa.drive.navigation.NavigationViewController;
import com.amazon.alexa.drive.navigation.NavigationViewControllerV2;
import com.amazon.alexa.drive.smart.device.LockPermissionViewController;
import com.amazon.alexa.drive.smart.device.SmartHomeViewController;
import com.amazon.alexa.drive.view.ViewManagerViewController;
import com.amazon.alexa.drive.warning.WarningViewController;
import dagger.Subcomponent;
import javax.inject.Singleton;
@Singleton
@Subcomponent(modules = {AndroidModule.class, ModeMetricsModule.class, PresenterModule.class, RepositoryModule.class, RoutingModule.class})
/* loaded from: classes7.dex */
public interface DriveModeRootComponent {
    void inject(CommsLandingPageViewController commsLandingPageViewController);

    void inject(EntertainmentLandingPageViewController entertainmentLandingPageViewController);

    void inject(EntertainmentLandingPageViewControllerV2 entertainmentLandingPageViewControllerV2);

    void inject(NowPlayingScreenViewController nowPlayingScreenViewController);

    void inject(LandingPageViewController landingPageViewController);

    void inject(LandingPageViewControllerV2 landingPageViewControllerV2);

    void inject(DriveModeMainActivityViewModel driveModeMainActivityViewModel);

    void inject(NavigationViewController navigationViewController);

    void inject(NavigationViewControllerV2 navigationViewControllerV2);

    void inject(LockPermissionViewController lockPermissionViewController);

    void inject(SmartHomeViewController smartHomeViewController);

    void inject(ViewManagerViewController viewManagerViewController);

    void inject(WarningViewController warningViewController);
}
