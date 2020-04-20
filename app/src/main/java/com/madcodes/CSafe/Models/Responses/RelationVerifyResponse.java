package com.madcodes.CSafe.Models.Responses;

public class RelationVerifyResponse {
    String status, message;
    ResultClass result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultClass getResult() {
        return result;
    }

    public void setResult(ResultClass result) {
        this.result = result;
    }

    class ResultClass {
        String id, username, relation1, mobile1, otp1, verify1, relation2, mobile2, otp2, verify2, relation3, mobile3, otp3, verify3, status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRelation1() {
            return relation1;
        }

        public void setRelation1(String relation1) {
            this.relation1 = relation1;
        }

        public String getMobile1() {
            return mobile1;
        }

        public void setMobile1(String mobile1) {
            this.mobile1 = mobile1;
        }

        public String getOtp1() {
            return otp1;
        }

        public void setOtp1(String otp1) {
            this.otp1 = otp1;
        }

        public String getVerify1() {
            return verify1;
        }

        public void setVerify1(String verify1) {
            this.verify1 = verify1;
        }

        public String getRelation2() {
            return relation2;
        }

        public void setRelation2(String relation2) {
            this.relation2 = relation2;
        }

        public String getMobile2() {
            return mobile2;
        }

        public void setMobile2(String mobile2) {
            this.mobile2 = mobile2;
        }

        public String getOtp2() {
            return otp2;
        }

        public void setOtp2(String otp2) {
            this.otp2 = otp2;
        }

        public String getVerify2() {
            return verify2;
        }

        public void setVerify2(String verify2) {
            this.verify2 = verify2;
        }

        public String getRelation3() {
            return relation3;
        }

        public void setRelation3(String relation3) {
            this.relation3 = relation3;
        }

        public String getMobile3() {
            return mobile3;
        }

        public void setMobile3(String mobile3) {
            this.mobile3 = mobile3;
        }

        public String getOtp3() {
            return otp3;
        }

        public void setOtp3(String otp3) {
            this.otp3 = otp3;
        }

        public String getVerify3() {
            return verify3;
        }

        public void setVerify3(String verify3) {
            this.verify3 = verify3;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

}
