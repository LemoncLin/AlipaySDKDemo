package com.lqh.alipay;

public class OrderBodyInfo {

	 public OrderBodyInfo(String body, String subject, String out_trade_no, String timeout_express, String total_amount,
	            String  product_id) {
	        super();
	        this.body = body;
	        this.subject = subject;
	        this.out_trade_no = out_trade_no;
	        this.timeout_express = timeout_express;
	        this.total_amount = total_amount;
	        this.product_code="QUICK_MSECURITY_PAY";
	        this.product_id = product_id;
	    }


	    //支付宝业务参数信息
	    //必填信息
	    public String body;//对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。如：Iphone6 16G
	    public String subject;//商品的标题/交易标题/订单标题/订单关键字等。如：大乐透，既是显示在订单信息界面的
	    public String out_trade_no;//商户网站唯一订单号.如：70501111111S001111119
	    public String timeout_express;//该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
	                                  //注：若为空，则默认为15d。如：90m
	    public String total_amount;//订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
	    public String product_code;//销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY

	    //以下信息为可选
	    public String goods_type;//商品主类型：0—虚拟类商品，1—实物类商品 注：虚拟类商品不支持使用花呗渠道
	    public String passback_params;//公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝
	    public String promo_params;//优惠参数   注：仅与支付宝协商后可用. 如： {"storeIdType":"1"}
	    public String extend_params;//业务扩展参数，详见下面的“业务扩展参数说明”     如：   {"sys_service_provider_id":"2088511833207846"}

	    public String enable_pay_channels;//可用渠道，用户只能在指定渠道范围内支付当有多个渠道时用“,”分隔  注：与disable_pay_channels互斥.   如：  pcredit,moneyFund,debitCardExpress
	    public String disable_pay_channels;//禁用渠道，用户不可用指定渠道支付当有多个渠道时用“,”分隔    注：与enable_pay_channels互斥.         如：  pcredit,moneyFund,debitCardExpress
	    public String store_id;//商户门店编号。该参数用于请求参数中以区分各门店，非必传项            如：NJ_001

	    //自定义信息
	    public String product_id;//销售的商品id  
}
