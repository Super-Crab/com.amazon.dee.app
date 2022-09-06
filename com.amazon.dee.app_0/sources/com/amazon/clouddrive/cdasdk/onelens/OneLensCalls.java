package com.amazon.clouddrive.cdasdk.onelens;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes11.dex */
public interface OneLensCalls {
    @NonNull
    Single<GetContactInfoResponse> getContactInfo(@NonNull GetContactInfoRequest getContactInfoRequest);
}
