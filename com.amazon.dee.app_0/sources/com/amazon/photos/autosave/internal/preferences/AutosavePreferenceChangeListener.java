package com.amazon.photos.autosave.internal.preferences;

import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.amazon.photos.autosave.AutosavePreferences;
import com.amazon.photos.autosave.DefaultAutosavePreferences;
import com.amazon.photos.autosave.internal.coroutines.DispatcherProvider;
import com.amazon.photos.autosave.model.MediaType;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AutosavePreferenceChangeListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0003J&\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\u001c\u0010\u0014\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J \u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/photos/autosave/internal/preferences/AutosavePreferenceChangeListener;", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "defaultPreferences", "Lcom/amazon/photos/autosave/DefaultAutosavePreferences;", "dispatcherProvider", "Lcom/amazon/photos/autosave/internal/coroutines/DispatcherProvider;", "queueHelper", "Lcom/amazon/photos/autosave/internal/preferences/PreferenceUploadQueueHelper;", "(Lcom/amazon/photos/autosave/DefaultAutosavePreferences;Lcom/amazon/photos/autosave/internal/coroutines/DispatcherProvider;Lcom/amazon/photos/autosave/internal/preferences/PreferenceUploadQueueHelper;)V", "applyPreferences", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "key", "", "handlePreference", "preferenceKey", "defaultVal", "Lkotlin/Function0;", "", "onSharedPreferenceChanged", "updateAutosaveEnabled", "itemType", "Lcom/amazon/photos/autosave/model/MediaType;", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosavePreferenceChangeListener implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final DefaultAutosavePreferences defaultPreferences;
    private final DispatcherProvider dispatcherProvider;
    private final PreferenceUploadQueueHelper queueHelper;

    public AutosavePreferenceChangeListener(@NotNull DefaultAutosavePreferences defaultPreferences, @NotNull DispatcherProvider dispatcherProvider, @NotNull PreferenceUploadQueueHelper queueHelper) {
        Intrinsics.checkParameterIsNotNull(defaultPreferences, "defaultPreferences");
        Intrinsics.checkParameterIsNotNull(dispatcherProvider, "dispatcherProvider");
        Intrinsics.checkParameterIsNotNull(queueHelper, "queueHelper");
        this.defaultPreferences = defaultPreferences;
        this.dispatcherProvider = dispatcherProvider;
        this.queueHelper = queueHelper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final void applyPreferences(SharedPreferences sharedPreferences, String str) {
        if (Intrinsics.areEqual(str, AutosavePreferences.Companion.getAutosavePreferenceKey(MediaType.PHOTO))) {
            updateAutosaveEnabled(sharedPreferences, str, MediaType.PHOTO);
        } else if (Intrinsics.areEqual(str, AutosavePreferences.Companion.getAutosavePreferenceKey(MediaType.VIDEO))) {
            updateAutosaveEnabled(sharedPreferences, str, MediaType.VIDEO);
        } else if (Intrinsics.areEqual(str, AutosavePreferences.Companion.getMeteredNetworkPreferenceKey(MediaType.PHOTO))) {
            handlePreference(sharedPreferences, str, new AutosavePreferenceChangeListener$applyPreferences$1(this));
        } else if (Intrinsics.areEqual(str, AutosavePreferences.Companion.getMeteredNetworkPreferenceKey(MediaType.VIDEO))) {
            handlePreference(sharedPreferences, str, new AutosavePreferenceChangeListener$applyPreferences$2(this));
        } else if (Intrinsics.areEqual(str, "charging_settings")) {
            handlePreference(sharedPreferences, str, new AutosavePreferenceChangeListener$applyPreferences$3(this));
        } else if (Intrinsics.areEqual(str, "low_battery_settings")) {
            handlePreference(sharedPreferences, str, new AutosavePreferenceChangeListener$applyPreferences$4(this));
        } else if (Intrinsics.areEqual(str, "power_saver_settings")) {
            handlePreference(sharedPreferences, str, new AutosavePreferenceChangeListener$applyPreferences$5(this));
        } else if (Intrinsics.areEqual(str, "add_family_settings")) {
            handlePreference(sharedPreferences, str, new AutosavePreferenceChangeListener$applyPreferences$6(this));
        } else if (!Intrinsics.areEqual(str, "all_folders_settings")) {
        } else {
            handlePreference(sharedPreferences, str, new AutosavePreferenceChangeListener$applyPreferences$7(this));
        }
    }

    private final void handlePreference(SharedPreferences sharedPreferences, String str, Function0<Boolean> function0) {
        this.queueHelper.handlePreference(str, sharedPreferences.getBoolean(str, function0.mo12560invoke().booleanValue()));
    }

    private final void updateAutosaveEnabled(SharedPreferences sharedPreferences, String str, MediaType mediaType) {
        this.queueHelper.handlePreference(str, sharedPreferences.getBoolean(str, this.defaultPreferences.isAutosaveEnabled(mediaType)));
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(@Nullable SharedPreferences sharedPreferences, @Nullable String str) {
        if (sharedPreferences != null) {
            if (str == null || str.length() == 0) {
                return;
            }
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(this.dispatcherProvider.getIo()), null, null, new AutosavePreferenceChangeListener$onSharedPreferenceChanged$1(this, sharedPreferences, str, null), 3, null);
        }
    }
}
