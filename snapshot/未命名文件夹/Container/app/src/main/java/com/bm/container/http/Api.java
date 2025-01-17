package com.bm.container.http;

import com.bm.container.entity.AlipayBaenEntity;
import com.bm.container.entity.OrderEntity;
import com.bm.container.entity.SaveSaleOfferInfoEntity;
import com.bm.container.entity.UserMeesageEntity;
import com.bm.container.entity.WeiXinPayEntity;
import com.bm.container.module.base.Entity.CheckVersionEntity;
import com.bm.container.module.base.bean.AllCityBean;
import com.bm.container.module.base.bean.HotCityBean;
import com.bm.container.module.base.bean.MainBannerBean;
import com.bm.container.module.discuss.bean.BannerBean;
import com.bm.container.module.discuss.bean.DiscussListBean;
import com.bm.container.module.login.bean.GetCityListBean;
import com.bm.container.module.login.bean.GetCountryListBean;
import com.bm.container.module.login.bean.LoginBean;
import com.bm.container.module.market.adapter.StatusBean;
import com.bm.container.module.market.adapter.TypeBean;
import com.bm.container.module.market.bean.BuyContainerBean;
import com.bm.container.module.market.bean.BuyListBean;
import com.bm.container.module.market.bean.OfferBuyListBean;
import com.bm.container.module.market.bean.OfferListBean;
import com.bm.container.module.market.bean.SaleContainerBean;
import com.bm.container.module.market.bean.SaleListBean;
import com.bm.container.module.personal.bean.ContainerOrderRedPointBean;
import com.bm.container.module.personal.bean.DeliveryNoteByBean;
import com.bm.container.module.personal.bean.DeliveryNoteRedPointBean;
import com.bm.container.module.personal.bean.DeliveryOrderBean;
import com.bm.container.module.personal.bean.FeedbackListBean;
import com.bm.container.module.personal.bean.HtmlInfoBean;
import com.bm.container.module.personal.bean.MessageList;
import com.bm.container.module.personal.bean.OrderDetailBean;
import com.bm.container.module.personal.bean.OrderDetailBeanTwo;
import com.bm.container.module.personal.bean.OrderListBean;
import com.bm.container.module.personal.bean.SeeDeliveryOrderBean;
import com.bm.container.module.personal.bean.UserInfoBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author nfmomo
 * <p>
 * 接口信息
 */

public interface Api {

    String baseIp = "http://www.findcontainer.cn";
//        String baseIp = "http://47.97.192.241";
//    String baseIp = "http://10.58.110.79";

    String baseCode = "";
//    String baseCode = ":8080";

    //    String project = "/container";
    String project = "";

    String baseUrl = baseIp + baseCode + project + "/";
//    String baseUrl = baseIp + baseCode + "/";

    String picUrl = baseIp + baseCode;

    /**
     * 获取用户信息
     */
    @GET("app/appUser/getAppUser")
    Observable<UserInfoBean> userInfo(
            @Query("id") String id, //编号
            @Query("telephone") String phone //帐号（电话号码）
    );

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST("app/appUser/modifyPassword")
    Observable<BaseBean> changePwd(
            @Field("telephone") String phone, //电话号码
            @Field("password") String pwd //密码（明文）
    );

    /**
     * 用户登录
     */
    @FormUrlEncoded
    @POST("app/appUser/userLogin")
    Observable<LoginBean> login(
            @Field("telephone") String phone, //电话号码
            @Field("password") String pwd //密码（明文）
    );

    /**
     * 用户登录 推送消息需要
     */
    @FormUrlEncoded
    @POST("app/appUser/verifyUser")
    Observable<BaseBean> loginVerifyUser(
//            @Field("regId") String regId,//
            @Field("userFlag") String userFlag, //
            @Field("appUserType") String appUserType, //
            @Field("id") String id //
    );

