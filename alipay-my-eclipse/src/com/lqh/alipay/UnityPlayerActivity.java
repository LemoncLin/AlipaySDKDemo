package com.lqh.alipay;

import java.util.Map;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.lqh.alipay.PayResult;
import com.lqh.alipay.util.OrderInfoUtil2_0;
import com.unity3d.player.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import android.view.Window;

import android.widget.Toast;

public class UnityPlayerActivity extends Activity
{
    protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code

      
    
	/** 支付宝支付业务：入参app_id */
	public static final String APPID = "2016091400510534";
	
	/** 支付宝账户登录授权业务：入参pid值 */
	public static final String PID = "2088102175615761";
	/** 支付宝账户登录授权业务：入参target_id值 */
	public static final String TARGET_ID = "";

	/** 商户私钥，pkcs8格式 */
	/** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
	/** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
	/** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
	/** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
	/** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
	public static final String RSA2_PRIVATE = "MIIEogIBAAKCAQEAwWS+j9Uuw5NntpwRqUl5sYc1t9KDLCvgIBzkiosrhg52M1nZ9FqibjdGm+FuY4Qi3BkHGI/qIzJQkbrbn93/6hwTij07HvVglJ2hSnBCTiUmkZ7euBhuv4JbWDUbxXczDM9X1ikNafgHGemJKZJ+nwo19+HgYqj8KiS44VgDgz3/rlZx42d8CmFYPsOxyYSO/BCste0qgBokG5EvQuCWt7ZTgq2XCtn9RsTDtY+84qrj2OJ42pzwMghC2PQ7r1J8bj8R1RJGCCGbhVp6fBkVNIUrqDwTwxgh01JSPxasqncs+DpDh6KtKZ+y2ctgIKalJxz64W9FIBzGjMNtTwwC2QIDAQABAoIBAE+cCx3mYeO8cDspr9OCHYhI4oITNFAgl5hgjGvDslzy4xFscV43WRoJwxU0DV8F69uyqcsgov9uHOCAjRwYJNrYR+rmHco2tFU5NkYQRBsrC99yCLi7xgsrvDJpWk9FLEyfpcbRC2aN5U5zZjUoytrpNyIdZ2L1sSggCcMpmfiR+8tU+vSD/YYD3O8J/Mz+tU5q4evJbylzoPQ1brdeLs+zCU4os94OfUTeAgkH+rtEQjtgEklR81IcAWMp7lV2nlZgEdWyMrebRxFS114SGLvhiD1hojCsGbFAdCqid7zJGUp18RhP6BA+OZOVpfbnoFzeWi6IM5z8VW0LGJnF5skCgYEA47XCMwonpRxuWJ6AjE8WZqCBZZ2lqrmoVrZRdFbkuS0OzzhWCA7XrN2DhUQQQQ6Y53j3sDzstjL9yG0cyUCS9o44yLuQRHaXoijDtZKzIl3ru86DDLrGnw3Q79D7D+eCx92VnoLiaH4uuJciXmbC9CdZ8r1P2OO5AJGDZhmyB1sCgYEA2WuP4LORdyUzhCTRX0vS7iPgC/OjWIAaXWfE+Asx4FUi+xpdTwn0baTfejwJHhUGJecnjkg1EcfIOrMX05ufghuhxhgCG0lAC/RvqsUPRDfN6T8uwTU80iU7MFXxOuC+LD+3HWV0ormw/leyFSx8K8joS6OUBl4O10ux2FyEqNsCgYAUMaZLSHdDmUXf7g6QYuMlrs5K6LQsc9KN/hUzMBGQqoJJtkFK7aYK2oozY1ndqyG8RWoR4xVLAzKmd6a54lQmMW6vvpKIv8uqwdq5W40vWwzw1tugYWAD8wG4gtwBCRL8DpaaPBlAExCpph2SKaLYbDT55fgSz/80IbN5doXnSQKBgGRQqpvEflN4yKIp0qijdXkG+HLLQQyuXnvGSy0fDS4yWed9rJuxPiPwtGPTm2lTzfhjBO8zBblkclATHeU2oowHb8bUgPRSAckCSRuDvJ1/GfSKgGoIxD05jGuq25iM5XHJHBXmH8CM6MsRb0sxsUEKe5Oar8uYTkSr2iy300WNAoGAFkrdYD4EcabJsOmnVgkpcJRmoqnTFMtMvPj2YpkFkIEGp8lfbgdGuNOXbrugPddycelNz+5Lx4WqSelicMHsRVq/yk/tu5Z+tGw0zMi+p0HfEivSsX40oZw2kXUyrfSA4OfKpqVd7N59Yq0OHea8nlDg1flGFs7Fj6ZZke5TEgg=";
	public static final String RSA_PRIVATE = "";
	
	private static final int SDK_PAY_FLAG = 1;  //sdk正常运行了吗?
	private static final int SDK_AUTH_FLAG = 2;  //sdk确认了吗?

	//返回给unity成功
	public  String  productid="SUCCES";
	


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case SDK_PAY_FLAG: {  //如果消息是   sdk正常运行
                @SuppressWarnings("unchecked")
               //将随该消息附带的msg.obj强转回map中，建立新的payresult支付结果
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                /**
                 		对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 */
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();//得到resultstatus
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) 
                {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    Toast.makeText(UnityPlayerActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    //处理支付结果
                    PayResultToUnity(productid);
                } 
                else
                {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    Toast.makeText(UnityPlayerActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                   
                }
                break;
            }

            default:
                break;
            }
        };
    };
    
    
    
    
    // Setup activity layout
    @Override protected void onCreate (Bundle savedInstanceState)
    {
    	
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        
       //这是唤醒沙箱app   后面加的
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
    
        getWindow().setFormat(PixelFormat.RGBX_8888); // <--- This makes xperia play happy

        mUnityPlayer = new UnityPlayer(this);
        
        setContentView(mUnityPlayer);
        
        mUnityPlayer.requestFocus();
        
    }
    
    
    
    
    /**
     * 支付宝支付业务
     * 
     * @param v
     */
    public String ToAliPay(String name,String price,String productid) 
    {
    	
    	Log.i("DLog", "unity获得:  "+name);
    	Log.i("DLog", "unity获得:  "+price);
    	Log.i("DLog", "unity获得:  "+productid);
    	
        this.productid=productid;  
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);  //判断有没有设置RSA2_PRIVATE
        //在这里  传入AppID和私钥  得到请求map   即包含支付订单信息的map
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID,name,price,productid, rsa2);
        //将map解析成一个string类型的支付订单
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
     
        //获得私钥
        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;

        //得到sign
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        
        Log.i("DLog", "sign :   "+sign);
        //拼接咯
        final String orderInfo = orderParam + "&" + sign;    
        //新开一个线程，将orderInfo字串传入到PayTask任务中去
        Runnable payRunnable = new Runnable() 
        {

            @Override
            public void run() 
            {
            	//新建一个任务对象
                PayTask alipay = new PayTask(UnityPlayerActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                
                Log.i("DLog", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
        Log.i("DLog", orderInfo);
        
        return orderInfo;
    }
    
    
    //返回给Unity
   public   void   PayResultToUnity(String productid)
   {   
                                 //物体名字，   方法名字    方法的参数 
    UnityPlayer.UnitySendMessage("MainCamera","PayResult",productid);

    }

    
    
    //unity

    @Override protected void onNewIntent(Intent intent)
    {
        // To support deep linking, we need to make sure that the client can get access to
        // the last sent intent. The clients access this through a JNI api that allows them
        // to get the intent set on launch. To update that after launch we have to manually
        // replace the intent with the one caught here.
        setIntent(intent);
    }

    // Quit Unity
    @Override protected void onDestroy ()
    {
        mUnityPlayer.quit();
        super.onDestroy();
    }

    // Pause Unity
    @Override protected void onPause()
    {
        super.onPause();
        mUnityPlayer.pause();
    }

    // Resume Unity
    @Override protected void onResume()
    {
        super.onResume();
        mUnityPlayer.resume();
    }

    // Low Memory Unity
    @Override public void onLowMemory()
    {
        super.onLowMemory();
        mUnityPlayer.lowMemory();
    }

    // Trim Memory Unity
    @Override public void onTrimMemory(int level)
    {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_RUNNING_CRITICAL)
        {
            mUnityPlayer.lowMemory();
        }
    }

    // This ensures the layout will be correct.
    @Override public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    // Notify Unity of the focus change.
    @Override public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    // For some reason the multiple keyevent type is not supported by the ndk.
    // Force event injection by overriding dispatchKeyEvent().
    @Override public boolean dispatchKeyEvent(KeyEvent event)
    {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.injectEvent(event);
        return super.dispatchKeyEvent(event);
    }  
    

    // Pass any events not handled by (unfocused) views straight to UnityPlayer
    @Override public boolean onKeyUp(int keyCode, KeyEvent event)     { return mUnityPlayer.injectEvent(event); }
    @Override public boolean onKeyDown(int keyCode, KeyEvent event)   { return mUnityPlayer.injectEvent(event); }
    @Override public boolean onTouchEvent(MotionEvent event)          { return mUnityPlayer.injectEvent(event); }
    /*API12*/ public boolean onGenericMotionEvent(MotionEvent event)  { return mUnityPlayer.injectEvent(event); }
}
