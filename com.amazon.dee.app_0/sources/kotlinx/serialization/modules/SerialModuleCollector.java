package kotlinx.serialization.modules;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: SerialModuleCollector.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J.\u0010\u0002\u001a\u00020\u0003\"\b\b\u0000\u0010\u0004*\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00040\bH&JF\u0010\t\u001a\u00020\u0003\"\b\b\u0000\u0010\n*\u00020\u0001\"\b\b\u0001\u0010\u000b*\u0002H\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000b0\bH&¨\u0006\u000f"}, d2 = {"Lkotlinx/serialization/modules/SerialModuleCollector;", "", "contextual", "", ExifInterface.GPS_DIRECTION_TRUE, "kClass", "Lkotlin/reflect/KClass;", "serializer", "Lkotlinx/serialization/KSerializer;", "polymorphic", "Base", "Sub", "baseClass", "actualClass", "actualSerializer", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface SerialModuleCollector {
    <T> void contextual(@NotNull KClass<T> kClass, @NotNull KSerializer<T> kSerializer);

    <Base, Sub extends Base> void polymorphic(@NotNull KClass<Base> kClass, @NotNull KClass<Sub> kClass2, @NotNull KSerializer<Sub> kSerializer);
}
