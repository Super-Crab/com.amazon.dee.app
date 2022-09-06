package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty1;
/* loaded from: classes11.dex */
public abstract class MutablePropertyReference1 extends MutablePropertyReference implements KMutableProperty1 {
    public MutablePropertyReference1() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.mutableProperty1(this);
    }

    @Override // kotlin.reflect.KProperty1
    @SinceKotlin(version = "1.1")
    public Object getDelegate(Object obj) {
        return ((KMutableProperty1) mo11339getReflected()).getDelegate(obj);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public Object mo12165invoke(Object obj) {
        return get(obj);
    }

    @SinceKotlin(version = "1.1")
    public MutablePropertyReference1(Object obj) {
        super(obj);
    }

    @Override // kotlin.reflect.KProperty, kotlin.reflect.KProperty0
    /* renamed from: getGetter  reason: collision with other method in class */
    public KProperty1.Getter mo11472getGetter() {
        return ((KMutableProperty1) mo11339getReflected()).mo11472getGetter();
    }

    @Override // kotlin.reflect.KMutableProperty, kotlin.reflect.KMutableProperty0
    /* renamed from: getSetter  reason: collision with other method in class */
    public KMutableProperty1.Setter mo11451getSetter() {
        return ((KMutableProperty1) mo11339getReflected()).mo11451getSetter();
    }
}
