package com.bm.container.module.personal.bean;

/**
 * Created by nfmomo on 17/4/17.
 */

public class UserInfoBean {

    /**
     * currentPage :
     * data : {"accountName":"","accountSource":"","address":"","appUserType":0,"appUserTypeName":"个人","bankAccount":"","cities":"","citiesName":"","city":1,"cityName":"上海","companyAddress":"","companyName":"","companyPerson":"","companyTelephone":"","contact":"15962099596","country":1,"countryName":"中国","createTime":"2017-04-11 17:44:29","createUser":"0a846e77-c42a-4bfe-b4e1-fa6484857ad5","id":"0a846e77-c42a-4bfe-b4e1-fa6484857ad5","imageUrl":"","isVerify":1,"lawyerPerson":"","password":"96e79218965eb72c92a549dd5a330112","qq":"779674059","telephone":"15962099596","updateTime":"","updateUser":"","userFlag":0,"userFlagName":"买家","userId":"SHRU1SB","userName":"王文舜","weixin":"nfmomo"}
     * error :
     * msg : 操作成功
     * pageCount :
     * pageData :
     * pageSize :
     * recordsTotal :
     * status : 1
     */

    private String currentPage;
    private DataBean data;
    private String error;
    private String msg;
    private String pageCount;
    private String pageData;
    private String pageSize;
    private String recordsTotal;
    private String status;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getPageData() {
        return pageData;
    }

    public void setPageData(String pageData) {
        this.pageData = pageData;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(String recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * accountName :
         * accountSource :
         * address :
         * appUserType : 0
         * appUserTypeName : 个人
         * bankAccount :
         * cities :
         * citiesName :
         * city : 1
         * cityName : 上海
         * companyAddress :
         * companyName :
         * companyPerson :
         * companyTelephone :
         * contact : 15962099596
         * country : 1
         * countryName : 中国
         * createTime : 2017-04-11 17:44:29
         * createUser : 0a846e77-c42a-4bfe-b4e1-fa6484857ad5
         * id : 0a846e77-c42a-4bfe-b4e1-fa6484857ad5
         * imageUrl :
         * isVerify : 1
         * lawyerPerson :
         * password : 96e79218965eb72c92a549dd5a330112
         * qq : 779674059
         * telephone : 15962099596
         * updateTime :
         * updateUser :
         * userFlag : 0
         * userFlagName : 买家
         * userId : SHRU1SB
         * userName : 王文舜
         * weixin : nfmomo
         */

        private String accountName;
        private String accountSource;
        private String address;
        private String appUserType;
        private String appUserTypeName;
        private String bankAccount;
        private String cities;
        private String citiesName;
        private String city;
        private String cityName;
        private String companyAddress;
        private String companyName;
        private String companyPerson;
        private String companyTelephone;
        private String contact;
        private String country;
        private String countryName;
        private String createTime;
        private String createUser;
        private String id;
        private String imageUrl;
        private String isVerify;
        private String lawyerPerson;
        private String password;
        private String qq;
        private String telephone;
        private String updateTime;
        private String updateUser;
        private String userFlag;
        private String userFlagName;
        private String userId;
        private String userName;
        private String weixin;
        private String isBan;
        private String isSysRed;

        public String getIsSysRed() {
            return isSysRed;
        }

        public void setIsSysRed(String isSysRed) {
            this.isSysRed = isSysRed;
        }

        public String getIsBan() {
            return isBan;
        }

        public void setIsBan(String isBan) {
            this.isBan = isBan;
        }


        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getAccountSource() {
            return accountSource;
        }

        public void setAccountSource(String accountSource) {
            this.accountSource = accountSource;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAppUserType() {
            return appUserType;
        }

        public void setAppUserType(String appUserType) {
            this.appUserType = appUserType;
        }

        public String getAppUserTypeName() {
            return appUserTypeName;
        }

        public void setAppUserTypeName(String appUserTypeName) {
            this.appUserTypeName = appUserTypeName;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public String getCities() {
            return cities;
        }

        public void setCities(String cities) {
            this.cities = cities;
        }

        public String getCitiesName() {
            return citiesName;
        }

        public void setCitiesName(String citiesName) {
            this.citiesName = citiesName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyPerson() {
            return companyPerson;
        }

        public void setCompanyPerson(String companyPerson) {
            this.companyPerson = companyPerson;
        }

        public String getCompanyTelephone() {
            return companyTelephone;
        }

        public void setCompanyTelephone(String companyTelephone) {
            this.companyTelephone = companyTelephone;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getIsVerify() {
            return isVerify;
        }

        public void setIsVerify(String isVerify) {
            this.isVerify = isVerify;
        }

        public String getLawyerPerson() {
            return lawyerPerson;
        }

        public void setLawyerPerson(String lawyerPerson) {
            this.lawyerPerson = lawyerPerson;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(String updateUser) {
            this.updateUser = updateUser;
        }

        public String getUserFlag() {
            return userFlag;
        }

        public void setUserFlag(String userFlag) {
            this.userFlag = userFlag;
        }

        public String getUserFlagName() {
            return userFlagName;
        }

        public void setUserFlagName(String userFlagName) {
            this.userFlagName = userFlagName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }
    }
}
