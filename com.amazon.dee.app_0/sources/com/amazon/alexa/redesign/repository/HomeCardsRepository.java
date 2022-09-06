package com.amazon.alexa.redesign.repository;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.cache.HomeCacheService;
import com.amazon.alexa.redesign.client.HomeFeedServiceClient;
import com.amazon.alexa.redesign.debug.menu.DebugMenuService;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.entity.ranking.RankingRules;
import com.amazon.alexa.redesign.entity.ranking.Rule;
import com.amazon.alexa.redesign.entity.templates.DomainCardTemplateModel;
import com.amazon.alexa.redesign.utils.HomeCardsProducer;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import com.amazon.alexa.redesign.utils.HomeOEInteractor;
import com.amazon.alexa.redesign.utils.ResourceUtils;
import com.amazon.alexa.redesign.utils.verifier.EmptyMetricsConditionCallback;
import com.amazon.alexa.redesign.utils.verifier.OutageConditionCallback;
import com.amazon.alexa.redesign.utils.verifier.Verifier;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.cachemanager.CacheException;
import com.dee.app.http.CoralServiceException;
import com.google.common.collect.ImmutableMap;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.functions.Action1;
/* loaded from: classes10.dex */
public class HomeCardsRepository {
    private static final String TAG = "HomeCardsRepository";
    private static List<DomainCardTemplateModel> localCards;
    private final ActionFactory actionFactory;
    private final Context context;
    private final HomeCacheService homeCacheService;
    private final HomeFeedServiceClient homeFeedServiceClient;
    private final HomeContract.OEInteractor homeOEInteractor;
    private final IdentityService identityService;
    private final IsAppOnlyRepository isAppOnlyRepository;
    private final ReadWriteLock lock;
    private final Mobilytics mobilytics;
    private RankingRules rankingRules;

    public HomeCardsRepository(HomeFeedServiceClient homeFeedServiceClient, HomeCacheService homeCacheService, Mobilytics mobilytics, ActionFactory actionFactory, Context context, IdentityService identityService, IsAppOnlyRepository isAppOnlyRepository) {
        this.homeFeedServiceClient = homeFeedServiceClient;
        this.homeCacheService = homeCacheService;
        this.mobilytics = mobilytics;
        this.context = context;
        this.actionFactory = actionFactory;
        this.identityService = identityService;
        this.isAppOnlyRepository = isAppOnlyRepository;
        this.homeOEInteractor = new HomeOEInteractor(mobilytics);
        if (localCards == null) {
            localCards = new ArrayList();
        }
        this.lock = new ReentrantReadWriteLock();
    }

    private ImmutableMap.Builder<String, String> addDirectedId(ImmutableMap.Builder<String, String> builder, IdentityService identityService) {
        String directedId;
        UserIdentity user = identityService.getUser(TAG);
        if (user != null && (directedId = user.getDirectedId()) != null) {
            builder.mo7828put(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID, directedId);
        }
        return builder;
    }

