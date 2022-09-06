package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.clouddrive.extended.model.CustomerInfo;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class CustomerInfoDeserializer implements JsonDeserializer<CustomerInfo> {
    public static final JsonDeserializer<CustomerInfo> INSTANCE = new CustomerInfoDeserializer();
    public static final JsonDeserializer<List<CustomerInfo>> LIST_DESERIALIZER = new ListDeserializer(INSTANCE);
    private final CustomerInfoFieldDeserializer mFieldHandler = new CustomerInfoFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class CustomerInfoFieldDeserializer implements JsonFieldDeserializer<CustomerInfo> {
        CustomerInfoFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((CustomerInfo) obj));
        }

        public <U extends CustomerInfo> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("customerId".equals(str)) {
                u.setCustomerId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("name".equals(str)) {
                u.setName(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("avatarNodeId".equals(str)) {
                u.setAvatarNodeId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("role".equals(str)) {
                u.setRole(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("resourceId".equals(str)) {
                u.setResourceId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (Message.SERIALIZED_NAME_MODIFIED_DATE.equals(str)) {
                u.setModifiedDate(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if (!"avatarTempLink".equals(str)) {
                return false;
            } else {
                u.setAvatarTempLink(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private CustomerInfoDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public CustomerInfo mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            CustomerInfo customerInfo = new CustomerInfo();
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                    String currentName = jsonParser.getCurrentName();
                    if (jsonParser.nextToken() != null) {
                        if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) customerInfo)) {
                            jsonParser.skipChildren();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else {
                    throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                }
            }
            return customerInfo;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