    @FormUrlEncoded
    @POST("app/appUser/updateRegIdByUserId")
    Observable<BaseBean> updateRegIdByUserId(
            @Field("regId") String regId,//
            @Field("userId") String userId//
    );

    /**
     * 修改用户信息－个人
     */
    @FormUrlEncoded
    @POST("app/appUser/verifyUser")
    Observable<BaseBean> changePersonInfo(
            @Field("id") String id, //id
            @Field("userFlag") String userFlag,
            @Field("appUserType") String appUserType,
            @Field("userName") String name, //姓名
            @Field("address") String address, //地址
            @Field("accountName") String bankName, //账户名
            @Field("accountSource") String bank, //支行
            @Field("bankAccount") String bankId, //帐号
            @Field("weixin") String weixin, //微信
            @Field("qq") String qq, //qq
            @Field("cities") String cities //主营城市
    );

    /**
     * 修改用户信息－公司
     */
    @FormUrlEncoded
    @POST("app/appUser/verifyUser")
    Observable<BaseBean> changeCompanyInfo(
            @Field("id") String id, //id
            @Field("userFlag") String userFlag,
            @Field("appUserType") String appUserType,
            @Field("companyName") String name, //公司名称
            @Field("companyTelephone") String phone, //公司电话
            @Field("companyAddress") String address, //公司地址
            @Field("companyPerson") String person, //公司负责人
            @Field("accountName") String bankName, //账户名
            @Field("accountSource") String bank, //支行
            @Field("bankAccount") String bankId, //帐号
            @Field("cities") String cities //主营城市
    );

    /**
     * 获取首页轮播图
     */
    @GET("app/banner/getFirstPageBanner")
    Observable<MainBannerBean> mainBanner(
            @Query("currentPage") String page, //当前页（从1开始）
            @Query("pageSize") String num //页数
    );

    /**
     * 获取注册协议
     */
    @GET("app/baseSystem/getProtocol")
    Observable<BaseBean> registerAgreement();

    /**
     * 获取反馈信息
     */
    @FormUrlEncoded
    @POST("app/feedBack/getFeedBack")
    Observable<BaseBean> getFeedback(
            @Field("id") String id //编号
    );

    /**
     * 获取反馈信息列表
     */
    @FormUrlEncoded
    @POST("app/feedBack/getFeedBackList")
    Observable<BaseBean> getFeedbackList(
            @Field("orderNo") String order, //订单号
            @Field("currentPage") String page, //当前页（从1开始）
            @Field("pageSize") String num //页数
    );

    /**
     * 保存反馈信息
     */
    @FormUrlEncoded
    @POST("app/feedBack/saveFeedBack")
    Observable<BaseBean> saveFeedback(
            @Field("createUser") String id, // 创建者
            @Field("telephone") String phone, // 手机号
            @Field("content") String content // 内容
    );

    /**
     * 获取帖子信息
     */
    @FormUrlEncoded
    @POST("app/forum/getForumList")
    Observable<BaseBean> postingList(
            @Field("currentPage") String page, //当前页（从1开始）
            @Field("pageSize") String num //页数
    );

    /**
     * 保存帖子信息
     */
    @FormUrlEncoded
    @POST("app/forum/saveForum")
    Observable<BaseBean> savePosting(
            @Field("content") String content, //内容
            @Field("imageUrl") String pic, //图片列表地址
            @Field("headImageUrl") String face, //人物头像地址
            @Field("telephone") String phone, //帐号（电话号码）
            @Field("createUserName") String name, //发送帖子人姓名
            @Field("createUser") String user //创建人信息
    );


