package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.GetAccountEndpointResponse;
import com.amazon.clouddrive.model.serializer.GetEndpointRequestSerializer;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GetEndpointResponseSerializer implements JsonSerializer<GetAccountEndpointResponse> {
    public static final JsonSerializer<GetAccountEndpointResponse> INSTANCE = new GetEndpointResponseSerializer();
    private final GetEndpointRequestSerializer.GetEndpointResponseFieldSerializer mFieldSerializer = new GetEndpointRequestSerializer.GetEndpointResponseFieldSerializer();

    private GetEndpointResponseSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(GetAccountEndpointResponse getAccountEndpointResponse, JsonGenerator jsonGenerator) throws IOException {
        if (getAccountEndpointResponse == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((GetEndpointRequestSerializer.GetEndpointResponseFieldSerializer) getAccountEndpointResponse, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
