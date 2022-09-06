package com.amazon.alexa.voice.ui.onedesign.list;

import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.list.ListContract;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes11.dex */
public final class ListPresenter implements ListContract.Presenter {
    private final ListContract.Interactor interactor;
    private final CardMetricsInteractor metricsInteractor;
    private final Resources resources;
    private final ListContract.View view;
    private final CardInteractionTracker interactionTracker = new CardInteractionTracker();
    private Disposable disposable = null;

    /* renamed from: com.amazon.alexa.voice.ui.onedesign.list.ListPresenter$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$voice$ui$onedesign$list$ListType = new int[ListType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$list$ListType[ListType.TODO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$list$ListType[ListType.SHOPPING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$list$ListType[ListType.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public ListPresenter(ListContract.View view, ListContract.Interactor interactor, Resources resources, CardMetricsInteractor cardMetricsInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.resources = resources;
        this.metricsInteractor = cardMetricsInteractor;
    }

    private String getCardName() {
        return this.interactor.getCard().getClass().getSimpleName();
    }

    private CharSequence getTitleForListType(String str) {
        int ordinal = ListType.from(str).ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return this.resources.getText(R.string.voice_ui_od_list_todo_title);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Got unexpected list type: ", str));
        }
        return this.resources.getText(R.string.voice_ui_od_list_shopping_title);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Presenter
    public void dismiss() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Presenter
    public void end() {
        Disposable disposable = this.disposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.disposable.dispose();
        this.disposable = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Presenter
    public void manageButtonClicked() {
        this.interactor.openLists();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Presenter
    public void start() {
        ListCard card = this.interactor.getCard();
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(getTitleForListType(card.getListType()));
        this.view.setSubTitle(this.resources.getString(R.string.voice_ui_od_list_n_items, Integer.valueOf(card.getItemList().size())));
        Observable map = Observable.fromIterable(card.getItemList()).map($$Lambda$ListPresenter$7NhOpnsc313TF7NkNgBXSVOhoaU.INSTANCE);
        final ListContract.View view = this.view;
        view.getClass();
        this.disposable = map.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.list.-$$Lambda$L0Dyp3EVdAw6D0Hp2u67qfj4Dag
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ListContract.View.this.add((ListItem) obj);
            }
        });
        this.metricsInteractor.reportCardShown(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Presenter
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(getCardName(), this.interactionTracker.wasInteracted());
    }
}
