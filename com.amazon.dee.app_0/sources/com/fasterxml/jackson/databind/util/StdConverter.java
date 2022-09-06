package com.fasterxml.jackson.databind.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
/* loaded from: classes2.dex */
public abstract class StdConverter<IN, OUT> implements Converter<IN, OUT> {
    protected JavaType _findConverterType(TypeFactory typeFactory) {
        JavaType findSuperType = typeFactory.constructType(StdConverter.class).findSuperType(Converter.class);
        if (findSuperType == null || findSuperType.containedTypeCount() < 2) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline38(StdConverter.class, GeneratedOutlineSupport1.outline107("Cannot find OUT type parameter for Converter of type ")));
        }
        return findSuperType;
    }

    @Override // com.fasterxml.jackson.databind.util.Converter
    public abstract OUT convert(IN in);

    @Override // com.fasterxml.jackson.databind.util.Converter
    public JavaType getInputType(TypeFactory typeFactory) {
        return _findConverterType(typeFactory).mo7254containedType(0);
    }

    @Override // com.fasterxml.jackson.databind.util.Converter
    public JavaType getOutputType(TypeFactory typeFactory) {
        return _findConverterType(typeFactory).mo7254containedType(1);
    }
}
