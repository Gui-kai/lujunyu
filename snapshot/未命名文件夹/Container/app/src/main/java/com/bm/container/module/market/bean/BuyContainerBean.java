package com.bm.container.module.market.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kec on 2017/5/26.
 */

public class BuyContainerBean {


    /**
     * currentPage :
     * data : {"billAccountSource":"","billAddress":"","billBankAccount":"","billCheque":"","billContent":"","billNo":"","billTelephone":"","billType":"","billTypeName":"","city":2,"cityName":"北京","containerStatus":1,"containerStatusName":"投标中(买箱)","containerType":2,"containerTypeName":"20HC","count":99,"country":1,"countryName":"中国","createTime":"2017-07-13 13:15:36","createUser":"c64bead6-fe6f-41c4-b4ee-d707bff705ef","deadLineTime":"2017-07-21 02:35:40","id":"cd7debf3-199b-4fc8-b815-7ac952de5a12","imageUrl":"","isSpecialPrice":"","isSupportBill":"","isTop":0,"price":"","remark":"","scanCount":6,"specialPrice":"","specialRemark":"默默wwwYY","startPrice":"","statusType":5,"statusTypeName":"全新","timeType":9,"timeTypeName":"一周","title":"买家  买箱","tradeType":"","tradeTypeName":"","type":0,"updateTime":"","updateUser":""}
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

    public static class DataBean implements Parcelable {
        /**
         * billAccountSource :
         * billAddress :
         * billBankAccount :
         * billCheque :
         * billContent :
         * billNo :
         * billTelephone :
         * billType :
         * billTypeName :
         * city : 2
         * cityName : 北京
         * containerStatus : 1
         * containerStatusName : 投标中(买箱)
         * containerType : 2
         * containerTypeName : 20HC
         * count : 99
         * country : 1
         * countryName : 中国
         * createTime : 2017-07-13 13:15:36
         * createUser : c64bead6-fe6f-41c4-b4ee-d707bff705ef
         * deadLineTime : 2017-07-21 02:35:40
         * id : cd7debf3-199b-4fc8-b815-7ac952de5a12
         * imageUrl :
         * isSpecialPrice :
         * isSupportBill :
         * isTop : 0
         * price :
         * remark :
         * scanCount : 6
         * specialPrice :
         * specialRemark : 默默wwwYY
         * startPrice :
         * statusType : 5
         * statusTypeName : 全新
         * timeType : 9
         * timeTypeName : 一周
         * title : 买家  买箱
         * tradeType :
         * tradeTypeName :
         * type : 0
         * updateTime :
         * updateUser :
         */

        private String billAccountSource;
        private String billAddress;
        private String billBankAccount;
        private String billCheque;
        private String billContent;
        private String billNo;
        private String billTelephone;
        private String billType;
        private String billTypeName;
        private String city;
        private String cityName;
        private String containerStatus;
        private String containerStatusName;
        private String containerType;
        private String containerTypeName;
        private String count;
        private String country;
        private String countryName;
        private String createTime;
        private String createUser;
        private String deadLineTime;
        private String id;
        private String imageUrl;
        private String isSpecialPrice;
        private String isSupportBill;
        private String isTop;
        private String price;
        private String remark;
        private String scanCount;
        private String specialPrice;
        private String specialRemark;
        private String startPrice;
        private String statusType;
        private String statusTypeName;
        private String timeType;
        private String timeTypeName;
        private String title;
        private String tradeType;
        private String tradeTypeName;
        private String type;
        private String updateTime;
        private String updateUser;

        public String getAppUserType() {
            return appUserType;
        }

        public void setAppUserType(String appUserType) {
            this.appUserType = appUserType;
        }

        private String appUserType;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        private String age;