    /**
     * 保存出售出价表信息
     * --------
     */
    @FormUrlEncoded
    @POST("app/tradeContainer/saveBuyOrder")
    Observable<SaveSaleOfferInfoEntity> saveSaleOfferInfo(
            @Field("billType") String yype, //发票类型
            @Field("billTypeName") String name, //发票类型名称
            @Field("billCheque") String top, //发票抬头
            @Field("billContent") String content, //发票内容
            @Field("billTelephone") String phone, //发票电话
            @Field("price") String money, //价格
            @Field("containerId") String no,//卖箱信息编号
            @Field("createUser") String user,//创建人信息

            @Field("billNo") String billNo,//发票税号
            @Field("billBankAccount") String billBankAccount,//发票银行帐号
            @Field("billAccountSource") String billAccountSource,//发票帐号开户行
            @Field("billAddress") String billAddress //发票地址

    );

    /**
     * 生成买卖订单
     */
    @FormUrlEncoded
    @POST("app/order/createContainerOrder")
    Observable<OrderEntity> creatOrder(
            @Field("payType") String payType, //支付类型 “0”
            @Field("containerId") String containerId, //买卖箱信息编号
            @Field("paidUser") String paidUser, //付款人
            @Field("createUser") String createUser,//生成订单的人

            @Field("bidOrderId") String buyNo, //求购出价表编号
            @Field("buyOrderId") String saleNo, //出售出价表编号 -----
            @Field("orderNo") String orderNo //订单编号    null
    );


    /**
     * 根据编号获取买箱信息
     */
    @GET("tradeContainer/getBuyContainer")
    Observable<BaseBean> getBuyInfo(
            @Query("id") String id //编号
    );

    /**
     * 根据订单号获取提货单
     */
    @GET("tradeContainer/getDeliveryNoteByOrderNo")
    Observable<BaseBean> billOfLoading(
            @Query("orderNo") String no //订单号
    );

    /**
     * 根据编号获取卖箱信息
     */
    @GET("tradeContainer/getSaleContainer")
    Observable<BaseBean> getSaleInfo(
            @Query("id") String id //编号
    );

    /**
     * 保存求购出价表信息
     */
    @FormUrlEncoded
    @POST("tradeContainer/saveBidOrder")
    Observable<BaseBean> saveBuyOfferInfo(
            @Field("bidCount") String num, //购买个数
            @Field("price") String money, //出价的价格
            @Field("isSpecialPrice") String need, //是否有特殊要求
            @Field("specialPrice") String specialFee, //特殊费用
            @Field("isSupportBill") String invoice, //是否有发票
            @Field("imageUrl") String pic, //图片列表
            @Field("buyContainerId") String id //买箱信息编号
    );

    /**
     * 保存买箱信息
     */
    @FormUrlEncoded
    @POST("tradeContainer/saveBuyContainer")
    Observable<BaseBean> saveBuyInfo(
            @Field("country") String country, //所在国家
            @Field("city") String city, //所在城市
            @Field("containerType") String type, //集装箱类型
            @Field("statusType") String status, //集装箱状态
            @Field("count") String num, //购买个数
            @Field("time") String time, //投标时间
            @Field("isSpecialPrice") String specialFee, //是否有特殊费用
            @Field("specialPrice") String money //特殊费用
    );


    /**
     * 保存提货单
     */
    @FormUrlEncoded
    @POST("tradeContainer/saveDeliveryNote")
    Observable<BaseBean> saveBillOfLoading(
            @Field("orderNo") String orderId, //订单号
            @Field("containerHeapNo") String placeId, //堆场编号
            @Field("containerType") String typeId, //集装箱类型编号
            @Field("containerTypeName") String typeName, //集装箱类型名称
            @Field("count") String num, //数量
            @Field("containerNo") String containerId, //集装箱编号
            @Field("heapName") String palceName, //堆场名称
            @Field("heapTelephone") String placePhone, //堆场电话
            @Field("heapContact") String placePerson, //堆场联系人
            @Field("deliveryDate") String time, //预计提货时间
            @Field("isSpecialPrice") String needSpecialFee, //是否有特殊费用
            @Field("specialPrice") String specialFee, //特殊费用
            @Field("heapEMail") String placeEmail, //堆场邮件
            @Field("companyName") String companyName, //公司名称
            @Field("companyEMail") String companyEmail, //公司邮件
            @Field("createUser") String creater, //创建人
            @Field("deliveryDetailList") String carList //车队信息列表
    );

