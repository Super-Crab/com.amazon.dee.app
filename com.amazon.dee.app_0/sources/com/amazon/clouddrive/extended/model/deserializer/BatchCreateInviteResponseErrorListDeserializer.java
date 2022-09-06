package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.BatchCreateInviteResponse;
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
class BatchCreateInviteResponseErrorListDeserializer extends ListDeserializer<BatchCreateInviteResponse.Error> {
    public static final JsonDeserializer<List<BatchCreateInviteResponse.Error>> INSTANCE = new BatchCreateInviteResponseErrorListDeserializer(ErrorDeserializer.INSTANCE);

    /* loaded from: classes11.dex */
    private static class ErrorDeserializer implements JsonDeserializer<BatchCreateInviteResponse.Error> {
        public static final JsonDeserializer<BatchCreateInviteResponse.Error> INSTANCE = new ErrorDeserializer();
        private static final JsonFieldDeserializer<BatchCreateInviteResponse.Error.ErrorBuilder> FIELD_DESERIALIZER = new FieldDeserializer();

        /* loaded from: classes11.dex */
        private static class FieldDeserializer implements JsonFieldDeserializer<BatchCreateInviteResponse.Error.ErrorBuilder> {
            private FieldDeserializer() {
            }

            @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
            public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
                return handleField(jsonParser, str, (String) ((BatchCreateInviteResponse.Error.ErrorBuilder) obj));
            }

            public <U extends BatchCreateInviteResponse.Error.ErrorBuilder> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
                if ("inviteAddress".equals(str)) {
                    u.address(InviteAddressDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                    return true;
                } else if (!"message".equals(str)) {
                    return false;
                } else {
                    u.message(SimpleDeserializers.deserializeString(jsonParser));
                    return true;
                }
            }
        }

        private ErrorDeserializer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
        /* renamed from: deserialize */
        public BatchCreateInviteResponse.Error mo3229deserialize(JsonParser jsonParser) throws IOException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NULL) {
                return null;
            }
            if (currentToken == JsonToken.START_OBJECT) {
                BatchCreateInviteResponse.Error.ErrorBuilder builder = BatchCreateInviteResponse.Error.builder();
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                        String currentName = jsonParser.getCurrentName();
                        if (jsonParser.nextToken() != null) {
                            if (!FIELD_DESERIALIZER.handleField(jsonParser, currentName, builder)) {
                                jsonParser.skipChildren();
                            }
                        } else {
                            throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                        }
                    } else {
                        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected field name, got ", currentToken), jsonParser.getTokenLocation());
                    }
                }
                return builder.build();
            }
            throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
        }
    }

    private BatchCreateInviteResponseErrorListDeserializer(JsonDeserializer<BatchCreateInviteResponse.Error> jsonDeserializer) {
        super(jsonDeserializer);
    }
}
