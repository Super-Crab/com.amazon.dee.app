package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LazyJavaAnnotationDescriptor.kt */
/* loaded from: classes3.dex */
public final class LazyJavaAnnotationDescriptor$type$2 extends Lambda implements Function0<SimpleType> {
    final /* synthetic */ LazyJavaAnnotationDescriptor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyJavaAnnotationDescriptor$type$2(LazyJavaAnnotationDescriptor lazyJavaAnnotationDescriptor) {
        super(0);
        this.this$0 = lazyJavaAnnotationDescriptor;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final SimpleType mo12560invoke() {
        JavaAnnotation javaAnnotation;
        LazyJavaResolverContext lazyJavaResolverContext;
        JavaAnnotation javaAnnotation2;
        LazyJavaResolverContext lazyJavaResolverContext2;
        FqName fqName = this.this$0.getFqName();
        if (fqName != null) {
            Intrinsics.checkExpressionValueIsNotNull(fqName, "fqName ?: return@createL…fqName: $javaAnnotation\")");
            JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
            lazyJavaResolverContext = this.this$0.c;
            ClassDescriptor mapJavaToKotlin$default = JavaToKotlinClassMap.mapJavaToKotlin$default(javaToKotlinClassMap, fqName, lazyJavaResolverContext.getModule().getBuiltIns(), null, 4, null);
            if (mapJavaToKotlin$default == null) {
                javaAnnotation2 = this.this$0.javaAnnotation;
                JavaClass mo11615resolve = javaAnnotation2.mo11615resolve();
                if (mo11615resolve != null) {
                    lazyJavaResolverContext2 = this.this$0.c;
                    mapJavaToKotlin$default = lazyJavaResolverContext2.getComponents().getModuleClassResolver().resolveClass(mo11615resolve);
                } else {
                    mapJavaToKotlin$default = null;
                }
            }
            if (mapJavaToKotlin$default == null) {
                mapJavaToKotlin$default = this.this$0.createTypeForMissingDependencies(fqName);
            }
            return mapJavaToKotlin$default.getDefaultType();
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No fqName: ");
        javaAnnotation = this.this$0.javaAnnotation;
        outline107.append(javaAnnotation);
        return ErrorUtils.createErrorType(outline107.toString());
    }
}
