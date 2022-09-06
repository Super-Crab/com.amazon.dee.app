package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import com.amazon.alexa.voice.tta.metrics.SimbaEventProcessor;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Annotations.kt */
/* loaded from: classes2.dex */
public interface Annotations extends Iterable<AnnotationDescriptor>, KMappedMarker {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: Annotations.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        @NotNull
        private static final Annotations EMPTY = new Annotations() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations$Companion$EMPTY$1
            @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
            @Nullable
            /* renamed from: findAnnotation */
            public Void mo11701findAnnotation(@NotNull FqName fqName) {
                Intrinsics.checkParameterIsNotNull(fqName, "fqName");
                return null;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
            /* renamed from: findAnnotation  reason: collision with other method in class */
            public /* bridge */ /* synthetic */ AnnotationDescriptor mo11701findAnnotation(FqName fqName) {
                return (AnnotationDescriptor) mo11701findAnnotation(fqName);
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
            public boolean hasAnnotation(@NotNull FqName fqName) {
                Intrinsics.checkParameterIsNotNull(fqName, "fqName");
                return Annotations.DefaultImpls.hasAnnotation(this, fqName);
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations
            public boolean isEmpty() {
                return true;
            }

            @Override // java.lang.Iterable
            @NotNull
            public Iterator<AnnotationDescriptor> iterator() {
                List emptyList;
                emptyList = CollectionsKt__CollectionsKt.emptyList();
                return emptyList.iterator();
            }

            @NotNull
            public String toString() {
                return SimbaEventProcessor.SOURCE_EMPTY;
            }
        };

        private Companion() {
        }

        @NotNull
        public final Annotations create(@NotNull List<? extends AnnotationDescriptor> annotations) {
            Intrinsics.checkParameterIsNotNull(annotations, "annotations");
            return annotations.isEmpty() ? EMPTY : new AnnotationsImpl(annotations);
        }

        @NotNull
        public final Annotations getEMPTY() {
            return EMPTY;
        }
    }

    /* compiled from: Annotations.kt */
    /* loaded from: classes2.dex */
    public static final class DefaultImpls {
        @Nullable
        public static AnnotationDescriptor findAnnotation(Annotations annotations, @NotNull FqName fqName) {
            AnnotationDescriptor annotationDescriptor;
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            Iterator<AnnotationDescriptor> it2 = annotations.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    annotationDescriptor = null;
                    break;
                }
                annotationDescriptor = it2.next();
                if (Intrinsics.areEqual(annotationDescriptor.getFqName(), fqName)) {
                    break;
                }
            }
            return annotationDescriptor;
        }

        public static boolean hasAnnotation(Annotations annotations, @NotNull FqName fqName) {
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            return annotations.mo11701findAnnotation(fqName) != null;
        }
    }

    @Nullable
    /* renamed from: findAnnotation */
    AnnotationDescriptor mo11701findAnnotation(@NotNull FqName fqName);

    boolean hasAnnotation(@NotNull FqName fqName);

    boolean isEmpty();
}
