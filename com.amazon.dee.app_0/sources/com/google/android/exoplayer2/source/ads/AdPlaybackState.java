package com.google.android.exoplayer2.source.ads;

import android.net.Uri;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes2.dex */
public final class AdPlaybackState {
    public static final int AD_STATE_AVAILABLE = 1;
    public static final int AD_STATE_ERROR = 4;
    public static final int AD_STATE_PLAYED = 3;
    public static final int AD_STATE_SKIPPED = 2;
    public static final int AD_STATE_UNAVAILABLE = 0;
    public static final AdPlaybackState NONE = new AdPlaybackState(null, new long[0], null, 0, C.TIME_UNSET);
    public final int adGroupCount;
    public final long[] adGroupTimesUs;
    public final AdGroup[] adGroups;
    public final long adResumePositionUs;
    @Nullable
    public final Object adsId;
    public final long contentDurationUs;

    /* loaded from: classes2.dex */
    public static final class AdGroup {
        public final int count;
        public final long[] durationsUs;
        public final int[] states;
        public final Uri[] uris;

        public AdGroup() {
            this(-1, new int[0], new Uri[0], new long[0]);
        }

        @CheckResult
        private static long[] copyDurationsUsWithSpaceForAdCount(long[] jArr, int i) {
            int length = jArr.length;
            int max = Math.max(i, length);
            long[] copyOf = Arrays.copyOf(jArr, max);
            Arrays.fill(copyOf, length, max, (long) C.TIME_UNSET);
            return copyOf;
        }

        @CheckResult
        private static int[] copyStatesWithSpaceForAdCount(int[] iArr, int i) {
            int length = iArr.length;
            int max = Math.max(i, length);
            int[] copyOf = Arrays.copyOf(iArr, max);
            Arrays.fill(copyOf, length, max, 0);
            return copyOf;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || AdGroup.class != obj.getClass()) {
                return false;
            }
            AdGroup adGroup = (AdGroup) obj;
            return this.count == adGroup.count && Arrays.equals(this.uris, adGroup.uris) && Arrays.equals(this.states, adGroup.states) && Arrays.equals(this.durationsUs, adGroup.durationsUs);
        }

        public int getFirstAdIndexToPlay() {
            return getNextAdIndexToPlay(-1);
        }

        public int getNextAdIndexToPlay(int i) {
            int i2 = i + 1;
            while (true) {
                int[] iArr = this.states;
                if (i2 >= iArr.length || iArr[i2] == 0 || iArr[i2] == 1) {
                    break;
                }
                i2++;
            }
            return i2;
        }

        public boolean hasUnplayedAds() {
            return this.count == -1 || getFirstAdIndexToPlay() < this.count;
        }

        public int hashCode() {
            int hashCode = Arrays.hashCode(this.states);
            return Arrays.hashCode(this.durationsUs) + ((hashCode + (((this.count * 31) + Arrays.hashCode(this.uris)) * 31)) * 31);
        }

        @CheckResult
        public AdGroup withAdCount(int i) {
            return new AdGroup(i, copyStatesWithSpaceForAdCount(this.states, i), (Uri[]) Arrays.copyOf(this.uris, i), copyDurationsUsWithSpaceForAdCount(this.durationsUs, i));
        }

