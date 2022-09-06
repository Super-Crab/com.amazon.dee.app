package com.amazon.tarazed.core;

import kotlin.Metadata;
/* compiled from: TarazedBuildConfig.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/core/TarazedBuildConfig;", "", "()V", "isDebugBuild", "", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedBuildConfig {
    public static final TarazedBuildConfig INSTANCE = new TarazedBuildConfig();

    private TarazedBuildConfig() {
    }

    public final boolean isDebugBuild() {
        return false;
    }
}
