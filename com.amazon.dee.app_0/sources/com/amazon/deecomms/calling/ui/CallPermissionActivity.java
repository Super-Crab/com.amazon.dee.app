package com.amazon.deecomms.calling.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.MPUSettingHandler;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import java.util.Collections;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class CallPermissionActivity extends AppCompatActivity {
    @Inject
    CallManager callManager;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState currentSipClientState;
    private Button declineButton;
    private Button enableButton;
    private boolean learnMoreSelected;

    private void setupLayouts() {
        this.enableButton = (Button) findViewById(R.id.enableButton);
        this.declineButton = (Button) findViewById(R.id.declineButton);
        this.enableButton.setText(R.string.group_calling_enhanced_processing_setting_enable);
        this.enableButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$CallPermissionActivity$bBtGtq76WKqroHe8v2UH6pYRas8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CallPermissionActivity.this.lambda$setupLayouts$1$CallPermissionActivity(view);
            }
        });
        this.declineButton.setText(R.string.group_calling_enhanced_processing_setting_later);
        this.declineButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$CallPermissionActivity$YVfr5fd5OTA-1Sp1-mlclTHT4Bw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CallPermissionActivity.this.lambda$setupLayouts$2$CallPermissionActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$CallPermissionActivity() {
        this.currentSipClientState.setEnhancedProcessingSetting(true);
        setPermissionResult(true);
    }

    public /* synthetic */ void lambda$setupLayouts$1$CallPermissionActivity(View view) {
        if (this.learnMoreSelected) {
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.ENABLE_TAPPED_AFTER_LEARN_MORE);
        } else {
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.ENABLE_TAPPED);
        }
        new MPUSettingHandler(this.commsIdentityManager.getHomeGroupId("CallPermissionActivity", false), this.currentSipClientState, Collections.singletonList(Constants.SET_SETTING), new Runnable() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$CallPermissionActivity$CxJCsD1tOUrVQFYL8kI_Aztu3uc
            @Override // java.lang.Runnable
            public final void run() {
                CallPermissionActivity.this.lambda$null$0$CallPermissionActivity();
            }
        }).execute(new Void[0]);
    }

    public /* synthetic */ void lambda$setupLayouts$2$CallPermissionActivity(View view) {
        if (this.learnMoreSelected) {
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.LATER_TAPPED_AFTER_LEARN_MORE);
        } else {
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.LATER_TAPPED);
        }
        setPermissionResult(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        CommsDaggerWrapper.getComponent().inject(this);
        if (this.capabilitiesManager.isThemedUIEnabled() || this.capabilitiesManager.isMosaicThemingEnabled()) {
            ThemeUtil.setTheme(this);
        }
        setContentView(R.layout.call_permission_activity);
        setupLayouts();
        EnhancedProcessingPermissionFragment enhancedProcessingPermissionFragment = new EnhancedProcessingPermissionFragment();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, enhancedProcessingPermissionFragment);
        beginTransaction.commitAllowingStateLoss();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OPTIN_SHOWN);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        this.callManager.setOptInUIVisibility(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.callManager.setOptInUIVisibility(false);
    }

    public void setLearnMoreSelected(boolean z) {
        this.learnMoreSelected = z;
    }

    public void setPermissionResult(boolean z) {
        setResult(z ? 20 : 10, getIntent());
        finish();
    }
}
