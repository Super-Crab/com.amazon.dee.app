package kotlinx.serialization.json;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorBuilderKt;
import kotlinx.serialization.json.internal.TreeJsonOutputKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: JsonTransformingSerializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0014J\u001b\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u0000¢\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0014R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lkotlinx/serialization/json/JsonTransformingSerializer;", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/serialization/KSerializer;", "tSerializer", "transformationName", "", "(Lkotlinx/serialization/KSerializer;Ljava/lang/String;)V", "descriptor", "Lkotlinx/serialization/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/Decoder;", "(Lkotlinx/serialization/Decoder;)Ljava/lang/Object;", "readTransform", "Lkotlinx/serialization/json/JsonElement;", "element", "serialize", "", "encoder", "Lkotlinx/serialization/Encoder;", "value", "(Lkotlinx/serialization/Encoder;Ljava/lang/Object;)V", "writeTransform", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public abstract class JsonTransformingSerializer<T> implements KSerializer<T> {
    @NotNull
    private final SerialDescriptor descriptor;
    private final KSerializer<T> tSerializer;

    public JsonTransformingSerializer(@NotNull KSerializer<T> tSerializer, @NotNull String transformationName) {
        Intrinsics.checkParameterIsNotNull(tSerializer, "tSerializer");
        Intrinsics.checkParameterIsNotNull(transformationName, "transformationName");
        this.tSerializer = tSerializer;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("JsonTransformingSerializer<");
        outline107.append(this.tSerializer.getDescriptor().getSerialName());
        outline107.append(">(");
        outline107.append(transformationName);
        outline107.append(')');
        this.descriptor = SerialDescriptorBuilderKt.SerialDescriptor$default(outline107.toString(), this.tSerializer.getDescriptor().mo12397getKind(), null, 4, null);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    @NotNull
    /* renamed from: deserialize */
    public final T mo12413deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        JsonInput asJsonInput = JsonElementSerializerKt.asJsonInput(decoder);
        return (T) asJsonInput.getJson().fromJson(this.tSerializer, readTransform(asJsonInput.decodeJson()));
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.DeserializationStrategy
    @NotNull
    public T patch(@NotNull Decoder decoder, @NotNull T old) {
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        Intrinsics.checkParameterIsNotNull(old, "old");
        return (T) KSerializer.DefaultImpls.patch(this, decoder, old);
    }

    @NotNull
    protected JsonElement readTransform(@NotNull JsonElement element) {
        Intrinsics.checkParameterIsNotNull(element, "element");
        return element;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(@NotNull Encoder encoder, @NotNull T value) {
        Intrinsics.checkParameterIsNotNull(encoder, "encoder");
        Intrinsics.checkParameterIsNotNull(value, "value");
        JsonOutput asJsonOutput = JsonElementSerializerKt.asJsonOutput(encoder);
        asJsonOutput.encodeJson(writeTransform(TreeJsonOutputKt.writeJson(asJsonOutput.getJson(), value, this.tSerializer)));
    }

    @NotNull
    protected JsonElement writeTransform(@NotNull JsonElement element) {
        Intrinsics.checkParameterIsNotNull(element, "element");
        return element;
    }
}
