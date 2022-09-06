package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.BulkGetReactionSummariesResponse;
import com.amazon.clouddrive.extended.model.ReactionSummaries;
import com.amazon.clouddrive.extended.model.deserializer.ReactionSummariesDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class BulkGetReactionSummariesResponseDeserializer extends AbstractDeserializer<ReactionSummaries, BulkGetReactionSummariesResponse> {
    public static final JsonDeserializer<BulkGetReactionSummariesResponse> INSTANCE = new BulkGetReactionSummariesResponseDeserializer();

    /* loaded from: classes11.dex */
    static class BulkGetReactionSummariesResponseFieldDeserializer extends ReactionSummariesDeserializer.ReactionSummariesFieldDeserializer {
        BulkGetReactionSummariesResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.ReactionSummariesDeserializer.ReactionSummariesFieldDeserializer, com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((ReactionSummaries) obj));
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.ReactionSummariesDeserializer.ReactionSummariesFieldDeserializer
        public <U extends ReactionSummaries> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            return super.handleField(jsonParser, str, (String) u);
        }
    }

    private BulkGetReactionSummariesResponseDeserializer() {
        super(new BulkGetReactionSummariesResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public BulkGetReactionSummariesResponse mo3156createValue() {
        return new BulkGetReactionSummariesResponse();
    }
}
