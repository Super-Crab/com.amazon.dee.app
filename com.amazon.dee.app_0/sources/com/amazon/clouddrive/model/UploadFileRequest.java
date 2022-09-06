package com.amazon.clouddrive.model;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class UploadFileRequest extends PostNodeRequest {
    private String mContentDate;
    private long mContentLength;
    private InputStream mInputStream;
    private String mMD5;
    private Suppress mSuppress;
    private int mBlockSize = 32768;
    private boolean mUseChunkedStreaming = false;
    private int mChunkSize = 0;

    public UploadFileRequest(String str, InputStream inputStream, long j) {
        setName(str);
        setKind("FILE");
        this.mInputStream = inputStream;
        this.mContentLength = j;
    }

    @Override // com.amazon.clouddrive.model.PostNodeRequest
    public int compareTo(EditableNodeRequest editableNodeRequest) {
        if (editableNodeRequest == null) {
            return -1;
        }
        if (editableNodeRequest == this) {
            return 0;
        }
        if (!(editableNodeRequest instanceof UploadFileRequest)) {
            return 1;
        }
        UploadFileRequest uploadFileRequest = (UploadFileRequest) editableNodeRequest;
        InputStream inputStream = getInputStream();
        InputStream inputStream2 = uploadFileRequest.getInputStream();
        if (inputStream != inputStream2) {
            if (inputStream == null) {
                return -1;
            }
            if (inputStream2 == null) {
                return 1;
            }
            if (inputStream instanceof Comparable) {
                int compareTo = ((Comparable) inputStream).compareTo(inputStream2);
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (!inputStream.equals(inputStream2)) {
                int hashCode = inputStream.hashCode();
                int hashCode2 = inputStream2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        Integer valueOf = Integer.valueOf(getBlockSize());
        Integer valueOf2 = Integer.valueOf(uploadFileRequest.getBlockSize());
        if (valueOf != valueOf2) {
            if (valueOf == null) {
                return -1;
            }
            if (valueOf2 == null) {
                return 1;
            }
            int compareTo2 = valueOf.compareTo(valueOf2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String suppress = getSuppress();
        String suppress2 = uploadFileRequest.getSuppress();
        if (suppress != suppress2) {
            if (suppress == null) {
                return -1;
            }
            if (suppress2 == null) {
                return 1;
            }
            int compareTo3 = suppress.compareTo(suppress2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        Long valueOf3 = Long.valueOf(getContentLength());
        Long valueOf4 = Long.valueOf(uploadFileRequest.getContentLength());
        if (valueOf3 != valueOf4) {
            if (valueOf3 == null) {
                return -1;
            }
            if (valueOf4 == null) {
                return 1;
            }
            int compareTo4 = valueOf3.compareTo(valueOf4);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        Boolean valueOf5 = Boolean.valueOf(useChunkedStreaming());
        Boolean valueOf6 = Boolean.valueOf(uploadFileRequest.useChunkedStreaming());
        if (valueOf5 != valueOf6) {
            if (valueOf5 == null) {
                return -1;
            }
            if (valueOf6 == null) {
                return 1;
            }
            int compareTo5 = valueOf5.compareTo(valueOf6);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        Integer valueOf7 = Integer.valueOf(getChunkSize());
        Integer valueOf8 = Integer.valueOf(uploadFileRequest.getChunkSize());
        if (valueOf7 != valueOf8) {
            if (valueOf7 == null) {
                return -1;
            }
            if (valueOf8 == null) {
                return 1;
            }
            int compareTo6 = valueOf7.compareTo(valueOf8);
            if (compareTo6 != 0) {
                return compareTo6;
            }
        }
        String md5 = getMD5();
        String md52 = uploadFileRequest.getMD5();
        if (md5 != md52) {
            if (md5 == null) {
                return -1;
            }
            if (md52 == null) {
                return 1;
            }
            int compareTo7 = md5.compareTo(md52);
            if (compareTo7 != 0) {
                return compareTo7;
            }
        }
        return super.compareTo(editableNodeRequest);
    }

    @Override // com.amazon.clouddrive.model.PostNodeRequest, com.amazon.clouddrive.model.EditableNodeRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof UploadFileRequest) && compareTo((EditableNodeRequest) ((UploadFileRequest) obj)) == 0;
    }

    public int getBlockSize() {
        return this.mBlockSize;
    }

    public int getChunkSize() {
        return this.mChunkSize;
    }

    public String getContentDate() {
        return this.mContentDate;
    }

    public long getContentLength() {
        return this.mContentLength;
    }

    public InputStream getInputStream() {
        return this.mInputStream;
    }

    public String getMD5() {
        return this.mMD5;
    }

    public String getSuppress() {
        Suppress suppress = this.mSuppress;
        if (suppress == null) {
            return null;
        }
        return suppress.parameter;
    }

    @Override // com.amazon.clouddrive.model.PostNodeRequest, com.amazon.clouddrive.model.EditableNodeRequest
    public int hashCode() {
        int i = 0;
        int hashCode = Integer.valueOf(getChunkSize()).hashCode() + Boolean.valueOf(useChunkedStreaming()).hashCode() + Long.valueOf(getContentLength()).hashCode() + Integer.valueOf(getBlockSize()).hashCode() + (getInputStream() == null ? 0 : getInputStream().hashCode()) + 1 + (getSuppress() == null ? 0 : getSuppress().hashCode());
        if (getMD5() != null) {
            i = getMD5().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setBlockSize(int i) {
        if (i >= 1) {
            this.mBlockSize = i;
            return;
        }
        throw new IllegalArgumentException("The block size must be at the very least 1.");
    }

    public void setChunkSize(int i) {
        this.mChunkSize = i;
    }

    public void setChunkedStreaming(boolean z) {
        this.mUseChunkedStreaming = z;
    }

    public void setContentDate(String str) {
        this.mContentDate = str;
    }

    public void setContentLength(long j) {
        this.mContentLength = j;
    }

    public void setInputStream(InputStream inputStream) {
        this.mInputStream = inputStream;
    }

    public void setMD5(String str) {
        this.mMD5 = str;
    }

    public void setSuppress(Suppress suppress) {
        this.mSuppress = suppress;
    }

    public boolean useChunkedStreaming() {
        return this.mUseChunkedStreaming;
    }

    public UploadFileRequest withBlockSize(int i) {
        setBlockSize(i);
        return this;
    }

    public UploadFileRequest withChunkSize(int i) {
        setChunkSize(i);
        return this;
    }

    public UploadFileRequest withChunkedStreaming(boolean z) {
        setChunkedStreaming(z);
        return this;
    }

    public UploadFileRequest withConflictResolution(String str) {
        setConflictResolution(str);
        return this;
    }

    public UploadFileRequest withContentDate(String str) {
        this.mContentDate = str;
        return this;
    }

    public UploadFileRequest withContentProperties(ContentProperties contentProperties) {
        setContentProperties(contentProperties);
        return this;
    }

    public UploadFileRequest withDescription(String str) {
        setDescription(str);
        return this;
    }

    public UploadFileRequest withKind(String str) {
        setKind(str);
        return this;
    }

    public UploadFileRequest withLabels(List<String> list) {
        setLabels(list);
        return this;
    }

    public UploadFileRequest withLocalId(String str) {
        setLocalId(str);
        return this;
    }

    public UploadFileRequest withMD5(String str) {
        setMD5(str);
        return this;
    }

    public UploadFileRequest withParents(List<String> list) {
        setParents(list);
        return this;
    }

    public UploadFileRequest withProperties(Map<String, Map<String, String>> map) {
        setProperties(map);
        return this;
    }

    public UploadFileRequest withSubKindProperties(Map<String, Map<String, String>> map) {
        setSubKindProperties(map);
        return this;
    }

    public UploadFileRequest withSubKinds(List<String> list) {
        setSubKinds(list);
        return this;
    }

    public UploadFileRequest withSuppress(Suppress suppress) {
        setSuppress(suppress);
        return this;
    }

    public UploadFileRequest withSymbolicNodeHeroId(String str) {
        setSymbolicNodeHeroId(str);
        return this;
    }
}
