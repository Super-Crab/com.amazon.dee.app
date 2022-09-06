package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ResolutionScope.kt */
/* loaded from: classes4.dex */
public interface ResolutionScope {

    /* compiled from: ResolutionScope.kt */
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Collection getContributedDescriptors$default(ResolutionScope resolutionScope, DescriptorKindFilter descriptorKindFilter, Function1 function1, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    descriptorKindFilter = DescriptorKindFilter.ALL;
                }
                Function1<Name, Boolean> function12 = function1;
                if ((i & 2) != 0) {
                    function12 = MemberScope.Companion.getALL_NAME_FILTER();
                }
                return resolutionScope.mo12065getContributedDescriptors(descriptorKindFilter, function12);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getContributedDescriptors");
        }

        public static void recordLookup(ResolutionScope resolutionScope, @NotNull Name name, @NotNull LookupLocation location) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(location, "location");
            resolutionScope.mo12099getContributedFunctions(name, location);
        }
    }

    @Nullable
    /* renamed from: getContributedClassifier */
    ClassifierDescriptor mo12030getContributedClassifier(@NotNull Name name, @NotNull LookupLocation lookupLocation);

    @NotNull
    /* renamed from: getContributedDescriptors */
    Collection<DeclarationDescriptor> mo12065getContributedDescriptors(@NotNull DescriptorKindFilter descriptorKindFilter, @NotNull Function1<? super Name, Boolean> function1);

    @NotNull
    /* renamed from: getContributedFunctions */
    Collection<? extends FunctionDescriptor> mo12099getContributedFunctions(@NotNull Name name, @NotNull LookupLocation lookupLocation);
}
