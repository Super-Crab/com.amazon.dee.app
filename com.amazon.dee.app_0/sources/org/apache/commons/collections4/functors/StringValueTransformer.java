package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public final class StringValueTransformer<T> implements Transformer<T, String>, Serializable {
    private static final Transformer<Object, String> INSTANCE = new StringValueTransformer();
    private static final long serialVersionUID = 7511110693171758606L;

    private StringValueTransformer() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public static <T> Transformer<T, String> stringValueTransformer() {
        return (Transformer<T, String>) INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.Transformer
    /* renamed from: transform */
    public /* bridge */ /* synthetic */ String mo12738transform(Object obj) {
        return mo12738transform((StringValueTransformer<T>) obj);
    }

    @Override // org.apache.commons.collections4.Transformer
    /* renamed from: transform  reason: collision with other method in class */
    public String mo12738transform(T t) {
        return String.valueOf(t);
    }
}
