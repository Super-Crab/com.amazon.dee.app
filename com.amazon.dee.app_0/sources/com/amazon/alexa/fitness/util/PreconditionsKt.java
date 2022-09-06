package com.amazon.alexa.fitness.util;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessorKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Preconditions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a3\u0010\u0004\u001a\u0002H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u0006*\u0002H\u00052\u0006\u0010\u0007\u001a\u0002H\u00052\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000b\u001a\u001e\u0010\f\u001a\u00020\r*\u0004\u0018\u00010\r2\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¨\u0006\u000e"}, d2 = {"checkAfxThread", "", "currentThread", "Ljava/lang/Thread;", "assertGreaterThanOrEqualTo", ExifInterface.GPS_DIRECTION_TRUE, "", "other", "lazyMessage", "Lkotlin/Function0;", "", "(Ljava/lang/Number;Ljava/lang/Number;Lkotlin/jvm/functions/Function0;)Ljava/lang/Number;", "assertNotNullOrBlank", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class PreconditionsKt {
    @NotNull
    public static final <T extends Number> T assertGreaterThanOrEqualTo(@NotNull T assertGreaterThanOrEqualTo, @NotNull T other, @Nullable Function0<String> function0) throws AssertionException {
        String str;
        Intrinsics.checkParameterIsNotNull(assertGreaterThanOrEqualTo, "$this$assertGreaterThanOrEqualTo");
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (assertGreaterThanOrEqualTo.doubleValue() >= other.doubleValue()) {
            return assertGreaterThanOrEqualTo;
        }
        if (function0 == null || (str = function0.mo12560invoke()) == null) {
            str = "Value " + assertGreaterThanOrEqualTo + " must be greater than or equal to " + other;
        }
        throw new AssertionException(str);
    }

    public static /* synthetic */ Number assertGreaterThanOrEqualTo$default(Number number, Number number2, Function0 function0, int i, Object obj) throws AssertionException {
        if ((i & 2) != 0) {
            function0 = null;
        }
        return assertGreaterThanOrEqualTo(number, number2, function0);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x000e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0021  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.CharSequence assertNotNullOrBlank(@org.jetbrains.annotations.Nullable java.lang.CharSequence r1, @org.jetbrains.annotations.Nullable kotlin.jvm.functions.Function0<java.lang.String> r2) throws com.amazon.alexa.fitness.util.AssertionException {
        /*
            if (r1 == 0) goto Lb
            boolean r0 = kotlin.text.StringsKt.isBlank(r1)
            if (r0 == 0) goto L9
            goto Lb
        L9:
            r0 = 0
            goto Lc
        Lb:
            r0 = 1
        Lc:
            if (r0 == 0) goto L21
            com.amazon.alexa.fitness.util.AssertionException r1 = new com.amazon.alexa.fitness.util.AssertionException
            if (r2 == 0) goto L1b
            java.lang.Object r2 = r2.mo12560invoke()
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L1b
            goto L1d
        L1b:
            java.lang.String r2 = "Value must not be null or blank"
        L1d:
            r1.<init>(r2)
            throw r1
        L21:
            if (r1 != 0) goto L26
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L26:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.util.PreconditionsKt.assertNotNullOrBlank(java.lang.CharSequence, kotlin.jvm.functions.Function0):java.lang.CharSequence");
    }

    public static /* synthetic */ CharSequence assertNotNullOrBlank$default(CharSequence charSequence, Function0 function0, int i, Object obj) throws AssertionException {
        if ((i & 1) != 0) {
            function0 = null;
        }
        return assertNotNullOrBlank(charSequence, function0);
    }

    public static final boolean checkAfxThread(@NotNull Thread currentThread) {
        Intrinsics.checkParameterIsNotNull(currentThread, "currentThread");
        if (!(!Intrinsics.areEqual(currentThread.getName(), AfxMessageProcessorKt.AfxMessageProcessorThread))) {
            return true;
        }
        throw new AssertionException("Method should be called using AfxMessageProcessor only");
    }
}
