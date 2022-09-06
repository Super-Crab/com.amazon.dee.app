package com.amazon.alexa.voice.handsfree.decider.setup;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.notification.views.languageswitching.LanguageSwitchingNotificationPresenter;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.ui.ManagedActivity;
import com.amazon.alexa.voice.handsfree.R;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
/* loaded from: classes11.dex */
public class LanguageSelectorActivity extends ManagedActivity {
    private Initializer mInitializer;
    private boolean mIsLaunchedFromNotification;
    private LanguageSelectorPresenter mLanguageSelectorPresenter;

    @VisibleForTesting
    LanguageSelectorActivity(@NonNull boolean z, @NonNull LanguageSelectorPresenter languageSelectorPresenter, @NonNull Initializer initializer) {
        this.mIsLaunchedFromNotification = z;
        this.mLanguageSelectorPresenter = languageSelectorPresenter;
        this.mInitializer = initializer;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.handsfree.ui.ManagedActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mInitializer.initialize(this);
        setContentView(R.layout.handsfree_oobe);
        this.mLanguageSelectorPresenter = new LanguageSelectorPresenter(this, bundle, getApplicationContext());
        this.mLanguageSelectorPresenter.initializeHandsFreeLocaleAuthority();
        Intent intent = getIntent();
        this.mIsLaunchedFromNotification = intent.getExtras().getBoolean(LanguageSwitchingNotificationPresenter.IS_LS_PAGE_LAUNCHED_FROM_NOTIFICATION);
        if (this.mIsLaunchedFromNotification) {
            this.mLanguageSelectorPresenter.onNotificationClicked(intent);
        }
    }

    public void setupLanguageRouter() {
        runOnUiThread(new Runnable() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.LanguageSelectorActivity.1
            @Override // java.lang.Runnable
            public void run() {
                LanguageSelectorActivity.this.mLanguageSelectorPresenter.setupPermissionsRouter((ViewGroup) LanguageSelectorActivity.this.findViewById(R.id.handsfree_oobe_container), new Router.OnTransactionAdapter() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.LanguageSelectorActivity.1.1
                    @Override // com.amazon.regulator.Router.OnTransactionAdapter, com.amazon.regulator.Router.OnTransactionListener
                    public void onAfterTransition(ControllerTransaction controllerTransaction) {
                        if (!LanguageSelectorActivity.this.mIsLaunchedFromNotification) {
                            LanguageSelectorActivity.this.finishStep(ManagedActivity.StepResult.CONTINUE);
                        } else {
                            LanguageSelectorActivity.this.finish();
                        }
                    }
                });
            }
        });
    }

    public LanguageSelectorActivity() {
        this.mIsLaunchedFromNotification = false;
        this.mInitializer = InitializerProvider.getInitializer();
    }
}
