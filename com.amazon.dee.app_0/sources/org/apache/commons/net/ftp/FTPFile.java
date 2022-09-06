package org.apache.commons.net.ftp;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Calendar;
/* loaded from: classes4.dex */
public class FTPFile implements Serializable {
    public static final int DIRECTORY_TYPE = 1;
    public static final int EXECUTE_PERMISSION = 2;
    public static final int FILE_TYPE = 0;
    public static final int GROUP_ACCESS = 1;
    public static final int READ_PERMISSION = 0;
    public static final int SYMBOLIC_LINK_TYPE = 2;
    public static final int UNKNOWN_TYPE = 3;
    public static final int USER_ACCESS = 0;
    public static final int WORLD_ACCESS = 2;
    public static final int WRITE_PERMISSION = 1;
    String _link;
    boolean[][] _permissions = (boolean[][]) Array.newInstance(boolean.class, 3, 3);
    String _rawListing = null;
    int _type = 3;
    int _hardLinkCount = 0;
    long _size = 0;
    String _user = null;
    String _group = null;
    Calendar _date = null;
    String _name = null;

    public String getGroup() {
        return this._group;
    }

    public int getHardLinkCount() {
        return this._hardLinkCount;
    }

    public String getLink() {
        return this._link;
    }

    public String getName() {
        return this._name;
    }

    public String getRawListing() {
        return this._rawListing;
    }

    public long getSize() {
        return this._size;
    }

    public Calendar getTimestamp() {
        return this._date;
    }

    public int getType() {
        return this._type;
    }

    public String getUser() {
        return this._user;
    }

    public boolean hasPermission(int i, int i2) {
        return this._permissions[i][i2];
    }

    public boolean isDirectory() {
        return this._type == 1;
    }

    public boolean isFile() {
        return this._type == 0;
    }

    public boolean isSymbolicLink() {
        return this._type == 2;
    }

    public boolean isUnknown() {
        return this._type == 3;
    }

    public void setGroup(String str) {
        this._group = str;
    }

    public void setHardLinkCount(int i) {
        this._hardLinkCount = i;
    }

    public void setLink(String str) {
        this._link = str;
    }

    public void setName(String str) {
        this._name = str;
    }

    public void setPermission(int i, int i2, boolean z) {
        this._permissions[i][i2] = z;
    }

    public void setRawListing(String str) {
        this._rawListing = str;
    }

    public void setSize(long j) {
        this._size = j;
    }

    public void setTimestamp(Calendar calendar) {
        this._date = calendar;
    }

    public void setType(int i) {
        this._type = i;
    }

    public void setUser(String str) {
        this._user = str;
    }

    public String toString() {
        return this._rawListing;
    }
}
