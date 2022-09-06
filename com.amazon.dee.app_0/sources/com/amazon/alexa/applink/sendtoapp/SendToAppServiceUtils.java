package com.amazon.alexa.applink.sendtoapp;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.applink.evaluator.EvaluatedTargetState;
import com.amazon.alexa.applink.evaluator.InstallStatus;
import com.amazon.alexa.applink.evaluator.Target;
import com.amazon.alexa.applink.evaluator.TargetEvaluator;
import com.amazon.alexa.applink.launcher.AppLauncher;
import com.amazon.alexa.applink.metrics.AppLinkMetricsConstants;
import com.amazon.alexa.applink.metrics.MobilyticsMetricsRecorder;
import com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionOutcome;
import com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionPayload;
import com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionReason;
import com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionResult;
import com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionResults;
import com.amazon.alexa.applink.sendtoapp.reportuserinteraction.ReportUserInteractionType;
import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.marketplace.api.Region;
import com.amazon.alexa.mobilytics.event.EventType;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/* loaded from: classes6.dex */
public class SendToAppServiceUtils {
    private static final int EVALUATED_TARGET_STATE_PRIMARY_INDEX = 0;
    private static final int EVALUATED_TARGET_STATE_SECONDARY_INDEX = 1;
    private static final String TAG = "SendToAppServiceUtils";
    @VisibleForTesting
    final String alexaApiBaseUrlstring;
    private final AppLauncher appLauncher;
    private final Context context;
    private final IdentityService identityService;
    private final OkHttpClient okHttpClient;
    private final TargetEvaluator targetEvaluator;

