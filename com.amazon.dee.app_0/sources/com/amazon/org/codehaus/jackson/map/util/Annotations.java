package com.amazon.org.codehaus.jackson.map.util;

import java.lang.annotation.Annotation;
/* loaded from: classes13.dex */
public interface Annotations {
    <A extends Annotation> A get(Class<A> cls);

    int size();
}
