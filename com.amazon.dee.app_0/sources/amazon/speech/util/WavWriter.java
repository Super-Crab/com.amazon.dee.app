package amazon.speech.util;

import amazon.speech.util.DebugUtil;
import android.os.Build;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
/* loaded from: classes.dex */
public class WavWriter {
    private static final int HEADER_SIZE = 44;
    private static final String RECORDING_DIRECTORY = "/data/data/amazon.speech.sim/utterances/";
    private static final String RECORDING_WAV_EXT = ".wav";
    protected RandomAccessFile mFile;
    private final String mFileName;
    private static final boolean DEBUG = DebugUtil.getShouldDebug(DebugUtil.Module.AP);
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.AP, WavWriter.class);

    public WavWriter(String str) {
        this.mFileName = str;
    }

    private boolean createFile(String str) {
        File file = new File(str);
        try {
            if (file.exists()) {
                file.delete();
            }
            if (DEBUG) {
                String str2 = TAG;
                Log.d(str2, "creating file: " + str);
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            this.mFile = new RandomAccessFile(file, "rw");
            return true;
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File not found", e);
            return false;
        } catch (IOException e2) {
            Log.e(TAG, "can't create file", e2);
            return false;
        }
    }

    private static String getOnDevicePath(String str) {
        return GeneratedOutlineSupport1.outline75(RECORDING_DIRECTORY, str, RECORDING_WAV_EXT);
    }

    static void writeWavHeader(RandomAccessFile randomAccessFile) throws IOException {
        if (DEBUG) {
            Log.d(TAG, "Writing Wav Header");
        }
        long filePointer = randomAccessFile.getFilePointer();
        try {
            randomAccessFile.seek(0L);
            long size = randomAccessFile.getChannel().size() - 44;
            long j = 36 + size;
            try {
                randomAccessFile.write(new byte[]{82, 73, 70, 70, (byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255), 87, 65, 86, 69, 102, 109, 116, 32, 16, 0, 0, 0, 1, 0, (byte) 1, 0, (byte) 128, (byte) 62, (byte) 0, (byte) 0, (byte) 0, (byte) 125, (byte) 0, (byte) 0, (byte) 2, 0, 16, 0, 100, 97, 116, 97, (byte) (size & 255), (byte) ((size >> 8) & 255), (byte) ((size >> 16) & 255), (byte) ((size >> 24) & 255)}, 0, 44);
                randomAccessFile.seek(filePointer);
            } catch (Exception e) {
                Log.e(TAG, "Exception in writeWavHeader() finish header", e);
            }
        } catch (Exception e2) {
            Log.e(TAG, "Exception in writeWavHeader() seeking position", e2);
        }
    }

    public void close() throws IOException {
        RandomAccessFile randomAccessFile;
        if (!Build.TYPE.equals("user") && (randomAccessFile = this.mFile) != null) {
            try {
                writeWavHeader(randomAccessFile);
                this.mFile.close();
            } catch (IOException e) {
                Log.e(TAG, "Exception closing file", e);
            }
        }
    }

    public void open() {
        if (!Build.TYPE.equals("user") && createFile(getOnDevicePath(this.mFileName))) {
            try {
                this.mFile.seek(44L);
            } catch (Exception e) {
                Log.e(TAG, "Exception seeking position in file", e);
            }
        }
    }

    public void write(byte[] bArr) throws IOException {
        if (Build.TYPE.equals("user")) {
            return;
        }
        try {
            this.mFile.write(bArr);
        } catch (Exception e) {
            Log.e(TAG, "Exception in write(byte[],int,int)", e);
        }
    }

    public void write(byte[] bArr, int i) throws IOException {
        if (Build.TYPE.equals("user")) {
            return;
        }
        try {
            this.mFile.write(bArr, 0, i);
        } catch (Exception e) {
            Log.e(TAG, "Exception in write(byte[],int,int)", e);
        }
    }
}
