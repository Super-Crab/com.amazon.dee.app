package com.amazon.clouddrive.cdasdk.onelens;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
/* loaded from: classes11.dex */
public interface OneLensRetrofitBinding {
    @GET("account/contactinfo/{customerId}")
    Single<GetContactInfoResponse> getContactInfo(@Path("customerId") String str);
}
