package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.lang3.SerializationUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "session", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class SessionManagerImpl$decodeSession$1 extends Lambda implements Function1<byte[], Session> {
    public static final SessionManagerImpl$decodeSession$1 INSTANCE = new SessionManagerImpl$decodeSession$1();

    SessionManagerImpl$decodeSession$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final Session mo12165invoke(@NotNull byte[] session) {
        Intrinsics.checkParameterIsNotNull(session, "session");
        try {
            Object deserialize = SerializationUtils.deserialize(session);
            if (deserialize == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.api.fitnessSdk.Session");
            }
            return (Session) deserialize;
        } catch (Exception unused) {
            return null;
        }
    }
}
