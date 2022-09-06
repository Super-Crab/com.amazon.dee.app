package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ConstantValueFactory.kt */
/* loaded from: classes4.dex */
public final class ConstantValueFactory {
    public static final ConstantValueFactory INSTANCE = new ConstantValueFactory();

    private ConstantValueFactory() {
    }

    @NotNull
    public final ArrayValue createArrayValue(@NotNull List<? extends ConstantValue<?>> value, @NotNull KotlinType type) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        Intrinsics.checkParameterIsNotNull(type, "type");
        return new ArrayValue(value, new ConstantValueFactory$createArrayValue$1(type));
    }

    @Nullable
    public final ConstantValue<?> createConstantValue(@Nullable Object obj) {
        List<Boolean> list;
        List<Double> list2;
        List<Float> list3;
        List<Character> list4;
        List<Long> list5;
        List<Integer> list6;
        List<Short> list7;
        List<Byte> list8;
        if (obj instanceof Byte) {
            return new ByteValue(((Number) obj).byteValue());
        }
        if (obj instanceof Short) {
            return new ShortValue(((Number) obj).shortValue());
        }
        if (obj instanceof Integer) {
            return new IntValue(((Number) obj).intValue());
        }
        if (obj instanceof Long) {
            return new LongValue(((Number) obj).longValue());
        }
        if (obj instanceof Character) {
            return new CharValue(((Character) obj).charValue());
        }
        if (obj instanceof Float) {
            return new FloatValue(((Number) obj).floatValue());
        }
        if (obj instanceof Double) {
            return new DoubleValue(((Number) obj).doubleValue());
        }
        if (obj instanceof Boolean) {
            return new BooleanValue(((Boolean) obj).booleanValue());
        }
        if (obj instanceof String) {
            return new StringValue((String) obj);
        }
        if (obj instanceof byte[]) {
            list8 = ArraysKt___ArraysKt.toList((byte[]) obj);
            return createArrayValue(list8, PrimitiveType.BYTE);
        } else if (obj instanceof short[]) {
            list7 = ArraysKt___ArraysKt.toList((short[]) obj);
            return createArrayValue(list7, PrimitiveType.SHORT);
        } else if (obj instanceof int[]) {
            list6 = ArraysKt___ArraysKt.toList((int[]) obj);
            return createArrayValue(list6, PrimitiveType.INT);
        } else if (obj instanceof long[]) {
            list5 = ArraysKt___ArraysKt.toList((long[]) obj);
            return createArrayValue(list5, PrimitiveType.LONG);
        } else if (obj instanceof char[]) {
            list4 = ArraysKt___ArraysKt.toList((char[]) obj);
            return createArrayValue(list4, PrimitiveType.CHAR);
        } else if (obj instanceof float[]) {
            list3 = ArraysKt___ArraysKt.toList((float[]) obj);
            return createArrayValue(list3, PrimitiveType.FLOAT);
        } else if (obj instanceof double[]) {
            list2 = ArraysKt___ArraysKt.toList((double[]) obj);
            return createArrayValue(list2, PrimitiveType.DOUBLE);
        } else if (obj instanceof boolean[]) {
            list = ArraysKt___ArraysKt.toList((boolean[]) obj);
            return createArrayValue(list, PrimitiveType.BOOLEAN);
        } else if (obj != null) {
            return null;
        } else {
            return new NullValue();
        }
    }

    private final ArrayValue createArrayValue(List<?> list, PrimitiveType primitiveType) {
        List<Object> list2;
        list2 = CollectionsKt___CollectionsKt.toList(list);
        ArrayList arrayList = new ArrayList();
        for (Object obj : list2) {
            ConstantValue<?> createConstantValue = createConstantValue(obj);
            if (createConstantValue != null) {
                arrayList.add(createConstantValue);
            }
        }
        return new ArrayValue(arrayList, new ConstantValueFactory$createArrayValue$3(primitiveType));
    }
}
