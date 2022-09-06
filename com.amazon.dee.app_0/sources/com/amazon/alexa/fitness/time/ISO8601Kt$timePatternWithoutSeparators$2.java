package com.amazon.alexa.fitness.time;

import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: ISO8601.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class ISO8601Kt$timePatternWithoutSeparators$2 extends Lambda implements Function0<Pattern> {
    public static final ISO8601Kt$timePatternWithoutSeparators$2 INSTANCE = new ISO8601Kt$timePatternWithoutSeparators$2();

    ISO8601Kt$timePatternWithoutSeparators$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public final Pattern mo12560invoke() {
        return Pattern.compile("(\\d\\d)((\\d\\d)((\\d\\d)([.](\\d\\d\\d))?)?)?((Z)|([+-]\\d\\d(\\d\\d)?))?");
    }
}
