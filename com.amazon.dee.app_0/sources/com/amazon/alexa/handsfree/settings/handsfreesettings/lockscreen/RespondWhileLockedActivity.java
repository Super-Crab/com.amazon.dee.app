package com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.settings.R;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.ResponsePreferenceCallback;
import com.amazon.alexa.handsfree.settings.dependencies.FalcoSettingsComponent;
import com.amazon.alexa.handsfree.settings.metrics.ClickMetricMetadata;
import com.amazon.alexa.handsfree.settings.voxsettings.ShowOnLockscreenResultReceiver;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsEnqueuer;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import javax.inject.Inject;
/* loaded from: classes8.dex */
public class RespondWhileLockedActivity extends AppCompatActivity {
    private static final int FFFFFF = 16777215;
    private static final String TAG = RespondWhileLockedActivity.class.getSimpleName();
    @Inject
    MetricsBuilderProvider mMetricsBuilderProvider;
    private VoxSettingsEnqueuer mVoxSettingsEnqueuer;

    public RespondWhileLockedActivity() {
        configure(new VoxSettingsEnqueuer());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkCurrentLockScreenValue() {
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.alexa_respond_while_locked_radiogroup);
        this.mVoxSettingsEnqueuer.getShowOnLockscreen(this, new ShowOnLockscreenResultReceiver(new ResponsePreferenceCallback() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.RespondWhileLockedActivity.3
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.ResponsePreferenceCallback
            public void onPreferenceChanged(String str) {
                if (str.equals(RespondWhileLockedActivity.this.getString(R.string.alexa_respond_while_locked_block_none))) {
                    radioGroup.check(R.id.response_while_locked_on);
                } else if (str.equals(RespondWhileLockedActivity.this.getString(R.string.alexa_respond_while_locked_block_personal))) {
                    radioGroup.check(R.id.response_while_locked_on_block_resp);
                } else if (str.equals(RespondWhileLockedActivity.this.getString(R.string.alexa_respond_while_locked_block_all))) {
                    radioGroup.check(R.id.response_while_locked_off);
                } else {
                    radioGroup.check(R.id.response_while_locked_on_block_resp);
                }
            }
        }));
    }

    private ClickMetricMetadata.Action getActionType(@NonNull String str) {
        if (str.equals(getString(R.string.alexa_respond_while_locked_block_none))) {
            return ClickMetricMetadata.Action.BLOCK_NONE;
        }
        if (str.equals(getString(R.string.alexa_respond_while_locked_block_all))) {
            return ClickMetricMetadata.Action.BLOCK_ALL;
        }
        return ClickMetricMetadata.Action.BLOCK_PERSONAL;
    }

    @VisibleForTesting
    void configure(@NonNull VoxSettingsEnqueuer voxSettingsEnqueuer) {
        this.mVoxSettingsEnqueuer = voxSettingsEnqueuer;
    }

    @VisibleForTesting
    AlertDialog createAlertDialogForEnableOption(@NonNull RadioGroup radioGroup, @NonNull String str) {
        return setAndCreateAlertDialogForEnableOption(radioGroup, str, new AlertDialog.Builder(this, R.style.AlertDialogBackground));
    }

    String getSelectedRadioButtonOption(RadioGroup radioGroup) {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.response_while_locked_on) {
            return getString(R.string.alexa_respond_while_locked_block_none);
        }
        if (checkedRadioButtonId == R.id.response_while_locked_on_block_resp) {
            return getString(R.string.alexa_respond_while_locked_block_personal);
        }
        if (checkedRadioButtonId == R.id.response_while_locked_off) {
            return getString(R.string.alexa_respond_while_locked_block_all);
        }
        return getString(R.string.alexa_respond_while_locked_block_personal);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((FalcoSettingsComponent) AhfComponentsProvider.getComponent(this, FalcoSettingsComponent.class)).inject(this);
        setContentView(R.layout.respond_while_locked_choice);
        setSupportActionBar((Toolbar) findViewById(R.id.alexa_respond_while_locked_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        checkCurrentLockScreenValue();
    }

    public void onRespondWhileLockedOffRadioButtonClick(@NonNull View view) {
        updateSelectedValue(getString(R.string.alexa_respond_while_locked_block_all));
    }

    public void onRespondWhileLockedOnBlockPersonalRadioButtonClick(@NonNull View view) {
        updateSelectedValue(getString(R.string.alexa_respond_while_locked_block_personal));
    }

    public void onRespondWhileLockedOnRadioButtonClick(@NonNull View view) {
        updateSelectedValue(getString(R.string.alexa_respond_while_locked_block_none));
    }

    @VisibleForTesting
    AlertDialog setAndCreateAlertDialogForEnableOption(@NonNull final RadioGroup radioGroup, @NonNull String str, @NonNull AlertDialog.Builder builder) {
        builder.setMessage(Html.fromHtml(String.format("<p style=\"color:#%s\">%s</p>", Integer.toHexString(ThemeUtil.getColorFromAttribute(this, R.attr.mosaicNeutral10) & 16777215), getString(R.string.alexa_handsfree_show_on_lock_screen_alert_text))));
        return builder.setTitle(R.string.alexa_handsfree_show_on_lock_screen_alert_title).setPositiveButton(R.string.alexa_handsfree_show_on_lock_screen_alert_positive_text, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.RespondWhileLockedActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                Log.i(RespondWhileLockedActivity.TAG, "RwL Dialog Allow Button Pressed");
                radioGroup.check(R.id.response_while_locked_on);
                VoxSettingsEnqueuer voxSettingsEnqueuer = RespondWhileLockedActivity.this.mVoxSettingsEnqueuer;
                RespondWhileLockedActivity respondWhileLockedActivity = RespondWhileLockedActivity.this;
                voxSettingsEnqueuer.updateShowOnLockscreenPref(respondWhileLockedActivity, respondWhileLockedActivity.getString(R.string.alexa_respond_while_locked_block_none));
                RespondWhileLockedActivity.this.mMetricsBuilderProvider.newBuilder().withClickMetric(RespondWhileLockedActivity.TAG, ClickMetricMetadata.Component.SETTINGS_MENU, ClickMetricMetadata.PageType.RESPOND_ON_LOCK_SCREEN, ClickMetricMetadata.Action.BLOCK_NONE).emit(RespondWhileLockedActivity.this.getApplicationContext());
            }
        }).setNegativeButton(R.string.alexa_handsfree_show_on_lock_screen_alert_negative_text, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.lockscreen.RespondWhileLockedActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                Log.i(RespondWhileLockedActivity.TAG, "RwL Dialog Deny Button Pressed");
                RespondWhileLockedActivity.this.checkCurrentLockScreenValue();
                RespondWhileLockedActivity.this.mMetricsBuilderProvider.newBuilder().withClickMetric(RespondWhileLockedActivity.TAG, ClickMetricMetadata.Component.SETTINGS_MENU, ClickMetricMetadata.PageType.RESPOND_ON_LOCK_SCREEN, ClickMetricMetadata.Action.ALERT_DIALOG_DENY).emit(RespondWhileLockedActivity.this.getApplicationContext());
            }
        }).create();
    }

    public void updateSelectedValue(@NonNull String str) {
        if (str.equals(getString(R.string.alexa_respond_while_locked_block_none))) {
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.alexa_respond_while_locked_radiogroup);
            createAlertDialogForEnableOption(radioGroup, getSelectedRadioButtonOption(radioGroup)).show();
            return;
        }
        this.mVoxSettingsEnqueuer.updateShowOnLockscreenPref(this, str);
        this.mMetricsBuilderProvider.newBuilder().withClickMetric(TAG, ClickMetricMetadata.Component.SETTINGS_MENU, ClickMetricMetadata.PageType.RESPOND_ON_LOCK_SCREEN, getActionType(str)).emit(getApplicationContext());
    }
}
