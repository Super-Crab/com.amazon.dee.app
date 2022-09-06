package com.amazon.photos.discovery.internal.dagger.module;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.photos.discovery.DiscoveryConfiguration;
import com.amazon.photos.discovery.dao.EditDao;
import com.amazon.photos.discovery.dao.LocalFolderDao;
import com.amazon.photos.discovery.dao.LocalItemDao;
import com.amazon.photos.discovery.dao.UnifiedItemDao;
import com.amazon.photos.discovery.internal.OpenForTesting;
import com.amazon.photos.discovery.internal.dagger.PerAccount;
import com.amazon.photos.discovery.internal.dao.WorkerDao;
import com.amazon.photos.discovery.internal.db.DiscoveryDatabase;
import com.amazon.photos.discovery.internal.dedupe.filter.DedupeFilter;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterEvents;
import com.amazon.photos.discovery.internal.dedupe.filter.FilterUtils;
import com.amazon.photos.discovery.internal.dedupe.filter.filters.CameraDedupeFilter;
import com.amazon.photos.discovery.internal.dedupe.filter.filters.CloudDedupeFilter;
import com.amazon.photos.discovery.internal.dedupe.filter.filters.CompositeDedupeFilter;
import com.amazon.photos.discovery.internal.util.FileUtils;
import com.amazon.photos.discovery.internal.util.OrphanRemover;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import dagger.Module;
import dagger.Provides;
import java.util.List;
import javax.security.auth.Destroyable;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DataModule.kt */
@OpenForTesting
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u000e\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0010J\b\u0010\u0011\u001a\u00020\u000fH\u0017J\b\u0010\u0012\u001a\u00020\fH\u0016J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J\b\u0010\u001b\u001a\u00020\u001cH\u0007J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 H\u0007J\u0018\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001f\u001a\u00020 H\u0007J\b\u0010$\u001a\u00020%H\u0007J\b\u0010&\u001a\u00020'H\u0007J\b\u0010(\u001a\u00020)H\u0007J\u0018\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010\"\u001a\u00020#H\u0007J\b\u0010.\u001a\u00020\u0018H\u0007J\b\u0010/\u001a\u000200H\u0007J\b\u00101\u001a\u00020-H\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/amazon/photos/discovery/internal/dagger/module/DataModule;", "Ljavax/security/auth/Destroyable;", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/photos/discovery/DiscoveryConfiguration;", "(Lcom/amazon/photos/discovery/DiscoveryConfiguration;)V", "appContext", "Landroid/content/Context;", "database", "Lcom/amazon/photos/discovery/internal/db/DiscoveryDatabase;", "dbName", "", "destroyed", "", "hashedDirectedId", MetricsConstants.Method.CACHE_CLEAR, "", "clear$AndroidPhotosDiscovery_release", "destroy", "isDestroyed", "provideDedupeFilter", "Lcom/amazon/photos/discovery/internal/dedupe/filter/DedupeFilter;", "fileUtils", "Lcom/amazon/photos/discovery/internal/util/FileUtils;", "sharedPreferences", "Landroid/content/SharedPreferences;", "utils", "Lcom/amazon/photos/discovery/internal/dedupe/filter/FilterUtils;", "provideEditDao", "Lcom/amazon/photos/discovery/dao/EditDao;", "provideFilterEvents", "Lcom/amazon/photos/discovery/internal/dedupe/filter/FilterEvents;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "provideFilterUtils", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "provideLocalFolderDao", "Lcom/amazon/photos/discovery/dao/LocalFolderDao;", "provideLocalItemDao", "Lcom/amazon/photos/discovery/dao/LocalItemDao;", "provideMediaStoreContentResolver", "Landroid/content/ContentResolver;", "provideOrphanRemover", "Lcom/amazon/photos/discovery/internal/util/OrphanRemover;", "workerDao", "Lcom/amazon/photos/discovery/internal/dao/WorkerDao;", "provideSharedPreferences", "provideUnifiedItemDao", "Lcom/amazon/photos/discovery/dao/UnifiedItemDao;", "provideWorkerDao", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
@Module
/* loaded from: classes13.dex */
public final class DataModule implements Destroyable {
    private final Context appContext;
    private final DiscoveryDatabase database;
    private final String dbName;
    private boolean destroyed;
    private final String hashedDirectedId;

