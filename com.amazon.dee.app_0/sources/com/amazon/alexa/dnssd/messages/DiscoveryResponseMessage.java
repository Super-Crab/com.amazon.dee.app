package com.amazon.alexa.dnssd.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
/* loaded from: classes7.dex */
public class DiscoveryResponseMessage {
    public Service service;
    @JsonSerialize(using = TextRecordsSerializer.class)
    public Map<String, byte[]> textRecords;

    @JsonIgnoreProperties(ignoreUnknown = true)
    /* loaded from: classes7.dex */
    public static class Service {
        public String host;
        public String name;
        public Integer port;
        public String type;

        protected boolean canEqual(Object obj) {
            return obj instanceof Service;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Service)) {
                return false;
            }
            Service service = (Service) obj;
            if (!service.canEqual(this)) {
                return false;
            }
            String str = this.type;
            String str2 = service.type;
            if (str != null ? !str.equals(str2) : str2 != null) {
                return false;
            }
            String str3 = this.name;
            String str4 = service.name;
            if (str3 != null ? !str3.equals(str4) : str4 != null) {
                return false;
            }
            Integer num = this.port;
            Integer num2 = service.port;
            if (num != null ? !num.equals(num2) : num2 != null) {
                return false;
            }
            String str5 = this.host;
            String str6 = service.host;
            return str5 != null ? str5.equals(str6) : str6 == null;
        }

        public int hashCode() {
            String str = this.type;
            int i = 43;
            int hashCode = str == null ? 43 : str.hashCode();
            String str2 = this.name;
            int hashCode2 = ((hashCode + 59) * 59) + (str2 == null ? 43 : str2.hashCode());
            Integer num = this.port;
            int hashCode3 = (hashCode2 * 59) + (num == null ? 43 : num.hashCode());
            String str3 = this.host;
            int i2 = hashCode3 * 59;
            if (str3 != null) {
                i = str3.hashCode();
            }
            return i2 + i;
        }
    }

    /* loaded from: classes7.dex */
    public static class TextRecordsSerializer extends JsonSerializer<Map<String, byte[]>> {
        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(Map<String, byte[]> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            for (Map.Entry<String, byte[]> entry : map.entrySet()) {
                jsonGenerator.writeFieldName(entry.getKey());
                jsonGenerator.writeString(new String(entry.getValue()));
            }
            jsonGenerator.writeEndObject();
        }
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof DiscoveryResponseMessage;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DiscoveryResponseMessage)) {
            return false;
        }
        DiscoveryResponseMessage discoveryResponseMessage = (DiscoveryResponseMessage) obj;
        if (!discoveryResponseMessage.canEqual(this)) {
            return false;
        }
        Service service = this.service;
        Service service2 = discoveryResponseMessage.service;
        if (service != null ? !service.equals(service2) : service2 != null) {
            return false;
        }
        Map<String, byte[]> map = this.textRecords;
        Map<String, byte[]> map2 = discoveryResponseMessage.textRecords;
        return map != null ? map.equals(map2) : map2 == null;
    }

    public int hashCode() {
        Service service = this.service;
        int i = 43;
        int hashCode = service == null ? 43 : service.hashCode();
        Map<String, byte[]> map = this.textRecords;
        int i2 = (hashCode + 59) * 59;
        if (map != null) {
            i = map.hashCode();
        }
        return i2 + i;
    }
}
