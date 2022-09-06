package com.amazon.deecomms.conversation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.calling.model.VoxCallInfo;
/* loaded from: classes12.dex */
public interface ConversationService {
    void acceptCall(@NonNull String str);

    void beginCall(@NonNull VoxCallInfo voxCallInfo);

    void enableVideoStreamInVideoCall(@NonNull boolean z);

    void endActiveCallIfAny(@NonNull String str);

    void ensureInitialized();

    @Nullable
    CharSequence getProfileName();

    void initialize();

    boolean isUserProvisioned();

    void passDirectivePayload(@NonNull String str, @NonNull String str2, @NonNull String str3);
}
