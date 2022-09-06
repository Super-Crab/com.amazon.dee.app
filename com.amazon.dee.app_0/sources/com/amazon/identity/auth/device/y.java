package com.amazon.identity.auth.device;

import android.content.Intent;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface y {
    boolean doesAccountHaveMapping(String str, MultipleAccountManager.AccountMappingType accountMappingType);

    String getAccountForMapping(MultipleAccountManager.AccountMappingType... accountMappingTypeArr);

    String getAccountMappingOwner(MultipleAccountManager.AccountMappingType accountMappingType);

    Intent getIntentToRemoveAccountMapping(MultipleAccountManager.AccountMappingType accountMappingType);

    boolean removeAccountMappings(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr);

    boolean setAccountMappings(String str, MultipleAccountManager.AccountMappingType... accountMappingTypeArr);
}
