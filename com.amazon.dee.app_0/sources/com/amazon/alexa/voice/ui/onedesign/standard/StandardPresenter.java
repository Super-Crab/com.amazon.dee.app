package com.amazon.alexa.voice.ui.onedesign.standard;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.standard.StandardContract;
import com.amazon.alexa.voice.ui.onedesign.util.AvsDomain;
import com.amazon.alexa.voice.ui.onedesign.util.AvsDomainTypes;
/* loaded from: classes11.dex */
public class StandardPresenter implements StandardContract.Presenter {
    private final CardInteractionTracker interactionTracker;
    private final StandardContract.Interactor interactor;
    private final CardMetricsInteractor metricsInteractor;
    private final StandardContract.View view;

    public StandardPresenter(StandardContract.View view, StandardContract.Interactor interactor, CardMetricsInteractor cardMetricsInteractor) {
        this(view, interactor, cardMetricsInteractor, new CardInteractionTracker());
    }

    private String getCardName() {
        return this.interactor.getCard().getClass().getSimpleName();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.Presenter
    public void dismiss() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.Presenter
    public void linkClicked() {
        this.interactor.openLink();
        this.metricsInteractor.reportNavigationToExternalLink(getCardName());
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.Presenter
    public void start() {
        StandardCardModel card = this.interactor.getCard();
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(card.getTitle());
        this.view.setSubTitle(card.getSubTitle());
        this.view.setImageUrl(card.getContentImageUrl());
        this.view.setContent(card.getContent());
        this.view.setImageAttribution(card.getContentImageAttribution());
        this.view.setLinkText(card.getLinkText());
        if (AvsDomain.containsAnyDomain(card.getDomains(), AvsDomainTypes.CALCULATION_QUESTION_AND_ANSWER)) {
            this.view.setVisibleLayout(0);
        } else {
            this.view.setVisibleLayout(1);
        }
        this.metricsInteractor.reportCardShown(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.Presenter
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(getCardName(), this.interactionTracker.wasInteracted());
    }

    @VisibleForTesting
    public StandardPresenter(StandardContract.View view, StandardContract.Interactor interactor, CardMetricsInteractor cardMetricsInteractor, CardInteractionTracker cardInteractionTracker) {
        this.view = view;
        this.interactor = interactor;
        this.metricsInteractor = cardMetricsInteractor;
        this.interactionTracker = cardInteractionTracker;
    }
}
