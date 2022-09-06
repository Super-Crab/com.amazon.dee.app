package kotlin.reflect.jvm.internal.impl.descriptors;

import com.amazon.device.messaging.ADMConstants;
import io.ktor.client.utils.CacheControl;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeAliasConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.SuperCallReceiverValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ThisClassReceiver;
import kotlin.reflect.jvm.internal.impl.types.DynamicTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.util.ModuleVisibilityHelper;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class Visibilities {
    public static final ReceiverValue ALWAYS_SUITABLE_RECEIVER;
    public static final Visibility DEFAULT_VISIBILITY;
    @Deprecated
    public static final ReceiverValue FALSE_IF_PROTECTED;
    public static final Set<Visibility> INVISIBLE_FROM_OTHER_MODULES;
    private static final ReceiverValue IRRELEVANT_RECEIVER;
    @NotNull
    private static final ModuleVisibilityHelper MODULE_VISIBILITY_HELPER;
    private static final Map<Visibility, Integer> ORDERED_VISIBILITIES;
    @NotNull
    public static final Visibility PRIVATE = new Visibility(CacheControl.PRIVATE, false) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.1
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 1) {
                objArr[0] = "what";
            } else if (i != 2) {
                objArr[0] = "descriptor";
            } else {
                objArr[0] = ADMConstants.EXTRA_FROM;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$1";
            if (i == 1 || i == 2) {
                objArr[2] = "isVisible";
            } else {
                objArr[2] = "hasContainingSourceFile";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        private boolean hasContainingSourceFile(@NotNull DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(0);
            }
            return DescriptorUtils.getContainingSourceFile(declarationDescriptor) != SourceFile.NO_SOURCE_FILE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v0, types: [kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility] */
        /* JADX WARN: Type inference failed for: r5v1, types: [kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor] */
        /* JADX WARN: Type inference failed for: r5v2, types: [kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor] */
        /* JADX WARN: Type inference failed for: r5v4, types: [kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor] */
        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptorWithVisibility == 0) {
                $$$reportNull$$$0(1);
            }
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(2);
            }
            if (DescriptorUtils.isTopLevelDeclaration(declarationDescriptorWithVisibility) && hasContainingSourceFile(declarationDescriptor)) {
                return Visibilities.inSameFile(declarationDescriptorWithVisibility, declarationDescriptor);
            }
            if (declarationDescriptorWithVisibility instanceof ConstructorDescriptor) {
                ClassifierDescriptorWithTypeParameters mo11607getContainingDeclaration = ((ConstructorDescriptor) declarationDescriptorWithVisibility).mo11607getContainingDeclaration();
                if (DescriptorUtils.isSealedClass(mo11607getContainingDeclaration) && DescriptorUtils.isTopLevelDeclaration(mo11607getContainingDeclaration) && (declarationDescriptor instanceof ConstructorDescriptor) && DescriptorUtils.isTopLevelDeclaration(declarationDescriptor.mo11607getContainingDeclaration()) && Visibilities.inSameFile(declarationDescriptorWithVisibility, declarationDescriptor)) {
                    return true;
                }
            }
            while (declarationDescriptorWithVisibility != 0) {
                declarationDescriptorWithVisibility = declarationDescriptorWithVisibility.mo11607getContainingDeclaration();
                if (declarationDescriptorWithVisibility instanceof ClassDescriptor) {
                    if (!DescriptorUtils.isCompanionObject(declarationDescriptorWithVisibility)) {
                        break;
                    }
                }
                if (declarationDescriptorWithVisibility instanceof PackageFragmentDescriptor) {
                    break;
                }
            }
            if (declarationDescriptorWithVisibility == 0) {
                return false;
            }
            while (declarationDescriptor != null) {
                if (declarationDescriptorWithVisibility == declarationDescriptor) {
                    return true;
                }
                if (declarationDescriptor instanceof PackageFragmentDescriptor) {
                    return (declarationDescriptorWithVisibility instanceof PackageFragmentDescriptor) && declarationDescriptorWithVisibility.getFqName().equals(((PackageFragmentDescriptor) declarationDescriptor).getFqName()) && DescriptorUtils.areInSameModule(declarationDescriptor, declarationDescriptorWithVisibility);
                }
                declarationDescriptor = declarationDescriptor.mo11607getContainingDeclaration();
            }
            return false;
        }
    };
    @NotNull
    public static final Visibility PRIVATE_TO_THIS = new Visibility("private_to_this", false) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.2
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "what";
            } else {
                objArr[0] = ADMConstants.EXTRA_FROM;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$2";
            objArr[2] = "isVisible";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        @NotNull
        public String getInternalDisplayName() {
            return "private/*private to this*/";
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            DeclarationDescriptor parentOfType;
            if (declarationDescriptorWithVisibility == null) {
                $$$reportNull$$$0(0);
            }
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(1);
            }
            if (Visibilities.PRIVATE.isVisible(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor)) {
                if (receiverValue == Visibilities.ALWAYS_SUITABLE_RECEIVER) {
                    return true;
                }
                if (receiverValue != Visibilities.IRRELEVANT_RECEIVER && (parentOfType = DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility, ClassDescriptor.class)) != null && (receiverValue instanceof ThisClassReceiver)) {
                    return ((ThisClassReceiver) receiverValue).getClassDescriptor().mo11613getOriginal().equals(parentOfType.mo11613getOriginal());
                }
            }
            return false;
        }
    };
    @NotNull
    public static final Visibility PROTECTED = new Visibility("protected", true) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.3
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 1) {
                objArr[0] = ADMConstants.EXTRA_FROM;
            } else if (i == 2) {
                objArr[0] = "whatDeclaration";
            } else if (i != 3) {
                objArr[0] = "what";
            } else {
                objArr[0] = "fromClass";
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$3";
            if (i == 2 || i == 3) {
                objArr[2] = "doesReceiverFitForProtectedVisibility";
            } else {
                objArr[2] = "isVisible";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        private boolean doesReceiverFitForProtectedVisibility(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull ClassDescriptor classDescriptor) {
            if (declarationDescriptorWithVisibility == null) {
                $$$reportNull$$$0(2);
            }
            if (classDescriptor == null) {
                $$$reportNull$$$0(3);
            }
            if (receiverValue == Visibilities.FALSE_IF_PROTECTED) {
                return false;
            }
            if (!(declarationDescriptorWithVisibility instanceof CallableMemberDescriptor) || (declarationDescriptorWithVisibility instanceof ConstructorDescriptor) || receiverValue == Visibilities.ALWAYS_SUITABLE_RECEIVER) {
                return true;
            }
            if (receiverValue == Visibilities.IRRELEVANT_RECEIVER || receiverValue == null) {
                return false;
            }
            KotlinType thisType = receiverValue instanceof SuperCallReceiverValue ? ((SuperCallReceiverValue) receiverValue).getThisType() : receiverValue.mo12036getType();
            return DescriptorUtils.isSubtypeOfClass(thisType, classDescriptor) || DynamicTypesKt.isDynamic(thisType);
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            ClassDescriptor classDescriptor;
            if (declarationDescriptorWithVisibility == null) {
                $$$reportNull$$$0(0);
            }
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(1);
            }
            ClassDescriptor classDescriptor2 = (ClassDescriptor) DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility, ClassDescriptor.class);
            ClassDescriptor classDescriptor3 = (ClassDescriptor) DescriptorUtils.getParentOfType(declarationDescriptor, ClassDescriptor.class, false);
            if (classDescriptor3 == null) {
                return false;
            }
            if (classDescriptor2 != null && DescriptorUtils.isCompanionObject(classDescriptor2) && (classDescriptor = (ClassDescriptor) DescriptorUtils.getParentOfType(classDescriptor2, ClassDescriptor.class)) != null && DescriptorUtils.isSubclass(classDescriptor3, classDescriptor)) {
                return true;
            }
            DeclarationDescriptorWithVisibility unwrapFakeOverrideToAnyDeclaration = DescriptorUtils.unwrapFakeOverrideToAnyDeclaration(declarationDescriptorWithVisibility);
            ClassDescriptor classDescriptor4 = (ClassDescriptor) DescriptorUtils.getParentOfType(unwrapFakeOverrideToAnyDeclaration, ClassDescriptor.class);
            if (classDescriptor4 == null) {
                return false;
            }
            if (DescriptorUtils.isSubclass(classDescriptor3, classDescriptor4) && doesReceiverFitForProtectedVisibility(receiverValue, unwrapFakeOverrideToAnyDeclaration, classDescriptor3)) {
                return true;
            }
            return isVisible(receiverValue, declarationDescriptorWithVisibility, classDescriptor3.mo11607getContainingDeclaration());
        }
    };
    @NotNull
    public static final Visibility INTERNAL = new Visibility("internal", false) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.4
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "what";
            } else {
                objArr[0] = ADMConstants.EXTRA_FROM;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$4";
            objArr[2] = "isVisible";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptorWithVisibility == null) {
                $$$reportNull$$$0(0);
            }
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(1);
            }
            if (!DescriptorUtils.getContainingModule(declarationDescriptor).shouldSeeInternalsOf(DescriptorUtils.getContainingModule(declarationDescriptorWithVisibility))) {
                return false;
            }
            return Visibilities.MODULE_VISIBILITY_HELPER.isInFriendModule(declarationDescriptorWithVisibility, declarationDescriptor);
        }
    };
    @NotNull
    public static final Visibility PUBLIC = new Visibility(CacheControl.PUBLIC, true) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.5
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "what";
            } else {
                objArr[0] = ADMConstants.EXTRA_FROM;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$5";
            objArr[2] = "isVisible";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptorWithVisibility == null) {
                $$$reportNull$$$0(0);
            }
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(1);
            }
            return true;
        }
    };
    @NotNull
    public static final Visibility LOCAL = new Visibility("local", false) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.6
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "what";
            } else {
                objArr[0] = ADMConstants.EXTRA_FROM;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$6";
            objArr[2] = "isVisible";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptorWithVisibility == null) {
                $$$reportNull$$$0(0);
            }
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(1);
            }
            throw new IllegalStateException("This method shouldn't be invoked for LOCAL visibility");
        }
    };
    @NotNull
    public static final Visibility INHERITED = new Visibility("inherited", false) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.7
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "what";
            } else {
                objArr[0] = ADMConstants.EXTRA_FROM;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$7";
            objArr[2] = "isVisible";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptorWithVisibility == null) {
                $$$reportNull$$$0(0);
            }
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(1);
            }
            throw new IllegalStateException("Visibility is unknown yet");
        }
    };
    @NotNull
    public static final Visibility INVISIBLE_FAKE = new Visibility("invisible_fake", false) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.8
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "what";
            } else {
                objArr[0] = ADMConstants.EXTRA_FROM;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$8";
            objArr[2] = "isVisible";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptorWithVisibility == null) {
                $$$reportNull$$$0(0);
            }
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(1);
            }
            return false;
        }
    };
    @NotNull
    public static final Visibility UNKNOWN = new Visibility("unknown", false) { // from class: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.9
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "what";
            } else {
                objArr[0] = ADMConstants.EXTRA_FROM;
            }
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/Visibilities$9";
            objArr[2] = "isVisible";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptorWithVisibility == null) {
                $$$reportNull$$$0(0);
            }
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(1);
            }
            return false;
        }
    };

    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static /* synthetic */ void $$$reportNull$$$0(int r4) {
        /*
            r0 = 3
            java.lang.Object[] r1 = new java.lang.Object[r0]
            r2 = 1
            r3 = 0
            if (r4 == r2) goto L29
            if (r4 == r0) goto L29
            r0 = 5
            if (r4 == r0) goto L29
            r0 = 7
            if (r4 == r0) goto L29
            switch(r4) {
                case 9: goto L29;
                case 10: goto L24;
                case 11: goto L1e;
                case 12: goto L24;
                case 13: goto L1e;
                case 14: goto L18;
                default: goto L12;
            }
        L12:
            java.lang.String r0 = "what"
            r1[r3] = r0
            goto L2d
        L18:
            java.lang.String r0 = "visibility"
            r1[r3] = r0
            goto L2d
        L1e:
            java.lang.String r0 = "second"
            r1[r3] = r0
            goto L2d
        L24:
            java.lang.String r0 = "first"
            r1[r3] = r0
            goto L2d
        L29:
            java.lang.String r0 = "from"
            r1[r3] = r0
        L2d:
            java.lang.String r0 = "kotlin/reflect/jvm/internal/impl/descriptors/Visibilities"
            r1[r2] = r0
            r0 = 2
            switch(r4) {
                case 2: goto L58;
                case 3: goto L58;
                case 4: goto L53;
                case 5: goto L53;
                case 6: goto L4e;
                case 7: goto L4e;
                case 8: goto L49;
                case 9: goto L49;
                case 10: goto L44;
                case 11: goto L44;
                case 12: goto L3f;
                case 13: goto L3f;
                case 14: goto L3a;
                default: goto L35;
            }
        L35:
            java.lang.String r4 = "isVisible"
            r1[r0] = r4
            goto L5c
        L3a:
            java.lang.String r4 = "isPrivate"
            r1[r0] = r4
            goto L5c
        L3f:
            java.lang.String r4 = "compare"
            r1[r0] = r4
            goto L5c
        L44:
            java.lang.String r4 = "compareLocal"
            r1[r0] = r4
            goto L5c
        L49:
            java.lang.String r4 = "findInvisibleMember"
            r1[r0] = r4
            goto L5c
        L4e:
            java.lang.String r4 = "inSameFile"
            r1[r0] = r4
            goto L5c
        L53:
            java.lang.String r4 = "isVisibleWithAnyReceiver"
            r1[r0] = r4
            goto L5c
        L58:
            java.lang.String r4 = "isVisibleIgnoringReceiver"
            r1[r0] = r4
        L5c:
            java.lang.String r4 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            java.lang.String r4 = java.lang.String.format(r4, r1)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.$$$reportNull$$$0(int):void");
    }

    static {
        Set of;
        of = SetsKt__SetsKt.setOf((Object[]) new Visibility[]{PRIVATE, PRIVATE_TO_THIS, INTERNAL, LOCAL});
        INVISIBLE_FROM_OTHER_MODULES = Collections.unmodifiableSet(of);
        HashMap newHashMapWithExpectedSize = CollectionsKt.newHashMapWithExpectedSize(4);
        newHashMapWithExpectedSize.put(PRIVATE_TO_THIS, 0);
        newHashMapWithExpectedSize.put(PRIVATE, 0);
        newHashMapWithExpectedSize.put(INTERNAL, 1);
        newHashMapWithExpectedSize.put(PROTECTED, 1);
        newHashMapWithExpectedSize.put(PUBLIC, 2);
        ORDERED_VISIBILITIES = Collections.unmodifiableMap(newHashMapWithExpectedSize);
        DEFAULT_VISIBILITY = PUBLIC;
        IRRELEVANT_RECEIVER = new ReceiverValue() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.10
            @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
            @NotNull
            /* renamed from: getType */
            public KotlinType mo12036getType() {
                throw new IllegalStateException("This method should not be called");
            }
        };
        ALWAYS_SUITABLE_RECEIVER = new ReceiverValue() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.11
            @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
            @NotNull
            /* renamed from: getType */
            public KotlinType mo12036getType() {
                throw new IllegalStateException("This method should not be called");
            }
        };
        FALSE_IF_PROTECTED = new ReceiverValue() { // from class: kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.12
            @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue
            @NotNull
            /* renamed from: getType */
            public KotlinType mo12036getType() {
                throw new IllegalStateException("This method should not be called");
            }
        };
        Iterator it2 = ServiceLoader.load(ModuleVisibilityHelper.class, ModuleVisibilityHelper.class.getClassLoader()).iterator();
        MODULE_VISIBILITY_HELPER = it2.hasNext() ? (ModuleVisibilityHelper) it2.next() : ModuleVisibilityHelper.EMPTY.INSTANCE;
    }

    @Nullable
    public static Integer compare(@NotNull Visibility visibility, @NotNull Visibility visibility2) {
        if (visibility == null) {
            $$$reportNull$$$0(12);
        }
        if (visibility2 == null) {
            $$$reportNull$$$0(13);
        }
        Integer compareTo = visibility.compareTo(visibility2);
        if (compareTo != null) {
            return compareTo;
        }
        Integer compareTo2 = visibility2.compareTo(visibility);
        if (compareTo2 == null) {
            return null;
        }
        return Integer.valueOf(-compareTo2.intValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static Integer compareLocal(@NotNull Visibility visibility, @NotNull Visibility visibility2) {
        if (visibility == null) {
            $$$reportNull$$$0(10);
        }
        if (visibility2 == null) {
            $$$reportNull$$$0(11);
        }
        if (visibility == visibility2) {
            return 0;
        }
        Integer num = ORDERED_VISIBILITIES.get(visibility);
        Integer num2 = ORDERED_VISIBILITIES.get(visibility2);
        if (num != null && num2 != null && !num.equals(num2)) {
            return Integer.valueOf(num.intValue() - num2.intValue());
        }
        return null;
    }

    @Nullable
    public static DeclarationDescriptorWithVisibility findInvisibleMember(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
        DeclarationDescriptorWithVisibility findInvisibleMember;
        if (declarationDescriptorWithVisibility == null) {
            $$$reportNull$$$0(8);
        }
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(9);
        }
        for (DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility2 = (DeclarationDescriptorWithVisibility) declarationDescriptorWithVisibility.mo11613getOriginal(); declarationDescriptorWithVisibility2 != null && declarationDescriptorWithVisibility2.getVisibility() != LOCAL; declarationDescriptorWithVisibility2 = (DeclarationDescriptorWithVisibility) DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility2, DeclarationDescriptorWithVisibility.class)) {
            if (!declarationDescriptorWithVisibility2.getVisibility().isVisible(receiverValue, declarationDescriptorWithVisibility2, declarationDescriptor)) {
                return declarationDescriptorWithVisibility2;
            }
        }
        if (!(declarationDescriptorWithVisibility instanceof TypeAliasConstructorDescriptor) || (findInvisibleMember = findInvisibleMember(receiverValue, ((TypeAliasConstructorDescriptor) declarationDescriptorWithVisibility).getUnderlyingConstructorDescriptor(), declarationDescriptor)) == null) {
            return null;
        }
        return findInvisibleMember;
    }

    public static boolean inSameFile(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull DeclarationDescriptor declarationDescriptor2) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(6);
        }
        if (declarationDescriptor2 == null) {
            $$$reportNull$$$0(7);
        }
        SourceFile containingSourceFile = DescriptorUtils.getContainingSourceFile(declarationDescriptor2);
        if (containingSourceFile != SourceFile.NO_SOURCE_FILE) {
            return containingSourceFile.equals(DescriptorUtils.getContainingSourceFile(declarationDescriptor));
        }
        return false;
    }

    public static boolean isPrivate(@NotNull Visibility visibility) {
        if (visibility == null) {
            $$$reportNull$$$0(14);
        }
        return visibility == PRIVATE || visibility == PRIVATE_TO_THIS;
    }

    public static boolean isVisibleIgnoringReceiver(@NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptorWithVisibility == null) {
            $$$reportNull$$$0(2);
        }
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(3);
        }
        return findInvisibleMember(ALWAYS_SUITABLE_RECEIVER, declarationDescriptorWithVisibility, declarationDescriptor) == null;
    }
}