    /**
     * 保存卖箱信息
     */
    @FormUrlEncoded
    @POST("tradeContainer/saveSaleContainer")
    Observable<BaseBean> saveSaleInfo(
            @Field("country") String country, //所在国家
            @Field("city") String city, //所在城市
            @Field("containerType") String type, //集装箱类型
            @Field("statusType") String status, //集装箱状态
            @Field("count") String num, //购买个数
            @Field("tradeType") String payType, //交易类型
            @Field("price") String money, //交易金额
            @Field("time") String time, //有效时间
            @Field("isSpecialPrice") String needSpecialFee, //是否有特殊费用
            @Field("specialPrice") String specialFee, //特殊费用
            @Field("isSupportBill") String needInvoice, //是否提供发票
            @Field("imageUrl") String pic //图片
    );

    /**
     * 发送验证码
     */
    @FormUrlEncoded
    @POST("app/appUser/sendValidateCode")
    Observable<BaseBean> sendCode(
            @Field("telephone") String phone, //电话号码
            @Field("type") String pwd //发送类型（0为注册时发送，1为忘记密码时发送）
    );


    /**
     * 获取首字母的城市列表
     */
    @GET("app/area/getHotCity")
    Observable<HotCityBean> hotCityList(
    );

    /**
     * 获取首字母的城市列表
     */
    @GET("app/area/getCityRelInfoWithFirstLetter")
    Observable<AllCityBean> allCityList(
    );

    /**
     * 获取国家列表
     */
    @GET("app/area/getCountry")
    Observable<GetCountryListBean> getCountryList(
    );

    /**
     * 获取国家获取下属城市
     */
    @GET("app/area/getCityByCountryId")
    Observable<GetCityListBean> getCityList(
            @Query("countryId") String id //城市编号
    );

    /**
     * 自动生成用户ID
     */
    @GET("app/appUser/getUserId")
    Observable<BaseBean> createId(
            @Query("cityId") String id //城市编号
    );

    /**
     * 用户注册－买家个人
     */
    @POST("app/appUser/registerAppUser")
    Observable<BaseBean> buyPerson(
//            @Field("userFlag") String userType, //用户类型
//            @Field("appUserType") String identityType, //身份类型
//            @Field("telephone") String phone, //手机号
//            @Field("password") String pwd, //密码
//            @Field("country") String country, //国家
//            @Field("city") String city, //城市
//            @Field("userId") String user, //用户id
//            @Field("userName") String name, //姓名
//            @Field("contact") String userPhone, //联系方式
//            @Field("weixin") String weixin, //微信
//            @Field("qq") String qq //qq
            @Body MultipartBody body
    );


    /**
     * 用户注册－买家公司
     */
    @POST("app/appUser/registerAppUser")
    Observable<BaseBean> buyCompany(
//            @Field("userFlag") String userType, //用户类型
//            @Field("appUserType") String identityType, //身份类型
//            @Field("telephone") String phone, //手机号
//            @Field("password") String pwd, //密码
//            @Field("country") String country, //国家
//            @Field("city") String city, //城市
//            @Field("userId") String user, //用户id
//            @Field("companyName") String companyName, //公司名
//            @Field("companyTelephone") String companyPhone, //公司电话
//            @Field("companyAddress") String companyAddress, //公司地址
//            @Field("companyPerson") String companyPerson //公司负责人

            @Body MultipartBody body
    );

