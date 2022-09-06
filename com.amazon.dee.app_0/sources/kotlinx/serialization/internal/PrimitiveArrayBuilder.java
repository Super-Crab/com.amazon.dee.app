package kotlinx.serialization.internal;

import com.facebook.react.uimanager.ViewProps;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlinx.serialization.InternalSerializationApi;
/* compiled from: CollectionSerializers.kt */
@Deprecated(level = DeprecationLevel.HIDDEN, message = "For internal use")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007\b\u0000¢\u0006\u0002\u0010\u0003J\r\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\tJ\u0012\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u0005H&R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lkotlinx/serialization/internal/PrimitiveArrayBuilder;", "Array", "", "()V", ViewProps.POSITION, "", "getPosition", "()I", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "()Ljava/lang/Object;", "ensureCapacity", "", "requiredCapacity", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
@InternalSerializationApi
/* loaded from: classes4.dex */
public abstract class PrimitiveArrayBuilder<Array> {
    public static /* synthetic */ void ensureCapacity$default(PrimitiveArrayBuilder primitiveArrayBuilder, int i, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 1) != 0) {
                i = primitiveArrayBuilder.getPosition() + 1;
            }
            primitiveArrayBuilder.ensureCapacity(i);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: ensureCapacity");
    }

    public abstract Array build();

    public abstract void ensureCapacity(int i);

    public abstract int getPosition();
}
