package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import android.os.StrictMode;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public abstract class UnpackingSoSource extends DirectorySoSource {
    private static final String DEPS_FILE_NAME = "dso_deps";
    private static final String LOCK_FILE_NAME = "dso_lock";
    private static final String MANIFEST_FILE_NAME = "dso_manifest";
    private static final byte MANIFEST_VERSION = 1;
    private static final byte STATE_CLEAN = 1;
    private static final byte STATE_DIRTY = 0;
    private static final String STATE_FILE_NAME = "dso_state";
    private static final String TAG = "fb-UnpackingSoSource";
    @Nullable
    private String[] mAbis;
    protected final Context mContext;
    @Nullable
    protected String mCorruptedLib;
    private final Map<String, Object> mLibsBeingLoaded;

    /* loaded from: classes2.dex */
    public static class Dso {
        public final String hash;
        public final String name;

        public Dso(String str, String str2) {
            this.name = str;
            this.hash = str2;
        }
    }

    /* loaded from: classes2.dex */
    public static final class DsoManifest {
        public final Dso[] dsos;

        public DsoManifest(Dso[] dsoArr) {
            this.dsos = dsoArr;
        }

        static final DsoManifest read(DataInput dataInput) throws IOException {
            if (dataInput.readByte() == 1) {
                int readInt = dataInput.readInt();
                if (readInt >= 0) {
                    Dso[] dsoArr = new Dso[readInt];
                    for (int i = 0; i < readInt; i++) {
                        dsoArr[i] = new Dso(dataInput.readUTF(), dataInput.readUTF());
                    }
                    return new DsoManifest(dsoArr);
                }
                throw new RuntimeException("illegal number of shared libraries");
            }
            throw new RuntimeException("wrong dso manifest version");
        }

        public final void write(DataOutput dataOutput) throws IOException {
            dataOutput.writeByte(1);
            dataOutput.writeInt(this.dsos.length);
            int i = 0;
            while (true) {
                Dso[] dsoArr = this.dsos;
                if (i < dsoArr.length) {
                    dataOutput.writeUTF(dsoArr[i].name);
                    dataOutput.writeUTF(this.dsos[i].hash);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static final class InputDso implements Closeable {
        public final InputStream content;
        public final Dso dso;

        public InputDso(Dso dso, InputStream inputStream) {
            this.dso = dso;
            this.content = inputStream;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.content.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static abstract class InputDsoIterator implements Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public abstract boolean hasNext();

        public abstract InputDso next() throws IOException;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static abstract class Unpacker implements Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        protected abstract DsoManifest getDsoManifest() throws IOException;

        protected abstract InputDsoIterator openDsoIterator() throws IOException;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UnpackingSoSource(Context context, String str) {
        super(getSoStorePath(context, str), 1);
        this.mLibsBeingLoaded = new HashMap();
        this.mContext = context;
    }

    private void deleteUnmentionedFiles(Dso[] dsoArr) throws IOException {
        String[] list = this.soDirectory.list();
        if (list != null) {
            for (String str : list) {
                if (!str.equals(STATE_FILE_NAME) && !str.equals(LOCK_FILE_NAME) && !str.equals(DEPS_FILE_NAME) && !str.equals(MANIFEST_FILE_NAME)) {
                    boolean z = false;
                    for (int i = 0; !z && i < dsoArr.length; i++) {
                        if (dsoArr[i].name.equals(str)) {
                            z = true;
                        }
                    }
                    if (!z) {
                        File file = new File(this.soDirectory, str);
                        String str2 = "deleting unaccounted-for file " + file;
                        SysUtil.dumbDeleteRecursive(file);
                    }
                }
            }
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("unable to list directory ");
        outline107.append(this.soDirectory);
        throw new IOException(outline107.toString());
    }

    private void extractDso(InputDso inputDso, byte[] bArr) throws IOException {
        boolean writable;
        GeneratedOutlineSupport1.outline179(GeneratedOutlineSupport1.outline107("extracting DSO "), inputDso.dso.name, TAG);
        try {
            if (this.soDirectory.setWritable(true)) {
                extractDsoImpl(inputDso, bArr);
                if (writable) {
                    return;
                }
                return;
            }
            throw new IOException("cannot make directory writable for us: " + this.soDirectory);
        } finally {
            if (!this.soDirectory.setWritable(false)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("error removing ");
                outline107.append(this.soDirectory.getCanonicalPath());
                outline107.append(" write permission");
                Log.w(TAG, outline107.toString());
            }
        }
    }

    private void extractDsoImpl(InputDso inputDso, byte[] bArr) throws IOException {
        RandomAccessFile randomAccessFile;
        File file = new File(this.soDirectory, inputDso.dso.name);
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                if (file.exists() && !file.setWritable(true)) {
                    Log.w(TAG, "error adding write permission to: " + file);
                }
                try {
                    randomAccessFile = new RandomAccessFile(file, "rw");
                } catch (IOException e) {
                    Log.w(TAG, "error overwriting " + file + " trying to delete and start over", e);
                    SysUtil.dumbDeleteRecursive(file);
                    randomAccessFile = new RandomAccessFile(file, "rw");
                }
                randomAccessFile2 = randomAccessFile;
                int available = inputDso.content.available();
                if (available > 1) {
                    SysUtil.fallocateIfSupported(randomAccessFile2.getFD(), available);
                }
                SysUtil.copyBytes(randomAccessFile2, inputDso.content, Integer.MAX_VALUE, bArr);
                randomAccessFile2.setLength(randomAccessFile2.getFilePointer());
                if (file.setExecutable(true, false)) {
                    if (!file.setWritable(false)) {
                        Log.w(TAG, "error removing " + file + " write permission");
                    }
                    randomAccessFile2.close();
                    return;
                }
                throw new IOException("cannot make file executable: " + file);
            } catch (Throwable th) {
                if (!file.setWritable(false)) {
                    Log.w(TAG, "error removing " + file + " write permission");
                }
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                throw th;
            }
        } catch (IOException e2) {
            SysUtil.dumbDeleteRecursive(file);
            throw e2;
        }
    }

    private Object getLibraryLock(String str) {
        Object obj;
        synchronized (this.mLibsBeingLoaded) {
            obj = this.mLibsBeingLoaded.get(str);
            if (obj == null) {
                obj = new Object();
                this.mLibsBeingLoaded.put(str, obj);
            }
        }
        return obj;
    }

    public static File getSoStorePath(Context context, String str) {
        return new File(GeneratedOutlineSupport1.outline92(new StringBuilder(), context.getApplicationInfo().dataDir, "/", str));
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x008d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean refreshLocked(final com.facebook.soloader.FileLocker r11, int r12, final byte[] r13) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 229
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.refreshLocked(com.facebook.soloader.FileLocker, int, byte[]):boolean");
    }

    private void regenerate(byte b, DsoManifest dsoManifest, InputDsoIterator inputDsoIterator) throws IOException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("regenerating DSO store ");
        outline107.append(getClass().getName());
        outline107.toString();
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(this.soDirectory, MANIFEST_FILE_NAME), "rw");
        DsoManifest dsoManifest2 = null;
        if (b == 1) {
            try {
                try {
                    dsoManifest2 = DsoManifest.read(randomAccessFile);
                } catch (Exception e) {
                    Log.i(TAG, "error reading existing DSO manifest", e);
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        randomAccessFile.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (dsoManifest2 == null) {
            dsoManifest2 = new DsoManifest(new Dso[0]);
        }
        deleteUnmentionedFiles(dsoManifest.dsos);
        byte[] bArr = new byte[32768];
        while (inputDsoIterator.hasNext()) {
            InputDso next = inputDsoIterator.next();
            boolean z = true;
            for (int i = 0; z && i < dsoManifest2.dsos.length; i++) {
                if (dsoManifest2.dsos[i].name.equals(next.dso.name) && dsoManifest2.dsos[i].hash.equals(next.dso.hash)) {
                    z = false;
                }
            }
            if (z) {
                extractDso(next, bArr);
            }
            if (next != null) {
                next.close();
            }
        }
        randomAccessFile.close();
        String str = "Finished regenerating DSO store " + getClass().getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void writeState(File file, byte b) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            randomAccessFile.seek(0L);
            randomAccessFile.write(b);
            randomAccessFile.setLength(randomAccessFile.getFilePointer());
            randomAccessFile.getFD().sync();
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    randomAccessFile.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    protected byte[] getDepsBlock() throws IOException {
        Parcel obtain = Parcel.obtain();
        Unpacker makeUnpacker = makeUnpacker();
        try {
            Dso[] dsoArr = makeUnpacker.getDsoManifest().dsos;
            obtain.writeByte((byte) 1);
            obtain.writeInt(dsoArr.length);
            for (int i = 0; i < dsoArr.length; i++) {
                obtain.writeString(dsoArr[i].name);
                obtain.writeString(dsoArr[i].hash);
            }
            makeUnpacker.close();
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            return marshall;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (makeUnpacker != null) {
                    try {
                        makeUnpacker.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // com.facebook.soloader.SoSource
    public String[] getSoSourceAbis() {
        String[] strArr = this.mAbis;
        return strArr == null ? super.getSoSourceAbis() : strArr;
    }

    @Override // com.facebook.soloader.DirectorySoSource, com.facebook.soloader.SoSource
    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        int loadLibraryFrom;
        synchronized (getLibraryLock(str)) {
            loadLibraryFrom = loadLibraryFrom(str, i, this.soDirectory, threadPolicy);
        }
        return loadLibraryFrom;
    }

    protected abstract Unpacker makeUnpacker() throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.soloader.SoSource
    public void prepare(int i) throws IOException {
        SysUtil.mkdirOrThrow(this.soDirectory);
        FileLocker lock = FileLocker.lock(new File(this.soDirectory, LOCK_FILE_NAME));
        try {
            String str = "locked dso store " + this.soDirectory;
            if (refreshLocked(lock, i, getDepsBlock())) {
                lock = null;
            } else {
                Log.i(TAG, "dso store is up-to-date: " + this.soDirectory);
            }
        } finally {
            if (lock != null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("releasing dso store lock for ");
                outline107.append(this.soDirectory);
                outline107.toString();
                lock.close();
            } else {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("not releasing dso store lock for ");
                outline1072.append(this.soDirectory);
                outline1072.append(" (syncer thread started)");
                outline1072.toString();
            }
        }
    }

    public void setSoSourceAbis(String[] strArr) {
        this.mAbis = strArr;
    }

    protected UnpackingSoSource(Context context, File file) {
        super(file, 1);
        this.mLibsBeingLoaded = new HashMap();
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void prepare(String str) throws IOException {
        synchronized (getLibraryLock(str)) {
            this.mCorruptedLib = str;
            prepare(2);
        }
    }
}
