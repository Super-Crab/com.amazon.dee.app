package kotlinx.serialization.json;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.Decoder;
import kotlinx.serialization.Encoder;
import org.jetbrains.annotations.NotNull;
/* compiled from: JsonElementSerializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\u0003H\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\u0005H\u0000¨\u0006\n"}, d2 = {"verify", "", "decoder", "Lkotlinx/serialization/Decoder;", "encoder", "Lkotlinx/serialization/Encoder;", "asJsonInput", "Lkotlinx/serialization/json/JsonInput;", "asJsonOutput", "Lkotlinx/serialization/json/JsonOutput;", "kotlinx-serialization-runtime"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class JsonElementSerializerKt {
    public static final /* synthetic */ void access$verify(Decoder decoder) {
        verify(decoder);
    }

    @NotNull
    public static final JsonInput asJsonInput(@NotNull Decoder asJsonInput) {
        Intrinsics.checkParameterIsNotNull(asJsonInput, "$this$asJsonInput");
        JsonInput jsonInput = (JsonInput) (!(asJsonInput instanceof JsonInput) ? null : asJsonInput);
        if (jsonInput != null) {
            return jsonInput;
        }
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("This serializer can be used only with Json format.", "Expected Decoder to be JsonInput, got ");
        outline113.append(Reflection.getOrCreateKotlinClass(asJsonInput.getClass()));
        throw new IllegalStateException(outline113.toString());
    }

    @NotNull
    public static final JsonOutput asJsonOutput(@NotNull Encoder asJsonOutput) {
        Intrinsics.checkParameterIsNotNull(asJsonOutput, "$this$asJsonOutput");
        JsonOutput jsonOutput = (JsonOutput) (!(asJsonOutput instanceof JsonOutput) ? null : asJsonOutput);
        if (jsonOutput != null) {
            return jsonOutput;
        }
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("This serializer can be used only with Json format.", "Expected Encoder to be JsonOutput, got ");
        outline113.append(Reflection.getOrCreateKotlinClass(asJsonOutput.getClass()));
        throw new IllegalStateException(outline113.toString());
    }

    public static final void verify(Encoder encoder) {
        asJsonOutput(encoder);
    }

    public static final /* synthetic */ void access$verify(Encoder encoder) {
        verify(encoder);
    }

    public static final void verify(Decoder decoder) {
        asJsonInput(decoder);
    }
}
