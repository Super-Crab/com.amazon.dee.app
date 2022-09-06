package com.amazon.clouddrive.configuration;

import com.amazon.clouddrive.internal.BasicSourceInfo;
import com.amazon.clouddrive.model.SetupSourceRequest;
/* loaded from: classes11.dex */
public interface SourceInfoCache {
    void cacheSourceInfo(BasicSourceInfo basicSourceInfo);

    void clear();

    /* renamed from: getSetupSourceRequest */
    SetupSourceRequest mo2997getSetupSourceRequest();

    BasicSourceInfo getSourceInfo();

    boolean isSourceInfoCached();
}
