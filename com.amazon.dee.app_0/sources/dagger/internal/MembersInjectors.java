package dagger.internal;

import dagger.MembersInjector;
/* loaded from: classes3.dex */
public final class MembersInjectors {

    /* loaded from: classes3.dex */
    private enum NoOpMembersInjector implements MembersInjector<Object> {
        INSTANCE;

        @Override // dagger.MembersInjector
        public void injectMembers(Object instance) {
            Preconditions.checkNotNull(instance, "Cannot inject members into a null reference");
        }
    }

    private MembersInjectors() {
    }

    public static <T> MembersInjector<T> noOp() {
        return NoOpMembersInjector.INSTANCE;
    }
}