    public DataModule(@NotNull DiscoveryConfiguration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        this.hashedDirectedId = configuration.getHashedDirectedId();
        this.appContext = configuration.getApplicationContext();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("discovery_database_");
        outline107.append(this.hashedDirectedId);
        this.dbName = outline107.toString();
        RoomDatabase build = Room.databaseBuilder(this.appContext, DiscoveryDatabase.class, this.dbName).fallbackToDestructiveMigrationFrom(1).fallbackToDestructiveMigrationOnDowngrade().build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Room.databaseBuilder(app…de()\n            .build()");
        this.database = (DiscoveryDatabase) build;
    }

    @VisibleForTesting
    public final void clear$AndroidPhotosDiscovery_release() {
        provideSharedPreferences().edit().clear().apply();
        this.database.clearAllTables();
    }

    @Override // javax.security.auth.Destroyable
    @WorkerThread
    public void destroy() {
        this.destroyed = true;
        provideSharedPreferences().edit().clear().apply();
        this.database.clearAllTables();
        this.database.close();
        this.appContext.deleteDatabase(this.dbName);
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.destroyed;
    }

    @PerAccount
    @Provides
    @NotNull
    public final DedupeFilter provideDedupeFilter(@NotNull FileUtils fileUtils, @NotNull SharedPreferences sharedPreferences, @NotNull FilterUtils utils) {
        List listOf;
        Intrinsics.checkParameterIsNotNull(fileUtils, "fileUtils");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(utils, "utils");
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new DedupeFilter[]{new CloudDedupeFilter(sharedPreferences, utils, 0L, 4, null), new CameraDedupeFilter(fileUtils, sharedPreferences, utils)});
        return new CompositeDedupeFilter(listOf);
    }

    @PerAccount
    @Provides
    @NotNull
    public final EditDao provideEditDao() {
        return this.database.editDao();
    }

    @PerAccount
    @Provides
    @NotNull
    public final FilterEvents provideFilterEvents(@NotNull SharedPreferences sharedPreferences, @NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "sharedPreferences");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        return new FilterEvents(sharedPreferences, logger);
    }

    @Provides
    @NotNull
    public final FilterUtils provideFilterUtils(@NotNull Metrics metrics, @NotNull Logger logger) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        return new FilterUtils(metrics, logger);
    }

    @PerAccount
    @Provides
    @NotNull
    public final LocalFolderDao provideLocalFolderDao() {
        return this.database.localFolderDao();
    }

    @PerAccount
    @Provides
    @NotNull
    public final LocalItemDao provideLocalItemDao() {
        return this.database.localItemDao();
    }

    @Provides
    @NotNull
    public final ContentResolver provideMediaStoreContentResolver() {
        ContentResolver contentResolver = this.appContext.getContentResolver();
        Intrinsics.checkExpressionValueIsNotNull(contentResolver, "appContext.contentResolver");
        return contentResolver;
    }

    @PerAccount
    @Provides
    @NotNull
    public final OrphanRemover provideOrphanRemover(@NotNull WorkerDao workerDao, @NotNull Metrics metrics) {
        Intrinsics.checkParameterIsNotNull(workerDao, "workerDao");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        return new OrphanRemover(workerDao, metrics);
    }

    @PerAccount
    @Provides
    @NotNull
    public final SharedPreferences provideSharedPreferences() {
        Context context = this.appContext;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("discovery_preferences_");
        outline107.append(this.hashedDirectedId);
        SharedPreferences sharedPreferences = context.getSharedPreferences(outline107.toString(), 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences, "appContext.getSharedPref…Id, Context.MODE_PRIVATE)");
        return sharedPreferences;
    }

    @PerAccount
    @Provides
    @NotNull
    public final UnifiedItemDao provideUnifiedItemDao() {
        return this.database.discoveryItemDao();
    }

    @PerAccount
    @Provides
    @NotNull
    public final WorkerDao provideWorkerDao() {
        return this.database.workerDao();
    }
}
