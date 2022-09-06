package com.amazon.dee.app.framework;

import android.os.Bundle;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public interface ViewModel {
    void create(@Nullable Bundle bundle);

    void destroy();

    @Nullable
    Bundle saveState();

    void updateContentMode(int i);
}
