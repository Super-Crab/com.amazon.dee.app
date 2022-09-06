package androidx.media;

import android.annotation.SuppressLint;
import android.media.AudioAttributes;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.media.AudioAttributesImpl;
import com.android.tools.r8.GeneratedOutlineSupport1;
@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class AudioAttributesImplApi21 implements AudioAttributesImpl {
    private static final String TAG = "AudioAttributesCompat21";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public AudioAttributes mAudioAttributes;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public int mLegacyStreamType;

    /* loaded from: classes.dex */
    static class Builder implements AudioAttributesImpl.Builder {
        final AudioAttributes.Builder mFwkBuilder;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
            this.mFwkBuilder = new AudioAttributes.Builder();
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        public AudioAttributesImpl build() {
            return new AudioAttributesImplApi21(this.mFwkBuilder.build());
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        /* renamed from: setContentType  reason: collision with other method in class */
        public Builder mo180setContentType(int i) {
            this.mFwkBuilder.setContentType(i);
            return this;
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        /* renamed from: setFlags  reason: collision with other method in class */
        public Builder mo181setFlags(int i) {
            this.mFwkBuilder.setFlags(i);
            return this;
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        /* renamed from: setLegacyStreamType  reason: collision with other method in class */
        public Builder mo182setLegacyStreamType(int i) {
            this.mFwkBuilder.setLegacyStreamType(i);
            return this;
        }

        @Override // androidx.media.AudioAttributesImpl.Builder
        /* renamed from: setUsage  reason: collision with other method in class */
        public Builder mo183setUsage(int i) {
            if (i == 16) {
                i = 12;
            }
            this.mFwkBuilder.setUsage(i);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(Object obj) {
            this.mFwkBuilder = new AudioAttributes.Builder((AudioAttributes) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioAttributesImplApi21() {
        this.mLegacyStreamType = -1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesImplApi21)) {
            return false;
        }
        return this.mAudioAttributes.equals(((AudioAttributesImplApi21) obj).mAudioAttributes);
    }

    @Override // androidx.media.AudioAttributesImpl
    public Object getAudioAttributes() {
        return this.mAudioAttributes;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getContentType() {
        return this.mAudioAttributes.getContentType();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getFlags() {
        return this.mAudioAttributes.getFlags();
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getLegacyStreamType() {
        int i = this.mLegacyStreamType;
        return i != -1 ? i : AudioAttributesCompat.toVolumeStreamType(false, getFlags(), getUsage());
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getRawLegacyStreamType() {
        return this.mLegacyStreamType;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getUsage() {
        return this.mAudioAttributes.getUsage();
    }

    @Override // androidx.media.AudioAttributesImpl
    @SuppressLint({"NewApi"})
    public int getVolumeControlStream() {
        return AudioAttributesCompat.toVolumeStreamType(true, getFlags(), getUsage());
    }

    public int hashCode() {
        return this.mAudioAttributes.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioAttributesCompat: audioattributes=");
        outline107.append(this.mAudioAttributes);
        return outline107.toString();
    }

    AudioAttributesImplApi21(AudioAttributes audioAttributes) {
        this(audioAttributes, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioAttributesImplApi21(AudioAttributes audioAttributes, int i) {
        this.mLegacyStreamType = -1;
        this.mAudioAttributes = audioAttributes;
        this.mLegacyStreamType = i;
    }
}
