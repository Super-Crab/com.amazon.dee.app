package com.amazon.wellness.io.format.abs;

import java.io.InputStream;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessDataParser.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/wellness/io/format/abs/FitnessDataParser;", "", "parse", "Lcom/amazon/wellness/io/format/abs/AlexaBiometricData;", "inputStream", "Ljava/io/InputStream;", "AlexaBiometricSampleEncoderAndroid_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface FitnessDataParser {
    @NotNull
    AlexaBiometricData parse(@NotNull InputStream inputStream);
}
