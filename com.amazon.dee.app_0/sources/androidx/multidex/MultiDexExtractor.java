package androidx.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
/* loaded from: classes.dex */
final class MultiDexExtractor implements Closeable {
    private static final int BUFFER_SIZE = 16384;
    private static final String DEX_PREFIX = "classes";
    static final String DEX_SUFFIX = ".dex";
    private static final String EXTRACTED_NAME_EXT = ".classes";
    static final String EXTRACTED_SUFFIX = ".zip";
    private static final String KEY_CRC = "crc";
    private static final String KEY_DEX_CRC = "dex.crc.";
    private static final String KEY_DEX_NUMBER = "dex.number";
    private static final String KEY_DEX_TIME = "dex.time.";
    private static final String KEY_TIME_STAMP = "timestamp";
    private static final String LOCK_FILENAME = "MultiDex.lock";
    private static final int MAX_EXTRACT_ATTEMPTS = 3;
    private static final long NO_VALUE = -1;
    private static final String PREFS_FILE = "multidex.version";
    private static final String TAG = "MultiDex";
    private final FileLock cacheLock;
    private final File dexDir;
    private final FileChannel lockChannel;
    private final RandomAccessFile lockRaf;
    private final File sourceApk;
    private final long sourceCrc;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ExtractedDex extends File {
        public long crc;

        public ExtractedDex(File file, String str) {
            super(file, str);
            this.crc = -1L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MultiDexExtractor(File file, File file2) throws IOException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MultiDexExtractor(");
        outline107.append(file.getPath());
        outline107.append(", ");
        outline107.append(file2.getPath());
        outline107.append(")");
        Log.i(TAG, outline107.toString());
        this.sourceApk = file;
        this.dexDir = file2;
        this.sourceCrc = getZipCrc(file);
        File file3 = new File(file2, LOCK_FILENAME);
        this.lockRaf = new RandomAccessFile(file3, "rw");
        try {
            this.lockChannel = this.lockRaf.getChannel();
            try {
                Log.i(TAG, "Blocking on lock " + file3.getPath());
                this.cacheLock = this.lockChannel.lock();
                Log.i(TAG, file3.getPath() + " locked");
            } catch (IOException e) {
                e = e;
                closeQuietly(this.lockChannel);
                throw e;
            } catch (Error e2) {
                e = e2;
                closeQuietly(this.lockChannel);
                throw e;
            } catch (RuntimeException e3) {
                e = e3;
                closeQuietly(this.lockChannel);
                throw e;
            }
        } catch (IOException | Error | RuntimeException e4) {
            closeQuietly(this.lockRaf);
            throw e4;
        }
    }

    private void clearDexDir() {
        File[] listFiles = this.dexDir.listFiles(new FileFilter() { // from class: androidx.multidex.MultiDexExtractor.1
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                return !file.getName().equals(MultiDexExtractor.LOCK_FILENAME);
            }
        });
        if (listFiles == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to list secondary dex dir content (");
            outline107.append(this.dexDir.getPath());
            outline107.append(").");
            Log.w(TAG, outline107.toString());
            return;
        }
        for (File file : listFiles) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Trying to delete old file ");
            outline1072.append(file.getPath());
            outline1072.append(" of size ");
            outline1072.append(file.length());
            Log.i(TAG, outline1072.toString());
            if (!file.delete()) {
                StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Failed to delete old file ");
                outline1073.append(file.getPath());
                Log.w(TAG, outline1073.toString());
            } else {
                StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("Deleted old file ");
                outline1074.append(file.getPath());
                Log.i(TAG, outline1074.toString());
            }
        }
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w(TAG, "Failed to close resource", e);
        }
    }

