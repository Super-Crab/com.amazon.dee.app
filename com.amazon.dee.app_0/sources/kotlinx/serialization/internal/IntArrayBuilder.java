package kotlinx.serialization.internal;

import com.facebook.react.uimanager.ViewProps;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.InternalSerializationApi;
import org.jetbrains.annotations.NotNull;
/* compiled from: PrimitiveArraysSerializers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u000eJ\b\u0010\u000f\u001a\u00020\u0002H\u0016J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0007H\u0016R\u000e\u0010\u0005\u001a\u00020\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lkotlinx/serialization/internal/IntArrayBuilder;", "Lkotlinx/serialization/internal/PrimitiveArrayBuilder;", "", "bufferWithData", "([I)V", "buffer", "<set-?>", "", ViewProps.POSITION, "getPosition", "()I", "append", "", "c", "append$kotlinx_serialization_runtime", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "ensureCapacity", "requiredCapacity", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
@Deprecated(level = DeprecationLevel.HIDDEN, message = "For internal use")
/* loaded from: classes4.dex */
public final class IntArrayBuilder extends PrimitiveArrayBuilder<int[]> {
    private int[] buffer;
    private int position;

    public IntArrayBuilder(@NotNull int[] bufferWithData) {
        Intrinsics.checkParameterIsNotNull(bufferWithData, "bufferWithData");
        this.buffer = bufferWithData;
        this.position = bufferWithData.length;
        ensureCapacity(10);
    }

    public final void append$kotlinx_serialization_runtime(int i) {
        PrimitiveArrayBuilder.ensureCapacity$default(this, 0, 1, null);
        int[] iArr = this.buffer;
        int position = getPosition();
        this.position = position + 1;
        iArr[position] = i;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity(int i) {
        int coerceAtLeast;
        int[] iArr = this.buffer;
        if (iArr.length < i) {
            coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(i, iArr.length * 2);
            int[] copyOf = Arrays.copyOf(iArr, coerceAtLeast);
            Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
            this.buffer = copyOf;
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition() {
        return this.position;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    @NotNull
    public int[] build() {
        int[] copyOf = Arrays.copyOf(this.buffer, getPosition());
        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, newSize)");
        return copyOf;
    }
}