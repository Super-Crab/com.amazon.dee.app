package kotlinx.serialization.internal;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
/* compiled from: Primitives.kt */
@Deprecated(message = "Top level primitive descriptors are unavailable to avoid accidental misuage. Please use kind for comparison and primitive descriptor with a unique name for implementation", replaceWith = @ReplaceWith(expression = "PrimitiveDescriptor(\"yourSerializerUniqueName\", PrimitiveKind.INT)", imports = {}))
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lkotlinx/serialization/internal/IntDescriptor;", "Lkotlinx/serialization/internal/Migration;", "()V", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class IntDescriptor extends Migration {
    public static final IntDescriptor INSTANCE = new IntDescriptor();

    private IntDescriptor() {
    }
}
