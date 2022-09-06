package com.google.android.play.core.tasks;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public class NativeOnCompleteListener implements OnCompleteListener<Object> {
    private final long zza;
    private final int zzb;

    public NativeOnCompleteListener(long j, int i) {
        this.zza = j;
        this.zzb = i;
    }

    public native void nativeOnComplete(long j, int i, @Nullable Object obj, int i2);

    @Override // com.google.android.play.core.tasks.OnCompleteListener
    public void onComplete(Task<Object> task) {
        if (task.isComplete()) {
            if (task.isSuccessful()) {
                nativeOnComplete(this.zza, this.zzb, task.getResult(), 0);
                return;
            }
            Exception exception = task.getException();
            if (!(exception instanceof zzj)) {
                nativeOnComplete(this.zza, this.zzb, null, -100);
                return;
            }
            int errorCode = ((zzj) exception).getErrorCode();
            if (errorCode != 0) {
                nativeOnComplete(this.zza, this.zzb, null, errorCode);
                return;
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline27(51, "TaskException has error code 0 on task: ", this.zzb));
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline27(50, "onComplete called for incomplete task: ", this.zzb));
    }
}
