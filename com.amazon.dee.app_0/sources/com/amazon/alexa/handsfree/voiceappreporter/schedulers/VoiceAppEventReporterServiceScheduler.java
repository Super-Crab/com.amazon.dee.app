package com.amazon.alexa.handsfree.voiceappreporter.schedulers;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.utils.BroadcastScheduler;
import com.amazon.alexa.handsfree.voiceappreporter.receivers.VoiceAppEventReceiver;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class VoiceAppEventReporterServiceScheduler extends BroadcastScheduler {
    private static final int DELAY_MILIS = 86400000;
    private static final String LAST_SCHEDULE_CHECK_TAG = "com.amazon.alexa.handsfree.voiceappreporter.schedulers.LastScheduleCheck";
    @VisibleForTesting
    static final long LAST_SCHEDULE_CHECK_UNDEFINED = 0;
    private static final int PENDING_INTENT_REQUEST_CODE = 0;
    private static final String SHARED_PREFERENCES_FILE = "com.amazon.alexa.handsfree.voiceappreporter.schedulers.VoiceAppEventReporterServiceScheduler";

    @Inject
    public VoiceAppEventReporterServiceScheduler(@NonNull Context context) {
        super(context);
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.BroadcastScheduler
    protected int getDelayMillis() {
        return 86400000;
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.BroadcastScheduler
    @NonNull
    protected Intent getIntentToBroadcast() {
        return new Intent(getContext(), VoiceAppEventReceiver.class);
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.BroadcastScheduler
    @NonNull
    protected String getLastScheduleCheckTag() {
        return LAST_SCHEDULE_CHECK_TAG;
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.BroadcastScheduler
    @NonNull
    protected PendingIntent getPendingIntentToBroadcast() {
        return PendingIntent.getBroadcast(getContext(), 0, new Intent(getContext(), VoiceAppEventReceiver.class), 134217728);
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.BroadcastScheduler
    @NonNull
    protected String getSharedPreferencesFile() {
        return SHARED_PREFERENCES_FILE;
    }

    @Override // com.amazon.alexa.handsfree.protocols.utils.BroadcastScheduler
    @NonNull
    protected String getTag() {
        return VoiceAppEventReporterServiceScheduler.class.getSimpleName();
    }
}
