package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CertificateDescription;
import com.amazonaws.services.iot.model.CertificateValidity;
import com.amazonaws.services.iot.model.TransferData;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
/* loaded from: classes13.dex */
class CertificateDescriptionJsonMarshaller {
    private static CertificateDescriptionJsonMarshaller instance;

    CertificateDescriptionJsonMarshaller() {
    }

    public static CertificateDescriptionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CertificateDescriptionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(CertificateDescription certificateDescription, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (certificateDescription.getCertificateArn() != null) {
            String certificateArn = certificateDescription.getCertificateArn();
            awsJsonWriter.name("certificateArn");
            awsJsonWriter.value(certificateArn);
        }
        if (certificateDescription.getCertificateId() != null) {
            String certificateId = certificateDescription.getCertificateId();
            awsJsonWriter.name("certificateId");
            awsJsonWriter.value(certificateId);
        }
        if (certificateDescription.getCaCertificateId() != null) {
            String caCertificateId = certificateDescription.getCaCertificateId();
            awsJsonWriter.name("caCertificateId");
            awsJsonWriter.value(caCertificateId);
        }
        if (certificateDescription.getStatus() != null) {
            String status = certificateDescription.getStatus();
            awsJsonWriter.name("status");
            awsJsonWriter.value(status);
        }
        if (certificateDescription.getCertificatePem() != null) {
            String certificatePem = certificateDescription.getCertificatePem();
            awsJsonWriter.name("certificatePem");
            awsJsonWriter.value(certificatePem);
        }
        if (certificateDescription.getOwnedBy() != null) {
            String ownedBy = certificateDescription.getOwnedBy();
            awsJsonWriter.name("ownedBy");
            awsJsonWriter.value(ownedBy);
        }
        if (certificateDescription.getPreviousOwnedBy() != null) {
            String previousOwnedBy = certificateDescription.getPreviousOwnedBy();
            awsJsonWriter.name("previousOwnedBy");
            awsJsonWriter.value(previousOwnedBy);
        }
        if (certificateDescription.getCreationDate() != null) {
            Date creationDate = certificateDescription.getCreationDate();
            awsJsonWriter.name("creationDate");
            awsJsonWriter.value(creationDate);
        }
        if (certificateDescription.getLastModifiedDate() != null) {
            Date lastModifiedDate = certificateDescription.getLastModifiedDate();
            awsJsonWriter.name("lastModifiedDate");
            awsJsonWriter.value(lastModifiedDate);
        }
        if (certificateDescription.getCustomerVersion() != null) {
            Integer customerVersion = certificateDescription.getCustomerVersion();
            awsJsonWriter.name("customerVersion");
            awsJsonWriter.value(customerVersion);
        }
        if (certificateDescription.getTransferData() != null) {
            TransferData transferData = certificateDescription.getTransferData();
            awsJsonWriter.name("transferData");
            TransferDataJsonMarshaller.getInstance().marshall(transferData, awsJsonWriter);
        }
        if (certificateDescription.getGenerationId() != null) {
            String generationId = certificateDescription.getGenerationId();
            awsJsonWriter.name("generationId");
            awsJsonWriter.value(generationId);
        }
        if (certificateDescription.getValidity() != null) {
            CertificateValidity validity = certificateDescription.getValidity();
            awsJsonWriter.name("validity");
            CertificateValidityJsonMarshaller.getInstance().marshall(validity, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
