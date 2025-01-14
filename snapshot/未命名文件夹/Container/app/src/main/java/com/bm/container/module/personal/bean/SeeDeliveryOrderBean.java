package com.bm.container.module.personal.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by kec on 2017/6/23.
 */

public class SeeDeliveryOrderBean implements Parcelable {


    /**
     * currentPage :
     * data : {"containerHeapNo":"hghh","containerNo":"fghj,","containerType":2,"containerTypeName":"20HC","count":88,"createTime":"2017-06-26 18:18:46","createUser":"c2832cd1-65b6-4949-a19e-d7cd04620107","deliveryDate":"vdfh","deliveryDetailList":[{"companyEMail":"bdbdnejme.@qq.commnfjjdkdmdm","companyName":"33333333","contactTelephone":"6464649949494","count":6466464,"createTime":"2017-06-27 13:43:26","createUser":"","deliveryDate":"in移民","deliveryNoteId":"ed509b79-8743-4b8a-8314-3eba828ea893","id":"13fa4509-75f1-4baa-8e58-0465acd80885","motorcadeName":"民意why","orderNo":"FX201706261817000037","plateNo":"why嘻嘻嘻","updateTime":"","updateUser":""},{"companyEMail":"ncbndnsn9196494949.msms.com","companyName":"1111111","contactTelephone":"64665646466464","count":694949,"createTime":"2017-06-27 13:43:26","createUser":"","deliveryDate":"哦咯牙看有空","deliveryNoteId":"ed509b79-8743-4b8a-8314-3eba828ea893","id":"82bdcbbc-ecbb-4a6c-8f75-f12d485f8082","motorcadeName":"你明明哦哦","orderNo":"FX201706261817000037","plateNo":"我一下咯心咯做快我","updateTime":"","updateUser":""},{"companyEMail":"ndnndnmejdke.n@jdjdjdk163.com","companyName":"222222","contactTelephone":"946499494","count":46646465,"createTime":"2017-06-27 13:43:26","createUser":"","deliveryDate":"你一中","deliveryNoteId":"ed509b79-8743-4b8a-8314-3eba828ea893","id":"8afd7aef-38bc-430e-9f0d-7c2ada33a25f","motorcadeName":"哦了也快也赖我咯","orderNo":"FX201706261817000037","plateNo":"哦哟便宜一些月","updateTime":"","updateUser":""}],"heapAddress":"vfgnnmnff","heapContact":"vgjjj","heapEMail":"hgbggg","heapName":"ggjjj","heapTelephone":"5559996","id":"ed509b79-8743-4b8a-8314-3eba828ea893","imageUrl":"","isSpecialPrice":1,"orderNo":"FX201706261817000037","qRCodeUrl":"/usr/local/tomcat/webapps/container//upload//201762713432671.jpg","specialPrice":666666,"updateTime":"","updateUser":"","userId":""}
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

    protected SeeDeliveryOrderBean(Parcel in) {
        currentPage = in.readString();
        data = in.readParcelable(DataBean.class.getClassLoader());
        error = in.readString();
        msg = in.readString();
        pageCount = in.readString();
        pageData = in.readString();
        pageSize = in.readString();
        recordsTotal = in.readString();
        status = in.readString();
    }

    public static final Creator<SeeDeliveryOrderBean> CREATOR = new Creator<SeeDeliveryOrderBean>() {
        @Override
        public SeeDeliveryOrderBean createFromParcel(Parcel in) {
            return new SeeDeliveryOrderBean(in);
        }

        @Override
        public SeeDeliveryOrderBean[] newArray(int size) {
            return new SeeDeliveryOrderBean[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(currentPage);
        dest.writeParcelable(data, flags);
        dest.writeString(error);
        dest.writeString(msg);
        dest.writeString(pageCount);
        dest.writeString(pageData);
        dest.writeString(pageSize);
        dest.writeString(recordsTotal);
        dest.writeString(status);
    }

    public static class DataBean implements Parcelable {
        /**
         * containerHeapNo : hghh
         * containerNo : fghj,
         * containerType : 2
         * containerTypeName : 20HC
         * count : 88
         * createTime : 2017-06-26 18:18:46
         * createUser : c2832cd1-65b6-4949-a19e-d7cd04620107
         * deliveryDate : vdfh
         * deliveryDetailList : [{"companyEMail":"bdbdnejme.@qq.commnfjjdkdmdm","companyName":"33333333","contactTelephone":"6464649949494","count":6466464,"createTime":"2017-06-27 13:43:26","createUser":"","deliveryDate":"in移民","deliveryNoteId":"ed509b79-8743-4b8a-8314-3eba828ea893","id":"13fa4509-75f1-4baa-8e58-0465acd80885","motorcadeName":"民意why","orderNo":"FX201706261817000037","plateNo":"why嘻嘻嘻","updateTime":"","updateUser":""},{"companyEMail":"ncbndnsn9196494949.msms.com","companyName":"1111111","contactTelephone":"64665646466464","count":694949,"createTime":"2017-06-27 13:43:26","createUser":"","deliveryDate":"哦咯牙看有空","deliveryNoteId":"ed509b79-8743-4b8a-8314-3eba828ea893","id":"82bdcbbc-ecbb-4a6c-8f75-f12d485f8082","motorcadeName":"你明明哦哦","orderNo":"FX201706261817000037","plateNo":"我一下咯心咯做快我","updateTime":"","updateUser":""},{"companyEMail":"ndnndnmejdke.n@jdjdjdk163.com","companyName":"222222","contactTelephone":"946499494","count":46646465,"createTime":"2017-06-27 13:43:26","createUser":"","deliveryDate":"你一中","deliveryNoteId":"ed509b79-8743-4b8a-8314-3eba828ea893","id":"8afd7aef-38bc-430e-9f0d-7c2ada33a25f","motorcadeName":"哦了也快也赖我咯","orderNo":"FX201706261817000037","plateNo":"哦哟便宜一些月","updateTime":"","updateUser":""}]
         * heapAddress : vfgnnmnff
         * heapContact : vgjjj
         * heapEMail : hgbggg
         * heapName : ggjjj
         * heapTelephone : 5559996
         * id : ed509b79-8743-4b8a-8314-3eba828ea893
         * imageUrl :
         * isSpecialPrice : 1
         * orderNo : FX201706261817000037
         * qRCodeUrl : /usr/local/tomcat/webapps/container//upload//201762713432671.jpg
         * specialPrice : 666666.0
         * updateTime :
         * updateUser :
         * userId :
         */