    private static void extract(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException, FileNotFoundException {
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        File createTempFile = File.createTempFile(GeneratedOutlineSupport1.outline72("tmp-", str), EXTRACTED_SUFFIX, file.getParentFile());
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Extracting ");
        outline107.append(createTempFile.getPath());
        Log.i(TAG, outline107.toString());
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(createTempFile)));
            ZipEntry zipEntry2 = new ZipEntry("classes.dex");
            zipEntry2.setTime(zipEntry.getTime());
            zipOutputStream.putNextEntry(zipEntry2);
            byte[] bArr = new byte[16384];
            for (int read = inputStream.read(bArr); read != -1; read = inputStream.read(bArr)) {
                zipOutputStream.write(bArr, 0, read);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            if (createTempFile.setReadOnly()) {
                Log.i(TAG, "Renaming to " + file.getPath());
                if (createTempFile.renameTo(file)) {
                    return;
                }
                throw new IOException("Failed to rename \"" + createTempFile.getAbsolutePath() + "\" to \"" + file.getAbsolutePath() + EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            }
            throw new IOException("Failed to mark readonly \"" + createTempFile.getAbsolutePath() + "\" (tmp of \"" + file.getAbsolutePath() + "\")");
        } finally {
            closeQuietly(inputStream);
            createTempFile.delete();
        }
    }

    private static SharedPreferences getMultiDexPreferences(Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getSharedPreferences(PREFS_FILE, 4);
    }

    private static long getTimeStamp(File file) {
        long lastModified = file.lastModified();
        return lastModified == -1 ? lastModified - 1 : lastModified;
    }

    private static long getZipCrc(File file) throws IOException {
        long zipCrc = ZipUtil.getZipCrc(file);
        return zipCrc == -1 ? zipCrc - 1 : zipCrc;
    }

    private static boolean isModified(Context context, File file, long j, String str) {
        SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        if (multiDexPreferences.getLong(str + "timestamp", -1L) == getTimeStamp(file)) {
            if (multiDexPreferences.getLong(str + KEY_CRC, -1L) == j) {
                return false;
            }
        }
        return true;
    }

