package com.amazon.tarazed.core.logging;

import com.amazonaws.services.logs.model.InputLogEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedLogParser.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u00060\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/tarazed/core/logging/TarazedLogParser;", "", "appender", "Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender;", "(Lcom/amazon/tarazed/core/logging/TarazedLogFileAppender;)V", "parseLogEvents", "", "Lcom/amazonaws/services/logs/model/InputLogEvent;", "Lcom/amazon/tarazed/core/logging/api/InputLogEvent;", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedLogParser {
    public static final Companion Companion = new Companion(null);
    public static final int MAX_LOG_AGE_MILLIS = 259200000;
    public static final int ONE_DAY_IN_MILLIS = 86400000;
    private final TarazedLogFileAppender appender;

    /* compiled from: TarazedLogParser.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/logging/TarazedLogParser$Companion;", "", "()V", "MAX_LOG_AGE_MILLIS", "", "ONE_DAY_IN_MILLIS", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedLogParser(@NotNull TarazedLogFileAppender appender) {
        Intrinsics.checkParameterIsNotNull(appender, "appender");
        this.appender = appender;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, java.util.ArrayList] */
    @NotNull
    public final Collection<Collection<InputLogEvent>> parseLogEvents() {
        ArrayList arrayList = new ArrayList();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = 0L;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance()");
        this.appender.forEachLine(new TarazedLogParser$parseLogEvents$1(calendar.getTimeInMillis() - ((long) MAX_LOG_AGE_MILLIS), longRef, arrayList, objectRef));
        arrayList.add((List) objectRef.element);
        return arrayList;
    }
}
