package com.amazon.alexa.voice.ui.onedesign.empty;

import android.text.TextUtils;
import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
/* loaded from: classes11.dex */
public final class EmptyPresenter implements EmptyContract.Presenter {
    private final Factory<EmptyResourceBundle, EmptiableCardType> bundleFactory;
    private final CardInteractionTracker interactionTracker = new CardInteractionTracker();
    private final EmptyContract.Interactor interactor;
    private String manageButtonDestination;
    private final CardMetricsInteractor metricsInteractor;
    private final Resources resources;
    private final EmptyContract.View view;

    public EmptyPresenter(EmptyContract.View view, EmptyContract.Interactor interactor, Resources resources, Factory<EmptyResourceBundle, EmptiableCardType> factory, CardMetricsInteractor cardMetricsInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.resources = resources;
        this.bundleFactory = factory;
        this.metricsInteractor = cardMetricsInteractor;
    }

    private String getCardName() {
        return this.interactor.getClass().getSimpleName();
    }

    private void renderCard() {
        EmptyCard card = this.interactor.getCard();
        EmptyResourceBundle create = this.bundleFactory.create(EmptiableCardType.from(card.getType()));
        this.view.floodBackgroundToStatusBar();
        this.view.setContent(this.resources.getText(create.getContentResourceId()));
        if (!TextUtils.isEmpty(card.getTitle())) {
            this.view.setTitle(card.getTitle());
        } else {
            this.view.setTitle(this.resources.getText(create.getTitleResourceId()));
        }
        this.view.setIconResourceId(create.getIconResourceId());
        this.view.setUserHint(this.resources.getText(create.getUserHintResourceId()));
        this.manageButtonDestination = card.getManageButtonDestination();
        if (this.manageButtonDestination != null) {
            this.view.showManageButton();
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.Presenter
    public void dismiss() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.Presenter
    public void manageButtonClicked() {
        this.interactor.navigateToManageDestination();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.Presenter
    public void start() {
        renderCard();
        this.metricsInteractor.reportCardShown(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.Presenter
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(getCardName(), this.interactionTracker.wasInteracted());
    }
}
