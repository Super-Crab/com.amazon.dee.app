package com.amazon.scxml;

import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SCXMLStateMachine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&JF\u0010\u0004\u001a\u00020\u00032<\u0010\u0005\u001a8\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\u0006H&J\u001a\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H&J\b\u0010\u0011\u001a\u00020\u0003H&J\u001a\u0010\u0012\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\u0014H&J\u0012\u0010\u0015\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\u0014H&¨\u0006\u0016"}, d2 = {"Lcom/amazon/scxml/SCXMLStateMachine;", "", "initialize", "", "insertBlock", "completion", "Lkotlin/Function2;", "", "", "Lkotlin/ParameterName;", "name", "activeStates", "", "isValid", "sendEvent", JsonFields.EVENT_NAME, "eventData", "start", "waitForEvent", "timeoutMilliseconds", "", "waitUntilProcessed", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface SCXMLStateMachine {

    /* compiled from: SCXMLStateMachine.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ boolean waitForEvent$default(SCXMLStateMachine sCXMLStateMachine, String str, long j, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    j = 1000;
                }
                return sCXMLStateMachine.waitForEvent(str, j);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: waitForEvent");
        }

        public static /* synthetic */ boolean waitUntilProcessed$default(SCXMLStateMachine sCXMLStateMachine, long j, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    j = 1000;
                }
                return sCXMLStateMachine.waitUntilProcessed(j);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: waitUntilProcessed");
        }
    }

    void initialize();

    void insertBlock(@NotNull Function2<? super List<String>, ? super Boolean, Unit> function2);

    void sendEvent(@NotNull String str, @Nullable Object obj);

    void start();

    boolean waitForEvent(@NotNull String str, long j);

    boolean waitUntilProcessed(long j);
}
