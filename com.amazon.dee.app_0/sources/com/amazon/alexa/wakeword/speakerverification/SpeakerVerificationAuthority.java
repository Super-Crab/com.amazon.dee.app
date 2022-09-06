package com.amazon.alexa.wakeword.speakerverification;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.davs.CheckSumUtils;
import com.amazon.alexa.wakeword.speakerverification.encryption.EncryptionUtils;
import com.amazon.alexa.wakeword.speakerverification.enrollment.EnrollmentUtterance;
import com.amazon.alexa.wakeword.speakerverification.errors.ReadWriteFailure;
import com.amazon.alexa.wakeword.speakerverification.errors.StartProfileGenerationFailure;
import com.amazon.alexa.wakeword.speakerverification.metrics.NullSpeakerVerificationMetricsReporter;
import com.amazon.alexa.wakeword.speakerverification.metrics.SpeakerVerificationMetricsListener;
import com.amazon.alexa.wakeword.speakerverification.profile.ProfileContentProviderHelper;
import com.amazon.alexa.wakeword.speakerverification.pryon.EnrollmentModelProvider;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Charsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
/* loaded from: classes11.dex */
public class SpeakerVerificationAuthority {
    @VisibleForTesting
    static final int PROFILE_ID_LENGTH_DIVISOR = 4;
    private static final String TAG = "SpeakerVerificationAuthority";
    private final EncryptionUtils mEncryptionUtils;
    private final EnrollmentModelProvider mEnrollmentModelProvider;
    private final SpeakerVerificationMetricsListener mMetricsListener;
    private final ProfileContentProviderHelper mProfileContentProviderHelper;
    private final SpeakerVerificationAssetManager mSpeakerVerificationAssetManager;

    public SpeakerVerificationAuthority(@NonNull Context context) {
        this(new SpeakerVerificationAssetManager(context), new EncryptionUtils(context), null, new EnrollmentModelProvider(context), new ProfileContentProviderHelper(context));
    }

    private void updateProfileProvider(@NonNull String str) {
        this.mProfileContentProviderHelper.savePersonId(str);
    }

    public boolean deleteAllData() {
        return this.mSpeakerVerificationAssetManager.deleteAllData();
    }

    public boolean deleteUserData(@NonNull String str) {
        if (this.mSpeakerVerificationAssetManager.deleteUserData(str)) {
            this.mMetricsListener.onDeleteUserDataSuccess();
            return true;
        }
        this.mMetricsListener.onDeleteUserDataFailure(ReadWriteFailure.DISK_IO);
        return false;
    }

    @VisibleForTesting
    EnrollmentUtterance deserializeUtteranceData(@NonNull byte[] bArr) throws SerializationException {
        return (EnrollmentUtterance) SerializationUtils.deserialize(bArr);
    }

    public List<EnrollmentUtterance> getEnrollmentUtterances(@NonNull String str) {
        String str2 = TAG;
        Log.i(str2, "getEnrollmentUtterances. Person ID: " + str);
        ArrayList arrayList = new ArrayList();
        int numEnrollmentUtterances = this.mSpeakerVerificationAssetManager.getNumEnrollmentUtterances(str);
        for (int i = 0; i < numEnrollmentUtterances; i++) {
            byte[] readEncryptedUtteranceFromDisk = this.mSpeakerVerificationAssetManager.readEncryptedUtteranceFromDisk(str, i);
            if (readEncryptedUtteranceFromDisk == null) {
                this.mMetricsListener.onGetUtterancesFailure(ReadWriteFailure.DISK_IO);
                return null;
            }
            byte[] decrypt = this.mEncryptionUtils.decrypt(readEncryptedUtteranceFromDisk);
            if (decrypt == null) {
                this.mMetricsListener.onGetUtterancesFailure(ReadWriteFailure.CRYPTO);
                return null;
            }
            try {
                arrayList.add(deserializeUtteranceData(decrypt));
            } catch (SerializationException unused) {
                this.mMetricsListener.onGetUtterancesFailure(ReadWriteFailure.SERIALIZATION);
                return null;
            }
        }
        if (arrayList.size() == 0) {
            this.mMetricsListener.onGetUtterancesFailure(ReadWriteFailure.EMPTY);
            return null;
        }
        this.mMetricsListener.onGetUtterancesSuccess();
        return arrayList;
    }

    @Nullable
    public synchronized byte[] getProfile(@NonNull String str) {
        byte[] readEncryptedProfileFromDisk = this.mSpeakerVerificationAssetManager.readEncryptedProfileFromDisk(str);
        if (readEncryptedProfileFromDisk == null) {
            this.mMetricsListener.onGetProfileFailure(ReadWriteFailure.DISK_IO);
            return null;
        }
        byte[] decrypt = this.mEncryptionUtils.decrypt(readEncryptedProfileFromDisk);
        if (decrypt == null) {
            this.mMetricsListener.onGetProfileFailure(ReadWriteFailure.CRYPTO);
            return null;
        }
        this.mMetricsListener.onGetProfileSuccess();
        return decrypt;
    }

