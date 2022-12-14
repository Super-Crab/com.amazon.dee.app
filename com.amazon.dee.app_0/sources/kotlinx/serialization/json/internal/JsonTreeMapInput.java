package kotlinx.serialization.json.internal;

import com.amazon.alexa.enrollment.dialogs.DialogConstants;
import com.drew.metadata.mov.QuickTimeAtomTypes;
import com.facebook.react.uimanager.ViewProps;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
/* compiled from: TreeJsonInput.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\tH\u0014J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u000bH\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lkotlinx/serialization/json/internal/JsonTreeMapInput;", "Lkotlinx/serialization/json/internal/JsonTreeInput;", "json", "Lkotlinx/serialization/json/Json;", "value", "Lkotlinx/serialization/json/JsonObject;", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonObject;)V", QuickTimeAtomTypes.ATOM_KEYS, "", "", ViewProps.POSITION, "", "size", "getValue", "()Lkotlinx/serialization/json/JsonObject;", "currentElement", "Lkotlinx/serialization/json/JsonElement;", "tag", "decodeElementIndex", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "elementName", DialogConstants.DESC, "index", "endStructure", "", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class JsonTreeMapInput extends JsonTreeInput {
    private final List<String> keys;
    private int position;
    private final int size;
    @NotNull
    private final JsonObject value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonTreeMapInput(@NotNull Json json, @NotNull JsonObject value) {
        super(json, value);
        List<String> list;
        Intrinsics.checkParameterIsNotNull(json, "json");
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.value = value;
        list = CollectionsKt___CollectionsKt.toList(mo12417getValue().keySet());
        this.keys = list;
        this.size = this.keys.size() * 2;
        this.position = -1;
    }

    @Override // kotlinx.serialization.json.internal.JsonTreeInput, kotlinx.serialization.json.internal.AbstractJsonTreeInput
    @NotNull
    protected JsonElement currentElement(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        return this.position % 2 == 0 ? new JsonLiteral(tag) : (JsonElement) MapsKt.getValue(mo12417getValue(), tag);
    }

    @Override // kotlinx.serialization.json.internal.JsonTreeInput, kotlinx.serialization.CompositeDecoder
    public int decodeElementIndex(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
        int i = this.position;
        if (i < this.size - 1) {
            this.position = i + 1;
            return this.position;
        }
        return -1;
    }

    @Override // kotlinx.serialization.internal.NamedValueDecoder
    @NotNull
    public String elementName(@NotNull SerialDescriptor desc, int i) {
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        return this.keys.get(i / 2);
    }

    @Override // kotlinx.serialization.json.internal.JsonTreeInput, kotlinx.serialization.json.internal.AbstractJsonTreeInput, kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.CompositeDecoder
    public void endStructure(@NotNull SerialDescriptor descriptor) {
        Intrinsics.checkParameterIsNotNull(descriptor, "descriptor");
    }

    @Override // kotlinx.serialization.json.internal.JsonTreeInput, kotlinx.serialization.json.internal.AbstractJsonTreeInput
    @NotNull
    /* renamed from: getValue  reason: collision with other method in class */
    public JsonObject mo12417getValue() {
        return this.value;
    }
}
