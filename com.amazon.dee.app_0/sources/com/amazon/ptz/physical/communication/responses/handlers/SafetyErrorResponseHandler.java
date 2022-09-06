package com.amazon.ptz.physical.communication.responses.handlers;

import android.util.Log;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import com.amazon.alexa.response.payload.SafetyErrorResponsePayload;
import com.amazon.alexa.response.payload.type.SafetyErrorResponseType;
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
public class SafetyErrorResponseHandler implements ResponseHandler {
    private static final String TAG = LogTag.forClass(SafetyErrorResponseHandler.class);
    @Nonnull
    private final EventBus eventBus;
    @Nonnull
    private final MetricRecorder metricRecorder;
    @Nonnull
    private final RcSerializer rcSerializer;

    /* renamed from: com.amazon.ptz.physical.communication.responses.handlers.SafetyErrorResponseHandler$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$response$payload$type$SafetyErrorResponseType = new int[SafetyErrorResponseType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$response$payload$type$SafetyErrorResponseType[SafetyErrorResponseType.OBSTACLE_DETECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public SafetyErrorResponseHandler(@Nonnull EventBus eventBus, @Nonnull RcSerializer rcSerializer, @Nonnull MetricRecorder metricRecorder) {
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
        return HeaderName.ERROR_RESPONSE.equals(headerName) && NamespaceName.ALEXA_SAFETY.equals(namespaceName);
    }

    @Override // com.amazon.ptz.physical.communication.responses.handlers.ResponseHandler
    public void handle(JsonElement jsonElement) {
        SafetyErrorResponsePayload safetyErrorResponsePayload = (SafetyErrorResponsePayload) this.rcSerializer.fromJson(jsonElement, SafetyErrorResponsePayload.class);
        if (safetyErrorResponsePayload != null) {
            SafetyErrorResponseType type = safetyErrorResponsePayload.getType();
            String str = TAG;
            Log.i(str, String.format("Handling physical PTZ failed response for safety error type: " + type, new Object[0]));
            if (type.ordinal() != 0) {
                String str2 = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to handle safety error type: ");
                outline107.append(type.name());
                Log.i(str2, outline107.toString());
                return;
            }
            this.metricRecorder.recordErrorResponse(PhysicalPtzEvent.OBSTACLE_ENCOUNTERED);
            this.eventBus.post(PhysicalPtzEvent.OBSTACLE_ENCOUNTERED);
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline61("Payload is malformed: ", jsonElement));
    }
}
