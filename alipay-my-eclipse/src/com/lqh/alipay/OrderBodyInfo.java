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


	    //֧����ҵ�������Ϣ
	    //������Ϣ
	    public String body;//��һ�ʽ��׵ľ���������Ϣ������Ƕ�����Ʒ���뽫��Ʒ�����ַ����ۼӴ���body���磺Iphone6 16G
	    public String subject;//��Ʒ�ı���/���ױ���/��������/�����ؼ��ֵȡ��磺����͸��������ʾ�ڶ�����Ϣ�����
	    public String out_trade_no;//�̻���վΨһ������.�磺70501111111S001111119
	    public String timeout_express;//�ñʶ��������������ʱ�䣬���ڽ��رս��ס�ȡֵ��Χ��1m��15d��m-���ӣ�h-Сʱ��d-�죬1c-���죨1c-���������£����۽��׺�ʱ����������0��رգ��� �ò�����ֵ������С���㣬 �� 1.5h����ת��Ϊ 90m��
	                                  //ע����Ϊ�գ���Ĭ��Ϊ15d���磺90m
	    public String total_amount;//�����ܽ���λΪԪ����ȷ��С�������λ��ȡֵ��Χ[0.01,100000000]
	    public String product_code;//���۲�Ʒ�룬�̼Һ�֧����ǩԼ�Ĳ�Ʒ�룬Ϊ�̶�ֵQUICK_MSECURITY_PAY

	    //������ϢΪ��ѡ
	    public String goods_type;//��Ʒ�����ͣ�0����������Ʒ��1��ʵ������Ʒ ע����������Ʒ��֧��ʹ�û�������
	    public String passback_params;//���ûش��������������ʱ�����˸ò������򷵻ظ��̻�ʱ��ش��ò�����֧���������첽֪ͨʱ���ò���ԭ�����ء��������������UrlEncode֮��ſ��Է��͸�֧����
	    public String promo_params;//�Żݲ���   ע������֧����Э�̺����. �磺 {"storeIdType":"1"}
	    public String extend_params;//ҵ����չ�������������ġ�ҵ����չ����˵����     �磺   {"sys_service_provider_id":"2088511833207846"}

	    public String enable_pay_channels;//�����������û�ֻ����ָ��������Χ��֧�����ж������ʱ�á�,���ָ�  ע����disable_pay_channels����.   �磺  pcredit,moneyFund,debitCardExpress
	    public String disable_pay_channels;//�����������û�������ָ������֧�����ж������ʱ�á�,���ָ�    ע����enable_pay_channels����.         �磺  pcredit,moneyFund,debitCardExpress
	    public String store_id;//�̻��ŵ��š��ò���������������������ָ��ŵ꣬�Ǳش���            �磺NJ_001

	    //�Զ�����Ϣ
	    public String product_id;//���۵���Ʒid  
}
