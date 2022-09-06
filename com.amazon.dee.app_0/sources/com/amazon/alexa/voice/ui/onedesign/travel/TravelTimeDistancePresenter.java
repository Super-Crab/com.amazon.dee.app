package com.amazon.alexa.voice.ui.onedesign.travel;

import android.content.res.Resources;
import androidx.annotation.DrawableRes;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract;
import com.amazon.alexa.vox.ui.onedesign.R;
import java.util.List;
/* loaded from: classes11.dex */
public final class TravelTimeDistancePresenter implements TravelTimeDistanceContract.Presenter {
    private final TravelTimeDistanceContract.Interactor interactor;
    private final CardMetricsInteractor metricsInteractor;
    private final Resources resources;
    private final TravelTimeDistanceFormatter travelTimeDistanceFormatter;
    private final TravelTimeDistanceContract.View view;

    public TravelTimeDistancePresenter(TravelTimeDistanceContract.View view, TravelTimeDistanceContract.Interactor interactor, TravelTimeDistanceFormatter travelTimeDistanceFormatter, CardMetricsInteractor cardMetricsInteractor, Resources resources) {
        this.view = view;
        this.interactor = interactor;
        this.travelTimeDistanceFormatter = travelTimeDistanceFormatter;
        this.metricsInteractor = cardMetricsInteractor;
        this.resources = resources;
    }

    private String getCardName() {
        return this.interactor.getCard().getClass().getSimpleName();
    }

    @DrawableRes
    private int getIndicator(int i) {
        if (i == 0) {
            return R.drawable.ic_voice_ui_od_traffic_indicator_green;
        }
        if (i == 1) {
            return R.drawable.ic_voice_ui_od_traffic_indicator_orange;
        }
        return R.drawable.ic_voice_ui_od_traffic_indicator_red;
    }

    private CharSequence getPrimaryLabel(TravelTimeDistanceModel travelTimeDistanceModel) {
        if (travelTimeDistanceModel.getTravelCardType() == 0) {
            return this.travelTimeDistanceFormatter.format(travelTimeDistanceModel.getTimeToDestination());
        }
        return this.travelTimeDistanceFormatter.format(travelTimeDistanceModel.getDistanceToDestination());
    }

    private CharSequence getSecondaryLabel(TravelTimeDistanceModel travelTimeDistanceModel) {
        if (travelTimeDistanceModel.getTravelCardType() == 0) {
            return travelTimeDistanceModel.getDistanceToDestination();
        }
        return travelTimeDistanceModel.getTimeToDestination();
    }

    private CharSequence getSubTitle(TravelTimeDistanceModel travelTimeDistanceModel) {
        if (travelTimeDistanceModel.getOrigin().length() != 0) {
            return travelTimeDistanceModel.getOrigin();
        }
        if (travelTimeDistanceModel.getTravelCardType() == 0) {
            return this.resources.getString(R.string.voice_ui_od_travel_time);
        }
        return this.resources.getString(R.string.voice_ui_od_travel_distance);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.Presenter
    public void dismiss() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.Presenter
    public void end() {
    }

    public CharSequence formatRoadSegment(List<? extends CharSequence> list) {
        int size = list.size();
        if (size == 0) {
            return "";
        }
        if (size == 1) {
            return this.resources.getQuantityString(R.plurals.voice_ui_od_travel_time_distance_route, 1, list.get(0));
        }
        return this.resources.getQuantityString(R.plurals.voice_ui_od_travel_time_distance_route, 2, list.get(0), list.get(1));
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.Presenter
    public void interacted() {
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.Presenter
    public void start() {
        TravelTimeDistanceModel card = this.interactor.getCard();
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(card.getDestination());
        this.view.setSubTitle(getSubTitle(card));
        this.view.setPrimaryLabel(getPrimaryLabel(card));
        this.view.setRoadSegment(formatRoadSegment(card.getRoadSegments()));
        this.view.setSecondaryLabel(getSecondaryLabel(card));
        this.view.setTrafficCondition(getIndicator(card.getCondition()));
        CardMetricsInteractor cardMetricsInteractor = this.metricsInteractor;
        cardMetricsInteractor.reportCardShown(getCardName() + "_" + card.getTravelCardType());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.Presenter
    public void viewDestroyed() {
    }
}
