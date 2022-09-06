package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import org.jetbrains.annotations.NotNull;
/* compiled from: RawType.kt */
/* loaded from: classes3.dex */
final class RawTypeImpl$render$2 extends Lambda implements Function1<KotlinType, List<? extends String>> {
    final /* synthetic */ DescriptorRenderer $renderer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RawTypeImpl$render$2(DescriptorRenderer descriptorRenderer) {
        super(1);
        this.$renderer = descriptorRenderer;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final List<String> mo12165invoke(@NotNull KotlinType type) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(type, "type");
        List<TypeProjection> arguments = type.getArguments();
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arguments, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (TypeProjection typeProjection : arguments) {
            arrayList.add(this.$renderer.renderTypeProjection(typeProjection));
        }
        return arrayList;
    }
}
