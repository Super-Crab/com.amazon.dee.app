package amazon.speech.simclient.metrics.upl;

import amazon.speech.simclient.metrics.MetricsClient;
import amazon.speech.simclient.metrics.upl.ProcessingPoint;
import amazon.speech.simclient.metrics.upl.data.RequestData;
import android.os.Bundle;
import java.util.List;
/* loaded from: classes.dex */
class SpeechRequestUPLRecorder extends UPLRecorder {
    static final String STRING_PROCESSING_POINT_STOP_CAPTURE = "StopCapture";
    static final String STRING_PROCESSING_POINT_UTTERANCE_END = "UtteranceEnd";

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpeechRequestUPLRecorder(Bundle bundle, MetricsClient metricsClient) {
        super(RequestData.Type.SPEECH, bundle, metricsClient);
    }

    @Override // amazon.speech.simclient.metrics.upl.UPLRecorder
    protected void parseRequestProcessingPoints(Bundle bundle, List<ProcessingPoint> list) {
        tryExtractProcessingPoint(bundle, "end_of_speech_time", ProcessingPoint.ProcessingType.ServerProcessing, STRING_PROCESSING_POINT_UTTERANCE_END, list);
        tryExtractProcessingPoint(bundle, "stop_capture_time", ProcessingPoint.ProcessingType.ServerProcessing, STRING_PROCESSING_POINT_STOP_CAPTURE, list);
    }
}
