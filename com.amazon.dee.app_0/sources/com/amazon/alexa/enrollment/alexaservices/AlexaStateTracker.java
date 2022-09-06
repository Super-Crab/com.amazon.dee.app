package com.amazon.alexa.enrollment.alexaservices;

import android.util.Log;
import com.amazon.alexa.api.compat.AlexaState;
import com.amazon.alexa.api.compat.AlexaStateListener;
import com.amazon.alexa.enrollment.metrics.EnrollmentMetricsRecorder;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.EventBusException;
import com.amazon.alexa.eventbus.api.Message;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class AlexaStateTracker implements AlexaStateListener {
    private static final String CALL_VOICE_ACTIVITY_EVENT = "kids:enrollment:call:voiceactivity";
    private static final String ENROLLMENT_ALEXA_IDLE_EVENT = "kids:enrollment:alexa:idle";
    private static final String TAG = GeneratedOutlineSupport1.outline39(AlexaStateTracker.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private static final String UI_UPDATION_EVENT = "kids:enrollment:ui:updation";
    private AlexaState alexaState;
    private final EventBus eventBus;
    private final EnrollmentMetricsRecorder metricsRecorder;
    private EnrollmentEvents previousEvent = EnrollmentEvents.EVENT_INITIAL;

    @Inject
    public AlexaStateTracker(EventBus eventBus, EnrollmentMetricsRecorder enrollmentMetricsRecorder, AlexaState alexaState) {
        this.eventBus = eventBus;
        this.metricsRecorder = enrollmentMetricsRecorder;
        this.alexaState = alexaState;
    }

    private boolean isTerminalEvents(AlexaState alexaState, AlexaState alexaState2) {
        if (!((alexaState == AlexaState.THINKING && alexaState2 == AlexaState.IDLE) || (alexaState == AlexaState.LISTENING && alexaState2 == AlexaState.IDLE)) || EnrollmentEvents.EVENT_OTHER_TERMINALS.equals(this.previousEvent)) {
            return false;
        }
        Log.i(TAG, "previousEvent => Other Terminal event");
        this.previousEvent = EnrollmentEvents.EVENT_OTHER_TERMINALS;
        return true;
    }

    private boolean isUpdateUiEvent(AlexaState alexaState, AlexaState alexaState2) {
        if (!((alexaState == AlexaState.UNKNOWN && alexaState2 == AlexaState.IDLE) || ((alexaState == AlexaState.LISTENING || alexaState == AlexaState.THINKING) && alexaState2 == AlexaState.SPEAKING)) || EnrollmentEvents.EVENT_UI_UPDATION.equals(this.previousEvent)) {
            return false;
        }
        Log.i(TAG, "previousEvent => UI updation event");
        this.previousEvent = EnrollmentEvents.EVENT_UI_UPDATION;
        return true;
    }

    private boolean isVoiceActivityEvent(AlexaState alexaState, AlexaState alexaState2) {
        if (alexaState == AlexaState.SPEAKING && alexaState2 == AlexaState.IDLE && !EnrollmentEvents.EVENT_VOICE_ACTIVITY.equals(this.previousEvent)) {
            Log.i(TAG, "previousEvent => VoiceActivity event");
            this.previousEvent = EnrollmentEvents.EVENT_VOICE_ACTIVITY;
            return true;
        }
        return false;
    }

    private void sendEventToEventBus(Message message) {
        try {
            this.eventBus.publish(message);
            Log.i(TAG, "Messsage has been published to Event Bus");
        } catch (EventBusException unused) {
            Log.i(TAG, "EventBusException in publishing event");
            this.metricsRecorder.recordUserViewInteraction(MetricsConstants.OperationalMetrics.EVENT_BUS_EXCEPTION);
        }
    }

    private void sendStateEventsToEventBus(AlexaState alexaState, AlexaState alexaState2) {
        String str = TAG;
        Log.i(str, "sending events to event bus with old state: " + alexaState + " and with new state: " + alexaState2);
        if (isUpdateUiEvent(alexaState, alexaState2)) {
            Log.i(TAG, "Sending enrollment Update UI event");
            sendEventToEventBus(new Message.Builder().setEventType(UI_UPDATION_EVENT).build());
        } else if (isVoiceActivityEvent(alexaState, alexaState2)) {
            Log.i(TAG, "Sending enrollment call voice activity event");
            sendEventToEventBus(new Message.Builder().setEventType(CALL_VOICE_ACTIVITY_EVENT).build());
        } else if (!isTerminalEvents(alexaState, alexaState2)) {
        } else {
            Log.i(TAG, "Sending enrollment Alexa IDLE event");
            sendEventToEventBus(new Message.Builder().setEventType(ENROLLMENT_ALEXA_IDLE_EVENT).build());
        }
    }

    public AlexaState getAlexaState() {
        return this.alexaState;
    }

    @Override // com.amazon.alexa.api.compat.AlexaStateListener
    public void onAlexaStateChanged(AlexaState alexaState) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("inside onAlexaStateChanged to new state => ");
        outline107.append(alexaState.name());
        Log.i(str, outline107.toString());
        AlexaState alexaState2 = this.alexaState;
        this.alexaState = alexaState;
        sendStateEventsToEventBus(alexaState2, alexaState);
    }
}
