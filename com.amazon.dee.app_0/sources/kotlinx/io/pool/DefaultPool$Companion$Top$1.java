package kotlinx.io.pool;

import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KMutableProperty1;
import org.jetbrains.annotations.Nullable;
/* compiled from: DefaultPool.kt */
@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class DefaultPool$Companion$Top$1 extends MutablePropertyReference1 {
    public static final KMutableProperty1 INSTANCE = new DefaultPool$Companion$Top$1();

    DefaultPool$Companion$Top$1() {
    }

    @Override // kotlin.reflect.KProperty1
    @Nullable
    public Object get(@Nullable Object obj) {
        long j;
        j = ((DefaultPool) obj).top;
        return Long.valueOf(j);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return ViewProps.TOP;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(DefaultPool.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getTop()J";
    }

    @Override // kotlin.reflect.KMutableProperty1
    public void set(@Nullable Object obj, @Nullable Object obj2) {
        ((DefaultPool) obj).top = ((Number) obj2).longValue();
    }
}
