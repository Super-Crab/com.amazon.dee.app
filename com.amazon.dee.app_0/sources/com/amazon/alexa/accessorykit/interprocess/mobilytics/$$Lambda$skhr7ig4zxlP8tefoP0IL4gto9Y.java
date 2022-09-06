package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import android.util.Pair;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessorykit.interprocess.identity.Person;
import io.reactivex.rxjava3.functions.BiFunction;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.mobilytics.-$$Lambda$skhr7ig4zxlP8tefoP0IL4gto9Y  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$skhr7ig4zxlP8tefoP0IL4gto9Y implements BiFunction {
    public static final /* synthetic */ $$Lambda$skhr7ig4zxlP8tefoP0IL4gto9Y INSTANCE = new $$Lambda$skhr7ig4zxlP8tefoP0IL4gto9Y();

    private /* synthetic */ $$Lambda$skhr7ig4zxlP8tefoP0IL4gto9Y() {
    }

    @Override // io.reactivex.rxjava3.functions.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return new Pair((User) obj, (Person) obj2);
    }
}
