package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;
/* loaded from: classes13.dex */
public abstract class FromStringDeserializer<T> extends StdScalarDeserializer<T> {

    /* loaded from: classes13.dex */
    protected static class CharsetDeserializer extends FromStringDeserializer<Charset> {
        public CharsetDeserializer() {
            super(Charset.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        /* renamed from: _deserialize  reason: avoid collision after fix types in other method */
        public Charset mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            return Charset.forName(str);
        }
    }

    /* loaded from: classes13.dex */
    public static class CurrencyDeserializer extends FromStringDeserializer<Currency> {
        public CurrencyDeserializer() {
            super(Currency.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        /* renamed from: _deserialize  reason: avoid collision after fix types in other method */
        public Currency mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return Currency.getInstance(str);
        }
    }

    /* loaded from: classes13.dex */
    protected static class InetAddressDeserializer extends FromStringDeserializer<InetAddress> {
        public InetAddressDeserializer() {
            super(InetAddress.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        /* renamed from: _deserialize  reason: avoid collision after fix types in other method */
        public InetAddress mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            return InetAddress.getByName(str);
        }
    }

    /* loaded from: classes13.dex */
    protected static class LocaleDeserializer extends FromStringDeserializer<Locale> {
        public LocaleDeserializer() {
            super(Locale.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        /* renamed from: _deserialize  reason: avoid collision after fix types in other method */
        public Locale mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            int indexOf = str.indexOf(95);
            if (indexOf < 0) {
                return new Locale(str);
            }
            String substring = str.substring(0, indexOf);
            String substring2 = str.substring(indexOf + 1);
            int indexOf2 = substring2.indexOf(95);
            if (indexOf2 < 0) {
                return new Locale(substring, substring2);
            }
            return new Locale(substring, substring2.substring(0, indexOf2), substring2.substring(indexOf2 + 1));
        }
    }

    /* loaded from: classes13.dex */
    public static class PatternDeserializer extends FromStringDeserializer<Pattern> {
        public PatternDeserializer() {
            super(Pattern.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        /* renamed from: _deserialize  reason: avoid collision after fix types in other method */
        public Pattern mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return Pattern.compile(str);
        }
    }

    /* loaded from: classes13.dex */
    protected static class TimeZoneDeserializer extends FromStringDeserializer<TimeZone> {
        public TimeZoneDeserializer() {
            super(TimeZone.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        /* renamed from: _deserialize  reason: avoid collision after fix types in other method */
        public TimeZone mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            return TimeZone.getTimeZone(str);
        }
    }

    /* loaded from: classes13.dex */
    public static class URIDeserializer extends FromStringDeserializer<URI> {
        public URIDeserializer() {
            super(URI.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        /* renamed from: _deserialize  reason: avoid collision after fix types in other method */
        public URI mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return URI.create(str);
        }
    }

    /* loaded from: classes13.dex */
    public static class URLDeserializer extends FromStringDeserializer<URL> {
        public URLDeserializer() {
            super(URL.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        /* renamed from: _deserialize  reason: avoid collision after fix types in other method */
        public URL mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            return new URL(str);
        }
    }

    /* loaded from: classes13.dex */
    public static class UUIDDeserializer extends FromStringDeserializer<UUID> {
        public UUIDDeserializer() {
            super(UUID.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        /* renamed from: _deserialize  reason: avoid collision after fix types in other method */
        public UUID mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return UUID.fromString(str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public UUID _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                if (bArr.length != 16) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can only construct UUIDs from 16 byte arrays; got ");
                    outline107.append(bArr.length);
                    outline107.append(" bytes");
                    deserializationContext.mappingException(outline107.toString());
                }
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
                return new UUID(dataInputStream.readLong(), dataInputStream.readLong());
            }
            super._deserializeEmbedded(obj, deserializationContext);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FromStringDeserializer(Class<?> cls) {
        super(cls);
    }

    public static Iterable<FromStringDeserializer<?>> all() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new UUIDDeserializer());
        arrayList.add(new URLDeserializer());
        arrayList.add(new URIDeserializer());
        arrayList.add(new CurrencyDeserializer());
        arrayList.add(new PatternDeserializer());
        arrayList.add(new LocaleDeserializer());
        arrayList.add(new InetAddressDeserializer());
        arrayList.add(new TimeZoneDeserializer());
        arrayList.add(new CharsetDeserializer());
        return arrayList;
    }

    /* renamed from: _deserialize */
    protected abstract T mo4201_deserialize(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    protected T _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Don't know how to convert embedded Object of type ");
        outline107.append(obj.getClass().getName());
        outline107.append(" into ");
        outline107.append(this._valueClass.getName());
        throw deserializationContext.mappingException(outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize */
    public final T mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            try {
                T mo4201_deserialize = mo4201_deserialize(trim, deserializationContext);
                if (mo4201_deserialize != null) {
                    return mo4201_deserialize;
                }
            } catch (IllegalArgumentException unused) {
            }
            throw deserializationContext.weirdStringException(this._valueClass, "not a valid textual representation");
        } else if (jsonParser.getCurrentToken() == JsonToken.VALUE_EMBEDDED_OBJECT) {
            T t = (T) jsonParser.getEmbeddedObject();
            if (t == null) {
                return null;
            }
            return this._valueClass.isAssignableFrom(t.getClass()) ? t : _deserializeEmbedded(t, deserializationContext);
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }
}
