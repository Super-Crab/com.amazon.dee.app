package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import java.io.IOException;
import java.net.InetAddress;
/* loaded from: classes13.dex */
public class InetAddressSerializer extends ScalarSerializerBase<InetAddress> {
    public static final InetAddressSerializer instance = new InetAddressSerializer();

    public InetAddressSerializer() {
        super(InetAddress.class);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serialize(InetAddress inetAddress, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        String trim = inetAddress.toString().trim();
        int indexOf = trim.indexOf(47);
        if (indexOf >= 0) {
            if (indexOf == 0) {
                trim = trim.substring(1);
            } else {
                trim = trim.substring(0, indexOf);
            }
        }
        jsonGenerator.writeString(trim);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(InetAddress inetAddress, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForScalar(inetAddress, jsonGenerator, InetAddress.class);
        serialize(inetAddress, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForScalar(inetAddress, jsonGenerator);
    }
}