    /* renamed from: com.amazon.alexa.applink.sendtoapp.SendToAppServiceUtils$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$applink$evaluator$InstallStatus = new int[InstallStatus.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$marketplace$api$Region;

        static {
            try {
                $SwitchMap$com$amazon$alexa$applink$evaluator$InstallStatus[InstallStatus.INSTALLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$applink$evaluator$InstallStatus[InstallStatus.NOT_INSTALLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$applink$evaluator$InstallStatus[InstallStatus.INCOMPATIBLE_VERSION_INSTALLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$applink$evaluator$InstallStatus[InstallStatus.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$amazon$alexa$marketplace$api$Region = new int[Region.values().length];
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Region[Region.EUROPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$marketplace$api$Region[Region.FAR_EAST.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public SendToAppServiceUtils(@NonNull Context context) {
        this.context = context;
        EnvironmentService environmentService = (EnvironmentService) GeneratedOutlineSupport1.outline21(EnvironmentService.class);
        if (environmentService != null) {
            Log.i(TAG, String.format("Build flavor: %s", environmentService.getBuildFlavor()));
            String.format("Region: %s", environmentService.getMarketplace().getRegion());
            String buildFlavor = environmentService.getBuildFlavor();
            char c = 65535;
            int hashCode = buildFlavor.hashCode();
            if (hashCode != 3020272) {
                if (hashCode == 98120615 && buildFlavor.equals("gamma")) {
                    c = 1;
                }
            } else if (buildFlavor.equals("beta")) {
                c = 0;
            }
            if (c == 0) {
                int ordinal = environmentService.getMarketplace().getRegion().ordinal();
                if (ordinal == 0) {
                    this.alexaApiBaseUrlstring = "https://projectdee-ui-gb.aka.amazon.com";
                } else if (ordinal != 1) {
                    this.alexaApiBaseUrlstring = "https://projectdee-ui.aka.amazon.com";
                } else {
                    this.alexaApiBaseUrlstring = "https://projectdee-ui-jp.aka.amazon.com";
                }
            } else if (c != 1) {
                int ordinal2 = environmentService.getMarketplace().getRegion().ordinal();
                if (ordinal2 == 0) {
                    this.alexaApiBaseUrlstring = "https://alexa.amazon.co.uk";
                } else if (ordinal2 != 1) {
                    this.alexaApiBaseUrlstring = "https://alexa.amazon.com";
                } else {
                    this.alexaApiBaseUrlstring = "https://alexa.amazon.co.jp";
                }
            } else {
                int ordinal3 = environmentService.getMarketplace().getRegion().ordinal();
                if (ordinal3 == 0) {
                    this.alexaApiBaseUrlstring = "https://projectdee-ui-pre-prod.amazon.co.uk";
                } else if (ordinal3 != 1) {
                    this.alexaApiBaseUrlstring = "https://projectdee-ui-pre-prod.amazon.com";
                } else {
                    this.alexaApiBaseUrlstring = "https://projectdee-ui-pre-prod.amazon.co.jp";
                }
            }
            String.format("Alexa api base url: %s", this.alexaApiBaseUrlstring);
        } else {
            Log.w(TAG, "Environment service is null, defaulting to NA Prod endpoint");
            this.alexaApiBaseUrlstring = "https://alexa.amazon.com";
        }
        this.identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        this.appLauncher = new AppLauncher(context);
        this.targetEvaluator = new TargetEvaluator(context);
        this.okHttpClient = new OkHttpClient.Builder().build();
    }

    private void sendUserInteractionRequest(@NonNull String str, @NonNull final ReportUserInteractionType reportUserInteractionType, @NonNull ReportUserInteractionResult reportUserInteractionResult, @Nullable ReportUserInteractionResult reportUserInteractionResult2, @Nullable final String str2) {
        if (this.identityService == null) {
            Log.e(TAG, "Identity service is null");
            MobilyticsMetricsRecorder.recordCounter(AppLinkMetricsConstants.COMPONENT_NAME, reportUserInteractionType.name(), AppLinkMetricsConstants.IDENTITY_SERVICE_NULL, 1, str2);
            return;
        }
        MobilyticsMetricsRecorder.recordCounter(AppLinkMetricsConstants.COMPONENT_NAME, reportUserInteractionType.name(), AppLinkMetricsConstants.IDENTITY_SERVICE_NULL, 0, str2);
        if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "Session token is empty");
            MobilyticsMetricsRecorder.recordCounter(AppLinkMetricsConstants.COMPONENT_NAME, reportUserInteractionType.name(), AppLinkMetricsConstants.SESSION_TOKEN_EMPTY, 1, str2);
        } else {
            MobilyticsMetricsRecorder.recordCounter(AppLinkMetricsConstants.COMPONENT_NAME, reportUserInteractionType.name(), AppLinkMetricsConstants.SESSION_TOKEN_EMPTY, 0, str2);
        }
        HttpUrl parse = HttpUrl.parse(this.alexaApiBaseUrlstring);
        if (parse == null) {
            Log.e(TAG, String.format("Alexa API Base URL cannot be parsed: %s", this.alexaApiBaseUrlstring));
            return;
        }
        final HttpUrl build = parse.newBuilder().addPathSegment("api").addPathSegment("sendtoapp").addPathSegment("v1").addPathSegment(EventType.USER_INTERACTION).build();
        final String json = new Gson().toJson(ReportUserInteractionPayload.create(reportUserInteractionType, ReportUserInteractionResults.create(reportUserInteractionResult, reportUserInteractionResult2), str));
        final RequestBody create = RequestBody.create(MediaType.get(LocationPublisher.CONTENT_TYPE_JSON), json);
        Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.amazon.alexa.applink.sendtoapp.-$$Lambda$SendToAppServiceUtils$dCQ7mckStSokwndPS9oxyiy0Yl0
            @Override // java.lang.Runnable
            public final void run() {
                SendToAppServiceUtils.this.lambda$sendUserInteractionRequest$0$SendToAppServiceUtils(build, json, create, reportUserInteractionType, str2);
            }
        });
    }

    public void evaluateTargetsReportAndLaunch(@NonNull List<Target> list, @NonNull String str, @NonNull ReportUserInteractionType reportUserInteractionType, @Nullable String str2) {
        Log.i(TAG, "Evaluating targets, then will report and launch the selected target");
        List<EvaluatedTargetState> evaluateTargets = this.targetEvaluator.evaluateTargets(list);
        ArrayList arrayList = new ArrayList();
        Iterator<EvaluatedTargetState> it2 = evaluateTargets.iterator();
        boolean z = false;
        while (true) {
            ReportUserInteractionReason reportUserInteractionReason = null;
            if (!it2.hasNext()) {
                break;
            }
            EvaluatedTargetState next = it2.next();
            if (!z && next.canLaunch() && next.launchIntent() != null) {
                this.appLauncher.launchExternalApp(next.launchIntent());
                arrayList.add(ReportUserInteractionResult.create(ReportUserInteractionOutcome.PLATFORM_DETERMINED_TARGET_LAUNCHED, null));
                z = true;
            } else {
                int ordinal = next.installStatus().ordinal();
                if (ordinal != 0) {
                    if (ordinal == 1) {
                        reportUserInteractionReason = ReportUserInteractionReason.NOT_INSTALLED;
                    } else if (ordinal != 2) {
                        reportUserInteractionReason = ReportUserInteractionReason.UNKNOWN_ERROR;
                    } else {
                        reportUserInteractionReason = ReportUserInteractionReason.INCOMPAT_VERSION_INSTALLED;
                    }
                }
                arrayList.add(ReportUserInteractionResult.create(ReportUserInteractionOutcome.TARGET_NOT_LAUNCHED, reportUserInteractionReason));
            }
        }
        sendUserInteractionRequest(str, reportUserInteractionType, (ReportUserInteractionResult) arrayList.get(0), arrayList.size() > 1 ? (ReportUserInteractionResult) arrayList.get(1) : null, str2);
    }

    public /* synthetic */ void lambda$sendUserInteractionRequest$0$SendToAppServiceUtils(HttpUrl httpUrl, String str, RequestBody requestBody, ReportUserInteractionType reportUserInteractionType, String str2) {
        Log.i(TAG, String.format("Forming request to %s", httpUrl.toString()));
        String.format("Request body: %s", str);
        String accessToken = this.identityService.getAccessToken(TAG);
        if (TextUtils.isEmpty(accessToken)) {
            Log.e(TAG, "Access token is empty");
            return;
        }
        try {
            Response execute = this.okHttpClient.newCall(new Request.Builder().addHeader("x-amz-access-token", accessToken).post(requestBody).url(httpUrl).build()).execute();
            Log.i(TAG, String.format("Request success: %s, code: %s", Boolean.valueOf(execute.isSuccessful()), Integer.valueOf(execute.code())));
            if (execute.isSuccessful()) {
                MobilyticsMetricsRecorder.recordCounter(AppLinkMetricsConstants.COMPONENT_NAME, reportUserInteractionType.name(), AppLinkMetricsConstants.USER_INTERACTION_API_SUCCESS, 1, str2);
            } else {
                MobilyticsMetricsRecorder.recordCounter(AppLinkMetricsConstants.COMPONENT_NAME, reportUserInteractionType.name(), AppLinkMetricsConstants.USER_INTERACTION_API_SUCCESS, 0, str2);
            }
            MobilyticsMetricsRecorder.recordCounter(AppLinkMetricsConstants.COMPONENT_NAME, reportUserInteractionType.name(), AppLinkMetricsConstants.USER_INTERACTION_API_RESPONSE_CODE, execute.code(), str2);
            MobilyticsMetricsRecorder.recordCounter(AppLinkMetricsConstants.COMPONENT_NAME, reportUserInteractionType.name(), AppLinkMetricsConstants.USER_INTERACTION_API_EXCEPTION, 0, str2);
        } catch (IOException e) {
            Log.e(TAG, "Request failed");
            String str3 = "Request error" + e.getMessage();
            MobilyticsMetricsRecorder.recordCounter(AppLinkMetricsConstants.COMPONENT_NAME, reportUserInteractionType.name(), AppLinkMetricsConstants.USER_INTERACTION_API_EXCEPTION, 1, str2);
        }
    }

    SendToAppServiceUtils(@NonNull Context context, @NonNull AppLauncher appLauncher, @Nullable IdentityService identityService, @NonNull OkHttpClient okHttpClient, @NonNull String str, @NonNull TargetEvaluator targetEvaluator) {
        this.context = context;
        this.appLauncher = appLauncher;
        this.identityService = identityService;
        this.okHttpClient = okHttpClient;
        this.alexaApiBaseUrlstring = str;
        this.targetEvaluator = targetEvaluator;
    }
}
