package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.CheckpointFamilyChangesEvent;
import com.amazon.clouddrive.extended.model.FamilyChangesEvent;
import com.amazon.clouddrive.extended.model.FamilyChangesEventType;
import com.amazon.clouddrive.extended.model.ResetFamilyChangesEvent;
import com.amazon.clouddrive.extended.model.UpdateFamilyChangesEvent;
import com.amazon.clouddrive.extended.model.UpdateNodeFamilyChangesEvent;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.SimpleDeserializers;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
/* loaded from: classes11.dex */
public class FamilyChangesEventDeserializer implements JsonDeserializer<FamilyChangesEvent> {
    public static final JsonDeserializer<FamilyChangesEvent> INSTANCE = new FamilyChangesEventDeserializer();
    private final FamilyChangesEventFieldDeserializer mFieldHandler = new FamilyChangesEventFieldDeserializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class FamilyChangesEventFieldDeserializer implements JsonFieldDeserializer<FamilyChangesEvent> {
        FamilyChangesEventFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((FamilyChangesEvent) obj));
        }

        public <U extends FamilyChangesEvent> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if (MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY.equals(str)) {
                ((UpdateNodeFamilyChangesEvent) u).setOwnerId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("node".equals(str)) {
                if (!(u instanceof UpdateNodeFamilyChangesEvent)) {
                    return false;
                }
                ((UpdateNodeFamilyChangesEvent) u).setNode(NodeExtendedDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if ("checkpoint".equals(str)) {
                if (!(u instanceof CheckpointFamilyChangesEvent)) {
                    return false;
                }
                ((CheckpointFamilyChangesEvent) u).setCheckpoint(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            } else if ("member".equals(str)) {
                if (!(u instanceof UpdateFamilyChangesEvent)) {
                    return false;
                }
                ((UpdateFamilyChangesEvent) u).setMember(CustomerInfoDeserializer.INSTANCE.mo3229deserialize(jsonParser));
                return true;
            } else if (!"familyId".equals(str) || !(u instanceof ResetFamilyChangesEvent)) {
                return false;
            } else {
                ((ResetFamilyChangesEvent) u).setFamilyId(SimpleDeserializers.deserializeString(jsonParser));
                return true;
            }
        }
    }

    private FamilyChangesEventDeserializer() {
    }

    private FamilyChangesEvent newFamilyChangesEvent(JsonParser jsonParser) throws IOException {
        if (jsonParser.nextToken() == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            FamilyChangesEvent familyChangesEvent = null;
            if (!MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY.equals(currentName) && !"node".equals(currentName)) {
                if ("checkpoint".equals(currentName)) {
                    familyChangesEvent = new CheckpointFamilyChangesEvent();
                } else if ("member".equals(currentName)) {
                    familyChangesEvent = new UpdateFamilyChangesEvent();
                } else if ("eventType".equals(currentName)) {
                    if (jsonParser.nextToken() != null) {
                        String deserializeString = SimpleDeserializers.deserializeString(jsonParser);
                        if (FamilyChangesEventType.UPDATE_NODE.equals(deserializeString)) {
                            return new UpdateNodeFamilyChangesEvent();
                        }
                        if (FamilyChangesEventType.UPDATE_FAMILY.equals(deserializeString)) {
                            return new UpdateFamilyChangesEvent();
                        }
                        if (FamilyChangesEventType.CHECKPOINT.equals(deserializeString)) {
                            return new CheckpointFamilyChangesEvent();
                        }
                    } else {
                        throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                    }
                } else if ("familyId".equals(currentName)) {
                    familyChangesEvent = new ResetFamilyChangesEvent();
                }
            } else {
                familyChangesEvent = new UpdateNodeFamilyChangesEvent();
            }
            if (jsonParser.nextToken() != null) {
                this.mFieldHandler.handleField(jsonParser, currentName, (String) familyChangesEvent);
                return familyChangesEvent;
            }
            throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected field name, got ");
        outline107.append(jsonParser.getCurrentToken());
        throw new JsonParseException(outline107.toString(), jsonParser.getTokenLocation());
    }

    private FamilyChangesEvent parseEvent(FamilyChangesEvent familyChangesEvent, JsonParser jsonParser) throws IOException {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() == JsonToken.FIELD_NAME) {
                String currentName = jsonParser.getCurrentName();
                if (jsonParser.nextToken() != null) {
                    if (!this.mFieldHandler.handleField(jsonParser, currentName, (String) familyChangesEvent)) {
                        jsonParser.skipChildren();
                    }
                } else {
                    throw new JsonParseException("Unexpected end of input", jsonParser.getTokenLocation());
                }
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected field name, got ");
                outline107.append(jsonParser.getCurrentToken());
                throw new JsonParseException(outline107.toString(), jsonParser.getTokenLocation());
            }
        }
        return familyChangesEvent;
    }

    private void skipUnknownEvent(JsonParser jsonParser) throws IOException {
        do {
        } while (jsonParser.nextToken() != JsonToken.END_OBJECT);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public FamilyChangesEvent mo3229deserialize(JsonParser jsonParser) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken == JsonToken.START_OBJECT) {
            FamilyChangesEvent newFamilyChangesEvent = newFamilyChangesEvent(jsonParser);
            if (newFamilyChangesEvent != null) {
                return parseEvent(newFamilyChangesEvent, jsonParser);
            }
            return null;
        }
        throw new JsonParseException(GeneratedOutlineSupport1.outline81("Expected start of object, got ", currentToken), jsonParser.getTokenLocation());
    }
}
