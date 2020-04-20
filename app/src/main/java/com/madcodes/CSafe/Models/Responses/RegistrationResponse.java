package com.madcodes.CSafe.Models.Responses;

public class RegistrationResponse {

    String status, user_id, otp, message;
    UserDetails user_details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getMessage() {
        return message;
    }

    public UserDetails getUser_details() {
        return user_details;
    }

    public void setUser_details(UserDetails user_details) {
        this.user_details = user_details;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class UserDetails {
        User user;
        UserRelation userrelation;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public UserRelation getUserrelation() {
            return userrelation;
        }

        public void setUserrelation(UserRelation userrelation) {
            this.userrelation = userrelation;
        }

        public class User {

            String id;
            String username;
            String mobile;
            String email;
            String otp_no;
            String lat;
            String lng;
            String age;
            String gender;
            String address1;
            String address2;
            String city;
            String status;

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

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getOtp_no() {
                return otp_no;
            }

            public void setOtp_no(String otp_no) {
                this.otp_no = otp_no;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getAddress1() {
                return address1;
            }

            public void setAddress1(String address1) {
                this.address1 = address1;
            }

            public String getAddress2() {
                return address2;
            }

            public void setAddress2(String address2) {
                this.address2 = address2;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

        public static class UserRelation {


            String id;
            String username;

            String relation1;
            String mobile1;
            String otp1;
            String verify1;

            String relation2;
            String mobile2;
            String otp2;
            String verify2;

            String relation3;
            String mobile3;
            String otp3;
            String verify3;

            String status;



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
}
