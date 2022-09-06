package com.amazon.alexa.location;

import androidx.annotation.NonNull;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import com.dee.app.cachemanager.CacheMetadata;
import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.util.ArrayList;
import java.util.List;
import rx.functions.Action1;
/* loaded from: classes9.dex */
public class EncryptedLocationDataService implements LocationDataService {
    private static final String GEOFENCE_KEY = "GEOFENCES";
    private static final String MODULE_NAME = "LocationDataCache";
    private final Cache<AppDataCacheEntry> cache;
    private final CacheMetadata cacheMetadata = new CacheMetadata(MODULE_NAME);
    private final Gson gson;
    private final LazyComponent<Mobilytics> mobilytics;
    private static final String TAG = "LocationDataService";
    private static final String COMPONENT_NAME = MobilyticsUtil.getComponentName(TAG);

    public EncryptedLocationDataService(Cache<AppDataCacheEntry> cache, Gson gson, LazyComponent<Mobilytics> lazyComponent) {
        this.cache = cache;
        this.gson = gson;
        this.mobilytics = lazyComponent;
    }

    @NonNull
    private List<ALSGeofence> readALSGeofenceListFromCacheEntry(Optional<AppDataCacheEntry> optional) {
        if (optional.isPresent() && optional.get() != null && optional.get().getData() != null) {
            return (List) this.gson.fromJson(optional.get().getData(), new TypeToken<List<ALSGeofence>>() { // from class: com.amazon.alexa.location.EncryptedLocationDataService.1
            }.getType());
        }
        return new ArrayList();
    }

    @Override // com.amazon.alexa.location.LocationDataService
    @NonNull
    public Single<List<ALSGeofence>> getGeofences() {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.location.-$$Lambda$EncryptedLocationDataService$5uAgicogGAp-gg0xL-e2aL9-ROs
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                EncryptedLocationDataService.this.lambda$getGeofences$3$EncryptedLocationDataService(singleEmitter);
            }
        });
    }

    public /* synthetic */ void lambda$getGeofences$3$EncryptedLocationDataService(SingleEmitter singleEmitter) throws Throwable {
        try {
            List<ALSGeofence> readALSGeofenceListFromCacheEntry = readALSGeofenceListFromCacheEntry(this.cache.get(GEOFENCE_KEY, this.cacheMetadata).doOnError(new Action1() { // from class: com.amazon.alexa.location.-$$Lambda$EncryptedLocationDataService$b6Mq9nXIic5QIbpx-F28UmrBwow
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    EncryptedLocationDataService.this.lambda$null$2$EncryptedLocationDataService((Throwable) obj);
                }
            }).toBlocking().singleOrDefault(Optional.absent()));
            this.gson.toJson(readALSGeofenceListFromCacheEntry);
            singleEmitter.onSuccess(readALSGeofenceListFromCacheEntry);
        } catch (Throwable th) {
            th.printStackTrace();
            singleEmitter.onError(new LocationException(LocationErrorCode.GENERIC_ERROR, "[ERROR] Fail to get geofences from cache.", th));
        }
    }

    public /* synthetic */ void lambda$null$0$EncryptedLocationDataService(Throwable th) {
        String str = COMPONENT_NAME;
        this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.CANARY_CACHE_PUT_ERROR, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public /* synthetic */ void lambda$null$2$EncryptedLocationDataService(Throwable th) {
        String str = COMPONENT_NAME;
        this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.CANARY_CACHE_GET_ERROR, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public /* synthetic */ void lambda$saveGeofences$1$EncryptedLocationDataService(List list, CompletableEmitter completableEmitter) throws Throwable {
        try {
            this.cache.put(GEOFENCE_KEY, new AppDataCacheEntry(this.gson.toJson(list), 0, true), this.cacheMetadata).doOnError(new Action1() { // from class: com.amazon.alexa.location.-$$Lambda$EncryptedLocationDataService$Fs_NqqpqIQhBY6T4bwHLFdR46GM
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    EncryptedLocationDataService.this.lambda$null$0$EncryptedLocationDataService((Throwable) obj);
                }
            }).toBlocking().singleOrDefault(null);
            this.gson.toJson(list);
            completableEmitter.onComplete();
        } catch (Throwable th) {
            completableEmitter.onError(new LocationException(LocationErrorCode.GENERIC_ERROR, "[ERROR] Fail to save geofences to cache.", th));
        }
    }

    @Override // com.amazon.alexa.location.LocationDataService
    public Completable saveGeofences(@NonNull final List<ALSGeofence> list) {
        return Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.alexa.location.-$$Lambda$EncryptedLocationDataService$iRVG5ZSg7QbAdSXnUMOjucPs3Og
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) {
                EncryptedLocationDataService.this.lambda$saveGeofences$1$EncryptedLocationDataService(list, completableEmitter);
            }
        });
    }
}
