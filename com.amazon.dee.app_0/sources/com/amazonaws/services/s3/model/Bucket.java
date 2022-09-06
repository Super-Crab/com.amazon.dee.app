package com.amazonaws.services.s3.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class Bucket implements Serializable {
    private static final long serialVersionUID = -8646831898339939580L;
    private Date creationDate;
    private String name;
    private Owner owner;

    public Bucket() {
        this.name = null;
        this.owner = null;
        this.creationDate = null;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getName() {
        return this.name;
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("S3Bucket [name=");
        outline107.append(getName());
        outline107.append(", creationDate=");
        outline107.append(getCreationDate());
        outline107.append(", owner=");
        outline107.append(getOwner());
        outline107.append("]");
        return outline107.toString();
    }

    public Bucket(String str) {
        this.name = null;
        this.owner = null;
        this.creationDate = null;
        this.name = str;
    }
}
