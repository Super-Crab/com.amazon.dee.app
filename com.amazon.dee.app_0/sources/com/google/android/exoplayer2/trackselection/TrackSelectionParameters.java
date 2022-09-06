package com.google.android.exoplayer2.trackselection;

import android.content.Context;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.accessibility.CaptioningManager;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
/* loaded from: classes2.dex */
public class TrackSelectionParameters implements Parcelable {
    public final int disabledTextTrackSelectionFlags;
    public final ImmutableList<String> preferredAudioLanguages;
    public final int preferredAudioRoleFlags;
    public final ImmutableList<String> preferredTextLanguages;
    public final int preferredTextRoleFlags;
    public final boolean selectUndeterminedTextLanguage;
    public static final TrackSelectionParameters DEFAULT_WITHOUT_CONTEXT = new Builder().mo7434build();
    @Deprecated
    public static final TrackSelectionParameters DEFAULT = DEFAULT_WITHOUT_CONTEXT;
    public static final Parcelable.Creator<TrackSelectionParameters> CREATOR = new Parcelable.Creator<TrackSelectionParameters>() { // from class: com.google.android.exoplayer2.trackselection.TrackSelectionParameters.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public TrackSelectionParameters mo7446createFromParcel(Parcel parcel) {
            return new TrackSelectionParameters(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public TrackSelectionParameters[] mo7447newArray(int i) {
            return new TrackSelectionParameters[i];
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrackSelectionParameters(ImmutableList<String> immutableList, int i, ImmutableList<String> immutableList2, int i2, boolean z, int i3) {
        this.preferredAudioLanguages = immutableList;
        this.preferredAudioRoleFlags = i;
        this.preferredTextLanguages = immutableList2;
        this.preferredTextRoleFlags = i2;
        this.selectUndeterminedTextLanguage = z;
        this.disabledTextTrackSelectionFlags = i3;
    }

    public static TrackSelectionParameters getDefaults(Context context) {
        return new Builder(context).mo7434build();
    }

    /* renamed from: buildUpon */
    public Builder mo7431buildUpon() {
        return new Builder(this);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TrackSelectionParameters trackSelectionParameters = (TrackSelectionParameters) obj;
        return this.preferredAudioLanguages.equals(trackSelectionParameters.preferredAudioLanguages) && this.preferredAudioRoleFlags == trackSelectionParameters.preferredAudioRoleFlags && this.preferredTextLanguages.equals(trackSelectionParameters.preferredTextLanguages) && this.preferredTextRoleFlags == trackSelectionParameters.preferredTextRoleFlags && this.selectUndeterminedTextLanguage == trackSelectionParameters.selectUndeterminedTextLanguage && this.disabledTextTrackSelectionFlags == trackSelectionParameters.disabledTextTrackSelectionFlags;
    }

    public int hashCode() {
        return ((((((this.preferredTextLanguages.hashCode() + ((((this.preferredAudioLanguages.hashCode() + 31) * 31) + this.preferredAudioRoleFlags) * 31)) * 31) + this.preferredTextRoleFlags) * 31) + (this.selectUndeterminedTextLanguage ? 1 : 0)) * 31) + this.disabledTextTrackSelectionFlags;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.preferredAudioLanguages);
        parcel.writeInt(this.preferredAudioRoleFlags);
        parcel.writeList(this.preferredTextLanguages);
        parcel.writeInt(this.preferredTextRoleFlags);
        Util.writeBoolean(parcel, this.selectUndeterminedTextLanguage);
        parcel.writeInt(this.disabledTextTrackSelectionFlags);
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        int disabledTextTrackSelectionFlags;
        ImmutableList<String> preferredAudioLanguages;
        int preferredAudioRoleFlags;
        ImmutableList<String> preferredTextLanguages;
        int preferredTextRoleFlags;
        boolean selectUndeterminedTextLanguage;

        public Builder(Context context) {
            this();
            mo7440setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(context);
        }

        @RequiresApi(19)
        private void setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettingsV19(Context context) {
            CaptioningManager captioningManager;
            if ((Util.SDK_INT >= 23 || Looper.myLooper() != null) && (captioningManager = (CaptioningManager) context.getSystemService("captioning")) != null && captioningManager.isEnabled()) {
                this.preferredTextRoleFlags = PhotoshopDirectory.TAG_PATH_SELECTION_STATE;
                Locale locale = captioningManager.getLocale();
                if (locale == null) {
                    return;
                }
                this.preferredTextLanguages = ImmutableList.of(Util.getLocaleLanguageTag(locale));
            }
        }

        /* renamed from: build */
        public TrackSelectionParameters mo7434build() {
            return new TrackSelectionParameters(this.preferredAudioLanguages, this.preferredAudioRoleFlags, this.preferredTextLanguages, this.preferredTextRoleFlags, this.selectUndeterminedTextLanguage, this.disabledTextTrackSelectionFlags);
        }

        /* renamed from: setDisabledTextTrackSelectionFlags */
        public Builder mo7435setDisabledTextTrackSelectionFlags(int i) {
            this.disabledTextTrackSelectionFlags = i;
            return this;
        }

        /* renamed from: setPreferredAudioLanguage */
        public Builder mo7436setPreferredAudioLanguage(@Nullable String str) {
            if (str == null) {
                return mo7437setPreferredAudioLanguages(new String[0]);
            }
            return mo7437setPreferredAudioLanguages(str);
        }

        /* renamed from: setPreferredAudioLanguages */
        public Builder mo7437setPreferredAudioLanguages(String... strArr) {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (String str : (String[]) Assertions.checkNotNull(strArr)) {
                builder.mo7849add((ImmutableList.Builder) Util.normalizeLanguageCode((String) Assertions.checkNotNull(str)));
            }
            this.preferredAudioLanguages = builder.mo7852build();
            return this;
        }

        /* renamed from: setPreferredAudioRoleFlags */
        public Builder mo7438setPreferredAudioRoleFlags(int i) {
            this.preferredAudioRoleFlags = i;
            return this;
        }

        /* renamed from: setPreferredTextLanguage */
        public Builder mo7439setPreferredTextLanguage(@Nullable String str) {
            if (str == null) {
                return mo7441setPreferredTextLanguages(new String[0]);
            }
            return mo7441setPreferredTextLanguages(str);
        }

        /* renamed from: setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings */
        public Builder mo7440setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettings(Context context) {
            if (Util.SDK_INT >= 19) {
                setPreferredTextLanguageAndRoleFlagsToCaptioningManagerSettingsV19(context);
            }
            return this;
        }

        /* renamed from: setPreferredTextLanguages */
        public Builder mo7441setPreferredTextLanguages(String... strArr) {
            ImmutableList.Builder builder = ImmutableList.builder();
            for (String str : (String[]) Assertions.checkNotNull(strArr)) {
                builder.mo7849add((ImmutableList.Builder) Util.normalizeLanguageCode((String) Assertions.checkNotNull(str)));
            }
            this.preferredTextLanguages = builder.mo7852build();
            return this;
        }

        /* renamed from: setPreferredTextRoleFlags */
        public Builder mo7442setPreferredTextRoleFlags(int i) {
            this.preferredTextRoleFlags = i;
            return this;
        }

        /* renamed from: setSelectUndeterminedTextLanguage */
        public Builder mo7443setSelectUndeterminedTextLanguage(boolean z) {
            this.selectUndeterminedTextLanguage = z;
            return this;
        }

        @Deprecated
        public Builder() {
            this.preferredAudioLanguages = ImmutableList.of();
            this.preferredAudioRoleFlags = 0;
            this.preferredTextLanguages = ImmutableList.of();
            this.preferredTextRoleFlags = 0;
            this.selectUndeterminedTextLanguage = false;
            this.disabledTextTrackSelectionFlags = 0;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(TrackSelectionParameters trackSelectionParameters) {
            this.preferredAudioLanguages = trackSelectionParameters.preferredAudioLanguages;
            this.preferredAudioRoleFlags = trackSelectionParameters.preferredAudioRoleFlags;
            this.preferredTextLanguages = trackSelectionParameters.preferredTextLanguages;
            this.preferredTextRoleFlags = trackSelectionParameters.preferredTextRoleFlags;
            this.selectUndeterminedTextLanguage = trackSelectionParameters.selectUndeterminedTextLanguage;
            this.disabledTextTrackSelectionFlags = trackSelectionParameters.disabledTextTrackSelectionFlags;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrackSelectionParameters(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        parcel.readList(arrayList, null);
        this.preferredAudioLanguages = ImmutableList.copyOf((Collection) arrayList);
        this.preferredAudioRoleFlags = parcel.readInt();
        ArrayList arrayList2 = new ArrayList();
        parcel.readList(arrayList2, null);
        this.preferredTextLanguages = ImmutableList.copyOf((Collection) arrayList2);
        this.preferredTextRoleFlags = parcel.readInt();
        this.selectUndeterminedTextLanguage = Util.readBoolean(parcel);
        this.disabledTextTrackSelectionFlags = parcel.readInt();
    }
}
