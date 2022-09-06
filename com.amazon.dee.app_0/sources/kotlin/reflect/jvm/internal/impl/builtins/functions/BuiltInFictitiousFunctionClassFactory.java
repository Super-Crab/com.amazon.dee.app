package kotlin.reflect.jvm.internal.impl.builtins.functions;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsPackageFragment;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionInterfacePackageFragment;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BuiltInFictitiousFunctionClassFactory.kt */
/* loaded from: classes2.dex */
public final class BuiltInFictitiousFunctionClassFactory implements ClassDescriptorFactory {
    public static final Companion Companion = new Companion(null);
    private final ModuleDescriptor module;
    private final StorageManager storageManager;

    /* compiled from: BuiltInFictitiousFunctionClassFactory.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final KindWithArity parseClassName(String str, FqName fqName) {
            FunctionClassDescriptor.Kind byClassNamePrefix = FunctionClassDescriptor.Kind.Companion.byClassNamePrefix(fqName, str);
            if (byClassNamePrefix != null) {
                int length = byClassNamePrefix.getClassNamePrefix().length();
                if (str == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String substring = str.substring(length);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                Integer num = toInt(substring);
                if (num == null) {
                    return null;
                }
                return new KindWithArity(byClassNamePrefix, num.intValue());
            }
            return null;
        }

        private final Integer toInt(String str) {
            if (str.length() == 0) {
                return null;
            }
            int length = str.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                int charAt = str.charAt(i2) - '0';
                if (charAt < 0 || 9 < charAt) {
                    return null;
                }
                i = (i * 10) + charAt;
            }
            return Integer.valueOf(i);
        }

        @JvmStatic
        @Nullable
        public final FunctionClassDescriptor.Kind getFunctionalClassKind(@NotNull String className, @NotNull FqName packageFqName) {
            Intrinsics.checkParameterIsNotNull(className, "className");
            Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
            KindWithArity parseClassName = parseClassName(className, packageFqName);
            if (parseClassName != null) {
                return parseClassName.getKind();
            }
            return null;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BuiltInFictitiousFunctionClassFactory.kt */
    /* loaded from: classes2.dex */
    public static final class KindWithArity {
        private final int arity;
        @NotNull
        private final FunctionClassDescriptor.Kind kind;

        public KindWithArity(@NotNull FunctionClassDescriptor.Kind kind, int i) {
            Intrinsics.checkParameterIsNotNull(kind, "kind");
            this.kind = kind;
            this.arity = i;
        }

        @NotNull
        public final FunctionClassDescriptor.Kind component1() {
            return this.kind;
        }

        public final int component2() {
            return this.arity;
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof KindWithArity)) {
                    return false;
                }
                KindWithArity kindWithArity = (KindWithArity) obj;
                return Intrinsics.areEqual(this.kind, kindWithArity.kind) && this.arity == kindWithArity.arity;
            }
            return true;
        }

        @NotNull
        public final FunctionClassDescriptor.Kind getKind() {
            return this.kind;
        }

        public int hashCode() {
            FunctionClassDescriptor.Kind kind = this.kind;
            return ((kind != null ? kind.hashCode() : 0) * 31) + this.arity;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("KindWithArity(kind=");
            outline107.append(this.kind);
            outline107.append(", arity=");
            return GeneratedOutlineSupport1.outline86(outline107, this.arity, ")");
        }
    }

    public BuiltInFictitiousFunctionClassFactory(@NotNull StorageManager storageManager, @NotNull ModuleDescriptor module) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(module, "module");
        this.storageManager = storageManager;
        this.module = module;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    @Nullable
    public ClassDescriptor createClass(@NotNull ClassId classId) {
        boolean contains$default;
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        if (!classId.isLocal() && !classId.isNestedClass()) {
            String asString = classId.getRelativeClassName().asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "classId.relativeClassName.asString()");
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) asString, (CharSequence) "Function", false, 2, (Object) null);
            if (!contains$default) {
                return null;
            }
            FqName packageFqName = classId.getPackageFqName();
            Intrinsics.checkExpressionValueIsNotNull(packageFqName, "classId.packageFqName");
            KindWithArity parseClassName = Companion.parseClassName(asString, packageFqName);
            if (parseClassName != null) {
                FunctionClassDescriptor.Kind component1 = parseClassName.component1();
                int component2 = parseClassName.component2();
                List<PackageFragmentDescriptor> fragments = this.module.getPackage(packageFqName).getFragments();
                ArrayList arrayList = new ArrayList();
                for (Object obj : fragments) {
                    if (obj instanceof BuiltInsPackageFragment) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object obj2 : arrayList) {
                    if (obj2 instanceof FunctionInterfacePackageFragment) {
                        arrayList2.add(obj2);
                    }
                }
                PackageFragmentDescriptor packageFragmentDescriptor = (FunctionInterfacePackageFragment) CollectionsKt.firstOrNull((List<? extends Object>) arrayList2);
                if (packageFragmentDescriptor == null) {
                    packageFragmentDescriptor = (BuiltInsPackageFragment) CollectionsKt.first((List<? extends Object>) arrayList);
                }
                return new FunctionClassDescriptor(this.storageManager, packageFragmentDescriptor, component1, component2);
            }
        }
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    @NotNull
    public Collection<ClassDescriptor> getAllContributedClassesIfPossible(@NotNull FqName packageFqName) {
        Set emptySet;
        Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    public boolean shouldCreateClass(@NotNull FqName packageFqName, @NotNull Name name) {
        boolean startsWith$default;
        boolean startsWith$default2;
        boolean startsWith$default3;
        boolean startsWith$default4;
        Intrinsics.checkParameterIsNotNull(packageFqName, "packageFqName");
        Intrinsics.checkParameterIsNotNull(name, "name");
        String asString = name.asString();
        Intrinsics.checkExpressionValueIsNotNull(asString, "name.asString()");
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(asString, "Function", false, 2, null);
        if (!startsWith$default) {
            startsWith$default2 = StringsKt__StringsJVMKt.startsWith$default(asString, "KFunction", false, 2, null);
            if (!startsWith$default2) {
                startsWith$default3 = StringsKt__StringsJVMKt.startsWith$default(asString, "SuspendFunction", false, 2, null);
                if (!startsWith$default3) {
                    startsWith$default4 = StringsKt__StringsJVMKt.startsWith$default(asString, "KSuspendFunction", false, 2, null);
                    if (!startsWith$default4) {
                        return false;
                    }
                }
            }
        }
        return Companion.parseClassName(asString, packageFqName) != null;
    }
}