        protected DataBean(Parcel in) {
            billAccountSource = in.readString();
            billAddress = in.readString();
            billBankAccount = in.readString();
            billCheque = in.readString();
            billContent = in.readString();
            billNo = in.readString();
            billTelephone = in.readString();
            billType = in.readString();
            billTypeName = in.readString();
            city = in.readString();
            cityName = in.readString();
            containerStatus = in.readString();
            containerStatusName = in.readString();
            containerType = in.readString();
            containerTypeName = in.readString();
            count = in.readString();
            country = in.readString();
            countryName = in.readString();
            createTime = in.readString();
            createUser = in.readString();
            deadLineTime = in.readString();
            id = in.readString();
            imageUrl = in.readString();
            isSpecialPrice = in.readString();
            isSupportBill = in.readString();
            isTop = in.readString();
            price = in.readString();
            remark = in.readString();
            scanCount = in.readString();
            specialPrice = in.readString();
            specialRemark = in.readString();
            startPrice = in.readString();
            statusType = in.readString();
            statusTypeName = in.readString();
            timeType = in.readString();
            timeTypeName = in.readString();
            title = in.readString();
            tradeType = in.readString();
            tradeTypeName = in.readString();
            type = in.readString();
            updateTime = in.readString();
            updateUser = in.readString();
            appUserType = in.readString();
            age = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public String getBillAccountSource() {
            return billAccountSource;
        }

        public void setBillAccountSource(String billAccountSource) {
            this.billAccountSource = billAccountSource;
        }

        public String getBillAddress() {
            return billAddress;
        }

        public void setBillAddress(String billAddress) {
            this.billAddress = billAddress;
        }

        public String getBillBankAccount() {
            return billBankAccount;
        }

        public void setBillBankAccount(String billBankAccount) {
            this.billBankAccount = billBankAccount;
        }

        public String getBillCheque() {
            return billCheque;
        }

        public void setBillCheque(String billCheque) {
            this.billCheque = billCheque;
        }

        public String getBillContent() {
            return billContent;
        }

        public void setBillContent(String billContent) {
            this.billContent = billContent;
        }

        public String getBillNo() {
            return billNo;
        }

        public void setBillNo(String billNo) {
            this.billNo = billNo;
        }

        public String getBillTelephone() {
            return billTelephone;
        }

        public void setBillTelephone(String billTelephone) {
            this.billTelephone = billTelephone;
        }

        public String getBillType() {
            return billType;
        }

        public void setBillType(String billType) {
            this.billType = billType;
        }

        public String getBillTypeName() {
            return billTypeName;
        }

        public void setBillTypeName(String billTypeName) {
            this.billTypeName = billTypeName;
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

        public String getContainerStatus() {
            return containerStatus;
        }

        public void setContainerStatus(String containerStatus) {
            this.containerStatus = containerStatus;
        }

        public String getContainerStatusName() {
            return containerStatusName;
        }

        public void setContainerStatusName(String containerStatusName) {
            this.containerStatusName = containerStatusName;
        }

        public String getContainerType() {
            return containerType;
        }

        public void setContainerType(String containerType) {
            this.containerType = containerType;
        }

        public String getContainerTypeName() {
            return containerTypeName;
        }

        public void setContainerTypeName(String containerTypeName) {
            this.containerTypeName = containerTypeName;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
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

        public String getDeadLineTime() {
            return deadLineTime;
        }

        public void setDeadLineTime(String deadLineTime) {
            this.deadLineTime = deadLineTime;
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

        public String getIsSpecialPrice() {
            return isSpecialPrice;
        }

        public void setIsSpecialPrice(String isSpecialPrice) {
            this.isSpecialPrice = isSpecialPrice;
        }

        public String getIsSupportBill() {
            return isSupportBill;
        }

        public void setIsSupportBill(String isSupportBill) {
            this.isSupportBill = isSupportBill;
        }

        public String getIsTop() {
            return isTop;
        }

        public void setIsTop(String isTop) {
            this.isTop = isTop;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getScanCount() {
            return scanCount;
        }

        public void setScanCount(String scanCount) {
            this.scanCount = scanCount;
        }

        public String getSpecialPrice() {
            return specialPrice;
        }

        public void setSpecialPrice(String specialPrice) {
            this.specialPrice = specialPrice;
        }

        public String getSpecialRemark() {
            return specialRemark;
        }

        public void setSpecialRemark(String specialRemark) {
            this.specialRemark = specialRemark;
        }

        public String getStartPrice() {
            return startPrice;
        }

        public void setStartPrice(String startPrice) {
            this.startPrice = startPrice;
        }

        public String getStatusType() {
            return statusType;
        }

        public void setStatusType(String statusType) {
            this.statusType = statusType;
        }

        public String getStatusTypeName() {
            return statusTypeName;
        }

        public void setStatusTypeName(String statusTypeName) {
            this.statusTypeName = statusTypeName;
        }

        public String getTimeType() {
            return timeType;
        }

        public void setTimeType(String timeType) {
            this.timeType = timeType;
        }

        public String getTimeTypeName() {
            return timeTypeName;
        }

        public void setTimeTypeName(String timeTypeName) {
            this.timeTypeName = timeTypeName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTradeType() {
            return tradeType;
        }

        public void setTradeType(String tradeType) {
            this.tradeType = tradeType;
        }

        public String getTradeTypeName() {
            return tradeTypeName;
        }

        public void setTradeTypeName(String tradeTypeName) {
            this.tradeTypeName = tradeTypeName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(billAccountSource);
            dest.writeString(billAddress);
            dest.writeString(billBankAccount);
            dest.writeString(billCheque);
            dest.writeString(billContent);
            dest.writeString(billNo);
            dest.writeString(billTelephone);
            dest.writeString(billType);
            dest.writeString(billTypeName);
            dest.writeString(city);
            dest.writeString(cityName);
            dest.writeString(containerStatus);
            dest.writeString(containerStatusName);
            dest.writeString(containerType);
            dest.writeString(containerTypeName);
            dest.writeString(count);
            dest.writeString(country);
            dest.writeString(countryName);
            dest.writeString(createTime);
            dest.writeString(createUser);
            dest.writeString(deadLineTime);
            dest.writeString(id);
            dest.writeString(imageUrl);
            dest.writeString(isSpecialPrice);
            dest.writeString(isSupportBill);
            dest.writeString(isTop);
            dest.writeString(price);
            dest.writeString(remark);
            dest.writeString(scanCount);
            dest.writeString(specialPrice);
            dest.writeString(specialRemark);
            dest.writeString(startPrice);
            dest.writeString(statusType);
            dest.writeString(statusTypeName);
            dest.writeString(timeType);
            dest.writeString(timeTypeName);
            dest.writeString(title);
            dest.writeString(tradeType);
            dest.writeString(tradeTypeName);
            dest.writeString(type);
            dest.writeString(updateTime);
            dest.writeString(updateUser);
            dest.writeString(appUserType);
            dest.writeString(age);
        }
    }
}
