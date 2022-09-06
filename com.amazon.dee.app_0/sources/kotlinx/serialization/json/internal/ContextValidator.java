package kotlinx.serialization.json.internal;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.modules.SerialModuleCollector;
import org.jetbrains.annotations.NotNull;
/* compiled from: ContextValidator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J.\u0010\u0005\u001a\u00020\u0006\"\b\b\u0000\u0010\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00070\fH\u0016JF\u0010\r\u001a\u00020\u0006\"\b\b\u0000\u0010\u000e*\u00020\b\"\b\b\u0001\u0010\u000f*\u0002H\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000f0\n2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlinx/serialization/json/internal/ContextValidator;", "Lkotlinx/serialization/modules/SerialModuleCollector;", "discriminator", "", "(Ljava/lang/String;)V", "contextual", "", ExifInterface.GPS_DIRECTION_TRUE, "", "kClass", "Lkotlin/reflect/KClass;", "serializer", "Lkotlinx/serialization/KSerializer;", "polymorphic", "Base", "Sub", "baseClass", "actualClass", "actualSerializer", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ContextValidator implements SerialModuleCollector {
    private final String discriminator;

    public ContextValidator(@NotNull String discriminator) {
        Intrinsics.checkParameterIsNotNull(discriminator, "discriminator");
        this.discriminator = discriminator;
    }

    @Override // kotlinx.serialization.modules.SerialModuleCollector
    public <T> void contextual(@NotNull KClass<T> kClass, @NotNull KSerializer<T> serializer) {
        Intrinsics.checkParameterIsNotNull(kClass, "kClass");
        Intrinsics.checkParameterIsNotNull(serializer, "serializer");
    }

    @Override // kotlinx.serialization.modules.SerialModuleCollector
    public <Base, Sub extends Base> void polymorphic(@NotNull KClass<Base> baseClass, @NotNull KClass<Sub> actualClass, @NotNull KSerializer<Sub> actualSerializer) {
        Intrinsics.checkParameterIsNotNull(baseClass, "baseClass");
        Intrinsics.checkParameterIsNotNull(actualClass, "actualClass");
        Intrinsics.checkParameterIsNotNull(actualSerializer, "actualSerializer");
        SerialDescriptor descriptor = actualSerializer.getDescriptor();
        int elementsCount = descriptor.getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            String elementName = descriptor.getElementName(i);
            if (Intrinsics.areEqual(elementName, this.discriminator)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Polymorphic serializer for ");
                sb.append(actualClass);
                sb.append(" has property '");
                sb.append(elementName);
                sb.append("' that conflicts ");
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline92(sb, "with JSON class discriminator. You can either change class discriminator in JsonConfiguration, ", "rename property with @SerialName annotation ", "or fall back to array polymorphism"));
            }
        }
    }
}
