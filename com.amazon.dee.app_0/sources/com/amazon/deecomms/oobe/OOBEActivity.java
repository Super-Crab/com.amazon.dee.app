package com.amazon.deecomms.oobe;

import amazon.speech.simclient.settings.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.R;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.api.CommsFeature;
import com.amazon.deecomms.api.CommsIdentity;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.OobeService;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.common.util.WebIntentUtils;
import com.amazon.deecomms.contacts.model.UserInfo;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.exceptions.InvalidCommsIdentityException;
import com.amazon.deecomms.nativemodules.CommsEventEmitterBridge;
import com.amazon.deecomms.oobe.OOBEActivity;
import com.amazon.deecomms.oobe.OOBEStateMachine;
import com.amazon.deecomms.oobe.fragments.AccessoryPermissionsFragment;
import com.amazon.deecomms.oobe.fragments.CVFFragment;
import com.amazon.deecomms.oobe.fragments.IntroductionFragment;
import com.amazon.deecomms.oobe.fragments.MainOOBEFragment;
import com.amazon.deecomms.oobe.fragments.NameConfirmationFragment;
import com.amazon.deecomms.oobe.fragments.PermissionsFragment;
import com.amazon.deecomms.oobe.fragments.PhoneVerifiedFragment;
import com.amazon.deecomms.oobe.fragments.SSOUserSelectionFragment;
import com.amazon.deecomms.oobe.fragments.TermsFragment;
import com.amazon.deecomms.oobe.fragments.UserSelectionFragment;
import com.amazon.deecomms.oobe.structures.CVFResult;
import com.amazon.deecomms.oobe.util.DeviceMetadataStoreRegistrar;
import com.amazon.deecomms.oobe.util.OOBEPersonManager;
import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerContextProvider;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerManager;
import com.amazon.deecomms.util.DeviceInfo;
import com.amazon.deecomms.util.LogsUtil;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.HashMap;
import javax.inject.Inject;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class OOBEActivity extends FragmentActivity implements CommsIdentityReceiver, ActivityCompat.OnRequestPermissionsResultCallback {
    public static final String GO_TO_USER_SELECTION = "GO_TO_USER_SELECTION";
    private static final int OOBE_ERROR = 1;
    public static final String OOBE_INTENT = "OOBE_INTENT";
    public static final String OOBE_SIGNING_IN = "SIGNING_IN";
    private static final int OOBE_SIGNOUT = 2;
    @Inject
    ApplicationManager applicationManager;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommsAccessorySessionListener commsAccessorySessionListener;
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    DeviceMetadataStoreRegistrar deviceMetadataStoreRegistrar;
    @Inject
    EventBus eventBus;
    private LinearLayout headerBar;
    private TextView headerTitle;
    private ImageView mBackButton;
    private boolean mNavigateForward = false;
    private Button mSkipButton;
    @Inject
    MessagingControllerContextProvider mcContextProvider;
    @Inject
    MessagingControllerManager messagingControllerManager;
    @Inject
    OOBEPersonManager oobePersonManager;
    @Inject
    OobeService oobeService;
    private SharedPreferences settings;
    private Runnable signOutHandler;
    private OOBEStateMachine stateMachine;
    @Inject
    ThemingUpdateUtil themingUpdateUtil;
    private Person user;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, OOBEActivity.class);
    private static final Bundle fragmentStates = new Bundle();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.deecomms.oobe.OOBEActivity$10  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass10 extends Thread {
        AnonymousClass10() {
        }

        public /* synthetic */ void lambda$run$0$OOBEActivity$10(UserInfo userInfo) {
            if (userInfo != null) {
                try {
                    OOBEActivity.this.user.hashedCommsId = userInfo.getHashedCommsId();
                    OOBEActivity.this.user.homeGroupId = userInfo.getHomeGroupId();
                    OOBEActivity.this.user.aor = userInfo.getAor();
                    OOBEActivity.this.user.commsProvisionStatus = userInfo.getCommsProvisionStatus();
                    OOBEActivity.this.commitCommsUser(OOBEActivity.this.user, "setCommsIdentityForAutoprovisioned");
                } catch (InvalidCommsIdentityException unused) {
                    OOBEActivity.LOG.w("incomplete UserInfo returned for auto-provisioned user");
                }
            }
            OOBEActivity.this.finishProcessingSkip();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            OOBEActivity oOBEActivity = OOBEActivity.this;
            final UserInfo commsUserInfo = oOBEActivity.getCommsUserInfo(oOBEActivity.user.directedId, "setCommsIdentityForAutoprovisioned");
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.deecomms.oobe.-$$Lambda$OOBEActivity$10$hJ1b0BM8q_SV7wBPnAKo4YJsItE
                @Override // java.lang.Runnable
                public final void run() {
                    OOBEActivity.AnonymousClass10.this.lambda$run$0$OOBEActivity$10(commsUserInfo);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.deecomms.oobe.OOBEActivity$11  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass11 implements Runnable {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ VerificationFailure val$verificationFailure;

        AnonymousClass11(Activity activity, VerificationFailure verificationFailure) {
            this.val$activity = activity;
            this.val$verificationFailure = verificationFailure;
        }

        public /* synthetic */ void lambda$run$0$OOBEActivity$11(Activity activity, DialogInterface dialogInterface, int i) {
            LogsUtil.saveLogsAndSendViaEmail(activity);
            OOBEActivity.this.finishWithError();
        }

        public /* synthetic */ void lambda$run$1$OOBEActivity$11(Activity activity, DialogInterface dialogInterface, int i) {
            LogsUtil.saveLogsAndSendViaEmail(activity);
            OOBEActivity.this.finishWithError();
        }

        @Override // java.lang.Runnable
        public void run() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.val$activity);
            if (this.val$verificationFailure.getErrorClass() == CVFResult.NETWORK_DISCONNECTED) {
                MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_OFFLINE, MetricKeys.SCREEN_NAME_OOBE, this.val$verificationFailure.getAlertSource());
                builder.setMessage(R.string.oobe_connectivity_error);
                builder.setNeutralButton(R.string.oobe_close_error_dialog, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.OOBEActivity.11.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OOBEActivity.this.finishWithError();
                    }
                });
                if (OOBEActivity.this.capabilitiesManager.getDiagnosticsValue()) {
                    String string = OOBEActivity.this.getResources().getString(R.string.send_logs);
                    final Activity activity = this.val$activity;
                    builder.setPositiveButton(string, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.-$$Lambda$OOBEActivity$11$qBKRO2w754-5bXqZeUimbzE4z1U
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            OOBEActivity.AnonymousClass11.this.lambda$run$0$OOBEActivity$11(activity, dialogInterface, i);
                        }
                    });
                }
            } else {
                MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_PHONE_INVALID, MetricKeys.SCREEN_NAME_OOBE, this.val$verificationFailure.getAlertSource());
                builder.setMessage(OOBEActivity.this.getString(R.string.oobe_phone_verification_failure));
                builder.setPositiveButton(R.string.oobe_retry_dialog_button, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.OOBEActivity.11.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OOBEActivity.this.updateFragment();
                    }
                });
                builder.setNegativeButton(R.string.oobe_skip_dialog_button, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.OOBEActivity.11.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OOBEActivity.this.finishWithError();
                    }
                });
                if (OOBEActivity.this.capabilitiesManager.getDiagnosticsValue()) {
                    String string2 = OOBEActivity.this.getResources().getString(R.string.send_logs);
                    final Activity activity2 = this.val$activity;
                    builder.setNeutralButton(string2, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.-$$Lambda$OOBEActivity$11$V_CEUbUoBsIm76GJ0L4OuPqz0Mw
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            OOBEActivity.AnonymousClass11.this.lambda$run$1$OOBEActivity$11(activity2, dialogInterface, i);
                        }
                    });
                }
            }
            AlertDialog create = builder.create();
            create.setCanceledOnTouchOutside(false);
            create.setCancelable(false);
            create.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.deecomms.oobe.OOBEActivity$12  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass12 implements Runnable {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ AlertSource val$alertSource;
        final /* synthetic */ String val$pageSource;
        final /* synthetic */ Throwable val$t;

        AnonymousClass12(String str, Activity activity, Throwable th, AlertSource alertSource) {
            this.val$pageSource = str;
            this.val$activity = activity;
            this.val$t = th;
            this.val$alertSource = alertSource;
        }

        public /* synthetic */ void lambda$run$0$OOBEActivity$12(Activity activity, DialogInterface dialogInterface, int i) {
            LogsUtil.saveLogsAndSendViaEmail(activity);
            OOBEActivity.this.finishWithError();
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = this.val$pageSource;
            if (TextUtils.isEmpty(str)) {
                str = MetricKeys.SCREEN_NAME_OOBE;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this.val$activity);
            Throwable th = this.val$t;
            if (th != null && ((th.getCause() instanceof UnknownHostException) || (this.val$t.getCause() instanceof SocketTimeoutException))) {
                MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_OFFLINE, str, this.val$alertSource);
                builder.setMessage(R.string.oobe_connectivity_error);
            } else {
                Throwable th2 = this.val$t;
                if (th2 != null && (th2 instanceof ServiceException)) {
                    ServiceException serviceException = (ServiceException) th2;
                    Bundle bundle = new Bundle();
                    HashMap hashMap = new HashMap();
                    if (serviceException.getHttpResponseCode() != null) {
                        bundle.putInt("statusCode", serviceException.getHttpResponseCode().intValue());
                        hashMap.put("statusCode", serviceException.getHttpResponseCode());
                    }
                    if (serviceException.getRequestId() != null) {
                        bundle.putString("requestId", serviceException.getRequestId());
                        hashMap.put("requestId", serviceException.getRequestId());
                    }
                    MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, OOBEActivity.this.getOOBEErrorMetricKey(str), 1.0d, hashMap);
                    MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_ERROR, str, this.val$alertSource, bundle);
                    builder.setMessage(OOBEActivity.this.getString(R.string.oobe_service_call_error));
                } else {
                    MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_ERROR, str, this.val$alertSource);
                    MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, OOBEActivity.this.getOOBEErrorMetricKey(str), 1.0d, null);
                    builder.setMessage(OOBEActivity.this.getString(R.string.oobe_service_call_error));
                }
            }
            builder.setNeutralButton(R.string.oobe_close_error_dialog, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.OOBEActivity.12.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    OOBEActivity.this.finishWithError();
                }
            });
            if (OOBEActivity.this.capabilitiesManager.getDiagnosticsValue()) {
                String string = OOBEActivity.this.getResources().getString(R.string.send_logs);
                final Activity activity = this.val$activity;
                builder.setPositiveButton(string, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.-$$Lambda$OOBEActivity$12$LnbiBs7vG-V6PpU_7PH4JrIVlOQ
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        OOBEActivity.AnonymousClass12.this.lambda$run$0$OOBEActivity$12(activity, dialogInterface, i);
                    }
                });
            }
            AlertDialog create = builder.create();
            create.setCanceledOnTouchOutside(false);
            create.setCancelable(false);
            create.show();
        }
    }

    /* renamed from: com.amazon.deecomms.oobe.OOBEActivity$18  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass18 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$oobe$OOBEStateMachine$STATE = new int[OOBEStateMachine.STATE.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$oobe$OOBEStateMachine$STATE[OOBEStateMachine.STATE.INTRODUCTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$oobe$OOBEStateMachine$STATE[OOBEStateMachine.STATE.FNF_TOU.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$oobe$OOBEStateMachine$STATE[OOBEStateMachine.STATE.USER_SELECTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$oobe$OOBEStateMachine$STATE[OOBEStateMachine.STATE.NAME_CONFIRMATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$oobe$OOBEStateMachine$STATE[OOBEStateMachine.STATE.NEW_PROFILE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$oobe$OOBEStateMachine$STATE[OOBEStateMachine.STATE.PERMISSIONS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$oobe$OOBEStateMachine$STATE[OOBEStateMachine.STATE.ACCESSORY_PERMISSIONS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$oobe$OOBEStateMachine$STATE[OOBEStateMachine.STATE.MOBILE_VERIFICATION.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$oobe$OOBEStateMachine$STATE[OOBEStateMachine.STATE.PHONE_VERIFIED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$oobe$OOBEStateMachine$STATE[OOBEStateMachine.STATE.FINISH.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.deecomms.oobe.OOBEActivity$9  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass9 extends AsyncTask<String, Void, UserInfo> {
        ProgressDialog progressDialog;
        final /* synthetic */ Person val$user;

        AnonymousClass9(Person person) {
            this.val$user = person;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            OOBEActivity.this.runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.oobe.OOBEActivity.9.1
                @Override // java.lang.Runnable
                public void run() {
                    AnonymousClass9 anonymousClass9 = AnonymousClass9.this;
                    OOBEActivity oOBEActivity = OOBEActivity.this;
                    anonymousClass9.progressDialog = oOBEActivity.newProgressDialog(oOBEActivity.getString(R.string.oobe_signing_in));
                    AnonymousClass9.this.progressDialog.show();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public UserInfo doInBackground(String... strArr) {
            return OOBEActivity.this.getCommsUserInfo(strArr[0], "OOBEActivity finish");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(@Nullable UserInfo userInfo) {
            if (userInfo == null || TextUtils.isEmpty(userInfo.getHomeGroupId()) || TextUtils.isEmpty(userInfo.getHashedCommsId())) {
                OOBEActivity.LOG.e("Failed to fetch home group id or hashed comms id. Aborting OOBE.");
                OOBEActivity.this.dismissProgressDialog(this.progressDialog);
                OOBEActivity.this.finishWithError();
                return;
            }
            try {
                this.val$user.hashedCommsId = userInfo.getHashedCommsId();
                this.val$user.homeGroupId = userInfo.getHomeGroupId();
                this.val$user.aor = userInfo.getAor();
                this.val$user.commsProvisionStatus = userInfo.getCommsProvisionStatus();
                OOBEActivity.this.commitCommsUser(this.val$user, "OOBEActivity finish");
                OOBEActivity.this.resetRegisteredWithDMDS();
                OOBEActivity.this.deviceMetadataStoreRegistrar.registerDeviceAndCommsIdWithDMDS("OOBE finished");
                OOBEActivity.this.clearState();
                OOBEActivity.this.dismissProgressDialog(this.progressDialog);
                MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_FINISH);
                Intent intent = OOBEActivity.this.getIntent();
                if (intent != null) {
                    OOBEActivity.this.setResult(-1, intent);
                } else {
                    OOBEActivity.this.setResult(-1);
                }
                OOBEActivity.this.sendOOBECompleteNotification();
                OOBEActivity.this.oobeService.sendOOBECompletionEvent();
                OOBEActivity.this.finish();
            } catch (InvalidCommsIdentityException e) {
                OOBEActivity.LOG.e("AutoProvision API success -- but failed to save the commsUser", e);
                OOBEActivity.this.finishWithError();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public interface ChildPromptHandler {
        void cancel();

        void signOut();
    }

    public OOBEActivity() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishAndSignOut() {
        clearState();
        this.oobeService.setSkippedCommsOobe(false);
        setResult(2);
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_CHILD_SIGN_OUT);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getOOBEErrorMetricKey(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == -1543954959) {
            if (str.equals(MetricKeys.SCREEN_NAME_OOBE_USER_SELECTION)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -1488033299) {
            if (hashCode == 253278320 && str.equals(MetricKeys.SCREEN_NAME_OOBE_NAME_CONFIRMATION)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals(MetricKeys.SCREEN_NAME_OOBE_MOBILE_VERIFICATION)) {
                c = 1;
            }
            c = 65535;
        }
        return c != 0 ? c != 1 ? c != 2 ? MetricKeys.OOBE_ERROR : MetricKeys.OOBE_ERROR_NAME_CONFIRMATION : MetricKeys.OOBE_ERROR_MOBILE_VERIFICATION : MetricKeys.OOBE_ERROR_USER_SELECTION;
    }

    private AlertDialog.Builder getThemedBuilder() {
        if (!this.themingUpdateUtil.isLightMode(this)) {
            return new AlertDialog.Builder(this, 16974545);
        }
        return new AlertDialog.Builder(this);
    }

    private void initailizeDeprovisionedUserState(boolean z, boolean z2) {
        if (!z || !z2) {
            return;
        }
        if (TextUtils.isEmpty(this.user.phoneNumber)) {
            LOG.i("Deprovisioned user without a phone number");
            this.stateMachine.setCurrentState(OOBEStateMachine.STATE.MOBILE_VERIFICATION);
            return;
        }
        LOG.i("Deprovisioned user with a phone number");
        this.stateMachine.setCurrentState(OOBEStateMachine.STATE.PHONE_VERIFIED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showUnprovisionedUserDialog$0(DialogInterface dialogInterface, int i) {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACTS_OOBE_POPUP_PROCEED);
        dialogInterface.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetRegisteredWithDMDS() {
        this.settings.edit().remove(Constants.DEVICE_REGISTERED_WITH_DMDS).apply();
    }

    private void saveOOBEState() {
        saveUser(this.user);
        this.settings.edit().putInt(Constants.OOBE_STATE, getSaveState()).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCommsIdentityForAutoprovisioned() {
        new AnonymousClass10().start();
    }

    private void setFragment(OOBEStateMachine.STATE state) {
        NameConfirmationFragment nameConfirmationFragment;
        switch (state.ordinal()) {
            case 0:
                nameConfirmationFragment = new IntroductionFragment();
                break;
            case 1:
                nameConfirmationFragment = new TermsFragment();
                break;
            case 2:
                if (Utils.isFireOS()) {
                    nameConfirmationFragment = new SSOUserSelectionFragment();
                    break;
                } else {
                    nameConfirmationFragment = new UserSelectionFragment();
                    break;
                }
            case 3:
                NameConfirmationFragment nameConfirmationFragment2 = new NameConfirmationFragment();
                nameConfirmationFragment2.setOobeState(OOBEStateMachine.STATE.NAME_CONFIRMATION);
                nameConfirmationFragment = nameConfirmationFragment2;
                break;
            case 4:
                NameConfirmationFragment nameConfirmationFragment3 = new NameConfirmationFragment();
                nameConfirmationFragment3.setOobeState(OOBEStateMachine.STATE.NEW_PROFILE);
                nameConfirmationFragment = nameConfirmationFragment3;
                break;
            case 5:
                nameConfirmationFragment = new PermissionsFragment();
                break;
            case 6:
                nameConfirmationFragment = new AccessoryPermissionsFragment();
                break;
            case 7:
                nameConfirmationFragment = new CVFFragment();
                break;
            case 8:
                nameConfirmationFragment = new PhoneVerifiedFragment();
                break;
            case 9:
                finish(this.user);
                return;
            default:
                nameConfirmationFragment = new IntroductionFragment();
                break;
        }
        nameConfirmationFragment.loadState(fragmentStates);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.oobe_fragment_container, nameConfirmationFragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commitAllowingStateLoss();
        nameConfirmationFragment.recordOobePageStartMetric();
        this.applicationManager.loadingCompleteOobe();
    }

    private void showChildConfirmationDialog(Person person, final ChildPromptHandler childPromptHandler) {
        AlertDialog.Builder themedBuilder = getThemedBuilder();
        themedBuilder.setTitle(R.string.oobe_child_confirm_title);
        themedBuilder.setMessage(String.format(getString(R.string.oobe_child_confirm_message), person.firstName));
        themedBuilder.setCancelable(false);
        themedBuilder.setPositiveButton(R.string.confirmation_dialog_positive_response, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.OOBEActivity.15
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                OOBEActivity.this.showChildSignOutDialog(childPromptHandler);
            }
        });
        themedBuilder.setNegativeButton(R.string.confirmation_dialog_negative_response, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.OOBEActivity.16
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                childPromptHandler.cancel();
            }
        });
        themedBuilder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showChildDialog(Person person) {
        showChildConfirmationDialog(person, new ChildPromptHandler() { // from class: com.amazon.deecomms.oobe.OOBEActivity.14
            @Override // com.amazon.deecomms.oobe.OOBEActivity.ChildPromptHandler
            public void cancel() {
            }

            @Override // com.amazon.deecomms.oobe.OOBEActivity.ChildPromptHandler
            public void signOut() {
                OOBEActivity.this.signOutHandler.run();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showChildSignOutDialog(final ChildPromptHandler childPromptHandler) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.error_title);
        builder.setMessage(R.string.oobe_child_logout_message);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.oobe_child_logout_button, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.OOBEActivity.17
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                childPromptHandler.signOut();
            }
        });
        builder.create().show();
    }

    private void showDeprovisionedUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.oobe_reregister_message));
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.oobe_register_dialog_button, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.-$$Lambda$OOBEActivity$9CtprayJzWl3F9UR1CWQt9N0l6c
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                OOBEActivity.this.lambda$showDeprovisionedUserDialog$2$OOBEActivity(dialogInterface, i);
            }
        });
        builder.setNegativeButton(R.string.oobe_cancel_dialog_button, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.-$$Lambda$OOBEActivity$85UdntddjPif6naowwO4HLj5e2I
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                OOBEActivity.this.lambda$showDeprovisionedUserDialog$3$OOBEActivity(dialogInterface, i);
            }
        });
        builder.create().show();
    }

    private void showUnprovisionedUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.oobe_contacts_message)).setTitle(getString(R.string.oobe_setup_comms)).setCancelable(false).setPositiveButton(R.string.oobe_proceed_dialog_button, $$Lambda$OOBEActivity$uTrQYRhzlVTm6s_M6njoEk8ORNY.INSTANCE).setNegativeButton(R.string.oobe_cancel_dialog_button, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.oobe.-$$Lambda$OOBEActivity$OA_FCzloQ5brWQBK3mNIVIqXl7I
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                OOBEActivity.this.lambda$showUnprovisionedUserDialog$1$OOBEActivity(dialogInterface, i);
            }
        });
        AlertDialog create = builder.create();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACTS_OOBE_POPUP_SHOWN);
        create.show();
    }

    @VisibleForTesting
    boolean autoProvisionCommsId() {
        CommsProvisionStatus provisionStatus = this.commsIdentityManager.getProvisionStatus(true, "OOBEActivity.autoProvisionCommsId", false);
        if (provisionStatus != null && !CommsProvisionStatus.AUTO_PROVISIONED.equals(provisionStatus) && !CommsProvisionStatus.DEPROVISIONED.equals(provisionStatus)) {
            createAutoProvisionCommsIdTask(this.user).execute(new Void[0]);
            return true;
        }
        CommsLogger commsLogger = LOG;
        commsLogger.i("[OOBE-AUTOPROVISION] Skipping request to auto provision due to current status: " + provisionStatus);
        try {
            commitCommsUser(this.user, "autoProvisionCommsId");
        } catch (InvalidCommsIdentityException e) {
            LOG.e("Failed to commit comms user", e);
        }
        return false;
    }

    protected void clearState() {
        this.settings.edit().remove(Constants.OOBE_STATE).apply();
        fragmentStates.clear();
    }

    @VisibleForTesting
    void commitCommsUser(Person person, @NonNull String str) throws InvalidCommsIdentityException {
        saveUser(person);
        CommsIdentity commsIdentity = new CommsIdentity();
        String str2 = person.commsId;
        if (TextUtils.isEmpty(str2)) {
            LOG.i("Null comms id after oobe");
            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.COMMSID_NULL, Settings.OOBE);
        } else {
            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.COMMSID, Settings.OOBE);
        }
        commsIdentity.setCommsId(str2, str, false);
        commsIdentity.setHashedCommsId(person.hashedCommsId);
        commsIdentity.setDirectedId(person.directedId, str, false);
        commsIdentity.setFirstName(person.firstName);
        commsIdentity.setLastName(person.lastName);
        commsIdentity.setHomeGroupId(person.homeGroupId);
        commsIdentity.setAor(person.aor);
        commsIdentity.setProvisioningStatus(person.commsProvisionStatus, str, false);
        if (TextUtils.isEmpty(person.phoneCountryCode)) {
            commsIdentity.setPhoneNumber(person.phoneNumber);
        } else {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("+");
            outline1.append(person.phoneCountryCode);
            outline1.append(person.phoneNumber);
            commsIdentity.setPhoneNumber(outline1.toString());
        }
        this.commsIdentityManager.setCurrentUser(commsIdentity);
        this.commsIdentityManager.onCurrentUserUpdated();
    }

    @VisibleForTesting
    AsyncTask<Void, Void, Void> createAutoProvisionCommsIdTask(final Person person) {
        return new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.oobe.OOBEActivity.8
            @Override // android.os.AsyncTask
            protected void onPreExecute() {
                super.onPreExecute();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                ACMSClient aCMSClient = new ACMSClient(MetricKeys.OP_GET_IDENTITY_V2);
                try {
                    ObjectNode mo7041createObjectNode = new ObjectMapper().mo7041createObjectNode();
                    mo7041createObjectNode.put("autoProvision", true);
                    OOBEActivity.LOG.i("[OOBE-AUTOPROVISION] Making request to auto provision");
                    IHttpClient.Response mo3640execute = aCMSClient.request(MessageFormat.format(AppUrl.OOBE_PROVISION_COMMSID, person.directedId)).authenticated(person.directedId).postJson(mo7041createObjectNode).mo3640execute();
                    String body = mo3640execute.getBody();
                    if (mo3640execute.isSuccessful()) {
                        JSONObject jSONObject = new JSONObject(body);
                        person.directedId = jSONObject.getString("directedId");
                        person.commsId = jSONObject.getString("commsId");
                        person.commsProvisionStatus = CommsProvisionStatus.AUTO_PROVISIONED;
                        OOBEActivity.this.persistProvisionStatus(person.commsProvisionStatus);
                        OOBEActivity.this.saveUser(person);
                        OOBEActivity.this.commitCommsUser(person, "createAutoProvisionCommsIdTask");
                        CommsLogger commsLogger = OOBEActivity.LOG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("[OOBE-AUTOPROVISION] CVF Skip created auto provisioned user account with directedId ");
                        sb.append(OOBEActivity.LOG.sensitive(person.directedId));
                        sb.append(" and commsId ");
                        sb.append(OOBEActivity.LOG.sensitive(person.commsId));
                        commsLogger.i(sb.toString());
                        MetricsHelper.recordSingleOccurrence(CounterMetric.generateOperational(MetricKeys.OOBE_AUTOPROVISIONED_SUCCESS));
                        return null;
                    }
                    throw new IllegalStateException("The response returned was invalid.");
                } catch (Exception e) {
                    OOBEActivity.LOG.e("Error occurred during call to auto provision.", e);
                    CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.OOBE_AUTOPROVISIONED_ERROR);
                    generateOperational.getMetadata().put("source", e.getMessage());
                    MetricsHelper.recordSingleOccurrence(generateOperational);
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r1) {
                super.onPostExecute((AnonymousClass8) r1);
                OOBEActivity.this.setCommsIdentityForAutoprovisioned();
            }
        };
    }

    public void dismissProgressDialog(ProgressDialog progressDialog) {
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }
        progressDialog.dismiss();
    }

    @VisibleForTesting
    void finish(Person person) {
        this.oobeService.setSkippedCommsOobe(false);
        new AnonymousClass9(person).execute(person.directedId);
    }

    @VisibleForTesting
    void finishAsCancelled() {
        CommsProvisionStatus provisionStatus = this.commsIdentityManager.getProvisionStatus(true, "OOBEActivity.finishAsCancelled", false);
        if (!this.capabilitiesManager.isAutoProvisioningEnabled() || CommsProvisionStatus.DEPROVISIONED.equals(provisionStatus) || !autoProvisionCommsId()) {
            if (this.capabilitiesManager.isAutoProvisioningEnabled() && CommsProvisionStatus.AUTO_PROVISIONED.equals(provisionStatus)) {
                setCommsIdentityForAutoprovisioned();
            } else {
                finishProcessingSkip();
            }
        }
    }

    public void finishForDeprovisionedUser() {
        finish(3, MetricKeys.OOBE_DEPROVISIONED_USER_DIALOG_CANCEL, true);
    }

    public void finishForUnprovisionedUser() {
        finish(4, MetricKeys.CONTACTS_OOBE_POPUP_CANCEL, true);
    }

    void finishProcessingSkip() {
        this.oobeService.sendOOBECompletionEvent();
        fragmentStates.clear();
        saveOOBEState();
        this.oobeService.setSkippedCommsOobe(true);
        this.mSkipButton.setEnabled(true);
        Intent intent = getIntent();
        if (intent != null) {
            setResult(0, intent);
        } else {
            setResult(0);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("source", getCurrentState().name());
        MetricsHelper.recordCounterMetric(CommsMetric.MetricType.ClickStream, MetricKeys.OOBE_SKIP, 1.0d, hashMap);
        finish();
    }

    void finishWithError() {
        clearState();
        this.oobeService.setSkippedCommsOobe(false);
        setResult(1);
        finish();
    }

    @Nullable
    @VisibleForTesting
    UserInfo getCommsUserInfo(String str, String str2) {
        ACMSClient aCMSClient = new ACMSClient(MetricKeys.OP_AUTO_PROV_COMMS_USER_FROM_OOBE);
        try {
            CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.OP_GET_IDENTITY_V2);
            generateOperational.getMetadata().put("source", str2);
            MetricsHelper.recordSingleOccurrence(generateOperational);
            return (UserInfo) aCMSClient.request(MessageFormat.format("/users/{0}/identities", this.user.commsId)).authenticated(str).get().mo3640execute().convert(UserInfo.class);
        } catch (ServiceException e) {
            LOG.e("Service Error occurred while fetching UserInfo.", e);
            return null;
        }
    }

    protected OOBEStateMachine.STATE getCurrentState() {
        return this.stateMachine.getState();
    }

    public PCAProfile getPCAProfile() {
        return PCAProfile.fromPrefs(this.settings);
    }

    public int getSaveState() {
        int ordinal = getCurrentState().ordinal();
        if (ordinal != 2) {
            if (ordinal != 3) {
                return getCurrentState().order;
            }
            return OOBEStateMachine.STATE.NAME_CONFIRMATION.order;
        }
        return OOBEStateMachine.STATE.USER_SELECTION.order;
    }

    public Person getUser() {
        if (this.user == null) {
            retrieveUser();
        }
        return this.user;
    }

    public void goBack() {
        if (getCurrentState() == OOBEStateMachine.STATE.NAME_CONFIRMATION || getCurrentState() == OOBEStateMachine.STATE.NEW_PROFILE) {
            this.stateMachine.prev();
            updateFragment();
        }
    }

    public void hideBackButton() {
        runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.oobe.OOBEActivity.5
            @Override // java.lang.Runnable
            public void run() {
                OOBEActivity.this.mBackButton.setVisibility(4);
            }
        });
    }

    public void hideHeaderBar() {
        runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.oobe.OOBEActivity.3
            @Override // java.lang.Runnable
            public void run() {
                OOBEActivity.this.headerBar.setVisibility(8);
            }
        });
    }

    public void hideSkipButton() {
        runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.oobe.OOBEActivity.7
            @Override // java.lang.Runnable
            public void run() {
                OOBEActivity.this.mSkipButton.setVisibility(4);
            }
        });
    }

    public /* synthetic */ void lambda$onRequestPermissionsResult$4$OOBEActivity() {
        LOG.i("Starting SMS Monitor in OOBE workflow.");
        this.messagingControllerManager.newAndStartSMSReceiveManager();
    }

    public /* synthetic */ void lambda$showDeprovisionedUserDialog$2$OOBEActivity(DialogInterface dialogInterface, int i) {
        this.settings.edit().putBoolean(Constants.OOBE_DEPROVISIONED_DIALOG_ACCEPTED, true).apply();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_DEPROVISIONED_USER_DIALOG_REGISTER);
        dialogInterface.dismiss();
    }

    public /* synthetic */ void lambda$showDeprovisionedUserDialog$3$OOBEActivity(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        finishForDeprovisionedUser();
    }

    public /* synthetic */ void lambda$showUnprovisionedUserDialog$1$OOBEActivity(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        finishForUnprovisionedUser();
    }

    public ProgressDialog newProgressDialog(@Nullable String str) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        if (!TextUtils.isEmpty(str)) {
            progressDialog.setMessage(str);
        }
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public void nextFragment() {
        String str;
        int ordinal = this.stateMachine.getState().ordinal();
        String str2 = MetricKeys.OOBE_ERROR_PERMISSIONS;
        if (ordinal != 0) {
            switch (ordinal) {
                case 2:
                    str = MetricKeys.OOBE_PAGE_USER_SELECTION_COMPLETE;
                    str2 = MetricKeys.OOBE_ERROR_USER_SELECTION;
                    break;
                case 3:
                    str = MetricKeys.OOBE_PAGE_NAME_CONFIRMATION_COMPLETE;
                    str2 = MetricKeys.OOBE_ERROR_NAME_CONFIRMATION;
                    break;
                case 4:
                    str = MetricKeys.OOBE_PAGE_NEW_PROFILE_COMPLETE;
                    str2 = MetricKeys.OOBE_ERROR_NEW_PROFILE;
                    break;
                case 5:
                    str = MetricKeys.OOBE_PAGE_PERM_CONTACT_IMPORT_COMPLETE;
                    break;
                case 6:
                    str = MetricKeys.OOBE_PAGE_PERM_ACCESSORY_COMPLETE;
                    break;
                case 7:
                    str = MetricKeys.OOBE_PAGE_MOBILE_VERIFICATION_COMPLETE;
                    str2 = MetricKeys.OOBE_ERROR_MOBILE_VERIFICATION;
                    break;
                case 8:
                    str = MetricKeys.OOBE_PAGE_PHONE_VERIFIED_COMPLETE;
                    str2 = MetricKeys.OOBE_ERROR_PHONE_VERIFIED;
                    break;
                default:
                    str2 = MetricKeys.OOBE_ERROR;
                    str = null;
                    break;
            }
        } else {
            str = MetricKeys.OOBE_PAGE_INTRODUCTION_COMPLETE;
            str2 = MetricKeys.OOBE_ERROR_INTRO;
        }
        MetricsHelper.recordSingleOccurrenceClickstream(str);
        MetricsHelper.recordCounterMetric(CommsMetric.MetricType.Operational, str2, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, null);
        this.stateMachine.next(this.user);
        if (this.stateMachine.getState() == OOBEStateMachine.STATE.FINISH) {
            finish(this.user);
        } else {
            updateFragment();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        goBack();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        Bundle extras;
        CommsDaggerWrapper.getComponent().inject(this);
        this.settings = this.oobePersonManager.getOOBESharedPreferences();
        super.onCreate(bundle);
        boolean z = true;
        if (DeviceInfo.isPhone(getApplicationContext())) {
            setRequestedOrientation(1);
        }
        this.signOutHandler = new Runnable() { // from class: com.amazon.deecomms.oobe.-$$Lambda$OOBEActivity$LtDl33GiOdfF0ttqggTI9eAs31A
            @Override // java.lang.Runnable
            public final void run() {
                OOBEActivity.this.finishAndSignOut();
            }
        };
        Intent intent = getIntent();
        this.oobeService.setCommsOobeInProgress(true);
        String stringExtra = intent != null ? intent.getStringExtra(OOBE_INTENT) : null;
        HashMap hashMap = new HashMap();
        hashMap.put("source", stringExtra);
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_START, hashMap);
        MetricsHelper.recordDevicesCodecInfo();
        if (this.themingUpdateUtil.isMosaicThemingEnabled()) {
            ThemeUtil.setTheme(getApplicationContext());
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().getDecorView().setSystemUiVisibility(this.themingUpdateUtil.getSystemUiVisibility(getApplicationContext()));
            getWindow().setStatusBarColor(this.themingUpdateUtil.getColorFromAttribute(getApplicationContext(), R.attr.mosaicBackground));
        }
        setContentView(R.layout.fiesta_oobe_main);
        this.themingUpdateUtil.applyBackgroundColorToView(findViewById(R.id.fiesta_oobe_main), getApplicationContext(), R.attr.mosaicBackground);
        retrieveUser();
        this.stateMachine = new OOBEStateMachine(this.settings.getInt(Constants.OOBE_STATE, 0));
        this.mBackButton = (ImageView) findViewById(R.id.oobe_back_button);
        this.themingUpdateUtil.applyTintColorToImageView(this.mBackButton, getApplicationContext(), R.attr.mosaicNeutral10);
        this.mBackButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.oobe.OOBEActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OOBEActivity.this.onBackPressed();
            }
        });
        this.mSkipButton = (Button) findViewById(R.id.oobe_skip_button);
        this.headerBar = (LinearLayout) findViewById(R.id.oobe_header);
        this.themingUpdateUtil.applyBackgroundColorToView(this.headerBar, getApplicationContext(), R.attr.mosaicBackground);
        this.headerTitle = (TextView) findViewById(R.id.oobe_header_title);
        this.themingUpdateUtil.applyColorToTextView(this.headerTitle, getApplicationContext(), R.attr.mosaicNeutral10);
        this.themingUpdateUtil.applyColorToTextView((TextView) findViewById(R.id.oobe_skip_button), getApplicationContext(), R.attr.mosaicNeutral10);
        boolean equals = CommsProvisionStatus.DEPROVISIONED.equals(this.commsIdentityManager.getProvisionStatus(true, "OOBEActivity.onCreate", false));
        boolean equals2 = CommsView.ContactList.name().equals(stringExtra);
        boolean z2 = this.settings.getBoolean(Constants.OOBE_DEPROVISIONED_DIALOG_ACCEPTED, false);
        if (!equals2 && z2) {
            z = false;
        }
        initailizeDeprovisionedUserState(equals, z);
        if (equals2) {
            showUnprovisionedUserDialog();
        } else if (equals && !z2) {
            updateFragment();
            showDeprovisionedUserDialog();
        }
        setHeaderTitle(getResources().getString(R.string.oobe_setup_header));
        if (bundle == null) {
            if (intent != null && (intent.getBooleanExtra(GO_TO_USER_SELECTION, false) || getCurrentState() == OOBEStateMachine.STATE.INTRODUCTION)) {
                this.stateMachine.setCurrentState(OOBEStateMachine.STATE.USER_SELECTION);
            }
            updateFragment();
        }
        if (intent == null || (extras = intent.getExtras()) == null || !Constants.ACCESSORY_OOBE.equals(extras.getString(Constants.OOBE_STARTED_FROM)) || !this.user.isCommsProvisioned) {
            return;
        }
        this.stateMachine.setCurrentState(OOBEStateMachine.STATE.ACCESSORY_PERMISSIONS);
        updateFragment();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.oobeService.setCommsOobeInProgress(false);
    }

    public void onError(@Nullable Throwable th, @Nullable String str, @Nullable AlertSource alertSource) {
        runOnUiThread(new AnonymousClass12(str, this, th, alertSource));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        if (this.mNavigateForward) {
            this.mNavigateForward = false;
            nextFragment();
        }
    }

    public void onProvisioned(String str) {
        this.user.isCommsProvisioned = true;
        LOG.d("onVerifiedIntentity reached successfully, user.isCommsProvisioned now set to true");
        this.user.commsId = str;
        this.settings.edit().remove(Constants.OOBE_DEPROVISIONED_DIALOG_ACCEPTED).putString(Constants.OOBE_USER_PROVISION_STATUS, CommsProvisionStatus.PROVISIONED.name()).apply();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        CounterMetric generateClickstream;
        CounterMetric generateClickstream2;
        if (i == 134) {
            if (iArr.length > 0 && iArr[0] == 0) {
                LOG.i("Granted permission for contacts");
                generateClickstream2 = CounterMetric.generateClickstream(MetricKeys.PERMS_CONTACTS_ALLOW);
            } else {
                LOG.i("Not granted permission for contacts");
                generateClickstream2 = CounterMetric.generateClickstream(MetricKeys.PERMS_CONTACTS_DENY);
            }
            generateClickstream2.getMetadata().put("source", MetricKeys.PERMS_SOURCE_OOBE_PERMS);
            MetricsHelper.recordSingleOccurrence(generateClickstream2);
        }
        if (i == 334) {
            if (iArr.length > 0 && iArr[0] == 0) {
                generateClickstream = CounterMetric.generateClickstream(MetricKeys.PERMS_SEND_SMS_ALLOW);
                if (this.capabilitiesManager.isMessagingControllerFeaturesEnabled() && CommsDaggerWrapper.getComponent().getCommsAccessorySessionListener().isAnyAccessoryConnected()) {
                    this.mcContextProvider.updateContext(new MessagingControllerContextProvider.ContextUpdated() { // from class: com.amazon.deecomms.oobe.-$$Lambda$OOBEActivity$V3D3bmTT45QHwFU5beUMi30g0uk
                        @Override // com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerContextProvider.ContextUpdated
                        public final void onContextUpdated() {
                            OOBEActivity.this.lambda$onRequestPermissionsResult$4$OOBEActivity();
                        }
                    });
                } else {
                    LOG.i("Messaging Controller weblab is off. Do not start SMS listener.");
                }
            } else {
                generateClickstream = CounterMetric.generateClickstream(MetricKeys.PERMS_SEND_SMS_DENY);
            }
            generateClickstream.getMetadata().put("source", MetricKeys.PERMS_SOURCE_OOBE_PERMS);
            MetricsHelper.recordSingleOccurrence(generateClickstream);
        }
        this.mNavigateForward = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        saveOOBEState();
        saveFragmentStates();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        if (isFinishing()) {
            this.oobeService.setCommsOobeInProgress(false);
        }
    }

    @Override // com.amazon.deecomms.oobe.CommsIdentityReceiver
    public void onVerificationFailure(VerificationFailure verificationFailure) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Received CVF failure ");
        outline1.append(verificationFailure.getErrorClass().toString());
        commsLogger.e(outline1.toString());
        runOnUiThread(new AnonymousClass11(this, verificationFailure));
    }

    @Override // com.amazon.deecomms.oobe.CommsIdentityReceiver
    public void onVerifiedIdentity(String str, String str2, String str3) {
        this.user.phoneNumber = str3;
        onProvisioned(str2);
        saveUser(this.user);
        nextFragment();
    }

    public void persistProvisionStatus(CommsProvisionStatus commsProvisionStatus) {
        this.commsIdentityManager.setProvisionStatus(commsProvisionStatus, "OOBEActivity", false, true);
    }

    protected void retrieveUser() {
        this.user = this.oobePersonManager.getOOBEPerson();
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport.outline1("Is phone number retrieved from shared pref: "), !TextUtils.isEmpty(this.user.phoneNumber), LOG);
        this.user.isFriendsAndFamily = this.applicationManager.isFeatureEnabled(CommsFeature.FRIENDS_AND_FAMILY);
    }

    public void saveFragmentStates() {
        fragmentStates.clear();
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof MainOOBEFragment) {
                ((MainOOBEFragment) fragment).saveState(fragmentStates);
            }
        }
    }

    public void savePCAProfile(PCAProfile pCAProfile) {
        pCAProfile.toPrefs(this.settings);
    }

    public void saveUser(Person person) {
        this.user = person;
        this.oobePersonManager.saveOOBEPerson(person);
    }

    public void selectUser(@NonNull final Person person, final boolean z) {
        final String str = person.commsId;
        final CommsProvisionStatus commsProvisionStatus = person.commsProvisionStatus;
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("selected user provision status: ");
        outline1.append(commsProvisionStatus == null ? null : commsProvisionStatus.toString());
        commsLogger.i(outline1.toString());
        final boolean isPhone = DeviceInfo.isPhone(this);
        final String str2 = person.directedId;
        new AsyncTask<String, Void, Void>() { // from class: com.amazon.deecomms.oobe.OOBEActivity.13
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Removed duplicated region for block: B:16:0x0031 A[Catch: Exception -> 0x008b, TRY_LEAVE, TryCatch #0 {Exception -> 0x008b, blocks: (B:2:0x0000, B:4:0x0008, B:6:0x000e, B:11:0x001c, B:14:0x0022, B:16:0x0031, B:18:0x0037, B:19:0x003b, B:21:0x0050, B:23:0x0057, B:25:0x0079, B:27:0x0083), top: B:33:0x0000 }] */
            /* JADX WARN: Removed duplicated region for block: B:19:0x003b A[Catch: Exception -> 0x008b, TryCatch #0 {Exception -> 0x008b, blocks: (B:2:0x0000, B:4:0x0008, B:6:0x000e, B:11:0x001c, B:14:0x0022, B:16:0x0031, B:18:0x0037, B:19:0x003b, B:21:0x0050, B:23:0x0057, B:25:0x0079, B:27:0x0083), top: B:33:0x0000 }] */
            @Override // android.os.AsyncTask
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public java.lang.Void doInBackground(java.lang.String... r8) {
                /*
                    r7 = this;
                    com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus r8 = r2     // Catch: java.lang.Exception -> L8b
                    com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus r0 = com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus.PROVISIONED     // Catch: java.lang.Exception -> L8b
                    r1 = 0
                    r2 = 1
                    if (r8 == r0) goto L1b
                    com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus r8 = r2     // Catch: java.lang.Exception -> L8b
                    com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus r0 = com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus.AUTO_PROVISIONED     // Catch: java.lang.Exception -> L8b
                    if (r8 != r0) goto L19
                    com.amazon.deecomms.oobe.OOBEActivity r8 = com.amazon.deecomms.oobe.OOBEActivity.this     // Catch: java.lang.Exception -> L8b
                    com.amazon.deecomms.core.CapabilitiesManager r8 = r8.capabilitiesManager     // Catch: java.lang.Exception -> L8b
                    boolean r8 = r8.isAutoProvisioningEnabled()     // Catch: java.lang.Exception -> L8b
                    if (r8 == 0) goto L19
                    goto L1b
                L19:
                    r8 = r1
                    goto L1c
                L1b:
                    r8 = r2
                L1c:
                    boolean r0 = r3     // Catch: java.lang.Exception -> L8b
                    if (r0 != 0) goto L4f
                    if (r8 == 0) goto L4f
                    com.amazon.deecomms.common.network.acmsrecipes.GetPersonalDevices r0 = new com.amazon.deecomms.common.network.acmsrecipes.GetPersonalDevices     // Catch: java.lang.Exception -> L8b
                    r0.<init>()     // Catch: java.lang.Exception -> L8b
                    java.lang.String r3 = r4     // Catch: java.lang.Exception -> L8b
                    java.lang.String r4 = r5     // Catch: java.lang.Exception -> L8b
                    com.amazon.deecomms.contacts.model.GetPersonalDevicesResponse r0 = r0.getMasterDevice(r3, r4)     // Catch: java.lang.Exception -> L8b
                    if (r0 != 0) goto L3b
                    com.amazon.comms.log.CommsLogger r0 = com.amazon.deecomms.oobe.OOBEActivity.access$300()     // Catch: java.lang.Exception -> L8b
                    java.lang.String r3 = "Get personal devices call returned null"
                    r0.d(r3)     // Catch: java.lang.Exception -> L8b
                    goto L4f
                L3b:
                    com.amazon.deecomms.util.DeviceInfo r3 = new com.amazon.deecomms.util.DeviceInfo     // Catch: java.lang.Exception -> L8b
                    r3.<init>()     // Catch: java.lang.Exception -> L8b
                    com.amazon.deecomms.oobe.OOBEActivity r4 = com.amazon.deecomms.oobe.OOBEActivity.this     // Catch: java.lang.Exception -> L8b
                    java.lang.String r3 = r3.getUniqueDeviceId(r4)     // Catch: java.lang.Exception -> L8b
                    java.lang.String r0 = r0.getMasterDeviceId()     // Catch: java.lang.Exception -> L8b
                    boolean r0 = r3.equals(r0)     // Catch: java.lang.Exception -> L8b
                    goto L50
                L4f:
                    r0 = r2
                L50:
                    com.amazon.comms.log.CommsLogger r3 = com.amazon.deecomms.oobe.OOBEActivity.access$300()     // Catch: java.lang.Exception -> L8b
                    java.lang.String r4 = "Setting the shouldSupportContacts key to be: %b, isPhone: %b, isProvisioned: %b"
                    r5 = 3
                    java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> L8b
                    java.lang.Boolean r6 = java.lang.Boolean.valueOf(r0)     // Catch: java.lang.Exception -> L8b
                    r5[r1] = r6     // Catch: java.lang.Exception -> L8b
                    boolean r6 = r3     // Catch: java.lang.Exception -> L8b
                    java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch: java.lang.Exception -> L8b
                    r5[r2] = r6     // Catch: java.lang.Exception -> L8b
                    r2 = 2
                    java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)     // Catch: java.lang.Exception -> L8b
                    r5[r2] = r8     // Catch: java.lang.Exception -> L8b
                    java.lang.String r8 = java.lang.String.format(r4, r5)     // Catch: java.lang.Exception -> L8b
                    r3.i(r8)     // Catch: java.lang.Exception -> L8b
                    com.amazon.deecomms.oobe.OOBEActivity r8 = com.amazon.deecomms.oobe.OOBEActivity.this     // Catch: java.lang.Exception -> L8b
                    java.lang.String r2 = "SHARED_PREFS"
                    android.content.SharedPreferences r8 = r8.getSharedPreferences(r2, r1)     // Catch: java.lang.Exception -> L8b
                    android.content.SharedPreferences$Editor r8 = r8.edit()     // Catch: java.lang.Exception -> L8b
                    java.lang.String r1 = "SHOULD_SUPPORT_CONTACTS_ON_DEVICES"
                    android.content.SharedPreferences$Editor r8 = r8.putBoolean(r1, r0)     // Catch: java.lang.Exception -> L8b
                    r8.apply()     // Catch: java.lang.Exception -> L8b
                    goto La8
                L8b:
                    r8 = move-exception
                    com.amazon.deecomms.oobe.OOBEActivity r0 = com.amazon.deecomms.oobe.OOBEActivity.this
                    java.lang.String r1 = "Get Personal Devices exception "
                    java.lang.StringBuilder r1 = com.android.tools.r8.GeneratedOutlineSupport.outline1(r1)
                    java.lang.String r2 = r8.getMessage()
                    r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    com.amazon.deecomms.common.metrics.AlertSource r1 = com.amazon.deecomms.common.metrics.AlertSource.newRequestSource(r1)
                    java.lang.String r2 = "comms.screen.oobe.userSelection"
                    r0.onError(r8, r2, r1)
                La8:
                    r8 = 0
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.oobe.OOBEActivity.AnonymousClass13.doInBackground(java.lang.String[]):java.lang.Void");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(@Nullable Void r3) {
                Person person2 = person;
                if (person2.isChild && z) {
                    OOBEActivity.this.showChildDialog(person2);
                    return;
                }
                Person person3 = person;
                if (person3.commsProvisionStatus == CommsProvisionStatus.DEPROVISIONED) {
                    OOBEActivity.LOG.i("Deprovisioned user selected");
                    try {
                        OOBEActivity.this.commitCommsUser(person, "selectUser.getPersonalDevicesAsyncTask.deprovisioned");
                    } catch (InvalidCommsIdentityException e) {
                        OOBEActivity.LOG.e("InvalidCommsIdentityException for deprovisioned user", e);
                    }
                    OOBEActivity.this.getSharedPreferences("SHARED_PREFS", 0).edit().putString(Constants.OOBE_USER_PROVISION_STATUS, CommsProvisionStatus.DEPROVISIONED.name()).apply();
                    OOBEActivity.this.finishForDeprovisionedUser();
                    return;
                }
                OOBEActivity.this.saveUser(person3);
                OOBEActivity.this.nextFragment();
            }
        }.execute(new String[0]);
    }

    void sendOOBECompleteNotification() {
        CommsEventEmitterBridge.sendOOBECompleteNotification();
    }

    public void setHeaderTitle(String str) {
        try {
            if (this.headerTitle == null) {
                this.headerTitle = (TextView) findViewById(R.id.oobe_header_title);
            }
            this.headerTitle.setText(str);
        } catch (Exception e) {
            LOG.e("Error setting title", e);
        }
    }

    public void showBackButton() {
        runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.oobe.OOBEActivity.4
            @Override // java.lang.Runnable
            public void run() {
                OOBEActivity.this.mBackButton.setVisibility(0);
            }
        });
    }

    public void showHeaderBar() {
        runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.oobe.OOBEActivity.2
            @Override // java.lang.Runnable
            public void run() {
                OOBEActivity.this.headerBar.setVisibility(0);
            }
        });
    }

    public void showSkipButton() {
        runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.oobe.OOBEActivity.6
            @Override // java.lang.Runnable
            public void run() {
                OOBEActivity.this.mSkipButton.setVisibility(0);
                if (OOBEActivity.this.mSkipButton.getText().length() > 10) {
                    OOBEActivity.this.mSkipButton.setTextSize(10.0f);
                }
                OOBEActivity.this.mSkipButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.oobe.OOBEActivity.6.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        String str;
                        OOBEActivity.this.mSkipButton.setEnabled(false);
                        int ordinal = OOBEActivity.this.getCurrentState().ordinal();
                        String str2 = null;
                        if (ordinal == 7) {
                            str2 = MetricKeys.OOBE_CVF_SKIP;
                            str = MetricKeys.OOBE_PAGE_MOBILE_VERIFICATION_COMPLETE;
                        } else if (ordinal != 8) {
                            str = null;
                        } else {
                            str2 = MetricKeys.OOBE_PHONE_VERIFIED_SKIP;
                            str = MetricKeys.OOBE_PAGE_PHONE_VERIFIED_COMPLETE;
                        }
                        if (!TextUtils.isEmpty(str2)) {
                            MetricsHelper.recordSingleOccurrenceClickstream(str2);
                        }
                        if (!TextUtils.isEmpty(str)) {
                            MetricsHelper.recordSingleOccurrenceClickstream(str);
                        }
                        OOBEActivity.this.finishAsCancelled();
                    }
                });
            }
        });
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        super.startActivity(WebIntentUtils.ensurePopupIfApplicable(intent));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        super.startActivityForResult(WebIntentUtils.ensurePopupIfApplicable(intent), i);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void startActivityFromFragment(@NonNull Fragment fragment, Intent intent, int i) {
        super.startActivityFromFragment(fragment, WebIntentUtils.ensurePopupIfApplicable(intent), i);
    }

    protected void updateFragment() {
        setFragment(getCurrentState());
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent, @Nullable Bundle bundle) {
        super.startActivity(WebIntentUtils.ensurePopupIfApplicable(intent), bundle);
    }

    OOBEActivity(@NonNull OOBEStateMachine oOBEStateMachine, @NonNull Button button, @NonNull SharedPreferences sharedPreferences) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.stateMachine = oOBEStateMachine;
        this.mSkipButton = button;
        this.settings = sharedPreferences;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void startActivityForResult(Intent intent, int i, @Nullable Bundle bundle) {
        super.startActivityForResult(WebIntentUtils.ensurePopupIfApplicable(intent), i, bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void startActivityFromFragment(@NonNull Fragment fragment, Intent intent, int i, @Nullable Bundle bundle) {
        super.startActivityFromFragment(fragment, WebIntentUtils.ensurePopupIfApplicable(intent), i, bundle);
    }

    private void finish(int i, @NonNull String str, boolean z) {
        fragmentStates.clear();
        saveOOBEState();
        this.oobeService.setSkippedCommsOobe(z);
        setResult(i);
        MetricsHelper.recordSingleOccurrenceClickstream(str);
        finish();
    }
}
