package com.amazon.deecomms.calling.incallexperiences.storytime;

import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.log.annotation.Log;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelApplication;
import com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelEventListenerImpl;
import com.amazon.deecomms.calling.datachannel.CommsDataChannelEvent;
import com.amazon.deecomms.calling.datachannel.EventName;
import com.amazon.deecomms.calling.datachannel.InCallOrientation;
import com.amazon.deecomms.calling.datachannel.capabilities.screenevents.ReserveInCallControls;
import com.amazon.deecomms.calling.incallexperiences.actions.GetInCallApplicationActions;
import com.amazon.deecomms.calling.incallexperiences.actions.TriggerInCallApplicationAction;
import com.amazon.deecomms.calling.incallexperiences.actions.runnables.ApplicationActionsRunnable;
import com.amazon.deecomms.calling.incallexperiences.actions.runnables.TriggerActionRunnable;
import com.amazon.deecomms.calling.incallexperiences.model.ApplicationAction;
import com.amazon.deecomms.calling.incallexperiences.model.TriggerActionResponse;
import com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonPresenterContract;
import com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonViewContract;
import com.amazon.deecomms.calling.model.Boundary;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.util.ChangeOrientationListener;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes12.dex */
public class StoryTimeButtonPresenter implements StoryTimeButtonPresenterContract, AmcIncallDataChannelApplication {
    private static final String ACTION_ID = "StartStoryTime";
    private static final String APPLICATION_NAME = "StoryTime";
    private static final long RESPONSE_TIMEOUT_MS = 10000;
    private static CommsLogger log = CommsLogger.getLogger(StoryTimeButtonPresenter.class);
    private Call call;
    private ChangeOrientationListener changeOrientationListener;
    private AmcIncallDataChannelEventListenerImpl dataChannelEventListener;
    private boolean isSupported;
    private final String localParticipantCommsId;
    private final long responseTimeout;
    private StoryTimeButtonViewContract storyTimeButtonViewContract;
    private final StoryTimeLogic storyTimeLogic;
    private StoryTimeState storyTimeState;
    private Runnable timeoutRunnable;
    private final Handler uiHandler;

