package com.amazon.alexa.voice.wakeword;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.voice.R;
import com.amazon.alexa.voice.dagger.VoiceDependencies;
import com.amazon.alexa.voice.feature.FeatureAvailability;
import com.amazon.alexa.voice.metrics.VoiceMetricsConstants;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.ui.DefaultWindowInteractor;
import com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority;
import com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsController;
import com.amazon.alexa.voice.ui.window.WindowInteractor;
import com.amazon.regulator.Component;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import javax.inject.Inject;
/* loaded from: classes11.dex */
public class WakewordPermissionRequestActivity extends AppCompatActivity {
    private static final String EXTRA_FROM = "extra_from";
    private static final int PERMISSION_REQUEST_CODE_WAKEWORD = 0;
    @Inject
    AlexaLocaleAuthority alexaLocaleAuthority;
    @Inject
    FeatureAvailability featureAvailability;
    @Inject
    MetricsService metricsService;
    private Router router;
    @Inject
    VoicePermissionsAuthority voicePermissionsAuthority;
    @Inject
    WakewordPreference wakewordPreference;

    public static void requestMinimumPermission(@NonNull Context context, @NonNull String str) {
        Intent intent = new Intent(context, WakewordPermissionRequestActivity.class);
        intent.addFlags(268435456);
        intent.putExtra(EXTRA_FROM, str);
        context.startActivity(intent);
    }

    private void validatePermissions() {
        if (!this.voicePermissionsAuthority.hasMinimumPermissions()) {
            this.router.pushController(new ControllerTransaction(GoToSettingsController.create()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.voice_permissions);
        VoiceDependencies.inject(this);
        Component component = new Component();
        component.provide((Class<? extends Class>) AlexaLocaleAuthority.class, (Class) this.alexaLocaleAuthority).register();
        component.provide((Class<? extends Class>) WindowInteractor.class, (Class) new DefaultWindowInteractor(getWindow())).register();
        component.provide((Class<? extends Class>) FeatureAvailability.class, (Class) this.featureAvailability).register();
        this.router = new Router(this, component, bundle);
        this.router.attach((ViewGroup) findViewById(R.id.voice_permissions));
        this.router.addOnPopTransactionListener(new Router.OnTransactionAdapter() { // from class: com.amazon.alexa.voice.wakeword.WakewordPermissionRequestActivity.1
            @Override // com.amazon.regulator.Router.OnTransactionAdapter, com.amazon.regulator.Router.OnTransactionListener
            public void onAfterTransition(ControllerTransaction controllerTransaction) {
                WakewordPermissionRequestActivity.this.finish();
            }
        });
        WakewordPermissionsUiUtil.requestPermissionIfNeededOrFinish(this, 0);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.voicePermissionsAuthority.update();
        if (i == 0) {
            validatePermissions();
            VoiceMetricsConstants.recordPermissionsMetrics(strArr, iArr, this.metricsService, getIntent().getStringExtra(EXTRA_FROM));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.voicePermissionsAuthority.hasMinimumPermissions()) {
            this.wakewordPreference.enableWakeword(true);
            finish();
        }
    }
}