    /**
     * 用户注册－卖家个人
     */
    @POST("app/appUser/registerAppUser")
    Observable<BaseBean> salePerson(
//            @Field("userFlag") String userType, //用户类型
//            @Field("appUserType") String identityType, //身份类型
//            @Field("telephone") String phone, //手机号
//            @Field("password") String pwd, //密码
//            @Field("country") String country, //国家
//            @Field("city") String city, //城市
//            @Field("userId") String user, //用户id
//            @Field("userName") String name, //姓名
//            @Field("contact") String userPhone, //联系方式
//            @Field("accountName") String bankName, //账号名
//            @Field("accountSource") String bank, //银行
//            @Field("bankAccount") String bankId, //账号
//            @Field("cities") String cities //主营城市
            @Body MultipartBody body
    );


    /**
     * 用户注册－卖家公司
     */
    @POST("app/appUser/registerAppUser")
    Observable<BaseBean> saleCompany(
//            @Field("userFlag") String userType, //用户类型
//            @Field("appUserType") String identityType, //身份类型
//            @Field("telephone") String phone, //手机号
//            @Field("password") String pwd, //密码
//            @Field("country") String country, //国家
//            @Field("city") String city, //城市
//            @Field("userId") String user, //用户id
//            @Field("companyName") String companyName, //公司名
//            @Field("companyTelephone") String companyPhone, //公司电话
//            @Field("companyAddress") String companyAddress, //公司地址
//            @Field("companyPerson") String companyPerson, //公司负责人
//            @Field("accountName") String bankName, //账号名
//            @Field("accountSource") String bank, //银行
//            @Field("bankAccount") String bankId, //账号
//            @Field("cities") String cities //主营城市
            @Body MultipartBody body
    );

    /**
     * 获取APP介绍
     */
    @GET("app/baseSystem/getAppIntroduce")
    Observable<BaseBean> introduction(
    );

    /**
     * 获取注册协议
     */
    @GET("app/baseSystem/getProtocol")
    Observable<BaseBean> agreement(
    );

    /**
     * 获取广告（最多三个）
     */
    @GET("app/forum/getAdvertisement")
    Observable<BannerBean> banner(
    );

    /**
     * 获取帖子信息
     */
    @GET("app/forum/getForumList")
    Observable<DiscussListBean> discussList(
            @Query("userId") String id, //当前用户的编号
            @Query("currentPage") String page, //当前页（从1开始）
            @Query("pageSize") String num //页数
    );

    /**
     * 帖子回复
     */
    @FormUrlEncoded
    @POST("app/forum/commentForum")
    Observable<BaseBean> reply(
            @Field("parentForumId") String id, //评论的帖子
            @Field("telephone") String phone, //帐号
            @Field("createUserName") String name, //发送者名字
            @Field("content") String content, //内容
            @Field("targetUserId") String targetId, //发送目标编号
            @Field("targetUserName") String targetName, //发送目标名称
            @Field("createUser") String userId //这个参数就是用户的id，不是userid

    );

    /**
     * 发布帖子
     */
    @POST("app/forum/saveForum")
    Observable<BaseBean> comment(@Body MultipartBody body);

    /**
     * 帖子点赞
     */
    @FormUrlEncoded
    @POST("app/forum/zanForum")
    Observable<BaseBean> zan(
            @Field("userId") String userId, //当前用户的编号
            @Field("forumId") String id //帖子编号

    );

    /**
     * 帖子取消点赞
     */
    @FormUrlEncoded
    @POST("app/forum/cancelZanForum")
    Observable<BaseBean> zanCancel(
            @Field("userId") String userId, //当前用户的编号
            @Field("forumId") String id //帖子编号

    );

    /**
     * 我的帖子
     */
    @GET("app/forum/getMyForumList")
    Observable<DiscussListBean> myDiscussList(
            @Query("userId") String id, //当前用户的编号
            @Query("currentPage") String page, //当前页（从1开始）
            @Query("pageSize") String num //页数
    );

    /**
     * 意见反馈列表
     */
    @GET("app/feedBack/getFeedBackList")
    Observable<FeedbackListBean> feedbackList(
            @Query("createUser") String user, //订单号(存疑)
            @Query("currentPage") String page, //当前页（从1开始）
            @Query("pageSize") String num //页数
    );

