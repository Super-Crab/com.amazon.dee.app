package androidx.work;

import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.WorkRequest;
import androidx.work.impl.model.WorkSpec;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class PeriodicWorkRequest extends WorkRequest {
    @SuppressLint({"MinMaxConstant"})
    public static final long MIN_PERIODIC_FLEX_MILLIS = 300000;
    @SuppressLint({"MinMaxConstant"})
    public static final long MIN_PERIODIC_INTERVAL_MILLIS = 900000;

    /* loaded from: classes.dex */
    public static final class Builder extends WorkRequest.Builder<Builder, PeriodicWorkRequest> {
        public Builder(@NonNull Class<? extends ListenableWorker> cls, long j, @NonNull TimeUnit timeUnit) {
            super(cls);
            this.mWorkSpec.setPeriodic(timeUnit.toMillis(j));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.work.WorkRequest.Builder
        @NonNull
        /* renamed from: getThis */
        public Builder mo231getThis() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.work.WorkRequest.Builder
        @NonNull
        /* renamed from: buildInternal */
        public PeriodicWorkRequest mo230buildInternal() {
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
            return new PeriodicWorkRequest(this);
        }

        @RequiresApi(26)
        public Builder(@NonNull Class<? extends ListenableWorker> cls, @NonNull Duration duration) {
            super(cls);
            this.mWorkSpec.setPeriodic(duration.toMillis());
        }

        public Builder(@NonNull Class<? extends ListenableWorker> cls, long j, @NonNull TimeUnit timeUnit, long j2, @NonNull TimeUnit timeUnit2) {
            super(cls);
            this.mWorkSpec.setPeriodic(timeUnit.toMillis(j), timeUnit2.toMillis(j2));
        }

        @RequiresApi(26)
        public Builder(@NonNull Class<? extends ListenableWorker> cls, @NonNull Duration duration, @NonNull Duration duration2) {
            super(cls);
            this.mWorkSpec.setPeriodic(duration.toMillis(), duration2.toMillis());
        }
    }

    PeriodicWorkRequest(Builder builder) {
        super(builder.mId, builder.mWorkSpec, builder.mTags);
    }
}
