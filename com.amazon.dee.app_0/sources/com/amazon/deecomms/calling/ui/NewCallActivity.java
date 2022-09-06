package com.amazon.deecomms.calling.ui;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.WebRTCViewRenderer;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.app.RemoteViewManager;
import com.amazon.deecomms.app.SelfViewManager;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.controller.CallDowngradedObservable;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallingContentProviderNotifier;
import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.calling.core.CallingFragmentFactory;
import com.amazon.deecomms.calling.enums.CallState;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.model.CallModel;
import com.amazon.deecomms.calling.receiver.HeadsetPluggedBroadcastReceiver;
import com.amazon.deecomms.calling.receiver.PowerButtonPressReceiver;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.calling.ui.ep.ActiveEnhancedProcessingVideoCallFragment;
import com.amazon.deecomms.calling.ui.ep.IncomingEnhancedProcessingVideoCallFragment;
import com.amazon.deecomms.calling.ui.ep.OutgoingEnhancedProcessingVideoCallFragment;
import com.amazon.deecomms.calling.ui.listener.VideoDisplayListener;
import com.amazon.deecomms.calling.ui.listener.VideoStreamingListener;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.calling.video.display.RemoteVideoDisplayPresenter;
import com.amazon.deecomms.calling.video.display.SelfVideoDisplayPresenter;
import com.amazon.deecomms.calling.video.streaming.RemoteVideoStreamingPresenter;
import com.amazon.deecomms.calling.video.streaming.SelfVideoStreamingPresenter;
import com.amazon.deecomms.calling.video.streaming.VideoStreamingPresenterInterface;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.service.DeviceCallingAndroidService;
import com.amazon.deecomms.common.service.IncomingPushAndroidService;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.audio.CallMediaControlFacade;
import com.amazon.deecomms.perms.PermissionsHelper;
import com.amazon.deecomms.util.ThreadUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
import java.util.Observable;
import java.util.Observer;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes12.dex */
public class NewCallActivity extends AppCompatActivity implements VideoStreamingListener, VideoDisplayListener, Observer {
    private static final int LAYOUT_FLAGS = 4352;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, NewCallActivity.class);
    @Inject
    CallContext callContext;
    @Inject
    CallDowngradedObservable callDowngradedObservable;
    private String callFragmentToLaunch;
    @Inject
    CallManager callManager;
    @Inject
    CallMediaControlFacade callMediaControlFacade;
    private CallModel callModel;
    private TextView callStatusText;
    private CallingFragmentFactory callingFragmentFactory;
    private FrameLayout callingViewContainer;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommandProcessor commandProcessor;
    @Inject
    CommsNotificationManager commsNotificationManager;
    @Inject
    DeviceCallingService deviceCallingService;
    private BroadcastReceiver endCallReceiver;
    @VisibleForTesting
    Handler handler;
    private HeadsetPluggedBroadcastReceiver headsetPluggedBroadcastReceiver;
    @Inject
    AlexaAudioPlayer mAlexaAudioPlayer;
    CallingContentProviderNotifier mCallingContentProviderNotifier;
    private WindowManager mWindowManager;
    private OptInUI optin;
    private PowerButtonPressReceiver powerButtonPressReceiver;
    @Inject
    RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;
    private RemoteVideoDisplayPresenter remoteVideoDisplayPresenter;
    private RemoteVideoStreamingPresenter remoteVideoStreamingPresenter;
    private RelativeLayout remoteViewLayout;
    private RemoteViewManager remoteViewManager;
    private Runnable runnable;
    private LinearLayout scrimLayout;
    private SelfVideoDisplayPresenter selfVideoDisplayPresenter;
    private SelfVideoStreamingPresenter selfVideoStreamingPresenter;
    private RelativeLayout selfViewLayout;
    private SelfViewManager selfViewManager;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;
    @Inject
    TelecomCallAudioRouteObservable telecomCallAudioRouteObservable;
    private String currentScreen = "";
    private boolean hasAcceptedCall = false;
    private boolean hasRejectedCall = false;
    private boolean hasStartedFromPendingIntent = false;
    private BroadcastReceiver callPermissionRequestReceiver = new BroadcastReceiver() { // from class: com.amazon.deecomms.calling.ui.NewCallActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int i;
            if (intent != null && Constants.CALL_PERMISSION_REQUEST_ACTION.equals(intent.getAction())) {
                NewCallActivity newCallActivity = NewCallActivity.this;
                if (newCallActivity.callContext.isIncomingCall()) {
                    i = NewCallActivity.this.callContext.isVideoCall() ? 7 : 1;
                } else {
                    i = 14;
                }
                newCallActivity.requestCallPermission(i, NewCallActivity.this.callContext.isVideoCall());
            }
        }
    };
    private BroadcastReceiver callEndedBecauseOfErrorReceiver = new BroadcastReceiver() { // from class: com.amazon.deecomms.calling.ui.NewCallActivity.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intent intent2 = new Intent(context, DeviceCallingAndroidService.class);
            intent2.setAction(Constants.CANCEL_OUTGOING_CALL);
            context.startService(intent2);
        }
    };

    /* renamed from: com.amazon.deecomms.calling.ui.NewCallActivity$4  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState = new int[SipClientState.CallState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.CALLING_INITIATED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.CALLING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.OUTBOUND_RINGING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.ACTIVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$common$sip$SipClientState$CallState[SipClientState.CallState.INBOUND_RINGING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private CallModel buildCallModel() {
        CallModel callModel = new CallModel();
        String nameToBeDisplayed = getNameToBeDisplayed();
        String callStatus = CallViewUtils.getCallStatus(getIntent().getExtras(), this);
        callModel.setRemoteParticipantName(nameToBeDisplayed);
        callModel.setCallStatus(callStatus);
        return callModel;
    }

    private void checkIntentActions() {
        Bundle extras = getIntent().getExtras();
        this.hasAcceptedCall = extras.getBoolean(Constants.KEY_HAVE_ANSWERED_CALL, false);
        this.hasRejectedCall = extras.getBoolean(Constants.KEY_HAVE_DECLINED_CALL, false);
        this.hasStartedFromPendingIntent = extras.getBoolean(Constants.KEY_STARTED_FROM_PENDING_INTENT, false);
    }

    private void commitFragmentTransaction(@NonNull Fragment fragment) {
        fragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(16908290, fragment).commit();
    }

    private String getCallUIFragmentKey(@NonNull String str) {
        if (StringUtils.isEmpty(str)) {
            SipClientState.CallState callState = this.sipClientState.getCallState();
            int ordinal = callState.ordinal();
            if (ordinal == 1) {
                return Constants.FRAGMENT_CALL_INITIATED_KEY;
            }
            if (ordinal != 2) {
                if (ordinal != 4) {
                    if (ordinal != 5) {
                        if (ordinal != 6) {
                            CommsLogger commsLogger = LOG;
                            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Call UI Fragment not available for state : ");
                            outline1.append(callState.name());
                            commsLogger.i(outline1.toString());
                            return null;
                        }
                        return Constants.FRAGMENT_ACTIVE_CALL_KEY;
                    }
                }
                return Constants.FRAGMENT_INCOMING_CALL_KEY;
            }
            return Constants.FRAGMENT_OUTGOING_CALL_KEY;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1170214476:
                if (str.equals(Constants.Calling.ACTIVE_CALL_SCREEN)) {
                    c = 2;
                    break;
                }
                break;
            case -1099089638:
                if (str.equals(Constants.Calling.OUTGOING_CALL_SCREEN)) {
                    c = 0;
                    break;
                }
                break;
            case 612671359:
                if (str.equals(Constants.Calling.ERROR_DIALOG)) {
                    c = 4;
                    break;
                }
                break;
            case 1275372521:
                if (str.equals(Constants.Calling.END_CALL_SCREEN)) {
                    c = 3;
                    break;
                }
                break;
            case 1703196372:
                if (str.equals(Constants.Calling.INCOMING_CALL_SCREEN)) {
                    c = 1;
                    break;
                }
                break;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    if (c == 3) {
                        return Constants.FRAGMENT_END_CALL_KEY;
                    }
                    if (c == 4) {
                        return Constants.Calling.DIALOG_CALL_ERROR_KEY;
                    }
                    GeneratedOutlineSupport.outline4("Call UI Fragment not available for screen Name : ", str, LOG);
                    return null;
                }
                return Constants.FRAGMENT_ACTIVE_CALL_KEY;
            }
            return Constants.FRAGMENT_INCOMING_CALL_KEY;
        }
        return Constants.FRAGMENT_OUTGOING_CALL_KEY;
    }

    private VideoStreamingPresenterInterface[] getVideoPresenters(@NonNull CallState callState) {
        return callState == CallState.ACTIVE ? new VideoStreamingPresenterInterface[]{this.selfVideoStreamingPresenter, this.remoteVideoStreamingPresenter} : new VideoStreamingPresenterInterface[]{this.selfVideoStreamingPresenter};
    }

    private void hideStatusBar() {
        getWindow().getDecorView().setSystemUiVisibility(OlympusImageProcessingMakernoteDirectory.TagUnknownBlock4);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void init() {
        this.headsetPluggedBroadcastReceiver = new HeadsetPluggedBroadcastReceiver();
        this.powerButtonPressReceiver = new PowerButtonPressReceiver();
        this.mWindowManager = (WindowManager) getApplicationContext().getSystemService("window");
        this.mCallingContentProviderNotifier = new CallingContentProviderNotifier(getApplicationContext());
        this.optin = new OptInUI();
        this.callModel = buildCallModel();
        this.callingFragmentFactory = new CallingFragmentFactory(this.callModel);
    }

    private void initialize() {
        init();
        setTheme();
        setWindowMode();
        hideStatusBar();
        setRequestedOrientation(1);
        registerReceivers();
        registerObservers();
    }

    private void launchFragmentBasedOnCallState(boolean z) {
        if (this.callContext == null) {
            LOG.e("Error!! Call context is null even before Call Activity can be launched.");
            finish();
            return;
        }
        String stringExtra = getIntent().getStringExtra(Constants.Calling.SCREEN_NAME);
        this.callFragmentToLaunch = getCallUIFragmentKey(stringExtra);
        String str = this.callFragmentToLaunch;
        boolean z2 = false;
        int i = 1;
        if (str == null) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("No call fragments found for screen name :");
            outline1.append(StringUtils.defaultString(stringExtra));
            commsLogger.e(outline1.toString());
            LOG.d("hasAcceptedCall: %s, hasRejectedCall: %s", Boolean.valueOf(this.hasAcceptedCall), Boolean.valueOf(this.hasRejectedCall));
            if (this.hasAcceptedCall || this.hasRejectedCall || this.hasStartedFromPendingIntent) {
                return;
            }
            finish();
        } else if (str.equals(this.currentScreen) && !z) {
        } else {
            if (this.callFragmentToLaunch.equals(Constants.Calling.DIALOG_CALL_ERROR_KEY)) {
                showErrorDialog(getIntent().getIntExtra(Constants.Calling.ERROR_DIALOG_TITLE, -1), getIntent().getIntExtra(Constants.Calling.ERROR_DIALOG_MESSAGE, -1));
                return;
            }
            Fragment fragment = this.callingFragmentFactory.getFragment(this.callContext, this.callFragmentToLaunch, getIntent().getExtras(), this);
            stopTimer();
            this.callStatusText.setVisibility(8);
            if (this.callContext.isVideoCall()) {
                setupLayoutForVideoCalling();
            }
            if (PermissionsHelper.checkPermissions(this, this.callContext.isVideoCall() ? PermissionsHelper.getPermissionListForVideoCalling() : PermissionsHelper.getPermissionListForAudio()).length > 0) {
                z2 = true;
            }
            if (this.optin.isNeeded(this.sipClientState, this.callContext.isGroupCall())) {
                this.optin.show(this.callContext.getDirection(), this);
            } else if (!this.callContext.isIncomingCall() && z2) {
                if (!this.callContext.isIncomingCall()) {
                    i = 14;
                } else if (this.callContext.isVideoCall()) {
                    i = 7;
                }
                requestCallPermission(i, this.callContext.isVideoCall());
            } else {
                commitFragmentTransaction(fragment);
                this.currentScreen = this.callFragmentToLaunch;
            }
        }
    }

    private void registerObservers() {
        this.callDowngradedObservable.addObserver(this);
    }

    private void registerReceivers() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.media.AUDIO_BECOMING_NOISY");
        registerReceiver(this.headsetPluggedBroadcastReceiver, intentFilter);
        registerReceiver(this.powerButtonPressReceiver, new IntentFilter("android.intent.action.SCREEN_OFF"));
        LocalBroadcastManager.getInstance(this).registerReceiver(this.callPermissionRequestReceiver, new IntentFilter(Constants.CALL_PERMISSION_REQUEST_ACTION));
        this.endCallReceiver = new BroadcastReceiver() { // from class: com.amazon.deecomms.calling.ui.NewCallActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                NewCallActivity.this.finish();
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(this.endCallReceiver, new IntentFilter(Constants.CLEAR_CALL_ACTIVITY));
        LocalBroadcastManager.getInstance(this).registerReceiver(this.callEndedBecauseOfErrorReceiver, new IntentFilter(Constants.CALL_ENDED_BECAUSE_OF_ERROR));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public boolean requestCallPermission(@NonNull int i, boolean z) {
        return CallUtils.checkPermissions(this, AlertSource.newClassSource(NewCallActivity.class.getName()), i, z, new DialogInterface.OnDismissListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$NewCallActivity$sIlt40ISeXeUkdYPnbycCvx9-0U
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                NewCallActivity.this.lambda$requestCallPermission$0$NewCallActivity(dialogInterface);
            }
        }, this);
    }

    private void resetSipClientState() {
        if (this.sipClientState.getCallState() == SipClientState.CallState.CALLING_INITIATED || this.sipClientState.getCallState() == SipClientState.CallState.CALLING) {
            this.sipClientState.setCallState(SipClientState.CallState.INACTIVE);
        }
    }

    private void setPhoneOrientation(int i) {
        setRequestedOrientation(i);
    }

    private void setTheme() {
        if (this.capabilitiesManager.isThemedUIEnabled()) {
            if (this.capabilitiesManager.isMosaicThemingEnabled()) {
                setTheme(R.style.Theme_Mosaic_Jasper_Dark);
            } else {
                ThemeUtil.setTheme(this);
            }
        }
    }

    private void setWindowMode() {
        getWindow().addFlags(2654336);
        getWindow().getDecorView().setSystemUiVisibility(4352);
    }

    private void setupLayoutForVideoCalling() {
        LOG.i(" Preparing layout for Video Calling ");
        if (this.capabilitiesManager.isMosaicThemingEnabled()) {
            setContentView(R.layout.mosaic_video_call_activity);
        } else if (this.capabilitiesManager.isThemedUIEnabled()) {
            setContentView(R.layout.fiesta_video_call_activity);
        } else {
            setContentView(R.layout.video_call_activity);
        }
        this.callingViewContainer = (FrameLayout) findViewById(R.id.calling_view_wrapper);
        this.selfViewLayout = (RelativeLayout) findViewById(R.id.selfViewContainer);
        this.remoteViewLayout = (RelativeLayout) findViewById(R.id.remoteViewContainer);
        this.scrimLayout = (LinearLayout) findViewById(R.id.scrim);
        this.callingViewContainer.setSystemUiVisibility(4352);
        this.selfViewManager = new SelfViewManager(this.selfViewLayout, this.deviceCallingService, this.scrimLayout, getApplicationContext(), this.realTimeTextEnablementAuthority);
        this.remoteViewManager = new RemoteViewManager(this.remoteViewLayout, this.deviceCallingService);
        this.selfVideoStreamingPresenter = new SelfVideoStreamingPresenter(this.selfViewManager);
        this.remoteVideoStreamingPresenter = new RemoteVideoStreamingPresenter(this.remoteViewManager);
        this.selfVideoDisplayPresenter = new SelfVideoDisplayPresenter(this.selfViewManager);
        this.remoteVideoDisplayPresenter = new RemoteVideoDisplayPresenter(this.remoteViewManager, this);
        this.selfViewManager.start();
    }

    private void showErrorDialog(int i, int i2) {
        if (isFinishing() || isDestroyed()) {
            LOG.e("Cannot show dialog if the activity is finished");
        } else if (i2 != -1 && i != -1) {
            new AlertDialog.Builder(this).setTitle(i).setMessage(i2).setPositiveButton(R.string.dialog_ok_button, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$NewCallActivity$tk_RACB3nA_YvpFvzoW0fGo_Wtg
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i3) {
                    NewCallActivity.this.lambda$showErrorDialog$3$NewCallActivity(dialogInterface, i3);
                }
            }).create().show();
        } else {
            LOG.e("Cannot show error dialog as the content is invalid.");
        }
    }

    private void stopRingTone() {
        this.mAlexaAudioPlayer.stop(1);
    }

    private void stopTimer() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
            this.handler = null;
            this.runnable = null;
            LOG.d("[Comms-calling]: stop fail-safe Timer as SIP INVITE has been received.");
        }
    }

    private void updateConnectingStatusIfNeeded() {
        this.callStatusText = (TextView) findViewById(R.id.callStatus);
        if (this.hasAcceptedCall || this.hasStartedFromPendingIntent) {
            startTimer(15000);
            this.callStatusText.setVisibility(0);
        }
    }

    @VisibleForTesting
    void dismissHeadupNotificationIfNeeded(Context context) {
        IncomingPushAndroidService.dismissHeadupNotification(context);
    }

    public String getNameToBeDisplayed() {
        String remoteParticipantName = this.callContext.getRemoteParticipantName();
        if (remoteParticipantName == null || remoteParticipantName.length() <= 0) {
            String remoteParticipantName2 = this.sipClientState.getRemoteParticipantName();
            if (!StringUtils.isEmpty(remoteParticipantName2)) {
                return remoteParticipantName2;
            }
            LOG.i("Name is still empty. Resorting to old approach to get name");
            return CallViewUtils.getRemoteDisplayName(this.sipClientState, this.callContext.isGroupCall(), this);
        }
        return remoteParticipantName;
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void hidePip(boolean z) {
        if (z) {
            this.selfVideoDisplayPresenter.removePIP();
        } else {
            this.remoteVideoDisplayPresenter.removePIP();
        }
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void hideScrim() {
        this.selfVideoDisplayPresenter.hideScrim();
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void hideSelfView() {
        this.selfVideoDisplayPresenter.hideSelfView();
    }

    public /* synthetic */ void lambda$onRequestPermissionsResult$1$NewCallActivity() {
        launchFragmentBasedOnCallState(false);
    }

    public /* synthetic */ void lambda$onRequestPermissionsResult$2$NewCallActivity(DialogInterface dialogInterface) {
        LOG.d("Permission not available dialog dismissed, finishing activity");
        finish();
    }

    public /* synthetic */ void lambda$requestCallPermission$0$NewCallActivity(DialogInterface dialogInterface) {
        LOG.d("Permissions dialog was shown and the user has cancelled or gone to settings");
        CallUtils.handleCallInitiationErrors(this.sipClientState);
        if (!isFinishing()) {
            LOG.d("Finishing activity as there is nothing to do without permissions");
            finish();
        }
    }

    public /* synthetic */ void lambda$showErrorDialog$3$NewCallActivity(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        finish();
    }

    public /* synthetic */ void lambda$startTimer$5$NewCallActivity() {
        stopRingTone();
        LOG.i("[Comms-calling]: timer is fired. Call invite was not received within 15 seconds of the call push notification.");
        finish();
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void maximizeVideo(boolean z) {
        if (z) {
            this.selfVideoDisplayPresenter.maximiseVideo();
        } else {
            this.remoteVideoDisplayPresenter.maximiseVideo();
        }
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void minimizeVideo(@NonNull int i, boolean z) {
        if (z) {
            this.selfVideoDisplayPresenter.minimizeVideo(i);
        } else {
            this.remoteVideoDisplayPresenter.minimizeVideo(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent == null) {
            LOG.i("No information about opt-in preference, dismissing activity");
            resetSipClientState();
            finish();
            return;
        }
        boolean isIncomingCall = this.callContext.isIncomingCall();
        if (i != 200) {
            return;
        }
        if (i2 == 10) {
            if (isIncomingCall) {
                CallUtils.rejectCall(this);
            } else {
                CallUtils.cancelOutgoingCall(this);
            }
            finish();
        } else if (i2 != 20) {
        } else {
            if (isIncomingCall) {
                CallUtils.acceptIncomingCall(this, this.callContext.isVideoCall());
                this.commandProcessor.enableVideoStreamInVideoCall(false);
                this.commandProcessor.enableVideoStreamInVideoCall(true);
                return;
            }
            launchFragmentBasedOnCallState(false);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onAttachFragment(@NonNull Fragment fragment) {
        LOG.i("onAttachFragment called.");
        if (fragment instanceof IncomingEnhancedProcessingVideoCallFragment) {
            ((IncomingEnhancedProcessingVideoCallFragment) fragment).setVideoDisplayListener(this);
        } else if (fragment instanceof OutgoingEnhancedProcessingVideoCallFragment) {
            ((OutgoingEnhancedProcessingVideoCallFragment) fragment).setVideoDisplayListener(this);
        } else if (!(fragment instanceof ActiveEnhancedProcessingVideoCallFragment)) {
        } else {
            ((ActiveEnhancedProcessingVideoCallFragment) fragment).setVideoDisplayListener(this);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    /* renamed from: onCallDowngraded */
    public void lambda$update$4$NewCallActivity() {
        LOG.i("Downgrading to audio only call");
        this.callManager.setCallDowngraded(true);
        this.callContext.setCallDowngradedToAudio(true);
        launchFragmentBasedOnCallState(true);
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoStreamingListener
    public void onCameraToggledToFront(@NonNull CallState callState, @NonNull CallType callType) {
        this.selfVideoStreamingPresenter.turnFrontSelfVideoFeedOn();
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoStreamingListener
    public void onCameraToggledToRear(@NonNull CallState callState, @NonNull CallType callType) {
        this.selfVideoStreamingPresenter.turnFrontSelfVideoFeedOn();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = configuration.orientation;
        if (i == 1) {
            LOG.i("Orientation changed to Portrait");
        } else if (i != 2) {
        } else {
            LOG.i("Orientation changed to landscape");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        super.onCreate(bundle);
        LOG.d("[Comms-calling]: New Call Activity has been created");
        setContentView(R.layout.activity_new_call);
        checkIntentActions();
        updateConnectingStatusIfNeeded();
        initialize();
        launchFragmentBasedOnCallState(false);
        dismissHeadupNotificationIfNeeded(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        SelfVideoStreamingPresenter selfVideoStreamingPresenter;
        super.onDestroy();
        LOG.d("[Comms-calling]: NewCallActivity onDestroy ");
        unregisterReceiver(this.headsetPluggedBroadcastReceiver);
        unregisterReceiver(this.powerButtonPressReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.callPermissionRequestReceiver);
        if (this.callContext.isVideoCall() && (selfVideoStreamingPresenter = this.selfVideoStreamingPresenter) != null && this.remoteVideoStreamingPresenter != null) {
            selfVideoStreamingPresenter.stopStreaming();
            this.remoteVideoStreamingPresenter.stopStreaming();
        }
        if (getIntent().getBooleanExtra(Constants.KEY_DROP_IN_NOT_AVAILABLE, false)) {
            this.sipClientState.setCallType(CallType.NONE);
            this.sipClientState.setCallProvider("");
        }
        this.mCallingContentProviderNotifier.notifyObservers();
        this.telecomCallAudioRouteObservable.deleteObservers();
        this.callDowngradedObservable.deleteObservers();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LOG.d("[Comms-calling]: NewCallActivity onNewIntent ");
        getIntent().putExtra(Constants.KEY_HAVE_ANSWERED_CALL, this.hasAcceptedCall);
        getIntent().putExtra(Constants.KEY_HAVE_DECLINED_CALL, this.hasRejectedCall);
        getIntent().putExtra(Constants.KEY_STARTED_FROM_PENDING_INTENT, this.hasStartedFromPendingIntent);
        getIntent().putExtra(Constants.Calling.SCREEN_NAME, intent.getStringExtra(Constants.Calling.SCREEN_NAME));
        launchFragmentBasedOnCallState(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.callManager.setCallUINavigation(true);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (!PermissionsHelper.checkGrantedPermissions(iArr)) {
            LOG.i(" Permission denied ");
            if (i == 14) {
                CallUtils.handleCallInitiationErrors(this.sipClientState);
                PermissionsHelper.showPermissionNotAvailableDialog(this, PermissionsHelper.getDeniedCallingPermissionsRationale(this, true), MetricKeys.ALERT_PERM_MIC_AND_CAMERA, MetricKeys.SCREEN_NAME_OUTGOING_CALL, AlertSource.newClassSource(NewCallActivity.class.getName()), false, new DialogInterface.OnDismissListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$NewCallActivity$gc5KmkoefDkQFUOP3MVK6Pw39aU
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        NewCallActivity.this.lambda$onRequestPermissionsResult$2$NewCallActivity(dialogInterface);
                    }
                });
                return;
            }
            CallUtils.rejectCall(this);
        } else if (i == 1) {
            LOG.i(" Mic Permission granted. accepting audio call ");
            CallUtils.acceptIncomingCall(this, false);
        } else if (i != 7) {
            if (i != 14) {
                CommsLogger commsLogger = LOG;
                commsLogger.e(" Unknown permission code granted " + i);
                return;
            }
            runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$NewCallActivity$blSCXAJUA2CIAJ609VHgJPjoPu0
                @Override // java.lang.Runnable
                public final void run() {
                    NewCallActivity.this.lambda$onRequestPermissionsResult$1$NewCallActivity();
                }
            });
        } else {
            LOG.i("Mic & Camera permissions granted. Checking opt-in preference");
            if (this.optin.isNeeded(this.sipClientState, this.callContext.isGroupCall())) {
                this.optin.show(Constants.OUTGOING, this);
                return;
            }
            CommsLogger commsLogger2 = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Opt-in not required. Accepting call which is video enabled : ");
            outline1.append(this.callContext.isVideoCall());
            commsLogger2.i(outline1.toString());
            CallUtils.acceptIncomingCall(this, this.callContext.isVideoCall());
            this.commandProcessor.enableVideoStreamInVideoCall(false);
            this.commandProcessor.enableVideoStreamInVideoCall(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LOG.d(" NewCallActivity onResume ");
        String str = this.callFragmentToLaunch;
        if (str == null) {
            return;
        }
        if (!str.equals(Constants.Calling.END_CALL_SCREEN)) {
            this.callManager.setCallUIVisibility(true);
        }
        this.mCallingContentProviderNotifier.notifyObservers();
        hideStatusBar();
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void onSetBackground(boolean z, boolean z2, boolean z3) {
        if (z2) {
            this.selfVideoDisplayPresenter.setBackground(z, z3);
        } else {
            this.remoteVideoDisplayPresenter.setBackground(z, z3);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        LOG.i(" NewCallActivity onStart");
        String str = this.callFragmentToLaunch;
        if (str == null) {
            return;
        }
        if (!str.equals(Constants.Calling.END_CALL_SCREEN)) {
            this.callManager.setCallUIVisibility(true);
            this.callManager.setCallUINavigation(false);
        }
        if (!this.callContext.isVideoCall()) {
            return;
        }
        this.commandProcessor.enableVideoStreamInVideoCall(true);
        SelfVideoStreamingPresenter selfVideoStreamingPresenter = this.selfVideoStreamingPresenter;
        if (selfVideoStreamingPresenter == null) {
            return;
        }
        selfVideoStreamingPresenter.startStreaming();
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoStreamingListener
    public void onStartStreamingVideo(@NonNull CallState callState, @NonNull CallType callType) {
        for (VideoStreamingPresenterInterface videoStreamingPresenterInterface : getVideoPresenters(callState)) {
            videoStreamingPresenterInterface.startStreaming();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        this.callManager.setCallUIVisibility(false);
        if (this.callContext.isVideoCall()) {
            this.commandProcessor.enableVideoStreamInVideoCall(false);
        }
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoStreamingListener
    public void onStopStreamingVideo(@NonNull CallState callState, @NonNull CallType callType) {
        for (VideoStreamingPresenterInterface videoStreamingPresenterInterface : getVideoPresenters(callState)) {
            videoStreamingPresenterInterface.stopStreaming();
        }
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void setScalingType(@NonNull WebRTCViewRenderer.ScalingType scalingType, boolean z) {
        if (z) {
            this.selfVideoDisplayPresenter.setScalingType(scalingType);
        } else {
            this.remoteVideoDisplayPresenter.setScalingType(scalingType);
        }
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void showPip(boolean z) {
        if (z) {
            this.selfVideoDisplayPresenter.showSelfView();
        } else {
            this.remoteVideoDisplayPresenter.showSelfView();
        }
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void showScrim() {
        this.selfVideoDisplayPresenter.showScrim();
    }

    @VisibleForTesting
    void startTimer(int i) {
        stopTimer();
        this.handler = new Handler();
        this.runnable = new Runnable() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$NewCallActivity$z7O6XpfAOHzIeh-Synbu6dkSW0c
            @Override // java.lang.Runnable
            public final void run() {
                NewCallActivity.this.lambda$startTimer$5$NewCallActivity();
            }
        };
        this.handler.postDelayed(this.runnable, i);
        LOG.d("[Comms-calling]: start fail-safe Timer");
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object obj) {
        if (!(observable instanceof CallDowngradedObservable) || !((Boolean) obj).booleanValue()) {
            return;
        }
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$NewCallActivity$52dCxyAedc5SzPxXI0Cgh8uqCI8
            @Override // java.lang.Runnable
            public final void run() {
                NewCallActivity.this.lambda$update$4$NewCallActivity();
            }
        });
    }
}
