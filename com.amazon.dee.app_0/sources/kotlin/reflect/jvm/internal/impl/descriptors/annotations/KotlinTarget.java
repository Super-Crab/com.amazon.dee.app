package kotlin.reflect.jvm.internal.impl.descriptors.annotations;

import com.amazon.deecomms.calling.incallcommands.constants.CommsFocusConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: KotlinTarget.kt */
/* loaded from: classes2.dex */
public enum KotlinTarget {
    CLASS("class", false, 2, null),
    ANNOTATION_CLASS("annotation class", false, 2, null),
    TYPE_PARAMETER("type parameter", false),
    PROPERTY("property", false, 2, null),
    FIELD("field", false, 2, null),
    LOCAL_VARIABLE("local variable", false, 2, null),
    VALUE_PARAMETER("value parameter", false, 2, null),
    CONSTRUCTOR("constructor", false, 2, null),
    FUNCTION("function", false, 2, null),
    PROPERTY_GETTER("getter", false, 2, null),
    PROPERTY_SETTER("setter", false, 2, null),
    TYPE("type usage", false),
    EXPRESSION("expression", false),
    FILE("file", false),
    TYPEALIAS("typealias", false),
    TYPE_PROJECTION("type projection", false),
    STAR_PROJECTION("star projection", false),
    PROPERTY_PARAMETER("property constructor parameter", false),
    CLASS_ONLY("class", false),
    OBJECT("object", false),
    COMPANION_OBJECT("companion object", false),
    INTERFACE(CommsFocusConstants.INTERFACE, false),
    ENUM_CLASS("enum class", false),
    ENUM_ENTRY("enum entry", false),
    LOCAL_CLASS("local class", false),
    LOCAL_FUNCTION("local function", false),
    MEMBER_FUNCTION("member function", false),
    TOP_LEVEL_FUNCTION("top level function", false),
    MEMBER_PROPERTY("member property", false),
    MEMBER_PROPERTY_WITH_BACKING_FIELD("member property with backing field", false),
    MEMBER_PROPERTY_WITH_DELEGATE("member property with delegate", false),
    MEMBER_PROPERTY_WITHOUT_FIELD_OR_DELEGATE("member property without backing field or delegate", false),
    TOP_LEVEL_PROPERTY("top level property", false),
    TOP_LEVEL_PROPERTY_WITH_BACKING_FIELD("top level property with backing field", false),
    TOP_LEVEL_PROPERTY_WITH_DELEGATE("top level property with delegate", false),
    TOP_LEVEL_PROPERTY_WITHOUT_FIELD_OR_DELEGATE("top level property without backing field or delegate", false),
    INITIALIZER("initializer", false),
    DESTRUCTURING_DECLARATION("destructuring declaration", false),
    LAMBDA_EXPRESSION("lambda expression", false),
    ANONYMOUS_FUNCTION("anonymous function", false),
    OBJECT_LITERAL("object literal", false);
    
    @NotNull
    private static final Set<KotlinTarget> ALL_TARGET_SET;
    @NotNull
    private static final Set<KotlinTarget> DEFAULT_TARGET_SET;
    @NotNull
    private static final Map<AnnotationUseSiteTarget, KotlinTarget> USE_SITE_MAPPING;
    @NotNull
    private final String description;
    private final boolean isDefault;
    public static final Companion Companion = new Companion(null);
    private static final HashMap<String, KotlinTarget> map = new HashMap<>();

    /* compiled from: KotlinTarget.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        KotlinTarget[] values;
        Set<KotlinTarget> set;
        Set<KotlinTarget> set2;
        Map<AnnotationUseSiteTarget, KotlinTarget> mapOf;
        for (KotlinTarget kotlinTarget : values()) {
            map.put(kotlinTarget.name(), kotlinTarget);
        }
        KotlinTarget[] values2 = values();
        ArrayList arrayList = new ArrayList();
        for (KotlinTarget kotlinTarget2 : values2) {
            if (kotlinTarget2.isDefault) {
                arrayList.add(kotlinTarget2);
            }
        }
        set = CollectionsKt___CollectionsKt.toSet(arrayList);
        DEFAULT_TARGET_SET = set;
        set2 = ArraysKt___ArraysKt.toSet(values());
        ALL_TARGET_SET = set2;
        mapOf = MapsKt__MapsKt.mapOf(TuplesKt.to(AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER, VALUE_PARAMETER), TuplesKt.to(AnnotationUseSiteTarget.FIELD, FIELD), TuplesKt.to(AnnotationUseSiteTarget.PROPERTY, PROPERTY), TuplesKt.to(AnnotationUseSiteTarget.FILE, FILE), TuplesKt.to(AnnotationUseSiteTarget.PROPERTY_GETTER, PROPERTY_GETTER), TuplesKt.to(AnnotationUseSiteTarget.PROPERTY_SETTER, PROPERTY_SETTER), TuplesKt.to(AnnotationUseSiteTarget.RECEIVER, VALUE_PARAMETER), TuplesKt.to(AnnotationUseSiteTarget.SETTER_PARAMETER, VALUE_PARAMETER), TuplesKt.to(AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD, FIELD));
        USE_SITE_MAPPING = mapOf;
    }

    KotlinTarget(String str, boolean z) {
        this.description = str;
        this.isDefault = z;
    }

    /* synthetic */ KotlinTarget(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? true : z);
    }
}
