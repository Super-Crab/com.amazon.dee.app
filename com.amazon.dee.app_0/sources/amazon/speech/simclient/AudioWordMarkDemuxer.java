package amazon.speech.simclient;

import amazon.speech.tts.BaseAudioWordMarkDemuxer;
import java.io.IOException;
import java.io.InputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AudioWordMarkDemuxer extends BaseAudioWordMarkDemuxer {
    AudioWordMarkDemuxer(InputStream inputStream, final ITtsSpeechMarksEmitter iTtsSpeechMarksEmitter) throws IOException {
        super(inputStream);
        setEmitter(new BaseAudioWordMarkDemuxer.Emitter() { // from class: amazon.speech.simclient.AudioWordMarkDemuxer.1
            @Override // amazon.speech.tts.BaseAudioWordMarkDemuxer.Emitter
            public void scheduleSpeechMark(String str) throws Exception {
                ITtsSpeechMarksEmitter iTtsSpeechMarksEmitter2 = iTtsSpeechMarksEmitter;
                if (iTtsSpeechMarksEmitter2 != null) {
                    iTtsSpeechMarksEmitter2.scheduleSpeechMark(str);
                }
            }
        });
    }

    public static AudioWordMarkDemuxer split(InputStream inputStream, ITtsSpeechMarksEmitter iTtsSpeechMarksEmitter) throws IOException {
        return new AudioWordMarkDemuxer(inputStream, iTtsSpeechMarksEmitter);
    }
}
