package com.amazon.alexa.api.compat.alerts;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlertType.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/api/compat/alerts/AlertType;", "", "(Ljava/lang/String;I)V", "ALARM", "TIMER", "REMINDER", "Companion", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum AlertType {
    ALARM,
    TIMER,
    REMINDER;
    
    public static final Companion Companion = new Companion(null);

    /* compiled from: AlertType.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/api/compat/alerts/AlertType$Companion;", "", "()V", "convertToCompat", "Lcom/amazon/alexa/api/compat/alerts/AlertType;", "type", "Lcom/amazon/alexa/api/AlertType;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[com.amazon.alexa.api.AlertType.values().length];

            static {
                $EnumSwitchMapping$0[com.amazon.alexa.api.AlertType.REMINDER.ordinal()] = 1;
                $EnumSwitchMapping$0[com.amazon.alexa.api.AlertType.ALARM.ordinal()] = 2;
                $EnumSwitchMapping$0[com.amazon.alexa.api.AlertType.TIMER.ordinal()] = 3;
            }
        }

        private Companion() {
        }

        @NotNull
        public final AlertType convertToCompat(@NotNull com.amazon.alexa.api.AlertType type) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    return AlertType.ALARM;
                }
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                return AlertType.TIMER;
            }
            return AlertType.REMINDER;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
