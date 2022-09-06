package com.amazon.alexa.voice.tta.typing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.tta.RouterDelegate;
import com.amazon.alexa.voice.tta.ViewDismissedListener;
import com.amazon.alexa.voice.ui.onedesign.transitions.DismissContentTransition;
import com.amazon.alexa.voice.ui.onedesign.transitions.PushContentTransition;
import com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenController;
import com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenParameters;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class TypingView {
    private static final String TAG = "TypingView";
    @VisibleForTesting
    static final String TYPING_CONTROLLER = "typing";
    private final Handler mainHandler;
    private TtaScreenParameters ttaScreenParameters;
    private final RouterDelegate typingRouter;
    private ViewDismissedListener viewDismissedListener;

    @Inject
    public TypingView(@Named("typingRouterDelegate") RouterDelegate routerDelegate) {
        this(routerDelegate, new Handler(Looper.getMainLooper()));
    }

    public boolean backButtonPressed() {
        return this.typingRouter.handleBack();
    }

    public void cleanup() {
        this.typingRouter.destroy();
    }

    public boolean isVisible() {
        return this.typingRouter.hasRootController();
    }

    public /* synthetic */ void lambda$showTypingView$0$TypingView() {
        TtaScreenParameters ttaScreenParameters = this.ttaScreenParameters;
        this.typingRouter.pushController(new ControllerTransaction(ttaScreenParameters != null ? TtaScreenController.create(ttaScreenParameters) : TtaScreenController.create(), new DismissContentTransition(), new PushContentTransition(), TYPING_CONTROLLER));
    }

    public void prepareForConfigurationChange() {
        this.typingRouter.detach();
    }

    public void registerViewDismissedListener(ViewDismissedListener viewDismissedListener) {
        this.viewDismissedListener = viewDismissedListener;
    }

    public void saveInstanceState(Bundle bundle) {
        this.typingRouter.saveInstanceState(bundle);
    }

    public void setScreenParameters(TtaScreenParameters ttaScreenParameters) {
        this.ttaScreenParameters = ttaScreenParameters;
    }

    public void showTypingView() {
        if (this.typingRouter.getControllerWithTag(TYPING_CONTROLLER) == null && this.typingRouter.isAttached()) {
            this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.voice.tta.typing.-$$Lambda$TypingView$SUVpDGRVRcFVkETQP3JyVsXbMd4
                @Override // java.lang.Runnable
                public final void run() {
                    TypingView.this.lambda$showTypingView$0$TypingView();
                }
            });
        }
    }

    @VisibleForTesting
    TypingView(RouterDelegate routerDelegate, Handler handler) {
        this.typingRouter = routerDelegate;
        this.mainHandler = handler;
        routerDelegate.addOnPopTransactionListener(new Router.OnTransactionAdapter() { // from class: com.amazon.alexa.voice.tta.typing.TypingView.1
            @Override // com.amazon.regulator.Router.OnTransactionAdapter, com.amazon.regulator.Router.OnTransactionListener
            public void onAfterTransition(ControllerTransaction controllerTransaction) {
                if (TypingView.this.viewDismissedListener != null) {
                    TypingView.this.viewDismissedListener.onViewDismissed();
                }
            }
        });
    }
}
