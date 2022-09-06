package com.amazon.alexa.voiceui.cards;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.DriveModeState;
import com.amazon.alexa.utils.ApiThreadHelper;
import com.amazon.alexa.voice.ui.onedesign.transitions.DismissContentTransition;
import com.amazon.alexa.voice.ui.onedesign.transitions.PushContentTransition;
import com.amazon.alexa.voice.ui.provider.CardFactoryWithMode;
import com.amazon.alexa.voice.ui.provider.CardMode;
import com.amazon.alexa.voice.ui.window.WindowInteractor;
import com.amazon.alexa.voiceui.RouterDelegate;
import com.amazon.alexa.voiceui.driveMode.DriveModeListener;
import com.amazon.alexa.voiceui.driveMode.DriveModeModel;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class CardView {
    private static final String TAG = "CardView";
    private final List<CardDismissedListener> cardDismissedListeners;
    private final CardFactoryWithMode cardFactory;
    private final List<CardLatencyListener> cardLatencyListeners;
    private final PublishSubject<Boolean> cardRenderObservable;
    private final RouterDelegate cardRouter;
    private final CardViewDriveModeListener driveModeListener;
    private final DriveModeModel driveModeModel;
    private volatile DriveModeState driveModeState;
    private final AtomicBoolean hideCardSilently;
    private volatile boolean isDriveModeDarkTheme;
    private volatile boolean isDriveModeEnabled;
    private final Handler mainHandler;
    private final WindowInteractor windowInteractor;

    /* loaded from: classes11.dex */
    public interface CardDismissedListener {
        void onCardDismissed();
    }

    /* loaded from: classes11.dex */
    public interface CardLatencyListener {
        void onCardCreationCompleted(boolean z, CardMode cardMode);

        void onCardCreationFailed(String str, CardMode cardMode);

        void onCardCreationInitiated();

        void onCardViewInflationInitiated();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class CardViewDriveModeListener implements DriveModeListener {
        private CardViewDriveModeListener() {
        }

        @Override // com.amazon.alexa.voiceui.driveMode.DriveModeListener
        public void onAutoModeEnabled(boolean z) {
            CardView.this.isDriveModeEnabled = z;
        }

        @Override // com.amazon.alexa.voiceui.driveMode.DriveModeListener
        public void onAutoModeState(DriveModeState driveModeState) {
            CardView.this.driveModeState = driveModeState;
        }

        @Override // com.amazon.alexa.voiceui.driveMode.DriveModeListener
        public void onThemeChanged(boolean z) {
            CardView.this.isDriveModeDarkTheme = z;
        }
    }

    @Inject
    public CardView(@Named("cards") RouterDelegate routerDelegate, CardFactoryWithMode cardFactoryWithMode, DriveModeModel driveModeModel, WindowInteractor windowInteractor) {
        this(routerDelegate, cardFactoryWithMode, driveModeModel, windowInteractor, new Handler(Looper.getMainLooper()));
    }

    private CardMode getCardMode() {
        return this.isDriveModeEnabled ? CardMode.DRIVE_MODE : CardMode.STANDARD;
    }

    private int getCardTheme() {
        if (this.isDriveModeEnabled) {
            return !this.isDriveModeDarkTheme ? 1 : 0;
        }
        return -1;
    }

    private void sendCardCreationCompleted(boolean z) {
        synchronized (this.cardLatencyListeners) {
            for (CardLatencyListener cardLatencyListener : this.cardLatencyListeners) {
                cardLatencyListener.onCardCreationCompleted(z, getCardMode());
            }
        }
    }

    private void sendCardCreationFailed(String str) {
        synchronized (this.cardLatencyListeners) {
            for (CardLatencyListener cardLatencyListener : this.cardLatencyListeners) {
                cardLatencyListener.onCardCreationFailed(str, getCardMode());
            }
        }
    }

    private void sendCardCreationInitiated() {
        synchronized (this.cardLatencyListeners) {
            for (CardLatencyListener cardLatencyListener : this.cardLatencyListeners) {
                cardLatencyListener.onCardCreationInitiated();
            }
        }
    }

    private void sendCardViewInflationInitiated() {
        synchronized (this.cardLatencyListeners) {
            for (CardLatencyListener cardLatencyListener : this.cardLatencyListeners) {
                cardLatencyListener.onCardViewInflationInitiated();
            }
        }
    }

    public void addCardDismissedListener(CardDismissedListener cardDismissedListener) {
        synchronized (this.cardDismissedListeners) {
            this.cardDismissedListeners.add(cardDismissedListener);
        }
    }

    public void addCardLatencyListener(CardLatencyListener cardLatencyListener) {
        synchronized (this.cardLatencyListeners) {
            this.cardLatencyListeners.add(cardLatencyListener);
        }
    }

    public void cleanup() {
        this.cardRouter.destroy();
        this.driveModeModel.release();
        this.driveModeModel.removeDriveModeListener(this.driveModeListener);
    }

    public void hideCardSilently() {
        if (this.cardRouter.hasRootController()) {
            this.hideCardSilently.set(true);
            this.windowInteractor.restoreDefaultStatusBarColor();
            this.cardRouter.popRootController(new InstantTransition());
        }
    }

    public boolean isVisible() {
        return this.cardRouter.hasRootController();
    }

    public Observable<Boolean> onCardRenderObservable() {
        return this.cardRenderObservable;
    }

    public void prepareForConfigurationChange() {
        this.cardRouter.detach();
    }

    public void setDriveModeEnabled(boolean z) {
        this.isDriveModeEnabled = z;
    }

    public void setDriveModeState(DriveModeState driveModeState) {
        this.driveModeState = driveModeState;
    }

    public void showCard(String str) {
        sendCardCreationInitiated();
        final ViewController buildCard = this.cardFactory.buildCard(str, getCardMode(), getCardTheme());
        if (buildCard != null) {
            ApiThreadHelper.runOnDefaultHandler(this.mainHandler, new Runnable() { // from class: com.amazon.alexa.voiceui.cards.CardView.2
                @Override // java.lang.Runnable
                public void run() {
                    CardView.this.cardRenderObservable.onNext(Boolean.valueOf(CardView.this.showCard(buildCard)));
                }
            });
            return;
        }
        sendCardCreationFailed("null ViewController");
        this.cardRenderObservable.onNext(false);
    }

    @VisibleForTesting
    CardView(RouterDelegate routerDelegate, CardFactoryWithMode cardFactoryWithMode, DriveModeModel driveModeModel, WindowInteractor windowInteractor, Handler handler) {
        this.cardRenderObservable = PublishSubject.create();
        this.cardRouter = routerDelegate;
        this.cardFactory = cardFactoryWithMode;
        this.driveModeModel = driveModeModel;
        this.windowInteractor = windowInteractor;
        this.mainHandler = handler;
        this.cardDismissedListeners = new ArrayList();
        this.cardLatencyListeners = new ArrayList();
        this.hideCardSilently = new AtomicBoolean(false);
        this.isDriveModeDarkTheme = true;
        routerDelegate.addOnPopTransactionListener(new Router.OnTransactionAdapter() { // from class: com.amazon.alexa.voiceui.cards.CardView.1
            @Override // com.amazon.regulator.Router.OnTransactionAdapter, com.amazon.regulator.Router.OnTransactionListener
            public void onAfterTransition(ControllerTransaction controllerTransaction) {
                if (!CardView.this.hideCardSilently.compareAndSet(true, false) && !CardView.this.isVisible()) {
                    synchronized (CardView.this.cardDismissedListeners) {
                        for (CardDismissedListener cardDismissedListener : CardView.this.cardDismissedListeners) {
                            cardDismissedListener.onCardDismissed();
                        }
                    }
                }
            }
        });
        this.driveModeListener = new CardViewDriveModeListener();
        driveModeModel.initialize();
        driveModeModel.addDriveModeListener(this.driveModeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @MainThread
    public boolean showCard(ViewController viewController) {
        sendCardViewInflationInitiated();
        boolean z = false;
        try {
            if (this.cardRouter.isAttached()) {
                try {
                    this.cardRouter.replaceCurrentController(new ControllerTransaction(viewController, new DismissContentTransition(), new PushContentTransition()));
                    sendCardCreationCompleted(true);
                    z = true;
                } catch (Exception e) {
                    Log.e(TAG, "Caught exception displaying card", e);
                    sendCardCreationFailed(e.getMessage());
                    sendCardCreationCompleted(false);
                }
            } else {
                sendCardCreationFailed("Card router is not attached");
            }
            return z;
        } catch (Throwable th) {
            sendCardCreationCompleted(z);
            throw th;
        }
    }
}
