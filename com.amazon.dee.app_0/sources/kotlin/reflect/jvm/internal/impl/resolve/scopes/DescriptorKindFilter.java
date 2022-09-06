package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MemberScope.kt */
/* loaded from: classes4.dex */
public final class DescriptorKindFilter {
    @JvmField
    @NotNull
    public static final DescriptorKindFilter ALL;
    @JvmField
    @NotNull
    public static final DescriptorKindFilter CALLABLES;
    private static final int CALLABLES_MASK;
    @JvmField
    @NotNull
    public static final DescriptorKindFilter CLASSIFIERS;
    private static final int CLASSIFIERS_MASK;
    private static final List<Companion.MaskToName> DEBUG_MASK_BIT_NAMES;
    private static final List<Companion.MaskToName> DEBUG_PREDEFINED_FILTERS_MASK_NAMES;
    @JvmField
    @NotNull
    public static final DescriptorKindFilter FUNCTIONS;
    @JvmField
    @NotNull
    public static final DescriptorKindFilter NON_SINGLETON_CLASSIFIERS;
    @JvmField
    @NotNull
    public static final DescriptorKindFilter PACKAGES;
    @JvmField
    @NotNull
    public static final DescriptorKindFilter SINGLETON_CLASSIFIERS;
    @JvmField
    @NotNull
    public static final DescriptorKindFilter TYPE_ALIASES;
    @JvmField
    @NotNull
    public static final DescriptorKindFilter VALUES;
    private static final int VALUES_MASK;
    @JvmField
    @NotNull
    public static final DescriptorKindFilter VARIABLES;
    @NotNull
    private final List<DescriptorKindExclude> excludes;
    private final int kindMask;
    public static final Companion Companion = new Companion(null);
    private static int nextMaskValue = 1;
    private static final int NON_SINGLETON_CLASSIFIERS_MASK = Companion.nextMask();
    private static final int SINGLETON_CLASSIFIERS_MASK = Companion.nextMask();
    private static final int TYPE_ALIASES_MASK = Companion.nextMask();
    private static final int PACKAGES_MASK = Companion.nextMask();
    private static final int FUNCTIONS_MASK = Companion.nextMask();
    private static final int VARIABLES_MASK = Companion.nextMask();
    private static final int ALL_KINDS_MASK = Companion.nextMask() - 1;

    /* compiled from: MemberScope.kt */
    /* loaded from: classes4.dex */
    public static final class Companion {

        /* compiled from: MemberScope.kt */
        /* loaded from: classes4.dex */
        private static final class MaskToName {
            private final int mask;
            @NotNull
            private final String name;

            public MaskToName(int i, @NotNull String name) {
                Intrinsics.checkParameterIsNotNull(name, "name");
                this.mask = i;
                this.name = name;
            }

            public final int getMask() {
                return this.mask;
            }

            @NotNull
            public final String getName() {
                return this.name;
            }
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int nextMask() {
            int i = DescriptorKindFilter.nextMaskValue;
            DescriptorKindFilter.nextMaskValue <<= 1;
            return i;
        }

        public final int getALL_KINDS_MASK() {
            return DescriptorKindFilter.ALL_KINDS_MASK;
        }

        public final int getCLASSIFIERS_MASK() {
            return DescriptorKindFilter.CLASSIFIERS_MASK;
        }

        public final int getFUNCTIONS_MASK() {
            return DescriptorKindFilter.FUNCTIONS_MASK;
        }

        public final int getNON_SINGLETON_CLASSIFIERS_MASK() {
            return DescriptorKindFilter.NON_SINGLETON_CLASSIFIERS_MASK;
        }

        public final int getPACKAGES_MASK() {
            return DescriptorKindFilter.PACKAGES_MASK;
        }

        public final int getSINGLETON_CLASSIFIERS_MASK() {
            return DescriptorKindFilter.SINGLETON_CLASSIFIERS_MASK;
        }

        public final int getTYPE_ALIASES_MASK() {
            return DescriptorKindFilter.TYPE_ALIASES_MASK;
        }

