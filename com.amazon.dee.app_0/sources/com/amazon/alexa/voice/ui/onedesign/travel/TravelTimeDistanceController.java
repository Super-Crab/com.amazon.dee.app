package com.amazon.alexa.voice.ui.onedesign.travel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.HeaderUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public class TravelTimeDistanceController extends ViewController implements TravelTimeDistanceContract.View {
    private static final String EXTRA_CARD = "card";
    private TravelTimeDistanceContract.Presenter presenter;
    private TextView primaryLabelView;
    private TextView roadSegmentView;
    private TextView secondaryLabelView;
    private TextView subTitleView;
    private TextView titleView;
    private ImageView trafficIndicatorView;

    public static TravelTimeDistanceController create(TravelTimeDistanceModel travelTimeDistanceModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", travelTimeDistanceModel);
        TravelTimeDistanceController travelTimeDistanceController = new TravelTimeDistanceController();
        travelTimeDistanceController.setArguments(bundle);
        return travelTimeDistanceController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.View
    public void add(Object obj) {
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreateView$0$TravelTimeDistanceController(View view) {
        this.presenter.closeClicked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(View view) {
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        super.onCreate();
        overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Dark);
        Bundle arguments = getArguments();
        TravelTimeDistanceModel travelTimeDistanceModel = arguments != null ? (TravelTimeDistanceModel) arguments.getParcelable("card") : null;
        if (travelTimeDistanceModel != null) {
            this.presenter = new TravelTimeDistancePresenter(this, new TravelTimeDistanceInteractor(travelTimeDistanceModel, new TravelTimeDistanceMediator(this)), new TravelTimeDistanceFormatter(getContext()), (CardMetricsInteractor) getComponent().get(CardMetricsInteractor.class), getContext().getResources());
            return;
        }
        throw new IllegalStateException("Use TravelTimeDistanceController.create(TravelTimeDistanceCard) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    protected View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_travel_time_distance, viewGroup, false);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate.findViewById(R.id.cardLayout));
        inflate.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.travel.-$$Lambda$TravelTimeDistanceController$4yxLbH91rRear7Ie2OvI5XNVEUY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TravelTimeDistanceController.this.lambda$onCreateView$0$TravelTimeDistanceController(view);
            }
        });
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        this.subTitleView = (TextView) inflate.findViewById(R.id.subTitle);
        this.primaryLabelView = (TextView) inflate.findViewById(R.id.primaryLabel);
        this.roadSegmentView = (TextView) inflate.findViewById(R.id.roadSegments);
        this.secondaryLabelView = (TextView) inflate.findViewById(R.id.secondaryLabel);
        this.trafficIndicatorView = (ImageView) inflate.findViewById(R.id.indicator);
        HeaderUtils.applyFontStyles(this.titleView);
        FontUtils.apply(Font.AMAZON_EMBER_DISPLAY_BOLD, this.primaryLabelView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.roadSegmentView);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.secondaryLabelView);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(View view) {
        this.presenter.viewDestroyed();
        this.titleView = null;
        this.subTitleView = null;
        this.primaryLabelView = null;
        this.roadSegmentView = null;
        this.secondaryLabelView = null;
        this.trafficIndicatorView = null;
    }

    @Override // com.amazon.regulator.ViewController
    public void onDetach(View view) {
        this.presenter.end();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.View
    public void setPrimaryLabel(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.primaryLabelView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.View
    public void setRoadSegment(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.roadSegmentView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.View
    public void setSecondaryLabel(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.secondaryLabelView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.View
    public void setSubTitle(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.subTitleView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.View
    public void setTitle(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.titleView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.View
    public void setTrafficCondition(int i) {
        this.trafficIndicatorView.setImageResource(i);
    }
}
