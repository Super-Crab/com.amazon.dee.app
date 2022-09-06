package amazon.speech.tts.asp;

import amazon.speech.util.DebugUtil;
import amazon.speech.util.Log;
import com.amazon.asp.AudioSignalProcessor;
/* loaded from: classes.dex */
public class AspWrapper implements IAspWrapper {
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.CSM, AspWrapper.class);
    AudioSignalProcessor mAsp;

    public AspWrapper() {
        this(createAudioSignalProcessor());
    }

    private static AudioSignalProcessor createAudioSignalProcessor() {
        try {
            return AudioSignalProcessor.getInstance();
        } catch (LinkageError unused) {
            Log.i(TAG, "AudioSignalProcessor client not found on this device");
            return null;
        }
    }

    @Override // amazon.speech.tts.asp.IAspWrapper
    public void command(int i, byte[] bArr, byte[] bArr2) {
        AudioSignalProcessor audioSignalProcessor = this.mAsp;
        if (audioSignalProcessor != null) {
            audioSignalProcessor.command(i, bArr, bArr2);
        }
    }

    AspWrapper(AudioSignalProcessor audioSignalProcessor) {
        this.mAsp = audioSignalProcessor;
    }
}
