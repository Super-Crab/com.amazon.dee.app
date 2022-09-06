package com.amazon.whisperjoin.softap.serializer;

import com.amazon.whisperjoin.wifi.WifiConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import java.nio.charset.Charset;
/* loaded from: classes13.dex */
public class WifiConfigurationJsonSerializer implements WifiConfigurationSerializer {
    private Gson gson;

    /* loaded from: classes13.dex */
    public static class WifiConfigurationJsonSerializerBuilder {
        private Gson gson;

        WifiConfigurationJsonSerializerBuilder() {
        }

        public WifiConfigurationJsonSerializer build() {
            return new WifiConfigurationJsonSerializer(this.gson);
        }

        public WifiConfigurationJsonSerializerBuilder gson(Gson gson) {
            this.gson = gson;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiConfigurationJsonSerializer.WifiConfigurationJsonSerializerBuilder(gson=");
            outline107.append(this.gson);
            outline107.append(")");
            return outline107.toString();
        }
    }

    WifiConfigurationJsonSerializer(Gson gson) {
        this.gson = gson;
    }

    public static WifiConfigurationJsonSerializerBuilder builder() {
        return new WifiConfigurationJsonSerializerBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WifiConfigurationJsonSerializer;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WifiConfigurationJsonSerializer)) {
            return false;
        }
        WifiConfigurationJsonSerializer wifiConfigurationJsonSerializer = (WifiConfigurationJsonSerializer) obj;
        if (!wifiConfigurationJsonSerializer.canEqual(this)) {
            return false;
        }
        Gson gson = getGson();
        Gson gson2 = wifiConfigurationJsonSerializer.getGson();
        return gson != null ? gson.equals(gson2) : gson2 == null;
    }

    public Gson getGson() {
        return this.gson;
    }

    public int hashCode() {
        Gson gson = getGson();
        return 59 + (gson == null ? 43 : gson.hashCode());
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiConfigurationJsonSerializer(gson=");
        outline107.append(getGson());
        outline107.append(")");
        return outline107.toString();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.whisperjoin.softap.serializer.ByteSerializer
    public byte[] serialize(WifiConfiguration wifiConfiguration) {
        return this.gson.toJson(wifiConfiguration).getBytes(Charset.forName("UTF-8"));
    }
}
