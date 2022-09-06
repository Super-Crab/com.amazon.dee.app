package kotlin.jvm.internal;

import kotlin.reflect.KDeclarationContainer;
/* loaded from: classes11.dex */
public class MutablePropertyReference0Impl extends MutablePropertyReference0 {
    private final String name;
    private final KDeclarationContainer owner;
    private final String signature;

    public MutablePropertyReference0Impl(KDeclarationContainer kDeclarationContainer, String str, String str2) {
        this.owner = kDeclarationContainer;
        this.name = str;
        this.signature = str2;
    }

    @Override // kotlin.reflect.KProperty0
    public Object get() {
        return mo11472getGetter().call(new Object[0]);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return this.name;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return this.owner;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return this.signature;
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(Object obj) {
        mo11451getSetter().call(obj);
    }
}
