package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.CreateMemberResponse;
import com.amazon.clouddrive.extended.model.FeaturedMember;
import com.amazon.clouddrive.extended.model.deserializer.FeaturedMemberDeserializer;
import com.amazon.clouddrive.model.deserializer.JsonDeserializer;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class CreateMemberResponseDeserializer extends AbstractDeserializer<FeaturedMember, CreateMemberResponse> {
    public static final JsonDeserializer<CreateMemberResponse> INSTANCE = new CreateMemberResponseDeserializer();

    /* loaded from: classes11.dex */
    static class CreateMemberResponseFieldDeserializer extends FeaturedMemberDeserializer.FeaturedMemberFieldDeserializer {
        CreateMemberResponseFieldDeserializer() {
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.FeaturedMemberDeserializer.FeaturedMemberFieldDeserializer, com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((FeaturedMember) obj));
        }

        @Override // com.amazon.clouddrive.extended.model.deserializer.FeaturedMemberDeserializer.FeaturedMemberFieldDeserializer
        public <U extends FeaturedMember> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            return super.handleField(jsonParser, str, (String) u);
        }
    }

    public CreateMemberResponseDeserializer() {
        super(new CreateMemberResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public CreateMemberResponse mo3156createValue() {
        return new CreateMemberResponse();
    }
}
