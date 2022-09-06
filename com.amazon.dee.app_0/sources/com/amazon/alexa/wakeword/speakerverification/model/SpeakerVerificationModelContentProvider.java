package com.amazon.alexa.wakeword.speakerverification.model;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes11.dex */
public class SpeakerVerificationModelContentProvider extends ContentProvider {
    static final String KEY_ARTIFACT_DOWNLOADED_TIME = "artifact_downloaded_time";
    static final String KEY_ARTIFACT_IDENTIFIER = "artifact_identifier";
    static final String KEY_LAST_USED_ENGINE_COMPAT_ID = "engine_compatibility_id";
    static final String KEY_LAST_USED_LOCALE = "last_used_locale";
    private static final String LOCALE_FOR_BACK_UP_MODEL = "en-US";
    private static final String LOG_TAG = SpeakerVerificationModelContentProvider.class.getSimpleName();
    static final String READ_MODE = "r";
    static final String SPEAKER_VERIFICATION_CLASSIFICATION_MODEL_PATH = "classification";
    static final String SPEAKER_VERIFICATION_MODEL_FILE_PROVIDER_AUTHORITY = "com.amazon.alexa.pryon.speakerverification.model";
    static final String SPEAKER_VERIFICATION_REGENERATION_MODEL_PATH = "regeneration";
    private static final String STRING_UTILS_DELIMTIER = ";";
    @VisibleForTesting
    static final int UPDATED_ROWS = 1;
    private SpeakerVerificationModelAuthority speakerVerificationModelAuthority;

    /* loaded from: classes11.dex */
    private static class PipeDecryptionThread extends Thread {
        private final InputStream inputStream;
        private final OutputStream outputStream;

