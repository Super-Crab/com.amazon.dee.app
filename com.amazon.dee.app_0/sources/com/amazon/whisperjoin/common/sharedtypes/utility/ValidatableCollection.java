package com.amazon.whisperjoin.common.sharedtypes.utility;

import com.amazon.whisperjoin.common.sharedtypes.utility.Validatable;
import java.util.Collection;
/* loaded from: classes13.dex */
public class ValidatableCollection<Type extends Validatable> implements Validatable {
    private Collection<Type> mCollection;

    public ValidatableCollection(Collection<Type> collection) {
        this.mCollection = collection;
    }

    public Collection<Type> getCollection() {
        return this.mCollection;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.utility.Validatable
    public void validate() {
        for (Type type : this.mCollection) {
            type.validate();
        }
    }
}
