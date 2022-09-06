package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.GetAccountEndpointRequest;
import com.amazon.clouddrive.model.GetAccountEndpointResponse;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class GetEndpointRequestSerializer implements JsonSerializer<GetAccountEndpointRequest> {
    public static final JsonSerializer<GetAccountEndpointRequest> INSTANCE = new GetEndpointRequestSerializer();

    /* loaded from: classes11.dex */
    static class GetEndpointResponseFieldSerializer implements JsonFieldSerializer<GetAccountEndpointResponse> {
        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((GetEndpointResponseFieldSerializer) ((GetAccountEndpointResponse) obj), jsonGenerator);
        }

        public <U extends GetAccountEndpointResponse> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("contentUrl");
            SimpleSerializers.serializeString(u.getContentUrl(), jsonGenerator);
            jsonGenerator.writeFieldName("marketplaceAtSignup");
            SimpleSerializers.serializeString(u.getMarketplaceAtSignup(), jsonGenerator);
            jsonGenerator.writeFieldName("countryAtSignup");
            SimpleSerializers.serializeString(u.getCountryAtSignup(), jsonGenerator);
            jsonGenerator.writeFieldName("customerExists");
            SimpleSerializers.serializePrimitiveBoolean(u.isCustomerExists(), jsonGenerator);
            jsonGenerator.writeFieldName("metadataUrl");
            SimpleSerializers.serializeString(u.getMetadataUrl(), jsonGenerator);
            jsonGenerator.writeFieldName("region");
            SimpleSerializers.serializeString(u.getRegion(), jsonGenerator);
            jsonGenerator.writeFieldName("retailUrl");
            SimpleSerializers.serializeString(u.getRetailUrl(), jsonGenerator);
        }
    }

    private GetEndpointRequestSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(GetAccountEndpointRequest getAccountEndpointRequest, JsonGenerator jsonGenerator) throws IOException {
        if (getAccountEndpointRequest == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        jsonGenerator.writeEndObject();
    }
}
