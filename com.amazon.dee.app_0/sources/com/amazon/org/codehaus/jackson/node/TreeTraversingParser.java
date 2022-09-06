package com.amazon.org.codehaus.jackson.node;

import com.amazon.org.codehaus.jackson.Base64Variant;
import com.amazon.org.codehaus.jackson.JsonLocation;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.JsonParseException;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonStreamContext;
import com.amazon.org.codehaus.jackson.JsonToken;
import com.amazon.org.codehaus.jackson.ObjectCodec;
import com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase;
import com.amazon.org.codehaus.jackson.node.NodeCursor;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes13.dex */
public class TreeTraversingParser extends JsonParserMinimalBase {
    protected boolean _closed;
    protected JsonToken _nextToken;
    protected NodeCursor _nodeCursor;
    protected ObjectCodec _objectCodec;
    protected boolean _startContainer;

    /* renamed from: com.amazon.org.codehaus.jackson.node.TreeTraversingParser$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken = new int[JsonToken.values().length];

        static {
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.FIELD_NAME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$org$codehaus$jackson$JsonToken[JsonToken.VALUE_EMBEDDED_OBJECT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public TreeTraversingParser(JsonNode jsonNode) {
        this(jsonNode, null);
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase
    protected void _handleEOF() throws JsonParseException {
        _throwInternal();
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this._closed) {
            this._closed = true;
            this._nodeCursor = null;
            this._currToken = null;
        }
    }

    protected JsonNode currentNode() {
        NodeCursor nodeCursor;
        if (this._closed || (nodeCursor = this._nodeCursor) == null) {
            return null;
        }
        return nodeCursor.currentNode();
    }

    protected JsonNode currentNumericNode() throws JsonParseException {
        JsonNode currentNode = currentNode();
        if (currentNode == null || !currentNode.isNumber()) {
            JsonToken asToken = currentNode == null ? null : currentNode.asToken();
            throw _constructError("Current token (" + asToken + ") not numeric, can not use numeric value accessors");
        }
        return currentNode;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
        return currentNumericNode().getBigIntegerValue();
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        JsonNode currentNode = currentNode();
        if (currentNode != null) {
            byte[] binaryValue = currentNode.getBinaryValue();
            if (binaryValue != null) {
                return binaryValue;
            }
            if (!currentNode.isPojo()) {
                return null;
            }
            Object pojo = ((POJONode) currentNode).getPojo();
            if (!(pojo instanceof byte[])) {
                return null;
            }
            return (byte[]) pojo;
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonLocation getCurrentLocation() {
        return JsonLocation.NA;
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public String getCurrentName() {
        NodeCursor nodeCursor = this._nodeCursor;
        if (nodeCursor == null) {
            return null;
        }
        return nodeCursor.getCurrentName();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public BigDecimal getDecimalValue() throws IOException, JsonParseException {
        return currentNumericNode().getDecimalValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public double getDoubleValue() throws IOException, JsonParseException {
        return currentNumericNode().getDoubleValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public Object getEmbeddedObject() {
        JsonNode currentNode;
        if (this._closed || (currentNode = currentNode()) == null) {
            return null;
        }
        if (currentNode.isPojo()) {
            return ((POJONode) currentNode).getPojo();
        }
        if (!currentNode.isBinary()) {
            return null;
        }
        return ((BinaryNode) currentNode).getBinaryValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public float getFloatValue() throws IOException, JsonParseException {
        return (float) currentNumericNode().getDoubleValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public int getIntValue() throws IOException, JsonParseException {
        return currentNumericNode().getIntValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public long getLongValue() throws IOException, JsonParseException {
        return currentNumericNode().getLongValue();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonParser.NumberType getNumberType() throws IOException, JsonParseException {
        JsonNode currentNumericNode = currentNumericNode();
        if (currentNumericNode == null) {
            return null;
        }
        return currentNumericNode.getNumberType();
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public Number getNumberValue() throws IOException, JsonParseException {
        return currentNumericNode().getNumberValue();
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    /* renamed from: getParsingContext */
    public JsonStreamContext mo4092getParsingContext() {
        return this._nodeCursor;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public String getText() {
        if (this._closed) {
            return null;
        }
        switch (this._currToken.ordinal()) {
            case 5:
                return this._nodeCursor.getCurrentName();
            case 6:
                JsonNode currentNode = currentNode();
                if (currentNode != null && currentNode.isBinary()) {
                    return currentNode.asText();
                }
                break;
            case 7:
                return currentNode().getTextValue();
            case 8:
            case 9:
                return String.valueOf(currentNode().getNumberValue());
        }
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            return jsonToken.asString();
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public char[] getTextCharacters() throws IOException, JsonParseException {
        return getText().toCharArray();
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public int getTextLength() throws IOException, JsonParseException {
        return getText().length();
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public int getTextOffset() throws IOException, JsonParseException {
        return 0;
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public JsonLocation getTokenLocation() {
        return JsonLocation.NA;
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public boolean hasTextCharacters() {
        return false;
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public boolean isClosed() {
        return this._closed;
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public JsonToken nextToken() throws IOException, JsonParseException {
        JsonToken jsonToken = this._nextToken;
        if (jsonToken != null) {
            this._currToken = jsonToken;
            this._nextToken = null;
            return this._currToken;
        } else if (this._startContainer) {
            this._startContainer = false;
            if (!this._nodeCursor.currentHasChildren()) {
                this._currToken = this._currToken == JsonToken.START_OBJECT ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
                return this._currToken;
            }
            this._nodeCursor = this._nodeCursor.iterateChildren();
            this._currToken = this._nodeCursor.nextToken();
            JsonToken jsonToken2 = this._currToken;
            if (jsonToken2 == JsonToken.START_OBJECT || jsonToken2 == JsonToken.START_ARRAY) {
                this._startContainer = true;
            }
            return this._currToken;
        } else {
            NodeCursor nodeCursor = this._nodeCursor;
            if (nodeCursor == null) {
                this._closed = true;
                return null;
            }
            this._currToken = nodeCursor.nextToken();
            JsonToken jsonToken3 = this._currToken;
            if (jsonToken3 != null) {
                if (jsonToken3 == JsonToken.START_OBJECT || jsonToken3 == JsonToken.START_ARRAY) {
                    this._startContainer = true;
                }
                return this._currToken;
            }
            this._currToken = this._nodeCursor.endToken();
            this._nodeCursor = this._nodeCursor.mo4257getParent();
            return this._currToken;
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    @Override // com.amazon.org.codehaus.jackson.impl.JsonParserMinimalBase, com.amazon.org.codehaus.jackson.JsonParser
    public JsonParser skipChildren() throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.START_OBJECT) {
            this._startContainer = false;
            this._currToken = JsonToken.END_OBJECT;
        } else if (jsonToken == JsonToken.START_ARRAY) {
            this._startContainer = false;
            this._currToken = JsonToken.END_ARRAY;
        }
        return this;
    }

    public TreeTraversingParser(JsonNode jsonNode, ObjectCodec objectCodec) {
        super(0);
        this._objectCodec = objectCodec;
        if (jsonNode.isArray()) {
            this._nextToken = JsonToken.START_ARRAY;
            this._nodeCursor = new NodeCursor.Array(jsonNode, null);
        } else if (jsonNode.isObject()) {
            this._nextToken = JsonToken.START_OBJECT;
            this._nodeCursor = new NodeCursor.Object(jsonNode, null);
        } else {
            this._nodeCursor = new NodeCursor.RootValue(jsonNode, null);
        }
    }
}
