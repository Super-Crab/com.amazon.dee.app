package com.amazon.alexa.voiceui.chrome;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.utils.ApiThreadHelper;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimController;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimParameters;
import com.amazon.alexa.voice.ui.onedesign.transitions.ScrimCloseTransition;
import com.amazon.alexa.voice.ui.onedesign.transitions.ScrimOpenTransition;
import com.amazon.alexa.voiceui.RouterDelegate;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class VoiceChromeView {
    private static final String TAG = "VoiceChromeView";
    private static final String VOICE_CHROME_CONTROLLER = "voiceChrome";
    private AlexaUserInterfaceOptions alexaUserInterfaceOptions;
    private volatile boolean hintsEnabled;
    private final List<VoiceChromeEventListener> listeners;
    private final Handler mainHandler;
    private volatile boolean typingIngressEnabled;
    private final RouterDelegate voiceChromeRouter;

    /* renamed from: com.amazon.alexa.voiceui.chrome.VoiceChromeView$4  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaUserInterfaceOptions$Theme = new int[AlexaUserInterfaceOptions.Theme.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaUserInterfaceOptions$Theme[AlexaUserInterfaceOptions.Theme.MINIMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaUserInterfaceOptions$Theme[AlexaUserInterfaceOptions.Theme.DEFAULT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Inject
    public VoiceChromeView(@Named("voiceChromeRouterDelegate") RouterDelegate routerDelegate) {
        this(routerDelegate, new Handler(Looper.getMainLooper()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ScrimController createScrimController() {
        return ScrimController.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void informVoiceChromeDismissed() {
        synchronized (this.listeners) {
            for (VoiceChromeEventListener voiceChromeEventListener : this.listeners) {
                voiceChromeEventListener.onVoiceChromeDismissed();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void informVoiceChromeShown() {
        synchronized (this.listeners) {
            for (VoiceChromeEventListener voiceChromeEventListener : this.listeners) {
                voiceChromeEventListener.onVoiceChromeShown();
            }
        }
    }

    public void addVoiceChromeEventListener(VoiceChromeEventListener voiceChromeEventListener) {
        synchronized (this.listeners) {
            this.listeners.add(voiceChromeEventListener);
        }
    }

    public void cleanup() {
        this.voiceChromeRouter.destroy();
    }

    public void hideVoiceChrome() {
        ApiThreadHelper.runOnDefaultHandler(this.mainHandler, new Runnable() { // from class: com.amazon.alexa.voiceui.chrome.VoiceChromeView.2
            @Override // java.lang.Runnable
            public void run() {
                VoiceChromeView.this.voiceChromeRouter.popControllerWithTag(VoiceChromeView.VOICE_CHROME_CONTROLLER);
            }
        });
    }

    public boolean isVisible() {
        return this.voiceChromeRouter.hasRootController();
    }

    public void prepareForConfigurationChange() {
        this.voiceChromeRouter.detach();
    }

    public void setAlexaUserInterfaceOptions(AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
        this.alexaUserInterfaceOptions = alexaUserInterfaceOptions;
    }

    public void setHintsEnabled(boolean z) {
        this.hintsEnabled = z;
    }

    public void setTypingIngressEnabled(boolean z) {
        this.typingIngressEnabled = z;
    }

    public void showVoiceChrome() {
        showVoiceChrome(this.alexaUserInterfaceOptions);
    }

    @VisibleForTesting
    VoiceChromeView(final RouterDelegate routerDelegate, Handler handler) {
        this.hintsEnabled = true;
        this.typingIngressEnabled = true;
        this.voiceChromeRouter = routerDelegate;
        this.mainHandler = handler;
        this.listeners = new ArrayList();
        routerDelegate.addOnPopTransactionListener(new Router.OnTransactionAdapter() { // from class: com.amazon.alexa.voiceui.chrome.VoiceChromeView.1
            @Override // com.amazon.regulator.Router.OnTransactionAdapter, com.amazon.regulator.Router.OnTransactionListener
            public void onAfterTransition(ControllerTransaction controllerTransaction) {
                if (!routerDelegate.hasRootController()) {
                    VoiceChromeView.this.informVoiceChromeDismissed();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ScrimController createScrimController(AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
        ScrimParameters.Builder builder = new ScrimParameters.Builder();
        boolean z = false;
        builder.showHint(this.hintsEnabled && alexaUserInterfaceOptions.getHintsEnabled());
        if (alexaUserInterfaceOptions.getHintText() != null) {
            builder.hint(alexaUserInterfaceOptions.getHintText());
        }
        if (alexaUserInterfaceOptions.getTheme().ordinal() != 1) {
            if (this.typingIngressEnabled && alexaUserInterfaceOptions.getTypingEnabled()) {
                z = true;
            }
            builder.showTTAIngress(z);
            String str = "createScrimController: typingIngressEnabled|" + this.typingIngressEnabled + ", tta|" + alexaUserInterfaceOptions.getTypingEnabled();
        } else {
            builder.hideCancelButton(true);
            builder.transparentBackground(true);
            builder.showTTAIngress(false);
        }
        return ScrimController.create(builder.build());
    }

    private void showVoiceChrome(@Nullable final AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
        if (this.voiceChromeRouter.getControllerWithTag(VOICE_CHROME_CONTROLLER) == null && this.voiceChromeRouter.isAttached()) {
            Log.i(TAG, "Showing voice chrome");
            ApiThreadHelper.runOnDefaultHandler(this.mainHandler, new Runnable() { // from class: com.amazon.alexa.voiceui.chrome.VoiceChromeView.3
                @Override // java.lang.Runnable
                public void run() {
                    RouterDelegate routerDelegate = VoiceChromeView.this.voiceChromeRouter;
                    AlexaUserInterfaceOptions alexaUserInterfaceOptions2 = alexaUserInterfaceOptions;
                    routerDelegate.pushController(new ControllerTransaction(alexaUserInterfaceOptions2 == null ? VoiceChromeView.this.createScrimController() : VoiceChromeView.this.createScrimController(alexaUserInterfaceOptions2), new ScrimCloseTransition(), new ScrimOpenTransition(), VoiceChromeView.VOICE_CHROME_CONTROLLER));
                    VoiceChromeView.this.informVoiceChromeShown();
                }
            });
        }
    }
}
