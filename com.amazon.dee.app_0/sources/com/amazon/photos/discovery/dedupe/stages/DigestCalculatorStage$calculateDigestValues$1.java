package com.amazon.photos.discovery.dedupe.stages;

import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: DigestCalculatorStage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"isJpeg", "", "fileExtension", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class DigestCalculatorStage$calculateDigestValues$1 extends Lambda implements Function1<String, Boolean> {
    public static final DigestCalculatorStage$calculateDigestValues$1 INSTANCE = new DigestCalculatorStage$calculateDigestValues$1();

    DigestCalculatorStage$calculateDigestValues$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(String str) {
        return Boolean.valueOf(invoke2(str));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@NotNull String fileExtension) {
        Set set;
        Intrinsics.checkParameterIsNotNull(fileExtension, "fileExtension");
        set = DigestCalculatorStageKt.EXTENSION_JPEG;
        Locale locale = Locale.US;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
        String lowerCase = fileExtension.toLowerCase(locale);
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
        return set.contains(lowerCase);
    }
}
