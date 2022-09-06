package kotlinx.io.core;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* compiled from: CloseableJVM.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/lang/reflect/Method;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes4.dex */
final class CloseableJVMKt$AddSuppressedMethod$2 extends Lambda implements Function0<Method> {
    public static final CloseableJVMKt$AddSuppressedMethod$2 INSTANCE = new CloseableJVMKt$AddSuppressedMethod$2();

    CloseableJVMKt$AddSuppressedMethod$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    @Nullable
    /* renamed from: invoke  reason: collision with other method in class */
    public final Method mo12560invoke() {
        try {
            return Throwable.class.getMethod("addSuppressed", Throwable.class);
        } catch (Throwable unused) {
            return null;
        }
    }
}
