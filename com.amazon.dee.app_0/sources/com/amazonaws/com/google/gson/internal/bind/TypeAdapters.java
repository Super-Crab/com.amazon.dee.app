package com.amazonaws.com.google.gson.internal.bind;

import com.amazonaws.com.google.gson.Gson;
import com.amazonaws.com.google.gson.JsonArray;
import com.amazonaws.com.google.gson.JsonElement;
import com.amazonaws.com.google.gson.JsonIOException;
import com.amazonaws.com.google.gson.JsonNull;
import com.amazonaws.com.google.gson.JsonObject;
import com.amazonaws.com.google.gson.JsonPrimitive;
import com.amazonaws.com.google.gson.JsonSyntaxException;
import com.amazonaws.com.google.gson.TypeAdapter;
import com.amazonaws.com.google.gson.TypeAdapterFactory;
import com.amazonaws.com.google.gson.annotations.SerializedName;
import com.amazonaws.com.google.gson.internal.LazilyParsedNumber;
import com.amazonaws.com.google.gson.reflect.TypeToken;
import com.amazonaws.com.google.gson.stream.JsonReader;
import com.amazonaws.com.google.gson.stream.JsonToken;
import com.amazonaws.com.google.gson.stream.JsonWriter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
/* loaded from: classes13.dex */
public final class TypeAdapters {
    public static final TypeAdapter<Class> CLASS = new TypeAdapter<Class>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read */
        public Class mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Class cls) throws IOException {
            if (cls == null) {
                jsonWriter.nullValue();
                return;
            }
            throw new UnsupportedOperationException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Attempted to serialize java.lang.Class: "), ". Forgot to register a type adapter?"));
        }
    };
    public static final TypeAdapterFactory CLASS_FACTORY = newFactory(Class.class, CLASS);
    public static final TypeAdapter<BitSet> BIT_SET = new TypeAdapter<BitSet>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.2
        /* JADX WARN: Code restructure failed: missing block: B:19:0x004d, code lost:
            if (r8.nextInt() != 0) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0058, code lost:
            if (java.lang.Integer.parseInt(r1) != 0) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x005a, code lost:
            r1 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x005c, code lost:
            r1 = false;
         */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read  reason: collision with other method in class */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.util.BitSet mo6687read(com.amazonaws.com.google.gson.stream.JsonReader r8) throws java.io.IOException {
            /*
                r7 = this;
                com.amazonaws.com.google.gson.stream.JsonToken r0 = r8.peek()
                com.amazonaws.com.google.gson.stream.JsonToken r1 = com.amazonaws.com.google.gson.stream.JsonToken.NULL
                if (r0 != r1) goto Ld
                r8.nextNull()
                r8 = 0
                return r8
            Ld:
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r8.beginArray()
                com.amazonaws.com.google.gson.stream.JsonToken r1 = r8.peek()
                r2 = 0
                r3 = r2
            L1b:
                com.amazonaws.com.google.gson.stream.JsonToken r4 = com.amazonaws.com.google.gson.stream.JsonToken.END_ARRAY
                if (r1 == r4) goto L75
                int r4 = r1.ordinal()
                r5 = 5
                r6 = 1
                if (r4 == r5) goto L50
                r5 = 6
                if (r4 == r5) goto L49
                r5 = 7
                if (r4 != r5) goto L32
                boolean r1 = r8.nextBoolean()
                goto L5d
            L32:
                com.amazonaws.com.google.gson.JsonSyntaxException r8 = new com.amazonaws.com.google.gson.JsonSyntaxException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "Invalid bitset value type: "
                r0.append(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r8.<init>(r0)
                throw r8
            L49:
                int r1 = r8.nextInt()
                if (r1 == 0) goto L5c
                goto L5a
            L50:
                java.lang.String r1 = r8.nextString()
                int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L69
                if (r1 == 0) goto L5c
            L5a:
                r1 = r6
                goto L5d
            L5c:
                r1 = r2
            L5d:
                if (r1 == 0) goto L62
                r0.set(r3)
            L62:
                int r3 = r3 + 1
                com.amazonaws.com.google.gson.stream.JsonToken r1 = r8.peek()
                goto L1b
            L69:
                com.amazonaws.com.google.gson.JsonSyntaxException r8 = new com.amazonaws.com.google.gson.JsonSyntaxException
                java.lang.String r0 = "Error: Expecting: bitset number value (1, 0), Found: "
                java.lang.String r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline72(r0, r1)
                r8.<init>(r0)
                throw r8
            L75:
                r8.endArray()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.AnonymousClass2.mo6687read(com.amazonaws.com.google.gson.stream.JsonReader):java.util.BitSet");
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginArray();
            for (int i = 0; i < bitSet.length(); i++) {
                jsonWriter.value(bitSet.get(i) ? 1L : 0L);
            }
            jsonWriter.endArray();
        }
    };
    public static final TypeAdapterFactory BIT_SET_FACTORY = newFactory(BitSet.class, BIT_SET);
    public static final TypeAdapter<Boolean> BOOLEAN = new TypeAdapter<Boolean>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.3
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read */
        public Boolean mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            } else if (jsonReader.peek() == JsonToken.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(jsonReader.nextString()));
            } else {
                return Boolean.valueOf(jsonReader.nextBoolean());
            }
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
            if (bool == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(bool.booleanValue());
            }
        }
    };
    public static final TypeAdapter<Boolean> BOOLEAN_AS_STRING = new TypeAdapter<Boolean>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.4
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read */
        public Boolean mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return Boolean.valueOf(jsonReader.nextString());
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
            jsonWriter.value(bool == null ? "null" : bool.toString());
        }
    };
    public static final TypeAdapterFactory BOOLEAN_FACTORY = newFactory(Boolean.TYPE, Boolean.class, BOOLEAN);
    public static final TypeAdapter<Number> BYTE = new TypeAdapter<Number>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.5
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Byte.valueOf((byte) jsonReader.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapterFactory BYTE_FACTORY = newFactory(Byte.TYPE, Byte.class, BYTE);
    public static final TypeAdapter<Number> SHORT = new TypeAdapter<Number>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.6
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Short.valueOf((short) jsonReader.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapterFactory SHORT_FACTORY = newFactory(Short.TYPE, Short.class, SHORT);
    public static final TypeAdapter<Number> INTEGER = new TypeAdapter<Number>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.7
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Integer.valueOf(jsonReader.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapterFactory INTEGER_FACTORY = newFactory(Integer.TYPE, Integer.class, INTEGER);
    public static final TypeAdapter<Number> LONG = new TypeAdapter<Number>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.8
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Long.valueOf(jsonReader.nextLong());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapter<Number> FLOAT = new TypeAdapter<Number>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.9
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return Float.valueOf((float) jsonReader.nextDouble());
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapter<Number> DOUBLE = new TypeAdapter<Number>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.10
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return Double.valueOf(jsonReader.nextDouble());
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapter<Number> NUMBER = new TypeAdapter<Number>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.11
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read */
        public Number mo6687read(JsonReader jsonReader) throws IOException {
            JsonToken peek = jsonReader.peek();
            int ordinal = peek.ordinal();
            if (ordinal != 6) {
                if (ordinal == 8) {
                    jsonReader.nextNull();
                    return null;
                }
                throw new JsonSyntaxException("Expecting number, got: " + peek);
            }
            return new LazilyParsedNumber(jsonReader.nextString());
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };
    public static final TypeAdapterFactory NUMBER_FACTORY = newFactory(Number.class, NUMBER);
    public static final TypeAdapter<Character> CHARACTER = new TypeAdapter<Character>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.12
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read */
        public Character mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String nextString = jsonReader.nextString();
            if (nextString.length() == 1) {
                return Character.valueOf(nextString.charAt(0));
            }
            throw new JsonSyntaxException(GeneratedOutlineSupport1.outline72("Expecting character, got: ", nextString));
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Character ch) throws IOException {
            jsonWriter.value(ch == null ? null : String.valueOf(ch));
        }
    };
    public static final TypeAdapterFactory CHARACTER_FACTORY = newFactory(Character.TYPE, Character.class, CHARACTER);
    public static final TypeAdapter<String> STRING = new TypeAdapter<String>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.13
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read  reason: collision with other method in class */
        public String mo6687read(JsonReader jsonReader) throws IOException {
            JsonToken peek = jsonReader.peek();
            if (peek == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            } else if (peek == JsonToken.BOOLEAN) {
                return Boolean.toString(jsonReader.nextBoolean());
            } else {
                return jsonReader.nextString();
            }
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, String str) throws IOException {
            jsonWriter.value(str);
        }
    };
    public static final TypeAdapter<BigDecimal> BIG_DECIMAL = new TypeAdapter<BigDecimal>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.14
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read  reason: collision with other method in class */
        public BigDecimal mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return new BigDecimal(jsonReader.nextString());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, BigDecimal bigDecimal) throws IOException {
            jsonWriter.value(bigDecimal);
        }
    };
    public static final TypeAdapter<BigInteger> BIG_INTEGER = new TypeAdapter<BigInteger>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.15
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read  reason: collision with other method in class */
        public BigInteger mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return new BigInteger(jsonReader.nextString());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, BigInteger bigInteger) throws IOException {
            jsonWriter.value(bigInteger);
        }
    };
    public static final TypeAdapterFactory STRING_FACTORY = newFactory(String.class, STRING);
    public static final TypeAdapter<StringBuilder> STRING_BUILDER = new TypeAdapter<StringBuilder>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.16
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read  reason: collision with other method in class */
        public StringBuilder mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return new StringBuilder(jsonReader.nextString());
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, StringBuilder sb) throws IOException {
            jsonWriter.value(sb == null ? null : sb.toString());
        }
    };
    public static final TypeAdapterFactory STRING_BUILDER_FACTORY = newFactory(StringBuilder.class, STRING_BUILDER);
    public static final TypeAdapter<StringBuffer> STRING_BUFFER = new TypeAdapter<StringBuffer>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.17
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read  reason: collision with other method in class */
        public StringBuffer mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return new StringBuffer(jsonReader.nextString());
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, StringBuffer stringBuffer) throws IOException {
            jsonWriter.value(stringBuffer == null ? null : stringBuffer.toString());
        }
    };
    public static final TypeAdapterFactory STRING_BUFFER_FACTORY = newFactory(StringBuffer.class, STRING_BUFFER);
    public static final TypeAdapter<URL> URL = new TypeAdapter<URL>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.18
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read  reason: collision with other method in class */
        public URL mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String nextString = jsonReader.nextString();
            if (!"null".equals(nextString)) {
                return new URL(nextString);
            }
            return null;
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, URL url) throws IOException {
            jsonWriter.value(url == null ? null : url.toExternalForm());
        }
    };
    public static final TypeAdapterFactory URL_FACTORY = newFactory(URL.class, URL);
    public static final TypeAdapter<URI> URI = new TypeAdapter<URI>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.19
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read  reason: collision with other method in class */
        public URI mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                String nextString = jsonReader.nextString();
                if (!"null".equals(nextString)) {
                    return new URI(nextString);
                }
                return null;
            } catch (URISyntaxException e) {
                throw new JsonIOException(e);
            }
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, URI uri) throws IOException {
            jsonWriter.value(uri == null ? null : uri.toASCIIString());
        }
    };
    public static final TypeAdapterFactory URI_FACTORY = newFactory(URI.class, URI);
    public static final TypeAdapter<InetAddress> INET_ADDRESS = new TypeAdapter<InetAddress>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.20
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read  reason: collision with other method in class */
        public InetAddress mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return InetAddress.getByName(jsonReader.nextString());
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, InetAddress inetAddress) throws IOException {
            jsonWriter.value(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    };
    public static final TypeAdapterFactory INET_ADDRESS_FACTORY = newTypeHierarchyFactory(InetAddress.class, INET_ADDRESS);
    public static final TypeAdapter<UUID> UUID = new TypeAdapter<UUID>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.21
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read  reason: collision with other method in class */
        public UUID mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return UUID.fromString(jsonReader.nextString());
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, UUID uuid) throws IOException {
            jsonWriter.value(uuid == null ? null : uuid.toString());
        }
    };
    public static final TypeAdapterFactory UUID_FACTORY = newFactory(UUID.class, UUID);
    public static final TypeAdapterFactory TIMESTAMP_FACTORY = new TypeAdapterFactory() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.22
        @Override // com.amazonaws.com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() != Timestamp.class) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(Date.class);
            return (TypeAdapter<T>) new TypeAdapter<Timestamp>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.22.1
                @Override // com.amazonaws.com.google.gson.TypeAdapter
                /* renamed from: read  reason: collision with other method in class */
                public Timestamp mo6687read(JsonReader jsonReader) throws IOException {
                    Date date = (Date) adapter.mo6687read(jsonReader);
                    if (date != null) {
                        return new Timestamp(date.getTime());
                    }
                    return null;
                }

                @Override // com.amazonaws.com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, Timestamp timestamp) throws IOException {
                    adapter.write(jsonWriter, timestamp);
                }
            };
        }
    };
    public static final TypeAdapter<Calendar> CALENDAR = new TypeAdapter<Calendar>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.23
        private static final String DAY_OF_MONTH = "dayOfMonth";
        private static final String HOUR_OF_DAY = "hourOfDay";
        private static final String MINUTE = "minute";
        private static final String MONTH = "month";
        private static final String SECOND = "second";
        private static final String YEAR = "year";

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read  reason: collision with other method in class */
        public Calendar mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (jsonReader.peek() != JsonToken.END_OBJECT) {
                String nextName = jsonReader.nextName();
                int nextInt = jsonReader.nextInt();
                if ("year".equals(nextName)) {
                    i = nextInt;
                } else if ("month".equals(nextName)) {
                    i2 = nextInt;
                } else if (DAY_OF_MONTH.equals(nextName)) {
                    i3 = nextInt;
                } else if (HOUR_OF_DAY.equals(nextName)) {
                    i4 = nextInt;
                } else if (MINUTE.equals(nextName)) {
                    i5 = nextInt;
                } else if (SECOND.equals(nextName)) {
                    i6 = nextInt;
                }
            }
            jsonReader.endObject();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Calendar calendar) throws IOException {
            if (calendar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("year");
            jsonWriter.value(calendar.get(1));
            jsonWriter.name("month");
            jsonWriter.value(calendar.get(2));
            jsonWriter.name(DAY_OF_MONTH);
            jsonWriter.value(calendar.get(5));
            jsonWriter.name(HOUR_OF_DAY);
            jsonWriter.value(calendar.get(11));
            jsonWriter.name(MINUTE);
            jsonWriter.value(calendar.get(12));
            jsonWriter.name(SECOND);
            jsonWriter.value(calendar.get(13));
            jsonWriter.endObject();
        }
    };
    public static final TypeAdapterFactory CALENDAR_FACTORY = newFactoryForMultipleTypes(Calendar.class, GregorianCalendar.class, CALENDAR);
    public static final TypeAdapter<Locale> LOCALE = new TypeAdapter<Locale>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.24
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read  reason: collision with other method in class */
        public Locale mo6687read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(jsonReader.nextString(), "_");
            String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            if (stringTokenizer.hasMoreElements()) {
                str = stringTokenizer.nextToken();
            }
            if (nextToken2 == null && str == null) {
                return new Locale(nextToken);
            }
            if (str == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, str);
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Locale locale) throws IOException {
            jsonWriter.value(locale == null ? null : locale.toString());
        }
    };
    public static final TypeAdapterFactory LOCALE_FACTORY = newFactory(Locale.class, LOCALE);
    public static final TypeAdapter<JsonElement> JSON_ELEMENT = new TypeAdapter<JsonElement>() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.25
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read */
        public JsonElement mo6687read(JsonReader jsonReader) throws IOException {
            int ordinal = jsonReader.peek().ordinal();
            if (ordinal == 0) {
                JsonArray jsonArray = new JsonArray();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonArray.add(mo6687read(jsonReader));
                }
                jsonReader.endArray();
                return jsonArray;
            } else if (ordinal == 2) {
                JsonObject jsonObject = new JsonObject();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    jsonObject.add(jsonReader.nextName(), mo6687read(jsonReader));
                }
                jsonReader.endObject();
                return jsonObject;
            } else if (ordinal == 5) {
                return new JsonPrimitive(jsonReader.nextString());
            } else {
                if (ordinal == 6) {
                    return new JsonPrimitive((Number) new LazilyParsedNumber(jsonReader.nextString()));
                }
                if (ordinal == 7) {
                    return new JsonPrimitive(Boolean.valueOf(jsonReader.nextBoolean()));
                }
                if (ordinal == 8) {
                    jsonReader.nextNull();
                    return JsonNull.INSTANCE;
                }
                throw new IllegalArgumentException();
            }
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, JsonElement jsonElement) throws IOException {
            if (jsonElement != null && !jsonElement.isJsonNull()) {
                if (jsonElement.isJsonPrimitive()) {
                    JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
                    if (asJsonPrimitive.isNumber()) {
                        jsonWriter.value(asJsonPrimitive.getAsNumber());
                        return;
                    } else if (asJsonPrimitive.isBoolean()) {
                        jsonWriter.value(asJsonPrimitive.getAsBoolean());
                        return;
                    } else {
                        jsonWriter.value(asJsonPrimitive.getAsString());
                        return;
                    }
                } else if (jsonElement.isJsonArray()) {
                    jsonWriter.beginArray();
                    Iterator<JsonElement> it2 = jsonElement.getAsJsonArray().iterator();
                    while (it2.hasNext()) {
                        write(jsonWriter, it2.next());
                    }
                    jsonWriter.endArray();
                    return;
                } else if (jsonElement.isJsonObject()) {
                    jsonWriter.beginObject();
                    for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                        jsonWriter.name(entry.getKey());
                        write(jsonWriter, entry.getValue());
                    }
                    jsonWriter.endObject();
                    return;
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Couldn't write ");
                    outline107.append(jsonElement.getClass());
                    throw new IllegalArgumentException(outline107.toString());
                }
            }
            jsonWriter.nullValue();
        }
    };
    public static final TypeAdapterFactory JSON_ELEMENT_FACTORY = newTypeHierarchyFactory(JsonElement.class, JSON_ELEMENT);
    public static final TypeAdapterFactory ENUM_FACTORY = newEnumTypeHierarchyFactory();

    /* renamed from: com.amazonaws.com.google.gson.internal.bind.TypeAdapters$32  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass32 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* loaded from: classes13.dex */
    private static final class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {
        private final Map<String, T> nameToConstant = new HashMap();
        private final Map<T, String> constantToName = new HashMap();

        public EnumTypeAdapter(Class<T> cls) {
            T[] enumConstants;
            try {
                for (T t : cls.getEnumConstants()) {
                    String name = t.name();
                    SerializedName serializedName = (SerializedName) cls.getField(name).getAnnotation(SerializedName.class);
                    name = serializedName != null ? serializedName.value() : name;
                    this.nameToConstant.put(name, t);
                    this.constantToName.put(t, name);
                }
            } catch (NoSuchFieldException unused) {
                throw new AssertionError();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.amazonaws.com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) throws IOException {
            write(jsonWriter, (JsonWriter) ((Enum) obj));
        }

        @Override // com.amazonaws.com.google.gson.TypeAdapter
        /* renamed from: read */
        public T mo6687read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return this.nameToConstant.get(jsonReader.nextString());
        }

        public void write(JsonWriter jsonWriter, T t) throws IOException {
            jsonWriter.value(t == null ? null : this.constantToName.get(t));
        }
    }

    private TypeAdapters() {
    }

    public static TypeAdapterFactory newEnumTypeHierarchyFactory() {
        return new TypeAdapterFactory() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.26
            @Override // com.amazonaws.com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class rawType = typeToken.getRawType();
                if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                    return null;
                }
                if (!rawType.isEnum()) {
                    rawType = (Class<? super Object>) rawType.getSuperclass();
                }
                return new EnumTypeAdapter(rawType);
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final TypeToken<TT> typeToken, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.27
            @Override // com.amazonaws.com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken2) {
                if (typeToken2.equals(TypeToken.this)) {
                    return typeAdapter;
                }
                return null;
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactoryForMultipleTypes(final Class<TT> cls, final Class<? extends TT> cls2, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.30
            @Override // com.amazonaws.com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                if (rawType == cls || rawType == cls2) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Factory[type=");
                GeneratedOutlineSupport1.outline146(cls, outline107, "+");
                GeneratedOutlineSupport1.outline146(cls2, outline107, ",adapter=");
                outline107.append(typeAdapter);
                outline107.append("]");
                return outline107.toString();
            }
        };
    }

    public static <TT> TypeAdapterFactory newTypeHierarchyFactory(final Class<TT> cls, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.31
            @Override // com.amazonaws.com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                if (cls.isAssignableFrom(typeToken.getRawType())) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Factory[typeHierarchy=");
                GeneratedOutlineSupport1.outline146(cls, outline107, ",adapter=");
                outline107.append(typeAdapter);
                outline107.append("]");
                return outline107.toString();
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final Class<TT> cls, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.28
            @Override // com.amazonaws.com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                if (typeToken.getRawType() == cls) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Factory[type=");
                GeneratedOutlineSupport1.outline146(cls, outline107, ",adapter=");
                outline107.append(typeAdapter);
                outline107.append("]");
                return outline107.toString();
            }
        };
    }

    public static <TT> TypeAdapterFactory newFactory(final Class<TT> cls, final Class<TT> cls2, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.amazonaws.com.google.gson.internal.bind.TypeAdapters.29
            @Override // com.amazonaws.com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                if (rawType == cls || rawType == cls2) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Factory[type=");
                GeneratedOutlineSupport1.outline146(cls2, outline107, "+");
                GeneratedOutlineSupport1.outline146(cls, outline107, ",adapter=");
                outline107.append(typeAdapter);
                outline107.append("]");
                return outline107.toString();
            }
        };
    }
}
