package com.amazon.alexa.voice.handsfree.decider.setup;

import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.protocols.metrics.PermissionResult;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.ui.ManagedActivity;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.primer.EnrollmentTermsActivity;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.alexa.voice.handsfree.PartnerIntentResolver;
import com.amazon.alexa.voice.handsfree.R;
import com.amazon.alexa.voice.handsfree.decider.SetupFlowExecutionService;
import com.amazon.alexa.voice.handsfree.decider.StepType;
import com.amazon.alexa.voice.handsfree.decider.StepsProvider;
import com.amazon.alexa.voice.handsfree.metrics.MetricsConstants;
import com.amazon.alexa.voice.handsfree.metrics.permission.PermissionAlarmScheduler;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes11.dex */
public class SinglePermissionsActivity extends ManagedActivity implements PermissionDelegate {
    protected static final int ALEXA_PERMISSIONS_REQUEST_CODE = 2590;
    private static final int ANDROID_11_VERSION_CODE = 30;
    protected static final int HANDS_FREE_PERMISSION_REQUEST_CODE = 1590;
    private static final String PERMISSION_COUNT = "permission_count";
    private static final String SHARED_PREFS_FILE_NAME = "partner_permission";
    private static final String TAG = SinglePermissionsActivity.class.getSimpleName();
    private AMPDInformationProvider mAMPDInformationProvider;
    private MetricsConstants.PageType mCurrentPage;
    private boolean mHasPermissionInfoShown;
    private Initializer mInitializer;
    private NotificationManager mNotificationManager;
    private PackageManager mPackageManager;
    private Intent mPartnerPermissionsIntent;
    private final PermissionAlarmScheduler mPermissionAlarmScheduler;
    private PermissionMetricRecorder mPermissionMetricRecorder;
    private SharedPreferences mSharedPreferences;
    private final StepsProvider mStepsProvider;

