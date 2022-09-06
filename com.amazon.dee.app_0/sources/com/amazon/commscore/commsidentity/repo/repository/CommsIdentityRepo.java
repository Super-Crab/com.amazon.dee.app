package com.amazon.commscore.commsidentity.repo.repository;

import android.annotation.SuppressLint;
import androidx.core.util.Pair;
import com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao;
import com.amazon.commscore.commsidentity.database.schema.Account;
import com.amazon.commscore.commsidentity.remote.client.AcmsClient;
import com.amazon.commscore.commsidentity.repo.mapper.AccountForDirectedIdMapper;
import com.amazon.commscore.commsidentity.repo.mapper.IdentityV2Mapper;
import com.amazon.commscore.commsidentity.repo.model.AccountForDirectedId;
import com.amazon.commscore.commsidentity.repo.model.IdentityV2;
import com.amazon.commscore.commsidentity.repo.provider.CommsIdentityProvider;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class CommsIdentityRepo implements CommsIdentityProvider {
    private AccountForDirectedIdMapper accountForDirectedIdMapper;
    private AcmsClient acmsClient;
    private CommsIdentityDao commsIdentityDao;
    private IdentityV2Mapper identityV2Mapper;

    @Inject
    public CommsIdentityRepo(AcmsClient acmsClient, CommsIdentityDao commsIdentityDao, IdentityV2Mapper identityV2Mapper, AccountForDirectedIdMapper accountForDirectedIdMapper) {
        this.acmsClient = acmsClient;
        this.commsIdentityDao = commsIdentityDao;
        this.identityV2Mapper = identityV2Mapper;
        this.accountForDirectedIdMapper = accountForDirectedIdMapper;
    }

    @Override // com.amazon.commscore.commsidentity.repo.provider.CommsIdentityProvider
    public Flowable<AccountForDirectedId> getAccount(String str) {
        return Flowable.just(this.commsIdentityDao.getAccountDetailsForDirectedId(str)).map(new Function() { // from class: com.amazon.commscore.commsidentity.repo.repository.-$$Lambda$CommsIdentityRepo$L6kRC9ojHOi0loUTfDerquH21ws
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CommsIdentityRepo.this.lambda$getAccount$4$CommsIdentityRepo((Account) obj);
            }
        });
    }

    @Override // com.amazon.commscore.commsidentity.repo.provider.CommsIdentityProvider
    public Flowable<IdentityV2> getIdentityV2(String str) {
        return Flowable.just(this.commsIdentityDao.getIdentityV2ForDirectedId(str)).map(new Function() { // from class: com.amazon.commscore.commsidentity.repo.repository.-$$Lambda$CommsIdentityRepo$StG6NFTbnUUQkTcSVkFxam4_Rng
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CommsIdentityRepo.this.lambda$getIdentityV2$3$CommsIdentityRepo((com.amazon.commscore.commsidentity.database.schema.IdentityV2) obj);
            }
        });
    }

    public /* synthetic */ AccountForDirectedId lambda$getAccount$4$CommsIdentityRepo(Account account) throws Throwable {
        return this.accountForDirectedIdMapper.fromDatabaseModel(account);
    }

    public /* synthetic */ IdentityV2 lambda$getIdentityV2$3$CommsIdentityRepo(com.amazon.commscore.commsidentity.database.schema.IdentityV2 identityV2) throws Throwable {
        return this.identityV2Mapper.fromDatabaseModel(identityV2);
    }

    public /* synthetic */ SingleSource lambda$refreshCommsIdentity$0$CommsIdentityRepo(com.amazon.commscore.commsidentity.remote.model.AccountForDirectedId accountForDirectedId) throws Throwable {
        return Single.zip(Single.just(accountForDirectedId), this.acmsClient.getIdentityV2ForDirectedId(accountForDirectedId.getCommsId()), $$Lambda$HXLOmC9t7mZ2fqK3vqFuMkyVfRQ.INSTANCE);
    }

    public /* synthetic */ Pair lambda$refreshCommsIdentity$1$CommsIdentityRepo(Pair pair) throws Throwable {
        com.amazon.commscore.commsidentity.remote.model.AccountForDirectedId accountForDirectedId = (com.amazon.commscore.commsidentity.remote.model.AccountForDirectedId) pair.first;
        com.amazon.commscore.commsidentity.remote.model.IdentityV2 identityV2 = (com.amazon.commscore.commsidentity.remote.model.IdentityV2) pair.second;
        if (identityV2 != null) {
            if (accountForDirectedId != null) {
                return Pair.create(this.accountForDirectedIdMapper.fromAPIModel(accountForDirectedId), this.identityV2Mapper.fromAPIModel(identityV2));
            }
            throw new Exception("Accounts query failed");
        }
        throw new Exception("IdentityV2 query failed");
    }

    public /* synthetic */ void lambda$refreshCommsIdentity$2$CommsIdentityRepo(String str, Pair pair) throws Throwable {
        Account databaseModel = this.accountForDirectedIdMapper.toDatabaseModel((AccountForDirectedId) pair.first);
        com.amazon.commscore.commsidentity.database.schema.IdentityV2 databaseModel2 = this.identityV2Mapper.toDatabaseModel((IdentityV2) pair.second);
        databaseModel2.setDirectedId(str);
        this.commsIdentityDao.insertIdentityV2AndAccountDetails(databaseModel2, databaseModel);
    }

    @Override // com.amazon.commscore.commsidentity.repo.provider.CommsIdentityProvider
    @SuppressLint({"CheckResult"})
    public Completable refreshCommsIdentity(final String str) {
        return Completable.fromSingle(this.acmsClient.getFullAccountDetailsForDirectedId(str).flatMap(new Function() { // from class: com.amazon.commscore.commsidentity.repo.repository.-$$Lambda$CommsIdentityRepo$eF0d9NxFgVqe_5eDsU_9SmR_RdQ
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CommsIdentityRepo.this.lambda$refreshCommsIdentity$0$CommsIdentityRepo((com.amazon.commscore.commsidentity.remote.model.AccountForDirectedId) obj);
            }
        }).map(new Function() { // from class: com.amazon.commscore.commsidentity.repo.repository.-$$Lambda$CommsIdentityRepo$C5GKCssM1AvTq2TWEd7QdfFVb-c
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return CommsIdentityRepo.this.lambda$refreshCommsIdentity$1$CommsIdentityRepo((Pair) obj);
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.commscore.commsidentity.repo.repository.-$$Lambda$CommsIdentityRepo$7dg3T_YcOX4VT4P5fpep-8kpZc4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CommsIdentityRepo.this.lambda$refreshCommsIdentity$2$CommsIdentityRepo(str, (Pair) obj);
            }
        }));
    }
}
