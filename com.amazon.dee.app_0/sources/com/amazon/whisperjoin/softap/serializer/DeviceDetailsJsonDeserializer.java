package com.amazon.whisperjoin.softap.serializer;

import android.util.Log;
import com.amazon.whisperjoin.softap.exceptions.SoftAPDeserializerException;
import com.amazon.whisperjoin.softap.pojos.DeviceDetails;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
/* loaded from: classes13.dex */
public class DeviceDetailsJsonDeserializer implements DeviceDetailsDeserializer {
    private static final String TAG = "DeviceDetailsJsonDeserializer";
    private final Gson gson;

    /* loaded from: classes13.dex */
    public static class DeviceDetailsJsonDeserializerBuilder {
        private Gson gson;

        DeviceDetailsJsonDeserializerBuilder() {
        }

        public DeviceDetailsJsonDeserializer build() {
            return new DeviceDetailsJsonDeserializer(this.gson);
        }

        public DeviceDetailsJsonDeserializerBuilder gson(Gson gson) {
            this.gson = gson;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceDetailsJsonDeserializer.DeviceDetailsJsonDeserializerBuilder(gson=");
            outline107.append(this.gson);
            outline107.append(")");
            return outline107.toString();
        }
    }

    DeviceDetailsJsonDeserializer(Gson gson) {
        this.gson = gson;
    }

    public static DeviceDetailsJsonDeserializerBuilder builder() {
        return new DeviceDetailsJsonDeserializerBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof DeviceDetailsJsonDeserializer;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeviceDetailsJsonDeserializer)) {
            return false;
        }
        DeviceDetailsJsonDeserializer deviceDetailsJsonDeserializer = (DeviceDetailsJsonDeserializer) obj;
        if (!deviceDetailsJsonDeserializer.canEqual(this)) {
            return false;
        }
        Gson gson = getGson();
        Gson gson2 = deviceDetailsJsonDeserializer.getGson();
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceDetailsJsonDeserializer(gson=");
        outline107.append(getGson());
        outline107.append(")");
        return outline107.toString();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.softap.serializer.DeviceDetailsDeserializer, com.amazon.whisperjoin.softap.serializer.StringDeserializer
    /* renamed from: deserialize */
    public DeviceDetails mo6645deserialize(String str) throws SoftAPDeserializerException {
        try {
            return (DeviceDetails) this.gson.fromJson(str, (Class<Object>) DeviceDetails.class);
        } catch (Exception e) {
            String format = String.format("An error occurred while deserializing string %s", str);
            Log.e(TAG, format, e);
            throw new SoftAPDeserializerException(format, e);
        }
    }
}
