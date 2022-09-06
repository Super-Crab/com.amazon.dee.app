package com.amazon.alexa.voice.ui.onedesign.traffic;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.traffic.TrafficCardModel;
import com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.vox.ui.onedesign.R;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
/* loaded from: classes11.dex */
public final class TrafficPresenter implements TrafficContract.Presenter {
    private final TrafficContract.Interactor interactor;
    private final CardMetricsInteractor metricsInteractor;
    private final Resources resources;
    private final RouteFormatter routeFormatter;
    private final TimeFormatter timeFormatter;
    private final TrafficContract.View view;
    private final CardInteractionTracker interactionTracker = new CardInteractionTracker();
    private Disposable disposable = null;

    public TrafficPresenter(TrafficContract.View view, TrafficContract.Interactor interactor, TimeFormatter timeFormatter, RouteFormatter routeFormatter, Resources resources, CardMetricsInteractor cardMetricsInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.timeFormatter = timeFormatter;
        this.routeFormatter = routeFormatter;
        this.resources = resources;
        this.metricsInteractor = cardMetricsInteractor;
    }

    private String getCardName() {
        return this.interactor.getCard().getClass().getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @DrawableRes
    public int getIndicator(TrafficCardModel.RouteModel routeModel) {
        if (routeModel.getCondition() == 0) {
            return R.drawable.ic_voice_ui_od_traffic_indicator_green;
        }
        if (routeModel.getCondition() == 1) {
            return R.drawable.ic_voice_ui_od_traffic_indicator_orange;
        }
        return R.drawable.ic_voice_ui_od_traffic_indicator_red;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.Presenter
    public void dismiss() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.Presenter
    public void end() {
        Disposable disposable = this.disposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.disposable.dispose();
        this.disposable = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.Presenter
    public void start() {
        TrafficCardModel card = this.interactor.getCard();
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(this.resources.getText(R.string.voice_ui_od_traffic_title));
        Observable cast = Observable.fromIterable(card.getRouteList()).map(new Function<TrafficCardModel.RouteModel, TrafficRouteModel>() { // from class: com.amazon.alexa.voice.ui.onedesign.traffic.TrafficPresenter.1
            boolean primary = true;

            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public TrafficRouteModel mo10358apply(@NonNull TrafficCardModel.RouteModel routeModel) throws Exception {
                TrafficRoute trafficRoute = new TrafficRoute(TrafficPresenter.this.timeFormatter.format(routeModel.getTimeToDestination()), TrafficPresenter.this.routeFormatter.format(routeModel.getDistance(), routeModel.getStreetList()), this.primary, TrafficPresenter.this.getIndicator(routeModel));
                this.primary = false;
                return trafficRoute;
            }
        }).cast(Object.class);
        final TrafficContract.View view = this.view;
        view.getClass();
        this.disposable = cast.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.traffic.-$$Lambda$j5zWLjyE8FC80zSBGdqLfqvOCeU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                TrafficContract.View.this.add(obj);
            }
        });
        this.metricsInteractor.reportCardShown(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.Presenter
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(this.interactor.getCard().getClass().getSimpleName(), this.interactionTracker.wasInteracted());
    }
}
