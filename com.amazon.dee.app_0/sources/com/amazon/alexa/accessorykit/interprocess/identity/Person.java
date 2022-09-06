package com.amazon.alexa.accessorykit.interprocess.identity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.JsonUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class Person implements JsonObjectSerializable {
    private static final String ABSENT_PERSON_ID_VALUE = "";
    private static final String HASHED_COMMS_ID_KEY = "hashedCommsId";
    private static final String HOUSEHOLD_COMMS_ID_KEY = "householdCommsId";
    private static final String PERSON_ID_KEY = "personId";
    public final String hashedCommsId;
    public final String househouldCommsId;
    public final String personId;
    public static final Person ABSENT = new Person();
    public static final JsonObjectSerializable.Factory<Person> FACTORY = new Factory();

    /* loaded from: classes6.dex */
    private static final class Factory implements JsonObjectSerializable.Factory<Person> {
        private Factory() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public Person mo1239create(JSONObject jSONObject) throws JSONException {
            String string = jSONObject.getString("personId");
            if (string.equals("")) {
                return Person.ABSENT;
            }
            return new Person(string, JsonUtils.getStringOrNull(jSONObject, "hashedCommsId"), JsonUtils.getStringOrNull(jSONObject, Person.HOUSEHOLD_COMMS_ID_KEY));
        }
    }

    public Person(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        Preconditions.notNull(str, "personId");
        Preconditions.precondition(!str.isEmpty(), "personId cannot be empty");
        this.personId = str;
        this.hashedCommsId = str2;
        this.househouldCommsId = str3;
    }

    public boolean equals(Object obj) {
        return true;
    }

    public int hashCode() {
        return 0;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("personId", this.personId).put("hashedCommsId", this.hashedCommsId).put(HOUSEHOLD_COMMS_ID_KEY, this.househouldCommsId);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Person{personId='");
        GeneratedOutlineSupport1.outline176(outline107, this.personId, Chars.QUOTE, ", hashedCommsId='");
        GeneratedOutlineSupport1.outline176(outline107, this.hashedCommsId, Chars.QUOTE, ", househouldCommsId='");
        return GeneratedOutlineSupport1.outline90(outline107, this.househouldCommsId, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    private Person() {
        this.personId = "";
        this.hashedCommsId = null;
        this.househouldCommsId = null;
    }
}