    /**
     * 获取反馈内容
     */
    @GET("app/feedBack/getFeedBack")
    Observable<FeedbackListBean> getReply(
            @Query("id") String id //页数
    );

    /**
     * 头像更新
     */
    @POST("app/appUser/uploadHeadImage")
    Observable<BaseBean> uploadFace(@Body MultipartBody body);

    /**
     * 获取系统消息列表
     */
    @GET("app/systemMessage/getSystemMessageList")
    Observable<MessageList> messageList(
            @Query("targetId") String id, //用户编号
            @Query("currentPage") String page, //用户编号
            @Query("pageSize") String num //用户编号
    );

    /**
     * 箱型
     */
    @GET("app/tradeContainer/getContainerType")
    Observable<TypeBean> containerType(
    );

    /**
     * 状态
     */
    @GET("app/tradeContainer/getContainerStatusType")
    Observable<StatusBean> containerStatus(
    );

    /**
     * 发布买箱信息
     */
    @POST("app/tradeContainer/saveBuyContainer")
    Observable<BaseBean> sendBuyInfo(@Body MultipartBody body);

    /**
     * 发布卖箱信息
     */
    @POST("app/tradeContainer/saveSaleContainer")
    Observable<BaseBean> sendSaleInfo(@Body MultipartBody body);

    /**
     * 买箱需求列表 我发起的
     */
    @FormUrlEncoded
    @POST("app/tradeContainer/searchBuyContainerList")
    Observable<BuyListBean> buyList(
            @Field("createUser") String id, //id
            @Field("currentPage") String page, //页面
            @Field("pageSize") String num, //页面大小
            @Field("city") String cityId, //城市id
            @Field("containerType") String containerId, //箱型编号
            @Field("statusType") String statusType, //箱况id
            @Field("containerTypeName") String content, //搜索内容
            @Field("isJoin") String isJoin
    );


    /**
     * 买箱需求列表  参与的
     */
    @FormUrlEncoded
    @POST("app/tradeContainer/getContainerListWithBuy")
    Observable<BuyListBean> buyListWithBuy(
            @Field("createUser") String id, //id
            @Field("currentPage") String page, //页面
            @Field("pageSize") String num//页面大小
    );

    /**
     * 买箱需求列表 发起的卖箱信息
     */
    @FormUrlEncoded
    @POST("app/tradeContainer/searchSaleContainerList")
    Observable<SaleListBean> saleList(
            @Field("createUser") String id, //id
            @Field("currentPage") String page, //页面
            @Field("pageSize") String num, //页面大小
            @Field("city") String cityId, //城市id
            @Field("containerType") String containerId, //箱型编号
            @Field("statusType") String statusType, //箱况id
            @Field("sort") String sort,//排序（0为升序，1为降序）
            @Field("containerTypeName") String content //搜索内容
    );


    /**
     * 买箱需求列表 发起的卖箱信息
     */
    @FormUrlEncoded
    @POST("app/tradeContainer/searchSaleContainerList")
    Observable<SaleListBean> saleListTwo(
            @Field("createUser") String id, //id
            @Field("currentPage") String page, //页面
            @Field("pageSize") String num, //页面大小
            @Field("isJoin") String isJoin
    );

    /**
     * 买箱需求列表 参与的卖箱信息
     */
    @FormUrlEncoded
    @POST("app/tradeContainer/getContainerListWithBid")
    Observable<SaleListBean> listWithBid(
            @Field("createUser") String id, //id
            @Field("currentPage") String page, //页面
            @Field("pageSize") String num//页面大小
    );

    /**
     * 根据市场交易编号获取出价记录
     */
    @GET("app/tradeContainer/getBidOrderList")
    Observable<OfferListBean> getOfferList(
            @Query("containerId") String id, //编号
            @Query("createUser") String name,
            @Query("currentPage") String page,
            @Query("pageSize") String num
    );

