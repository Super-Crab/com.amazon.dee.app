package com.amazon.clouddrive.cdasdk.prompto;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.prompto.collections.GroupsCollectionsCalls;
import com.amazon.clouddrive.cdasdk.prompto.groups.PromptoGroupsCalls;
/* loaded from: classes11.dex */
public interface PromptoCalls {
    @NonNull
    GroupsCollectionsCalls getCollectionCalls();

    @NonNull
    PromptoGroupsCalls getGroupsCalls();
}
