package com.amazon.wellness.io.format.abs;

import java.io.InputStream;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AbsFitnessDataParserImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/wellness/io/format/abs/AbsFitnessDataParserImpl;", "Lcom/amazon/wellness/io/format/abs/FitnessDataParser;", "()V", "parse", "Lcom/amazon/wellness/io/format/abs/AlexaBiometricData;", "inputStream", "Ljava/io/InputStream;", "AlexaBiometricSampleEncoderAndroid_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AbsFitnessDataParserImpl implements FitnessDataParser {
    @Override // com.amazon.wellness.io.format.abs.FitnessDataParser
    @NotNull
    public AlexaBiometricData parse(@NotNull InputStream inputStream) {
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        try {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            if (Header.parseDelimitedFrom(inputStream) != null) {
                Event parseDelimitedFrom = Event.parseDelimitedFrom(inputStream);
                while (parseDelimitedFrom != null) {
                    linkedHashSet.add(parseDelimitedFrom);
                    parseDelimitedFrom = Event.parseDelimitedFrom(inputStream);
                }
            }
            AlexaBiometricData alexaBiometricData = new AlexaBiometricData(linkedHashSet);
            CloseableKt.closeFinally(inputStream, null);
            return alexaBiometricData;
        } finally {
        }
    }
}
