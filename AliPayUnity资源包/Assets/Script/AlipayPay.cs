using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System;

public class AlipayPay : MonoBehaviour {

	private  AndroidJavaObject _jo;
	private  AndroidJavaClass _jc;

	public  InputField inputFieldMoney;		//商品金额
	public  InputField inputFileTitle;      //商品详情
	public  InputField inputFileProductid;  //商品id
	public  Text resultLabel;



	void Start () {
		//初始化
		_jc=new AndroidJavaClass("com.unity3d.player.UnityPlayer");
		_jo = _jc.GetStatic<AndroidJavaObject> ("currentActivity");
	}
	

	//唤起支付宝的支付
	public  void   Pay()
	{
		string money = inputFieldMoney.text;
		string title = inputFileTitle.text;
		string productid = inputFileProductid.text;
		resultLabel.text = "支付结果: " + _jo.Call<string> ("ToAliPay",title,money,productid);
	}

}
