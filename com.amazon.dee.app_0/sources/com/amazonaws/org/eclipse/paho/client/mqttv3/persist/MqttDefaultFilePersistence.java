package com.amazonaws.org.eclipse.paho.client.mqttv3.persist;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistable;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.FileLock;
import com.amazonaws.org.eclipse.paho.client.mqttv3.internal.MqttPersistentData;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes13.dex */
public class MqttDefaultFilePersistence implements MqttClientPersistence {
    private static final FilenameFilter FILE_FILTER = new FilenameFilter() { // from class: com.amazonaws.org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence.1
        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.endsWith(MqttDefaultFilePersistence.MESSAGE_FILE_EXTENSION);
        }
    };
    private static final String LOCK_FILENAME = ".lck";
    private static final String MESSAGE_BACKUP_FILE_EXTENSION = ".bup";
    private static final String MESSAGE_FILE_EXTENSION = ".msg";
    private File clientDir;
    private File dataDir;
    private FileLock fileLock;

    public MqttDefaultFilePersistence() {
        this(System.getProperty("user.dir"));
    }

    private void checkIsOpen() throws MqttPersistenceException {
        if (this.clientDir != null) {
            return;
        }
        throw new MqttPersistenceException();
    }

    private File[] getFiles() throws MqttPersistenceException {
        checkIsOpen();
        File[] listFiles = this.clientDir.listFiles(FILE_FILTER);
        if (listFiles != null) {
            return listFiles;
        }
        throw new MqttPersistenceException();
    }

    private boolean isSafeChar(char c) {
        return Character.isJavaIdentifierPart(c) || c == '-';
    }

    private void restoreBackups(File file) throws MqttPersistenceException {
        File[] listFiles = file.listFiles(new FileFilter() { // from class: com.amazonaws.org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence.2
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.getName().endsWith(MqttDefaultFilePersistence.MESSAGE_BACKUP_FILE_EXTENSION);
            }
        });
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                File file2 = new File(file, listFiles[i].getName().substring(0, listFiles[i].getName().length() - 4));
                if (!listFiles[i].renameTo(file2)) {
                    file2.delete();
                    listFiles[i].renameTo(file2);
                }
            }
            return;
        }
        throw new MqttPersistenceException();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void clear() throws MqttPersistenceException {
        checkIsOpen();
        for (File file : getFiles()) {
            file.delete();
        }
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void close() throws MqttPersistenceException {
        synchronized (this) {
            if (this.fileLock != null) {
                this.fileLock.release();
            }
            if (getFiles().length == 0) {
                this.clientDir.delete();
            }
            this.clientDir = null;
        }
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public boolean containsKey(String str) throws MqttPersistenceException {
        checkIsOpen();
        File file = this.clientDir;
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(str));
        stringBuffer.append(MESSAGE_FILE_EXTENSION);
        return new File(file, stringBuffer.toString()).exists();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public MqttPersistable get(String str) throws MqttPersistenceException {
        checkIsOpen();
        try {
            File file = this.clientDir;
            StringBuffer stringBuffer = new StringBuffer(String.valueOf(str));
            stringBuffer.append(MESSAGE_FILE_EXTENSION);
            FileInputStream fileInputStream = new FileInputStream(new File(file, stringBuffer.toString()));
            int available = fileInputStream.available();
            byte[] bArr = new byte[available];
            for (int i = 0; i < available; i += fileInputStream.read(bArr, i, available - i)) {
            }
            fileInputStream.close();
            return new MqttPersistentData(str, bArr, 0, bArr.length, null, 0, 0);
        } catch (IOException e) {
            throw new MqttPersistenceException(e);
        }
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public Enumeration keys() throws MqttPersistenceException {
        String name;
        checkIsOpen();
        File[] files = getFiles();
        Vector vector = new Vector(files.length);
        for (File file : files) {
            vector.addElement(file.getName().substring(0, name.length() - 4));
        }
        return vector.elements();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void open(String str, String str2) throws MqttPersistenceException {
        if (this.dataDir.exists() && !this.dataDir.isDirectory()) {
            throw new MqttPersistenceException();
        }
        if (!this.dataDir.exists() && !this.dataDir.mkdirs()) {
            throw new MqttPersistenceException();
        }
        if (this.dataDir.canWrite()) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                if (isSafeChar(charAt)) {
                    stringBuffer.append(charAt);
                }
            }
            stringBuffer.append(ProcessIdUtil.DEFAULT_PROCESSID);
            for (int i2 = 0; i2 < str2.length(); i2++) {
                char charAt2 = str2.charAt(i2);
                if (isSafeChar(charAt2)) {
                    stringBuffer.append(charAt2);
                }
            }
            synchronized (this) {
                if (this.clientDir == null) {
                    this.clientDir = new File(this.dataDir, stringBuffer.toString());
                    if (!this.clientDir.exists()) {
                        this.clientDir.mkdir();
                    }
                }
                try {
                    this.fileLock = new FileLock(this.clientDir, LOCK_FILENAME);
                } catch (Exception unused) {
                }
                restoreBackups(this.clientDir);
            }
            return;
        }
        throw new MqttPersistenceException();
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void put(String str, MqttPersistable mqttPersistable) throws MqttPersistenceException {
        boolean exists;
        checkIsOpen();
        File file = this.clientDir;
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(str));
        stringBuffer.append(MESSAGE_FILE_EXTENSION);
        File file2 = new File(file, stringBuffer.toString());
        File file3 = new File(this.clientDir, GeneratedOutlineSupport1.outline83(new StringBuffer(String.valueOf(str)), MESSAGE_FILE_EXTENSION, MESSAGE_BACKUP_FILE_EXTENSION));
        if (file2.exists() && !file2.renameTo(file3)) {
            file3.delete();
            file2.renameTo(file3);
        }
        try {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                fileOutputStream.write(mqttPersistable.getHeaderBytes(), mqttPersistable.getHeaderOffset(), mqttPersistable.getHeaderLength());
                if (mqttPersistable.getPayloadBytes() != null) {
                    fileOutputStream.write(mqttPersistable.getPayloadBytes(), mqttPersistable.getPayloadOffset(), mqttPersistable.getPayloadLength());
                }
                fileOutputStream.getFD().sync();
                fileOutputStream.close();
                if (file3.exists()) {
                    file3.delete();
                }
                if (!exists) {
                    return;
                }
            } catch (IOException e) {
                throw new MqttPersistenceException(e);
            }
        } finally {
            if (file3.exists() && !file3.renameTo(file2)) {
                file2.delete();
                file3.renameTo(file2);
            }
        }
    }

    @Override // com.amazonaws.org.eclipse.paho.client.mqttv3.MqttClientPersistence
    public void remove(String str) throws MqttPersistenceException {
        checkIsOpen();
        File file = this.clientDir;
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(str));
        stringBuffer.append(MESSAGE_FILE_EXTENSION);
        File file2 = new File(file, stringBuffer.toString());
        if (file2.exists()) {
            file2.delete();
        }
    }

    public MqttDefaultFilePersistence(String str) {
        this.clientDir = null;
        this.fileLock = null;
        this.dataDir = new File(str);
    }
}
