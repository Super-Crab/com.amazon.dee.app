package com.amazon.alexa.accessorykit.interprocess.identity;

import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes6.dex */
public interface PersonSupplier {
    Observable<Person> queryPerson();
}
