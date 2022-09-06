package com.amazon.alexa.sendtoapp.notification;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.applink.evaluator.Target;
import com.amazon.alexa.applink.evaluator.TargetIdentifierType;
import com.amazon.alexa.applink.metrics.MobilyticsMetricsRecorder;
import com.amazon.alexa.applink.sendtoapp.SendToAppServiceUtils;
import com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionType;
import com.amazon.alexa.sendtoapp.notification.actions.Action;
import com.amazon.alexa.sendtoapp.notification.actions.Actions;
import com.amazon.alexa.sendtoapp.notification.actions.LaunchConfig;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
/* loaded from: classes10.dex */
public class SendToAppNotificationsActivity extends Activity {
    static final String ACTIONS_STRING_URI_PARAM = "actionsString";
    static final String CREATION_TIME_URI_PARAM = "creationTime";
    static final String METRIC_ID_URI_PARAM = "metricId";
    private static final String TAG = SendToAppNotificationsActivity.class.getSimpleName();
    static final String TOKEN_URI_PARAM = "token";

    private Target getTarget(@Nullable Action action) {
        if (action == null) {
            return null;
        }
        try {
            TargetIdentifierType valueOf = TargetIdentifierType.valueOf(action.identifierType());
            LaunchConfig launchConfig = action.launchConfig();
            return Target.create(action.catalogId(), action.identifier(), valueOf, (launchConfig == null || launchConfig.mustLaunchTargetInGivenApp() == null) ? false : launchConfig.mustLaunchTargetInGivenApp().booleanValue());
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "Action identifier type is null");
            return null;
        }
    }

    private void parseIntentEvaluateTargetsReportAndLaunch(Intent intent, String str) {
        String queryParameter = intent.getData().getQueryParameter("token");
        if (TextUtils.isEmpty(queryParameter)) {
            Log.e(TAG, "Token is null or empty.");
            return;
        }
        String queryParameter2 = intent.getData().getQueryParameter(ACTIONS_STRING_URI_PARAM);
        if (TextUtils.isEmpty(queryParameter2)) {
            Log.e(TAG, "Actions string is null or empty.");
            return;
        }
        try {
            Actions actions = (Actions) new GsonBuilder().registerTypeAdapter(Actions.class, Actions.typeAdapter()).create().fromJson(queryParameter2, (Class<Object>) Actions.class);
            ArrayList arrayList = new ArrayList();
            Target target = getTarget(actions.primary());
            if (target == null) {
                Log.e(TAG, "Primary target could not be formed.");
                return;
            }
            arrayList.add(target);
            Target target2 = getTarget(actions.fallback());
            if (target2 == null) {
                Log.e(TAG, "Fallback target could not be formed. Attempting to launch with just the primary target.");
            } else {
                arrayList.add(target2);
            }
            Log.i(TAG, String.format("Launching %d targets", Integer.valueOf(arrayList.size())));
            String.format("Targets: %s", arrayList);
            getSendToAppServiceUtils().evaluateTargetsReportAndLaunch(arrayList, queryParameter, ReportUserInteractionType.NOTIFICATION, str);
        } catch (Exception unused) {
            Log.e(TAG, "Actions json string could not be parsed");
            String.format("Actions json string: %s", queryParameter2);
        }
    }

    private void preventActivityAnimation() {
        overridePendingTransition(0, 0);
    }

    @VisibleForTesting
    SendToAppServiceUtils getSendToAppServiceUtils() {
        return new SendToAppServiceUtils(this);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, "onCreate called");
        preventActivityAnimation();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume called");
        Intent intent = getIntent();
        if (intent == null) {
            Log.e(TAG, "Intent is null.");
        } else if (intent.getData() == null) {
            Log.e(TAG, "Intent data is null.");
        } else {
            String queryParameter = intent.getData().getQueryParameter(CREATION_TIME_URI_PARAM);
            if (TextUtils.isEmpty(queryParameter)) {
                Log.e(TAG, "Creation time is null or empty.");
                return;
            }
            try {
                long parseLong = Long.parseLong(queryParameter);
                String queryParameter2 = intent.getData().getQueryParameter(METRIC_ID_URI_PARAM);
                if (TextUtils.isEmpty(queryParameter2)) {
                    Log.e(TAG, "Metric id is null or empty.");
                }
                Long valueOf = Long.valueOf(System.currentTimeMillis() - parseLong);
                if (getString(R.string.send_to_app_notifications_create_action).equals(intent.getAction())) {
                    Log.i(TAG, String.format("The notification was clicked after %d ms.", valueOf));
                    MobilyticsMetricsRecorder.recordUserInteractionEvent(SendToAppNotificationsMetricsConstants.COMPONENT_NAME, "PushNotifications", "Clicked", "click", queryParameter2);
                    MobilyticsMetricsRecorder.recordTimer(SendToAppNotificationsMetricsConstants.COMPONENT_NAME, "PushNotifications", "Clicked", valueOf.longValue(), queryParameter2);
                    parseIntentEvaluateTargetsReportAndLaunch(intent, queryParameter2);
                } else if (getString(R.string.send_to_app_notifications_delete_action).equals(intent.getAction())) {
                    if (valueOf.longValue() > SendToAppNotifications.EXPIRATION_TIME.longValue() - SendToAppNotifications.EXPIRATION_FLEX_TIME.longValue()) {
                        Log.i(TAG, String.format("The notification expired after %d ms.", valueOf));
                        MobilyticsMetricsRecorder.recordTimer(SendToAppNotificationsMetricsConstants.COMPONENT_NAME, "PushNotifications", SendToAppNotificationsMetricsConstants.EXPIRED_EVENT_NAME, valueOf.longValue(), queryParameter2);
                    } else {
                        Log.i(TAG, String.format("The notification was dismissed after %d ms.", valueOf));
                        MobilyticsMetricsRecorder.recordTimer(SendToAppNotificationsMetricsConstants.COMPONENT_NAME, "PushNotifications", "Dismissed", valueOf.longValue(), queryParameter2);
                    }
                } else {
                    Log.e(TAG, "The action required to invoke this activity is invalid.");
                }
                finish();
            } catch (NumberFormatException e) {
                Log.e(TAG, String.format("Creation time cannot be parsed to long: %s.", queryParameter), e);
            }
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop called");
        preventActivityAnimation();
    }
}
