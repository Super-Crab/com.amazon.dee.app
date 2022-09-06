package com.amazon.alexa.voice.ftue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.voice.feature.FeatureAvailability;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.metrics.VoiceMetricsConstants;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.metrics.service.MetricsService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsFreeConstants;
import com.amazon.alexa.voice.ui.onedesign.util.JSONUtils;
import com.amazon.alexa.voice.wakeword.WakewordPreference;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class VoiceFtueActivity extends AppCompatActivity implements OnFtueCompletedListener {
    private static final String EXTRA_CONFIG = "extra_config";
    private static final String EXTRA_FROM = "extra_from";
    private static final String ROUTER_FTUE = "ftue";
    private static final String TAG = VoiceFtueActivity.class.getSimpleName();
    @Inject
    AlexaLocaleAuthority alexaLocaleAuthority;
    @Inject
    FeatureAvailability featureAvailability;
    private String ftueCancelRoute;
    @Inject
    FtuePreference ftuePreference;
    private boolean ftueSuccess;
    private String ftueSuccessRoute;
    Router.OnTransactionAdapter ftueTransactionAdapter = new Router.OnTransactionAdapter() { // from class: com.amazon.alexa.voice.ftue.VoiceFtueActivity.1
        @Override // com.amazon.regulator.Router.OnTransactionAdapter, com.amazon.regulator.Router.OnTransactionListener
        public void onAfterTransition(ControllerTransaction controllerTransaction) {
            if (!VoiceFtueActivity.this.router.hasRootController()) {
                VoiceFtueActivity.this.finish();
                VoiceFtueActivity.this.navigateToPostFtueRoute();
            }
        }
    };
    @Inject
    MetricsBridge metricsBridge;
    @Inject
    MetricsService metricsService;
    private Router router;
    @Inject
    RoutingService routingService;
    @Inject
    VoicePermissionsAuthority voicePermissionsAuthority;
    @Inject
    VoxMetricEventProcessingService voxMetricEventProcessingService;
    @Inject
    WakewordPreference wakewordPreference;

    @Nullable
    private String getCancelRoute(JSONObject jSONObject) {
        return JSONUtils.optionalString(jSONObject, HandsFreeConstants.CANCEL_ROUTE, (String) null);
    }

    @Nullable
    private String getSuccessRoute(JSONObject jSONObject) {
        return JSONUtils.optionalString(jSONObject, HandsFreeConstants.SUCCESS_ROUTE, (String) null);
    }

    @Nullable
    private JSONObject getViewProperties(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.optJSONObject(HandsFreeConstants.VIEW_PROPS);
    }

    public static void launchVoiceFtue(@NonNull Activity activity, @NonNull String str, @Nullable String str2) {
        Intent intent = new Intent(activity, VoiceFtueActivity.class);
        intent.putExtra(EXTRA_FROM, str);
        if (str2 != null) {
            intent.putExtra(EXTRA_CONFIG, str2);
        }
        activity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void navigateToPostFtueRoute() {
        if (this.routingService == null) {
            Log.e(TAG, "Routing service is null cannot transition to next route after VoiceFTUE");
            return;
        }
        String str = this.ftueSuccess ? this.ftueSuccessRoute : this.ftueCancelRoute;
        if (str == null) {
            return;
        }
        GeneratedOutlineSupport1.outline163("Voice FTUE finished and attempting to navigate to: ", str, TAG);
        RoutingService.RoutingBuilder match = this.routingService.match(str);
        if (match == null) {
            return;
        }
        match.clearBackStack().navigate();
    }

    private void preventActivityAnimation() {
        overridePendingTransition(0, 0);
    }

    private void saveRouterState(Bundle bundle, Router router, String str) {
        Bundle bundle2 = new Bundle();
        router.saveInstanceState(bundle2);
        bundle.putParcelable(str, bundle2);
    }

    public /* synthetic */ boolean lambda$onCreate$0$VoiceFtueActivity() {
        return this.voicePermissionsAuthority.hasMinimumPermissions();
    }

    public /* synthetic */ void lambda$onCreate$1$VoiceFtueActivity(boolean z) {
        this.wakewordPreference.enableWakeword(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:11:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006c  */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onCreate(@androidx.annotation.Nullable android.os.Bundle r9) {
        /*
            Method dump skipped, instructions count: 278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.voice.ftue.VoiceFtueActivity.onCreate(android.os.Bundle):void");
    }

    @Override // com.amazon.alexa.voice.ftue.OnFtueCompletedListener
    public void onFtueCompleted(boolean z) {
        this.ftueSuccess = z;
        if (z) {
            this.wakewordPreference.enableWakeword(true);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.router.setRequestPermissionsResult(i, strArr, iArr);
        VoiceMetricsConstants.recordPermissionsMetrics(strArr, iArr, this.metricsService, getIntent().getStringExtra(EXTRA_FROM));
        this.voicePermissionsAuthority.update();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        saveRouterState(bundle, this.router, ROUTER_FTUE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.router.addOnPopTransactionListener(this.ftueTransactionAdapter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.router.removeOnPopTransactionListener(this.ftueTransactionAdapter);
        preventActivityAnimation();
    }
}
