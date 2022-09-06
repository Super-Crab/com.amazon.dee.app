package com.amazon.org.codehaus.jackson.util;

import com.amazon.org.codehaus.jackson.Base64Variant;
import com.amazon.org.codehaus.jackson.FormatSchema;
import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.JsonStreamContext;
import com.amazon.org.codehaus.jackson.ObjectCodec;
import com.amazon.org.codehaus.jackson.SerializableString;
import com.amazon.org.codehaus.jackson.Version;
import com.amazon.org.codehaus.jackson.io.SerializedString;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes13.dex */
public class JsonGeneratorDelegate extends JsonGenerator {
    protected JsonGenerator delegate;

    public JsonGeneratorDelegate(JsonGenerator jsonGenerator) {
        this.delegate = jsonGenerator;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public boolean canUseSchema(FormatSchema formatSchema) {
        return this.delegate.canUseSchema(formatSchema);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void copyCurrentEvent(JsonParser jsonParser) throws IOException, JsonProcessingException {
        this.delegate.copyCurrentEvent(jsonParser);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void copyCurrentStructure(JsonParser jsonParser) throws IOException, JsonProcessingException {
        this.delegate.copyCurrentStructure(jsonParser);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public JsonGenerator disable(JsonGenerator.Feature feature) {
        return this.delegate.disable(feature);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public JsonGenerator enable(JsonGenerator.Feature feature) {
        return this.delegate.enable(feature);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void flush() throws IOException {
        this.delegate.flush();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public ObjectCodec getCodec() {
        return this.delegate.getCodec();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    /* renamed from: getOutputContext */
    public JsonStreamContext mo4261getOutputContext() {
        return this.delegate.mo4261getOutputContext();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public Object getOutputTarget() {
        return this.delegate.getOutputTarget();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public boolean isClosed() {
        return this.delegate.isClosed();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public boolean isEnabled(JsonGenerator.Feature feature) {
        return this.delegate.isEnabled(feature);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public JsonGenerator setCodec(ObjectCodec objectCodec) {
        this.delegate.setCodec(objectCodec);
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void setSchema(FormatSchema formatSchema) {
        this.delegate.setSchema(formatSchema);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public JsonGenerator useDefaultPrettyPrinter() {
        this.delegate.useDefaultPrettyPrinter();
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator, com.amazon.org.codehaus.jackson.Versioned
    public Version version() {
        return this.delegate.version();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        this.delegate.writeBinary(base64Variant, bArr, i, i2);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeBoolean(boolean z) throws IOException, JsonGenerationException {
        this.delegate.writeBoolean(z);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeEndArray() throws IOException, JsonGenerationException {
        this.delegate.writeEndArray();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeEndObject() throws IOException, JsonGenerationException {
        this.delegate.writeEndObject();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeFieldName(String str) throws IOException, JsonGenerationException {
        this.delegate.writeFieldName(str);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNull() throws IOException, JsonGenerationException {
        this.delegate.writeNull();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(int i) throws IOException, JsonGenerationException {
        this.delegate.writeNumber(i);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeObject(Object obj) throws IOException, JsonProcessingException {
        this.delegate.writeObject(obj);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRaw(String str) throws IOException, JsonGenerationException {
        this.delegate.writeRaw(str);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        this.delegate.writeRawUTF8String(bArr, i, i2);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(String str) throws IOException, JsonGenerationException {
        this.delegate.writeRawValue(str);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeStartArray() throws IOException, JsonGenerationException {
        this.delegate.writeStartArray();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeStartObject() throws IOException, JsonGenerationException {
        this.delegate.writeStartObject();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeString(String str) throws IOException, JsonGenerationException {
        this.delegate.writeString(str);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeTree(JsonNode jsonNode) throws IOException, JsonProcessingException {
        this.delegate.writeTree(jsonNode);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        this.delegate.writeUTF8String(bArr, i, i2);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeFieldName(SerializedString serializedString) throws IOException, JsonGenerationException {
        this.delegate.writeFieldName(serializedString);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(long j) throws IOException, JsonGenerationException {
        this.delegate.writeNumber(j);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRaw(String str, int i, int i2) throws IOException, JsonGenerationException {
        this.delegate.writeRaw(str, i, i2);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(String str, int i, int i2) throws IOException, JsonGenerationException {
        this.delegate.writeRawValue(str, i, i2);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeString(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        this.delegate.writeString(cArr, i, i2);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeFieldName(SerializableString serializableString) throws IOException, JsonGenerationException {
        this.delegate.writeFieldName(serializableString);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(BigInteger bigInteger) throws IOException, JsonGenerationException {
        this.delegate.writeNumber(bigInteger);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRaw(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        this.delegate.writeRaw(cArr, i, i2);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRawValue(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        this.delegate.writeRawValue(cArr, i, i2);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeString(SerializableString serializableString) throws IOException, JsonGenerationException {
        this.delegate.writeString(serializableString);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(double d) throws IOException, JsonGenerationException {
        this.delegate.writeNumber(d);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeRaw(char c) throws IOException, JsonGenerationException {
        this.delegate.writeRaw(c);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(float f) throws IOException, JsonGenerationException {
        this.delegate.writeNumber(f);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) throws IOException, JsonGenerationException {
        this.delegate.writeNumber(bigDecimal);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonGenerator
    public void writeNumber(String str) throws IOException, JsonGenerationException, UnsupportedOperationException {
        this.delegate.writeNumber(str);
    }
}
