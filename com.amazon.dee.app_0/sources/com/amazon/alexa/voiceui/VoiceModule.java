package com.amazon.alexa.voiceui;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.PowerManager;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.alexa.voice.ui.DefaultWindowInteractor;
import com.amazon.alexa.voice.ui.driveMode.metrics.DriveModeCardMetricsInteractor;
import com.amazon.alexa.voice.ui.locale.GetLocaleAuthority;
import com.amazon.alexa.voice.ui.marketplace.MarketplaceAuthority;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsChecker;
import com.amazon.alexa.voice.ui.permissions.AndroidPermissionsRequester;
import com.amazon.alexa.voice.ui.routing.Navigator;
import com.amazon.alexa.voice.ui.speech.AttentionSystemContract;
import com.amazon.alexa.voice.ui.speech.SpeechRecognizer;
import com.amazon.alexa.voice.ui.tta.TypingPrimerDisplayer;
import com.amazon.alexa.voice.ui.window.WindowInteractor;
import com.amazon.alexa.voiceui.cards.VoiceNavigator;
import com.amazon.alexa.voiceui.driveMode.DriveModeStateProvider;
import com.amazon.alexa.voiceui.screen.DefaultScreenLockManager;
import com.amazon.alexa.voiceui.screen.ScreenLockManager;
import com.amazon.alexa.voiceui.window.DefaultWindowManager;
import com.amazon.alexa.voiceui.window.WindowManager;
import com.amazon.regulator.Component;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public class VoiceModule {
    private final Activity activity;

    public VoiceModule(Activity activity) {
        this.activity = activity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Activity providesActivity() {
        return this.activity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public ExecutorService providesBackgroundThread() {
        return ExecutorFactory.newSingleThreadExecutor("voiceui-background-thread");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Component providesComponent(SpeechRecognizer speechRecognizer, GetLocaleAuthority getLocaleAuthority, MarketplaceAuthority marketplaceAuthority, CardMetricsInteractor cardMetricsInteractor, DriveModeCardMetricsInteractor driveModeCardMetricsInteractor, WindowInteractor windowInteractor, AttentionSystemContract attentionSystemContract, AndroidPermissionsRequester androidPermissionsRequester, AndroidPermissionsChecker androidPermissionsChecker, Navigator navigator, TypingPrimerDisplayer typingPrimerDisplayer) {
        Component component = new Component();
        component.provide((Class<? extends Class>) SpeechRecognizer.class, (Class) speechRecognizer).register();
        component.provide((Class<? extends Class>) GetLocaleAuthority.class, (Class) getLocaleAuthority).register();
        component.provide((Class<? extends Class>) MarketplaceAuthority.class, (Class) marketplaceAuthority).register();
        component.provide((Class<? extends Class>) CardMetricsInteractor.class, (Class) cardMetricsInteractor).register();
        component.provide((Class<? extends Class>) DriveModeCardMetricsInteractor.class, (Class) driveModeCardMetricsInteractor).register();
        component.provide((Class<? extends Class>) WindowInteractor.class, (Class) windowInteractor).register();
        component.provide((Class<? extends Class>) AndroidPermissionsRequester.class, (Class) androidPermissionsRequester).register();
        component.provide((Class<? extends Class>) AndroidPermissionsChecker.class, (Class) androidPermissionsChecker).register();
        component.provide((Class<? extends Class>) AttentionSystemContract.class, (Class) attentionSystemContract).register();
        component.provide((Class<? extends Class>) Navigator.class, (Class) navigator).register();
        component.provide((Class<? extends Class>) TypingPrimerDisplayer.class, (Class) typingPrimerDisplayer).register();
        return component;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Context providesContext() {
        return this.activity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public DriveModeStateProvider providesDriveModeStateProvider() {
        return new DriveModeStateProvider();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public KeyguardManager providesKeyguardManager(Context context) {
        return (KeyguardManager) context.getSystemService("keyguard");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public Navigator providesNavigator(VoiceNavigator voiceNavigator) {
        return voiceNavigator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public PackageManager providesPackageManager(Context context) {
        return context.getPackageManager();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AndroidPermissionsChecker providesPermissionsChecker(DefaultAndroidPermissionsHandler defaultAndroidPermissionsHandler) {
        return defaultAndroidPermissionsHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public AndroidPermissionsRequester providesPermissionsRequester(DefaultAndroidPermissionsHandler defaultAndroidPermissionsHandler) {
        return defaultAndroidPermissionsHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public PowerManager providesPowerManager(Context context) {
        return (PowerManager) context.getSystemService("power");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public ScreenLockManager providesScreenLockManager(PowerManager powerManager, AttentionSystemContract attentionSystemContract, DriveModeStateProvider driveModeStateProvider) {
        return new DefaultScreenLockManager(this.activity.getWindow(), new Handler(), this.activity, powerManager, attentionSystemContract, driveModeStateProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public SpeechRecognizer providesSpeechRecognizer(DefaultSpeechRecognizer defaultSpeechRecognizer) {
        return defaultSpeechRecognizer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public WindowManager providesWindowController() {
        return new DefaultWindowManager(this.activity.getWindow());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public WindowInteractor providesWindowInteractor() {
        return new DefaultWindowInteractor(this.activity.getWindow());
    }
}
