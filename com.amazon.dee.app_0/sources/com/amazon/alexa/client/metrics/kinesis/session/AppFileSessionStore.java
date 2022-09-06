package com.amazon.alexa.client.metrics.kinesis.session;

import android.content.Context;
import android.util.Log;
import com.amazon.alexa.client.metrics.kinesis.context.KinesisContext;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes6.dex */
public class AppFileSessionStore implements AppSessionStore {
    private static final long MAX_STORAGE_SIZE = 22560;
    private static final String SESSION_DIRECTORY_RELATIVE_PATH = "/kinesisSessions/AVS";
    private static final String SESSION_FILE_NAME = "kinesisSessionFile";
    private static final String TAG = "AppFileSessionStore";
    private File sessionDir;
    private File sessionFile;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock.ReadLock readSessionLock = this.lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeSessionLock = this.lock.writeLock();
    private BufferedReader reader = null;

    public AppFileSessionStore(Context context, KinesisContext kinesisContext) {
        try {
            this.sessionDir = new File(context.getFilesDir() + SESSION_DIRECTORY_RELATIVE_PATH);
            if (!this.sessionDir.exists()) {
                this.sessionDir.mkdirs();
            }
            this.sessionFile = new File(this.sessionDir, SESSION_FILE_NAME);
            this.sessionFile.createNewFile();
        } catch (IOException | SecurityException e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Error in creating the session file "), TAG);
        }
    }

    private void createFileDirectories(File file) {
        if (file.getParentFile().mkdirs() || file.getParentFile().exists()) {
            return;
        }
        throw new AppSessionStoreException(String.format("Could not create directories for file - %s", file.getAbsolutePath()));
    }

    private void tryCloseReader() {
        if (this.reader != null) {
            this.readSessionLock.lock();
            try {
                try {
                    this.reader.close();
                } catch (IOException e) {
                    String str = TAG;
                    Log.e(str, "Unable to close reader for appSession file " + e.getMessage());
                }
                this.reader = null;
            } finally {
                this.readSessionLock.unlock();
            }
        }
    }

    private void tryCloseWriter(Writer writer) throws AppSessionStoreException {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to close writer for appSession file ");
                outline107.append(e.getMessage());
                Log.e(str, outline107.toString());
            }
        }
    }

    private Writer tryInitializeWriter() throws AppSessionStoreException {
        try {
            createFileDirectories(this.sessionFile);
            return new OutputStreamWriter(new FileOutputStream(this.sessionFile), StringUtils.UTF8);
        } catch (FileNotFoundException e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to save appSession file ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
            throw new AppSessionStoreException("Unable to save appSession file", e);
        } catch (Exception e2) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unexpected exception ");
            outline1072.append(e2.getMessage());
            Log.e(str2, outline1072.toString());
            throw new AppSessionStoreException("Unable to save appSession file", e2);
        }
    }

    private boolean tryOpenReader() {
        if (this.reader != null) {
            return true;
        }
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(this.sessionFile), StringUtils.UTF8);
        } catch (FileNotFoundException e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not open the appSession file ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
        }
        if (inputStreamReader == null) {
            return false;
        }
        this.reader = new BufferedReader(inputStreamReader);
        return true;
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.AppSessionStore
    public AppSession getSession() {
        File file;
        this.readSessionLock.lock();
        String str = null;
        try {
            try {
                tryOpenReader();
                if (this.reader != null) {
                    str = this.reader.readLine();
                }
                tryCloseReader();
                if (this.sessionFile.delete() || !this.sessionFile.exists()) {
                    this.sessionFile = new File(this.sessionDir, SESSION_FILE_NAME);
                    try {
                        this.sessionFile.createNewFile();
                    } catch (IOException e) {
                        String str2 = TAG;
                        Log.e(str2, "Unable to clear session file " + e.getMessage());
                    }
                }
                file = this.sessionFile;
            } catch (IOException e2) {
                String str3 = TAG;
                Log.e(str3, "Failed to read the appSession " + e2.getMessage());
                tryCloseReader();
                if (this.sessionFile.delete() || !this.sessionFile.exists()) {
                    this.sessionFile = new File(this.sessionDir, SESSION_FILE_NAME);
                    try {
                        this.sessionFile.createNewFile();
                    } catch (IOException e3) {
                        String str4 = TAG;
                        Log.e(str4, "Unable to clear session file " + e3.getMessage());
                    }
                }
                file = this.sessionFile;
            }
            file.exists();
            this.readSessionLock.unlock();
            return AppSession.getSessionFromSerializedSession(str);
        } catch (Throwable th) {
            this.readSessionLock.unlock();
            throw th;
        }
    }

    @Override // com.amazon.alexa.client.metrics.kinesis.session.AppSessionStore
    public void storeSession(AppSession appSession) throws AppSessionStoreException {
        String jSONObject = appSession.toJSONObject().toString();
        this.writeSessionLock.lock();
        try {
            try {
                Writer tryInitializeWriter = tryInitializeWriter();
                if (tryInitializeWriter != null) {
                    if (this.sessionFile.length() + jSONObject.length() <= MAX_STORAGE_SIZE) {
                        tryInitializeWriter.write(jSONObject);
                        tryInitializeWriter.flush();
                    } else {
                        String.format("The appSession file exceeded its allowed size of %d bytes", Long.valueOf((long) MAX_STORAGE_SIZE));
                    }
                }
                tryCloseWriter(tryInitializeWriter);
            } catch (IOException e) {
                String str = TAG;
                Log.e(str, "Failed to persist the appSession " + e.getMessage());
                throw new AppSessionStoreException("Failed to persist the appSession", e);
            }
        } finally {
            this.writeSessionLock.unlock();
        }
    }
}
