package com.amazon.blueshift.bluefront.android.task;

import android.os.AsyncTask;
import com.amazon.blueshift.bluefront.android.SpeechClientException;
import com.amazon.blueshift.bluefront.android.http.MultipartRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
/* loaded from: classes11.dex */
public class MultipartRequestTask extends AsyncTask<Void, Progress<AudioStreamingTaskState>, byte[]> {
    private static final String TAG = MultipartRequestTask.class.getCanonicalName();
    private final MultipartRequestTaskListener mListener;
    private final MultipartRequest mRequest;

    /* renamed from: com.amazon.blueshift.bluefront.android.task.MultipartRequestTask$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$blueshift$bluefront$android$task$MultipartRequestTask$AudioStreamingTaskState = new int[AudioStreamingTaskState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$blueshift$bluefront$android$task$MultipartRequestTask$AudioStreamingTaskState[AudioStreamingTaskState.Streaming.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$blueshift$bluefront$android$task$MultipartRequestTask$AudioStreamingTaskState[AudioStreamingTaskState.Error.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public enum AudioStreamingTaskState {
        Streaming,
        Error
    }

    public MultipartRequestTask(MultipartRequest multipartRequest, MultipartRequestTaskListener multipartRequestTaskListener) {
        this.mRequest = (MultipartRequest) Preconditions.checkNotNull(multipartRequest, "Request cannot be null.");
        this.mListener = (MultipartRequestTaskListener) Preconditions.checkNotNull(multipartRequestTaskListener, "Listener cannot be null.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public byte[] doInBackground(Void... voidArr) {
        try {
            return this.mRequest.execute();
        } catch (SpeechClientException e) {
            publishProgress(new Progress(AudioStreamingTaskState.Error, e));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(byte[] bArr) {
        if (bArr != null) {
            this.mListener.onResponse(bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onProgressUpdate(Progress<AudioStreamingTaskState>... progressArr) {
        Preconditions.checkNotNull(progressArr, "States cannot be null.");
        Preconditions.checkArgument(progressArr.length == 1, "Only one update is allowed.");
        Progress<AudioStreamingTaskState> progress = progressArr[0];
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onProgressUpdate: ");
        outline107.append(progress.getState());
        outline107.toString();
        Object payload = progress.getPayload();
        int ordinal = progress.getState().ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                this.mListener.onError(new SpeechClientException("Received an unknown progress update state"));
            } else if (payload == null || !(payload instanceof SpeechClientException)) {
            } else {
                this.mListener.onError((SpeechClientException) payload);
            }
        }
    }
}