        private String containerHeapNo;
        private String containerNo;
        private String containerType;
        private String containerTypeName;
        private String count;
        private String createTime;
        private String createUser;
        private String deliveryDate;
        private String heapAddress;
        private String heapContact;
        private String heapEMail;
        private String heapName;
        private String heapTelephone;
        private String id;
        private String imageUrl;
        private String isSpecialPrice;
        private String orderNo;
        private String qRCodeUrl;
        private String specialPrice;
        private String updateTime;
        private String updateUser;
        private String userId;
        private List<DeliveryDetailListBean> deliveryDetailList;

        protected DataBean(Parcel in) {
            containerHeapNo = in.readString();
            containerNo = in.readString();
            containerType = in.readString();
            containerTypeName = in.readString();
            count = in.readString();
            createTime = in.readString();
            createUser = in.readString();
            deliveryDate = in.readString();
            heapAddress = in.readString();
            heapContact = in.readString();
            heapEMail = in.readString();
            heapName = in.readString();
            heapTelephone = in.readString();
            id = in.readString();
            imageUrl = in.readString();
            isSpecialPrice = in.readString();
            orderNo = in.readString();
            qRCodeUrl = in.readString();
            specialPrice = in.readString();
            updateTime = in.readString();
            updateUser = in.readString();
            userId = in.readString();
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

        public String getContainerHeapNo() {
            return containerHeapNo;
        }

        public void setContainerHeapNo(String containerHeapNo) {
            this.containerHeapNo = containerHeapNo;
        }

        public String getContainerNo() {
            return containerNo;
        }

        public void setContainerNo(String containerNo) {
            this.containerNo = containerNo;
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

        public String getDeliveryDate() {
            return deliveryDate;
        }

        public void setDeliveryDate(String deliveryDate) {
            this.deliveryDate = deliveryDate;
        }

        public String getHeapAddress() {
            return heapAddress;
        }

        public void setHeapAddress(String heapAddress) {
            this.heapAddress = heapAddress;
        }

        public String getHeapContact() {
            return heapContact;
        }

        public void setHeapContact(String heapContact) {
            this.heapContact = heapContact;
        }

        public String getHeapEMail() {
            return heapEMail;
        }

        public void setHeapEMail(String heapEMail) {
            this.heapEMail = heapEMail;
        }

        public String getHeapName() {
            return heapName;
        }

        public void setHeapName(String heapName) {
            this.heapName = heapName;
        }

        public String getHeapTelephone() {
            return heapTelephone;
        }

        public void setHeapTelephone(String heapTelephone) {
            this.heapTelephone = heapTelephone;
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

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getQRCodeUrl() {
            return qRCodeUrl;
        }

        public void setQRCodeUrl(String qRCodeUrl) {
            this.qRCodeUrl = qRCodeUrl;
        }

        public String getSpecialPrice() {
            return specialPrice;
        }

        public void setSpecialPrice(String specialPrice) {
            this.specialPrice = specialPrice;
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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<DeliveryDetailListBean> getDeliveryDetailList() {
            return deliveryDetailList;
        }

        public void setDeliveryDetailList(List<DeliveryDetailListBean> deliveryDetailList) {
            this.deliveryDetailList = deliveryDetailList;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(containerHeapNo);
            dest.writeString(containerNo);
            dest.writeString(containerType);
            dest.writeString(containerTypeName);
            dest.writeString(count);
            dest.writeString(createTime);
            dest.writeString(createUser);
            dest.writeString(deliveryDate);
            dest.writeString(heapAddress);
            dest.writeString(heapContact);
            dest.writeString(heapEMail);
            dest.writeString(heapName);
            dest.writeString(heapTelephone);
            dest.writeString(id);
            dest.writeString(imageUrl);
            dest.writeString(isSpecialPrice);
            dest.writeString(orderNo);
            dest.writeString(qRCodeUrl);
            dest.writeString(specialPrice);
            dest.writeString(updateTime);
            dest.writeString(updateUser);
            dest.writeString(userId);
        }

        public static class DeliveryDetailListBean {
            /**
             * companyEMail : bdbdnejme.@qq.commnfjjdkdmdm
             * companyName : 33333333
             * contactTelephone : 6464649949494
             * count : 6466464
             * createTime : 2017-06-27 13:43:26
             * createUser :
             * deliveryDate : in移民
             * deliveryNoteId : ed509b79-8743-4b8a-8314-3eba828ea893
             * id : 13fa4509-75f1-4baa-8e58-0465acd80885
             * motorcadeName : 民意why
             * orderNo : FX201706261817000037
             * plateNo : why嘻嘻嘻
             * updateTime :
             * updateUser :
             */

            private String companyEMail;
            private String companyName;
            private String contactTelephone;
            private String count;
            private String createTime;
            private String createUser;
            private String deliveryDate;
            private String deliveryNoteId;
            private String id;
            private String motorcadeName;
            private String orderNo;
            private String plateNo;
            private String updateTime;
            private String updateUser;

            public String getCompanyEMail() {
                return companyEMail;
            }

            public void setCompanyEMail(String companyEMail) {
                this.companyEMail = companyEMail;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getContactTelephone() {
                return contactTelephone;
            }

            public void setContactTelephone(String contactTelephone) {
                this.contactTelephone = contactTelephone;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
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

            public String getDeliveryDate() {
                return deliveryDate;
            }

            public void setDeliveryDate(String deliveryDate) {
                this.deliveryDate = deliveryDate;
            }

            public String getDeliveryNoteId() {
                return deliveryNoteId;
            }

            public void setDeliveryNoteId(String deliveryNoteId) {
                this.deliveryNoteId = deliveryNoteId;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMotorcadeName() {
                return motorcadeName;
            }

            public void setMotorcadeName(String motorcadeName) {
                this.motorcadeName = motorcadeName;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getPlateNo() {
                return plateNo;
            }

            public void setPlateNo(String plateNo) {
                this.plateNo = plateNo;
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
        }
    }
}
