package com.dee.app.cachemanager;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pools;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.function.BiCallback;
import com.dee.app.function.Callback;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.hash.Hashing;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
/* loaded from: classes9.dex */
public class DefaultObjectCache<T> implements Cache<T> {
    private static final Charset ENCODING_CHARSET = Charset.forName("UTF-8");
    private static final int KRYO_MAX_POOL_SIZE = 2;
    public static final int MAX_SIZE = 2147;
    @VisibleForTesting
    static final String METRICS_CACHE_KEY = "last_cache_key";
    @VisibleForTesting
    static final String METRICS_CACHE_PAYLOAD_SIZE = "last_cache_payload_size";
    public static final int NUM_DISK_THREADS = 2;
    private static final int PENDING_OPS_CONCURRENCY_LEVEL = 2;
    private static final int PENDING_OPS_INITIAL_CAPACITY = 11;
    private static final float PENDING_OPS_LOAD_FACTOR = 0.75f;
    private static final int PENDING_OPS_NUM_PARTITIONS = 8;
    private static final String TAG = "DefaultObjectCache";
    private final ByteCache byteCache;
    private final Class<T> clazz;
    private final CrashMetadata crashMetadata;
    private final Scheduler diskOperationsScheduler;
    private final Encryptor encryptor;
    private final FeatureServiceV2 featureServiceV2;
    private final TwoTierLruMemoryCache<T> memoryCache;
    @VisibleForTesting
    final ConcurrentHashMap<Integer, Deque<CacheMutation<T>>> pendingCacheMutations;
    private final CacheEvents cacheEvents = new CacheEvents();
    private final Pools.Pool<Kryo> kryoPool = new Pools.SynchronizedPool(2);

    /* loaded from: classes9.dex */
    private static final class GetMetricsObserver<T> implements Observer<Optional<T>> {
        private final CacheEvents cacheEvents;
        private final CacheMetadata cacheMetadata;
        @Nullable
        private final String key;

        GetMetricsObserver(@Nullable String str, CacheMetadata cacheMetadata, CacheEvents cacheEvents) {
            this.key = str;
            this.cacheMetadata = cacheMetadata;
            this.cacheEvents = cacheEvents;
        }

