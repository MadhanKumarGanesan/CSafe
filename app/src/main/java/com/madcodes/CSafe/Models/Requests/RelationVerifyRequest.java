package com.madcodes.CSafe.Models.Requests;

public class RelationVerifyRequest {

    private String mobile,relation_id,relation_mobile,otp;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(String relation_id) {
        this.relation_id = relation_id;
    }

    public String getRelation_mobile() {
        return relation_mobile;
    }

    public void setRelation_mobile(String relation_mobile) {
        this.relation_mobile = relation_mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
