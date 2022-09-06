package com.amazon.alexa.presence.reporter;

import android.util.Log;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.presence.Presence;
import com.amazon.alexa.presence.detection.BeaconDetection;
import com.amazon.alexa.presence.detection.BeaconFormatLogic;
import com.amazon.alexa.presence.support.BackgroundBatchProcessor;
import com.amazon.alexa.presence.utils.BeaconRequestGeneratorUtil;
import com.dee.app.metrics.MetricsServiceV2;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class PresenceBeaconResolverClient {
    public static final int BEACON_BATCH_SIZE = 30;
    public static final int MAX_BATCH_IDLE_MS = 2500;
    public static final int MAX_BEACON_AGE_MS = 5500;
    private static final String TAG = Presence.tag();
    private final BackgroundBatchProcessor<BeaconDetection> mBatchProcessor = new BackgroundBatchProcessor<>(new BackgroundBatchProcessor.Handler() { // from class: com.amazon.alexa.presence.reporter.-$$Lambda$PresenceBeaconResolverClient$TGRs5vw90F8NRcdwv0I-ceBsVs8
        @Override // com.amazon.alexa.presence.support.BackgroundBatchProcessor.Handler
        public final void accept(List list) {
            PresenceBeaconResolverClient.this.resolveBeaconBatch(list);
        }
    }, new BackgroundBatchProcessor.Config.Builder().withMaxBatchSize(30).withMaxItemAgeMs(MAX_BEACON_AGE_MS).withMaxQueueIdleToFlushMs(2500).build());
    private final BeaconFormatLogic mBeaconFormatLogic;
    private final HttpAsyncTaskInstanceFactory mHttpAsyncTaskInstanceFactory;
    private final MetricsServiceV2 mMetricsServiceV2;
    private final PersonIdProvider personIdProvider;

    @Inject
    public PresenceBeaconResolverClient(HttpAsyncTaskInstanceFactory httpAsyncTaskInstanceFactory, MetricsServiceV2 metricsServiceV2, BeaconFormatLogic beaconFormatLogic, PersonIdProvider personIdProvider) {
        this.mHttpAsyncTaskInstanceFactory = httpAsyncTaskInstanceFactory;
        this.mMetricsServiceV2 = metricsServiceV2;
        this.mBeaconFormatLogic = beaconFormatLogic;
        this.personIdProvider = personIdProvider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resolveBeaconBatch(List<BeaconDetection> list) {
        Log.i(TAG, String.format("Sending out batch of %d beacons.", Integer.valueOf(list.size())));
        this.mHttpAsyncTaskInstanceFactory.getInstance().sendRequest(BeaconRequestGeneratorUtil.generateResolveBeaconsRequest(this.mBeaconFormatLogic, list, this.personIdProvider.getPersonId()));
    }

    public void resolve(BeaconDetection beaconDetection) {
        if (beaconDetection == null) {
            Log.w(TAG, "Application tried to send a null beacon.  Disregarding.");
        }
        this.mBatchProcessor.process((BackgroundBatchProcessor<BeaconDetection>) beaconDetection);
    }
}
