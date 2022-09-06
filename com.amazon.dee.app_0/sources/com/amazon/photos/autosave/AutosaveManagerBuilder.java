package com.amazon.photos.autosave;

import android.content.Context;
import androidx.work.WorkManager;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.photos.autosave.DefaultAutosavePreferences;
import com.amazon.photos.autosave.internal.dagger.component.AutosaveComponent;
import com.amazon.photos.autosave.internal.dagger.component.DaggerAutosaveComponent;
import com.amazon.photos.autosave.internal.dagger.module.AutosaveModule;
import com.amazon.photos.autosave.internal.dagger.module.DatabaseModule;
import com.amazon.photos.autosave.internal.metrics.AutosaveMetrics;
import com.amazon.photos.autosave.internal.upload.AutosaveUploadConfigurationProviderKt;
import com.amazon.photos.autosave.internal.upload.DefaultPriorityResolver;
import com.amazon.photos.autosave.internal.utils.AndroidLogger;
import com.amazon.photos.autosave.model.MediaType;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.uploader.UploadManager;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AutosaveManagerBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010\u0015\u001a\u00020\u0016J\r\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019J\u0017\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0000¢\u0006\u0002\b\u001eJ\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0010J\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/photos/autosave/AutosaveManagerBuilder;", "", "autosaveFrameworkContext", "Lcom/amazon/photos/autosave/AutosaveFrameworkContext;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "discovery", "Lcom/amazon/photos/discovery/Discovery;", "uploadManager", "Lcom/amazon/photos/uploader/UploadManager;", "(Lcom/amazon/photos/autosave/AutosaveFrameworkContext;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/photos/discovery/Discovery;Lcom/amazon/photos/uploader/UploadManager;)V", "autosaveMetrics", "Lcom/amazon/photos/autosave/internal/metrics/AutosaveMetrics;", "defaultPreferences", "Lcom/amazon/photos/autosave/DefaultAutosavePreferences;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "uploadPriorityResolver", "Lcom/amazon/photos/autosave/UploadPriorityResolver;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/photos/autosave/AutosaveManager;", "getAutosaveModule", "Lcom/amazon/photos/autosave/internal/dagger/module/AutosaveModule;", "getAutosaveModule$AndroidPhotosAutosave_release", "getWorkManagerInstance", "Landroidx/work/WorkManager;", "applicationContext", "Landroid/content/Context;", "getWorkManagerInstance$AndroidPhotosAutosave_release", "withDefaultAutosavePreferences", "defaultAutosavePreferences", "withLogger", "withPriorityResolver", "resolver", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveManagerBuilder {
    private final AutosaveFrameworkContext autosaveFrameworkContext;
    private final AutosaveMetrics autosaveMetrics;
    private final CDClient cdClient;
    private DefaultAutosavePreferences defaultPreferences;
    private final Discovery discovery;
    private Logger logger;
    private final Metrics metrics;
    private final UploadManager uploadManager;
    private UploadPriorityResolver uploadPriorityResolver;

    public AutosaveManagerBuilder(@NotNull AutosaveFrameworkContext autosaveFrameworkContext, @NotNull Metrics metrics, @NotNull CDClient cdClient, @NotNull Discovery discovery, @NotNull UploadManager uploadManager) {
        Intrinsics.checkParameterIsNotNull(autosaveFrameworkContext, "autosaveFrameworkContext");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        Intrinsics.checkParameterIsNotNull(discovery, "discovery");
        Intrinsics.checkParameterIsNotNull(uploadManager, "uploadManager");
        this.autosaveFrameworkContext = autosaveFrameworkContext;
        this.metrics = metrics;
        this.cdClient = cdClient;
        this.discovery = discovery;
        this.uploadManager = uploadManager;
        this.logger = new AndroidLogger();
        this.uploadPriorityResolver = new DefaultPriorityResolver();
        this.defaultPreferences = new DefaultAutosavePreferences() { // from class: com.amazon.photos.autosave.AutosaveManagerBuilder$defaultPreferences$1
            @Override // com.amazon.photos.autosave.DefaultAutosavePreferences
            public boolean isAddToFamilyEnabled() {
                return DefaultAutosavePreferences.DefaultImpls.isAddToFamilyEnabled(this);
            }

            @Override // com.amazon.photos.autosave.DefaultAutosavePreferences
            public boolean isAllFoldersEnabled() {
                return DefaultAutosavePreferences.DefaultImpls.isAllFoldersEnabled(this);
            }

            @Override // com.amazon.photos.autosave.DefaultAutosavePreferences
            public boolean isAllowedOnLowBattery() {
                return DefaultAutosavePreferences.DefaultImpls.isAllowedOnLowBattery(this);
            }

            @Override // com.amazon.photos.autosave.DefaultAutosavePreferences
            public boolean isAllowedOnPowerSaverMode() {
                return DefaultAutosavePreferences.DefaultImpls.isAllowedOnPowerSaverMode(this);
            }

            @Override // com.amazon.photos.autosave.DefaultAutosavePreferences
            public boolean isAllowedOnlyWhileCharging() {
                return DefaultAutosavePreferences.DefaultImpls.isAllowedOnlyWhileCharging(this);
            }

            @Override // com.amazon.photos.autosave.DefaultAutosavePreferences
            public boolean isAutosaveAllowedOnMetered(@NotNull MediaType itemType) {
                Intrinsics.checkParameterIsNotNull(itemType, "itemType");
                return DefaultAutosavePreferences.DefaultImpls.isAutosaveAllowedOnMetered(this, itemType);
            }

            @Override // com.amazon.photos.autosave.DefaultAutosavePreferences
            public boolean isAutosaveEnabled(@NotNull MediaType itemType) {
                Intrinsics.checkParameterIsNotNull(itemType, "itemType");
                return DefaultAutosavePreferences.DefaultImpls.isAutosaveEnabled(this, itemType);
            }
        };
        this.autosaveMetrics = new AutosaveMetrics(this.metrics);
    }

    @NotNull
    public final AutosaveManager build() {
        AutosaveComponent build = DaggerAutosaveComponent.builder().autosaveModule(getAutosaveModule$AndroidPhotosAutosave_release()).databaseModule(new DatabaseModule(this.autosaveFrameworkContext)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "DaggerAutosaveComponent.…\n                .build()");
        return new AutosaveManager(build);
    }

    @NotNull
    public final AutosaveModule getAutosaveModule$AndroidPhotosAutosave_release() {
        return new AutosaveModule(this.logger, this.autosaveMetrics, this.autosaveFrameworkContext, this.cdClient, getWorkManagerInstance$AndroidPhotosAutosave_release(this.autosaveFrameworkContext.getApplicationContext()), AutosaveUploadConfigurationProviderKt.configureForAutosave(this.uploadManager), this.discovery, this.defaultPreferences, this.uploadPriorityResolver);
    }

    @NotNull
    public final WorkManager getWorkManagerInstance$AndroidPhotosAutosave_release(@Nullable Context context) {
        if (context == null) {
            Intrinsics.throwNpe();
        }
        WorkManager workManager = WorkManager.getInstance(context);
        Intrinsics.checkExpressionValueIsNotNull(workManager, "WorkManager.getInstance(applicationContext!!)");
        return workManager;
    }

    @NotNull
    public final AutosaveManagerBuilder withDefaultAutosavePreferences(@NotNull DefaultAutosavePreferences defaultAutosavePreferences) {
        Intrinsics.checkParameterIsNotNull(defaultAutosavePreferences, "defaultAutosavePreferences");
        this.defaultPreferences = defaultAutosavePreferences;
        return this;
    }

    @NotNull
    public final AutosaveManagerBuilder withLogger(@NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.logger = logger;
        return this;
    }

    @NotNull
    public final AutosaveManagerBuilder withPriorityResolver(@NotNull UploadPriorityResolver resolver) {
        Intrinsics.checkParameterIsNotNull(resolver, "resolver");
        this.uploadPriorityResolver = resolver;
        return this;
    }
}
