package org.codehaus.jackson.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.codehaus.jackson.Base64Variant;
import org.codehaus.jackson.FormatSchema;
import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.Version;
/* loaded from: classes5.dex */
public class JsonParserDelegate extends JsonParser {
    protected JsonParser delegate;

    public JsonParserDelegate(JsonParser jsonParser) {
        this.delegate = jsonParser;
    }

    @Override // org.codehaus.jackson.JsonParser
    public boolean canUseSchema(FormatSchema formatSchema) {
        return this.delegate.canUseSchema(formatSchema);
    }

    @Override // org.codehaus.jackson.JsonParser
    public void clearCurrentToken() {
        this.delegate.clearCurrentToken();
    }

    @Override // org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonParser disable(JsonParser.Feature feature) {
        this.delegate.disable(feature);
        return this;
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonParser enable(JsonParser.Feature feature) {
        this.delegate.enable(feature);
        return this;
    }

    @Override // org.codehaus.jackson.JsonParser
    public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
        return this.delegate.getBigIntegerValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        return this.delegate.getBinaryValue(base64Variant);
    }

    @Override // org.codehaus.jackson.JsonParser
    public boolean getBooleanValue() throws IOException, JsonParseException {
        return this.delegate.getBooleanValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public byte getByteValue() throws IOException, JsonParseException {
        return this.delegate.getByteValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public ObjectCodec getCodec() {
        return this.delegate.getCodec();
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    @Override // org.codehaus.jackson.JsonParser
    public String getCurrentName() throws IOException, JsonParseException {
        return this.delegate.getCurrentName();
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonToken getCurrentToken() {
        return this.delegate.getCurrentToken();
    }

    @Override // org.codehaus.jackson.JsonParser
    public BigDecimal getDecimalValue() throws IOException, JsonParseException {
        return this.delegate.getDecimalValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public double getDoubleValue() throws IOException, JsonParseException {
        return this.delegate.getDoubleValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public Object getEmbeddedObject() throws IOException, JsonParseException {
        return this.delegate.getEmbeddedObject();
    }

    @Override // org.codehaus.jackson.JsonParser
    public float getFloatValue() throws IOException, JsonParseException {
        return this.delegate.getFloatValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public Object getInputSource() {
        return this.delegate.getInputSource();
    }

    @Override // org.codehaus.jackson.JsonParser
    public int getIntValue() throws IOException, JsonParseException {
        return this.delegate.getIntValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonToken getLastClearedToken() {
        return this.delegate.getLastClearedToken();
    }

    @Override // org.codehaus.jackson.JsonParser
    public long getLongValue() throws IOException, JsonParseException {
        return this.delegate.getLongValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonParser.NumberType getNumberType() throws IOException, JsonParseException {
        return this.delegate.getNumberType();
    }

    @Override // org.codehaus.jackson.JsonParser
    public Number getNumberValue() throws IOException, JsonParseException {
        return this.delegate.getNumberValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    /* renamed from: getParsingContext */
    public JsonStreamContext mo12869getParsingContext() {
        return this.delegate.mo12869getParsingContext();
    }

    @Override // org.codehaus.jackson.JsonParser
    public short getShortValue() throws IOException, JsonParseException {
        return this.delegate.getShortValue();
    }

    @Override // org.codehaus.jackson.JsonParser
    public String getText() throws IOException, JsonParseException {
        return this.delegate.getText();
    }

    @Override // org.codehaus.jackson.JsonParser
    public char[] getTextCharacters() throws IOException, JsonParseException {
        return this.delegate.getTextCharacters();
    }

    @Override // org.codehaus.jackson.JsonParser
    public int getTextLength() throws IOException, JsonParseException {
        return this.delegate.getTextLength();
    }

    @Override // org.codehaus.jackson.JsonParser
    public int getTextOffset() throws IOException, JsonParseException {
        return this.delegate.getTextOffset();
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    @Override // org.codehaus.jackson.JsonParser
    public boolean hasCurrentToken() {
        return this.delegate.hasCurrentToken();
    }

    @Override // org.codehaus.jackson.JsonParser
    public boolean isClosed() {
        return this.delegate.isClosed();
    }

    @Override // org.codehaus.jackson.JsonParser
    public boolean isEnabled(JsonParser.Feature feature) {
        return this.delegate.isEnabled(feature);
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonToken nextToken() throws IOException, JsonParseException {
        return this.delegate.nextToken();
    }

    @Override // org.codehaus.jackson.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this.delegate.setCodec(objectCodec);
    }

    @Override // org.codehaus.jackson.JsonParser
    public void setSchema(FormatSchema formatSchema) {
        this.delegate.setSchema(formatSchema);
    }

    @Override // org.codehaus.jackson.JsonParser
    public JsonParser skipChildren() throws IOException, JsonParseException {
        this.delegate.skipChildren();
        return this;
    }

    @Override // org.codehaus.jackson.JsonParser, org.codehaus.jackson.Versioned
    public Version version() {
        return this.delegate.version();
    }
}
