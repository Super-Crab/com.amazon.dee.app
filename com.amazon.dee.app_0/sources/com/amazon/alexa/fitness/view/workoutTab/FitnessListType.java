package com.amazon.alexa.fitness.view.workoutTab;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessViewHolders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/FitnessListType;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "LIST_ITEM", "HEADER", "WEEKLY_VIEW", "LOADING", "Companion", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public enum FitnessListType {
    LIST_ITEM(0),
    HEADER(1),
    WEEKLY_VIEW(2),
    LOADING(3);
    
    public static final Companion Companion = new Companion(null);
    private final int value;

    /* compiled from: FitnessViewHolders.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/FitnessListType$Companion;", "", "()V", "fromInt", "Lcom/amazon/alexa/fitness/view/workoutTab/FitnessListType;", "value", "", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final FitnessListType fromInt(int i) {
            FitnessListType[] values;
            for (FitnessListType fitnessListType : FitnessListType.values()) {
                if (fitnessListType.getValue() == i) {
                    return fitnessListType;
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    FitnessListType(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
