package kotlinx.serialization;

import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialDescriptor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u001b\u001a\u00020\bH&J\u0010\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\bH&J\u0010\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\bH&J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0017J\u0010\u0010 \u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\bH&R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u00138VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0018\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017¨\u0006!"}, d2 = {"Lkotlinx/serialization/SerialDescriptor;", "", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "elementsCount", "", "getElementsCount", "()I", "isNullable", "", "()Z", ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME, "Lkotlinx/serialization/SerialKind;", "getKind", "()Lkotlinx/serialization/SerialKind;", "name", "", "name$annotations", "()V", "getName", "()Ljava/lang/String;", "serialName", "getSerialName", "getElementAnnotations", "index", "getElementDescriptor", "getElementIndex", "getElementName", "getEntityAnnotations", "isElementOptional", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface SerialDescriptor {

    /* compiled from: SerialDescriptor.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        @NotNull
        public static List<Annotation> getAnnotations(SerialDescriptor serialDescriptor) {
            return CollectionsKt.emptyList();
        }

        @Deprecated(message = "Deprecated in the favour of 'annotations' property", replaceWith = @ReplaceWith(expression = "annotations", imports = {}))
        @NotNull
        public static List<Annotation> getEntityAnnotations(SerialDescriptor serialDescriptor) {
            return CollectionsKt.emptyList();
        }

        @NotNull
        public static String getName(SerialDescriptor serialDescriptor) {
            return serialDescriptor.getSerialName();
        }

        public static boolean isNullable(SerialDescriptor serialDescriptor) {
            return false;
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "name property deprecated in the favour of serialName", replaceWith = @ReplaceWith(expression = "serialName", imports = {}))
        public static /* synthetic */ void name$annotations() {
        }
    }

    @NotNull
    List<Annotation> getAnnotations();

    @NotNull
    List<Annotation> getElementAnnotations(int i);

    @NotNull
    SerialDescriptor getElementDescriptor(int i);

    int getElementIndex(@NotNull String str);

    @NotNull
    String getElementName(int i);

    int getElementsCount();

    @Deprecated(message = "Deprecated in the favour of 'annotations' property", replaceWith = @ReplaceWith(expression = "annotations", imports = {}))
    @NotNull
    List<Annotation> getEntityAnnotations();

    @NotNull
    /* renamed from: getKind */
    SerialKind mo12397getKind();

    @NotNull
    String getName();

    @NotNull
    String getSerialName();

    boolean isElementOptional(int i);

    boolean isNullable();
}
