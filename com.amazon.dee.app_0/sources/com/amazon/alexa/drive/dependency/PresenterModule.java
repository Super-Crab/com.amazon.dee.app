package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.comms.contract.CommsLandingPageContract;
import com.amazon.alexa.drive.comms.presenter.CommsLandingPagePresenter;
import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.presenter.EntertainmentLandingPagePresenter;
import com.amazon.alexa.drive.entertainment.presenter.NowPlayingScreenPresenter;
import com.amazon.alexa.drive.main.DriveModeMainContract;
import com.amazon.alexa.drive.main.DriveModeMainPresenter;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.weblab.WeblabManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes7.dex */
class PresenterModule {
    @Provides
    @Singleton
    public CommsLandingPageContract.Presenter provideCommsLandingPagePresenter(CommsLandingPageContract.Interactor interactor) {
        return new CommsLandingPagePresenter(interactor);
    }

    @Provides
    @Singleton
    public EntertainmentLandingPageContract.Presenter provideEntertainmentLandingPagePresenter(EntertainmentLandingPageContract.EntertainmentInteractor entertainmentInteractor) {
        return new EntertainmentLandingPagePresenter(entertainmentInteractor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public DriveModeMainContract.Presenter provideMainPresenter(DriveModeMetricsHelper driveModeMetricsHelper, WeblabManager weblabManager) {
        return new DriveModeMainPresenter(driveModeMetricsHelper, weblabManager);
    }

    @Provides
    @Singleton
    public NowPlayingScreenContract.Presenter provideNowPlayingScreenPresenter(NowPlayingScreenContract.Interactor interactor) {
        return new NowPlayingScreenPresenter(interactor);
    }
}
