package com.amazon.whisperjoin.softap.serializer;

import com.amazon.whisperjoin.softap.pojos.ProvisioningData;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import java.nio.charset.Charset;
/* loaded from: classes13.dex */
public class ProvisioningDataJsonSerializer implements ProvisioningDataSerializer {
    private final Gson gson;

    /* loaded from: classes13.dex */
    public static class ProvisioningDataJsonSerializerBuilder {
        private Gson gson;

        ProvisioningDataJsonSerializerBuilder() {
        }

        public ProvisioningDataJsonSerializer build() {
            return new ProvisioningDataJsonSerializer(this.gson);
        }

        public ProvisioningDataJsonSerializerBuilder gson(Gson gson) {
            this.gson = gson;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisioningDataJsonSerializer.ProvisioningDataJsonSerializerBuilder(gson=");
            outline107.append(this.gson);
            outline107.append(")");
            return outline107.toString();
        }
    }

    ProvisioningDataJsonSerializer(Gson gson) {
        this.gson = gson;
    }

    public static ProvisioningDataJsonSerializerBuilder builder() {
        return new ProvisioningDataJsonSerializerBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof ProvisioningDataJsonSerializer;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProvisioningDataJsonSerializer)) {
            return false;
        }
        ProvisioningDataJsonSerializer provisioningDataJsonSerializer = (ProvisioningDataJsonSerializer) obj;
        if (!provisioningDataJsonSerializer.canEqual(this)) {
            return false;
        }
        Gson gson = getGson();
        Gson gson2 = provisioningDataJsonSerializer.getGson();
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisioningDataJsonSerializer(gson=");
        outline107.append(getGson());
        outline107.append(")");
        return outline107.toString();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.softap.serializer.ByteSerializer
    public byte[] serialize(ProvisioningData provisioningData) {
        return this.gson.toJson(provisioningData).getBytes(Charset.forName("UTF-8"));
    }
}
