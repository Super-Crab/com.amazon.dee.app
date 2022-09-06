package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.CheckpointFamilyChangesEvent;
import com.amazon.clouddrive.extended.model.FamilyChangesEvent;
import com.amazon.clouddrive.extended.model.GetFamilyChangesResponse;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class GetFamilyChangesResponseDeserializer implements JsonDeserializer<GetFamilyChangesResponse> {
    public static final JsonDeserializer<GetFamilyChangesResponse> INSTANCE = new GetFamilyChangesResponseDeserializer();

    private GetFamilyChangesResponseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.model.deserializer.JsonDeserializer
    /* renamed from: deserialize */
    public GetFamilyChangesResponse mo3229deserialize(JsonParser jsonParser) throws IOException {
        List<FamilyChangesEvent> mo3229deserialize = new ListDeserializer(FamilyChangesEventDeserializer.INSTANCE).mo3229deserialize(jsonParser);
        GetFamilyChangesResponse getFamilyChangesResponse = new GetFamilyChangesResponse();
        getFamilyChangesResponse.setEvents(mo3229deserialize);
        for (FamilyChangesEvent familyChangesEvent : mo3229deserialize) {
            if (familyChangesEvent instanceof CheckpointFamilyChangesEvent) {
                getFamilyChangesResponse.setLastSeenCheckpoint(((CheckpointFamilyChangesEvent) familyChangesEvent).getCheckpoint());
            }
        }
        return getFamilyChangesResponse;
    }
}