    private void getRawHomeCardsFromCacheWithMetrics(SingleEmitter<List<CardModel>> singleEmitter) {
        MobilyticsMetricsTimer createTimer = this.mobilytics.createTimer("jasper-home-native.time.CacheRead", HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_READ, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
        JSONObject first = this.homeCacheService.getRawCards().toBlocking().first();
        createTimer.finishTimer();
        this.mobilytics.recordTimer(createTimer);
        this.mobilytics.recordOccurrence("jasper-home-native.fault.CacheRead", false, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_READ, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Successfully loaded cards from cache: \n");
        outline107.append(first.toString());
        outline107.toString();
        singleEmitter.onSuccess(HomeCardsProducer.fromRawHomeCards(first, this.actionFactory, this.homeOEInteractor, false));
        initRankingRules(first);
    }

    private JSONObject getRawHomeCardsFromServerWithMetrics(SingleEmitter<List<CardModel>> singleEmitter, ImmutableMap.Builder<String, String> builder) {
        MobilyticsMetricsTimer createTimer = this.mobilytics.createTimer("jasper-home-native.time.GetFeed", HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.GET_FEED, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
        JSONObject blockingGet = this.homeFeedServiceClient.getRawCardFeeds(builder.mo7826build()).blockingGet();
        createTimer.finishTimer();
        long elapsedTime = createTimer.getElapsedTime();
        this.mobilytics.recordTimer(createTimer);
        this.mobilytics.recordOccurrence("jasper-home-native.fault.GetFeed", false, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.GET_FEED, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
        List<CardModel> fromRawHomeCards = HomeCardsProducer.fromRawHomeCards(blockingGet, this.actionFactory, this.homeOEInteractor, true);
        this.isAppOnlyRepository.persistIsAppOnlyStatus(blockingGet);
        sendOEMetricsBasedOnCards(fromRawHomeCards, elapsedTime);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Successfully loaded cards from remote: \n");
        outline107.append(blockingGet.toString());
        outline107.toString();
        singleEmitter.onSuccess(fromRawHomeCards);
        initRankingRules(blockingGet);
        return blockingGet;
    }

    private int indexWithSameCardId(DomainCardTemplateModel domainCardTemplateModel) {
        for (int i = 0; i < localCards.size(); i++) {
            if (domainCardTemplateModel.getCardId().equals(localCards.get(i).getCardId())) {
                return i;
            }
        }
        return -1;
    }

    private void rerankLocalCards() {
        this.lock.writeLock().lock();
        try {
            ArrayList<DomainCardTemplateModel> arrayList = new ArrayList(localCards);
            localCards.clear();
            for (DomainCardTemplateModel domainCardTemplateModel : arrayList) {
                insertNewCardIntoLocalCards(domainCardTemplateModel, this.rankingRules);
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    private void saveHomeCardsToCacheWithMetrics(JSONObject jSONObject) {
        if (jSONObject != null) {
            MobilyticsMetricsTimer createTimer = this.mobilytics.createTimer("jasper-home-native.time.CacheWrite", HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_WRITE, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
            this.homeCacheService.saveRawCards(jSONObject).toBlocking().firstOrDefault(null);
            createTimer.finishTimer();
            this.mobilytics.recordTimer(createTimer);
            this.mobilytics.recordOccurrence("jasper-home-native.fault.CacheWrite", false, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_WRITE, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Successfully saved cards to cache: \n");
            outline107.append(jSONObject.toString());
            outline107.toString();
        }
    }

    private void sendOEMetricsBasedOnCards(List<CardModel> list, long j) {
        Verifier.verify(list, Arrays.asList(new EmptyMetricsConditionCallback(this.mobilytics), new OutageConditionCallback(this.mobilytics, j)));
    }

    public ImmutableMap.Builder<String, String> addDebugOptions(ImmutableMap.Builder<String, String> builder) {
        if (DebugMenuService.getInstance().getModel().getBypass()) {
            builder.mo7828put("bypassRanking", "true");
        }
        return builder;
    }

    public void addOrUpdateLocalCard(DomainCardTemplateModel domainCardTemplateModel) {
        this.lock.writeLock().lock();
        try {
            int indexWithSameCardId = indexWithSameCardId(domainCardTemplateModel);
            if (indexWithSameCardId != -1) {
                localCards.set(indexWithSameCardId, domainCardTemplateModel);
            } else if (this.rankingRules != null) {
                insertNewCardIntoLocalCards(domainCardTemplateModel, this.rankingRules);
            } else {
                localCards.add(0, domainCardTemplateModel);
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public Completable clearHomeCardsFromCache() {
        return Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.alexa.redesign.repository.-$$Lambda$HomeCardsRepository$ruF-YvS2wPc08fVg5Cp1O7dVl-o
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) {
                HomeCardsRepository.this.lambda$clearHomeCardsFromCache$4$HomeCardsRepository(completableEmitter);
            }
        });
    }

    public void clearLocalCards() {
        this.lock.writeLock().lock();
        try {
            localCards.clear();
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public Single<List<CardModel>> getCardsFromMockedData() {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.redesign.repository.-$$Lambda$HomeCardsRepository$cI4Mjf0BSZTnLV3o_rV0pKS1vy4
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                HomeCardsRepository.this.lambda$getCardsFromMockedData$5$HomeCardsRepository(singleEmitter);
            }
        });
    }

    public Single<List<DomainCardTemplateModel>> getLocalCards() {
        this.lock.readLock().lock();
        try {
            return Single.just(Collections.unmodifiableList(localCards));
        } finally {
            this.lock.readLock().unlock();
        }
    }

    public Single<List<CardModel>> getRawHomeCardsFromCache() {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.redesign.repository.-$$Lambda$HomeCardsRepository$yhB3S9GgtA8T_PktBdY_A-6pI1w
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                HomeCardsRepository.this.lambda$getRawHomeCardsFromCache$0$HomeCardsRepository(singleEmitter);
            }
        });
    }

    public Single<List<CardModel>> getRawHomeCardsFromServer() {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.redesign.repository.-$$Lambda$HomeCardsRepository$ooroXNcuhgYRJOx7dKNG_MBwcDA
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                HomeCardsRepository.this.lambda$getRawHomeCardsFromServer$1$HomeCardsRepository(singleEmitter);
            }
        });
    }

    @VisibleForTesting
    void initRankingRules(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("clientLocalContentRanking");
        RankingRules rankingRules = optJSONArray != null ? new RankingRules(optJSONArray) : null;
        boolean z = this.rankingRules != rankingRules;
        this.rankingRules = rankingRules;
        if (!z || this.rankingRules == null) {
            return;
        }
        rerankLocalCards();
    }

    public void insertNewCardIntoLocalCards(DomainCardTemplateModel domainCardTemplateModel, RankingRules rankingRules) {
        Rule rule;
        this.lock.writeLock().lock();
        try {
            try {
                rule = rankingRules.getRule(domainCardTemplateModel.getContentProvider(), domainCardTemplateModel.getContentType());
            } catch (IllegalArgumentException e) {
                Log.e(TAG, "Could not insert new card into local cards.", e);
            }
            if (rule == null) {
                throw new IllegalArgumentException("New rule was null");
            }
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= localCards.size()) {
                    break;
                }
                DomainCardTemplateModel domainCardTemplateModel2 = localCards.get(i);
                if (!RankingRules.isAfter(rule, rankingRules.getRule(domainCardTemplateModel2.getContentProvider(), domainCardTemplateModel2.getContentType()))) {
                    localCards.add(i, domainCardTemplateModel);
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                localCards.add(localCards.size(), domainCardTemplateModel);
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public /* synthetic */ void lambda$clearHomeCardsFromCache$4$HomeCardsRepository(final CompletableEmitter completableEmitter) throws Throwable {
        try {
            this.homeCacheService.clearCacheCards().subscribe(new Action1() { // from class: com.amazon.alexa.redesign.repository.-$$Lambda$HomeCardsRepository$dDthDIAH8EDXC93BhkNstikH7JY
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    HomeCardsRepository.this.lambda$null$2$HomeCardsRepository(completableEmitter, (Void) obj);
                }
            }, new Action1() { // from class: com.amazon.alexa.redesign.repository.-$$Lambda$HomeCardsRepository$r5ROr5-XHLG3UGHsarIuLq7C_fY
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    HomeCardsRepository.this.lambda$null$3$HomeCardsRepository(completableEmitter, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Failed to clear cache.");
            this.mobilytics.recordOccurrence("jasper-home-native.fault", true, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_CLEAR, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
            completableEmitter.onError(e);
        }
    }

    public /* synthetic */ void lambda$getCardsFromMockedData$5$HomeCardsRepository(SingleEmitter singleEmitter) throws Throwable {
        singleEmitter.onSuccess(HomeCardsProducer.fromRawHomeCards(ResourceUtils.getJsonObject(this.context.getAssets().open("temp.json")), this.actionFactory, this.homeOEInteractor, false));
    }

    public /* synthetic */ void lambda$getRawHomeCardsFromCache$0$HomeCardsRepository(SingleEmitter singleEmitter) throws Throwable {
        try {
            getRawHomeCardsFromCacheWithMetrics(singleEmitter);
        } catch (Throwable th) {
            if (th instanceof CacheException) {
                this.mobilytics.recordOccurrence("jasper-home-native.CacheException.CacheRead", true, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_READ, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CacheException occurred when loading cards from cache: \n");
                outline107.append(Log.getStackTraceString(th));
                Log.e(str, outline107.toString());
            } else if (th.getCause() instanceof JSONException) {
                this.mobilytics.recordOccurrence("jasper-home-native.malformed", true, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_READ, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
                String str2 = TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("JSONException occurred when loading cards from cache: \n");
                outline1072.append(Log.getStackTraceString(th));
                Log.e(str2, outline1072.toString());
            } else if (th instanceof ClassCastException) {
                this.mobilytics.recordOccurrence("jasper-home-native.malformed", true, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_READ, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
                String str3 = TAG;
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ClassCastException occurred when loading cards from cache: \n");
                outline1073.append(Log.getStackTraceString(th));
                Log.e(str3, outline1073.toString());
            }
            this.mobilytics.recordOccurrence("jasper-home-native.fault.CacheRead", true, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_READ, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
            singleEmitter.onError(th);
        }
    }

    public /* synthetic */ void lambda$getRawHomeCardsFromServer$1$HomeCardsRepository(SingleEmitter singleEmitter) throws Throwable {
        JSONObject jSONObject;
        ImmutableMap.Builder<String, String> addDebugOptions = addDebugOptions(ImmutableMap.builder());
        addDirectedId(addDebugOptions, this.identityService);
        try {
            jSONObject = getRawHomeCardsFromServerWithMetrics(singleEmitter, addDebugOptions);
        } catch (Throwable th) {
            Throwable cause = th.getCause();
            if (cause instanceof IOException) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IOException occurred when loading cards from server: \n");
                outline107.append(Log.getStackTraceString(th));
                Log.e(str, outline107.toString());
            } else if (cause instanceof JSONException) {
                this.mobilytics.recordOccurrence("jasper-home-native.malformed", true, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.GET_FEED, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
                String str2 = TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("JSONException occurred when loading cards from server: \n");
                outline1072.append(Log.getStackTraceString(th));
                Log.e(str2, outline1072.toString());
            } else if (cause instanceof CoralServiceException) {
                this.mobilytics.recordOccurrence("jasper-home-native.CoralServiceException", true, HomeMetricsRecorder.APP_COMPONENT, GeneratedOutlineSupport1.outline49("GetFeed-", ((CoralServiceException) cause).getStatusCode()), OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
                String str3 = TAG;
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("CoralServiceException occurred when loading cards from server: \n");
                outline1073.append(Log.getStackTraceString(th));
                Log.e(str3, outline1073.toString());
            }
            this.mobilytics.recordOccurrence("jasper-home-native.fault.GetFeed", true, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.GET_FEED, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
            String str4 = TAG;
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Error occurred when loading cards from remote: \n");
            outline1074.append(Log.getStackTraceString(th));
            Log.e(str4, outline1074.toString());
            singleEmitter.onError(th);
            jSONObject = null;
        }
        try {
            saveHomeCardsToCacheWithMetrics(jSONObject);
        } catch (Throwable th2) {
            if (th2 instanceof CacheException) {
                this.mobilytics.recordOccurrence("jasper-home-native.CacheException.CacheWrite", true, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_WRITE, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
                String str5 = TAG;
                StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("CacheException occurred when saving cards to cache: \n");
                outline1075.append(Log.getStackTraceString(th2));
                Log.e(str5, outline1075.toString());
            } else if (th2.getCause() instanceof JSONException) {
                this.mobilytics.recordOccurrence("jasper-home-native.malformed", true, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_WRITE, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
                String str6 = TAG;
                StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("JSONException occurred when saving cards to cache: \n");
                outline1076.append(Log.getStackTraceString(th2));
                Log.e(str6, outline1076.toString());
            }
            this.mobilytics.recordOccurrence("jasper-home-native.fault.CacheWrite", true, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_WRITE, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
        }
    }

    public /* synthetic */ void lambda$null$2$HomeCardsRepository(CompletableEmitter completableEmitter, Void r8) {
        this.mobilytics.recordOccurrence("jasper-home-native.fault", false, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_CLEAR, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
        completableEmitter.onComplete();
    }

    public /* synthetic */ void lambda$null$3$HomeCardsRepository(CompletableEmitter completableEmitter, Throwable th) {
        Log.e(TAG, "Failed to clear cache.");
        this.mobilytics.recordOccurrence("jasper-home-native.fault", true, HomeMetricsRecorder.APP_COMPONENT, HomeOEInteractor.CACHE_CLEAR, OwnerIdentifier.MOBILE_ORG_HOME_FRONTEND);
        completableEmitter.onError(th);
    }

    public void removeLocalCard(DomainCardTemplateModel domainCardTemplateModel) {
        this.lock.writeLock().lock();
        try {
            int indexWithSameCardId = indexWithSameCardId(domainCardTemplateModel);
            if (indexWithSameCardId != -1) {
                localCards.remove(indexWithSameCardId);
            }
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    public void sendOEMetricsBasedOnDomainCard(CardModel cardModel) {
        Verifier.verify(Collections.singletonList(cardModel), Collections.singletonList(new EmptyMetricsConditionCallback(this.mobilytics)));
    }
}
