package com.amazon.alexa.fitness.model.device;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt__SetsKt;
import org.jetbrains.annotations.NotNull;
/* JADX WARN: Init of enum ARMSTRONG can be incorrect */
/* JADX WARN: Init of enum ARROWHEAD can be incorrect */
/* JADX WARN: Init of enum ELLINGTON can be incorrect */
/* compiled from: DeviceType.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0015\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/model/device/DeviceType;", "", "identifiers", "", "", "(Ljava/lang/String;ILjava/util/Set;)V", "getIdentifiers", "()Ljava/util/Set;", "ELLINGTON", "ARMSTRONG", "ARROWHEAD", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public enum DeviceType {
    ELLINGTON(r2),
    ARMSTRONG(r2),
    ARROWHEAD(r2);
    
    @NotNull
    private final Set<String> identifiers;

    static {
        SetsKt__SetsKt.setOf((Object[]) new String[]{"A16MZVIFVHX6P6", "AS8OPU4NNXJI8", "ALWUIN0P635PT", "AE9FIEPOC6D9B"});
        SetsKt__SetsKt.setOf((Object[]) new String[]{"A15QWUTQ6FSMYX", "A3HVREY4JWAZ6K", "A2QDHDQIWC2LTG", "A31PMVIWCRNTX2"});
        SetsKt__SetsKt.setOf((Object[]) new String[]{"ADXK6Q246T9EA", "A21YKVRGQV9TET", "A168KS6Z8QG6RV", "A3KF60B9RDMWLY"});
    }

    DeviceType(Set set) {
        this.identifiers = set;
    }

    @NotNull
    public final Set<String> getIdentifiers() {
        return this.identifiers;
    }
}