    private List<ExtractedDex> loadExistingExtractions(Context context, String str) throws IOException {
        Log.i(TAG, "loading existing secondary dex files");
        String str2 = this.sourceApk.getName() + EXTRACTED_NAME_EXT;
        SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        int i = multiDexPreferences.getInt(str + KEY_DEX_NUMBER, 1);
        ArrayList arrayList = new ArrayList(i + (-1));
        int i2 = 2;
        while (i2 <= i) {
            ExtractedDex extractedDex = new ExtractedDex(this.dexDir, GeneratedOutlineSupport1.outline52(str2, i2, EXTRACTED_SUFFIX));
            if (extractedDex.isFile()) {
                extractedDex.crc = getZipCrc(extractedDex);
                long j = multiDexPreferences.getLong(GeneratedOutlineSupport1.outline74(str, KEY_DEX_CRC, i2), -1L);
                long j2 = multiDexPreferences.getLong(GeneratedOutlineSupport1.outline74(str, KEY_DEX_TIME, i2), -1L);
                long lastModified = extractedDex.lastModified();
                if (j2 == lastModified) {
                    String str3 = str2;
                    SharedPreferences sharedPreferences = multiDexPreferences;
                    if (j == extractedDex.crc) {
                        arrayList.add(extractedDex);
                        i2++;
                        multiDexPreferences = sharedPreferences;
                        str2 = str3;
                    }
                }
                throw new IOException("Invalid extracted dex: " + extractedDex + " (key \"" + str + "\"), expected modification time: " + j2 + ", modification time: " + lastModified + ", expected crc: " + j + ", file crc: " + extractedDex.crc);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Missing extracted secondary dex file '");
            outline107.append(extractedDex.getPath());
            outline107.append("'");
            throw new IOException(outline107.toString());
        }
        return arrayList;
    }

    private List<ExtractedDex> performExtractions() throws IOException {
        boolean z;
        String str = this.sourceApk.getName() + EXTRACTED_NAME_EXT;
        clearDexDir();
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(this.sourceApk);
        try {
            ZipEntry entry = zipFile.getEntry(DEX_PREFIX + 2 + DEX_SUFFIX);
            int i = 2;
            while (entry != null) {
                ExtractedDex extractedDex = new ExtractedDex(this.dexDir, str + i + EXTRACTED_SUFFIX);
                arrayList.add(extractedDex);
                Log.i(TAG, "Extraction is needed for file " + extractedDex);
                int i2 = 0;
                boolean z2 = false;
                while (i2 < 3 && !z2) {
                    int i3 = i2 + 1;
                    extract(zipFile, entry, extractedDex, str);
                    try {
                        extractedDex.crc = getZipCrc(extractedDex);
                        z = true;
                    } catch (IOException e) {
                        Log.w(TAG, "Failed to read crc from " + extractedDex.getAbsolutePath(), e);
                        z = false;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Extraction ");
                    sb.append(z ? "succeeded" : "failed");
                    sb.append(" '");
                    sb.append(extractedDex.getAbsolutePath());
                    sb.append("': length ");
                    sb.append(extractedDex.length());
                    sb.append(" - crc: ");
                    sb.append(extractedDex.crc);
                    Log.i(TAG, sb.toString());
                    if (!z) {
                        extractedDex.delete();
                        if (extractedDex.exists()) {
                            Log.w(TAG, "Failed to delete corrupted secondary dex '" + extractedDex.getPath() + "'");
                        }
                    }
                    z2 = z;
                    i2 = i3;
                }
                if (z2) {
                    i++;
                    entry = zipFile.getEntry(DEX_PREFIX + i + DEX_SUFFIX);
                } else {
                    throw new IOException("Could not create zip file " + extractedDex.getAbsolutePath() + " for secondary dex (" + i + ")");
                }
            }
            try {
                zipFile.close();
            } catch (IOException e2) {
                Log.w(TAG, "Failed to close resource", e2);
            }
            return arrayList;
        } catch (Throwable th) {
            try {
                zipFile.close();
            } catch (IOException e3) {
                Log.w(TAG, "Failed to close resource", e3);
            }
            throw th;
        }
    }

    private static void putStoredApkInfo(Context context, String str, long j, long j2, List<ExtractedDex> list) {
        SharedPreferences.Editor edit = getMultiDexPreferences(context).edit();
        edit.putLong(str + "timestamp", j);
        edit.putLong(GeneratedOutlineSupport1.outline91(new StringBuilder(), str, KEY_CRC), j2);
        edit.putInt(str + KEY_DEX_NUMBER, list.size() + 1);
        int i = 2;
        for (ExtractedDex extractedDex : list) {
            edit.putLong(GeneratedOutlineSupport1.outline74(str, KEY_DEX_CRC, i), extractedDex.crc);
            edit.putLong(str + KEY_DEX_TIME + i, extractedDex.lastModified());
            i++;
        }
        edit.commit();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.cacheLock.release();
        this.lockChannel.close();
        this.lockRaf.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<? extends File> load(Context context, String str, boolean z) throws IOException {
        List<ExtractedDex> performExtractions;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MultiDexExtractor.load(");
        outline107.append(this.sourceApk.getPath());
        outline107.append(", ");
        outline107.append(z);
        outline107.append(", ");
        outline107.append(str);
        outline107.append(")");
        Log.i(TAG, outline107.toString());
        if (this.cacheLock.isValid()) {
            if (!z && !isModified(context, this.sourceApk, this.sourceCrc, str)) {
                try {
                    performExtractions = loadExistingExtractions(context, str);
                } catch (IOException e) {
                    Log.w(TAG, "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", e);
                    performExtractions = performExtractions();
                    putStoredApkInfo(context, str, getTimeStamp(this.sourceApk), this.sourceCrc, performExtractions);
                }
            } else {
                if (z) {
                    Log.i(TAG, "Forced extraction must be performed.");
                } else {
                    Log.i(TAG, "Detected that extraction must be performed.");
                }
                performExtractions = performExtractions();
                putStoredApkInfo(context, str, getTimeStamp(this.sourceApk), this.sourceCrc, performExtractions);
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("load found ");
            outline1072.append(performExtractions.size());
            outline1072.append(" secondary dex files");
            Log.i(TAG, outline1072.toString());
            return performExtractions;
        }
        throw new IllegalStateException("MultiDexExtractor was closed");
    }
}
