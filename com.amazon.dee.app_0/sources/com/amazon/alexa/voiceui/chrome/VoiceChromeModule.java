package com.amazon.alexa.voiceui.chrome;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import com.amazon.alexa.voice.ui.speech.AttentionSystemContract;
import com.amazon.alexa.voice.ui.tta.TypingPrimerDisplayer;
import com.amazon.alexa.voiceui.BackButtonPressHandler;
import com.amazon.alexa.voiceui.DefaultBackButtonPressHandler;
import com.amazon.alexa.voiceui.DefaultSaveInstanceStateHandler;
import com.amazon.alexa.voiceui.R;
import com.amazon.alexa.voiceui.RouterDelegate;
import com.amazon.alexa.voiceui.SaveInstanceStateHandler;
import com.amazon.regulator.Component;
import com.amazon.regulator.Router;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public class VoiceChromeModule {
    static final String VOICE_CHROME_BACK_PRESS_HANDLER = "voiceChromeBackPressHandler";
    private static final String VOICE_CHROME_CONTAINER = "voiceChromeContainer";
    public static final String VOICE_CHROME_ROUTER = "voiceChromeRouter";
    static final String VOICE_CHROME_ROUTER_DELEGATE = "voiceChromeRouterDelegate";
    static final String VOICE_CHROME_SAVE_INSTANCE_STATE_HANDLER = "voiceChromeSaveInstanceState";
    private final Activity activity;
    private final Bundle savedInstanceState;

    public VoiceChromeModule(Activity activity, Bundle bundle) {
        this.activity = activity;
        this.savedInstanceState = bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AttentionSystemContract providesAttentionSystemContract(VoiceChromeModel voiceChromeModel) {
        return voiceChromeModel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(VOICE_CHROME_BACK_PRESS_HANDLER)
    public BackButtonPressHandler providesBackButtonPressHandler(@Named("voiceChromeRouterDelegate") RouterDelegate routerDelegate) {
        return new DefaultBackButtonPressHandler(routerDelegate);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(VOICE_CHROME_SAVE_INSTANCE_STATE_HANDLER)
    public SaveInstanceStateHandler providesSaveInstanceStateHandler(@Named("voiceChromeRouterDelegate") RouterDelegate routerDelegate) {
        return new DefaultSaveInstanceStateHandler(routerDelegate);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public TypingPrimerDisplayer providesTypingPrimerDisplayer() {
        return new DefaultTypingPrimerDisplayer(this.activity);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(VOICE_CHROME_CONTAINER)
    public ViewGroup providesVoiceChromeContainer() {
        return (ViewGroup) this.activity.findViewById(R.id.voice_chrome_container);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(VOICE_CHROME_ROUTER)
    public Router providesVoiceChromeRouter(@Named("voiceChromeContainer") ViewGroup viewGroup, Component component) {
        Activity activity = this.activity;
        Bundle bundle = this.savedInstanceState;
        Router router = new Router(activity, component, bundle != null ? (Bundle) bundle.getParcelable(VOICE_CHROME_ROUTER) : null);
        router.attach(viewGroup);
        return router;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    @Named(VOICE_CHROME_ROUTER_DELEGATE)
    public RouterDelegate providesVoiceChromeRouterDelegate(@Named("voiceChromeRouter") Router router) {
        return new RouterDelegate(router);
    }
}
