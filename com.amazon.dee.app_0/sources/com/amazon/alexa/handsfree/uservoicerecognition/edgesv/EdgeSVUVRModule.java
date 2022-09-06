package com.amazon.alexa.handsfree.uservoicerecognition.edgesv;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.cache.EnrollmentStateCache;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.connection.EdgeSVUVRConnector;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.enrollment.EdgeSVUVREnroller;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.metrics.EdgeSVWakeWordDetectionMetricsListener;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.tuningsetting.EdgeSVUVRTuningSettings;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.vendorsetting.EdgeSVUVRVendorSettings;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsReporter;
/* loaded from: classes8.dex */
public class EdgeSVUVRModule {
    private static final String TAG = "EdgeSVUVRModule";
    private static EdgeSVUVRModule sEdgeSVUVRModule;
    private UVRContract mUVRContract;

    @VisibleForTesting
    EdgeSVUVRModule() {
    }

    public static synchronized EdgeSVUVRModule getInstance() {
        EdgeSVUVRModule edgeSVUVRModule;
        synchronized (EdgeSVUVRModule.class) {
            if (sEdgeSVUVRModule == null) {
                sEdgeSVUVRModule = new EdgeSVUVRModule();
            }
            edgeSVUVRModule = sEdgeSVUVRModule;
        }
        return edgeSVUVRModule;
    }

    @VisibleForTesting
    UVRContract createUVRContract(@NonNull Context context) {
        SpeakerVerificationMetricsReporter speakerVerificationMetricsReporter = new SpeakerVerificationMetricsReporter(context, SpeakerVerificationMetricsReporter.MetricsSource.ENROLLMENT);
        EdgeSVWakeWordDetectionMetricsListener edgeSVWakeWordDetectionMetricsListener = new EdgeSVWakeWordDetectionMetricsListener();
        EnrollmentStateCache enrollmentStateCache = new EnrollmentStateCache(context);
        EdgeSVUVRConnector edgeSVUVRConnector = new EdgeSVUVRConnector();
        return new UVRContract(new EdgeSVUVREnroller(context, speakerVerificationMetricsReporter, edgeSVWakeWordDetectionMetricsListener, enrollmentStateCache), new EdgeSVUVRTuningSettings(), new EdgeSVUVRVendorSettings(context, speakerVerificationMetricsReporter, enrollmentStateCache, edgeSVUVRConnector), edgeSVUVRConnector);
    }

    @NonNull
    public UVRContract getUVRContract(@NonNull Context context) {
        if (this.mUVRContract == null) {
            this.mUVRContract = createUVRContract(context);
        }
        return this.mUVRContract;
    }
}
