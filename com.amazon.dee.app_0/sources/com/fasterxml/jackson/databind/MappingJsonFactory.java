package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import java.io.IOException;
/* loaded from: classes2.dex */
public class MappingJsonFactory extends JsonFactory {
    private static final long serialVersionUID = -1;

    public MappingJsonFactory() {
        this(null);
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public JsonFactory copy() {
        _checkInvalidCopy(MappingJsonFactory.class);
        return new MappingJsonFactory(this, null);
    }

    @Override // com.fasterxml.jackson.core.JsonFactory, com.fasterxml.jackson.core.TokenStreamFactory
    public String getFormatName() {
        return "JSON";
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public MatchStrength hasFormat(InputAccessor inputAccessor) throws IOException {
        if (MappingJsonFactory.class == MappingJsonFactory.class) {
            return hasJSONFormat(inputAccessor);
        }
        return null;
    }

    public MappingJsonFactory(ObjectMapper objectMapper) {
        super(objectMapper);
        if (objectMapper == null) {
            setCodec(new ObjectMapper(this));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    /* renamed from: getCodec  reason: collision with other method in class */
    public final ObjectMapper mo7031getCodec() {
        return (ObjectMapper) this._objectCodec;
    }

    public MappingJsonFactory(JsonFactory jsonFactory, ObjectMapper objectMapper) {
        super(jsonFactory, objectMapper);
        if (objectMapper == null) {
            setCodec(new ObjectMapper(this));
        }
    }
}
