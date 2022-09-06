package kotlin.reflect.jvm.internal.impl.types.error;

import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public class ErrorSimpleFunctionDescriptorImpl extends SimpleFunctionDescriptorImpl {
    private final ErrorUtils.ErrorScope ownerScope;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 6 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 6 || i == 7) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "ownerScope";
                break;
            case 2:
                objArr[0] = "newOwner";
                break;
            case 3:
                objArr[0] = ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME;
                break;
            case 4:
                objArr[0] = "annotations";
                break;
            case 5:
                objArr[0] = "source";
                break;
            case 6:
            case 7:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/types/error/ErrorSimpleFunctionDescriptorImpl";
                break;
            case 8:
                objArr[0] = "overriddenDescriptors";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i == 6) {
            objArr[1] = "createSubstitutedCopy";
        } else if (i != 7) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/types/error/ErrorSimpleFunctionDescriptorImpl";
        } else {
            objArr[1] = "copy";
        }
        switch (i) {
            case 2:
            case 3:
            case 4:
            case 5:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 6:
            case 7:
                break;
            case 8:
                objArr[2] = "setOverriddenDescriptors";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (i == 6 || i == 7) {
            throw new IllegalStateException(format);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ErrorSimpleFunctionDescriptorImpl(@NotNull ClassDescriptor classDescriptor, @NotNull ErrorUtils.ErrorScope errorScope) {
        super(classDescriptor, null, Annotations.Companion.getEMPTY(), Name.special("<ERROR FUNCTION>"), CallableMemberDescriptor.Kind.DECLARATION, SourceElement.NO_SOURCE);
        if (classDescriptor == null) {
            $$$reportNull$$$0(0);
        }
        if (errorScope == null) {
            $$$reportNull$$$0(1);
        }
        this.ownerScope = errorScope;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    @NotNull
    /* renamed from: copy */
    public SimpleFunctionDescriptor mo12143copy(DeclarationDescriptor declarationDescriptor, Modality modality, Visibility visibility, CallableMemberDescriptor.Kind kind, boolean z) {
        return this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl
    @NotNull
    /* renamed from: createSubstitutedCopy */
    protected FunctionDescriptorImpl mo12045createSubstitutedCopy(@NotNull DeclarationDescriptor declarationDescriptor, @Nullable FunctionDescriptor functionDescriptor, @NotNull CallableMemberDescriptor.Kind kind, @Nullable Name name, @NotNull Annotations annotations, @NotNull SourceElement sourceElement) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(2);
        }
        if (kind == null) {
            $$$reportNull$$$0(3);
        }
        if (annotations == null) {
            $$$reportNull$$$0(4);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(5);
        }
        return this;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public <V> V getUserData(CallableDescriptor.UserDataKey<V> userDataKey) {
        return null;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isSuspend() {
        return false;
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    @NotNull
    public FunctionDescriptor.CopyBuilder<? extends SimpleFunctionDescriptor> newCopyBuilder() {
        return new FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor>() { // from class: kotlin.reflect.jvm.internal.impl.types.error.ErrorSimpleFunctionDescriptorImpl.1
            /* JADX WARN: Removed duplicated region for block: B:104:0x0161  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x004c  */
            /* JADX WARN: Removed duplicated region for block: B:34:0x0058  */
            /* JADX WARN: Removed duplicated region for block: B:35:0x005d  */
            /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
            /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
            /* JADX WARN: Removed duplicated region for block: B:38:0x006c  */
            /* JADX WARN: Removed duplicated region for block: B:39:0x0071  */
            /* JADX WARN: Removed duplicated region for block: B:40:0x0076  */
            /* JADX WARN: Removed duplicated region for block: B:41:0x007b  */
            /* JADX WARN: Removed duplicated region for block: B:42:0x0080  */
            /* JADX WARN: Removed duplicated region for block: B:43:0x0085  */
            /* JADX WARN: Removed duplicated region for block: B:44:0x008a  */
            /* JADX WARN: Removed duplicated region for block: B:47:0x0090  */
            /* JADX WARN: Removed duplicated region for block: B:78:0x010b  */
            /* JADX WARN: Removed duplicated region for block: B:80:0x0110  */
            /* JADX WARN: Removed duplicated region for block: B:81:0x0113  */
            /* JADX WARN: Removed duplicated region for block: B:82:0x0118  */
            /* JADX WARN: Removed duplicated region for block: B:83:0x011d  */
            /* JADX WARN: Removed duplicated region for block: B:84:0x0122  */
            /* JADX WARN: Removed duplicated region for block: B:85:0x0127  */
            /* JADX WARN: Removed duplicated region for block: B:86:0x012c  */
            /* JADX WARN: Removed duplicated region for block: B:87:0x0131  */
            /* JADX WARN: Removed duplicated region for block: B:88:0x0136  */
            /* JADX WARN: Removed duplicated region for block: B:89:0x013b  */
            /* JADX WARN: Removed duplicated region for block: B:90:0x0140  */
            /* JADX WARN: Removed duplicated region for block: B:93:0x014a A[ADDED_TO_REGION] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            private static /* synthetic */ void $$$reportNull$$$0(int r18) {
                /*
                    Method dump skipped, instructions count: 582
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.error.ErrorSimpleFunctionDescriptorImpl.AnonymousClass1.$$$reportNull$$$0(int):void");
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setAdditionalAnnotations(@NotNull Annotations annotations) {
                if (annotations == null) {
                    $$$reportNull$$$0(29);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setCopyOverrides(boolean z) {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setDispatchReceiverParameter(@Nullable ReceiverParameterDescriptor receiverParameterDescriptor) {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setDropOriginalInContainingParts() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setExtensionReceiverParameter(@Nullable ReceiverParameterDescriptor receiverParameterDescriptor) {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setHiddenForResolutionEverywhereBesideSupercalls() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setHiddenToOvercomeSignatureClash() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setKind(@NotNull CallableMemberDescriptor.Kind kind) {
                if (kind == null) {
                    $$$reportNull$$$0(6);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setModality(@NotNull Modality modality) {
                if (modality == null) {
                    $$$reportNull$$$0(2);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setName(@NotNull Name name) {
                if (name == null) {
                    $$$reportNull$$$0(9);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setOriginal(@Nullable CallableMemberDescriptor callableMemberDescriptor) {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setOwner(@NotNull DeclarationDescriptor declarationDescriptor) {
                if (declarationDescriptor == null) {
                    $$$reportNull$$$0(0);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setPreserveSourceElement() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setReturnType(@NotNull KotlinType kotlinType) {
                if (kotlinType == null) {
                    $$$reportNull$$$0(19);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setSignatureChange() {
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setSubstitution(@NotNull TypeSubstitution typeSubstitution) {
                if (typeSubstitution == null) {
                    $$$reportNull$$$0(13);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setTypeParameters(@NotNull List<TypeParameterDescriptor> list) {
                if (list == null) {
                    $$$reportNull$$$0(17);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setValueParameters(@NotNull List<ValueParameterDescriptor> list) {
                if (list == null) {
                    $$$reportNull$$$0(11);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @NotNull
            public FunctionDescriptor.CopyBuilder<SimpleFunctionDescriptor> setVisibility(@NotNull Visibility visibility) {
                if (visibility == null) {
                    $$$reportNull$$$0(4);
                }
                return this;
            }

            @Override // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder
            @Nullable
            public SimpleFunctionDescriptor build() {
                return ErrorSimpleFunctionDescriptorImpl.this;
            }
        };
    }

    @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public void setOverriddenDescriptors(@NotNull Collection<? extends CallableMemberDescriptor> collection) {
        if (collection == null) {
            $$$reportNull$$$0(8);
        }
    }
}
