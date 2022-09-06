package com.amazon.org.codehaus.jackson.map.introspect;

import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import java.lang.reflect.Member;
/* loaded from: classes13.dex */
public abstract class AnnotatedMember extends Annotated {
    protected final AnnotationMap _annotations;

    /* JADX INFO: Access modifiers changed from: protected */
    public AnnotatedMember(AnnotationMap annotationMap) {
        this._annotations = annotationMap;
    }

    public final void fixAccess() {
        ClassUtil.checkAndFixAccess(getMember());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.org.codehaus.jackson.map.introspect.Annotated
    public AnnotationMap getAllAnnotations() {
        return this._annotations;
    }

    public abstract Class<?> getDeclaringClass();

    public abstract Member getMember();

    public abstract void setValue(Object obj, Object obj2) throws UnsupportedOperationException, IllegalArgumentException;
}
