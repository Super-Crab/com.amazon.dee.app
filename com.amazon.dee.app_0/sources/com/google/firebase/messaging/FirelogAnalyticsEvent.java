package com.google.firebase.messaging;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
@KeepForSdk
/* loaded from: classes3.dex */
public final class FirelogAnalyticsEvent {
    private final String zza;
    private final Intent zzb;

    /* compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
    /* loaded from: classes3.dex */
    static class zza implements ObjectEncoder<FirelogAnalyticsEvent> {
        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public final /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws EncodingException, IOException {
            FirelogAnalyticsEvent firelogAnalyticsEvent = (FirelogAnalyticsEvent) obj;
            ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
            Intent zza = firelogAnalyticsEvent.zza();
            objectEncoderContext2.mo8298add("ttl", zzo.zzf(zza));
            objectEncoderContext2.mo8300add("event", firelogAnalyticsEvent.zzb());
            objectEncoderContext2.mo8300add("instanceId", zzo.zzc());
            objectEncoderContext2.mo8298add("priority", zzo.zzm(zza));
            objectEncoderContext2.mo8300add(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, zzo.zzb());
            objectEncoderContext2.mo8300add("sdkPlatform", "ANDROID");
            objectEncoderContext2.mo8300add("messageType", zzo.zzk(zza));
            String zzj = zzo.zzj(zza);
            if (zzj != null) {
                objectEncoderContext2.mo8300add("messageId", zzj);
            }
            String zzl = zzo.zzl(zza);
            if (zzl != null) {
                objectEncoderContext2.mo8300add("topic", zzl);
            }
            String zzg = zzo.zzg(zza);
            if (zzg != null) {
                objectEncoderContext2.mo8300add("collapseKey", zzg);
            }
            if (zzo.zzi(zza) != null) {
                objectEncoderContext2.mo8300add("analyticsLabel", zzo.zzi(zza));
            }
            if (zzo.zzh(zza) != null) {
                objectEncoderContext2.mo8300add("composerLabel", zzo.zzh(zza));
            }
            String zzd = zzo.zzd();
            if (zzd != null) {
                objectEncoderContext2.mo8300add("projectNumber", zzd);
            }
        }
    }

    /* compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
    /* loaded from: classes3.dex */
    static final class zzb implements ObjectEncoder<zzc> {
        @Override // com.google.firebase.encoders.ObjectEncoder, com.google.firebase.encoders.Encoder
        public final /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws EncodingException, IOException {
            objectEncoderContext.mo8300add("messaging_client_event", ((zzc) obj).zza());
        }
    }

    /* compiled from: com.google.firebase:firebase-messaging@@20.1.0 */
    /* loaded from: classes3.dex */
    static final class zzc {
        private final FirelogAnalyticsEvent zza;

        /* JADX INFO: Access modifiers changed from: package-private */
        public zzc(@NonNull FirelogAnalyticsEvent firelogAnalyticsEvent) {
            this.zza = (FirelogAnalyticsEvent) Preconditions.checkNotNull(firelogAnalyticsEvent);
        }

        @NonNull
        final FirelogAnalyticsEvent zza() {
            return this.zza;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirelogAnalyticsEvent(@NonNull String str, @NonNull Intent intent) {
        this.zza = Preconditions.checkNotEmpty(str, "evenType must be non-null");
        this.zzb = (Intent) Preconditions.checkNotNull(intent, "intent must be non-null");
    }

    @NonNull
    final Intent zza() {
        return this.zzb;
    }

    @NonNull
    final String zzb() {
        return this.zza;
    }
}