        public final int getVARIABLES_MASK() {
            return DescriptorKindFilter.VARIABLES_MASK;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        List<Companion.MaskToName> list;
        List<Companion.MaskToName> list2;
        Companion.MaskToName maskToName;
        Companion.MaskToName maskToName2;
        int i = NON_SINGLETON_CLASSIFIERS_MASK;
        int i2 = SINGLETON_CLASSIFIERS_MASK;
        CLASSIFIERS_MASK = i | i2 | TYPE_ALIASES_MASK;
        int i3 = FUNCTIONS_MASK;
        int i4 = VARIABLES_MASK;
        VALUES_MASK = i2 | i3 | i4;
        CALLABLES_MASK = i3 | i4;
        ALL = new DescriptorKindFilter(ALL_KINDS_MASK, null, 2, null);
        CALLABLES = new DescriptorKindFilter(CALLABLES_MASK, null, 2, null);
        NON_SINGLETON_CLASSIFIERS = new DescriptorKindFilter(NON_SINGLETON_CLASSIFIERS_MASK, null, 2, null);
        SINGLETON_CLASSIFIERS = new DescriptorKindFilter(SINGLETON_CLASSIFIERS_MASK, null, 2, null);
        TYPE_ALIASES = new DescriptorKindFilter(TYPE_ALIASES_MASK, null, 2, null);
        CLASSIFIERS = new DescriptorKindFilter(CLASSIFIERS_MASK, null, 2, null);
        PACKAGES = new DescriptorKindFilter(PACKAGES_MASK, null, 2, null);
        FUNCTIONS = new DescriptorKindFilter(FUNCTIONS_MASK, null, 2, null);
        VARIABLES = new DescriptorKindFilter(VARIABLES_MASK, null, 2, null);
        VALUES = new DescriptorKindFilter(VALUES_MASK, null, 2, null);
        Field[] fields = DescriptorKindFilter.class.getFields();
        Intrinsics.checkExpressionValueIsNotNull(fields, "T::class.java.fields");
        ArrayList<Field> arrayList = new ArrayList();
        for (Field it2 : fields) {
            Intrinsics.checkExpressionValueIsNotNull(it2, "it");
            if (Modifier.isStatic(it2.getModifiers())) {
                arrayList.add(it2);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Field field : arrayList) {
            Object obj = field.get(null);
            if (!(obj instanceof DescriptorKindFilter)) {
                obj = null;
            }
            DescriptorKindFilter descriptorKindFilter = (DescriptorKindFilter) obj;
            if (descriptorKindFilter != null) {
                int i5 = descriptorKindFilter.kindMask;
                Intrinsics.checkExpressionValueIsNotNull(field, "field");
                String name = field.getName();
                Intrinsics.checkExpressionValueIsNotNull(name, "field.name");
                maskToName2 = new Companion.MaskToName(i5, name);
            } else {
                maskToName2 = null;
            }
            if (maskToName2 != null) {
                arrayList2.add(maskToName2);
            }
        }
        list = CollectionsKt___CollectionsKt.toList(arrayList2);
        DEBUG_PREDEFINED_FILTERS_MASK_NAMES = list;
        Field[] fields2 = DescriptorKindFilter.class.getFields();
        Intrinsics.checkExpressionValueIsNotNull(fields2, "T::class.java.fields");
        ArrayList arrayList3 = new ArrayList();
        for (Field it3 : fields2) {
            Intrinsics.checkExpressionValueIsNotNull(it3, "it");
            if (Modifier.isStatic(it3.getModifiers())) {
                arrayList3.add(it3);
            }
        }
        ArrayList<Field> arrayList4 = new ArrayList();
        for (Object obj2 : arrayList3) {
            Field it4 = (Field) obj2;
            Intrinsics.checkExpressionValueIsNotNull(it4, "it");
            if (Intrinsics.areEqual(it4.getType(), Integer.TYPE)) {
                arrayList4.add(obj2);
            }
        }
        ArrayList arrayList5 = new ArrayList();
        for (Field field2 : arrayList4) {
            Object obj3 = field2.get(null);
            if (obj3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
            int intValue = ((Integer) obj3).intValue();
            if (intValue == ((-intValue) & intValue)) {
                Intrinsics.checkExpressionValueIsNotNull(field2, "field");
                String name2 = field2.getName();
                Intrinsics.checkExpressionValueIsNotNull(name2, "field.name");
                maskToName = new Companion.MaskToName(intValue, name2);
            } else {
                maskToName = null;
            }
            if (maskToName != null) {
                arrayList5.add(maskToName);
            }
        }
        list2 = CollectionsKt___CollectionsKt.toList(arrayList5);
        DEBUG_MASK_BIT_NAMES = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DescriptorKindFilter(int i, @NotNull List<? extends DescriptorKindExclude> excludes) {
        Intrinsics.checkParameterIsNotNull(excludes, "excludes");
        this.excludes = excludes;
        for (DescriptorKindExclude descriptorKindExclude : this.excludes) {
            i &= ~descriptorKindExclude.getFullyExcludedDescriptorKinds();
        }
        this.kindMask = i;
    }

    public final boolean acceptsKinds(int i) {
        return (i & this.kindMask) != 0;
    }

    @NotNull
    public final List<DescriptorKindExclude> getExcludes() {
        return this.excludes;
    }

    public final int getKindMask() {
        return this.kindMask;
    }

    @Nullable
    public final DescriptorKindFilter restrictedToKindsOrNull(int i) {
        int i2 = i & this.kindMask;
        if (i2 == 0) {
            return null;
        }
        return new DescriptorKindFilter(i2, this.excludes);
    }

    @NotNull
    public String toString() {
        Object obj;
        boolean z;
        Iterator<T> it2 = DEBUG_PREDEFINED_FILTERS_MASK_NAMES.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            if (((Companion.MaskToName) obj).getMask() == this.kindMask) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                break;
            }
        }
        Companion.MaskToName maskToName = (Companion.MaskToName) obj;
        String name = maskToName != null ? maskToName.getName() : null;
        if (name == null) {
            List<Companion.MaskToName> list = DEBUG_MASK_BIT_NAMES;
            ArrayList arrayList = new ArrayList();
            for (Companion.MaskToName maskToName2 : list) {
                String name2 = acceptsKinds(maskToName2.getMask()) ? maskToName2.getName() : null;
                if (name2 != null) {
                    arrayList.add(name2);
                }
            }
            name = CollectionsKt___CollectionsKt.joinToString$default(arrayList, " | ", null, null, 0, null, null, 62, null);
        }
        return GeneratedOutlineSupport1.outline94(GeneratedOutlineSupport1.outline115("DescriptorKindFilter(", name, ", "), this.excludes, ')');
    }

    public /* synthetic */ DescriptorKindFilter(int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
    }
}
