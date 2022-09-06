package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionRecoveryManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&Jb\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0006\u0012\u0004\u0018\u00010\n0\tj\u0002`\u000b2<\u0010\f\u001a8\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00050\rj\u0002`\u0013H&JE\u0010\u0014\u001a\u00020\u00052\u0012\u0010\u0015\u001a\u000e\u0012\u0006\u0012\u0004\u0018\u00010\n0\tj\u0002`\u00162'\u0010\u0017\u001a#\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0018j\u0002`\u0019H&J\b\u0010\u001a\u001a\u00020\u0005H&J\b\u0010\u001b\u001a\u00020\u0005H&¨\u0006\u001c"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/SessionRecoveryManager;", "", "recoverFitnessSession", "Lcom/amazon/alexa/fitness/sdk/SessionRecoveryResult;", "registerComponentRecovery", "", "recoverableId", "", "encodeRecoverableState", "Lkotlin/Function0;", "", "Lcom/amazon/alexa/fitness/sdk/encodeRecoverableComponentState;", "decodeRecoverableState", "Lkotlin/Function2;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "Lkotlin/ParameterName;", "name", "session", "recoverableState", "Lcom/amazon/alexa/fitness/sdk/decodeRecoverableComponentState;", "registerSessionRecovery", "encodeRecoverableSession", "Lcom/amazon/alexa/fitness/sdk/encodeRecoverableSession;", "decodeRecoverableSession", "Lkotlin/Function1;", "Lcom/amazon/alexa/fitness/sdk/decodeRecoverableSession;", "reset", "save", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface SessionRecoveryManager {
    @NotNull
    SessionRecoveryResult recoverFitnessSession();

    void registerComponentRecovery(@NotNull String str, @NotNull Function0<byte[]> function0, @NotNull Function2<? super Session, ? super byte[], Unit> function2);

    void registerSessionRecovery(@NotNull Function0<byte[]> function0, @NotNull Function1<? super byte[], Session> function1);

    void reset();

    void save();
}
