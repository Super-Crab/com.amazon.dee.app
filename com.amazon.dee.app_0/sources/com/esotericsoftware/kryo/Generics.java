package com.esotericsoftware.kryo;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class Generics {
    private Generics parentScope;
    private Map<String, Class> typeVar2class;

    public Generics() {
        this.typeVar2class = new HashMap();
        this.parentScope = null;
    }

    public void add(String str, Class cls) {
        this.typeVar2class.put(str, cls);
    }

    public Class getConcreteClass(String str) {
        Generics generics;
        Class cls = this.typeVar2class.get(str);
        return (cls != null || (generics = this.parentScope) == null) ? cls : generics.getConcreteClass(str);
    }

    public Map<String, Class> getMappings() {
        return this.typeVar2class;
    }

    public Generics getParentScope() {
        return this.parentScope;
    }

    public void resetParentScope() {
        this.parentScope = null;
    }

    public void setParentScope(Generics generics) {
        if (this.parentScope == null) {
            this.parentScope = generics;
            return;
        }
        throw new IllegalStateException("Parent scope can be set just once");
    }

    public String toString() {
        return this.typeVar2class.toString();
    }

    public Generics(Map<String, Class> map) {
        this.typeVar2class = new HashMap(map);
        this.parentScope = null;
    }

    public Generics(Generics generics) {
        this.typeVar2class = new HashMap();
        this.parentScope = generics;
    }
}
