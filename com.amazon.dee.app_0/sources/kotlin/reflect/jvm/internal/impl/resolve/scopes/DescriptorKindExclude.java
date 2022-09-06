package kotlin.reflect.jvm.internal.impl.resolve.scopes;
/* compiled from: MemberScope.kt */
/* loaded from: classes4.dex */
public abstract class DescriptorKindExclude {

    /* compiled from: MemberScope.kt */
    /* loaded from: classes4.dex */
    public static final class NonExtensions extends DescriptorKindExclude {
        public static final NonExtensions INSTANCE = new NonExtensions();
        private static final int fullyExcludedDescriptorKinds = DescriptorKindFilter.Companion.getALL_KINDS_MASK() & (~(DescriptorKindFilter.Companion.getFUNCTIONS_MASK() | DescriptorKindFilter.Companion.getVARIABLES_MASK()));

        private NonExtensions() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindExclude
        public int getFullyExcludedDescriptorKinds() {
            return fullyExcludedDescriptorKinds;
        }
    }

    /* compiled from: MemberScope.kt */
    /* loaded from: classes4.dex */
    public static final class TopLevelPackages extends DescriptorKindExclude {
        public static final TopLevelPackages INSTANCE = new TopLevelPackages();

        private TopLevelPackages() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindExclude
        public int getFullyExcludedDescriptorKinds() {
            return 0;
        }
    }

    public abstract int getFullyExcludedDescriptorKinds();

    public String toString() {
        return getClass().getSimpleName();
    }
}