        @CheckResult
        public AdGroup withAdDurationsUs(long[] jArr) {
            int length = jArr.length;
            Uri[] uriArr = this.uris;
            if (length < uriArr.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, uriArr.length);
            } else if (this.count != -1 && jArr.length > uriArr.length) {
                jArr = Arrays.copyOf(jArr, uriArr.length);
            }
            return new AdGroup(this.count, this.states, this.uris, jArr);
        }

        @CheckResult
        public AdGroup withAdState(int i, int i2) {
            int i3 = this.count;
            boolean z = false;
            Assertions.checkArgument(i3 == -1 || i2 < i3);
            int[] copyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i2 + 1);
            if (copyStatesWithSpaceForAdCount[i2] == 0 || copyStatesWithSpaceForAdCount[i2] == 1 || copyStatesWithSpaceForAdCount[i2] == i) {
                z = true;
            }
            Assertions.checkArgument(z);
            long[] jArr = this.durationsUs;
            if (jArr.length != copyStatesWithSpaceForAdCount.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, copyStatesWithSpaceForAdCount.length);
            }
            Uri[] uriArr = this.uris;
            if (uriArr.length != copyStatesWithSpaceForAdCount.length) {
                uriArr = (Uri[]) Arrays.copyOf(uriArr, copyStatesWithSpaceForAdCount.length);
            }
            copyStatesWithSpaceForAdCount[i2] = i;
            return new AdGroup(this.count, copyStatesWithSpaceForAdCount, uriArr, jArr);
        }

        @CheckResult
        public AdGroup withAdUri(Uri uri, int i) {
            int[] copyStatesWithSpaceForAdCount = copyStatesWithSpaceForAdCount(this.states, i + 1);
            long[] jArr = this.durationsUs;
            if (jArr.length != copyStatesWithSpaceForAdCount.length) {
                jArr = copyDurationsUsWithSpaceForAdCount(jArr, copyStatesWithSpaceForAdCount.length);
            }
            Uri[] uriArr = (Uri[]) Arrays.copyOf(this.uris, copyStatesWithSpaceForAdCount.length);
            uriArr[i] = uri;
            copyStatesWithSpaceForAdCount[i] = 1;
            return new AdGroup(this.count, copyStatesWithSpaceForAdCount, uriArr, jArr);
        }

        @CheckResult
        public AdGroup withAllAdsSkipped() {
            if (this.count == -1) {
                return new AdGroup(0, new int[0], new Uri[0], new long[0]);
            }
            int[] iArr = this.states;
            int length = iArr.length;
            int[] copyOf = Arrays.copyOf(iArr, length);
            for (int i = 0; i < length; i++) {
                if (copyOf[i] == 1 || copyOf[i] == 0) {
                    copyOf[i] = 2;
                }
            }
            return new AdGroup(length, copyOf, this.uris, this.durationsUs);
        }

        private AdGroup(int i, int[] iArr, Uri[] uriArr, long[] jArr) {
            Assertions.checkArgument(iArr.length == uriArr.length);
            this.count = i;
            this.states = iArr;
            this.uris = uriArr;
            this.durationsUs = jArr;
        }
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface AdState {
    }

    public AdPlaybackState(Object obj, long... jArr) {
        this(obj, jArr, null, 0L, C.TIME_UNSET);
    }

    private boolean isPositionBeforeAdGroup(long j, long j2, int i) {
        if (j == Long.MIN_VALUE) {
            return false;
        }
        long j3 = this.adGroupTimesUs[i];
        return j3 == Long.MIN_VALUE ? j2 == C.TIME_UNSET || j < j2 : j < j3;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AdPlaybackState.class != obj.getClass()) {
            return false;
        }
        AdPlaybackState adPlaybackState = (AdPlaybackState) obj;
        return Util.areEqual(this.adsId, adPlaybackState.adsId) && this.adGroupCount == adPlaybackState.adGroupCount && this.adResumePositionUs == adPlaybackState.adResumePositionUs && this.contentDurationUs == adPlaybackState.contentDurationUs && Arrays.equals(this.adGroupTimesUs, adPlaybackState.adGroupTimesUs) && Arrays.equals(this.adGroups, adPlaybackState.adGroups);
    }

    public int getAdGroupIndexAfterPositionUs(long j, long j2) {
        if (j == Long.MIN_VALUE || (j2 != C.TIME_UNSET && j >= j2)) {
            return -1;
        }
        int i = 0;
        while (true) {
            long[] jArr = this.adGroupTimesUs;
            if (i >= jArr.length || jArr[i] == Long.MIN_VALUE || (j < jArr[i] && this.adGroups[i].hasUnplayedAds())) {
                break;
            }
            i++;
        }
        if (i >= this.adGroupTimesUs.length) {
            return -1;
        }
        return i;
    }

    public int getAdGroupIndexForPositionUs(long j, long j2) {
        int length = this.adGroupTimesUs.length - 1;
        while (length >= 0 && isPositionBeforeAdGroup(j, j2, length)) {
            length--;
        }
        if (length < 0 || !this.adGroups[length].hasUnplayedAds()) {
            return -1;
        }
        return length;
    }

    public int hashCode() {
        int i = this.adGroupCount * 31;
        Object obj = this.adsId;
        int hashCode = obj == null ? 0 : obj.hashCode();
        return Arrays.hashCode(this.adGroups) + ((Arrays.hashCode(this.adGroupTimesUs) + ((((((i + hashCode) * 31) + ((int) this.adResumePositionUs)) * 31) + ((int) this.contentDurationUs)) * 31)) * 31);
    }

    public boolean isAdInErrorState(int i, int i2) {
        AdGroup adGroup;
        int i3;
        AdGroup[] adGroupArr = this.adGroups;
        return i < adGroupArr.length && (i3 = (adGroup = adGroupArr[i]).count) != -1 && i2 < i3 && adGroup.states[i2] == 4;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AdPlaybackState(adsId=");
        outline107.append(this.adsId);
        outline107.append(", adResumePositionUs=");
        outline107.append(this.adResumePositionUs);
        outline107.append(", adGroups=[");
        for (int i = 0; i < this.adGroups.length; i++) {
            outline107.append("adGroup(timeUs=");
            outline107.append(this.adGroupTimesUs[i]);
            outline107.append(", ads=[");
            for (int i2 = 0; i2 < this.adGroups[i].states.length; i2++) {
                outline107.append("ad(state=");
                int i3 = this.adGroups[i].states[i2];
                if (i3 == 0) {
                    outline107.append('_');
                } else if (i3 == 1) {
                    outline107.append(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
                } else if (i3 == 2) {
                    outline107.append('S');
                } else if (i3 == 3) {
                    outline107.append('P');
                } else if (i3 != 4) {
                    outline107.append(Constants.DEFAULT_IMAGE_CHAR);
                } else {
                    outline107.append('!');
                }
                outline107.append(", durationUs=");
                outline107.append(this.adGroups[i].durationsUs[i2]);
                outline107.append(')');
                if (i2 < this.adGroups[i].states.length - 1) {
                    outline107.append(", ");
                }
            }
            outline107.append("])");
            if (i < this.adGroups.length - 1) {
                outline107.append(", ");
            }
        }
        outline107.append("])");
        return outline107.toString();
    }

    @CheckResult
    public AdPlaybackState withAdCount(int i, int i2) {
        Assertions.checkArgument(i2 > 0);
        AdGroup[] adGroupArr = this.adGroups;
        if (adGroupArr[i].count == i2) {
            return this;
        }
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i] = this.adGroups[i].withAdCount(i2);
        return new AdPlaybackState(this.adsId, this.adGroupTimesUs, adGroupArr2, this.adResumePositionUs, this.contentDurationUs);
    }

    @CheckResult
    public AdPlaybackState withAdDurationsUs(long[][] jArr) {
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        for (int i = 0; i < this.adGroupCount; i++) {
            adGroupArr2[i] = adGroupArr2[i].withAdDurationsUs(jArr[i]);
        }
        return new AdPlaybackState(this.adsId, this.adGroupTimesUs, adGroupArr2, this.adResumePositionUs, this.contentDurationUs);
    }

    @CheckResult
    public AdPlaybackState withAdLoadError(int i, int i2) {
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i] = adGroupArr2[i].withAdState(4, i2);
        return new AdPlaybackState(this.adsId, this.adGroupTimesUs, adGroupArr2, this.adResumePositionUs, this.contentDurationUs);
    }

    @CheckResult
    public AdPlaybackState withAdResumePositionUs(long j) {
        return this.adResumePositionUs == j ? this : new AdPlaybackState(this.adsId, this.adGroupTimesUs, this.adGroups, j, this.contentDurationUs);
    }

    @CheckResult
    public AdPlaybackState withAdUri(int i, int i2, Uri uri) {
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i] = adGroupArr2[i].withAdUri(uri, i2);
        return new AdPlaybackState(this.adsId, this.adGroupTimesUs, adGroupArr2, this.adResumePositionUs, this.contentDurationUs);
    }

    @CheckResult
    public AdPlaybackState withContentDurationUs(long j) {
        return this.contentDurationUs == j ? this : new AdPlaybackState(this.adsId, this.adGroupTimesUs, this.adGroups, this.adResumePositionUs, j);
    }

    @CheckResult
    public AdPlaybackState withPlayedAd(int i, int i2) {
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i] = adGroupArr2[i].withAdState(3, i2);
        return new AdPlaybackState(this.adsId, this.adGroupTimesUs, adGroupArr2, this.adResumePositionUs, this.contentDurationUs);
    }

    @CheckResult
    public AdPlaybackState withSkippedAd(int i, int i2) {
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i] = adGroupArr2[i].withAdState(2, i2);
        return new AdPlaybackState(this.adsId, this.adGroupTimesUs, adGroupArr2, this.adResumePositionUs, this.contentDurationUs);
    }

    @CheckResult
    public AdPlaybackState withSkippedAdGroup(int i) {
        AdGroup[] adGroupArr = this.adGroups;
        AdGroup[] adGroupArr2 = (AdGroup[]) Util.nullSafeArrayCopy(adGroupArr, adGroupArr.length);
        adGroupArr2[i] = adGroupArr2[i].withAllAdsSkipped();
        return new AdPlaybackState(this.adsId, this.adGroupTimesUs, adGroupArr2, this.adResumePositionUs, this.contentDurationUs);
    }

    private AdPlaybackState(@Nullable Object obj, long[] jArr, @Nullable AdGroup[] adGroupArr, long j, long j2) {
        this.adsId = obj;
        this.adGroupTimesUs = jArr;
        this.adResumePositionUs = j;
        this.contentDurationUs = j2;
        this.adGroupCount = jArr.length;
        if (adGroupArr == null) {
            adGroupArr = new AdGroup[this.adGroupCount];
            for (int i = 0; i < this.adGroupCount; i++) {
                adGroupArr[i] = new AdGroup();
            }
        }
        this.adGroups = adGroupArr;
    }
}
