package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KProperty0;
/* loaded from: classes11.dex */
public abstract class MutablePropertyReference0 extends MutablePropertyReference implements KMutableProperty0 {
    public MutablePropertyReference0() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected KCallable computeReflected() {
        return Reflection.mutableProperty0(this);
    }

    @Override // kotlin.reflect.KProperty0
    @SinceKotlin(version = "1.1")
    public Object getDelegate() {
        return ((KMutableProperty0) mo11339getReflected()).getDelegate();
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public Object mo12560invoke() {
        return get();
    }

    @SinceKotlin(version = "1.1")
    public MutablePropertyReference0(Object obj) {
        super(obj);
    }

    @Override // kotlin.reflect.KProperty, kotlin.reflect.KProperty0
    /* renamed from: getGetter  reason: collision with other method in class */
    public KProperty0.Getter mo11472getGetter() {
        return ((KMutableProperty0) mo11339getReflected()).mo11472getGetter();
    }

    @Override // kotlin.reflect.KMutableProperty, kotlin.reflect.KMutableProperty0
    /* renamed from: getSetter  reason: collision with other method in class */
    public KMutableProperty0.Setter mo11451getSetter() {
        return ((KMutableProperty0) mo11339getReflected()).mo11451getSetter();
    }
}
