package com.amazon.photos.autosave.internal.preferences;

import com.amazon.photos.autosave.DefaultAutosavePreferences;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AutosavePreferenceChangeListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosavePreferenceChangeListener$applyPreferences$6 extends Lambda implements Function0<Boolean> {
    final /* synthetic */ AutosavePreferenceChangeListener this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutosavePreferenceChangeListener$applyPreferences$6(AutosavePreferenceChangeListener autosavePreferenceChangeListener) {
        super(0);
        this.this$0 = autosavePreferenceChangeListener;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12560invoke() {
        return Boolean.valueOf(mo12560invoke());
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [boolean, java.lang.Boolean] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public final Boolean mo12560invoke() {
        DefaultAutosavePreferences defaultAutosavePreferences;
        defaultAutosavePreferences = this.this$0.defaultPreferences;
        return defaultAutosavePreferences.isAddToFamilyEnabled();
    }
}
