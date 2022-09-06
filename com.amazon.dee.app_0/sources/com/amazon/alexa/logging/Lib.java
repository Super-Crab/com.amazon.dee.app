package com.amazon.alexa.logging;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;
/* compiled from: Lib.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0002"}, d2 = {"Log", "Lcom/amazon/alexa/logging/Logger;", "AlexaMobileAndroidLogging_release"}, k = 2, mv = {1, 1, 10})
@JvmName(name = "Lib")
/* loaded from: classes9.dex */
public final class Lib {
    @JvmField
    @NotNull
    public static final Logger Log;

    static {
        List listOf;
        Level level = Level.INFO;
        listOf = CollectionsKt__CollectionsJVMKt.listOf(new ConsoleLogHandler());
        Log = new AlexaLogger(level, listOf);
    }
}
