package com.amazon.commscore.commsidentity.remote.api;

import com.amazon.commscore.commsidentity.remote.constants.Url;
import com.amazon.commscore.commsidentity.remote.model.AccountForDirectedId;
import com.amazon.commscore.commsidentity.remote.model.IdentityV2;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
/* loaded from: classes12.dex */
public interface AcmsApi {
    @GET(Url.Acms.PATH.ACCOUNTS_FOR_DIRECTEDID)
    Single<AccountForDirectedId> getAccountForDirectedId(@Path("directedId") String str, @Query("view") String str2);

    @GET(Url.Acms.PATH.IDENTITYV2)
    Single<IdentityV2> getIdentityV2(@Path("directedId") String str, @Query("includeUserName") Boolean bool);
}
