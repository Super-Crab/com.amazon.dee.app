package net.sqlcipher.database;
/* loaded from: classes4.dex */
public abstract class SQLiteClosable {
    private int mReferenceCount = 1;
    private Object mLock = new Object();

    private String getObjInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" (");
        if (this instanceof SQLiteDatabase) {
            sb.append("database = ");
            sb.append(((SQLiteDatabase) this).getPath());
        } else if ((this instanceof SQLiteProgram) || (this instanceof SQLiteStatement) || (this instanceof SQLiteQuery)) {
            sb.append("mSql = ");
            sb.append(((SQLiteProgram) this).mSql);
        }
        sb.append(") ");
        return sb.toString();
    }

    public void acquireReference() {
        synchronized (this.mLock) {
            if (this.mReferenceCount > 0) {
                this.mReferenceCount++;
            } else {
                throw new IllegalStateException("attempt to re-open an already-closed object: " + getObjInfo());
            }
        }
    }

    protected abstract void onAllReferencesReleased();

    /* JADX INFO: Access modifiers changed from: protected */
    public void onAllReferencesReleasedFromContainer() {
    }

    public void releaseReference() {
        synchronized (this.mLock) {
            this.mReferenceCount--;
            if (this.mReferenceCount == 0) {
                onAllReferencesReleased();
            }
        }
    }

    public void releaseReferenceFromContainer() {
        synchronized (this.mLock) {
            this.mReferenceCount--;
            if (this.mReferenceCount == 0) {
                onAllReferencesReleasedFromContainer();
            }
        }
    }
}
