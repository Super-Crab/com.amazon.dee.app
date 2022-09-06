package kotlinx.serialization.json.internal;

import com.facebook.react.uimanager.ViewProps;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.PolymorphicKind;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.internal.UtilKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonExceptionsKt;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TreeJsonInput.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0012\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lkotlinx/serialization/json/internal/JsonTreeInput;", "Lkotlinx/serialization/json/internal/AbstractJsonTreeInput;", "json", "Lkotlinx/serialization/json/Json;", "value", "Lkotlinx/serialization/json/JsonObject;", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonObject;)V", ViewProps.POSITION, "", "getValue", "()Lkotlinx/serialization/json/JsonObject;", "currentElement", "Lkotlinx/serialization/json/JsonElement;", "tag", "", "decodeElementIndex", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "endStructure", "", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public class JsonTreeInput extends AbstractJsonTreeInput {
    private int position;
    @NotNull
    private final JsonObject value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonTreeInput(@NotNull Json json, @NotNull JsonObject value) {
        super(json, value, null);
        Intrinsics.checkParameterIsNotNull(json, "json");
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.value = value;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeInput
    @NotNull
    protected JsonElement currentElement(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        return (JsonElement) MapsKt.getValue(mo12417getValue(), tag);
    }

    @Override // kotlinx.serialization.CompositeDecoder
    public int decodeElementIndex(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        while (this.position < descriptor.getElementsCount()) {
            int i = this.position;
            this.position = i + 1;
            if (mo12417getValue().contains(getTag(descriptor, i))) {
                return this.position - 1;
            }
        }
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeInput, kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.CompositeDecoder
    public void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        if (this.configuration.getIgnoreUnknownKeys$kotlinx_serialization_runtime() || (descriptor.mo12397getKind() instanceof PolymorphicKind)) {
            return;
        }
        Set<String> cachedSerialNames = UtilKt.cachedSerialNames(descriptor);
        for (String str : mo12417getValue().keySet()) {
            if (!cachedSerialNames.contains(str)) {
                throw JsonExceptionsKt.UnknownKeyException(str, mo12417getValue().toString());
            }
        }
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeInput
    @NotNull
    /* renamed from: getValue  reason: collision with other method in class */
    public JsonObject mo12417getValue() {
        return this.value;
    }
}
