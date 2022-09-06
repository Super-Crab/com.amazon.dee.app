package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GroupCollection;
import com.amazon.clouddrive.extended.model.PatchGroupCollectionResponse;
import com.amazon.clouddrive.extended.model.deserializer.GroupCollectionDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class PatchGroupCollectionResponseDeserializer extends AbstractDeserializer<GroupCollection, PatchGroupCollectionResponse> {
    public static final JsonDeserializer<PatchGroupCollectionResponse> INSTANCE = new PatchGroupCollectionResponseDeserializer();

    /* loaded from: classes11.dex */
    static class PatchGroupCollectionResponseFieldDeserializer extends GroupCollectionDeserializer.GroupCollectionFieldDeserializer {
        PatchGroupCollectionResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.GroupCollectionDeserializer.GroupCollectionFieldDeserializer, com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((GroupCollection) obj));
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.GroupCollectionDeserializer.GroupCollectionFieldDeserializer
        public <U extends GroupCollection> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            return super.handleField(jsonParser, str, (String) u);
        }
    }

    public PatchGroupCollectionResponseDeserializer() {
        super(new PatchGroupCollectionResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public PatchGroupCollectionResponse mo3156createValue() {
        return new PatchGroupCollectionResponse();
    }
}
