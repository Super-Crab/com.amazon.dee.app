package com.amazon.ptz.physical.communication.responses.handlers;

import android.util.Log;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import com.amazon.alexa.response.payload.ErrorResponsePayload;
import com.amazon.alexa.response.payload.type.ErrorResponseType;
import com.amazon.ptz.metrics.MetricRecorder;
import com.amazon.ptz.physical.events.PhysicalPtzEvent;
import com.amazon.ptz.util.LogTag;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonElement;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.annotation.Nonnull;
import lombok.Generated;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes13.dex */
public class ErrorResponseHandler implements ResponseHandler {
    private static final String TAG = LogTag.forClass(ErrorResponseHandler.class);
    @Nonnull
    private final EventBus eventBus;
    @Nonnull
    private final MetricRecorder metricRecorder;
    @Nonnull
    private final RcSerializer rcSerializer;

    /* renamed from: com.amazon.ptz.physical.communication.responses.handlers.ErrorResponseHandler$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$response$payload$type$ErrorResponseType = new int[ErrorResponseType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$response$payload$type$ErrorResponseType[ErrorResponseType.VALUE_OUT_OF_RANGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public ErrorResponseHandler(@Nonnull EventBus eventBus, @Nonnull RcSerializer rcSerializer, @Nonnull MetricRecorder metricRecorder) {
        if (eventBus != null) {
            if (rcSerializer == null) {
                throw new IllegalArgumentException("rcSerializer is null");
            }
            if (metricRecorder == null) {
                throw new IllegalArgumentException("metricRecorder is null");
            }
            this.eventBus = eventBus;
            this.rcSerializer = rcSerializer;
            this.metricRecorder = metricRecorder;
            return;
        }
        throw new IllegalArgumentException("eventBus is null");
    }

    @Override // com.amazon.ptz.physical.communication.responses.handlers.ResponseHandler
    public boolean canHandle(HeaderName headerName, NamespaceName namespaceName) {
        return HeaderName.ERROR_RESPONSE.equals(headerName) && NamespaceName.ALEXA.equals(namespaceName);
    }

    @Override // com.amazon.ptz.physical.communication.responses.handlers.ResponseHandler
    public void handle(JsonElement jsonElement) {
        ErrorResponsePayload errorResponsePayload = (ErrorResponsePayload) this.rcSerializer.fromJson(jsonElement, ErrorResponsePayload.class);
        if (errorResponsePayload != null) {
            ErrorResponseType type = errorResponsePayload.getType();
            String str = TAG;
            Log.i(str, String.format("Handling physical PTZ failed response for error type: " + type, new Object[0]));
            if (type.ordinal() != 4) {
                String str2 = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to handle error type: ");
                outline107.append(type.name());
                Log.i(str2, outline107.toString());
                return;
            }
            this.metricRecorder.recordErrorResponse(PhysicalPtzEvent.END_OF_RANGE);
            this.eventBus.post(PhysicalPtzEvent.END_OF_RANGE);
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline61("Payload is malformed: ", jsonElement));
    }
}
