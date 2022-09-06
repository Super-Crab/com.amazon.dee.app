package com.amazon.alexa.voice.ui.onedesign.ftue.handsfree;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.ui.onedesign.ftue.R;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.NewUserPrimerContract;
import com.amazon.alexa.voice.ui.onedesign.ftue.metrics.VoxUiMetricEventName;
import com.amazon.alexa.voice.ui.onedesign.util.JSONUtils;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class NewUserPrimerPresenter extends HandsfreePrimerPresenter implements NewUserPrimerContract.Presenter {
    private static final String TAG = "NewUserPrimerPresenter";
    private final Context context;
    private final NewUserPrimerContract.Interactor interactor;
    private final MetricsBridge metricsBridge;
    private boolean shouldDisplayAlertDialog;
    private final HandsfreePrimerContract.View view;
    private final JSONObject viewProperties;

    public NewUserPrimerPresenter(@NonNull HandsfreePrimerContract.View view, @NonNull NewUserPrimerContract.Interactor interactor, @NonNull Resources resources, @NonNull MetricsBridge metricsBridge, @Nullable JSONObject jSONObject, @NonNull Context context) {
        super(interactor, resources, metricsBridge);
        this.interactor = interactor;
        this.view = view;
        this.metricsBridge = metricsBridge;
        this.viewProperties = jSONObject;
        this.context = context;
    }

    private String getAllowButtonText(Resources resources) {
        return JSONUtils.optionalString(this.viewProperties, HandsFreeConstants.PRIMARY_BUTTON_LABEL, resources.getString(R.string.voice_ui_od_handsfree_ftue_new_user_button_allow));
    }

    private String getHandsFreeSettingsText(Resources resources) {
        return JSONUtils.optionalString(this.viewProperties, HandsFreeConstants.BODY_TEXT_2, resources.getString(R.string.voice_ui_od_handsfree_ftue_new_user_handsfree_settings_info));
    }

    private String getLaterButtonText(Resources resources) {
        return JSONUtils.optionalString(this.viewProperties, HandsFreeConstants.SECONDARY_BUTTON_LABEL, resources.getString(R.string.voice_ui_od_handsfree_ftue_new_user_button_later));
    }

    private String getRationaleText(Resources resources, String str) {
        return JSONUtils.optionalString(this.viewProperties, HandsFreeConstants.BODY_TEXT_3, resources.getString(R.string.voice_ui_od_handsfree_ftue_new_user_rationale, str));
    }

    private String getTitleText(Resources resources) {
        return JSONUtils.optionalString(this.viewProperties, "title", resources.getString(R.string.voice_ui_od_handsfree_ftue_new_user_title));
    }

    private String getUsageText(Resources resources) {
        return JSONUtils.optionalString(this.viewProperties, HandsFreeConstants.BODY_TEXT_1, resources.getString(R.string.voice_ui_od_handsfree_ftue_new_user_usage));
    }

    public /* synthetic */ void lambda$showAlert$0$NewUserPrimerPresenter(DialogInterface dialogInterface, int i) {
        this.metricsBridge.reportEvent(VoxUiMetricEventName.HANDSFREE_ALERT_CONTINUE);
        this.interactor.deferPrimer();
    }

    public /* synthetic */ void lambda$showAlert$1$NewUserPrimerPresenter(DialogInterface dialogInterface, int i) {
        this.metricsBridge.reportEvent(VoxUiMetricEventName.HANDSFREE_ALERT_CANCELLED);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerPresenter, com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Presenter
    public void laterClicked() {
        if (this.shouldDisplayAlertDialog) {
            showAlert();
        } else {
            super.laterClicked();
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.NewUserPrimerContract.Presenter
    public void permissionsResultReceived() {
        this.interactor.permissionsResultReceived();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.NewUserPrimerContract.Presenter
    public void showAlert() {
        JSONObject jSONObject = this.viewProperties;
        if (jSONObject == null) {
            Log.e(TAG, "Cannot show alert if viewProperties are not initialized");
            return;
        }
        try {
            String string = jSONObject.getString(HandsFreeConstants.ALERT_TITLE);
            String string2 = this.viewProperties.getString(HandsFreeConstants.ALERT_DESCRIPTION);
            String string3 = this.viewProperties.getString(HandsFreeConstants.ALERT_PRIMARY_BUTTON_LABEL);
            new AlertDialog.Builder(this.context).setTitle(string).setMessage(string2).setPositiveButton(string3, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.-$$Lambda$NewUserPrimerPresenter$xkqWbiXhqrBEAFmJeyMyos2nc48
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    NewUserPrimerPresenter.this.lambda$showAlert$0$NewUserPrimerPresenter(dialogInterface, i);
                }
            }).setNegativeButton(this.viewProperties.getString(HandsFreeConstants.ALERT_SECONDARY_BUTTON_LABEL), new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.-$$Lambda$NewUserPrimerPresenter$q4G1iL-rd2dtp6mg1OooyofGJok
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    NewUserPrimerPresenter.this.lambda$showAlert$1$NewUserPrimerPresenter(dialogInterface, i);
                }
            }).show();
        } catch (JSONException e) {
            String str = TAG;
            Log.e(str, "Error in showing alert | " + e);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Presenter
    public void start() {
        Resources resources = getResources();
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(getTitleText(resources));
        this.view.setUsage(getUsageText(resources), null);
        this.view.setHandsfreeSettingsInfo(getHandsFreeSettingsText(resources));
        String string = resources.getString(R.string.voice_ui_od_handsfree_ftue_learn_more);
        this.view.setRationale(getRationaleText(resources, string), string);
        this.view.setAllowButtonText(getAllowButtonText(resources));
        this.view.setLaterButtonText(getLaterButtonText(resources));
        JSONObject jSONObject = this.viewProperties;
        boolean z = false;
        if (jSONObject != null && jSONObject.optBoolean(HandsFreeConstants.SHOW_ALERT, false)) {
            z = true;
        }
        this.shouldDisplayAlertDialog = z;
        this.metricsBridge.reportEvent(VoxUiMetricEventName.HANDSFREE_FTUE_SHOWN);
    }
}
