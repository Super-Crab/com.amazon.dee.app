package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KClassImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "Ljava/lang/reflect/Type;", ExifInterface.GPS_DIRECTION_TRUE, "", "invoke", "kotlin/reflect/jvm/internal/KClassImpl$Data$supertypes$2$1$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class KClassImpl$Data$supertypes$2$$special$$inlined$mapTo$lambda$1 extends Lambda implements Function0<Type> {
    final /* synthetic */ KotlinType $kotlinType;
    final /* synthetic */ KClassImpl$Data$supertypes$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KClassImpl$Data$supertypes$2$$special$$inlined$mapTo$lambda$1(KotlinType kotlinType, KClassImpl$Data$supertypes$2 kClassImpl$Data$supertypes$2) {
        super(0);
        this.$kotlinType = kotlinType;
        this.this$0 = kClassImpl$Data$supertypes$2;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Type mo12560invoke() {
        int indexOf;
        ClassifierDescriptor mo12085getDeclarationDescriptor = this.$kotlinType.mo12131getConstructor().mo12085getDeclarationDescriptor();
        if (mo12085getDeclarationDescriptor instanceof ClassDescriptor) {
            Class<?> javaClass = UtilKt.toJavaClass((ClassDescriptor) mo12085getDeclarationDescriptor);
            if (javaClass != null) {
                if (Intrinsics.areEqual(KClassImpl.this.getJClass().getSuperclass(), javaClass)) {
                    Type genericSuperclass = KClassImpl.this.getJClass().getGenericSuperclass();
                    Intrinsics.checkExpressionValueIsNotNull(genericSuperclass, "jClass.genericSuperclass");
                    return genericSuperclass;
                }
                Class<?>[] interfaces = KClassImpl.this.getJClass().getInterfaces();
                Intrinsics.checkExpressionValueIsNotNull(interfaces, "jClass.interfaces");
                indexOf = ArraysKt___ArraysKt.indexOf(interfaces, javaClass);
                if (indexOf >= 0) {
                    Type type = KClassImpl.this.getJClass().getGenericInterfaces()[indexOf];
                    Intrinsics.checkExpressionValueIsNotNull(type, "jClass.genericInterfaces[index]");
                    return type;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No superclass of ");
                outline107.append(this.this$0.this$0);
                outline107.append(" in Java reflection for ");
                outline107.append(mo12085getDeclarationDescriptor);
                throw new KotlinReflectionInternalError(outline107.toString());
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unsupported superclass of ");
            outline1072.append(this.this$0.this$0);
            outline1072.append(RealTimeTextConstants.COLON_SPACE);
            outline1072.append(mo12085getDeclarationDescriptor);
            throw new KotlinReflectionInternalError(outline1072.toString());
        }
        throw new KotlinReflectionInternalError("Supertype not a class: " + mo12085getDeclarationDescriptor);
    }
}
