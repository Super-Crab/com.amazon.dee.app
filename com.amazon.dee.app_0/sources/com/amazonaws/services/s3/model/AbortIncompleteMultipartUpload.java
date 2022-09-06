package com.amazonaws.services.s3.model;

import java.io.Serializable;
/* loaded from: classes13.dex */
public class AbortIncompleteMultipartUpload implements Serializable {
    private int daysAfterInitiation;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && AbortIncompleteMultipartUpload.class == obj.getClass() && this.daysAfterInitiation == ((AbortIncompleteMultipartUpload) obj).daysAfterInitiation;
    }

    public int getDaysAfterInitiation() {
        return this.daysAfterInitiation;
    }

    public int hashCode() {
        return this.daysAfterInitiation;
    }

    public void setDaysAfterInitiation(int i) {
        this.daysAfterInitiation = i;
    }

    public AbortIncompleteMultipartUpload withDaysAfterInitiation(int i) {
        setDaysAfterInitiation(i);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: clone */
    public AbortIncompleteMultipartUpload m6717clone() throws CloneNotSupportedException {
        try {
            return (AbortIncompleteMultipartUpload) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Got a CloneNotSupportedException from Object.clone() even though we're Cloneable!", e);
        }
    }
}
