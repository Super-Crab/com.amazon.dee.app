package com.amazon.tarazed.core.types.annotations;

import com.amazon.alexa.routing.api.RouteParameter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationConstructorMarker;
import kotlinx.serialization.internal.ArrayListSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FreeformAnnotation.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016B)\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\nJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u0019\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/amazon/tarazed/core/types/annotations/FreeformAnnotation;", "", "seen1", "", RouteParameter.PATH, "", "Lcom/amazon/tarazed/core/types/annotations/AnnotationPoint;", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(ILjava/util/List;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(Ljava/util/List;)V", "getPath", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class FreeformAnnotation {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<AnnotationPoint> path;

    /* compiled from: FreeformAnnotation.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/types/annotations/FreeformAnnotation$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/types/annotations/FreeformAnnotation;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<FreeformAnnotation> serializer() {
            return FreeformAnnotation$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ FreeformAnnotation(int i, @Nullable List<AnnotationPoint> list, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.path = list;
            return;
        }
        throw new MissingFieldException(RouteParameter.PATH);
    }

    public FreeformAnnotation(@NotNull List<AnnotationPoint> path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        this.path = path;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FreeformAnnotation copy$default(FreeformAnnotation freeformAnnotation, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = freeformAnnotation.path;
        }
        return freeformAnnotation.copy(list);
    }

    @JvmStatic
    public static final void write$Self(@NotNull FreeformAnnotation self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        output.encodeSerializableElement(serialDesc, 0, new ArrayListSerializer(AnnotationPoint$$serializer.INSTANCE), self.path);
    }

    @NotNull
    public final List<AnnotationPoint> component1() {
        return this.path;
    }

    @NotNull
    public final FreeformAnnotation copy(@NotNull List<AnnotationPoint> path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return new FreeformAnnotation(path);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof FreeformAnnotation) && Intrinsics.areEqual(this.path, ((FreeformAnnotation) obj).path);
        }
        return true;
    }

    @NotNull
    public final List<AnnotationPoint> getPath() {
        return this.path;
    }

    public int hashCode() {
        List<AnnotationPoint> list = this.path;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("FreeformAnnotation(path="), this.path, ")");
    }
}
