package com.amazon.clouddrive.cdasdk.prompto;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.ClientConfig;
import com.amazon.clouddrive.cdasdk.prompto.collections.GroupsCollectionsCalls;
import com.amazon.clouddrive.cdasdk.prompto.collections.GroupsCollectionsCallsImpl;
import com.amazon.clouddrive.cdasdk.prompto.groups.PromptoGroupsCalls;
import com.amazon.clouddrive.cdasdk.prompto.groups.PromptoGroupsCallsImpl;
import retrofit2.Retrofit;
/* loaded from: classes11.dex */
public class PromptoCallsImpl implements PromptoCalls {
    @NonNull
    private final GroupsCollectionsCalls collectionsCalls;
    @NonNull
    private final PromptoGroupsCalls groupsCalls;

    public PromptoCallsImpl(@NonNull ClientConfig clientConfig, @NonNull Retrofit retrofit) {
        PromptoCallUtil promptoCallUtil = new PromptoCallUtil(clientConfig);
        this.collectionsCalls = new GroupsCollectionsCallsImpl(promptoCallUtil, retrofit);
        this.groupsCalls = new PromptoGroupsCallsImpl(promptoCallUtil, retrofit);
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.PromptoCalls
    @NonNull
    public GroupsCollectionsCalls getCollectionCalls() {
        return this.collectionsCalls;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.PromptoCalls
    @NonNull
    public PromptoGroupsCalls getGroupsCalls() {
        return this.groupsCalls;
    }
}