    /* renamed from: com.amazon.deecomms.calling.incallexperiences.storytime.StoryTimeButtonPresenter$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$calling$datachannel$InCallOrientation;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$calling$datachannel$capabilities$screenevents$ReserveInCallControls$Status;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$calling$incallexperiences$model$TriggerActionResponse$TriggerStatusCode = new int[TriggerActionResponse.TriggerStatusCode.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$calling$incallexperiences$model$TriggerActionResponse$TriggerStatusCode[TriggerActionResponse.TriggerStatusCode.VPC_IS_REQUIRED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$incallexperiences$model$TriggerActionResponse$TriggerStatusCode[TriggerActionResponse.TriggerStatusCode.AMC_OPT_IN_REQUIRED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$incallexperiences$model$TriggerActionResponse$TriggerStatusCode[TriggerActionResponse.TriggerStatusCode.SUCCESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$incallexperiences$model$TriggerActionResponse$TriggerStatusCode[TriggerActionResponse.TriggerStatusCode.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$amazon$deecomms$calling$datachannel$InCallOrientation = new int[InCallOrientation.values().length];
            try {
                $SwitchMap$com$amazon$deecomms$calling$datachannel$InCallOrientation[InCallOrientation.PORTRAIT_UP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$datachannel$InCallOrientation[InCallOrientation.PORTRAIT_DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$datachannel$InCallOrientation[InCallOrientation.LANDSCAPE_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$datachannel$InCallOrientation[InCallOrientation.LANDSCAPE_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            $SwitchMap$com$amazon$deecomms$calling$datachannel$capabilities$screenevents$ReserveInCallControls$Status = new int[ReserveInCallControls.Status.values().length];
            try {
                $SwitchMap$com$amazon$deecomms$calling$datachannel$capabilities$screenevents$ReserveInCallControls$Status[ReserveInCallControls.Status.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$datachannel$capabilities$screenevents$ReserveInCallControls$Status[ReserveInCallControls.Status.STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$calling$datachannel$capabilities$screenevents$ReserveInCallControls$Status[ReserveInCallControls.Status.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public class ApplicationActions extends ApplicationActionsRunnable {
        public ApplicationActions() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MetricsHelper.stopTimerMetric(TimerMetric.generateOperational(MetricKeys.STORYTIME_GET_ACTIONS_LATENCY));
            if (StoryTimeButtonPresenter.this.storyTimeButtonViewContract == null) {
                return;
            }
            boolean z = false;
            Iterator<ApplicationAction> it2 = this.applicationActions.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (StoryTimeButtonPresenter.ACTION_ID.equalsIgnoreCase(it2.next().getActionId())) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            StoryTimeButtonPresenter storyTimeButtonPresenter = StoryTimeButtonPresenter.this;
            storyTimeButtonPresenter.isSupported = z & storyTimeButtonPresenter.isSupported;
            if (StoryTimeButtonPresenter.this.isSupported) {
                StoryTimeButtonPresenter.log.i("StoryTime is supported. Showing StoryTime button.");
                StoryTimeButtonPresenter.this.storyTimeButtonViewContract.showFloatingStoryTimeButton();
                return;
            }
            StoryTimeButtonPresenter.log.i("StoryTime is not supported. Not showing StoryTime button.");
            StoryTimeButtonPresenter.this.storyTimeButtonViewContract.hideFloatingStoryTimeButton();
        }
    }

    /* loaded from: classes12.dex */
    public class TriggerAction extends TriggerActionRunnable {
        public TriggerAction() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MetricsHelper.stopTimerMetric(TimerMetric.generateOperational(MetricKeys.STORYTIME_TRIGGER_ACTION_LATENCY));
            if (StoryTimeButtonPresenter.this.storyTimeButtonViewContract == null) {
                return;
            }
            CommsLogger commsLogger = StoryTimeButtonPresenter.log;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Start StoryTime Triggered returned with status code: ");
            outline1.append(this.statusCode.getValue());
            commsLogger.i(outline1.toString());
            int ordinal = this.statusCode.ordinal();
            if (ordinal == 0) {
                StoryTimeButtonPresenter.this.storyTimeLogic.sendApplicationEvent(EventName.BEGIN_APPLICATION);
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.STORYTIME_TRIGGER_RESULT_SUCCESS);
            } else if (ordinal == 1) {
                StoryTimeButtonPresenter.this.uiHandler.removeCallbacks(StoryTimeButtonPresenter.this.timeoutRunnable);
                StoryTimeButtonPresenter.this.storyTimeButtonViewContract.showStoryTimeMessage(R.string.story_time_vpc_generic_error);
                StoryTimeButtonPresenter.this.storyTimeButtonViewContract.showFloatingStoryTimeButton();
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.STORYTIME_TRIGGER_RESULT_VPC_NOT_ENABLED);
            } else if (ordinal == 2) {
                StoryTimeButtonPresenter.this.uiHandler.removeCallbacks(StoryTimeButtonPresenter.this.timeoutRunnable);
                StoryTimeButtonPresenter.this.storyTimeButtonViewContract.showStoryTimeMessage(R.string.story_time_amc_generic_error);
                StoryTimeButtonPresenter.this.storyTimeButtonViewContract.showFloatingStoryTimeButton();
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.STORYTIME_TRIGGER_RESULT_AMC_NOT_ENABLED);
            } else {
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.STORYTIME_TRIGGER_RESULT_UNKNOWN_REASON);
            }
        }
    }

    public StoryTimeButtonPresenter(@NonNull StoryTimeButtonViewContract storyTimeButtonViewContract, @NonNull Call call, boolean z) {
        this.timeoutRunnable = new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.storytime.StoryTimeButtonPresenter.1
            @Override // java.lang.Runnable
            public void run() {
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.STORYTIME_FAIL_TIMEOUT);
                if (StoryTimeButtonPresenter.this.storyTimeButtonViewContract == null) {
                    return;
                }
                StoryTimeButtonPresenter.log.i("Timed out while trying to start StoryTime.");
                StoryTimeButtonPresenter.this.storyTimeButtonViewContract.showStoryTimeMessage(R.string.story_time_generic_error);
                StoryTimeButtonPresenter.this.storyTimeButtonViewContract.showFloatingStoryTimeButton();
            }
        };
        this.storyTimeButtonViewContract = storyTimeButtonViewContract;
        this.call = call;
        this.uiHandler = new Handler(Looper.getMainLooper());
        this.responseTimeout = 10000L;
        this.isSupported = z;
        this.storyTimeLogic = new StoryTimeLogic(this.call);
        this.localParticipantCommsId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("StoryTimePresenter", true);
        this.storyTimeState = StoryTimeState.INSTANCE;
        this.dataChannelEventListener = AmcIncallDataChannelEventListenerImpl.getInstance(this.call);
        this.dataChannelEventListener.registerApplication(this);
        if (this.storyTimeState.isStoryTimeEnabled()) {
            onStoryTimeStarted(this.storyTimeState.getOrientation(), this.storyTimeState.getBoundary().getPoints());
        } else if (!this.isSupported) {
        } else {
            MetricsHelper.startTimerMetric(TimerMetric.generateOperational(MetricKeys.STORYTIME_GET_ACTIONS_LATENCY));
            new GetInCallApplicationActions(this.localParticipantCommsId, this.call.getCallId(), new ApplicationActions()).execute(new Void[0]);
        }
    }

    @Log(message = "StoryTime Started")
    private void onStoryTimeStarted(InCallOrientation inCallOrientation, List<Boundary.Point> list) {
        if (list == null) {
            log.i("ReserveInCallControls event received without screenPortion");
            this.storyTimeLogic.sendApplicationEvent(EventName.EXIT_APPLICATION);
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.STORYTIME_FAIL_NO_SCREEN_PORTION);
            return;
        }
        if (inCallOrientation != null && this.changeOrientationListener != null) {
            int ordinal = inCallOrientation.ordinal();
            if (ordinal == 0) {
                this.changeOrientationListener.onRequestChangeOrientation(9);
            } else if (ordinal == 1) {
                this.changeOrientationListener.onRequestChangeOrientation(1);
            } else if (ordinal == 2) {
                this.changeOrientationListener.onRequestChangeOrientation(8);
            } else if (ordinal == 3) {
                this.changeOrientationListener.onRequestChangeOrientation(0);
            }
        }
        CommsLogger commsLogger = log;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Reserving area for in-call controls: ");
        outline1.append(this.storyTimeState.getBoundary());
        commsLogger.i(outline1.toString());
        this.storyTimeState.setOrientation(inCallOrientation);
        this.storyTimeState.getBoundary().setPoints(list);
        this.storyTimeState.setStoryTimeEnabled(true);
        this.storyTimeButtonViewContract.startStoryTime(false);
        this.uiHandler.removeCallbacks(this.timeoutRunnable);
        MetricsHelper.startTimerMetric(TimerMetric.generateOperational(MetricKeys.STORYTIME_DURATION));
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.STORYTIME_STARTED);
    }

    @Log(message = "Processing ReserveInCallControls Events For StoryTime")
    private void processReserveInCallControlsEvents(ReserveInCallControls reserveInCallControls) {
        int ordinal = reserveInCallControls.getStatus().ordinal();
        if (ordinal == 0) {
            onStoryTimeStarted(reserveInCallControls.getOrientation(), reserveInCallControls.getScreenPortion());
        } else if (ordinal != 1) {
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.STORYTIME_FAIL_UNKNOWN_REASON);
            CommsLogger commsLogger = log;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("ReserveInCallControls event received with error: ");
            outline1.append(reserveInCallControls.getMessage());
            commsLogger.i(outline1.toString());
            this.storyTimeButtonViewContract.showStoryTimeMessage(R.string.story_time_generic_error);
            exitStoryTime();
        } else {
            exitStoryTime();
        }
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonPresenterContract
    @Log(message = "Exiting StoryTime")
    public void exitStoryTime() {
        this.storyTimeState.setStoryTimeEnabled(false);
        if (this.isSupported) {
            this.storyTimeButtonViewContract.showFloatingStoryTimeButton();
        }
        ChangeOrientationListener changeOrientationListener = this.changeOrientationListener;
        if (changeOrientationListener != null) {
            changeOrientationListener.restoreOrientation();
        }
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.STORYTIME_STOPPED);
        MetricsHelper.stopTimerMetric(TimerMetric.generateOperational(MetricKeys.STORYTIME_DURATION));
    }

    @Override // com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelApplication
    public String getApplicationName() {
        return APPLICATION_NAME;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonPresenterContract
    public boolean isStoryTimeEnabled() {
        return this.storyTimeState.isStoryTimeEnabled();
    }

    @Override // com.amazon.deecomms.calling.datachannel.AmcIncallDataChannelApplication
    public void onDataChannelEventReceived(CommsDataChannelEvent commsDataChannelEvent) {
        ReserveInCallControls processStoryTimeDataChannelEvents = this.storyTimeLogic.processStoryTimeDataChannelEvents(commsDataChannelEvent);
        if (processStoryTimeDataChannelEvents != null) {
            processReserveInCallControlsEvents(processStoryTimeDataChannelEvents);
        }
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonPresenterContract
    @Log(message = "Processing Touch Event For StoryTime")
    public boolean processStoryTimeTouchEvents(MotionEvent motionEvent) {
        return this.storyTimeLogic.processTouchEvents(motionEvent, this.storyTimeState.getBoundary());
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonPresenterContract
    @Log(message = "Releasing StoryTime resources")
    public void releaseStoryTimeResources() {
        this.storyTimeState.clear();
        AmcIncallDataChannelEventListenerImpl amcIncallDataChannelEventListenerImpl = this.dataChannelEventListener;
        if (amcIncallDataChannelEventListenerImpl != null) {
            amcIncallDataChannelEventListenerImpl.unregisterDataChannelEventListener();
        }
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonPresenterContract
    @Log(message = "setChangeOrientationListener")
    public void setChangeOrientationListener(ChangeOrientationListener changeOrientationListener) {
        this.changeOrientationListener = changeOrientationListener;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonPresenterContract
    @Log(message = "Starting StoryTime")
    public void startStoryTime() {
        startStoryTime(new TriggerInCallApplicationAction(this.localParticipantCommsId, this.call.getCallId(), ACTION_ID, new TriggerAction()));
    }

    @VisibleForTesting
    void startStoryTime(TriggerInCallApplicationAction triggerInCallApplicationAction) {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.STORYTIME_CLICKED);
        this.storyTimeButtonViewContract.startStoryTime(true);
        MetricsHelper.startTimerMetric(TimerMetric.generateOperational(MetricKeys.STORYTIME_TRIGGER_ACTION_LATENCY));
        triggerInCallApplicationAction.execute(new Void[0]);
        this.uiHandler.postDelayed(this.timeoutRunnable, this.responseTimeout);
    }

    @VisibleForTesting
    StoryTimeButtonPresenter(StoryTimeButtonViewContract storyTimeButtonViewContract, Call call, boolean z, StoryTimeState storyTimeState, Handler handler, long j, StoryTimeLogic storyTimeLogic, String str, GetInCallApplicationActions getInCallApplicationActions, ChangeOrientationListener changeOrientationListener) {
        this.timeoutRunnable = new Runnable() { // from class: com.amazon.deecomms.calling.incallexperiences.storytime.StoryTimeButtonPresenter.1
            @Override // java.lang.Runnable
            public void run() {
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.STORYTIME_FAIL_TIMEOUT);
                if (StoryTimeButtonPresenter.this.storyTimeButtonViewContract == null) {
                    return;
                }
                StoryTimeButtonPresenter.log.i("Timed out while trying to start StoryTime.");
                StoryTimeButtonPresenter.this.storyTimeButtonViewContract.showStoryTimeMessage(R.string.story_time_generic_error);
                StoryTimeButtonPresenter.this.storyTimeButtonViewContract.showFloatingStoryTimeButton();
            }
        };
        this.storyTimeButtonViewContract = storyTimeButtonViewContract;
        this.call = call;
        this.uiHandler = handler;
        this.responseTimeout = j;
        this.storyTimeState = storyTimeState;
        this.isSupported = z;
        this.storyTimeLogic = storyTimeLogic;
        this.localParticipantCommsId = str;
        this.changeOrientationListener = changeOrientationListener;
        if (this.storyTimeState.isStoryTimeEnabled()) {
            onStoryTimeStarted(this.storyTimeState.getOrientation(), this.storyTimeState.getBoundary().getPoints());
        } else if (!this.isSupported) {
        } else {
            MetricsHelper.startTimerMetric(TimerMetric.generateOperational(MetricKeys.STORYTIME_GET_ACTIONS_LATENCY));
            getInCallApplicationActions.execute(new Void[0]);
        }
    }
}