        @Override // rx.Observer
        public void onCompleted() {
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.cacheEvents.logGetError(this.key, this.cacheMetadata, th);
        }

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Optional) ((Optional) obj));
        }

        public void onNext(Optional<T> optional) {
            if (optional != null && optional.isPresent()) {
                this.cacheEvents.logHit(this.key, this.cacheMetadata);
            } else {
                this.cacheEvents.logMiss(this.key, this.cacheMetadata);
            }
        }
    }

    public DefaultObjectCache(@NonNull Class<T> cls, @NonNull TwoTierLruMemoryCache<T> twoTierLruMemoryCache, @NonNull ByteCache byteCache, @NonNull Encryptor encryptor, @NonNull ExecutorService executorService, @NonNull Scheduler scheduler) {
        this.clazz = cls;
        this.memoryCache = twoTierLruMemoryCache;
        this.byteCache = byteCache;
        this.encryptor = encryptor;
        initializeResourcesInBackground(executorService);
        this.diskOperationsScheduler = scheduler;
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        this.crashMetadata = (CrashMetadata) componentRegistry.get(CrashMetadata.class).get();
        this.featureServiceV2 = (FeatureServiceV2) componentRegistry.get(FeatureServiceV2.class).get();
        this.pendingCacheMutations = new ConcurrentHashMap<>(11, 0.75f, 2);
    }

    private boolean canLogCacheKeys() {
        return false;
    }

    private Optional<byte[]> diskGet(String str) {
        return this.byteCache.get(getLookUpKey(str));
    }

    private void diskPut(String str, byte[] bArr) {
        byte[] bytes = str.getBytes(ENCODING_CHARSET);
        setCacheKeyCrashMetadata(str, bArr);
        try {
            this.byteCache.put(getLookUpKey(str), this.encryptor.encrypt(ByteBuffer.allocate(bytes.length + 4 + bArr.length).putInt(bytes.length).put(bytes).put(bArr).array()));
        } catch (EncryptorException e) {
            Log.e(TAG, "Failed to encrypt value.", e);
            throw e;
        }
    }

    private void diskRemove(String str) {
        this.byteCache.remove(getLookUpKey(str));
    }

    private Kryo getKryo() {
        Kryo acquire = this.kryoPool.acquire();
        if (acquire != null) {
            return acquire;
        }
        Kryo kryo = new Kryo();
        kryo.setAsmEnabled(true);
        kryo.register(this.clazz);
        return kryo;
    }

    @Nullable
    private CacheMutation<T> getLastMutation(@NonNull String str) {
        Deque<CacheMutation<T>> deque = this.pendingCacheMutations.get(Integer.valueOf(partitionForKey(str)));
        if (deque != null) {
            Iterator<CacheMutation<T>> descendingIterator = deque.descendingIterator();
            while (descendingIterator.hasNext()) {
                CacheMutation<T> next = descendingIterator.next();
                if (next.key.equals(str)) {
                    return next;
                }
            }
            return null;
        }
        return null;
    }

    @VisibleForTesting
    static String getLookUpKey(String str) {
        return sha256(str);
    }

    private void initializeResourcesInBackground(@NonNull ExecutorService executorService) {
        executorService.submit(new Runnable() { // from class: com.dee.app.cachemanager.-$$Lambda$DefaultObjectCache$knC2gbnUITEryECFvGpp6AqNMpY
            @Override // java.lang.Runnable
            public final void run() {
                DefaultObjectCache.this.lambda$initializeResourcesInBackground$15$DefaultObjectCache();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$1(Void r0) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$12() {
    }

    private static int partitionForKey(String str) {
        return str.hashCode() % 8;
    }

    private void setCacheKeyCrashMetadata(String str, byte[] bArr) {
        if (canLogCacheKeys()) {
            this.crashMetadata.put(METRICS_CACHE_KEY, str);
            this.crashMetadata.put(METRICS_CACHE_PAYLOAD_SIZE, bArr.length);
        }
    }

    private static String sha256(String str) {
        return Hashing.sha256().hashString(str, ENCODING_CHARSET).toString();
    }

    @Override // com.dee.app.cachemanager.Cache
    public Observable<Void> clear(@NonNull final CacheMetadata cacheMetadata) {
        Preconditions.checkNotNull(cacheMetadata);
        for (Integer num : this.pendingCacheMutations.keySet()) {
            this.pendingCacheMutations.get(num).clear();
        }
        this.pendingCacheMutations.clear();
        this.memoryCache.clear();
        return (Observable<T>) Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.cachemanager.-$$Lambda$DefaultObjectCache$hBAklY5RjOxSy5wgzvtpUaup_tY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultObjectCache.this.lambda$clear$9$DefaultObjectCache((Subscriber) obj);
            }
        }).subscribeOn(this.diskOperationsScheduler).doOnError(new Action1() { // from class: com.dee.app.cachemanager.-$$Lambda$DefaultObjectCache$cvALv4Uu6rui_hNuetU_8TdXG8E
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultObjectCache.this.lambda$clear$10$DefaultObjectCache(cacheMetadata, (Throwable) obj);
            }
        });
    }

    @Override // com.dee.app.cachemanager.Cache
    public Observable<Optional<T>> get(@NonNull final String str, @NonNull final CacheMetadata cacheMetadata) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(cacheMetadata);
        CacheMutation<T> lastMutation = getLastMutation(str);
        if (lastMutation != null) {
            int i = lastMutation.type;
            if (i == 0) {
                this.cacheEvents.logMiss(str, cacheMetadata);
                return Observable.just(Optional.absent());
            } else if (i != 1) {
                Log.w(TAG, "Unknown cache mutation type!");
            } else {
                this.cacheEvents.logHit(str, cacheMetadata);
                return Observable.just(Optional.fromNullable(lastMutation.value));
            }
        }
        T t = this.memoryCache.get(str);
        if (t != null) {
            this.cacheEvents.logHit(str, cacheMetadata);
            return Observable.just(Optional.of(t));
        }
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.cachemanager.-$$Lambda$DefaultObjectCache$kWxOJl1dFtrsZO55lEE068J8Mrw
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultObjectCache.this.lambda$get$0$DefaultObjectCache(str, (Subscriber) obj);
            }
        }).subscribeOn(this.diskOperationsScheduler).observeOn(Schedulers.computation()).map(new Func1() { // from class: com.dee.app.cachemanager.-$$Lambda$DefaultObjectCache$jL8V9H4IyzG8DkiuHkmqRIwI2co
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return DefaultObjectCache.this.lambda$get$4$DefaultObjectCache(str, cacheMetadata, (Optional) obj);
            }
        }).doOnEach(new GetMetricsObserver(str, cacheMetadata, this.cacheEvents));
    }

    @Override // com.dee.app.cachemanager.Cache
    public Observable<CacheEvent> getCacheEvents() {
        return this.cacheEvents.getCacheEvents();
    }

    @SuppressLint({"NewApi"})
    @VisibleForTesting
    Deque<CacheMutation<T>> initPendingOpsForKey(@NonNull String str) {
        int partitionForKey = partitionForKey(str);
        Deque<CacheMutation<T>> deque = this.pendingCacheMutations.get(Integer.valueOf(partitionForKey));
        if (deque == null) {
            this.pendingCacheMutations.putIfAbsent(Integer.valueOf(partitionForKey), new ConcurrentLinkedDeque());
            return this.pendingCacheMutations.get(Integer.valueOf(partitionForKey));
        }
        return deque;
    }

    public /* synthetic */ void lambda$clear$10$DefaultObjectCache(CacheMetadata cacheMetadata, Throwable th) {
        this.cacheEvents.logClearError(null, cacheMetadata, th);
    }

    public /* synthetic */ void lambda$clear$11$DefaultObjectCache(String str, String str2, Object obj) {
        if (str2.startsWith(str)) {
            this.memoryCache.remove(str2);
        }
    }

    public /* synthetic */ void lambda$clear$14$DefaultObjectCache(final String str, Subscriber subscriber) {
        try {
            this.byteCache.forEach(new Callback() { // from class: com.dee.app.cachemanager.-$$Lambda$DefaultObjectCache$CCh8yqDuaPn6Bj9hjHpbe5noJbA
                @Override // com.dee.app.function.Callback
                public final void accept(Object obj) {
                    DefaultObjectCache.this.lambda$null$13$DefaultObjectCache(str, (byte[]) obj);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Failed to clear keys with prefix", e);
            subscriber.onError(e);
        }
        subscriber.onCompleted();
    }

    public /* synthetic */ void lambda$clear$9$DefaultObjectCache(Subscriber subscriber) {
        try {
            this.byteCache.clear();
        } catch (Exception e) {
            Log.e(TAG, "Failed to clear underlying byte-cache state.", e);
            subscriber.onError(e);
        }
        try {
            if (!this.encryptor.clearState()) {
                Log.e(TAG, "Failed to clear state for Encryptor.");
                subscriber.onError(new Exception("Failed to clear state for Encryptor."));
            }
        } catch (Exception e2) {
            Log.e(TAG, "Unknown exception occurred while clearing encryptor state", e2);
            subscriber.onError(e2);
        }
        subscriber.onCompleted();
    }

    public /* synthetic */ void lambda$get$0$DefaultObjectCache(String str, Subscriber subscriber) {
        try {
            try {
                subscriber.onNext(diskGet(str));
            } catch (Exception e) {
                Log.e(TAG, "Exception occurred during cache get.", e);
                subscriber.onError(e);
            }
        } finally {
            subscriber.onCompleted();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ Optional lambda$get$4$DefaultObjectCache(String str, CacheMetadata cacheMetadata, Optional optional) {
        if (!optional.isPresent()) {
            return Optional.absent();
        }
        try {
            byte[] decrypt = this.encryptor.decrypt((byte[]) optional.get());
            if (decrypt == null) {
                Log.e(TAG, "Decryption failed while decrypting entry.");
                this.cacheEvents.logGetError(str, cacheMetadata, null);
                return Optional.absent();
            }
            ByteBuffer wrap = ByteBuffer.wrap(decrypt);
            int i = wrap.getInt();
            byte[] bArr = new byte[i];
            wrap.get(bArr, 0, i);
            if (!str.equals(new String(bArr, ENCODING_CHARSET))) {
                return Optional.absent();
            }
            int position = wrap.position();
            int remaining = wrap.remaining();
            Kryo kryo = getKryo();
            try {
                Object readObject = kryo.readObject(new Input(decrypt, position, remaining), this.clazz);
                this.kryoPool.release(kryo);
                if (!this.memoryCache.put(str, readObject, 0, remaining)) {
                    String str2 = "Object for key " + str + " not stored in memory cache.";
                }
                return Optional.of(readObject);
            } catch (Throwable th) {
                this.kryoPool.release(kryo);
                throw th;
            }
        } catch (EncryptorException e) {
            Log.e(TAG, "Exception for key decryption. Clearing cache.", e);
            clear(cacheMetadata).subscribe($$Lambda$DefaultObjectCache$w7xoutzCGNyCqp_bIEkHdsCMoY.INSTANCE, new Action1() { // from class: com.dee.app.cachemanager.-$$Lambda$DefaultObjectCache$X3t6iWq9R6_mBdxrxVY_dn99XBs
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Throwable th2 = (Throwable) obj;
                    Log.e(DefaultObjectCache.TAG, "Error occurred while clearing cache for encryption error", EncryptorException.this);
                }
            }, $$Lambda$DefaultObjectCache$xaDIoqVXKqq4v86KeQkWnmEoIm4.INSTANCE);
            this.cacheEvents.logGetError(str, cacheMetadata, e);
            return Optional.absent();
        }
    }

    public /* synthetic */ void lambda$initializeResourcesInBackground$15$DefaultObjectCache() {
        try {
            this.encryptor.init();
        } catch (Exception e) {
            Log.e(TAG, "Failed to initialize encryptor.", e);
        }
        for (int i = 0; i < 2; i++) {
            this.kryoPool.release(getKryo());
        }
    }

    public /* synthetic */ void lambda$null$13$DefaultObjectCache(String str, byte[] bArr) {
        byte[] decrypt = this.encryptor.decrypt(bArr);
        if (decrypt == null) {
            GeneratedOutlineSupport1.outline162("Decryption failed while decrypting entry for clear-prefix: ", str, TAG);
            return;
        }
        ByteBuffer wrap = ByteBuffer.wrap(decrypt);
        int i = wrap.getInt();
        byte[] bArr2 = new byte[i];
        wrap.get(bArr2, 0, i);
        String str2 = new String(bArr2, ENCODING_CHARSET);
        if (!str2.startsWith(str)) {
            return;
        }
        remove(str2, CacheMetadata.EMPTY).toCompletable().subscribe($$Lambda$DefaultObjectCache$RXsNBeRSQWPRHaT2Kz09IiXjZH0.INSTANCE);
    }

    public /* synthetic */ void lambda$put$5$DefaultObjectCache(String str, Object obj, Deque deque, CacheMutation cacheMutation, Subscriber subscriber) {
        try {
            try {
                Kryo kryo = getKryo();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                setCacheKeyCrashMetadata(str);
                try {
                    Output output = new Output(byteArrayOutputStream);
                    kryo.writeObject(output, obj);
                    output.flush();
                    output.close();
                    this.kryoPool.release(kryo);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    if (!this.memoryCache.put(str, obj, 0, byteArray.length)) {
                        this.memoryCache.remove(str);
                    }
                    diskPut(str, byteArray);
                } catch (Throwable th) {
                    this.kryoPool.release(kryo);
                    throw th;
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        } finally {
            deque.remove(cacheMutation);
            subscriber.onCompleted();
        }
    }

    public /* synthetic */ void lambda$put$6$DefaultObjectCache(String str, CacheMetadata cacheMetadata, Throwable th) {
        this.cacheEvents.logPutError(str, cacheMetadata, th);
    }

    public /* synthetic */ void lambda$remove$7$DefaultObjectCache(String str, Deque deque, CacheMutation cacheMutation, Subscriber subscriber) {
        try {
            try {
                this.memoryCache.remove(str);
                diskRemove(str);
            } catch (Exception e) {
                subscriber.onError(e);
            }
        } finally {
            subscriber.onCompleted();
            deque.remove(cacheMutation);
        }
    }

    public /* synthetic */ void lambda$remove$8$DefaultObjectCache(String str, CacheMetadata cacheMetadata, Throwable th) {
        this.cacheEvents.logRemoveError(str, cacheMetadata, th);
    }

    @Override // com.dee.app.cachemanager.Cache
    public Observable<Void> put(@NonNull final String str, @NonNull final T t, @NonNull final CacheMetadata cacheMetadata) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(t);
        Preconditions.checkNotNull(cacheMetadata);
        final Deque<CacheMutation<T>> initPendingOpsForKey = initPendingOpsForKey(str);
        final CacheMutation<T> put = CacheMutation.put(str, t);
        initPendingOpsForKey.push(put);
        return (Observable<T>) Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.cachemanager.-$$Lambda$DefaultObjectCache$_BD3t1OnFep5hkyZ1uwbU9UnaAI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultObjectCache.this.lambda$put$5$DefaultObjectCache(str, t, initPendingOpsForKey, put, (Subscriber) obj);
            }
        }).subscribeOn(this.diskOperationsScheduler).observeOn(Schedulers.computation()).doOnError(new Action1() { // from class: com.dee.app.cachemanager.-$$Lambda$DefaultObjectCache$ckaDgqH8SYwym6kSL8BkE0acwFI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultObjectCache.this.lambda$put$6$DefaultObjectCache(str, cacheMetadata, (Throwable) obj);
            }
        });
    }

    @Override // com.dee.app.cachemanager.Cache
    public Observable<Void> remove(@NonNull final String str, @NonNull final CacheMetadata cacheMetadata) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(cacheMetadata);
        final Deque<CacheMutation<T>> initPendingOpsForKey = initPendingOpsForKey(str);
        final CacheMutation<T> remove = CacheMutation.remove(str);
        initPendingOpsForKey.push(remove);
        return (Observable<T>) Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.cachemanager.-$$Lambda$DefaultObjectCache$yZ7YaYQRlmW9ccUPDyLfQbc_Scc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultObjectCache.this.lambda$remove$7$DefaultObjectCache(str, initPendingOpsForKey, remove, (Subscriber) obj);
            }
        }).subscribeOn(this.diskOperationsScheduler).doOnError(new Action1() { // from class: com.dee.app.cachemanager.-$$Lambda$DefaultObjectCache$JsqMnUacXwgsBfuloj8CsIhLRMI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultObjectCache.this.lambda$remove$8$DefaultObjectCache(str, cacheMetadata, (Throwable) obj);
            }
        });
    }

    private void setCacheKeyCrashMetadata(String str) {
        if (canLogCacheKeys()) {
            this.crashMetadata.put(METRICS_CACHE_KEY, str);
            this.crashMetadata.put(METRICS_CACHE_PAYLOAD_SIZE, "UNKNOWN");
        }
    }

    @Override // com.dee.app.cachemanager.Cache
    public Observable<Void> clear(@NonNull final String str) {
        Preconditions.checkNotNull(str);
        this.memoryCache.forEach(new BiCallback() { // from class: com.dee.app.cachemanager.-$$Lambda$DefaultObjectCache$2XGp_dItZYAKmNIEc8BpYUCLTYM
            @Override // com.dee.app.function.BiCallback
            public final void accept(Object obj, Object obj2) {
                DefaultObjectCache.this.lambda$clear$11$DefaultObjectCache(str, (String) obj, obj2);
            }
        });
        return Observable.create(new Observable.OnSubscribe() { // from class: com.dee.app.cachemanager.-$$Lambda$DefaultObjectCache$MQuqK9wuy_Fm0aMTdwPuqCzbTc4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                DefaultObjectCache.this.lambda$clear$14$DefaultObjectCache(str, (Subscriber) obj);
            }
        }).subscribeOn(this.diskOperationsScheduler);
    }
}
