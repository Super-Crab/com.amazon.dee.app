package com.amazon.alexa.voice.wakeword;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
/* loaded from: classes11.dex */
final class UserWakewordPreference implements WakewordPreference {
    private static final String USER_WAKEWORD_PREFS_FILE_NAME = "user_wakeword_prefs";
    private static final String USER_WAKEWORD_PREFS_KEY = "wakeword_enabled";
    private final SharedPreferences sharedPreferences;
    private final BehaviorSubject<Boolean> wakewordEnabled = BehaviorSubject.createDefault(Boolean.valueOf(isWakewordEnabled()));

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserWakewordPreference(Context context) {
        this.sharedPreferences = context.getSharedPreferences(USER_WAKEWORD_PREFS_FILE_NAME, 0);
    }

    @Override // com.amazon.alexa.voice.wakeword.WakewordPreference
    public void enableWakeword(boolean z) {
        GeneratedOutlineSupport1.outline143(this.sharedPreferences, USER_WAKEWORD_PREFS_KEY, z);
        this.wakewordEnabled.onNext(Boolean.valueOf(z));
    }

    @Override // com.amazon.alexa.voice.wakeword.WakewordPreference
    public boolean isWakewordEnabled() {
        return this.sharedPreferences.getBoolean(USER_WAKEWORD_PREFS_KEY, false);
    }

    @Override // com.amazon.alexa.voice.wakeword.WakewordPreference
    public Observable<Boolean> onChanged() {
        return this.wakewordEnabled;
    }
}
