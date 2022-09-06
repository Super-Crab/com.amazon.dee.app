package amazon.speech.simclient.metrics.upl;

import amazon.speech.simclient.metrics.MetricsClient;
import amazon.speech.simclient.metrics.upl.ProcessingPoint;
import amazon.speech.simclient.metrics.upl.UPLConstants;
import amazon.speech.simclient.metrics.upl.data.RequestData;
import android.os.Bundle;
import java.util.List;
/* loaded from: classes.dex */
class EventRequestUPLRecorder extends UPLRecorder {
    static final String STRING_PROCESSING_POINT_DEVICE_REQUEST_TIME = "EventRequest";
    static final String STRING_PROCESSING_POINT_INTERACTION_START_TIME = "InteractionStart";

    /* JADX INFO: Access modifiers changed from: package-private */
    public EventRequestUPLRecorder(Bundle bundle, MetricsClient metricsClient) {
        super(RequestData.Type.INTERACTION, bundle, metricsClient);
    }

    @Override // amazon.speech.simclient.metrics.upl.UPLRecorder
    protected void parseRequestProcessingPoints(Bundle bundle, List<ProcessingPoint> list) {
        tryExtractProcessingPoint(bundle, UPLConstants.IntentKey.INTENT_KEY_INTERACTION_START_TIME, ProcessingPoint.ProcessingType.DevicePreProcessing, STRING_PROCESSING_POINT_INTERACTION_START_TIME, list);
        tryExtractProcessingPoint(bundle, UPLConstants.IntentKey.INTENT_KEY_DEVICE_REQUEST_TIME, ProcessingPoint.ProcessingType.ServerProcessing, STRING_PROCESSING_POINT_DEVICE_REQUEST_TIME, list);
    }
}
