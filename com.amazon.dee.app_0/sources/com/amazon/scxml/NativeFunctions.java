package com.amazon.scxml;

import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NativeFunctions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B+\u0012$\u0010\u0004\u001a \u0012\u0004\u0012\u00020\u0006\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0004\u0012\u00028\u00010\u00070\u0005¢\u0006\u0002\u0010\tJ'\u0010\n\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000b\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0011R,\u0010\u0004\u001a \u0012\u0004\u0012\u00020\u0006\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0004\u0012\u00028\u00010\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/scxml/NativeFunctions;", "TUserContext", "TResult", "Lcom/amazon/scxml/logging/Loggable;", "functions", "", "", "Lkotlin/Function1;", "Lcom/amazon/scxml/SCXMLContext;", "(Ljava/util/Map;)V", "execute", "name", "context", "execute$AlexaMobileAndroidVoice_TTA_release", "(Ljava/lang/String;Lcom/amazon/scxml/SCXMLContext;)Ljava/lang/Object;", "functionExists", "", "functionExists$AlexaMobileAndroidVoice_TTA_release", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class NativeFunctions<TUserContext, TResult> extends Loggable {
    private final Map<String, Function1<SCXMLContext<TUserContext>, TResult>> functions;

    public NativeFunctions(@NotNull Map<String, ? extends Function1<? super SCXMLContext<TUserContext>, ? extends TResult>> functions) {
        Map<String, Function1<SCXMLContext<TUserContext>, TResult>> map;
        Intrinsics.checkParameterIsNotNull(functions, "functions");
        map = MapsKt__MapsKt.toMap(functions);
        this.functions = map;
    }

    @Nullable
    public final TResult execute$AlexaMobileAndroidVoice_TTA_release(@NotNull String name, @NotNull SCXMLContext<TUserContext> context) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.d(tag, "executing [" + name + "] with context: [" + context + JsonReaderKt.END_LIST);
        Function1<SCXMLContext<TUserContext>, TResult> function1 = this.functions.get(name);
        if (function1 != null) {
            return function1.mo12165invoke(context);
        }
        return null;
    }

    public final boolean functionExists$AlexaMobileAndroidVoice_TTA_release(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return this.functions.containsKey(name);
    }
}
