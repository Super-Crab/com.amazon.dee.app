package com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents;

import android.util.Log;
import com.amazon.alexa.enrollment.metrics.MetricsConstants;
import com.amazon.alexa.enrollment.voiceSDK.enrollmentEvents.events.EnrollmentBaseEvent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes7.dex */
public class EnrollmentEventBus {
    private static final String TAG = GeneratedOutlineSupport1.outline39(EnrollmentEventBus.class, GeneratedOutlineSupport1.outline107(MetricsConstants.VOICE_ENROLL_LOGGING_PREFIX));
    private EventBus eventBus;

    public EnrollmentEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void deRegister(Object obj) {
        this.eventBus.unregister(obj);
    }

    public void register(Object obj) {
        this.eventBus.register(obj);
    }

    public void sendEvent(EnrollmentBaseEvent enrollmentBaseEvent) {
        Log.i(TAG, "Sending event");
        this.eventBus.post(enrollmentBaseEvent);
    }
}