    /**
     * 根据市场交易编号获取出价记录(买家出价)
     */
    @GET("app/tradeContainer/getBuyOrderList")
    Observable<OfferBuyListBean> getOfferBuyList(
            @Query("containerId") String id, //编号
            @Query("createUser") String name,
            @Query("currentPage") String page,
            @Query("pageSize") String num
    );

    /**
     * 卖家出价
     */
    @POST("app/tradeContainer/saveBidOrder")
    Observable<BaseBean> offerPrice(@Body MultipartBody body);

    /**
     * 直接购买
     */
    @FormUrlEncoded
    @POST("app/tradeContainer/saveBuyOrder")
    Observable<BaseBean> buyOrder(
            @Field("billType") String invoiceType, //发票类型
            @Field("billCheque") String invoiceHead, //发票抬头
            @Field("billContent") String invoiceContent, //发票内容
            @Field("billTelephone") String invoicePhone, //发票电话
            @Field("price") String price, //价格
            @Field("containerId") String orderId,//卖箱信息编号
            @Field("createUser") String id,//创建人编号

            @Field("billNo") String billNo,//发票税号
            @Field("billBankAccount") String billBankAccount,//发票银行帐号
            @Field("billAccountSource") String billAccountSource,//发票帐号开户行
            @Field("billAddress") String billAddress //发票地址
    );

    /**
     * 我的帖子列表
     */
    @GET("app/order/getContainerOrderList")
    Observable<OrderListBean> orderList(
            @Query("createUser") String creater, //编号
            @Query("paidUser") String paier, //编号
            @Query("orderStatusType") String type, //编号
            @Query("currentPage") String page,
            @Query("pageSize") String num
    );


    /**
     * 催单
     */
    @FormUrlEncoded
    @POST("app/order/sendDeliveryNoteRemind")
    Observable<BaseBean> requestRemind(
            @Field("orderNo") String id //订单ID
    );

    /**
     * 取消订单
     */
    @FormUrlEncoded
    @POST("app/order/cancelOrder")
    Observable<BaseBean> cancelOrder(
            @Field("orderNo") String id //订单ID
    );

    /**
     * 修改箱号
     */
    @FormUrlEncoded
    @POST("app/order/updateContainerNo")
    Observable<BaseBean> changContainerNo(
            @Field("orderNo") String id, //订单号
            @Field("containerNo") String no //订单号
    );

//    /**
//     * 生成买卖订单(支付前)
//     */
//    @FormUrlEncoded
//    @POST("app/order/createContainerOrder")
//    Observable<BeforePayBean> beforePay(
//            @Field("payType") String type, //支付类型（1为微信，2为支付宝，3为银联）
//            @Field("containerId") String id, //买卖箱信息编号
//            @Field("paidUser") String payer, //支付人
//            @Field("createUser") String owner //所属人
//    );

    /**
     * 支付宝
     */
    @FormUrlEncoded
    @POST("app/alipay/paidMoney")
    Observable<BaseBean> alipay(
            @Field("orderNo") String no // 订单号
    );

    /**
     * GET /app/tradeContainer/getBuyContainer 根据编号获取买箱信息
     */
    @GET("app/tradeContainer/getBuyContainer")
    Observable<BuyContainerBean> requesetBrowser(
            @Query("id") String id //页数
    );


    /**
     * 卖箱
     * GET /app/tradeContainer/getSaleContainer 根据编号获取卖箱信息
     */
    @GET("app/tradeContainer/getSaleContainer")
    Observable<SaleContainerBean> requesetSellBox(
            @Query("id") String id //页数
    );


