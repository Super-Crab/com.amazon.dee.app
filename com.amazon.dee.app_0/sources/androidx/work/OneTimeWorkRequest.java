package androidx.work;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.work.WorkRequest;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class OneTimeWorkRequest extends WorkRequest {

    /* loaded from: classes.dex */
    public static final class Builder extends WorkRequest.Builder<Builder, OneTimeWorkRequest> {
        public Builder(@NonNull Class<? extends ListenableWorker> cls) {
            super(cls);
            this.mWorkSpec.inputMergerClassName = OverwritingInputMerger.class.getName();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.work.WorkRequest.Builder
        @NonNull
        /* renamed from: getThis */
        public Builder mo231getThis() {
            return this;
        }

        @NonNull
        public Builder setInputMerger(@NonNull Class<? extends InputMerger> cls) {
            this.mWorkSpec.inputMergerClassName = cls.getName();
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.work.WorkRequest.Builder
        @NonNull
        /* renamed from: buildInternal */
        public OneTimeWorkRequest mo230buildInternal() {
            if (this.mBackoffCriteriaSet) {
                int i = Build.VERSION.SDK_INT;
                if (this.mWorkSpec.constraints.requiresDeviceIdle()) {
                    throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
                }
            }
            WorkSpec workSpec = this.mWorkSpec;
            if (workSpec.runInForeground) {
                int i2 = Build.VERSION.SDK_INT;
                if (workSpec.constraints.requiresDeviceIdle()) {
                    throw new IllegalArgumentException("Cannot run in foreground with an idle mode constraint");
                }
            }
            return new OneTimeWorkRequest(this);
        }
    }

    OneTimeWorkRequest(Builder builder) {
        super(builder.mId, builder.mWorkSpec, builder.mTags);
    }

    @NonNull
    public static OneTimeWorkRequest from(@NonNull Class<? extends ListenableWorker> cls) {
        return new Builder(cls).build();
    }

    @NonNull
    public static List<OneTimeWorkRequest> from(@NonNull List<Class<? extends ListenableWorker>> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Class<? extends ListenableWorker> cls : list) {
            arrayList.add(new Builder(cls).build());
        }
        return arrayList;
    }
}
