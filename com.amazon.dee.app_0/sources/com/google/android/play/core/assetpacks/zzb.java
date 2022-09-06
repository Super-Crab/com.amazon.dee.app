package com.google.android.play.core.assetpacks;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessorykit.finishsetup.presenters.FASCardPresenter;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzb extends com.google.android.play.core.internal.zzx {
    @VisibleForTesting
    final NotificationManager zza;
    private final com.google.android.play.core.internal.zzag zzb = new com.google.android.play.core.internal.zzag("AssetPackExtractionService");
    private final Context zzc;
    private final zzbh zzd;
    private final zzl zze;
    private final zzci zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(Context context, zzbh zzbhVar, zzl zzlVar, zzci zzciVar) {
        this.zzc = context;
        this.zzd = zzbhVar;
        this.zze = zzlVar;
        this.zzf = zzciVar;
        this.zza = (NotificationManager) context.getSystemService("notification");
    }

    @TargetApi(26)
    private final synchronized void zzd(@Nullable String str) {
        if (str == null) {
            str = "File downloads by Play";
        }
        this.zza.createNotificationChannel(new NotificationChannel("playcore-assetpacks-service-notification-channel", str, 2));
    }

    private final synchronized void zze(Bundle bundle, com.google.android.play.core.internal.zzz zzzVar) throws RemoteException {
        this.zzb.zza("updateServiceState AIDL call", new Object[0]);
        if (com.google.android.play.core.internal.zzch.zzb(this.zzc) && com.google.android.play.core.internal.zzch.zza(this.zzc)) {
            int i = bundle.getInt(FASCardPresenter.KEY_ACTION_TYPE);
            this.zzf.zzc(zzzVar);
            if (i != 1) {
                if (i == 2) {
                    this.zze.zzg(false);
                    this.zzf.zzb();
                    return;
                }
                this.zzb.zzb("Unknown action type received: %d", Integer.valueOf(i));
                zzzVar.zzd(new Bundle());
                return;
            }
            int i2 = Build.VERSION.SDK_INT;
            zzd(bundle.getString("notification_channel_name"));
            this.zze.zzg(true);
            zzci zzciVar = this.zzf;
            String string = bundle.getString("notification_title");
            String string2 = bundle.getString("notification_subtext");
            long j = bundle.getLong("notification_timeout", FeatureConstants.SESSION_CHANGE_THRESHOLD_IN_MILLISECONDS);
            Parcelable parcelable = bundle.getParcelable("notification_on_click_intent");
            int i3 = Build.VERSION.SDK_INT;
            Notification.Builder timeoutAfter = new Notification.Builder(this.zzc, "playcore-assetpacks-service-notification-channel").setTimeoutAfter(j);
            if (parcelable instanceof PendingIntent) {
                timeoutAfter.setContentIntent((PendingIntent) parcelable);
            }
            Notification.Builder ongoing = timeoutAfter.setSmallIcon(17301633).setOngoing(false);
            if (string == null) {
                string = "Downloading additional file";
            }
            Notification.Builder contentTitle = ongoing.setContentTitle(string);
            if (string2 == null) {
                string2 = "Transferring";
            }
            contentTitle.setSubText(string2);
            int i4 = Build.VERSION.SDK_INT;
            int i5 = bundle.getInt("notification_color");
            if (i5 != 0) {
                timeoutAfter.setColor(i5).setVisibility(-1);
            }
            zzciVar.zza(timeoutAfter.build());
            this.zzc.bindService(new Intent(this.zzc, ExtractionForegroundService.class), this.zzf, 1);
            return;
        }
        zzzVar.zzd(new Bundle());
    }

    @Override // com.google.android.play.core.internal.zzy
    public final void zzb(Bundle bundle, com.google.android.play.core.internal.zzz zzzVar) throws RemoteException {
        this.zzb.zza("clearAssetPackStorage AIDL call", new Object[0]);
        if (com.google.android.play.core.internal.zzch.zzb(this.zzc) && com.google.android.play.core.internal.zzch.zza(this.zzc)) {
            this.zzd.zzz();
            zzzVar.zzc(new Bundle());
            return;
        }
        zzzVar.zzd(new Bundle());
    }

    @Override // com.google.android.play.core.internal.zzy
    public final void zzc(Bundle bundle, com.google.android.play.core.internal.zzz zzzVar) throws RemoteException {
        zze(bundle, zzzVar);
    }
}
