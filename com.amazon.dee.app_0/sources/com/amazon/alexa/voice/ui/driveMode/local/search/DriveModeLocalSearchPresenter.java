package com.amazon.alexa.voice.ui.driveMode.local.search;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract;
import com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor;
import com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeVoxUiMetricsConstants;
import com.amazon.alexa.voice.ui.driveMode.space.EmptySpace;
import com.amazon.alexa.voice.ui.driveMode.util.DriverDistractionLog;
import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCard;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchSortKey;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.vox.ui.onedesign.R;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes11.dex */
public final class DriveModeLocalSearchPresenter implements DriveModeLocalSearchContract.Presenter {
    private Disposable disposable;
    private final DriveModeCardMetricsInteractor driveModeCardMetricsInteractor;
    private final DriverDistractionLog driverDistractionLog;
    private final CardInteractionTracker interactionTracker;
    private final DriveModeLocalSearchContract.Interactor interactor;
    private final LocalSearchPresentationHelper localSearchPresentationHelper;
    private final DriveModeLocalSearchContract.View view;

    /* renamed from: com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchPresenter$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$voice$ui$onedesign$local$search$LocalSearchSortKey = new int[LocalSearchSortKey.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$local$search$LocalSearchSortKey[LocalSearchSortKey.RELEVANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    static class LocalSearchPresentationHelper {
        private final Resources resources;

        LocalSearchPresentationHelper(Resources resources) {
            this.resources = resources;
        }

        CharSequence makeSubTitle(String str) {
            if (LocalSearchSortKey.from(str).ordinal() != 2) {
                return this.resources.getText(R.string.voice_ui_od_local_nearby);
            }
            return this.resources.getText(R.string.voice_ui_od_local_top_rated);
        }
    }

    public DriveModeLocalSearchPresenter(DriveModeLocalSearchContract.View view, DriveModeLocalSearchContract.Interactor interactor, Resources resources, DriveModeCardMetricsInteractor driveModeCardMetricsInteractor, DriverDistractionLog driverDistractionLog) {
        this(view, interactor, driveModeCardMetricsInteractor, driverDistractionLog, new CardInteractionTracker(), new LocalSearchPresentationHelper(resources));
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract.Presenter
    public void end() {
        Disposable disposable = this.disposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.disposable.dispose();
        this.disposable = null;
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract.Presenter
    public void itemClicked(LocalSearchItemModel localSearchItemModel) {
        this.interactor.showLocation((LocalCardModel.BusinessModel) localSearchItemModel.getTag());
        this.driveModeCardMetricsInteractor.reportNavigationToExternalLink(DriveModeVoxUiMetricsConstants.METRIC_VOICECARDS_POIDETAILSADDRESS);
        this.driverDistractionLog.logTouch("VoiceCards_POIList_Selected");
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract.Presenter
    public void start() {
        LocalCard card = this.interactor.getCard();
        this.view.setTitle(card.getTitle());
        this.view.setSubTitle(this.localSearchPresentationHelper.makeSubTitle(card.getSortKey()));
        Observable startWithItem = Observable.fromIterable(card.getBusinessList()).map($$Lambda$DriveModeLocalSearchPresenter$JtWN5dp28erOZgYwIoeU8oVRiw.INSTANCE).cast(Object.class).startWithItem(new EmptySpace());
        final DriveModeLocalSearchContract.View view = this.view;
        view.getClass();
        this.disposable = startWithItem.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.driveMode.local.search.-$$Lambda$3-cLulAu_BnxeZADnPDgOETubM4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DriveModeLocalSearchContract.View.this.add(obj);
            }
        });
        this.driveModeCardMetricsInteractor.reportCardShown(DriveModeVoxUiMetricsConstants.METRIC_VOICECARDS_POILIST);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract.Presenter
    public void viewDestroyed() {
        this.driveModeCardMetricsInteractor.reportCardInteracted(this.interactor.getCard().getClass().getSimpleName(), this.interactionTracker.wasInteracted());
    }

    @VisibleForTesting
    public DriveModeLocalSearchPresenter(DriveModeLocalSearchContract.View view, DriveModeLocalSearchContract.Interactor interactor, DriveModeCardMetricsInteractor driveModeCardMetricsInteractor, DriverDistractionLog driverDistractionLog, CardInteractionTracker cardInteractionTracker, LocalSearchPresentationHelper localSearchPresentationHelper) {
        this.view = view;
        this.interactor = interactor;
        this.driveModeCardMetricsInteractor = driveModeCardMetricsInteractor;
        this.driverDistractionLog = driverDistractionLog;
        this.interactionTracker = cardInteractionTracker;
        this.localSearchPresentationHelper = localSearchPresentationHelper;
        this.disposable = null;
    }
}
