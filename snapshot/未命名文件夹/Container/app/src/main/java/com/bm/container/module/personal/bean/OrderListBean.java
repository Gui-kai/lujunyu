package com.bm.container.module.personal.bean;

import java.util.List;

/**
 * Created by nfmomo on 17/5/8.
 */

public class OrderListBean {

    /**
     * currentPage : 1
     * data : [{"bidOrder":"","bidOrderId":"07710017-a42c-4a94-930d-32fd6ce23695","buyOrder":"","buyOrderId":"","container":{"billCheque":"","billContent":"","billTelephone":"","billType":"","billTypeName":"","city":1,"cityName":"上海","containerStatus":1,"containerStatusName":"正在进行中","containerType":1,"containerTypeName":"20英尺X8英尺X8英尺6寸（20尺货柜）","count":2,"country":1,"countryName":"中国","createTime":"2017-04-24 17:41:20","createUser":"4e9f8a6b-cb8a-4a9a-b1eb-e86757d15d97","deadLineTime":"2017-05-30 18:41:22","id":"db502423-45f7-453a-b881-5be40440bd89","imageUrl":"","isSpecialPrice":"","isSupportBill":"","isTop":0,"price":"","scanCount":0,"specialPrice":"","specialRemark":"no","statusType":4,"statusTypeName":"全新","timeType":1,"timeTypeName":"1小时","tradeType":"","tradeTypeName":"","type":0,"updateTime":"","updateUser":""},"containerId":"db502423-45f7-453a-b881-5be40440bd89","containerNo":"","count":2,"createTime":"2017-04-25 11:25:50","createUser":"4e9f8a6b-cb8a-4a9a-b1eb-e86757d15d97","createUserInfo":"","id":"9b4ed398-2dd8-44fc-be87-25ed582582fd","orderNo":"FX201704251125000057","orderStatusType":5,"orderStatusTypeName":"已取消","paidUser":"0a846e77-c42a-4bfe-b4e1-fa6484857ad5","paidUserInfo":{"accountName":"nfmomo","accountSource":"建设银行","address":"虹口水电路","appUserType":0,"appUserTypeName":"个人","bankAccount":"5665774555668874556","cities":"","citiesName":"","city":1,"cityName":"上海","companyAddress":"","companyName":"","companyPerson":"","companyTelephone":"","contact":"15962099596","country":1,"countryName":"中国","createTime":"2017-04-11 17:44:29","createUser":"0a846e77-c42a-4bfe-b4e1-fa6484857ad5","id":"0a846e77-c42a-4bfe-b4e1-fa6484857ad5","imageUrl":"/upload/20170427/Luban_1493283795820_20170427050306.jpg","isBan":0,"isReceiveMessage":1,"isVerify":1,"lawyerPerson":"","password":"96e79218965eb72c92a549dd5a330112","qq":"779674059","telephone":"15962099596","updateTime":"","updateUser":"","userFlag":0,"userFlagName":"买家","userId":"SHRU1SD","userName":"王文舜","weixin":"nfmomo"},"payType":1,"payTypeName":"微信","price":116,"receiveTime":"","remark":"","transcationId":"","updateTime":"","updateUser":""}]
     * error :
     * msg : 操作成功
     * pageCount : 1
     * pageData :
     * pageSize : 10
     * recordsTotal : 1
     * status : 1
     */

    private String currentPage;
    private String error;
    private String msg;
    private String pageCount;
    private String pageData;
    private String pageSize;
    private String recordsTotal;
    private String status;
    private List<OrderBean> data;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
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

    public List<OrderBean> getData() {
        return data;
    }

    public void setData(List<OrderBean> data) {
        this.data = data;
    }

}
