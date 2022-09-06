package com.amazon.alexa.hho.cache;

import androidx.annotation.NonNull;
import com.amazon.alexa.hho.utils.FileManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Predicate;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class MediaFileCacheManager {
    private static final long ONE_DAY_MS = 86400000;
    private static final String TAG = "MediaFileCacheManager";
    private final String cacheDirPath;
    private final FileManager fileManager;
    private final Map<String, Long> reservedSpaceMap = new HashMap();
    private final Scheduler cacheOperationsScheduler = Schedulers.from(Executors.newSingleThreadExecutor());
    private final Scheduler completionScheduler = Schedulers.computation();

    public MediaFileCacheManager(@Nonnull String str, @Nonnull FileManager fileManager) {
        this.fileManager = fileManager;
        this.cacheDirPath = str;
        fileManager.createDirectoryIfNeeded(str);
    }

    private long getCacheTotalSizeInner(@Nullable Predicate<String> predicate) {
        long j = 0;
        for (File file : getCachedFiles(predicate)) {
            j += file.length();
        }
        for (Long l : this.reservedSpaceMap.values()) {
            j += l.longValue();
        }
        GeneratedOutlineSupport1.outline153("Cache total size: ", j);
        return j;
    }

    @Nonnull
    private List<File> getCachedFiles(@Nullable Predicate<String> predicate) {
        File[] directoryFiles = this.fileManager.directoryFiles(this.cacheDirPath);
        if (directoryFiles == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        if (predicate != null) {
            for (File file : directoryFiles) {
                if (predicate.apply(file.getAbsolutePath())) {
                    arrayList.add(file);
                }
            }
        } else {
            Collections.addAll(arrayList, directoryFiles);
        }
        return arrayList;
    }

    @Nonnull
    private String getFilePathForKey(@Nonnull String str) {
        return GeneratedOutlineSupport1.outline92(new StringBuilder(), this.cacheDirPath, "/", str);
    }

    @Nonnull
    public Single<Boolean> attemptToReserveSpace(@Nonnull final String str, final long j, final long j2, @Nullable final Predicate<String> predicate) {
        String str2 = "Attempt to reserve space for: " + str;
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.hho.cache.-$$Lambda$MediaFileCacheManager$DRRAbRuuOok5CUoyNhoCzsXXkEI
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MediaFileCacheManager.this.lambda$attemptToReserveSpace$3$MediaFileCacheManager(str, predicate, j, j2);
            }
        }).subscribeOn(this.cacheOperationsScheduler).observeOn(this.completionScheduler);
    }

    @Nonnull
    public Completable delete(@NonNull final String str) {
        String str2 = "Delete media from cache: " + str;
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.hho.cache.-$$Lambda$MediaFileCacheManager$9WZ4CX5mode84CCCMfWtbsuDUjg
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                MediaFileCacheManager.this.lambda$delete$5$MediaFileCacheManager(str);
            }
        }).subscribeOn(this.cacheOperationsScheduler).observeOn(this.completionScheduler);
    }

    @Nonnull
    public Single<List<String>> deleteFiles(final int i) {
        String str = "Delete old media from cache: " + i;
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.hho.cache.-$$Lambda$MediaFileCacheManager$-bFsHYXCNzxPB7l0-HLYdS31ihQ
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MediaFileCacheManager.this.lambda$deleteFiles$7$MediaFileCacheManager(i);
            }
        }).subscribeOn(this.cacheOperationsScheduler).observeOn(this.completionScheduler);
    }

    @Nonnull
    public Completable freeReservedSpace(@Nonnull final String str) {
        String str2 = "Free reserved space for: " + str;
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.hho.cache.-$$Lambda$MediaFileCacheManager$fFVvQVaWw1YyUFvrYH8PoIITJo0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                MediaFileCacheManager.this.lambda$freeReservedSpace$1$MediaFileCacheManager(str);
            }
        }).subscribeOn(this.cacheOperationsScheduler).observeOn(this.completionScheduler);
    }

    @Nonnull
    public Maybe<String> get(@NonNull final String str) {
        String str2 = "Get media from cache: " + str;
        return Maybe.fromCallable(new Callable() { // from class: com.amazon.alexa.hho.cache.-$$Lambda$MediaFileCacheManager$otEQ_c_cOcU8bBJihaE8-XR1u3U
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MediaFileCacheManager.this.lambda$get$6$MediaFileCacheManager(str);
            }
        }).subscribeOn(this.cacheOperationsScheduler).observeOn(this.completionScheduler);
    }

    @Nonnull
    public Single<Boolean> isSpaceReserved(@Nonnull final String str) {
        String str2 = "Check if space reserved for: " + str;
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.hho.cache.-$$Lambda$MediaFileCacheManager$j7ukp53v8e3XcrKMMSkBUsZ1bu0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MediaFileCacheManager.this.lambda$isSpaceReserved$2$MediaFileCacheManager(str);
            }
        }).subscribeOn(this.cacheOperationsScheduler).observeOn(this.completionScheduler);
    }

    public /* synthetic */ Boolean lambda$attemptToReserveSpace$3$MediaFileCacheManager(String str, Predicate predicate, long j, long j2) throws Exception {
        if (this.reservedSpaceMap.containsKey(str)) {
            GeneratedOutlineSupport1.outline158("Space is already reserved for: ", str);
            return false;
        } else if (getCacheTotalSizeInner(predicate) + j >= j2) {
            GeneratedOutlineSupport1.outline158("No space left in cache for: ", str);
            return false;
        } else {
            GeneratedOutlineSupport1.outline158("Space reserved for: ", str);
            this.reservedSpaceMap.put(str, Long.valueOf(j));
            return true;
        }
    }

    public /* synthetic */ void lambda$delete$5$MediaFileCacheManager(String str) throws Throwable {
        this.fileManager.deleteFile(getFilePathForKey(str));
    }

    public /* synthetic */ List lambda$deleteFiles$7$MediaFileCacheManager(int i) throws Exception {
        File[] directoryFiles = this.fileManager.directoryFiles(this.cacheDirPath);
        ArrayList arrayList = new ArrayList();
        if (directoryFiles == null) {
            return arrayList;
        }
        for (File file : directoryFiles) {
            if (!file.isDirectory() && (new Date().getTime() - file.lastModified()) / 86400000 > i && file.delete()) {
                arrayList.add(file.getName());
            }
        }
        return arrayList;
    }

    public /* synthetic */ void lambda$freeReservedSpace$1$MediaFileCacheManager(String str) throws Throwable {
        this.reservedSpaceMap.remove(str);
    }

    public /* synthetic */ String lambda$get$6$MediaFileCacheManager(String str) throws Exception {
        String filePathForKey = getFilePathForKey(str);
        if (this.fileManager.fileExistsAtPath(filePathForKey)) {
            GeneratedOutlineSupport1.outline158("Media cache hit: ", str);
            this.fileManager.touchFile(filePathForKey);
            return filePathForKey;
        }
        GeneratedOutlineSupport1.outline158("Media cache miss: ", str);
        return null;
    }

    public /* synthetic */ Boolean lambda$isSpaceReserved$2$MediaFileCacheManager(String str) throws Exception {
        return Boolean.valueOf(this.reservedSpaceMap.containsKey(str));
    }

    public /* synthetic */ String lambda$moveToCache$4$MediaFileCacheManager(String str, String str2) throws Exception {
        this.reservedSpaceMap.remove(str);
        String filePathForKey = getFilePathForKey(str);
        this.fileManager.moveFile(str2, filePathForKey);
        return filePathForKey;
    }

    public /* synthetic */ void lambda$reserveSpace$0$MediaFileCacheManager(String str, long j) throws Throwable {
        this.reservedSpaceMap.put(str, Long.valueOf(j));
    }

    public /* synthetic */ List lambda$trimCacheSizeDown$9$MediaFileCacheManager(Predicate predicate, long j, int i) throws Exception {
        ArrayList arrayList = new ArrayList();
        long cacheTotalSizeInner = getCacheTotalSizeInner(predicate) - j;
        if (cacheTotalSizeInner <= 0) {
            return arrayList;
        }
        List<File> cachedFiles = getCachedFiles(predicate);
        Collections.sort(cachedFiles, $$Lambda$MediaFileCacheManager$ZTVMGKR1vAPfMh3i1ucGsPACsFg.INSTANCE);
        for (int i2 = 0; i2 < cachedFiles.size() - i; i2++) {
            File file = cachedFiles.get(i2);
            long length = file.length();
            if (file.delete()) {
                arrayList.add(file.getName());
                cacheTotalSizeInner -= length;
            }
            if (cacheTotalSizeInner <= 0) {
                break;
            }
        }
        return arrayList;
    }

    @Nonnull
    public Single<String> moveToCache(@NonNull final String str, @NonNull final String str2) {
        String str3 = "Move media to cache: " + str + " from: " + str2;
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.hho.cache.-$$Lambda$MediaFileCacheManager$lLuzEEOocTIziHsJwUsdtBqSTqk
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MediaFileCacheManager.this.lambda$moveToCache$4$MediaFileCacheManager(str, str2);
            }
        }).subscribeOn(this.cacheOperationsScheduler).observeOn(this.completionScheduler);
    }

    @Nonnull
    public Completable reserveSpace(@Nonnull final String str, final long j) {
        String str2 = "Reserve space for: " + str + " " + j;
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.hho.cache.-$$Lambda$MediaFileCacheManager$3MzNKMgn_lGUy7B8BcMubsb06BA
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                MediaFileCacheManager.this.lambda$reserveSpace$0$MediaFileCacheManager(str, j);
            }
        }).subscribeOn(this.cacheOperationsScheduler).observeOn(this.completionScheduler);
    }

    @Nonnull
    public Single<List<String>> trimCacheSizeDown(final long j, final int i, @Nullable final Predicate<String> predicate) {
        String str = "Delete memory exceeding media from cache: " + j;
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.hho.cache.-$$Lambda$MediaFileCacheManager$VY8PQCeXLbW54cW4W1Xe9uELWpM
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return MediaFileCacheManager.this.lambda$trimCacheSizeDown$9$MediaFileCacheManager(predicate, j, i);
            }
        }).subscribeOn(this.cacheOperationsScheduler).observeOn(this.completionScheduler);
    }
}
