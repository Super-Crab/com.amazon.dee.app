package com.amazon.alexa.wakeword.speakerverification.sync;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.davs.ArtifactManager;
/* loaded from: classes11.dex */
public class SpeakerVerificationSyncArtifactManager extends ArtifactManager {
    private static final String TAG = "SpeakerVerificationSyncArtifactManager";
    private Context mContext;
    private SpeakerVerificationSyncInterface mSpeakerVerificationSync;

    public SpeakerVerificationSyncArtifactManager(Context context) {
        this(context, new SpeakerVerificationSyncInterface() { // from class: com.amazon.alexa.wakeword.speakerverification.sync.SpeakerVerificationSyncArtifactManager.1
            @Override // com.amazon.alexa.wakeword.speakerverification.sync.SpeakerVerificationSyncInterface
            public Uri getFileFromSync(String str) {
                Log.d(SpeakerVerificationSyncArtifactManager.TAG, "Sync not yet implemented");
                return null;
            }
        });
    }

    private boolean getArtifactFromSync(String str) {
        return this.mSpeakerVerificationSync.getFileFromSync(str) != null;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactManager
    public synchronized boolean hasArtifact(String str) {
        boolean z;
        String str2 = TAG;
        Log.d(str2, "checking for artifact: " + str);
        if (!hasLocalArtifactFile(str)) {
            if (!getArtifactFromSync(str)) {
                z = false;
            }
        }
        z = true;
        return z;
    }

    @VisibleForTesting
    boolean hasLocalArtifactFile(String str) {
        return super.hasArtifact(str);
    }

    @VisibleForTesting
    SpeakerVerificationSyncArtifactManager(Context context, SpeakerVerificationSyncInterface speakerVerificationSyncInterface) {
        super(context);
        this.mContext = context;
        this.mSpeakerVerificationSync = speakerVerificationSyncInterface;
    }
}
