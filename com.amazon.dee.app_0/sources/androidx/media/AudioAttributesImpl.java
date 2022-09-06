package androidx.media;

import androidx.versionedparcelable.VersionedParcelable;
/* loaded from: classes.dex */
interface AudioAttributesImpl extends VersionedParcelable {

    /* loaded from: classes.dex */
    public interface Builder {
        AudioAttributesImpl build();

        /* renamed from: setContentType */
        Builder mo180setContentType(int i);

        /* renamed from: setFlags */
        Builder mo181setFlags(int i);

        /* renamed from: setLegacyStreamType */
        Builder mo182setLegacyStreamType(int i);

        /* renamed from: setUsage */
        Builder mo183setUsage(int i);
    }

    Object getAudioAttributes();

    int getContentType();

    int getFlags();

    int getLegacyStreamType();

    int getRawLegacyStreamType();

    int getUsage();

    int getVolumeControlStream();
}
