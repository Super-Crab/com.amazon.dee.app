package com.google.android.gms.internal.vision;

import java.io.IOException;
/* loaded from: classes2.dex */
public class zzcx extends IOException {
    private zzdx zzlr;

    public zzcx(String str) {
        super(str);
        this.zzlr = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzcx zzcb() {
        return new zzcx("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzcx zzcc() {
        return new zzcx("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzcx zzcd() {
        return new zzcx("Protocol message contained an invalid tag (zero).");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzcy zzce() {
        return new zzcy("Protocol message tag had invalid wire type.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzcx zzcf() {
        return new zzcx("Failed to parse the message.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzcx zzcg() {
        return new zzcx("Protocol message had invalid UTF-8.");
    }

    public final zzcx zzg(zzdx zzdxVar) {
        this.zzlr = zzdxVar;
        return this;
    }
}
