package com.amazon.tarazed.dagger.subcomponents;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import dagger.Subcomponent;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: DebugSubcomponent.kt */
@Subcomponent
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/tarazed/dagger/subcomponents/DebugSubcomponent;", "", "Builder", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface DebugSubcomponent {

    /* compiled from: DebugSubcomponent.kt */
    @Subcomponent.Builder
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/tarazed/dagger/subcomponents/DebugSubcomponent$Builder;", "", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/tarazed/dagger/subcomponents/DebugSubcomponent;", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public interface Builder {
        @NotNull
        DebugSubcomponent build();
    }
}
