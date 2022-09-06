package com.amazon.alexa.utils;

import java.io.File;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/utils/DataDirectoryProvider;", "", "dataDir", "Ljava/io/File;", "getDataDir", "()Ljava/io/File;", "AVSAndroidClient-utils_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes10.dex */
public interface DataDirectoryProvider {
    @Nullable
    File getDataDir();
}