    /* renamed from: com.amazon.alexa.voice.handsfree.decider.setup.SinglePermissionsActivity$10  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$voice$handsfree$decider$StepType = new int[StepType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$voice$handsfree$decider$StepType[StepType.ALEXA_APP_AUDIO_PERMISSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$handsfree$decider$StepType[StepType.ALEXA_APP_LOCATION_PERMISSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$handsfree$decider$StepType[StepType.HANDS_FREE_PERMISSIONS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @VisibleForTesting
    SinglePermissionsActivity(@NonNull Initializer initializer, @NonNull PermissionMetricRecorder permissionMetricRecorder, @NonNull PackageManager packageManager, @NonNull Intent intent, @NonNull PermissionAlarmScheduler permissionAlarmScheduler, @NonNull AMPDInformationProvider aMPDInformationProvider, @NonNull StepsProvider stepsProvider, @NonNull NotificationManager notificationManager, @NonNull SharedPreferences sharedPreferences) {
        this.mInitializer = initializer;
        this.mPermissionMetricRecorder = permissionMetricRecorder;
        this.mPackageManager = packageManager;
        this.mPartnerPermissionsIntent = intent;
        this.mPermissionAlarmScheduler = permissionAlarmScheduler;
        this.mAMPDInformationProvider = aMPDInformationProvider;
        this.mStepsProvider = stepsProvider;
        this.mNotificationManager = notificationManager;
        this.mSharedPreferences = sharedPreferences;
    }

    private void showManualPartnerPermissionsNeededDialog() {
        getNewAlertDialogBuilder().setMessage(getString(R.string.alexa_handsfree_partner_permission_manual_dialog_warning, new Object[]{getPartnerAppName()})).setPositiveButton(R.string.alexa_handsfree_partner_permission_manual_dialog_go_to_permissions, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.SinglePermissionsActivity.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                SinglePermissionsActivity.this.mPermissionMetricRecorder.recordClick(SinglePermissionsActivity.this.mCurrentPage, MetricsConstants.SubPageType.PARTNER_PERMISSIONS_MANUAL_PERMISSIONS_GO_TO_BUTTON);
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addFlags(268435456);
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("package:");
                outline107.append(SinglePermissionsActivity.this.mPartnerPermissionsIntent.getPackage());
                intent.setData(Uri.parse(outline107.toString()));
                SinglePermissionsActivity.this.startActivity(intent);
            }
        }).setNegativeButton(R.string.alexa_handsfree_permission_manual_dialog_close, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.SinglePermissionsActivity.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                SinglePermissionsActivity.this.mPermissionMetricRecorder.recordClick(SinglePermissionsActivity.this.mCurrentPage, MetricsConstants.SubPageType.PARTNER_PERMISSIONS_MANUAL_PERMISSIONS_CLOSE_BUTTON);
                SinglePermissionsActivity.this.finishStep(ManagedActivity.StepResult.EXIT);
            }
        }).create().show();
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionDelegate
    public void finishStepForPermission(@NonNull ManagedActivity.StepResult stepResult) {
        super.finishStep(stepResult);
    }

    @VisibleForTesting
    AlertDialog.Builder getNewAlertDialogBuilder() {
        return new AlertDialog.Builder(this, R.style.AlertDialogBackground);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0006, code lost:
        r0 = r0.getPackage();
     */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getPartnerAppName() {
        /*
            r5 = this;
            android.content.Intent r0 = r5.mPartnerPermissionsIntent
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            java.lang.String r0 = r0.getPackage()
            if (r0 != 0) goto Ld
            return r1
        Ld:
            android.content.pm.PackageManager r2 = r5.mPackageManager     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L1f
            r3 = 0
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo(r0, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L1f
            android.content.pm.PackageManager r3 = r5.mPackageManager     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L1f
            java.lang.CharSequence r2 = r2.loadLabel(r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L1f
            java.lang.String r0 = r2.toString()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L1f
            return r0
        L1f:
            java.lang.String r2 = com.amazon.alexa.voice.handsfree.decider.setup.SinglePermissionsActivity.TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "not find the application contains the package: "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.amazon.alexa.handsfree.protocols.utils.Log.e(r2, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.voice.handsfree.decider.setup.SinglePermissionsActivity.getPartnerAppName():java.lang.String");
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionDelegate
    public <T extends View> T getViewById(int i) {
        return (T) findViewById(i);
    }

    @VisibleForTesting
    boolean hasPartnerRecordAudioPermissions() {
        return hasPermission("android.permission.RECORD_AUDIO", this.mPartnerPermissionsIntent.getPackage());
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionDelegate
    @VisibleForTesting
    public boolean hasPermission(String str, String str2) {
        return this.mPackageManager.checkPermission(str, str2) == 0;
    }

    @VisibleForTesting
    boolean isAlexaLocationPermissionRequired() {
        return !hasPermission("android.permission.ACCESS_FINE_LOCATION", getPackageName()) && this.mCurrentPage == MetricsConstants.PageType.LOCATION_PERMISSION;
    }

    @VisibleForTesting
    boolean isAlexaMicrophonePermissionRequired() {
        MetricsConstants.PageType pageType;
        return !hasPermission("android.permission.RECORD_AUDIO", getPackageName()) && ((pageType = this.mCurrentPage) == MetricsConstants.PageType.DOUBLE_MICROPHONE_PERMISSIONS || pageType == MetricsConstants.PageType.SINGLE_MICROPHONE_PERMISSION);
    }

    @VisibleForTesting
    boolean isAndroid11OrHigher() {
        return Build.VERSION.SDK_INT >= 30;
    }

    public boolean isHasPermissionInfoShown() {
        return this.mHasPermissionInfoShown;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (hasPermission("android.permission.RECORD_AUDIO", getPackageName()) && hasPartnerRecordAudioPermissions()) {
            this.mNotificationManager.cancel(1007);
        }
        if (i == 1590) {
            boolean hasPartnerRecordAudioPermissions = hasPartnerRecordAudioPermissions();
            this.mPermissionMetricRecorder.recordPermissionResult(this.mPartnerPermissionsIntent.getPackage(), "android.permission.RECORD_AUDIO", hasPartnerRecordAudioPermissions ? PermissionResult.ALLOW : PermissionResult.DENY);
            if (hasPartnerRecordAudioPermissions) {
                if (isAndroid11OrHigher() && !this.mHasPermissionInfoShown) {
                    showInformationDialog(getPackageName());
                    return;
                } else {
                    requestAlexaPermissionsIfNeeded();
                    return;
                }
            }
            showConfirmationDialog(this.mPartnerPermissionsIntent.getPackage(), Arrays.asList("android.permission.RECORD_AUDIO"));
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        this.mPermissionMetricRecorder.recordClick(this.mCurrentPage, MetricsConstants.SubPageType.ANDROID_BACK_BUTTON);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionDelegate
    public void onClickContinueButton() {
        if (hasPartnerRecordAudioPermissions()) {
            if (isAndroid11OrHigher() && !isHasPermissionInfoShown() && this.mStepsProvider.isDoubleMicrophoneFlow()) {
                showInformationDialog(getPackageName());
            } else {
                requestAlexaPermissionsIfNeeded();
            }
        } else if (isAndroid11OrHigher() && !isHasPermissionInfoShown() && this.mStepsProvider.isDoubleMicrophoneFlow()) {
            showInformationDialog(this.mPartnerPermissionsIntent.getPackage());
        } else {
            requestPartnerAppPermission();
        }
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionDelegate
    public void onClickLaterButton() {
        finishStep(ManagedActivity.StepResult.EXIT);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionDelegate
    public void onClickLaterButtonWithConfirmationDialog() {
        showLaterConfirmationDialog();
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionDelegate
    public void onClickLearnMore() {
        Log.i(TAG, "User clicked learn more page");
        Intent intent = new Intent(this, EnrollmentTermsActivity.class);
        intent.putExtra("URL", "LEARN_MORE_URL");
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.handsfree.ui.ManagedActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        PermissionStrategy singleMicrophonePermissionStrategy;
        super.onCreate(bundle);
        ThemeUtil.setTheme(this);
        this.mInitializer.initialize(this);
        this.mPermissionMetricRecorder = new PermissionMetricRecorder(this);
        this.mPackageManager = getPackageManager();
        this.mPartnerPermissionsIntent = new PartnerIntentResolver(this).getPartnerPermissionsIntent();
        this.mAMPDInformationProvider = AMPDInformationProvider.getInstance(this);
        this.mNotificationManager = (NotificationManager) getSystemService("notification");
        this.mSharedPreferences = getSharedPreferences(SHARED_PREFS_FILE_NAME, 0);
        StepType stepType = (StepType) getIntent().getSerializableExtra(SetupFlowExecutionService.EXTRA_CALLER_STEP_TYPE);
        if (stepType == null) {
            singleMicrophonePermissionStrategy = new DoubleMicrophonePermissionStrategy();
        } else {
            int ordinal = stepType.ordinal();
            if (ordinal == 3) {
                singleMicrophonePermissionStrategy = new SingleMicrophonePermissionStrategy();
            } else if (ordinal != 4) {
                singleMicrophonePermissionStrategy = new DoubleMicrophonePermissionStrategy();
            } else {
                singleMicrophonePermissionStrategy = new LocationPermissionStrategy();
            }
        }
        singleMicrophonePermissionStrategy.execute(this, this.mPermissionMetricRecorder, this.mPartnerPermissionsIntent, getPackageName(), getPartnerAppName());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (i == ALEXA_PERMISSIONS_REQUEST_CODE) {
            String packageName = getPackageName();
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (iArr[i2] == 0) {
                    this.mPermissionMetricRecorder.recordPermissionResult(packageName, strArr[i2], PermissionResult.ALLOW);
                } else {
                    int i3 = Build.VERSION.SDK_INT;
                    if (!shouldShowRequestPermissionRationale(strArr[i2])) {
                        arrayList2.add(strArr[i2]);
                        this.mPermissionMetricRecorder.recordPermissionResult(packageName, strArr[i2], PermissionResult.DENY_NEVER_ASK_AGAIN);
                    } else {
                        arrayList.add(strArr[i2]);
                        this.mPermissionMetricRecorder.recordPermissionResult(packageName, strArr[i2], PermissionResult.DENY);
                    }
                }
            }
            if (arrayList2.contains("android.permission.RECORD_AUDIO")) {
                showManualPermissionsNeededDialog();
            } else if (!arrayList.isEmpty()) {
                showConfirmationDialog(packageName, arrayList);
            } else {
                if (isAndroid11OrHigher()) {
                    this.mPermissionAlarmScheduler.schedulePermissionChecker(this);
                }
                finishStep(ManagedActivity.StepResult.CONTINUE);
            }
            if (!hasPermission("android.permission.RECORD_AUDIO", getPackageName()) || !hasPartnerRecordAudioPermissions()) {
                return;
            }
            this.mNotificationManager.cancel(1007);
        }
    }

    @VisibleForTesting
    void requestAlexaPermissionsIfNeeded() {
        ArrayList arrayList = new ArrayList();
        if (isAlexaMicrophonePermissionRequired()) {
            arrayList.add("android.permission.RECORD_AUDIO");
        }
        if (isAlexaLocationPermissionRequired()) {
            arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        }
        if (arrayList.size() > 0) {
            this.mPermissionMetricRecorder.recordPageView(this.mCurrentPage, MetricsConstants.SubPageType.PERMISSION_DIALOG);
            ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[0]), ALEXA_PERMISSIONS_REQUEST_CODE);
            return;
        }
        if (isAndroid11OrHigher()) {
            this.mPermissionAlarmScheduler.schedulePermissionChecker(this);
        }
        finishStep(ManagedActivity.StepResult.CONTINUE);
    }

    @VisibleForTesting
    void requestPartnerAppPermission() {
        if (isAndroid11OrHigher() && this.mSharedPreferences.getInt(PERMISSION_COUNT, 0) >= 2) {
            showManualPartnerPermissionsNeededDialog();
            return;
        }
        this.mSharedPreferences.edit().putInt(PERMISSION_COUNT, this.mSharedPreferences.getInt(PERMISSION_COUNT, 0) + 1).apply();
        this.mPermissionMetricRecorder.recordPageView(this.mCurrentPage, MetricsConstants.SubPageType.PARTNER_PERMISSION_DIALOG);
        startActivityForResult(this.mPartnerPermissionsIntent, 1590);
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionDelegate
    public void setCurrentPage(@NonNull MetricsConstants.PageType pageType) {
        this.mCurrentPage = pageType;
    }

    @Override // com.amazon.alexa.voice.handsfree.decider.setup.PermissionDelegate
    public void setupContentView(@LayoutRes int i) {
        setContentView(i);
    }

    @VisibleForTesting
    void showConfirmationDialog(@NonNull final String str, @NonNull List<String> list) {
        final boolean hasPermission = hasPermission("android.permission.RECORD_AUDIO", getPackageName());
        AlertDialog.Builder newAlertDialogBuilder = getNewAlertDialogBuilder();
        newAlertDialogBuilder.setTitle(R.string.alexa_handsfree_permission_reject_confirmation_dialog_title);
        if (list.contains("android.permission.RECORD_AUDIO")) {
            if (isAndroid11OrHigher()) {
                newAlertDialogBuilder.setMessage(R.string.alexa_handsfree_permission_reject_confirmation_dialog_recording_android_11);
            } else {
                newAlertDialogBuilder.setMessage(R.string.alexa_handsfree_permission_reject_confirmation_dialog_recording);
            }
        } else if (list.contains("android.permission.ACCESS_FINE_LOCATION")) {
            newAlertDialogBuilder.setMessage(R.string.alexa_handsfree_permission_reject_confirmation_dialog_location);
        }
        newAlertDialogBuilder.setPositiveButton(R.string.alexa_handsfree_permission_reject_confirmation_dialog_confirm, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.SinglePermissionsActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                SinglePermissionsActivity.this.mPermissionMetricRecorder.recordClick(SinglePermissionsActivity.this.mCurrentPage, MetricsConstants.SubPageType.PERMISSIONS_REJECT_CONFIRM_BUTTON);
                if (hasPermission && SinglePermissionsActivity.this.hasPartnerRecordAudioPermissions()) {
                    if (SinglePermissionsActivity.this.isAndroid11OrHigher()) {
                        SinglePermissionsActivity.this.mPermissionAlarmScheduler.schedulePermissionChecker(SinglePermissionsActivity.this);
                    }
                    SinglePermissionsActivity.this.finishStep(ManagedActivity.StepResult.CONTINUE);
                    return;
                }
                SinglePermissionsActivity.this.finishStep(ManagedActivity.StepResult.EXIT);
            }
        });
        newAlertDialogBuilder.setNegativeButton(R.string.alexa_handsfree_permission_reject_confirmation_dialog_cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.SinglePermissionsActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                SinglePermissionsActivity.this.mPermissionMetricRecorder.recordClick(SinglePermissionsActivity.this.mCurrentPage, MetricsConstants.SubPageType.PERMISSIONS_REJECT_GO_BACK_BUTTON);
                if (str.equals(SinglePermissionsActivity.this.mPartnerPermissionsIntent.getPackage())) {
                    SinglePermissionsActivity.this.requestPartnerAppPermission();
                } else {
                    SinglePermissionsActivity.this.requestAlexaPermissionsIfNeeded();
                }
            }
        });
        newAlertDialogBuilder.create().show();
    }

    @VisibleForTesting
    void showInformationDialog(@NonNull final String str) {
        this.mHasPermissionInfoShown = true;
        getNewAlertDialogBuilder().setTitle(R.string.alexa_handsfree_permission_info_confirmation_dialog_title).setMessage(R.string.alexa_handsfree_permission_info_confirmation_dialog_recording).setPositiveButton(R.string.alexa_handsfree_permission_info_confirmation_dialog_confirm, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.SinglePermissionsActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                SinglePermissionsActivity.this.mPermissionMetricRecorder.recordClick(SinglePermissionsActivity.this.mCurrentPage, MetricsConstants.SubPageType.PERMISSIONS_INFORMATION_CONFIRM_BUTTON);
                if (str.equals(SinglePermissionsActivity.this.getPackageName())) {
                    SinglePermissionsActivity.this.requestAlexaPermissionsIfNeeded();
                } else {
                    SinglePermissionsActivity.this.requestPartnerAppPermission();
                }
            }
        }).create().show();
    }

    @VisibleForTesting
    void showLaterConfirmationDialog() {
        AlertDialog.Builder newAlertDialogBuilder = getNewAlertDialogBuilder();
        newAlertDialogBuilder.setTitle(R.string.alexa_handsfree_permission_reject_confirmation_dialog_title);
        newAlertDialogBuilder.setMessage(R.string.alexa_handsfree_permission_reject_confirmation_dialog_recording);
        newAlertDialogBuilder.setPositiveButton(R.string.alexa_app_audio_permission_later_confirmation_dialog_confirm_button, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.SinglePermissionsActivity.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                SinglePermissionsActivity.this.mPermissionMetricRecorder.recordClick(SinglePermissionsActivity.this.mCurrentPage, MetricsConstants.SubPageType.DIALOG_CONFIRM_BUTTON);
                SinglePermissionsActivity.this.finishStep(ManagedActivity.StepResult.EXIT);
            }
        }).setNegativeButton(R.string.alexa_app_audio_permission_later_confirmation_dialog_go_back_button, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.SinglePermissionsActivity.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                SinglePermissionsActivity.this.mPermissionMetricRecorder.recordClick(SinglePermissionsActivity.this.mCurrentPage, MetricsConstants.SubPageType.DIALOG_GO_BACK_BUTTON);
            }
        }).create().show();
    }

    @VisibleForTesting
    void showManualPermissionsNeededDialog() {
        getNewAlertDialogBuilder().setMessage(R.string.alexa_handsfree_permission_manual_dialog_warning).setPositiveButton(R.string.alexa_handsfree_permission_manual_dialog_go_to_permissions, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.SinglePermissionsActivity.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                SinglePermissionsActivity.this.mPermissionMetricRecorder.recordClick(SinglePermissionsActivity.this.mCurrentPage, MetricsConstants.SubPageType.PERMISSIONS_MANUAL_PERMISSIONS_GO_TO_BUTTON);
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.addFlags(268435456);
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("package:");
                outline107.append(SinglePermissionsActivity.this.getPackageName());
                intent.setData(Uri.parse(outline107.toString()));
                SinglePermissionsActivity.this.startActivity(intent);
            }
        }).setNegativeButton(R.string.alexa_handsfree_permission_manual_dialog_close, new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.decider.setup.SinglePermissionsActivity.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                SinglePermissionsActivity.this.mPermissionMetricRecorder.recordClick(SinglePermissionsActivity.this.mCurrentPage, MetricsConstants.SubPageType.PERMISSIONS_MANUAL_PERMISSIONS_CLOSE_BUTTON);
                SinglePermissionsActivity.this.finishStep(ManagedActivity.StepResult.EXIT);
            }
        }).create().show();
    }

    public SinglePermissionsActivity() {
        this.mInitializer = InitializerProvider.getInitializer();
        this.mPermissionAlarmScheduler = new PermissionAlarmScheduler();
        this.mStepsProvider = StepsProvider.getInstance();
    }
}
