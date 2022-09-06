package com.amazon.alexa.accessory.capabilities.instrumentation;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.List;
/* loaded from: classes.dex */
public enum OutputConfiguration {
    UTF_8(0),
    HEXADECIMAL_TABLE(1);
    
    private final int offset;

    OutputConfiguration(int i) {
        this.offset = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$parse$0(int i, OutputConfiguration outputConfiguration) throws Throwable {
        return ((i >> outputConfiguration.offset) & 1) == 1;
    }

    public static List<OutputConfiguration> parse(final int i) {
        return (List) Observable.fromArray(values()).filter(new Predicate() { // from class: com.amazon.alexa.accessory.capabilities.instrumentation.-$$Lambda$OutputConfiguration$0zlpINN_8Ed2lviFoivGPT4epfs
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return OutputConfiguration.lambda$parse$0(i, (OutputConfiguration) obj);
            }
        }).toList().blockingGet();
    }
}
