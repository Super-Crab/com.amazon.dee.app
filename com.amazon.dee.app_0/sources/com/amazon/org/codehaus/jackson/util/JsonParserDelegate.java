package com.amazon.org.codehaus.jackson.util;

import com.amazon.org.codehaus.jackson.Base64Variant;
import com.amazon.org.codehaus.jackson.FormatSchema;
import com.amazon.org.codehaus.jackson.JsonLocation;
import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonStreamContext;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.ObjectCodec;
import com.amazon.org.codehaus.jackson.Version;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes13.dex */
public class JsonParserDelegate extends JsonParser {
    protected JsonParser delegate;

    public JsonParserDelegate(JsonParser jsonParser) {
        this.delegate = jsonParser;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public boolean canUseSchema(FormatSchema formatSchema) {
        return this.delegate.canUseSchema(formatSchema);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public void clearCurrentToken() {
        this.delegate.clearCurrentToken();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonParser disable(JsonParser.Feature feature) {
        this.delegate.disable(feature);
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonParser enable(JsonParser.Feature feature) {
        this.delegate.enable(feature);
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
        return this.delegate.getBigIntegerValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        return this.delegate.getBinaryValue(base64Variant);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public boolean getBooleanValue() throws IOException, JsonParseException {
        return this.delegate.getBooleanValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public byte getByteValue() throws IOException, JsonParseException {
        return this.delegate.getByteValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public ObjectCodec getCodec() {
        return this.delegate.getCodec();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public String getCurrentName() throws IOException, JsonParseException {
        return this.delegate.getCurrentName();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonToken getCurrentToken() {
        return this.delegate.getCurrentToken();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public BigDecimal getDecimalValue() throws IOException, JsonParseException {
        return this.delegate.getDecimalValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public double getDoubleValue() throws IOException, JsonParseException {
        return this.delegate.getDoubleValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public Object getEmbeddedObject() throws IOException, JsonParseException {
        return this.delegate.getEmbeddedObject();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public float getFloatValue() throws IOException, JsonParseException {
        return this.delegate.getFloatValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public Object getInputSource() {
        return this.delegate.getInputSource();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public int getIntValue() throws IOException, JsonParseException {
        return this.delegate.getIntValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonToken getLastClearedToken() {
        return this.delegate.getLastClearedToken();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public long getLongValue() throws IOException, JsonParseException {
        return this.delegate.getLongValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonParser.NumberType getNumberType() throws IOException, JsonParseException {
        return this.delegate.getNumberType();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public Number getNumberValue() throws IOException, JsonParseException {
        return this.delegate.getNumberValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    /* renamed from: getParsingContext */
    public JsonStreamContext mo4092getParsingContext() {
        return this.delegate.mo4092getParsingContext();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public short getShortValue() throws IOException, JsonParseException {
        return this.delegate.getShortValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public String getText() throws IOException, JsonParseException {
        return this.delegate.getText();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public char[] getTextCharacters() throws IOException, JsonParseException {
        return this.delegate.getTextCharacters();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public int getTextLength() throws IOException, JsonParseException {
        return this.delegate.getTextLength();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public int getTextOffset() throws IOException, JsonParseException {
        return this.delegate.getTextOffset();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public boolean hasCurrentToken() {
        return this.delegate.hasCurrentToken();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public boolean isClosed() {
        return this.delegate.isClosed();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public boolean isEnabled(JsonParser.Feature feature) {
        return this.delegate.isEnabled(feature);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonToken nextToken() throws IOException, JsonParseException {
        return this.delegate.nextToken();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this.delegate.setCodec(objectCodec);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public void setSchema(FormatSchema formatSchema) {
        this.delegate.setSchema(formatSchema);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonParser skipChildren() throws IOException, JsonParseException {
        this.delegate.skipChildren();
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser, com.amazon.org.codehaus.jackson.Versioned
    public Version version() {
        return this.delegate.version();
    }
}
