package com.amazon.whisperjoin.provisioning.bluetooth.request.serializers;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
/* loaded from: classes13.dex */
public class JsonSerializer implements Serializer {
    public static final String TAG = "com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.JsonSerializer";
    private final Gson mGson;

    public JsonSerializer() {
        this(new Gson());
    }

    @Override // com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Serializer
    public <T extends Validatable> T deserialize(byte[] bArr, Class<T> cls) throws IllegalArgumentException {
        try {
            CharsetDecoder newDecoder = Charset.forName("UTF-8").newDecoder();
            newDecoder.onMalformedInput(CodingErrorAction.REPORT);
            newDecoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
            JsonReader jsonReader = new JsonReader(new StringReader(newDecoder.decode(ByteBuffer.wrap(bArr)).toString()));
            jsonReader.setLenient(true);
            T t = (T) this.mGson.fromJson(jsonReader, cls);
            t.validate();
            return t;
        } catch (JsonParseException unused) {
            Log.e(TAG, "JsonParseException thrown deserializing JSON string");
            throw new UnparsableDataException("JsonParseException thrown deserializing JSON string");
        } catch (CharacterCodingException unused2) {
            Log.e(TAG, "CharacterCodingException thrown deserializing JSON string");
            throw new UnparsableDataException("CharacterCodingException thrown deserializing JSON string");
        }
    }

    @Override // com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Serializer
    public byte[] serialize(Object obj) {
        return this.mGson.toJson(obj).getBytes(Charset.forName("UTF-8"));
    }

    public JsonSerializer(Gson gson) {
        this.mGson = gson;
    }
}
