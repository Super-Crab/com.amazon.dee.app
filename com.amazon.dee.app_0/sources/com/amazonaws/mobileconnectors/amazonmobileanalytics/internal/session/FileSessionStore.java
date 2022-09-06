package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session;

import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager;
import com.amazonaws.util.StringUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.concurrent.locks.ReentrantReadWriteLock;
@Deprecated
/* loaded from: classes13.dex */
public class FileSessionStore implements SessionStore {
    static final String KEY_MAX_STORAGE_SIZE = "maxStorageSize";
    static final long MAX_STORAGE_SIZE = 22560;
    protected static final String SESSION_DIRECTORY = "sessions";
    protected static final String SESSION_FILE_NAME = "sessionFile";
    private static final String TAG = "FileSessionStore";
    private final AnalyticsContext context;
    private File sessionFile;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock.ReadLock readSessionLock = this.lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeSessionLock = this.lock.writeLock();
    private BufferedReader reader = null;

    public FileSessionStore(AnalyticsContext analyticsContext) {
        this.context = analyticsContext;
        FileManager fileManager = this.context.getSystem().getFileManager();
        try {
            this.sessionFile = fileManager.createFile(new File(fileManager.createDirectory(SESSION_DIRECTORY), SESSION_FILE_NAME));
        } catch (IOException e) {
            Log.e(TAG, "An error occurred while attempting to create/open the session file", e);
        }
    }

    private void createFileDirectories(File file) {
        if (file.getParentFile().mkdirs() || file.getParentFile().exists()) {
            return;
        }
        String format = String.format("Could not create directories for file - %s", file.getAbsolutePath());
        Log.e(TAG, format);
        throw new SessionStoreException(format);
    }

    private void tryCloseReader() {
        if (this.reader != null) {
            this.readSessionLock.lock();
            try {
                try {
                    this.reader.close();
                } finally {
                    this.readSessionLock.unlock();
                }
            } catch (IOException e) {
                Log.e(TAG, "Unable to close reader for session file", e);
            }
            this.reader = null;
        }
    }

    private void tryCloseWriter(Writer writer) throws SessionStoreException {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                Log.e(TAG, "Unable to close writer for session file", e);
            }
        }
    }

    private Writer tryInitializeWriter() throws SessionStoreException {
        try {
            createFileDirectories(this.sessionFile);
            return new OutputStreamWriter(this.context.getSystem().getFileManager().newOutputStream(this.sessionFile, false), StringUtils.UTF8);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Unable to save session file", e);
            throw new SessionStoreException("Unable to save session file", e);
        } catch (Exception e2) {
            Log.e(TAG, "Unexpected exception", e2);
            throw new SessionStoreException("Unable to save session file", e2);
        }
    }

    private boolean tryOpenReader() {
        if (this.reader != null) {
            return true;
        }
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(this.context.getSystem().getFileManager().newInputStream(this.sessionFile), StringUtils.UTF8);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Could not open the session file", e);
        }
        if (inputStreamReader == null) {
            return false;
        }
        this.reader = new BufferedReader(inputStreamReader);
        return true;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.SessionStore
    public Session getSession() {
        FileManager fileManager;
        this.readSessionLock.lock();
        String str = null;
        try {
            try {
                tryOpenReader();
                if (this.reader != null) {
                    str = this.reader.readLine();
                }
                tryCloseReader();
                fileManager = this.context.getSystem().getFileManager();
            } catch (Throwable th) {
                this.readSessionLock.unlock();
                throw th;
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to read the session", e);
            tryCloseReader();
            FileManager fileManager2 = this.context.getSystem().getFileManager();
            try {
                if (fileManager2.deleteFile(this.sessionFile) || !this.sessionFile.exists()) {
                    this.sessionFile = fileManager2.createFile(this.sessionFile);
                }
            } catch (IOException e2) {
                e = e2;
                Log.e(TAG, "Unable to clear session file", e);
                this.readSessionLock.unlock();
                return Session.getSessionFromSerializedSession(str);
            }
        }
        try {
            if (fileManager.deleteFile(this.sessionFile) || !this.sessionFile.exists()) {
                this.sessionFile = fileManager.createFile(this.sessionFile);
            }
        } catch (IOException e3) {
            e = e3;
            Log.e(TAG, "Unable to clear session file", e);
            this.readSessionLock.unlock();
            return Session.getSessionFromSerializedSession(str);
        }
        this.readSessionLock.unlock();
        return Session.getSessionFromSerializedSession(str);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.session.SessionStore
    public void storeSession(Session session) throws SessionStoreException {
        String jSONObject = session.toJSONObject().toString();
        this.writeSessionLock.lock();
        try {
            try {
                Writer tryInitializeWriter = tryInitializeWriter();
                if (tryInitializeWriter != null) {
                    long longValue = this.context.getConfiguration().optLong(KEY_MAX_STORAGE_SIZE, Long.valueOf((long) MAX_STORAGE_SIZE)).longValue();
                    if (this.sessionFile.length() + jSONObject.length() <= longValue) {
                        tryInitializeWriter.write(jSONObject);
                        tryInitializeWriter.flush();
                    } else {
                        Log.e(TAG, "The session file exceeded its allowed size of " + longValue + " bytes");
                    }
                }
                tryCloseWriter(tryInitializeWriter);
            } finally {
                this.writeSessionLock.unlock();
            }
        } catch (IOException e) {
            Log.e(TAG, "Failed to persist the session", e);
            throw new SessionStoreException("Failed to persist the session", e);
        }
    }
}
