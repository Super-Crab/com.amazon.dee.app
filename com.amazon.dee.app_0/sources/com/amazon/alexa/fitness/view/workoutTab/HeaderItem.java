package com.amazon.alexa.fitness.view.workoutTab;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessViewHolders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/HeaderItem;", "Lcom/amazon/alexa/fitness/view/workoutTab/WorkoutItem;", "text", "", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "setText", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class HeaderItem extends WorkoutItem {
    @NotNull
    private String text;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeaderItem(@NotNull String text) {
        super(FitnessListType.HEADER);
        Intrinsics.checkParameterIsNotNull(text, "text");
        this.text = text;
    }

    @NotNull
    public final String getText() {
        return this.text;
    }

    public final void setText(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.text = str;
    }
}
