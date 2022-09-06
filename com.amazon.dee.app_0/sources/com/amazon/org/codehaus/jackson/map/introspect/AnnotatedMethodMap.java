package com.amazon.org.codehaus.jackson.map.introspect;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
/* loaded from: classes13.dex */
public final class AnnotatedMethodMap implements Iterable<AnnotatedMethod> {
    protected LinkedHashMap<MemberKey, AnnotatedMethod> _methods;

    public void add(AnnotatedMethod annotatedMethod) {
        if (this._methods == null) {
            this._methods = new LinkedHashMap<>();
        }
        this._methods.put(new MemberKey(annotatedMethod.mo4213getAnnotated()), annotatedMethod);
    }

    public AnnotatedMethod find(String str, Class<?>[] clsArr) {
        LinkedHashMap<MemberKey, AnnotatedMethod> linkedHashMap = this._methods;
        if (linkedHashMap == null) {
            return null;
        }
        return linkedHashMap.get(new MemberKey(str, clsArr));
    }

    public boolean isEmpty() {
        LinkedHashMap<MemberKey, AnnotatedMethod> linkedHashMap = this._methods;
        return linkedHashMap == null || linkedHashMap.size() == 0;
    }

    @Override // java.lang.Iterable
    public Iterator<AnnotatedMethod> iterator() {
        LinkedHashMap<MemberKey, AnnotatedMethod> linkedHashMap = this._methods;
        if (linkedHashMap != null) {
            return linkedHashMap.values().iterator();
        }
        return Collections.emptyList().iterator();
    }

    public AnnotatedMethod remove(AnnotatedMethod annotatedMethod) {
        return remove(annotatedMethod.mo4213getAnnotated());
    }

    public int size() {
        LinkedHashMap<MemberKey, AnnotatedMethod> linkedHashMap = this._methods;
        if (linkedHashMap == null) {
            return 0;
        }
        return linkedHashMap.size();
    }

    public AnnotatedMethod remove(Method method) {
        LinkedHashMap<MemberKey, AnnotatedMethod> linkedHashMap = this._methods;
        if (linkedHashMap != null) {
            return linkedHashMap.remove(new MemberKey(method));
        }
        return null;
    }

    public AnnotatedMethod find(Method method) {
        LinkedHashMap<MemberKey, AnnotatedMethod> linkedHashMap = this._methods;
        if (linkedHashMap == null) {
            return null;
        }
        return linkedHashMap.get(new MemberKey(method));
    }
}
