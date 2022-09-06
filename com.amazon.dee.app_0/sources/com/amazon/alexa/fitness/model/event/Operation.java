package com.amazon.alexa.fitness.model.event;

import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/fitness/model/event/Operation;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "IN_SESSION", "PAUSE", "RESUME", "START", "STOP", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public enum Operation {
    IN_SESSION("InSession"),
    PAUSE(EntertainmentMetrics.Button.PAUSE),
    RESUME("Resume"),
    START("Start"),
    STOP(PCCConstants.STOP_DIRECTIVE);
    
    @NotNull
    private final String value;

    Operation(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
