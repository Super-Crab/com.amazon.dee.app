package com.amazon.alexa.wakeword.speakerverification;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.io.File;
import java.util.Locale;
import org.apache.commons.io.FileUtils;
/* loaded from: classes11.dex */
public class SpeakerVerificationAssetManager {
    private static final String PROFILE_FILE_NAME = "profile";
    private static final String SV_DIRECTORY = "speaker_verification";
    private static final String TAG = "SpeakerVerificationAssetManager";
    private static final String UTTERANCES_DIRECTORY = "enrollment_utterances";
    private static final String UTTERANCE_FILE_NAME = "%d";
    private final File mSpeakerVerificationDir;

    public SpeakerVerificationAssetManager(@NonNull Context context) {
        this.mSpeakerVerificationDir = new File(context.getFilesDir(), SV_DIRECTORY);
    }

    private boolean createUserDir(@NonNull String str) {
        return getUserDirectory(str).exists() || getUserDirectory(str).mkdirs();
    }

    private boolean createUtterancesDir(@NonNull String str) {
        return getUtterancesDir(str).exists() || getUtterancesDir(str).mkdirs();
    }

    public synchronized boolean deleteAllData() {
        return deleteDirectory(this.mSpeakerVerificationDir);
    }

    @VisibleForTesting
    boolean deleteDirectory(@NonNull File file) {
        try {
            FileUtils.deleteDirectory(file);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Failed to delete directory", e, new Object[0]);
            return false;
        }
    }

    public synchronized boolean deleteUserData(@NonNull String str) {
        return deleteDirectory(getUserDirectory(str));
    }

    public int getNumEnrollmentUtterances(@NonNull String str) {
        File[] listFiles = getUtterancesDir(str).listFiles();
        if (listFiles == null) {
            return 0;
        }
        return listFiles.length;
    }

    @VisibleForTesting
    File getProfileFile(@NonNull String str) {
        return new File(getUserDirectory(str), "profile");
    }

    @VisibleForTesting
    File getUserDirectory(@NonNull String str) {
        return new File(this.mSpeakerVerificationDir, str);
    }

    @VisibleForTesting
    File getUtteranceFile(@NonNull File file, int i) {
        return new File(file, String.format(Locale.getDefault(), UTTERANCE_FILE_NAME, Integer.valueOf(i)));
    }

    @VisibleForTesting
    File getUtterancesDir(@NonNull String str) {
        return new File(getUserDirectory(str), UTTERANCES_DIRECTORY);
    }

    public boolean hasProfile(@NonNull String str) {
        return getProfileFile(str).exists();
    }

    @VisibleForTesting
    byte[] readBytesFromFile(@NonNull File file) {
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (Exception e) {
            Log.e(TAG, "Failed to read bytes from file.", e, new Object[0]);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] readEncryptedProfileFromDisk(@NonNull String str) {
        return readBytesFromFile(getProfileFile(str));
    }

    public byte[] readEncryptedUtteranceFromDisk(@NonNull String str, int i) {
        return readBytesFromFile(getUtteranceFile(getUtterancesDir(str), i));
    }

    @VisibleForTesting
    boolean writeBytesToFile(@NonNull File file, @NonNull byte[] bArr) {
        try {
            FileUtils.writeByteArrayToFile(file, bArr);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Encountered exception while writing bytes to disk", e, new Object[0]);
            return false;
        }
    }

    public boolean writeEncryptedProfileToDisk(@NonNull String str, @NonNull byte[] bArr) {
        if (!createUserDir(str)) {
            return false;
        }
        return writeBytesToFile(getProfileFile(str), bArr);
    }

    public boolean writeEnryptedUtteranceToDisk(@NonNull String str, @NonNull byte[] bArr, int i) {
        if (!createUtterancesDir(str)) {
            return false;
        }
        return writeBytesToFile(getUtteranceFile(getUtterancesDir(str), i), bArr);
    }

    @VisibleForTesting
    SpeakerVerificationAssetManager(@NonNull File file) {
        this.mSpeakerVerificationDir = file;
    }
}