    public byte[] getProfileId(@NonNull String str) {
        String[] split = str.split(ArcusConfig.PATH_SEPARATOR);
        String str2 = split[split.length - 1];
        int length = str2.length() % 4;
        if (length != 0) {
            str2 = GeneratedOutlineSupport1.outline51(str2, length, 0);
        }
        return str2.getBytes(Charsets.US_ASCII);
    }

    @VisibleForTesting
    SpeakerVerificationMetricsListener getSpeakerVerificationMetricsListener() {
        return this.mMetricsListener;
    }

    public boolean hasProfile(@NonNull String str) {
        return this.mSpeakerVerificationAssetManager.hasProfile(str);
    }

    public String readPersonId() {
        return this.mProfileContentProviderHelper.getPersonId();
    }

    public boolean saveEnrollmentUtterances(@NonNull String str, @NonNull List<EnrollmentUtterance> list) {
        String str2 = TAG;
        Log.i(str2, "saveEnrollmentUtterances. Person ID: " + str);
        for (int i = 0; i < list.size(); i++) {
            try {
                byte[] encrypt = this.mEncryptionUtils.encrypt(SerializationUtils.serialize(list.get(i)));
                if (encrypt == null) {
                    this.mMetricsListener.onSaveUtterancesFailure(ReadWriteFailure.CRYPTO);
                    return false;
                } else if (!this.mSpeakerVerificationAssetManager.writeEnryptedUtteranceToDisk(str, encrypt, i)) {
                    this.mMetricsListener.onSaveUtterancesFailure(ReadWriteFailure.DISK_IO);
                    return false;
                }
            } catch (SerializationException unused) {
                this.mMetricsListener.onSaveUtterancesFailure(ReadWriteFailure.SERIALIZATION);
                return false;
            }
        }
        if (list.size() == 0) {
            this.mMetricsListener.onSaveUtterancesFailure(ReadWriteFailure.EMPTY);
            return false;
        }
        this.mMetricsListener.onSaveUtterancesSuccess();
        return true;
    }

    public synchronized boolean saveSpeakerVerificationProfile(@NonNull String str, @NonNull byte[] bArr) {
        byte[] encrypt = this.mEncryptionUtils.encrypt(bArr);
        String md5 = CheckSumUtils.getMD5(this.mEnrollmentModelProvider.getSpeakerVerificationModel());
        if (md5.equals("")) {
            this.mMetricsListener.onStartProfileGenerationFailure(StartProfileGenerationFailure.MODELS_NULL, EnrollmentModelProvider.NULL_MODEL_ID);
            Log.i(TAG, "SV Model Id is null");
            return false;
        } else if (encrypt == null) {
            this.mMetricsListener.onSaveProfileFailure(ReadWriteFailure.CRYPTO, md5);
            return false;
        } else if (this.mSpeakerVerificationAssetManager.writeEncryptedProfileToDisk(str, encrypt)) {
            updateProfileProvider(str);
            Log.i(TAG, "SV Profile save success");
            this.mMetricsListener.onSaveProfileSuccess(md5);
            return true;
        } else {
            Log.e(TAG, "SV Profile save failure");
            this.mMetricsListener.onSaveProfileFailure(ReadWriteFailure.DISK_IO, md5);
            return false;
        }
    }

    public SpeakerVerificationAuthority(@NonNull Context context, @Nullable SpeakerVerificationMetricsListener speakerVerificationMetricsListener) {
        this(new SpeakerVerificationAssetManager(context), new EncryptionUtils(context), speakerVerificationMetricsListener, new EnrollmentModelProvider(context), new ProfileContentProviderHelper(context));
    }

    public boolean hasProfile() {
        return hasProfile(readPersonId());
    }

    @VisibleForTesting
    SpeakerVerificationAuthority(@NonNull SpeakerVerificationAssetManager speakerVerificationAssetManager, @NonNull EncryptionUtils encryptionUtils, @Nullable SpeakerVerificationMetricsListener speakerVerificationMetricsListener, @NonNull EnrollmentModelProvider enrollmentModelProvider, @NonNull ProfileContentProviderHelper profileContentProviderHelper) {
        this.mEncryptionUtils = encryptionUtils;
        this.mSpeakerVerificationAssetManager = speakerVerificationAssetManager;
        this.mMetricsListener = speakerVerificationMetricsListener == null ? new NullSpeakerVerificationMetricsReporter() : speakerVerificationMetricsListener;
        this.mEnrollmentModelProvider = enrollmentModelProvider;
        this.mProfileContentProviderHelper = profileContentProviderHelper;
    }
}
