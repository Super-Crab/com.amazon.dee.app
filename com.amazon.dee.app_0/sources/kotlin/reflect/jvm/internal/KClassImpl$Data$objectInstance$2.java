package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KClassImpl;
import kotlin.reflect.jvm.internal.impl.builtins.CompanionObjectMapping;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KClassImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", ExifInterface.GPS_DIRECTION_TRUE, "", "invoke", "()Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class KClassImpl$Data$objectInstance$2 extends Lambda implements Function0<T> {
    final /* synthetic */ KClassImpl.Data this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KClassImpl$Data$objectInstance$2(KClassImpl.Data data) {
        super(0);
        this.this$0 = data;
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [T, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function0
    @Nullable
    /* renamed from: invoke */
    public final T mo12560invoke() {
        Field declaredField;
        ClassDescriptor descriptor = this.this$0.getDescriptor();
        if (descriptor.getKind() != ClassKind.OBJECT) {
            return null;
        }
        if (descriptor.isCompanionObject() && !CompanionObjectMapping.INSTANCE.isMappedIntrinsicCompanionObject(descriptor)) {
            declaredField = KClassImpl.this.getJClass().getEnclosingClass().getDeclaredField(descriptor.getName().asString());
        } else {
            declaredField = KClassImpl.this.getJClass().getDeclaredField("INSTANCE");
        }
        ?? r0 = declaredField.get(null);
        if (r0 == 0) {
            throw new TypeCastException("null cannot be cast to non-null type T");
        }
        return r0;
    }
}
