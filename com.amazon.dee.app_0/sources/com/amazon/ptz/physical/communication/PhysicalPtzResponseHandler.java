package com.amazon.ptz.physical.communication;

import android.util.Log;
import com.amazon.alexa.event.Event;
import com.amazon.alexa.header.Header;
import com.amazon.alexa.rangecontroller.lib.model.EmptyInstance;
import com.amazon.alexa.rangecontroller.lib.model.HeaderName;
import com.amazon.alexa.rangecontroller.lib.model.NamespaceName;
import com.amazon.alexa.rangecontroller.lib.model.serialization.RcSerializer;
import com.amazon.alexa.rangecontroller.lib.model.serialization.type.response.RcResponse;
import com.amazon.ptz.physical.communication.responses.handlers.ResponseHandler;
import com.amazon.ptz.util.LogTag;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.gson.JsonElement;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Set;
import javax.annotation.Nonnull;
import lombok.Generated;
/* loaded from: classes13.dex */
public class PhysicalPtzResponseHandler {
    private static final String TAG = LogTag.forClass(PhysicalPtzResponseHandler.class);
    @Nonnull
    private final PhysicalPtzCommandCache physicalPtzCommandCache;
    @Nonnull
    private final RcSerializer rcSerializer;
    @Nonnull
    private final Set<ResponseHandler> responseHandlers;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public PhysicalPtzResponseHandler(@Nonnull RcSerializer rcSerializer, @Nonnull PhysicalPtzCommandCache physicalPtzCommandCache, @Nonnull Set<ResponseHandler> set) {
        if (rcSerializer != null) {
            if (physicalPtzCommandCache == null) {
                throw new IllegalArgumentException("physicalPtzCommandCache is null");
            }
            if (set == null) {
                throw new IllegalArgumentException("responseHandlers is null");
            }
            this.rcSerializer = rcSerializer;
            this.physicalPtzCommandCache = physicalPtzCommandCache;
            this.responseHandlers = set;
            return;
        }
        throw new IllegalArgumentException("rcSerializer is null");
    }

    private Optional<ResponseHandler> findResponseHandler(final HeaderName headerName, final NamespaceName namespaceName) {
        return Iterables.tryFind(this.responseHandlers, new Predicate<ResponseHandler>() { // from class: com.amazon.ptz.physical.communication.PhysicalPtzResponseHandler.1
            @Override // com.google.common.base.Predicate
            public boolean apply(@Nonnull ResponseHandler responseHandler) {
                return responseHandler.canHandle(headerName, namespaceName);
            }
        });
    }

    private Event<NamespaceName, HeaderName, EmptyInstance, JsonElement> getEvent(RcResponse rcResponse) {
        Event<NamespaceName, HeaderName, EmptyInstance, JsonElement> event = rcResponse.getEvent();
        if (event != null) {
            return event;
        }
        throw new IllegalArgumentException("Event is null");
    }

    private Header<NamespaceName, HeaderName, EmptyInstance> getHeader(Event<NamespaceName, HeaderName, EmptyInstance, ?> event) {
        Header<NamespaceName, HeaderName, EmptyInstance> header = event.getHeader();
        if (header != null) {
            return header;
        }
        throw new IllegalArgumentException("Header is null");
    }

    public void onResponseReceived(String str) {
        try {
            RcResponse rcResponse = (RcResponse) this.rcSerializer.fromJson(str, RcResponse.class);
            if (rcResponse == null) {
                Log.e(TAG, "Failed to deserialize response: " + str);
                return;
            }
            String str2 = "De-serialized response: " + rcResponse.toString();
            Event<NamespaceName, HeaderName, EmptyInstance, JsonElement> event = getEvent(rcResponse);
            Header<NamespaceName, HeaderName, EmptyInstance> header = getHeader(event);
            NamespaceName namespace = header.getNamespace();
            HeaderName name = header.getName();
            String correlationToken = header.getCorrelationToken();
            Log.i(TAG, String.format("Response received with Namespace: [%s], Name: [%s], CorrelationToken: [%s]", namespace, name, correlationToken));
            if (correlationToken != null && !correlationToken.isEmpty()) {
                this.physicalPtzCommandCache.onResponseReceived(correlationToken);
            }
            Optional<ResponseHandler> findResponseHandler = findResponseHandler(name, namespace);
            if (findResponseHandler.isPresent()) {
                findResponseHandler.get().handle(event.getPayload());
            } else {
                Log.e(TAG, "Cannot find any response handler for this response.");
            }
        } catch (Exception e) {
            Log.e(TAG, String.format("Something went wrong with handling the response.%n%s", e.getMessage()));
        }
    }
}
