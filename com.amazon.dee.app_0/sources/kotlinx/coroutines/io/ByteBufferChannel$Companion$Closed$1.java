package kotlinx.coroutines.io;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KMutableProperty1;
import kotlinx.coroutines.io.ByteBufferChannel;
import org.jetbrains.annotations.Nullable;
/* compiled from: ByteBufferChannel.kt */
@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class ByteBufferChannel$Companion$Closed$1 extends MutablePropertyReference1 {
    public static final KMutableProperty1 INSTANCE = new ByteBufferChannel$Companion$Closed$1();

    ByteBufferChannel$Companion$Closed$1() {
    }

    @Override // kotlin.reflect.KProperty1
    @Nullable
    public Object get(@Nullable Object obj) {
        return ((ByteBufferChannel) obj).closed;
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "closed";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ByteBufferChannel.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getClosed()Lkotlinx/coroutines/io/ByteBufferChannel$ClosedElement;";
    }

    @Override // kotlin.reflect.KMutableProperty1
    public void set(@Nullable Object obj, @Nullable Object obj2) {
        ((ByteBufferChannel) obj).closed = (ByteBufferChannel.ClosedElement) obj2;
    }
}
