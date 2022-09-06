package com.amazon.clouddrive.extended.model.serializer;

import com.amazon.clouddrive.extended.model.InviteAddress;
import com.amazon.clouddrive.model.serializer.JsonSerializer;
import com.amazon.clouddrive.model.serializer.SimpleSerializers;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class InviteAddressSerializer implements JsonSerializer<InviteAddress> {
    public static final JsonSerializer<InviteAddress> INSTANCE = new InviteAddressSerializer();

    private InviteAddressSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public void serialize(InviteAddress inviteAddress, JsonGenerator jsonGenerator) throws IOException {
        if (inviteAddress == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        if (inviteAddress.getType() != null) {
            jsonGenerator.writeFieldName("type");
            SimpleSerializers.serializeString(inviteAddress.getType().name(), jsonGenerator);
        }
        if (inviteAddress.getTarget() != null) {
            jsonGenerator.writeFieldName("target");
            SimpleSerializers.serializeString(inviteAddress.getTarget(), jsonGenerator);
        }
        jsonGenerator.writeEndObject();
    }
}
