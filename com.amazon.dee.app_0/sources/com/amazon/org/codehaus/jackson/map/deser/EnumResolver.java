package com.amazon.org.codehaus.jackson.map.deser;

import java.lang.Enum;
import java.util.HashMap;
@Deprecated
/* loaded from: classes13.dex */
public final class EnumResolver<T extends Enum<T>> extends com.amazon.org.codehaus.jackson.map.util.EnumResolver<T> {
    private EnumResolver(Class<T> cls, T[] tArr, HashMap<String, T> hashMap) {
        super(cls, tArr, hashMap);
    }
}
