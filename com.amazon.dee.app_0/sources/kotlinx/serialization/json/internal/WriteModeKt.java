package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.enrollment.dialogs.DialogConstants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.PolymorphicKind;
import kotlinx.serialization.PrimitiveKind;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialKind;
import kotlinx.serialization.StructureKind;
import kotlinx.serialization.UnionKind;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonExceptionsKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: WriteMode.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aP\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001\"\b\b\u0001\u0010\u0002*\u0002H\u0001\"\b\b\u0002\u0010\u0003*\u0002H\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00030\bH\u0080\b¢\u0006\u0002\u0010\n\u001a\u0014\u0010\u000b\u001a\u00020\f*\u00020\u00042\u0006\u0010\r\u001a\u00020\u0006H\u0000¨\u0006\u000e"}, d2 = {"selectMapMode", ExifInterface.GPS_DIRECTION_TRUE, "R1", "R2", "Lkotlinx/serialization/json/Json;", "mapDescriptor", "Lkotlinx/serialization/SerialDescriptor;", "ifMap", "Lkotlin/Function0;", "ifList", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/SerialDescriptor;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "switchMode", "Lkotlinx/serialization/json/internal/WriteMode;", DialogConstants.DESC, "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class WriteModeKt {
    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v5, types: [T, java.lang.Object] */
    public static final <T, R1 extends T, R2 extends T> T selectMapMode(@NotNull Json selectMapMode, @NotNull SerialDescriptor mapDescriptor, @NotNull Function0<? extends R1> ifMap, @NotNull Function0<? extends R2> ifList) {
        Intrinsics.checkParameterIsNotNull(selectMapMode, "$this$selectMapMode");
        Intrinsics.checkParameterIsNotNull(mapDescriptor, "mapDescriptor");
        Intrinsics.checkParameterIsNotNull(ifMap, "ifMap");
        Intrinsics.checkParameterIsNotNull(ifList, "ifList");
        SerialDescriptor elementDescriptor = mapDescriptor.getElementDescriptor(0);
        SerialKind mo12397getKind = elementDescriptor.mo12397getKind();
        if (!(mo12397getKind instanceof PrimitiveKind) && !Intrinsics.areEqual(mo12397getKind, UnionKind.ENUM_KIND.INSTANCE)) {
            if (selectMapMode.configuration.getAllowStructuredMapKeys$kotlinx_serialization_runtime()) {
                return ifList.mo12560invoke();
            }
            throw JsonExceptionsKt.InvalidKeyKindException(elementDescriptor);
        }
        return ifMap.mo12560invoke();
    }

    @NotNull
    public static final WriteMode switchMode(@NotNull Json switchMode, @NotNull SerialDescriptor desc) {
        Intrinsics.checkParameterIsNotNull(switchMode, "$this$switchMode");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        SerialKind mo12397getKind = desc.mo12397getKind();
        if (mo12397getKind instanceof PolymorphicKind) {
            return WriteMode.POLY_OBJ;
        }
        if (Intrinsics.areEqual(mo12397getKind, StructureKind.LIST.INSTANCE)) {
            return WriteMode.LIST;
        }
        if (Intrinsics.areEqual(mo12397getKind, StructureKind.MAP.INSTANCE)) {
            SerialDescriptor elementDescriptor = desc.getElementDescriptor(0);
            SerialKind mo12397getKind2 = elementDescriptor.mo12397getKind();
            if (!(mo12397getKind2 instanceof PrimitiveKind) && !Intrinsics.areEqual(mo12397getKind2, UnionKind.ENUM_KIND.INSTANCE)) {
                if (switchMode.configuration.getAllowStructuredMapKeys$kotlinx_serialization_runtime()) {
                    return WriteMode.LIST;
                }
                throw JsonExceptionsKt.InvalidKeyKindException(elementDescriptor);
            }
            return WriteMode.MAP;
        }
        return WriteMode.OBJ;
    }
}
