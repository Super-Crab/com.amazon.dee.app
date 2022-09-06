package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class ContentProperties implements Comparable<ContentProperties> {
    private String contentDate;
    private ContentSignatures contentSignatures;
    private String contentType;
    private DocumentProperties document;
    private String extension;
    private ImageProperties image;
    private String md5;
    private Long size;
    private Long version;
    private VideoProperties video;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ContentProperties) && compareTo((ContentProperties) obj) == 0;
    }

    public String getContentDate() {
        return this.contentDate;
    }

    public ContentSignatures getContentSignatures() {
        return this.contentSignatures;
    }

    public String getContentType() {
        return this.contentType;
    }

    public DocumentProperties getDocument() {
        return this.document;
    }

    public String getExtension() {
        return this.extension;
    }

    public ImageProperties getImage() {
        return this.image;
    }

    public String getMd5() {
        return this.md5;
    }

    public Long getSize() {
        return this.size;
    }

    public Long getVersion() {
        return this.version;
    }

    public VideoProperties getVideo() {
        return this.video;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getContentType() == null ? 0 : getContentType().hashCode()) + 1 + (getExtension() == null ? 0 : getExtension().hashCode()) + (getMd5() == null ? 0 : getMd5().hashCode()) + (getDocument() == null ? 0 : getDocument().hashCode()) + (getVideo() == null ? 0 : getVideo().hashCode()) + (getContentDate() == null ? 0 : getContentDate().hashCode()) + (getSize() == null ? 0 : getSize().hashCode()) + (getVersion() == null ? 0 : getVersion().hashCode()) + (getImage() == null ? 0 : getImage().hashCode());
        if (getContentSignatures() != null) {
            i = getContentSignatures().hashCode();
        }
        return hashCode + i;
    }

    public void setContentDate(String str) {
        this.contentDate = str;
    }

    public void setContentSignatures(ContentSignatures contentSignatures) {
        this.contentSignatures = contentSignatures;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public void setDocument(DocumentProperties documentProperties) {
        this.document = documentProperties;
    }

    public void setExtension(String str) {
        this.extension = str;
    }

    public void setImage(ImageProperties imageProperties) {
        this.image = imageProperties;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public void setSize(Long l) {
        this.size = l;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public void setVideo(VideoProperties videoProperties) {
        this.video = videoProperties;
    }

    @Override // java.lang.Comparable
    public int compareTo(ContentProperties contentProperties) {
        if (contentProperties == null) {
            return -1;
        }
        if (contentProperties == this) {
            return 0;
        }
        String contentType = getContentType();
        String contentType2 = contentProperties.getContentType();
        if (contentType != contentType2) {
            if (contentType == null) {
                return -1;
            }
            if (contentType2 == null) {
                return 1;
            }
            int compareTo = contentType.compareTo(contentType2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String extension = getExtension();
        String extension2 = contentProperties.getExtension();
        if (extension != extension2) {
            if (extension == null) {
                return -1;
            }
            if (extension2 == null) {
                return 1;
            }
            int compareTo2 = extension.compareTo(extension2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String md5 = getMd5();
        String md52 = contentProperties.getMd5();
        if (md5 != md52) {
            if (md5 == null) {
                return -1;
            }
            if (md52 == null) {
                return 1;
            }
            int compareTo3 = md5.compareTo(md52);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        ContentSignatures contentSignatures = getContentSignatures();
        ContentSignatures contentSignatures2 = contentProperties.getContentSignatures();
        if (contentSignatures != contentSignatures2) {
            if (contentSignatures == null) {
                return -1;
            }
            if (contentSignatures2 == null) {
                return 1;
            }
            int compareTo4 = contentSignatures.compareTo(contentSignatures2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        DocumentProperties document = getDocument();
        DocumentProperties document2 = contentProperties.getDocument();
        if (document != document2) {
            if (document == null) {
                return -1;
            }
            if (document2 == null) {
                return 1;
            }
            int compareTo5 = document.compareTo(document2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        VideoProperties video = getVideo();
        VideoProperties video2 = contentProperties.getVideo();
        if (video != video2) {
            if (video == null) {
                return -1;
            }
            if (video2 == null) {
                return 1;
            }
            int compareTo6 = video.compareTo(video2);
            if (compareTo6 != 0) {
                return compareTo6;
            }
        }
        String contentDate = getContentDate();
        String contentDate2 = contentProperties.getContentDate();
        if (contentDate != contentDate2) {
            if (contentDate == null) {
                return -1;
            }
            if (contentDate2 == null) {
                return 1;
            }
            int compareTo7 = contentDate.compareTo(contentDate2);
            if (compareTo7 != 0) {
                return compareTo7;
            }
        }
        Long size = getSize();
        Long size2 = contentProperties.getSize();
        if (size != size2) {
            if (size == null) {
                return -1;
            }
            if (size2 == null) {
                return 1;
            }
            int compareTo8 = size.compareTo(size2);
            if (compareTo8 != 0) {
                return compareTo8;
            }
        }
        Long version = getVersion();
        Long version2 = contentProperties.getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            int compareTo9 = version.compareTo(version2);
            if (compareTo9 != 0) {
                return compareTo9;
            }
        }
        ImageProperties image = getImage();
        ImageProperties image2 = contentProperties.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int compareTo10 = image.compareTo(image2);
            if (compareTo10 != 0) {
                return compareTo10;
            }
        }
        return 0;
    }
}
