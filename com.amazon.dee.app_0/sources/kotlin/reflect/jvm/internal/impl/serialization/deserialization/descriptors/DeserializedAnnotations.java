package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DeserializedAnnotations.kt */
/* loaded from: classes4.dex */
public class DeserializedAnnotations implements Annotations {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedAnnotations.class), "annotations", "getAnnotations()Ljava/util/List;"))};
    private final NotNullLazyValue annotations$delegate;

    public DeserializedAnnotations(@NotNull StorageManager storageManager, @NotNull Function0<? extends List<? extends AnnotationDescriptor>> compute) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(compute, "compute");
        this.annotations$delegate = storageManager.createLazyValue(compute);
    }

    private final List<AnnotationDescriptor> getAnnotations() {
        return (List) StorageKt.getValue(this.annotations$delegate, this, $$delegatedProperties[0]);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    @Nullable
    /* renamed from: findAnnotation */
    public AnnotationDescriptor mo11701findAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return Annotations.DefaultImpls.findAnnotation(this, fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean hasAnnotation(@NotNull FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        return Annotations.DefaultImpls.hasAnnotation(this, fqName);
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
    public boolean isEmpty() {
        return getAnnotations().isEmpty();
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<AnnotationDescriptor> iterator() {
        return getAnnotations().iterator();
    }
}
