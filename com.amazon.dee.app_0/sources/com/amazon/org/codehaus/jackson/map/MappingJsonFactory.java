package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.JsonFactory;
import com.amazon.org.codehaus.jackson.format.InputAccessor;
import com.amazon.org.codehaus.jackson.format.MatchStrength;
import java.io.IOException;
/* loaded from: classes13.dex */
public class MappingJsonFactory extends JsonFactory {
    public MappingJsonFactory() {
        this(null);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonFactory
    public String getFormatName() {
        return "JSON";
    }

    @Override // com.amazon.org.codehaus.jackson.JsonFactory
    public MatchStrength hasFormat(InputAccessor inputAccessor) throws IOException {
        return hasJSONFormat(inputAccessor);
    }

    public MappingJsonFactory(ObjectMapper objectMapper) {
        super(objectMapper);
        if (objectMapper == null) {
            setCodec(new ObjectMapper(this));
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonFactory
    /* renamed from: getCodec  reason: collision with other method in class */
    public final ObjectMapper mo4112getCodec() {
        return (ObjectMapper) this._objectCodec;
    }
}
