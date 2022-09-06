package com.amazon.alexa.accessorykit.finishsetup.persistence;

import androidx.annotation.Nullable;
/* loaded from: classes6.dex */
public interface FinishSetupRecordSupplier {
    @Nullable
    FinishSetupRecord get(String str);

    boolean has(String str);

    void remove(String str);

    void set(FinishSetupRecord finishSetupRecord);
}
