package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty2;
/* loaded from: classes11.dex */
public abstract class MutablePropertyReference2 extends MutablePropertyReference implements KMutableProperty2 {
    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.mutableProperty2(this);
    }

    @Override // kotlin.reflect.KProperty2
    @SinceKotlin(version = "1.1")
    public Object getDelegate(Object obj, Object obj2) {
        return ((KMutableProperty2) mo11339getReflected()).getDelegate(obj, obj2);
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public Object mo12248invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    @Override // kotlin.reflect.KProperty, kotlin.reflect.KProperty0
    /* renamed from: getGetter  reason: collision with other method in class */
    public KProperty2.Getter mo11472getGetter() {
        return ((KMutableProperty2) mo11339getReflected()).mo11472getGetter();
    }

    @Override // kotlin.reflect.KMutableProperty, kotlin.reflect.KMutableProperty0
    /* renamed from: getSetter  reason: collision with other method in class */
    public KMutableProperty2.Setter mo11451getSetter() {
        return ((KMutableProperty2) mo11339getReflected()).mo11451getSetter();
    }
}