    /**
     * 微信预支付
     */
    @FormUrlEncoded
    @POST("app/weixin/pay")
    Observable<WeiXinPayEntity> weiXinPay(
            @Field("out_trade_no") String out_trade_no, //订单编号
            @Field("total_fee") String total_fee, //金额
            @Field("nonceStr") String nonceStr, //随机字符串
            @Field("timeStamp") String timeStamp //时间戳
    );


    /**
     * 支付宝预支付
     */
    @FormUrlEncoded
    @POST("app/alipay/paidMoney")
    Observable<AlipayBaenEntity> alipayPay(
            @Field("orderNo") String out_trade_no //订单编号
    );

    /**
     * GET /app/tradeContainer/getDeliveryNoteByOrderNo
     * 卖家提货单
     */
    @GET("app/order/getContainerOrderListWithDeliveryNote")
    Observable<DeliveryOrderBean> deliveryOrder(
            @Query("isWrite") String isWrite,
            @Query("userId") String userId, //编号
            @Query("currentPage") String currentPage,
            @Query("pageSize") String pageSize
    );

    /**
     * 根据订单号获取提货单GET
     */
    @GET("app/order/getContainerOrder")
    Observable<DeliveryNoteByBean> getDeliveryNoteByOrderNo(
            @Query("orderNo") String orderNo,
            @Query("userId") String userId
    );

    /**
     * 根据订单号获取提货单GET
     */
    @GET("app/order/getContainerOrder")
    Observable<OrderDetailBean> getMyOrderDelivery(
            @Query("orderNo") String orderNo,
            @Query("userId") String userId
    );

    /**
     * 根据订单号获取提货单GET
     */
    @GET("app/order/getContainerOrder")
    Observable<OrderDetailBeanTwo> getMyOrderDeliveryTwo(
            @Query("orderNo") String orderNo,
            @Query("userId") String userId
    );

    /**
     * 保存提货单
     */
    @POST("app/tradeContainer/saveDeliveryNote")
    Observable<BaseBean> saveDeliveryNote(@Body MultipartBody body);

    /**
     * 根据订单号获取提货单
     */
    @GET("app/tradeContainer/getDeliveryNoteByOrderNo")
    Observable<SeeDeliveryOrderBean> seeDeliveryOrder(
            @Query("orderNo") String orderNo
    );

    /**
     * 卖家
     */
    @GET("app/order/getDeliveryNoteRedPoint")
    Observable<DeliveryNoteRedPointBean> deliveryNoteRedPoint(
            @Query("userId") String userId
    );

    /**
     * 买家
     */
    @GET("app/order/getContainerOrderRedPoint")
    Observable<ContainerOrderRedPointBean> containerOrderRedPoint(
            @Query("userId") String userId,
            @Query("paidUser") String paidUser
    );

    /**
     * 发送提货单
     * orderNo 订单编号
     */
    @GET("app/tradeContainer/sendDeliveryNote")
    Observable<BaseBean> sendDeliveryNote(
            @Query("orderNo") String orderNo,
            @Query("email") String email
    );


    /**
     * 下载提货单
     * orderNo 订单编号
     */
    @POST("app/tradeContainer/downloadDeliveryNote")
    Observable<HtmlInfoBean> getHtmlInfo(
            @Query("orderNo") String orderNo
    );

    /**
     * 版本检测
     *
     * @param type
     * @param versionId
     * @return
     */
    @GET("app/baseSystem/getVersion")
    Observable<CheckVersionEntity> checkVersionHtml(
            @Query("type") String type,
            @Query("versionId") String versionId
    );


    /**
     * 系统消息页面需要调
     */
    @POST("app/appUser/cancelUserSysRed")
    Observable<BaseBean> messageClick(
            @Query("userId") String userId
    );

    /**
     * 消息红点
     */
    @POST("app/tradeContainer/getMyCenterRedPoint")
    Observable<UserMeesageEntity> userMessage(
            @Query("createUser") String userId
    );

    @GET("/create")
    Observable<pics> entity();

    class pics {

        /**
         * a : a
         * b : B
         */

        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

}
