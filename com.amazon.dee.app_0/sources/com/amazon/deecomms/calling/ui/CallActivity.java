package com.amazon.deecomms.calling.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.WebRTCViewRenderer;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.alexa.AlexaInterface;
import com.amazon.deecomms.app.RemoteViewManager;
import com.amazon.deecomms.app.SelfViewManager;
import com.amazon.deecomms.call.active.ActiveCallView;
import com.amazon.deecomms.call.active.ActiveVideoCallView;
import com.amazon.deecomms.call.incoming.IncomingCallView;
import com.amazon.deecomms.call.outgoing.OutgoingCallView;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.contracts.active.ActiveAudioCallPresenterContract;
import com.amazon.deecomms.calling.contracts.active.ActiveVideoCallPresenterContract;
import com.amazon.deecomms.calling.contracts.incoming.IncomingCallPresenterContract;
import com.amazon.deecomms.calling.contracts.outgoing.OutgoingCallPresenterContract;
import com.amazon.deecomms.calling.controller.CallActionsDispatcher;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallTimerManager;
import com.amazon.deecomms.calling.controller.CallingContentProviderNotifier;
import com.amazon.deecomms.calling.controller.CommandProcessor;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.enums.CallState;
import com.amazon.deecomms.calling.enums.CallType;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsMenuPresenter;
import com.amazon.deecomms.calling.incallexperiences.effects.EffectsState;
import com.amazon.deecomms.calling.incallexperiences.effects.ReactionsMenuPresenter;
import com.amazon.deecomms.calling.model.CallModel;
import com.amazon.deecomms.calling.presenters.active.ActiveEnhancedProcessingAudioPresenter;
import com.amazon.deecomms.calling.presenters.active.ActiveEnhancedProcessingVideoPresenter;
import com.amazon.deecomms.calling.presenters.incoming.IncomingEnhancedProcessingAudioPresenter;
import com.amazon.deecomms.calling.presenters.incoming.IncomingEnhancedProcessingVideoPresenter;
import com.amazon.deecomms.calling.presenters.outgoing.OutgoingEnhancedProcessingAudioPresenter;
import com.amazon.deecomms.calling.presenters.outgoing.OutgoingEnhancedProcessingVideoPresenter;
import com.amazon.deecomms.calling.receiver.HeadsetPluggedBroadcastReceiver;
import com.amazon.deecomms.calling.receiver.PowerButtonPressReceiver;
import com.amazon.deecomms.calling.telecom.TelecomBridge;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioManager;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioRouteObservable;
import com.amazon.deecomms.calling.ui.ep.ActiveEnhancedProcessingVideoCallFragment;
import com.amazon.deecomms.calling.ui.ep.IncomingEnhancedProcessingVideoCallFragment;
import com.amazon.deecomms.calling.ui.ep.OutgoingEnhancedProcessingVideoCallFragment;
import com.amazon.deecomms.calling.ui.ep.audio.ActiveEnhancedProcessingAudioCallFragment;
import com.amazon.deecomms.calling.ui.ep.audio.IncomingEnhancedProcessingAudioCallFragment;
import com.amazon.deecomms.calling.ui.ep.audio.OutgoingEnhancedProcessingAudioCallFragment;
import com.amazon.deecomms.calling.ui.listener.VideoDisplayListener;
import com.amazon.deecomms.calling.ui.listener.VideoStreamingListener;
import com.amazon.deecomms.calling.util.AmcCallUtil;
import com.amazon.deecomms.calling.util.CallHistoryHelper;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.calling.video.display.RemoteVideoDisplayPresenter;
import com.amazon.deecomms.calling.video.display.SelfVideoDisplayPresenter;
import com.amazon.deecomms.calling.video.display.VideoDisplayInterface;
import com.amazon.deecomms.calling.video.streaming.RemoteVideoStreamingPresenter;
import com.amazon.deecomms.calling.video.streaming.SelfVideoStreamingPresenter;
import com.amazon.deecomms.calling.video.streaming.VideoStreamingPresenterInterface;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.ui.helper.ActivitiesManager;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.audio.AudioStateObservable;
import com.amazon.deecomms.media.audio.CallMediaControlFacade;
import com.amazon.deecomms.perms.PermissionsHelper;
import com.amazon.deecomms.util.BundleUtils;
import com.amazon.deecomms.util.ChangeOrientationListener;
import com.amazon.deecomms.util.DeviceInfo;
import com.amazon.deecomms.util.LogsUtil;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
@Deprecated
/* loaded from: classes12.dex */
public class CallActivity extends AppCompatActivity implements VideoStreamingListener, VideoDisplayListener {
    private static final int LAYOUT_FLAGS = 4352;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallActivity.class);
    @Inject
    ActivitiesManager activitiesManager;
    @Inject
    AlexaInterface alexaInterface;
    @Inject
    AudioStateObservable audioStateObservable;
    @Inject
    CallActionsDispatcher callActionsDispatcher;
    @Inject
    CallHistoryHelper callHistoryHelper;
    @NonNull
    private String callID;
    @Inject
    CallManager callManager;
    @Inject
    CallMediaControlFacade callMediaControlFacade;
    @Inject
    CallTimerManager callTimerManager;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommandProcessor commandProcessor;
    @Inject
    CommsNotificationManager commsNotificationManager;
    @Inject
    DeviceCallingService deviceCallingService;
    @Inject
    EffectsState effectsState;
    @Inject
    EnhancedProcessingStateObservable enhancedProcessingStateObservable;
    private HeadsetPluggedBroadcastReceiver headsetPluggedBroadcastReceiver;
    private boolean isEnhancedProcessingEnabled;
    @Inject
    AlexaAudioPlayer mAlexaAudioPlayer;
    private AudioManager mAudioManager;
    private String mCalleeCommsId;
    private String mCallerCommsId;
    CallingContentProviderNotifier mCallingContentProviderNotifier;
    private FrameLayout mCallingViewContainer;
    @Inject
    Context mContext;
    private String mFragmentKey;
    private int mLastRotation;
    private OrientationEventListener mOrientationEventListener;
    private String mRemoteParticipantName;
    private RelativeLayout mRemoteRelativeLayout;
    private RemoteViewManager mRemoteViewManager;
    private LinearLayout mScrim;
    private RelativeLayout mSelfRelativeLayout;
    private SelfViewManager mSelfViewManager;
    private ImageView mToggleCamera;
    private WindowManager mWindowManager;
    @Inject
    NameChangeObservable nameChangeObservable;
    private OptInUI optin;
    @Inject
    PipVisibilityObservable pipVisibilityObservable;
    private PowerButtonPressReceiver powerButtonPressReceiver;
    @Inject
    RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;
    @Inject
    TelecomBridge telecomBridge;
    @Inject
    TelecomCallAudioManager telecomCallAudioManager;
    @Inject
    TelecomCallAudioRouteObservable telecomCallAudioRouteObservable;
    private BroadcastReceiver callPermissionRequestReceiver = new BroadcastReceiver() { // from class: com.amazon.deecomms.calling.ui.CallActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String[] permissionListForAudio;
            if (intent != null && Constants.CALL_PERMISSION_REQUEST_ACTION.equals(intent.getAction())) {
                boolean booleanExtra = intent.getBooleanExtra("IS_VIDEO_CALL", false);
                String stringExtra = intent.getStringExtra(Constants.PERMISSION_REQUEST_SCREEN_NAME);
                Context context2 = CallActivity.this.mContext;
                if (booleanExtra) {
                    permissionListForAudio = PermissionsHelper.getPermissionListForVideoCalling();
                } else {
                    permissionListForAudio = PermissionsHelper.getPermissionListForAudio();
                }
                String[] checkPermissions = PermissionsHelper.checkPermissions(context2, permissionListForAudio);
                if (checkPermissions.length <= 0) {
                    return;
                }
                PermissionsHelper.requestPermission(CallActivity.this, PermissionsHelper.getDeniedCallingPermissionsRationale(CallActivity.this.mContext, booleanExtra), checkPermissions, booleanExtra ? 7 : 1, booleanExtra ? MetricKeys.ALERT_PERM_MIC_AND_CAMERA : MetricKeys.ALERT_PERM_MIC, stringExtra, AlertSource.newClassSource(CallActivity.class.getName()), booleanExtra, (DialogInterface.OnDismissListener) null);
            }
        }
    };
    @NonNull
    private CallType mCallType = CallType.NONE;
    @NonNull
    private String mCallProvider = "";
    private boolean initSurfaceViews = false;
    private boolean mIsVideoOrDropInVideoCall = false;
    private boolean isGroupCall = false;

    /* renamed from: com.amazon.deecomms.calling.ui.CallActivity$4  reason: invalid class name */
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
        callModel.setRemoteParticipantName(CallViewUtils.getRemoteParticipantDisplayName(getIntent().getExtras(), this.mContext));
        callModel.setCallStatus(CallViewUtils.getCallStatus(getIntent().getExtras(), this.mContext));
        return callModel;
    }

    @NonNull
    private boolean checkCallPermissions(@NonNull int i) {
        return CallUtils.checkPermissions(this, AlertSource.newClassSource(CallActivity.class.getName()), i, this.mIsVideoOrDropInVideoCall, new DialogInterface.OnDismissListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$CallActivity$k5Ynk-9OyuWn5mcY_q6MLqw6H7M
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                CallActivity.this.lambda$checkCallPermissions$5$CallActivity(dialogInterface);
            }
        }, this.mContext);
    }

    private String getCallUIFragmentKey() {
        int ordinal = this.sipClientState.getCallState().ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal == 4) {
                    return Constants.FRAGMENT_INCOMING_CALL_KEY;
                }
                if (ordinal != 5) {
                    if (ordinal == 6) {
                        return Constants.FRAGMENT_ACTIVE_CALL_KEY;
                    }
                    LOG.i("Invalid SIP client state. Returning null Intent");
                    return null;
                }
            }
            return Constants.FRAGMENT_OUTGOING_CALL_KEY;
        }
        return Constants.FRAGMENT_CALL_INITIATED_KEY;
    }

    private VideoDisplayInterface getVideoDisplayInterface(@NonNull CallState callState) {
        if (callState == CallState.ACTIVE) {
            return new RemoteVideoDisplayPresenter(this.mRemoteViewManager, getApplicationContext());
        }
        return new SelfVideoDisplayPresenter(this.mSelfViewManager);
    }

    private VideoStreamingPresenterInterface getVideoPresenter(@NonNull CallState callState, @NonNull CallType callType) {
        if (callState == CallState.ACTIVE) {
            return new RemoteVideoStreamingPresenter(this.mRemoteViewManager);
        }
        return new SelfVideoStreamingPresenter(this.mSelfViewManager);
    }

    private void initOrientationListener(final ActiveCallView activeCallView) {
        LOG.i("Initializing orientation listener");
        this.mOrientationEventListener = new OrientationEventListener(this, 3) { // from class: com.amazon.deecomms.calling.ui.CallActivity.2
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i) {
                int i2;
                int rotation = CallActivity.this.mWindowManager.getDefaultDisplay().getRotation();
                if (CallActivity.this.mLastRotation != rotation) {
                    MetricsHelper.recordOrientationMetric(MetricKeys.CALL_SCREEN_ORIENTATION_ROTATE, rotation);
                    if (CallActivity.this.sipClientState.isEnhancedProcessedCall()) {
                        AmcCallUtil.sendOrientationChangeEvent(CallActivity.this.sipClientState.getCurrentActiveCall(), CallActivity.this.mLastRotation, rotation);
                    }
                    CallActivity.this.mLastRotation = rotation;
                    CallActivity.this.mSelfViewManager.minimizeSelfView(rotation);
                    final LinearLayout linearLayout = (LinearLayout) CallActivity.this.findViewById(R.id.call_button_layout_left);
                    final LinearLayout linearLayout2 = (LinearLayout) CallActivity.this.findViewById(R.id.call_controls_layout_left);
                    final LinearLayout linearLayout3 = (LinearLayout) CallActivity.this.findViewById(R.id.call_button_layout_right);
                    final LinearLayout linearLayout4 = (LinearLayout) CallActivity.this.findViewById(R.id.call_controls_layout_right);
                    final LinearLayout linearLayout5 = (LinearLayout) CallActivity.this.findViewById(R.id.call_button_layout);
                    final LinearLayout linearLayout6 = (LinearLayout) CallActivity.this.findViewById(R.id.call_controls_layout);
                    final View findViewById = CallActivity.this.findViewById(R.id.call_screen_header_left);
                    final View findViewById2 = CallActivity.this.findViewById(R.id.call_screen_header_right);
                    final View findViewById3 = CallActivity.this.findViewById(R.id.call_screen_header);
                    final LinearLayout linearLayout7 = (LinearLayout) CallActivity.this.findViewById(R.id.reactions_layout_menu_landscape_left);
                    final LinearLayout linearLayout8 = (LinearLayout) CallActivity.this.findViewById(R.id.reactions_layout_menu_landscape_right);
                    final LinearLayout linearLayout9 = (LinearLayout) CallActivity.this.findViewById(R.id.reactions_layout_menu);
                    HashSet<View> hashSet = new HashSet<View>(2) { // from class: com.amazon.deecomms.calling.ui.CallActivity.2.1
                        {
                            add(linearLayout6);
                            add(linearLayout5);
                            add(findViewById3);
                            add(linearLayout9);
                        }
                    };
                    HashSet<View> hashSet2 = new HashSet<View>(2) { // from class: com.amazon.deecomms.calling.ui.CallActivity.2.2
                        {
                            add(linearLayout2);
                            add(linearLayout);
                            add(findViewById);
                            add(linearLayout7);
                        }
                    };
                    HashSet<View> hashSet3 = new HashSet<View>(2) { // from class: com.amazon.deecomms.calling.ui.CallActivity.2.3
                        {
                            add(linearLayout4);
                            add(linearLayout3);
                            add(findViewById2);
                            add(linearLayout8);
                        }
                    };
                    ((ActiveVideoCallView) activeCallView).updateRotationValue(rotation);
                    int buttonVisibility = ((ActiveVideoCallView) activeCallView).getButtonVisibility();
                    if (rotation == 1) {
                        if (linearLayout2 != null && findViewById != null && linearLayout7 != null) {
                            CallActivity.this.setViewVisibility(hashSet2, 0);
                            CallActivity.this.setViewVisibility(hashSet3, 4);
                            CallActivity.this.setViewVisibility(hashSet, 4);
                            ((ActiveVideoCallView) activeCallView).updateCallLayout(linearLayout, linearLayout2, findViewById, linearLayout7, buttonVisibility);
                        }
                        activeCallView.setSystemUiVisibility(4352);
                    } else if (rotation != 3) {
                        if (linearLayout6 == null || findViewById3 == null || linearLayout9 == null) {
                            i2 = 4352;
                        } else {
                            CallActivity.this.setViewVisibility(hashSet2, 4);
                            CallActivity.this.setViewVisibility(hashSet3, 4);
                            CallActivity.this.setViewVisibility(hashSet, 0);
                            i2 = 4352;
                            ((ActiveVideoCallView) activeCallView).updateCallLayout(linearLayout5, linearLayout6, findViewById3, linearLayout9, buttonVisibility);
                        }
                        activeCallView.setSystemUiVisibility(i2);
                    } else {
                        if (linearLayout4 != null && findViewById2 != null && linearLayout8 != null) {
                            CallActivity.this.setViewVisibility(hashSet2, 4);
                            CallActivity.this.setViewVisibility(hashSet3, 0);
                            CallActivity.this.setViewVisibility(hashSet, 4);
                            ((ActiveVideoCallView) activeCallView).updateCallLayout(linearLayout3, linearLayout4, findViewById2, linearLayout8, buttonVisibility);
                        }
                        activeCallView.setSystemUiVisibility(4352);
                    }
                }
            }
        };
        if (this.mOrientationEventListener.canDetectOrientation()) {
            MetricsHelper.recordOrientationMetric(MetricKeys.CALL_SCREEN_ORIENTATION_OPEN, this.mWindowManager.getDefaultDisplay().getRotation());
            this.mOrientationEventListener.enable();
        }
    }

    private void initializeSurfaceViewManager(DeviceCallingService deviceCallingService) {
        LOG.i(" Initializing Surface Views for Video Calling... ");
        this.mSelfViewManager = new SelfViewManager(this.mSelfRelativeLayout, deviceCallingService, this.mScrim, getApplicationContext(), this.realTimeTextEnablementAuthority);
        this.mRemoteViewManager = new RemoteViewManager(this.mRemoteRelativeLayout, deviceCallingService);
        this.initSurfaceViews = true;
    }

    private void launchFragment(String str) {
        String str2;
        CallActivity callActivity;
        CallActivity callActivity2;
        CallActivity callActivity3;
        ReactionsMenuPresenter reactionsMenuPresenter;
        GeneratedOutlineSupport.outline4(" Launching Fragment ", str, LOG);
        if (this.mIsVideoOrDropInVideoCall && !this.initSurfaceViews) {
            LOG.i(" Setting up video call layout");
            setupLayoutForVideoCalling();
            initializeSurfaceViewManager(this.deviceCallingService);
        }
        CallModel buildCallModel = buildCallModel();
        if (Constants.FRAGMENT_ACTIVE_CALL_KEY.equals(str) && this.callManager.isAnyActiveCallPresent()) {
            LOG.i("accepting incoming call request - checking permissions");
            if (checkCallPermissions(this.mIsVideoOrDropInVideoCall ? 7 : 1)) {
                LOG.i("User has necessary permissions to accept the call; proceeding");
                GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport.outline1("Enhanced Video Processing Enabled: "), this.isEnhancedProcessingEnabled, LOG);
                if (this.mIsVideoOrDropInVideoCall) {
                    LOG.i("Setting up Active video Call View");
                    if (this.isEnhancedProcessingEnabled && this.sipClientState.isEnhancedProcessedCall()) {
                        ActiveEnhancedProcessingVideoCallFragment activeEnhancedProcessingVideoCallFragment = new ActiveEnhancedProcessingVideoCallFragment();
                        activeEnhancedProcessingVideoCallFragment.setPresenter((ActiveVideoCallPresenterContract) new ActiveEnhancedProcessingVideoPresenter(activeEnhancedProcessingVideoCallFragment, activeEnhancedProcessingVideoCallFragment, buildCallModel, this.callActionsDispatcher, this.callHistoryHelper, this, this, this.sipClientState, this.callTimerManager, null, this.callMediaControlFacade, this.audioStateObservable, this.telecomCallAudioRouteObservable, this.alexaInterface, this.capabilitiesManager, this.nameChangeObservable, this.telecomCallAudioManager, this.telecomBridge, this.mAudioManager, this.enhancedProcessingStateObservable, this.pipVisibilityObservable, this.realTimeTextEnablementAuthority));
                        callActivity3 = this;
                        if (callActivity3.capabilitiesManager.isReactionsEnabled()) {
                            ReactionsMenuPresenter reactionsMenuPresenter2 = new ReactionsMenuPresenter(this, callActivity3.sipClientState, callActivity3.isGroupCall, callActivity3.mCallerCommsId, callActivity3.mCalleeCommsId, callActivity3.effectsState, callActivity3.capabilitiesManager);
                            activeEnhancedProcessingVideoCallFragment.setReactionsMenuPresenter(reactionsMenuPresenter2);
                            reactionsMenuPresenter = reactionsMenuPresenter2;
                        } else {
                            reactionsMenuPresenter = null;
                        }
                        if (callActivity3.capabilitiesManager.isWorldsEnabled()) {
                            activeEnhancedProcessingVideoCallFragment.setEffectsMenuPresenter(new EffectsMenuPresenter(this, callActivity3.sipClientState, callActivity3.isGroupCall, callActivity3.mCallerCommsId, callActivity3.mCalleeCommsId, reactionsMenuPresenter, callActivity3.effectsState, callActivity3.capabilitiesManager));
                        }
                        callActivity3.setupFragment(activeEnhancedProcessingVideoCallFragment, true);
                    } else {
                        callActivity3 = this;
                        setupActiveCallView();
                    }
                    str2 = str;
                    callActivity = callActivity3;
                } else {
                    LOG.i("Setting up Active call view");
                    if (this.isEnhancedProcessingEnabled && this.sipClientState.isEnhancedProcessedCall()) {
                        ActiveEnhancedProcessingAudioCallFragment activeEnhancedProcessingAudioCallFragment = new ActiveEnhancedProcessingAudioCallFragment();
                        activeEnhancedProcessingAudioCallFragment.setPresenter((ActiveAudioCallPresenterContract) new ActiveEnhancedProcessingAudioPresenter(activeEnhancedProcessingAudioCallFragment, activeEnhancedProcessingAudioCallFragment, buildCallModel, this.callActionsDispatcher, this.callHistoryHelper, this.sipClientState, this.callTimerManager, this.callManager, this.alexaInterface, this.callMediaControlFacade, this.audioStateObservable, this.nameChangeObservable, this.telecomCallAudioManager, this.telecomBridge, this.telecomCallAudioRouteObservable, this.realTimeTextEnablementAuthority));
                        callActivity2 = this;
                        callActivity2.setupFragment(activeEnhancedProcessingAudioCallFragment, false);
                    } else {
                        callActivity2 = this;
                        callActivity2.setupFragment(new ActiveCallFragment(), false);
                    }
                    str2 = str;
                    callActivity = callActivity2;
                }
            } else {
                str2 = str;
                callActivity = this;
            }
        } else {
            str2 = str;
            if (Constants.FRAGMENT_INCOMING_CALL_KEY.equals(str2)) {
                if (this.callManager.isAnyActiveCallPresent()) {
                    GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport.outline1("Enhanced Video Processing Enabled: "), this.isEnhancedProcessingEnabled, LOG);
                    if (this.mIsVideoOrDropInVideoCall) {
                        LOG.i("Setting up Incoming Video Call View");
                        if (this.isEnhancedProcessingEnabled && this.sipClientState.isEnhancedProcessedCall()) {
                            IncomingEnhancedProcessingVideoCallFragment incomingEnhancedProcessingVideoCallFragment = new IncomingEnhancedProcessingVideoCallFragment();
                            callActivity = this;
                            incomingEnhancedProcessingVideoCallFragment.setPresenter((IncomingCallPresenterContract) new IncomingEnhancedProcessingVideoPresenter(incomingEnhancedProcessingVideoCallFragment, buildCallModel, getIntent().getExtras(), this.nameChangeObservable, this.callActionsDispatcher, this, this.sipClientState));
                            callActivity.setupFragment(incomingEnhancedProcessingVideoCallFragment, true);
                        } else {
                            callActivity = this;
                            setupIncomingCallView();
                        }
                    } else {
                        callActivity = this;
                        LOG.i("Setting up Incoming call view");
                        if (callActivity.isEnhancedProcessingEnabled && callActivity.sipClientState.isEnhancedProcessedCall()) {
                            IncomingEnhancedProcessingAudioCallFragment incomingEnhancedProcessingAudioCallFragment = new IncomingEnhancedProcessingAudioCallFragment();
                            incomingEnhancedProcessingAudioCallFragment.setPresenter((IncomingCallPresenterContract) new IncomingEnhancedProcessingAudioPresenter(incomingEnhancedProcessingAudioCallFragment, buildCallModel, getIntent().getExtras(), callActivity.callActionsDispatcher, callActivity.nameChangeObservable, callActivity.sipClientState));
                            callActivity.setupFragment(incomingEnhancedProcessingAudioCallFragment, false);
                        } else {
                            callActivity.setupFragment(new IncomingCallFragment(), false);
                        }
                    }
                } else {
                    LOG.e("The application is invalid state and will restart.");
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(R.string.generic_error_title).setMessage(R.string.generic_error_msg).setCancelable(false).setPositiveButton(R.string.dialog_ok_button, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$CallActivity$NrZ_YPp7ax-cAU50AlSn5OwbSkA
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            CallActivity.this.lambda$launchFragment$2$CallActivity(dialogInterface, i);
                        }
                    });
                    if (this.capabilitiesManager.getDiagnosticsValue()) {
                        builder.setNeutralButton(getResources().getString(R.string.send_logs), new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$CallActivity$dxLLPQtoV9EcmYD71p78SDivGwU
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i) {
                                CallActivity.this.lambda$launchFragment$3$CallActivity(dialogInterface, i);
                            }
                        });
                    }
                    builder.create().show();
                    return;
                }
            } else {
                callActivity = this;
                if (Constants.FRAGMENT_OUTGOING_CALL_KEY.equals(str2)) {
                    LOG.i("Outgoing call request - checking permissions");
                    if (callActivity.checkCallPermissions(14)) {
                        LOG.i("User has camera & mic permissions to make the call; proceeding");
                        lambda$onRequestPermissionsResult$0$CallActivity();
                    }
                } else if (Constants.FRAGMENT_END_CALL_KEY.equals(str2)) {
                    if (callActivity.mIsVideoOrDropInVideoCall) {
                        LOG.i(" End Call Fragment");
                        callActivity.mCallingViewContainer.removeAllViews();
                        callActivity.mSelfViewManager.stop();
                        callActivity.mRemoteViewManager.stop();
                    }
                    callActivity.setupFragment(new EndCallFragment(), false);
                }
            }
        }
        callActivity.mFragmentKey = str2;
    }

    private void launchFragmentBasedOnIntent(Intent intent) {
        String callUIFragmentKey = getCallUIFragmentKey();
        Bundle extras = intent.getExtras();
        this.mIsVideoOrDropInVideoCall = CallUtils.isVideoOrDropInVideoCall();
        if (callUIFragmentKey != null || (extras != null && extras.containsKey(Constants.LAUNCH_FRAGMENT_KEY))) {
            if (callUIFragmentKey == null) {
                callUIFragmentKey = (String) extras.get(Constants.LAUNCH_FRAGMENT_KEY);
            }
            intent.putExtra(Constants.LAUNCH_FRAGMENT_KEY, callUIFragmentKey);
            if (callUIFragmentKey == null || callUIFragmentKey.equals(this.mFragmentKey) || !this.callManager.isCallUIVisible()) {
                return;
            }
            launchFragment(callUIFragmentKey);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: makeOutgoingCall */
    public void lambda$onRequestPermissionsResult$0$CallActivity() {
        if (!Utils.shouldAllowAlexaCall(this.mContext)) {
            LOG.i("Active call exists - do not make outgoing call");
        } else if (this.optin.isNeeded(this.sipClientState, this.isGroupCall)) {
            this.optin.show(Constants.OUTGOING, this);
        } else {
            CallModel buildCallModel = buildCallModel();
            GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport.outline1("Enhanced Video Processing Enabled: "), this.isEnhancedProcessingEnabled, LOG);
            if (this.mIsVideoOrDropInVideoCall) {
                LOG.i("Setting up Outgoing Call View");
                if (this.isEnhancedProcessingEnabled && this.sipClientState.isEnhancedProcessedCall()) {
                    OutgoingEnhancedProcessingVideoCallFragment outgoingEnhancedProcessingVideoCallFragment = new OutgoingEnhancedProcessingVideoCallFragment();
                    outgoingEnhancedProcessingVideoCallFragment.setPresenter((OutgoingCallPresenterContract) new OutgoingEnhancedProcessingVideoPresenter(outgoingEnhancedProcessingVideoCallFragment, this, buildCallModel, getIntent().getExtras(), this.callActionsDispatcher, this.callMediaControlFacade, this.nameChangeObservable));
                    setupFragment(outgoingEnhancedProcessingVideoCallFragment, true);
                    return;
                }
                setupOutgoingCallView();
            } else if (this.isEnhancedProcessingEnabled && this.sipClientState.isEnhancedProcessedCall()) {
                OutgoingEnhancedProcessingAudioCallFragment outgoingEnhancedProcessingAudioCallFragment = new OutgoingEnhancedProcessingAudioCallFragment();
                outgoingEnhancedProcessingAudioCallFragment.setPresenter((OutgoingCallPresenterContract) new OutgoingEnhancedProcessingAudioPresenter(outgoingEnhancedProcessingAudioCallFragment, buildCallModel, getIntent().getExtras(), this.sipClientState, this.callActionsDispatcher, this.audioStateObservable, this.nameChangeObservable, this.callMediaControlFacade));
                setupFragment(outgoingEnhancedProcessingAudioCallFragment, false);
            } else {
                setupFragment(new OutgoingCallFragment(), false);
            }
        }
    }

    private void resetSipClientState() {
        if (this.sipClientState.getCallState() == SipClientState.CallState.CALLING_INITIATED || this.sipClientState.getCallState() == SipClientState.CallState.CALLING) {
            this.sipClientState.setCallState(SipClientState.CallState.INACTIVE);
        }
    }

    private void restartApplication() {
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        launchIntentForPackage.addFlags(67108864);
        startActivity(launchIntentForPackage);
        finish();
    }

    private void setCallingView(View view) {
        this.mCallingViewContainer.removeAllViews();
        this.mCallingViewContainer.addView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceOrientation() {
        if (DeviceInfo.isPhone(getApplicationContext())) {
            setRequestedOrientation(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setViewVisibility(@NonNull Set<View> set, int i) {
        for (View view : set) {
            if (view != null) {
                view.setVisibility(i);
            }
        }
    }

    private void setupActiveCallView() {
        ReactionsMenuPresenter reactionsMenuPresenter;
        int rotation = this.mWindowManager.getDefaultDisplay().getRotation();
        CommsLogger commsLogger = LOG;
        commsLogger.i(" Setup ActiveCallView for rotation " + rotation);
        this.mSelfViewManager = new SelfViewManager(this.mSelfRelativeLayout, this.deviceCallingService, this.mScrim, getApplicationContext(), this.realTimeTextEnablementAuthority);
        this.mSelfViewManager.start();
        ActiveCallView activeCallView = ActiveCallView.getInstance(this, this.mSelfViewManager, this.mRemoteViewManager, this.mToggleCamera, rotation, this.capabilitiesManager.isThemedUIEnabled(), this.capabilitiesManager.isMosaicThemingEnabled());
        if (this.capabilitiesManager.isReactionsEnabled()) {
            reactionsMenuPresenter = new ReactionsMenuPresenter(this, this.sipClientState, this.isGroupCall, this.mCallerCommsId, this.mCalleeCommsId, this.effectsState, this.capabilitiesManager);
            ((ActiveVideoCallView) activeCallView).setReactionsMenuPresenter(reactionsMenuPresenter);
        } else {
            reactionsMenuPresenter = null;
        }
        ReactionsMenuPresenter reactionsMenuPresenter2 = reactionsMenuPresenter;
        if (this.capabilitiesManager.isWorldsEnabled()) {
            ((ActiveVideoCallView) activeCallView).setEffectsMenuPresenter(new EffectsMenuPresenter(this, this.sipClientState, this.isGroupCall, this.mCallerCommsId, this.mCalleeCommsId, reactionsMenuPresenter2, this.effectsState, this.capabilitiesManager));
        }
        activeCallView.bind(this.mRemoteParticipantName);
        activeCallView.setChangeOrientationListener(new ChangeOrientationListener() { // from class: com.amazon.deecomms.calling.ui.CallActivity.3
            @Override // com.amazon.deecomms.util.ChangeOrientationListener
            public void onRequestChangeOrientation(int i) {
                CallActivity.this.setRequestedOrientation(i);
            }

            @Override // com.amazon.deecomms.util.ChangeOrientationListener
            public void restoreOrientation() {
                CallActivity.this.setDeviceOrientation();
                CallActivity.this.setOrientation();
            }
        });
        setOrientation();
        initOrientationListener(activeCallView);
        this.mCallingViewContainer.removeAllViews();
        this.mCallingViewContainer.addView(activeCallView);
        this.mRemoteViewManager.start();
        this.mSelfRelativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$CallActivity$DH80PQD1ySDB4sAr6R9PeXvaei8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CallActivity.this.lambda$setupActiveCallView$4$CallActivity(view);
            }
        });
        this.mToggleCamera.bringToFront();
    }

    private void setupFragment(Fragment fragment, boolean z) {
        if (this.mCallingViewContainer != null && this.mSelfViewManager != null && this.mRemoteViewManager != null) {
            LOG.i("Clearing all views");
            this.mCallingViewContainer.removeAllViews();
            this.mScrim.setVisibility(8);
            if (!z) {
                this.mSelfViewManager.stop();
                this.mRemoteViewManager.stop();
            }
        }
        fragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(16908290, fragment).commitAllowingStateLoss();
    }

    private void setupIncomingCallView() {
        LOG.i(" Setup IncomingCallView ");
        IncomingCallView incomingCallView = IncomingCallView.getInstance(this, this.mSelfViewManager, this.capabilitiesManager, this.sipClientState, this.realTimeTextEnablementAuthority);
        incomingCallView.bindOnce(this.mCalleeCommsId, this.mCallerCommsId, this.mRemoteParticipantName, this);
        this.mCallingViewContainer.removeAllViews();
        this.mCallingViewContainer.addView(incomingCallView);
        this.mSelfViewManager.start();
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
        this.mCallingViewContainer = (FrameLayout) findViewById(R.id.calling_view_wrapper);
        this.mSelfRelativeLayout = (RelativeLayout) findViewById(R.id.selfViewContainer);
        this.mRemoteRelativeLayout = (RelativeLayout) findViewById(R.id.remoteViewContainer);
        this.mScrim = (LinearLayout) findViewById(R.id.scrim);
        this.mToggleCamera = (ImageView) findViewById(R.id.toggle_camera);
        getWindow().addFlags(128);
    }

    private void setupOutgoingCallView() {
        LOG.i(" Setup OutgoingCallView ");
        OutgoingCallView createInstance = OutgoingCallView.createInstance(this, this.mSelfViewManager, getIntent().getExtras(), this.capabilitiesManager.isThemedUIEnabled(), this.capabilitiesManager.isMosaicThemingEnabled());
        createInstance.bindOnce(this.mRemoteParticipantName, this.mCalleeCommsId);
        this.mCallingViewContainer.removeAllViews();
        this.mCallingViewContainer.addView(createInstance);
        this.mSelfViewManager.start();
    }

    boolean compareSipClientStateWithFragmentKey(@Nullable String str, @Nullable String str2) {
        return str2 == null || str == null || str2.equals(str);
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void hidePip(boolean z) {
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void hideScrim() {
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void hideSelfView() {
    }

    @VisibleForTesting
    void init(Bundle bundle) {
        if (!shouldAnswerNotification(bundle)) {
            return;
        }
        sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        this.optin = new OptInUI();
        if (bundle != null) {
            if (bundle.containsKey(Constants.CALL_ID)) {
                this.callID = bundle.getString(Constants.CALL_ID);
            }
            if (bundle.containsKey(Constants.CALL_TYPE)) {
                this.mCallType = CallType.fromBundle(bundle, Constants.CALL_TYPE);
            }
            if (bundle.containsKey(Constants.CALL_PROVIDER)) {
                this.mCallProvider = CallProvider.fromBundle(bundle, Constants.CALL_PROVIDER);
            }
            if (bundle.containsKey("COMMS_ID")) {
                this.mCalleeCommsId = bundle.getString("COMMS_ID");
            }
            if (bundle.containsKey(Constants.REMOTE_PARTICIPANT_NAME)) {
                this.mRemoteParticipantName = bundle.getString(Constants.REMOTE_PARTICIPANT_NAME);
            }
            if (bundle.containsKey(Constants.CALLEE_COMMS_ID)) {
                this.mCalleeCommsId = bundle.getString(Constants.CALLEE_COMMS_ID);
            }
            if (bundle.containsKey(Constants.CALLER_COMMS_ID)) {
                this.mCallerCommsId = bundle.getString(Constants.CALLER_COMMS_ID);
            }
            if (bundle.containsKey(Constants.LOCAL_COMMS_ID)) {
                this.mCallerCommsId = bundle.getString(Constants.LOCAL_COMMS_ID);
            }
            if (bundle.containsKey(Constants.GROUP_CALL)) {
                this.isGroupCall = bundle.getBoolean(Constants.GROUP_CALL);
            }
            if (bundle.getBoolean(Constants.SET_SIP_CLIENT_STATE, false)) {
                this.sipClientState.setCallId(this.callID);
                this.sipClientState.setCallState(SipClientState.CallState.CALLING);
                this.sipClientState.setDeviceGruu(bundle.getString(Constants.DEVICE_GRUU));
                this.sipClientState.setRemoteParticipantId(this.mCalleeCommsId);
                this.sipClientState.setRemoteParticipantName(this.mRemoteParticipantName);
                this.sipClientState.setCallType(this.mCallType);
                this.sipClientState.setCallProvider(this.mCallProvider);
            }
            if (!Constants.FRAGMENT_END_CALL_KEY.equals(bundle.getString(Constants.LAUNCH_FRAGMENT_KEY))) {
                setSpeakerphoneStatus();
            }
            if (!bundle.containsKey(Constants.NOTIFICATION_ID)) {
                return;
            }
            this.commsNotificationManager.cancelNotification(bundle.getInt(Constants.NOTIFICATION_ID));
            return;
        }
        LOG.e(" No extras found in Call Activity bundle ");
    }

    public /* synthetic */ void lambda$checkCallPermissions$5$CallActivity(DialogInterface dialogInterface) {
        LOG.d("Permissions dialog was shown and the user has cancelled or gone to settings");
        CallUtils.handleCallInitiationErrors(this.sipClientState);
        if (!isFinishing()) {
            LOG.d("Finishing activity as there is nothing to do without permissions");
            finish();
        }
    }

    public /* synthetic */ void lambda$launchFragment$2$CallActivity(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        restartApplication();
    }

    public /* synthetic */ void lambda$launchFragment$3$CallActivity(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        LogsUtil.saveLogsAndSendViaEmail(this);
        restartApplication();
    }

    public /* synthetic */ void lambda$onRequestPermissionsResult$1$CallActivity(DialogInterface dialogInterface) {
        LOG.d("Permission not availalbe dialog dismissed, finishing activity");
        finish();
    }

    public /* synthetic */ void lambda$setupActiveCallView$4$CallActivity(View view) {
        Call currentActiveCall = this.sipClientState.getCurrentActiveCall();
        if (currentActiveCall != null) {
            LOG.i("Switching camera");
            currentActiveCall.switchCamera();
            return;
        }
        LOG.w("No active call to switch camera");
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void maximizeVideo(boolean z) {
        boolean z2 = this.isEnhancedProcessingEnabled;
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void minimizeVideo(@NonNull int i, boolean z) {
        boolean z2 = this.isEnhancedProcessingEnabled;
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
        boolean equals = Constants.INCOMING.equals(intent.getStringExtra(Constants.SHOW_CALL_UI));
        if (i != 200) {
            return;
        }
        if (i2 == 10) {
            if (equals) {
                CallUtils.rejectCall(this);
            } else {
                CallUtils.cancelOutgoingCall(this);
            }
            finish();
        } else if (i2 != 20) {
        } else {
            if (equals) {
                CallUtils.acceptIncomingCall(this, this.mIsVideoOrDropInVideoCall);
                this.commandProcessor.enableVideoStreamInVideoCall(false);
                this.commandProcessor.enableVideoStreamInVideoCall(true);
                return;
            }
            lambda$onRequestPermissionsResult$0$CallActivity();
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

    @Override // com.amazon.deecomms.calling.ui.listener.VideoStreamingListener
    public void onCameraToggledToFront(@NonNull CallState callState, @NonNull CallType callType) {
        if (!this.isEnhancedProcessingEnabled) {
            return;
        }
        getVideoPresenter(callState, callType).turnFrontSelfVideoFeedOn();
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoStreamingListener
    public void onCameraToggledToRear(@NonNull CallState callState, @NonNull CallType callType) {
        if (!this.isEnhancedProcessingEnabled) {
            return;
        }
        getVideoPresenter(callState, callType).turnRearSelfVideoFeedOn();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        LOG.d("onCreate");
        super.onCreate(bundle);
        if (this.capabilitiesManager.isThemedUIEnabled()) {
            if (this.capabilitiesManager.isMosaicThemingEnabled()) {
                setTheme(R.style.Theme_Mosaic_Jasper_Dark);
            } else {
                ThemeUtil.setTheme(this);
            }
        }
        this.headsetPluggedBroadcastReceiver = new HeadsetPluggedBroadcastReceiver();
        this.powerButtonPressReceiver = new PowerButtonPressReceiver();
        setDeviceOrientation();
        this.mAudioManager = (AudioManager) getApplicationContext().getSystemService("audio");
        this.mWindowManager = (WindowManager) getApplicationContext().getSystemService("window");
        this.mCallingContentProviderNotifier = new CallingContentProviderNotifier(getApplicationContext());
        this.isEnhancedProcessingEnabled = this.capabilitiesManager.isEnhancedVideoProcessingEnabled();
        getWindow().addFlags(2654208);
        getWindow().getDecorView().setSystemUiVisibility(4352);
        init(getIntent().getExtras());
        this.activitiesManager.setTopActivity(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.media.AUDIO_BECOMING_NOISY");
        registerReceiver(this.headsetPluggedBroadcastReceiver, intentFilter);
        registerReceiver(this.powerButtonPressReceiver, new IntentFilter("android.intent.action.SCREEN_OFF"));
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.callPermissionRequestReceiver, new IntentFilter(Constants.CALL_PERMISSION_REQUEST_ACTION));
        launchFragmentBasedOnIntent(getIntent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LOG.d(" CallActivity On Destroy ");
        unregisterReceiver(this.headsetPluggedBroadcastReceiver);
        unregisterReceiver(this.powerButtonPressReceiver);
        LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.callPermissionRequestReceiver);
        String callUIFragmentKey = getCallUIFragmentKey();
        if (compareSipClientStateWithFragmentKey(callUIFragmentKey, this.mFragmentKey)) {
            if (CallUtils.shouldUpdateCallUIVisibility(callUIFragmentKey, this.mFragmentKey)) {
                this.activitiesManager.setTopActivity(null);
            }
            if (this.mIsVideoOrDropInVideoCall) {
                SelfViewManager selfViewManager = this.mSelfViewManager;
                if (selfViewManager != null) {
                    selfViewManager.stop();
                }
                RemoteViewManager remoteViewManager = this.mRemoteViewManager;
                if (remoteViewManager != null) {
                    remoteViewManager.stop();
                }
            }
        }
        OrientationEventListener orientationEventListener = this.mOrientationEventListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
            this.mOrientationEventListener = null;
        }
        if (getIntent().getBooleanExtra(Constants.KEY_DROP_IN_NOT_AVAILABLE, false)) {
            this.sipClientState.setCallType(CallType.NONE);
            this.sipClientState.setCallProvider("");
        }
        this.initSurfaceViews = false;
        this.mCallingContentProviderNotifier.notifyObservers();
        this.telecomCallAudioRouteObservable.deleteObservers();
        this.enhancedProcessingStateObservable.deleteObservers();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if ((i == 25 || i == 24) && Constants.FRAGMENT_INCOMING_CALL_KEY.equals(this.mFragmentKey)) {
            this.mAlexaAudioPlayer.stop(1);
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LOG.d(" CallActivity onNewIntent ");
        BundleUtils.merge(intent.getExtras(), getIntent().getExtras(), false);
        setIntent(intent);
        init(getIntent().getExtras());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.sipClientState.getCallState() == SipClientState.CallState.INACTIVE) {
            this.activitiesManager.setTopActivity(null);
        }
        OrientationEventListener orientationEventListener = this.mOrientationEventListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
        if (!isFinishing()) {
            String callUIFragmentKey = getCallUIFragmentKey();
            if (!CallUtils.shouldUpdateCallUIVisibility(callUIFragmentKey, this.mFragmentKey) || !compareSipClientStateWithFragmentKey(callUIFragmentKey, this.mFragmentKey)) {
                return;
            }
            this.callManager.setCallUINavigation(true);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (!PermissionsHelper.checkGrantedPermissions(iArr)) {
            LOG.i(" Permission denied ");
            if (i == 14) {
                CallUtils.handleCallInitiationErrors(this.sipClientState);
                PermissionsHelper.showPermissionNotAvailableDialog(this, PermissionsHelper.getDeniedCallingPermissionsRationale(this.mContext, true), MetricKeys.ALERT_PERM_MIC_AND_CAMERA, MetricKeys.SCREEN_NAME_OUTGOING_CALL, AlertSource.newClassSource(CallActivity.class.getName()), false, new DialogInterface.OnDismissListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$CallActivity$NTWv1c8Y9qwjfKQYBTImNRczBxA
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        CallActivity.this.lambda$onRequestPermissionsResult$1$CallActivity(dialogInterface);
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
            runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$CallActivity$4txSWL2DKGHrbNCK1NKZvVOE_no
                @Override // java.lang.Runnable
                public final void run() {
                    CallActivity.this.lambda$onRequestPermissionsResult$0$CallActivity();
                }
            });
        } else {
            LOG.i("Mic & Camera permissions granted. Checking opt-in preference");
            if (this.optin.isNeeded(this.sipClientState, this.isGroupCall)) {
                this.optin.show(Constants.OUTGOING, this);
                return;
            }
            GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport.outline1("Opt-in not required. Accepting video call : "), this.mIsVideoOrDropInVideoCall, LOG);
            CallUtils.acceptIncomingCall(this, this.mIsVideoOrDropInVideoCall);
            this.commandProcessor.enableVideoStreamInVideoCall(false);
            this.commandProcessor.enableVideoStreamInVideoCall(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (CallUtils.shouldUpdateCallUIVisibility(getCallUIFragmentKey(), this.mFragmentKey)) {
            this.callManager.setCallUIVisibility(true);
        }
        this.activitiesManager.setTopActivity(this);
        LOG.d(" CallActivity onResume ");
        launchFragmentBasedOnIntent(getIntent());
        this.mCallingContentProviderNotifier.notifyObservers();
        OrientationEventListener orientationEventListener = this.mOrientationEventListener;
        if (orientationEventListener != null) {
            orientationEventListener.enable();
        }
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void onSetBackground(boolean z, boolean z2, boolean z3) {
        boolean z4 = this.isEnhancedProcessingEnabled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        LOG.i(" onStart of CallActivity ");
        this.commandProcessor.enableVideoStreamInVideoCall(true);
        if (CallUtils.shouldUpdateCallUIVisibility(getCallUIFragmentKey(), this.mFragmentKey)) {
            this.callManager.setCallUIVisibility(true);
            this.callManager.setCallUINavigation(false);
        }
        if (this.mSelfViewManager == null || Constants.FRAGMENT_END_CALL_KEY.equals(this.mFragmentKey) || !this.mCallType.isVideo()) {
            return;
        }
        this.mSelfViewManager.start();
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoStreamingListener
    public void onStartStreamingVideo(@NonNull CallState callState, @NonNull CallType callType) {
        if (!this.isEnhancedProcessingEnabled) {
            return;
        }
        getVideoPresenter(callState, callType).startStreaming();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        LOG.i(" onStop ");
        String callUIFragmentKey = getCallUIFragmentKey();
        if (!CallUtils.shouldUpdateCallUIVisibility(callUIFragmentKey, this.mFragmentKey) || !compareSipClientStateWithFragmentKey(callUIFragmentKey, this.mFragmentKey)) {
            return;
        }
        this.callManager.setCallUIVisibility(false);
        this.commandProcessor.enableVideoStreamInVideoCall(false);
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoStreamingListener
    public void onStopStreamingVideo(@NonNull CallState callState, @NonNull CallType callType) {
        if (!this.isEnhancedProcessingEnabled) {
            return;
        }
        getVideoPresenter(callState, callType).stopStreaming();
    }

    public void setOrientation() {
        if (!this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
            setRequestedOrientation(-1);
        }
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void setScalingType(@NonNull WebRTCViewRenderer.ScalingType scalingType, boolean z) {
    }

    @VisibleForTesting
    void setSpeakerphoneStatus() {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Speakerphone is currently: ");
        outline1.append(this.mAudioManager.isSpeakerphoneOn());
        commsLogger.i(outline1.toString());
        if (!this.mCallType.isDropIn() || this.mAudioManager.isWiredHeadsetOn() || this.mAudioManager.isBluetoothScoOn() || this.mAudioManager.isBluetoothA2dpOn()) {
            return;
        }
        this.mAudioManager.setSpeakerphoneOn(true);
        CommsLogger commsLogger2 = LOG;
        StringBuilder outline12 = GeneratedOutlineSupport.outline1("Tried to turn speakerphone true; speakerphone was set to ");
        outline12.append(this.mAudioManager.isSpeakerphoneOn());
        commsLogger2.i(outline12.toString());
    }

    @VisibleForTesting
    boolean shouldAnswerNotification(Bundle bundle) {
        if (!Constants.FRAGMENT_OUTGOING_CALL_KEY.equals(bundle.getString(Constants.LAUNCH_FRAGMENT_KEY)) || Utils.shouldAllowAlexaCall(this.mContext)) {
            return true;
        }
        LOG.i("User requested call back, but an active call exists - do not answer notification");
        return false;
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void showPip(boolean z) {
    }

    @Override // com.amazon.deecomms.calling.ui.listener.VideoDisplayListener
    public void showScrim() {
    }
}
