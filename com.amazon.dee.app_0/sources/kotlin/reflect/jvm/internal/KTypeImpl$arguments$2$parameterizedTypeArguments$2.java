package kotlin.reflect.jvm.internal;

import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KTypeImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Ljava/lang/reflect/Type;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class KTypeImpl$arguments$2$parameterizedTypeArguments$2 extends Lambda implements Function0<List<? extends Type>> {
    final /* synthetic */ KTypeImpl$arguments$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KTypeImpl$arguments$2$parameterizedTypeArguments$2(KTypeImpl$arguments$2 kTypeImpl$arguments$2) {
        super(0);
        this.this$0 = kTypeImpl$arguments$2;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final List<? extends Type> mo12560invoke() {
        return ReflectClassUtilKt.getParameterizedTypeArguments(this.this$0.this$0.getJavaType$kotlin_reflection());
    }
}
