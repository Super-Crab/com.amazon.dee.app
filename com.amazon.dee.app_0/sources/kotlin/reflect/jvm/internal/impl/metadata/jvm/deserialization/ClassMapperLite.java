package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.mobilytics.configuration.KinesisEndpoint;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.jetbrains.annotations.NotNull;
/* compiled from: ClassMapperLite.kt */
/* loaded from: classes4.dex */
public final class ClassMapperLite {
    public static final ClassMapperLite INSTANCE = new ClassMapperLite();
    private static final Map<String, String> map;

    static {
        List listOf;
        IntRange indices;
        IntProgression step;
        List<String> listOf2;
        List<String> listOf3;
        List<String> listOf4;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"Boolean", "Z", "Char", "C", "Byte", "B", "Short", ExifInterface.LATITUDE_SOUTH, "Int", "I", "Float", "F", "Long", "J", "Double", "D"});
        indices = CollectionsKt__CollectionsKt.getIndices(listOf);
        step = RangesKt___RangesKt.step(indices, 2);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if (step2 < 0 ? first >= last : first <= last) {
            while (true) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("kotlin/");
                outline107.append((String) listOf.get(first));
                int i = first + 1;
                linkedHashMap.put(outline107.toString(), listOf.get(i));
                StringBuilder sb = new StringBuilder();
                sb.append("kotlin/");
                String outline91 = GeneratedOutlineSupport1.outline91(sb, (String) listOf.get(first), "Array");
                StringBuilder outline104 = GeneratedOutlineSupport1.outline104(JsonReaderKt.BEGIN_LIST);
                outline104.append((String) listOf.get(i));
                linkedHashMap.put(outline91, outline104.toString());
                if (first == last) {
                    break;
                }
                first += step2;
            }
        }
        linkedHashMap.put("kotlin/Unit", ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        ClassMapperLite$map$1$1 classMapperLite$map$1$1 = new ClassMapperLite$map$1$1(linkedHashMap);
        classMapperLite$map$1$1.invoke2(KinesisEndpoint.AppState.ANY, "java/lang/Object");
        classMapperLite$map$1$1.invoke2("Nothing", "java/lang/Void");
        classMapperLite$map$1$1.invoke2("Annotation", "java/lang/annotation/Annotation");
        listOf2 = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"String", "CharSequence", "Throwable", "Cloneable", "Number", "Comparable", "Enum"});
        for (String str : listOf2) {
            classMapperLite$map$1$1.invoke2(str, GeneratedOutlineSupport1.outline72("java/lang/", str));
        }
        listOf3 = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"Iterator", "Collection", "List", "Set", "Map", "ListIterator"});
        for (String str2 : listOf3) {
            classMapperLite$map$1$1.invoke2(GeneratedOutlineSupport1.outline72("collections/", str2), GeneratedOutlineSupport1.outline72("java/util/", str2));
            classMapperLite$map$1$1.invoke2(GeneratedOutlineSupport1.outline72("collections/Mutable", str2), GeneratedOutlineSupport1.outline72("java/util/", str2));
        }
        classMapperLite$map$1$1.invoke2("collections/Iterable", "java/lang/Iterable");
        classMapperLite$map$1$1.invoke2("collections/MutableIterable", "java/lang/Iterable");
        classMapperLite$map$1$1.invoke2("collections/Map.Entry", "java/util/Map$Entry");
        classMapperLite$map$1$1.invoke2("collections/MutableMap.MutableEntry", "java/util/Map$Entry");
        for (int i2 = 0; i2 <= 22; i2++) {
            classMapperLite$map$1$1.invoke2(GeneratedOutlineSupport1.outline49("Function", i2), GeneratedOutlineSupport1.outline49("kotlin/jvm/functions/Function", i2));
            classMapperLite$map$1$1.invoke2(GeneratedOutlineSupport1.outline49("reflect/KFunction", i2), "kotlin/reflect/KFunction");
        }
        listOf4 = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"Char", "Byte", "Short", "Int", "Float", "Long", "Double", "String", "Enum"});
        for (String str3 : listOf4) {
            classMapperLite$map$1$1.invoke2(GeneratedOutlineSupport1.outline72(str3, ".Companion"), GeneratedOutlineSupport1.outline75("kotlin/jvm/internal/", str3, "CompanionObject"));
        }
        map = linkedHashMap;
    }

    private ClassMapperLite() {
    }

    @JvmStatic
    @NotNull
    public static final String mapClass(@NotNull String classId) {
        String replace$default;
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        String str = map.get(classId);
        if (str != null) {
            return str;
        }
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104(Matrix.MATRIX_TYPE_RANDOM_LT);
        replace$default = StringsKt__StringsJVMKt.replace$default(classId, '.', '$', false, 4, (Object) null);
        return GeneratedOutlineSupport1.outline89(outline104, replace$default, ';');
    }
}
