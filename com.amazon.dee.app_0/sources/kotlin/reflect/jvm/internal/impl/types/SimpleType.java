package kotlin.reflect.jvm.internal.impl.types;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: KotlinType.kt */
/* loaded from: classes4.dex */
public abstract class SimpleType extends UnwrappedType implements SimpleTypeMarker, TypeArgumentListMarker {
    public SimpleType() {
        super(null);
    }

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: makeNullableAsSpecified */
    public abstract SimpleType mo12132makeNullableAsSpecified(boolean z);

    @Override // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    @NotNull
    /* renamed from: replaceAnnotations */
    public abstract SimpleType mo12134replaceAnnotations(@NotNull Annotations annotations);

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<AnnotationDescriptor> it2 = mo12070getAnnotations().iterator();
        while (it2.hasNext()) {
            StringsKt.append(sb, "[", DescriptorRenderer.renderAnnotation$default(DescriptorRenderer.DEBUG_TEXT, it2.next(), null, 2, null), "] ");
        }
        sb.append(mo12131getConstructor());
        if (!getArguments().isEmpty()) {
            CollectionsKt.joinTo$default(getArguments(), sb, ", ", Config.Compare.LESS_THAN, Config.Compare.GREATER_THAN, 0, null, null, 112, null);
        }
        if (isMarkedNullable()) {
            sb.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
