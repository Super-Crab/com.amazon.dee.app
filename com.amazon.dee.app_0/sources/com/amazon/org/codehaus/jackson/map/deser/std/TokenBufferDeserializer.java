package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.DeserializationContext;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.util.TokenBuffer;
import java.io.IOException;
@JacksonStdImpl
/* loaded from: classes13.dex */
public class TokenBufferDeserializer extends StdScalarDeserializer<TokenBuffer> {
    public TokenBufferDeserializer() {
        super(TokenBuffer.class);
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonDeserializer
    /* renamed from: deserialize */
    public TokenBuffer mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
        tokenBuffer.copyCurrentStructure(jsonParser);
        return tokenBuffer;
    }
}
