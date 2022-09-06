package com.amazon.alexa.voice.tta;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.voice.tta.dependencies.DependenciesModule;
import com.amazon.alexa.voice.tta.features.FeaturesModule;
import com.amazon.alexa.voice.tta.metrics.MetricsModule;
import com.amazon.alexa.voice.tta.permissions.PermissionsModule;
import com.amazon.alexa.voice.tta.sdk.SdkModule;
import com.amazon.alexa.voice.tta.search.SearchModule;
import com.amazon.alexa.voice.tta.simba.SimbaModule;
import com.amazon.alexa.voice.tta.statemachine.StateMachineModule;
import com.amazon.alexa.voice.tta.suggestions.SuggestionsModule;
import com.amazon.alexa.voice.tta.typing.TypingModule;
import com.amazon.alexa.voice.tta.typing.TypingPresenter;
import dagger.Component;
import javax.inject.Inject;
import javax.inject.Singleton;
/* loaded from: classes11.dex */
public class TtaActivity extends AppCompatActivity {
    @Inject
    TypingPresenter typingPresenter;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Component(modules = {TtaModule.class, TypingModule.class, SearchModule.class, SuggestionsModule.class, MetricsModule.class, FeaturesModule.class, StateMachineModule.class, SdkModule.class, SimbaModule.class, DependenciesModule.class, PermissionsModule.class})
    @Singleton
    /* loaded from: classes11.dex */
    public interface TtaComponent {
        void inject(TtaActivity ttaActivity);
    }

    private void clearSecureWindowFlags() {
        Window window = getWindow();
        if (window != null) {
            window.clearFlags(8192);
        }
    }

    private void preventActivityAnimation() {
        overridePendingTransition(0, 0);
    }

    private void setSecureWindowFlags() {
        Window window = getWindow();
        if (window != null) {
            window.addFlags(8192);
        }
    }

    @VisibleForTesting
    void injectDependencies(@Nullable Bundle bundle) {
        DaggerTtaActivity_TtaComponent.builder().ttaModule(new TtaModule(this)).typingModule(new TypingModule(this, bundle)).searchModule(new SearchModule()).suggestionsModule(new SuggestionsModule()).metricsModule(new MetricsModule()).featuresModule(new FeaturesModule()).stateMachineModule(new StateMachineModule()).sdkModule(new SdkModule()).simbaModule(new SimbaModule()).dependenciesModule(new DependenciesModule()).permissionsModule(new PermissionsModule()).build().inject(this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (!this.typingPresenter.backButtonPressed()) {
            super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        preventActivityAnimation();
        setContentView(R.layout.tta_activity);
        setSecureWindowFlags();
        injectDependencies(bundle);
        this.typingPresenter.initialize();
        this.typingPresenter.launched(getIntent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.typingPresenter.destroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.typingPresenter.launched(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        setSecureWindowFlags();
        super.onPause();
        this.typingPresenter.pause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        clearSecureWindowFlags();
        this.typingPresenter.resume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.typingPresenter.saveInstanceState(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.typingPresenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        preventActivityAnimation();
        this.typingPresenter.stop();
    }
}
