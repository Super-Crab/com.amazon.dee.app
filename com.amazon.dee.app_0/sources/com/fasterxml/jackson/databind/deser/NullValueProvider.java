package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.util.AccessPattern;
/* loaded from: classes2.dex */
public interface NullValueProvider {
    AccessPattern getNullAccessPattern();

    /* renamed from: getNullValue */
    Object mo7075getNullValue(DeserializationContext deserializationContext) throws JsonMappingException;
}
