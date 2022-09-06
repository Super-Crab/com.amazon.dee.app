package com.amazonaws.util.json;

import com.amazonaws.AmazonClientException;
import com.amazonaws.util.BinaryUtils;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Date;
/* loaded from: classes.dex */
final class JacksonFactory implements AwsJsonFactory {
    private final JsonFactory factory = new JsonFactory();

    /* renamed from: com.amazonaws.util.json.JacksonFactory$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.START_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.END_OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_TRUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_FALSE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_NULL.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$core$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class JacksonReader implements AwsJsonReader {
        private JsonToken nextToken = null;
        private JsonParser reader;

        public JacksonReader(JsonFactory jsonFactory, Reader reader) {
            try {
                this.reader = jsonFactory.createJsonParser(reader);
            } catch (IOException e) {
                throw new AmazonClientException("Failed to create JSON reader", e);
            }
        }

        private void clearToken() throws IOException {
            this.nextToken = null;
        }

        private void expect(JsonToken jsonToken) throws IOException {
            if (this.nextToken == jsonToken) {
                return;
            }
            throw new IOException("Expected " + jsonToken + " but was " + jsonToken);
        }

        private void nextToken() throws IOException {
            if (this.nextToken == null) {
                this.nextToken = this.reader.nextToken();
            }
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public void beginArray() throws IOException {
            nextToken();
            expect(JsonToken.START_ARRAY);
            clearToken();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public void beginObject() throws IOException {
            nextToken();
            expect(JsonToken.START_OBJECT);
            clearToken();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public void close() throws IOException {
            this.reader.close();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public void endArray() throws IOException {
            nextToken();
            expect(JsonToken.END_ARRAY);
            clearToken();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public void endObject() throws IOException {
            nextToken();
            expect(JsonToken.END_OBJECT);
            clearToken();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public boolean hasNext() throws IOException {
            nextToken();
            JsonToken jsonToken = JsonToken.END_OBJECT;
            JsonToken jsonToken2 = this.nextToken;
            return (jsonToken == jsonToken2 || JsonToken.END_ARRAY == jsonToken2) ? false : true;
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public boolean isContainer() throws IOException {
            nextToken();
            JsonToken jsonToken = JsonToken.START_ARRAY;
            JsonToken jsonToken2 = this.nextToken;
            return jsonToken == jsonToken2 || JsonToken.START_OBJECT == jsonToken2;
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public String nextName() throws IOException {
            nextToken();
            expect(JsonToken.FIELD_NAME);
            clearToken();
            return this.reader.getText();
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public String nextString() throws IOException {
            nextToken();
            String text = JsonToken.VALUE_NULL == this.nextToken ? null : this.reader.getText();
            clearToken();
            return text;
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public AwsJsonToken peek() throws IOException {
            nextToken();
            return JacksonFactory.convert(this.nextToken);
        }

        @Override // com.amazonaws.util.json.AwsJsonReader
        public void skipValue() throws IOException {
            nextToken();
            this.reader.skipChildren();
            clearToken();
        }
    }

    /* loaded from: classes.dex */
    private static final class JacksonWriter implements AwsJsonWriter {
        private static final int NEGATIVE_THREE = -3;
        private JsonGenerator writer;

        public JacksonWriter(JsonFactory jsonFactory, Writer writer) {
            try {
                this.writer = jsonFactory.createGenerator(writer);
            } catch (IOException e) {
                throw new AmazonClientException("Failed to create json writer", e);
            }
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter beginArray() throws IOException {
            this.writer.writeStartArray();
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter beginObject() throws IOException {
            this.writer.writeStartObject();
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public void close() throws IOException {
            this.writer.close();
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter endArray() throws IOException {
            this.writer.writeEndArray();
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter endObject() throws IOException {
            this.writer.writeEndObject();
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public void flush() throws IOException {
            this.writer.flush();
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter name(String str) throws IOException {
            this.writer.writeFieldName(str);
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(String str) throws IOException {
            this.writer.writeString(str);
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(boolean z) throws IOException {
            this.writer.writeBoolean(z);
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(double d) throws IOException {
            this.writer.writeNumber(d);
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(long j) throws IOException {
            this.writer.writeNumber(j);
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(Number number) throws IOException {
            this.writer.writeNumber(number.toString());
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(Date date) throws IOException {
            this.writer.writeNumber(BigDecimal.valueOf(date.getTime()).scaleByPowerOfTen(-3).toPlainString());
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value(ByteBuffer byteBuffer) throws IOException {
            byteBuffer.mark();
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr, 0, bArr.length);
            byteBuffer.reset();
            this.writer.writeString(BinaryUtils.toBase64(bArr));
            return this;
        }

        @Override // com.amazonaws.util.json.AwsJsonWriter
        public AwsJsonWriter value() throws IOException {
            this.writer.writeNull();
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AwsJsonToken convert(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        switch (jsonToken.ordinal()) {
            case 1:
                return AwsJsonToken.BEGIN_OBJECT;
            case 2:
                return AwsJsonToken.END_OBJECT;
            case 3:
                return AwsJsonToken.BEGIN_ARRAY;
            case 4:
                return AwsJsonToken.END_ARRAY;
            case 5:
                return AwsJsonToken.FIELD_NAME;
            case 6:
            default:
                return AwsJsonToken.UNKNOWN;
            case 7:
                return AwsJsonToken.VALUE_STRING;
            case 8:
            case 9:
                return AwsJsonToken.VALUE_NUMBER;
            case 10:
            case 11:
                return AwsJsonToken.VALUE_BOOLEAN;
            case 12:
                return AwsJsonToken.VALUE_NULL;
        }
    }

    @Override // com.amazonaws.util.json.AwsJsonFactory
    public AwsJsonReader getJsonReader(Reader reader) {
        return new JacksonReader(this.factory, reader);
    }

    @Override // com.amazonaws.util.json.AwsJsonFactory
    public AwsJsonWriter getJsonWriter(Writer writer) {
        return new JacksonWriter(this.factory, writer);
    }
}