        PipeDecryptionThread(@NonNull InputStream inputStream, @NonNull OutputStream outputStream) {
            this.inputStream = inputStream;
            this.outputStream = outputStream;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                byte[] byteArray = IOUtils.toByteArray(this.inputStream);
                this.inputStream.close();
                this.outputStream.write(byteArray);
                this.outputStream.flush();
                this.outputStream.close();
            } catch (IOException e) {
                Log.e(SpeakerVerificationModelContentProvider.LOG_TAG, "IOException while transferring file", e, new Object[0]);
            }
        }
    }

    @Nullable
    private ModelType getModelTypeFromUri(@NonNull Uri uri) {
        if (!SPEAKER_VERIFICATION_MODEL_FILE_PROVIDER_AUTHORITY.equals(uri.getAuthority())) {
            Log.e(LOG_TAG, String.format("Invalid uri authority: %s", uri));
            return null;
        }
        String encodedPath = uri.getEncodedPath();
        if (encodedPath != null) {
            String replace = encodedPath.replace("/", "");
            Log.d(LOG_TAG, String.format("Uri path: %s", replace));
            char c = 65535;
            int hashCode = replace.hashCode();
            if (hashCode != 382350310) {
                if (hashCode == 1032770443 && replace.equals(SPEAKER_VERIFICATION_REGENERATION_MODEL_PATH)) {
                    c = 0;
                }
            } else if (replace.equals(SPEAKER_VERIFICATION_CLASSIFICATION_MODEL_PATH)) {
                c = 1;
            }
            if (c == 0) {
                return ModelType.REGENERATION;
            }
            if (c != 1) {
                Log.e(LOG_TAG, String.format("Invalid uri path for uri: %s", uri));
                return null;
            }
            return ModelType.CLASSIFICATION;
        }
        Log.e(LOG_TAG, String.format("Invalid uri path for uri: %s", uri));
        return null;
    }

    @Override // android.content.ContentProvider
    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.speakerVerificationModelAuthority = new SpeakerVerificationModelAuthority(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    @Nullable
    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String str) throws FileNotFoundException {
        Log.i(LOG_TAG, String.format("Opening speaker verification model file with uri: %s", uri));
        if (Build.VERSION.SDK_INT >= 28) {
            Log.d(LOG_TAG, String.format("Called in process: %s", Application.getProcessName()));
        }
        if (!READ_MODE.equals(str)) {
            Log.d(LOG_TAG, String.format("Invalid read mode: %s, should be: %s", str, READ_MODE));
            return null;
        }
        ModelType modelTypeFromUri = getModelTypeFromUri(uri);
        if (modelTypeFromUri != null) {
            try {
                ArtifactModel model = this.speakerVerificationModelAuthority.getModel(modelTypeFromUri);
                if (model == null) {
                    Log.e(LOG_TAG, "Artifact model returned null");
                    return null;
                } else if (model.getArtifactData() == null) {
                    Log.e(LOG_TAG, "Artifact model data is not written.");
                    return null;
                } else {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(model.getArtifactData());
                    ParcelFileDescriptor[] createReliablePipe = ParcelFileDescriptor.createReliablePipe();
                    new PipeDecryptionThread(byteArrayInputStream, new ParcelFileDescriptor.AutoCloseOutputStream(createReliablePipe[1])).start();
                    return createReliablePipe[0];
                }
            } catch (IOException unused) {
                throw new FileNotFoundException(String.format("Could not open file or pipe for uri: %s", uri));
            }
        }
        throw new FileNotFoundException(String.format("Invalid uri authority: %s", uri));
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        Log.i(LOG_TAG, String.format("Querying speaker verification metadata, uri: %s", uri));
        if (Build.VERSION.SDK_INT >= 28) {
            Log.d(LOG_TAG, String.format("Called in process: %s", Application.getProcessName()));
        }
        if (strArr != null) {
            Log.e(LOG_TAG, String.format("Projection parameter is not supported: %s", StringUtils.join(strArr, STRING_UTILS_DELIMTIER)));
            return null;
        } else if (str != null) {
            Log.e(LOG_TAG, String.format("Selection parameter is not supported: %s", str));
            return null;
        } else if (strArr2 != null) {
            Log.e(LOG_TAG, String.format("Selection args parameter is not supported: %s", StringUtils.join(strArr2, STRING_UTILS_DELIMTIER)));
            return null;
        } else if (str2 != null) {
            Log.e(LOG_TAG, String.format("Sort order parameter is not supported: %s", str2));
            return null;
        } else {
            MatrixCursor matrixCursor = new MatrixCursor(new String[]{"artifact_downloaded_time", "engine_compatibility_id", "artifact_identifier", "last_used_locale"});
            ModelType modelTypeFromUri = getModelTypeFromUri(uri);
            if (modelTypeFromUri == null) {
                Log.e(LOG_TAG, "Invalid model type from uri");
                return null;
            }
            ArtifactModel model = this.speakerVerificationModelAuthority.getModel(modelTypeFromUri);
            if (model == null) {
                Log.e(LOG_TAG, "Artifact model returned null");
                return null;
            }
            matrixCursor.newRow().add("artifact_downloaded_time", model.getArtifactDownloadedTime()).add("engine_compatibility_id", model.getEngineCompatibilityId()).add("artifact_identifier", model.getArtifactIdentifier()).add("last_used_locale", model.getLocale());
            return matrixCursor;
        }
    }

    @VisibleForTesting
    void setSpeakerVerificationModelAuthority(@NonNull SpeakerVerificationModelAuthority speakerVerificationModelAuthority) {
        this.speakerVerificationModelAuthority = speakerVerificationModelAuthority;
    }

    @Override // android.content.ContentProvider
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        Log.i(LOG_TAG, String.format("Updating speaker verification metadata, uri: %s, content values: %s", uri, contentValues));
        if (Build.VERSION.SDK_INT >= 28) {
            Log.d(LOG_TAG, String.format("Called in process: %s", Application.getProcessName()));
        }
        if (contentValues == null) {
            Log.e(LOG_TAG, "Content values provided are null. Not updating.");
            return 0;
        }
        ModelType modelTypeFromUri = getModelTypeFromUri(uri);
        if (modelTypeFromUri == null) {
            Log.e(LOG_TAG, "Invalid model type from uri");
            return 0;
        }
        this.speakerVerificationModelAuthority.updateModelMetadata(modelTypeFromUri, contentValues.getAsString("artifact_identifier"), contentValues.getAsString("engine_compatibility_id"), contentValues.getAsString("last_used_locale"), contentValues.getAsLong("artifact_downloaded_time"));
        return 1;
    }
}
