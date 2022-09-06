package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.configuration.AccountConfiguration;
import com.amazon.clouddrive.configuration.SourceInfoCache;
import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.model.SourceInfoResponse;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class SourceInfoGenerator {
    private static final Object lock = new Object();
    private final AccountConfiguration mAccountConfiguration;
    private final OperationFactory mOperationFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SourceInfoGenerator(AccountConfiguration accountConfiguration, OperationFactory operationFactory) {
        this.mAccountConfiguration = accountConfiguration;
        this.mOperationFactory = operationFactory;
    }

    private BasicSourceInfo getInitializedSourceInfo() throws CloudDriveException, InterruptedException {
        BasicSourceInfo basicSourceInfo;
        synchronized (lock) {
            SourceInfoCache mo2996getSourceInfoCache = this.mAccountConfiguration.mo2996getSourceInfoCache();
            if (mo2996getSourceInfoCache == null) {
                basicSourceInfo = null;
            } else if (mo2996getSourceInfoCache.isSourceInfoCached()) {
                basicSourceInfo = mo2996getSourceInfoCache.getSourceInfo();
            } else {
                BasicSourceInfo basicSourceInfo2 = new BasicSourceInfo(fetchSourceInfo());
                mo2996getSourceInfoCache.cacheSourceInfo(basicSourceInfo2);
                basicSourceInfo = basicSourceInfo2;
            }
        }
        return basicSourceInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BasicSourceInfo createSourceInfo() throws CloudDriveException, InterruptedException {
        return getInitializedSourceInfo();
    }

    protected SourceInfoResponse fetchSourceInfo() throws InterruptedException, CloudDriveException {
        return this.mOperationFactory.newSetupSourceOperation(this.mAccountConfiguration.mo2996getSourceInfoCache().mo2997getSetupSourceRequest()).call();
    }
}
