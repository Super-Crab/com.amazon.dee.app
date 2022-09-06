package kotlin.reflect.jvm.internal.impl.load.java;

import com.amazon.appmanager.lib.DefaultPreloadManager;
import com.amazon.device.messaging.ADMConstants;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public class JavaVisibilities {
    @NotNull
    public static final Visibility PACKAGE_VISIBILITY = new Visibility("package", false) { // from class: kotlin.reflect.jvm.internal.impl.load.java.JavaVisibilities.1
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = (i == 3 || i == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
            Object[] objArr = new Object[(i == 3 || i == 5) ? 2 : 3];
            if (i == 1) {
                objArr[0] = ADMConstants.EXTRA_FROM;
            } else if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        objArr[0] = "classDescriptor";
                    } else if (i != 5) {
                        objArr[0] = "what";
                    }
                }
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/JavaVisibilities$1";
            } else {
                objArr[0] = "visibility";
            }
            if (i == 3) {
                objArr[1] = "normalize";
            } else if (i != 5) {
                objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaVisibilities$1";
            } else {
                objArr[1] = "effectiveVisibility";
            }
            if (i == 2) {
                objArr[2] = "compareTo";
            } else if (i != 3) {
                if (i == 4) {
                    objArr[2] = "effectiveVisibility";
                } else if (i != 5) {
                    objArr[2] = "isVisible";
                }
            }
            String format = String.format(str, objArr);
            if (i == 3 || i == 5) {
                throw new IllegalStateException(format);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public Integer compareTo(@NotNull Visibility visibility) {
            if (visibility == null) {
                $$$reportNull$$$0(2);
            }
            if (this == visibility) {
                return 0;
            }
            return Visibilities.isPrivate(visibility) ? 1 : -1;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        @NotNull
        public String getInternalDisplayName() {
            return "public/*package*/";
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptorWithVisibility == null) {
                $$$reportNull$$$0(0);
            }
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(1);
            }
            return JavaVisibilities.areInSamePackage(declarationDescriptorWithVisibility, declarationDescriptor);
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        @NotNull
        public Visibility normalize() {
            Visibility visibility = Visibilities.PROTECTED;
            if (visibility == null) {
                $$$reportNull$$$0(3);
            }
            return visibility;
        }
    };
    @NotNull
    public static final Visibility PROTECTED_STATIC_VISIBILITY = new Visibility("protected_static", true) { // from class: kotlin.reflect.jvm.internal.impl.load.java.JavaVisibilities.2
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 2 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 2 ? 3 : 2];
            if (i == 1) {
                objArr[0] = ADMConstants.EXTRA_FROM;
            } else if (i != 2) {
                objArr[0] = "what";
            } else {
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/JavaVisibilities$2";
            }
            if (i != 2) {
                objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaVisibilities$2";
            } else {
                objArr[1] = "normalize";
            }
            if (i != 2) {
                objArr[2] = "isVisible";
            }
            String format = String.format(str, objArr);
            if (i != 2) {
                throw new IllegalArgumentException(format);
            }
            throw new IllegalStateException(format);
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        @NotNull
        public String getInternalDisplayName() {
            return "protected/*protected static*/";
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptorWithVisibility == null) {
                $$$reportNull$$$0(0);
            }
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(1);
            }
            return JavaVisibilities.isVisibleForProtectedAndPackage(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor);
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        @NotNull
        public Visibility normalize() {
            Visibility visibility = Visibilities.PROTECTED;
            if (visibility == null) {
                $$$reportNull$$$0(2);
            }
            return visibility;
        }
    };
    @NotNull
    public static final Visibility PROTECTED_AND_PACKAGE = new Visibility("protected_and_package", true) { // from class: kotlin.reflect.jvm.internal.impl.load.java.JavaVisibilities.3
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 3 ? 3 : 2];
            if (i == 1) {
                objArr[0] = ADMConstants.EXTRA_FROM;
            } else if (i == 2) {
                objArr[0] = "visibility";
            } else if (i != 3) {
                objArr[0] = "what";
            } else {
                objArr[0] = "kotlin/reflect/jvm/internal/impl/load/java/JavaVisibilities$3";
            }
            if (i != 3) {
                objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaVisibilities$3";
            } else {
                objArr[1] = "normalize";
            }
            if (i == 2) {
                objArr[2] = "compareTo";
            } else if (i != 3) {
                objArr[2] = "isVisible";
            }
            String format = String.format(str, objArr);
            if (i != 3) {
                throw new IllegalArgumentException(format);
            }
            throw new IllegalStateException(format);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public Integer compareTo(@NotNull Visibility visibility) {
            if (visibility == null) {
                $$$reportNull$$$0(2);
            }
            if (this == visibility) {
                return 0;
            }
            if (visibility == Visibilities.INTERNAL) {
                return null;
            }
            return Visibilities.isPrivate(visibility) ? 1 : -1;
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        @NotNull
        public String getInternalDisplayName() {
            return "protected/*protected and package*/";
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        public boolean isVisible(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptorWithVisibility == null) {
                $$$reportNull$$$0(0);
            }
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(1);
            }
            return JavaVisibilities.isVisibleForProtectedAndPackage(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor);
        }

        @Override // kotlin.reflect.jvm.internal.impl.descriptors.Visibility
        @NotNull
        public Visibility normalize() {
            Visibility visibility = Visibilities.PROTECTED;
            if (visibility == null) {
                $$$reportNull$$$0(3);
            }
            return visibility;
        }
    };

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = ADMConstants.EXTRA_FROM;
        } else if (i == 2) {
            objArr[0] = DefaultPreloadManager.METRIC_PATH_FIRST;
        } else if (i != 3) {
            objArr[0] = "what";
        } else {
            objArr[0] = "second";
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/JavaVisibilities";
        if (i == 2 || i == 3) {
            objArr[2] = "areInSamePackage";
        } else {
            objArr[2] = "isVisibleForProtectedAndPackage";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean areInSamePackage(@NotNull DeclarationDescriptor declarationDescriptor, @NotNull DeclarationDescriptor declarationDescriptor2) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(2);
        }
        if (declarationDescriptor2 == null) {
            $$$reportNull$$$0(3);
        }
        PackageFragmentDescriptor packageFragmentDescriptor = (PackageFragmentDescriptor) DescriptorUtils.getParentOfType(declarationDescriptor, PackageFragmentDescriptor.class, false);
        PackageFragmentDescriptor packageFragmentDescriptor2 = (PackageFragmentDescriptor) DescriptorUtils.getParentOfType(declarationDescriptor2, PackageFragmentDescriptor.class, false);
        return (packageFragmentDescriptor2 == null || packageFragmentDescriptor == null || !packageFragmentDescriptor.getFqName().equals(packageFragmentDescriptor2.getFqName())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isVisibleForProtectedAndPackage(@Nullable ReceiverValue receiverValue, @NotNull DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, @NotNull DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptorWithVisibility == null) {
            $$$reportNull$$$0(0);
        }
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(1);
        }
        if (areInSamePackage(DescriptorUtils.unwrapFakeOverrideToAnyDeclaration(declarationDescriptorWithVisibility), declarationDescriptor)) {
            return true;
        }
        return Visibilities.PROTECTED.isVisible(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor);
    }
}
