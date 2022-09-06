package kotlinx.coroutines.io.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KMutableProperty1;
import org.jetbrains.annotations.Nullable;
/* compiled from: RingBufferCapacity.kt */
@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class RingBufferCapacity$Companion$AvailableForRead$1 extends MutablePropertyReference1 {
    public static final KMutableProperty1 INSTANCE = new RingBufferCapacity$Companion$AvailableForRead$1();

    RingBufferCapacity$Companion$AvailableForRead$1() {
    }

    @Override // kotlin.reflect.KProperty1
    @Nullable
    public Object get(@Nullable Object obj) {
        return Integer.valueOf(((RingBufferCapacity) obj).availableForRead);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "availableForRead";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(RingBufferCapacity.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getAvailableForRead()I";
    }

    @Override // kotlin.reflect.KMutableProperty1
    public void set(@Nullable Object obj, @Nullable Object obj2) {
        ((RingBufferCapacity) obj).availableForRead = ((Number) obj2).intValue();
    }
}
