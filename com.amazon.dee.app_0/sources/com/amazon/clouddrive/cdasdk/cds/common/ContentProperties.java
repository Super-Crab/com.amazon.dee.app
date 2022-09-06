package com.amazon.clouddrive.cdasdk.cds.common;

import com.amazon.alexa.handsfree.protocols.sierracontentprovider.SierraContentProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class ContentProperties {
    @JsonProperty("address")
    private Address address;
    @JsonProperty("contentDate")
    private ISO8601 contentDate;
    @JsonProperty("contentSignatures")
    private List<ContentSignature> contentSignatures;
    @JsonProperty("contentType")
    private String contentType;
    @JsonProperty("document")
    private DocumentProperties document;
    @JsonProperty("extension")
    private String extension;
    @JsonProperty("image")
    private ImageProperties image;
    @JsonProperty("locationId")
    private String locationId;
    @JsonProperty(SierraContentProviderContract.MD5_VALUE)
    private String md5;
    @JsonProperty("size")
    private Long size;
    @JsonProperty("version")
    private Long version;
    @JsonProperty("video")
    private VideoProperties video;

    protected boolean canEqual(Object obj) {
        return obj instanceof ContentProperties;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContentProperties)) {
            return false;
        }
        ContentProperties contentProperties = (ContentProperties) obj;
        if (!contentProperties.canEqual(this)) {
            return false;
        }
        Long version = getVersion();
        Long version2 = contentProperties.getVersion();
        if (version != null ? !version.equals(version2) : version2 != null) {
            return false;
        }
        String md5 = getMd5();
        String md52 = contentProperties.getMd5();
        if (md5 != null ? !md5.equals(md52) : md52 != null) {
            return false;
        }
        Long size = getSize();
        Long size2 = contentProperties.getSize();
        if (size != null ? !size.equals(size2) : size2 != null) {
            return false;
        }
        String contentType = getContentType();
        String contentType2 = contentProperties.getContentType();
        if (contentType != null ? !contentType.equals(contentType2) : contentType2 != null) {
            return false;
        }
        String extension = getExtension();
        String extension2 = contentProperties.getExtension();
        if (extension != null ? !extension.equals(extension2) : extension2 != null) {
            return false;
        }
        ISO8601 contentDate = getContentDate();
        ISO8601 contentDate2 = contentProperties.getContentDate();
        if (contentDate != null ? !contentDate.equals(contentDate2) : contentDate2 != null) {
            return false;
        }
        ImageProperties image = getImage();
        ImageProperties image2 = contentProperties.getImage();
        if (image != null ? !image.equals(image2) : image2 != null) {
            return false;
        }
        VideoProperties video = getVideo();
        VideoProperties video2 = contentProperties.getVideo();
        if (video != null ? !video.equals(video2) : video2 != null) {
            return false;
        }
        DocumentProperties document = getDocument();
        DocumentProperties document2 = contentProperties.getDocument();
        if (document != null ? !document.equals(document2) : document2 != null) {
            return false;
        }
        String locationId = getLocationId();
        String locationId2 = contentProperties.getLocationId();
        if (locationId != null ? !locationId.equals(locationId2) : locationId2 != null) {
            return false;
        }
        Address address = getAddress();
        Address address2 = contentProperties.getAddress();
        if (address != null ? !address.equals(address2) : address2 != null) {
            return false;
        }
        List<ContentSignature> contentSignatures = getContentSignatures();
        List<ContentSignature> contentSignatures2 = contentProperties.getContentSignatures();
        return contentSignatures != null ? contentSignatures.equals(contentSignatures2) : contentSignatures2 == null;
    }

    public Address getAddress() {
        return this.address;
    }

    public ISO8601 getContentDate() {
        return this.contentDate;
    }

    public List<ContentSignature> getContentSignatures() {
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

    public String getLocationId() {
        return this.locationId;
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
        Long version = getVersion();
        int i = 43;
        int hashCode = version == null ? 43 : version.hashCode();
        String md5 = getMd5();
        int hashCode2 = ((hashCode + 59) * 59) + (md5 == null ? 43 : md5.hashCode());
        Long size = getSize();
        int hashCode3 = (hashCode2 * 59) + (size == null ? 43 : size.hashCode());
        String contentType = getContentType();
        int hashCode4 = (hashCode3 * 59) + (contentType == null ? 43 : contentType.hashCode());
        String extension = getExtension();
        int hashCode5 = (hashCode4 * 59) + (extension == null ? 43 : extension.hashCode());
        ISO8601 contentDate = getContentDate();
        int hashCode6 = (hashCode5 * 59) + (contentDate == null ? 43 : contentDate.hashCode());
        ImageProperties image = getImage();
        int hashCode7 = (hashCode6 * 59) + (image == null ? 43 : image.hashCode());
        VideoProperties video = getVideo();
        int hashCode8 = (hashCode7 * 59) + (video == null ? 43 : video.hashCode());
        DocumentProperties document = getDocument();
        int hashCode9 = (hashCode8 * 59) + (document == null ? 43 : document.hashCode());
        String locationId = getLocationId();
        int hashCode10 = (hashCode9 * 59) + (locationId == null ? 43 : locationId.hashCode());
        Address address = getAddress();
        int hashCode11 = (hashCode10 * 59) + (address == null ? 43 : address.hashCode());
        List<ContentSignature> contentSignatures = getContentSignatures();
        int i2 = hashCode11 * 59;
        if (contentSignatures != null) {
            i = contentSignatures.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("contentDate")
    public void setContentDate(ISO8601 iso8601) {
        this.contentDate = iso8601;
    }

    @JsonProperty("contentSignatures")
    public void setContentSignatures(List<ContentSignature> list) {
        this.contentSignatures = list;
    }

    @JsonProperty("contentType")
    public void setContentType(String str) {
        this.contentType = str;
    }

    @JsonProperty("document")
    public void setDocument(DocumentProperties documentProperties) {
        this.document = documentProperties;
    }

    @JsonProperty("extension")
    public void setExtension(String str) {
        this.extension = str;
    }

    @JsonProperty("image")
    public void setImage(ImageProperties imageProperties) {
        this.image = imageProperties;
    }

    @JsonProperty("locationId")
    public void setLocationId(String str) {
        this.locationId = str;
    }

    @JsonProperty(SierraContentProviderContract.MD5_VALUE)
    public void setMd5(String str) {
        this.md5 = str;
    }

    @JsonProperty("size")
    public void setSize(Long l) {
        this.size = l;
    }

    @JsonProperty("version")
    public void setVersion(Long l) {
        this.version = l;
    }

    @JsonProperty("video")
    public void setVideo(VideoProperties videoProperties) {
        this.video = videoProperties;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ContentProperties(version=");
        outline107.append(getVersion());
        outline107.append(", md5=");
        outline107.append(getMd5());
        outline107.append(", size=");
        outline107.append(getSize());
        outline107.append(", contentType=");
        outline107.append(getContentType());
        outline107.append(", extension=");
        outline107.append(getExtension());
        outline107.append(", contentDate=");
        outline107.append(getContentDate());
        outline107.append(", image=");
        outline107.append(getImage());
        outline107.append(", video=");
        outline107.append(getVideo());
        outline107.append(", document=");
        outline107.append(getDocument());
        outline107.append(", locationId=");
        outline107.append(getLocationId());
        outline107.append(", address=");
        outline107.append(getAddress());
        outline107.append(", contentSignatures=");
        outline107.append(getContentSignatures());
        outline107.append(")");
        return outline107.toString();
    }
}
