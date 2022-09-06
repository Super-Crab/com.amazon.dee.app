package com.amazon.alexa.sharing.repo.models;

import androidx.annotation.NonNull;
import com.amazon.alexa.enrollment.dialogs.DialogConstants;
import com.amazon.deecomms.common.network.AppUrl;
/* loaded from: classes10.dex */
public enum SortOrder {
    ASCENDING(AppUrl.ACMS.QueryParam.Values.ASCENDING),
    DESCENDING(DialogConstants.DESC);
    
    @NonNull
    private final String sortOrder;

    SortOrder(@NonNull String str) {
        this.sortOrder = str;
    }
}
