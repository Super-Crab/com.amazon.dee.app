package com.amazon.alexa.voiceui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voiceui.cards.CardModule;
import com.amazon.alexa.voiceui.cards.CardPresenter;
import com.amazon.alexa.voiceui.chrome.VoiceChromeModule;
import com.amazon.alexa.voiceui.chrome.VoiceChromePresenter;
import com.amazon.alexa.voiceui.notifications.SmartLockNotificationPresenter;
import com.amazon.alexa.voiceui.screen.ScreenUtils;
import com.amazon.alexa.voiceui.voice.VoicePresenter;
import com.amazon.alexa.voiceui.window.WindowUtils;
import dagger.Component;
import javax.inject.Inject;
import javax.inject.Singleton;
/* loaded from: classes11.dex */
public class AlexaUIActivity extends Activity {
    private static final String EXTRA_VOICE_ALLOW_LANDSCAPE = "show_vox_ui_landscape";
    private static final String TAG = AlexaUIActivity.class.getSimpleName();
    @Inject
    VoicePresenter voicePresenter;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Component(modules = {VoiceModule.class, CardModule.class, VoiceChromeModule.class, AlexaServicesModule.class})
    @Singleton
    /* loaded from: classes11.dex */
    public interface VoiceComponent {
        CardPresenter cardPresenter();

        void inject(AlexaUIActivity alexaUIActivity);

        SmartLockNotificationPresenter smartLockNotificationPresenter();

        VoiceChromePresenter voiceChromePresenter();
    }

    private void preventActivityAnimation() {
        overridePendingTransition(0, 0);
    }

    private void setOrientation() {
        if (getIntent().getBooleanExtra("show_vox_ui_landscape", false) || !ScreenUtils.isPhoneFormFactor(this) || Build.VERSION.SDK_INT == 26) {
            return;
        }
        setRequestedOrientation(1);
    }

    @VisibleForTesting
    void injectDependencies(@Nullable Bundle bundle) {
        VoiceComponent build = DaggerAlexaUIActivity_VoiceComponent.builder().voiceModule(new VoiceModule(this)).cardModule(new CardModule(this, bundle)).voiceChromeModule(new VoiceChromeModule(this, bundle)).alexaServicesModule(new AlexaServicesModule()).build();
        build.cardPresenter();
        build.voiceChromePresenter();
        build.smartLockNotificationPresenter();
        build.inject(this);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.voicePresenter.attachToWindow();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (!this.voicePresenter.backButtonPressed()) {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        preventActivityAnimation();
        setOrientation();
        WindowUtils.setTouchEventPassThrough(getWindow(), true);
        setContentView(R.layout.voice);
        getWindow().getDecorView().setSystemUiVisibility(1280);
        injectDependencies(bundle);
        this.voicePresenter.initialize(getIntent(), bundle);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.voicePresenter.destroy();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.voicePresenter.reinitialize(intent);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        preventActivityAnimation();
        this.voicePresenter.pause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.voicePresenter.resume();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.voicePresenter.saveInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.voicePresenter.start();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        preventActivityAnimation();
        this.voicePresenter.stop();
    }
}
