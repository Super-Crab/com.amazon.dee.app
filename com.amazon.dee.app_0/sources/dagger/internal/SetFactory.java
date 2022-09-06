package dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;
/* loaded from: classes3.dex */
public final class SetFactory<T> implements Factory<Set<T>> {
    private static final Factory<Set<Object>> EMPTY_FACTORY = InstanceFactory.create(Collections.emptySet());
    private final List<Provider<Collection<T>>> collectionProviders;
    private final List<Provider<T>> individualProviders;

    /* loaded from: classes3.dex */
    public static final class Builder<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final List<Provider<Collection<T>>> collectionProviders;
        private final List<Provider<T>> individualProviders;

        public Builder<T> addCollectionProvider(Provider<? extends Collection<? extends T>> collectionProvider) {
            this.collectionProviders.add(collectionProvider);
            return this;
        }

        public Builder<T> addProvider(Provider<? extends T> individualProvider) {
            this.individualProviders.add(individualProvider);
            return this;
        }

        public SetFactory<T> build() {
            return new SetFactory<>(this.individualProviders, this.collectionProviders);
        }

        private Builder(int individualProviderSize, int collectionProviderSize) {
            this.individualProviders = DaggerCollections.presizedList(individualProviderSize);
            this.collectionProviders = DaggerCollections.presizedList(collectionProviderSize);
        }
    }

    public static <T> Builder<T> builder(int individualProviderSize, int collectionProviderSize) {
        return new Builder<>(individualProviderSize, collectionProviderSize);
    }

    public static <T> Factory<Set<T>> empty() {
        return (Factory<Set<T>>) EMPTY_FACTORY;
    }

    private SetFactory(List<Provider<T>> individualProviders, List<Provider<Collection<T>>> collectionProviders) {
        this.individualProviders = individualProviders;
        this.collectionProviders = collectionProviders;
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public Set<T> mo10268get() {
        int size = this.individualProviders.size();
        ArrayList arrayList = new ArrayList(this.collectionProviders.size());
        int size2 = this.collectionProviders.size();
        int i = size;
        for (int i2 = 0; i2 < size2; i2++) {
            Collection<T> mo10268get = this.collectionProviders.get(i2).mo10268get();
            i += mo10268get.size();
            arrayList.add(mo10268get);
        }
        HashSet newHashSetWithExpectedSize = DaggerCollections.newHashSetWithExpectedSize(i);
        int size3 = this.individualProviders.size();
        for (int i3 = 0; i3 < size3; i3++) {
            newHashSetWithExpectedSize.add(Preconditions.checkNotNull(this.individualProviders.get(i3).mo10268get()));
        }
        int size4 = arrayList.size();
        for (int i4 = 0; i4 < size4; i4++) {
            for (Object obj : (Collection) arrayList.get(i4)) {
                newHashSetWithExpectedSize.add(Preconditions.checkNotNull(obj));
            }
        }
        return Collections.unmodifiableSet(newHashSetWithExpectedSize);
    }
}
