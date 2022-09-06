package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.extended.configuration.AccountConfigurationExtended;
import com.amazon.clouddrive.model.SourceInfoResponse;
/* loaded from: classes11.dex */
public class SourceInfoExtendedGenerator extends SourceInfoGenerator {
    private final AccountConfigurationExtended mAccountConfigurationExtended;
    private final ExtendedOperationFactory mOperationFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SourceInfoExtendedGenerator(AccountConfigurationExtended accountConfigurationExtended, ExtendedOperationFactory extendedOperationFactory) {
        super(accountConfigurationExtended, extendedOperationFactory);
        this.mAccountConfigurationExtended = accountConfigurationExtended;
        this.mOperationFactory = extendedOperationFactory;
    }

    @Override // com.amazon.clouddrive.internal.SourceInfoGenerator
    protected SourceInfoResponse fetchSourceInfo() throws InterruptedException, CloudDriveException {
        return this.mOperationFactory.newSetupSourceExtendedOperation(this.mAccountConfigurationExtended.mo2996getSourceInfoCache().mo2997getSetupSourceRequest()).call();
    }
}
