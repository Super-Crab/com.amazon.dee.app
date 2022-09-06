package com.amazon.whisperjoin.softap.serializer;

import com.amazon.whisperjoin.provisioning.registration.RegistrationToken;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import java.nio.charset.Charset;
/* loaded from: classes13.dex */
public class RegistrationTokenJsonSerializer implements RegistrationTokenSerializer {
    private final Gson gson;

    /* loaded from: classes13.dex */
    public static class RegistrationTokenJsonSerializerBuilder {
        private Gson gson;

        RegistrationTokenJsonSerializerBuilder() {
        }

        public RegistrationTokenJsonSerializer build() {
            return new RegistrationTokenJsonSerializer(this.gson);
        }

        public RegistrationTokenJsonSerializerBuilder gson(Gson gson) {
            this.gson = gson;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RegistrationTokenJsonSerializer.RegistrationTokenJsonSerializerBuilder(gson=");
            outline107.append(this.gson);
            outline107.append(")");
            return outline107.toString();
        }
    }

    RegistrationTokenJsonSerializer(Gson gson) {
        this.gson = gson;
    }

    public static RegistrationTokenJsonSerializerBuilder builder() {
        return new RegistrationTokenJsonSerializerBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof RegistrationTokenJsonSerializer;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RegistrationTokenJsonSerializer)) {
            return false;
        }
        RegistrationTokenJsonSerializer registrationTokenJsonSerializer = (RegistrationTokenJsonSerializer) obj;
        if (!registrationTokenJsonSerializer.canEqual(this)) {
            return false;
        }
        Gson gson = getGson();
        Gson gson2 = registrationTokenJsonSerializer.getGson();
        return gson != null ? gson.equals(gson2) : gson2 == null;
    }

    public Gson getGson() {
        return this.gson;
    }

    public int hashCode() {
        Gson gson = getGson();
        return 59 + (gson == null ? 43 : gson.hashCode());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RegistrationTokenJsonSerializer(gson=");
        outline107.append(getGson());
        outline107.append(")");
        return outline107.toString();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.softap.serializer.ByteSerializer
    public byte[] serialize(RegistrationToken registrationToken) {
        return this.gson.toJson(registrationToken).getBytes(Charset.forName("UTF-8"));
    }
}
