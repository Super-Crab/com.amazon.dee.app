package com.amazon.clouddrive.cdasdk.dps.settings;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface DPSSettingsCalls {
    @NonNull
    Single<GetSettingsResponse> getSettings(@NonNull GetSettingsRequest getSettingsRequest);
}
