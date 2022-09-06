package com.amazon.clouddrive.extended.model.deserializer;

import com.amazon.clouddrive.extended.model.GroupPreferenceSet;
import com.amazon.clouddrive.extended.model.MemberPreferencesResponse;
import com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer;
import com.amazon.clouddrive.model.deserializer.ListDeserializer;
import java.io.IOException;
import org.codehaus.jackson.JsonParser;
/* loaded from: classes11.dex */
public class MemberPreferencesResponseDeserializer extends AbstractDeserializer<MemberPreferencesResponse, MemberPreferencesResponse> {
    public static MemberPreferencesResponseDeserializer INSTANCE = new MemberPreferencesResponseDeserializer();
    private static final ListDeserializer<GroupPreferenceSet> GROUP_PREFERENCE_SET_LIST_DESERIALIZER = new ListDeserializer<>(GroupPreferenceSetDeserializer.INSTANCE);

    /* loaded from: classes11.dex */
    public static class MemberPreferencesResponseFieldDeserializer implements JsonFieldDeserializer<MemberPreferencesResponse> {
        @Override // com.amazon.clouddrive.model.deserializer.JsonFieldDeserializer
        public /* bridge */ /* synthetic */ boolean handleField(JsonParser jsonParser, String str, Object obj) throws IOException {
            return handleField(jsonParser, str, (String) ((MemberPreferencesResponse) obj));
        }

        public <U extends MemberPreferencesResponse> boolean handleField(JsonParser jsonParser, String str, U u) throws IOException {
            if ("preferenceSets".equals(str)) {
                u.setPreferenceSets(MemberPreferencesResponseDeserializer.GROUP_PREFERENCE_SET_LIST_DESERIALIZER.mo3229deserialize(jsonParser));
                return true;
            }
            return false;
        }
    }

    private MemberPreferencesResponseDeserializer() {
        super(new MemberPreferencesResponseFieldDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.clouddrive.extended.model.deserializer.AbstractDeserializer
    /* renamed from: createValue */
    public MemberPreferencesResponse mo3156createValue() {
        return new MemberPreferencesResponse();
    }
}
