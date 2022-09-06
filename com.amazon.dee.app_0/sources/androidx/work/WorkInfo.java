package androidx.work;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public final class WorkInfo {
    @NonNull
    private UUID mId;
    @NonNull
    private Data mOutputData;
    @NonNull
    private Data mProgress;
    private int mRunAttemptCount;
    @NonNull
    private State mState;
    @NonNull
    private Set<String> mTags;

    /* loaded from: classes.dex */
    public enum State {
        ENQUEUED,
        RUNNING,
        SUCCEEDED,
        FAILED,
        BLOCKED,
        CANCELLED;

        public boolean isFinished() {
            return this == SUCCEEDED || this == FAILED || this == CANCELLED;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkInfo(@NonNull UUID uuid, @NonNull State state, @NonNull Data data, @NonNull List<String> list, @NonNull Data data2, int i) {
        this.mId = uuid;
        this.mState = state;
        this.mOutputData = data;
        this.mTags = new HashSet(list);
        this.mProgress = data2;
        this.mRunAttemptCount = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WorkInfo.class != obj.getClass()) {
            return false;
        }
        WorkInfo workInfo = (WorkInfo) obj;
        if (this.mRunAttemptCount != workInfo.mRunAttemptCount || !this.mId.equals(workInfo.mId) || this.mState != workInfo.mState || !this.mOutputData.equals(workInfo.mOutputData) || !this.mTags.equals(workInfo.mTags)) {
            return false;
        }
        return this.mProgress.equals(workInfo.mProgress);
    }

    @NonNull
    public UUID getId() {
        return this.mId;
    }

    @NonNull
    public Data getOutputData() {
        return this.mOutputData;
    }

    @NonNull
    public Data getProgress() {
        return this.mProgress;
    }

    @IntRange(from = 0)
    public int getRunAttemptCount() {
        return this.mRunAttemptCount;
    }

    @NonNull
    public State getState() {
        return this.mState;
    }

    @NonNull
    public Set<String> getTags() {
        return this.mTags;
    }

    public int hashCode() {
        int hashCode = this.mState.hashCode();
        int hashCode2 = this.mOutputData.hashCode();
        int hashCode3 = this.mTags.hashCode();
        return ((this.mProgress.hashCode() + ((hashCode3 + ((hashCode2 + ((hashCode + (this.mId.hashCode() * 31)) * 31)) * 31)) * 31)) * 31) + this.mRunAttemptCount;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WorkInfo{mId='");
        outline107.append(this.mId);
        outline107.append(Chars.QUOTE);
        outline107.append(", mState=");
        outline107.append(this.mState);
        outline107.append(", mOutputData=");
        outline107.append(this.mOutputData);
        outline107.append(", mTags=");
        outline107.append(this.mTags);
        outline107.append(", mProgress=");
        outline107.append(this.mProgress);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
