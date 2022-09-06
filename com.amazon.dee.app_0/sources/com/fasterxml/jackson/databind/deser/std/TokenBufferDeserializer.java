package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
@JacksonStdImpl
/* loaded from: classes2.dex */
public class TokenBufferDeserializer extends StdScalarDeserializer<TokenBuffer> {
    private static final long serialVersionUID = 1;

    public TokenBufferDeserializer() {
        super(TokenBuffer.class);
    }

    protected TokenBuffer createBufferInstance(JsonParser jsonParser) {
        return new TokenBuffer(jsonParser);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public LogicalType logicalType() {
        return LogicalType.Untyped;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    /* renamed from: deserialize */
    public TokenBuffer mo7111deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return createBufferInstance(jsonParser).deserialize(jsonParser, deserializationContext);
    }
}
