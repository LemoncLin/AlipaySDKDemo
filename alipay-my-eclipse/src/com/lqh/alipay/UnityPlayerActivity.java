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

      
    
	/** ֧����֧��ҵ�����app_id */
	public static final String APPID = "2016091400510534";
	
	/** ֧�����˻���¼��Ȩҵ�����pidֵ */
	public static final String PID = "2088102175615761";
	/** ֧�����˻���¼��Ȩҵ�����target_idֵ */
	public static final String TARGET_ID = "";

	/** �̻�˽Կ��pkcs8��ʽ */
	/** ����˽Կ��RSA2_PRIVATE ���� RSA_PRIVATE ֻ��Ҫ����һ�� */
	/** ����̻������������ˣ�����ʹ�� RSA2_PRIVATE */
	/** RSA2_PRIVATE ���Ա�֤�̻������ڸ��Ӱ�ȫ�Ļ����½��У�����ʹ�� RSA2_PRIVATE */
	/** ��ȡ RSA2_PRIVATE������ʹ��֧�����ṩ�Ĺ�˽Կ���ɹ������ɣ� */
	/** ���ߵ�ַ��https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
	public static final String RSA2_PRIVATE = "MIIEogIBAAKCAQEAwWS+j9Uuw5NntpwRqUl5sYc1t9KDLCvgIBzkiosrhg52M1nZ9FqibjdGm+FuY4Qi3BkHGI/qIzJQkbrbn93/6hwTij07HvVglJ2hSnBCTiUmkZ7euBhuv4JbWDUbxXczDM9X1ikNafgHGemJKZJ+nwo19+HgYqj8KiS44VgDgz3/rlZx42d8CmFYPsOxyYSO/BCste0qgBokG5EvQuCWt7ZTgq2XCtn9RsTDtY+84qrj2OJ42pzwMghC2PQ7r1J8bj8R1RJGCCGbhVp6fBkVNIUrqDwTwxgh01JSPxasqncs+DpDh6KtKZ+y2ctgIKalJxz64W9FIBzGjMNtTwwC2QIDAQABAoIBAE+cCx3mYeO8cDspr9OCHYhI4oITNFAgl5hgjGvDslzy4xFscV43WRoJwxU0DV8F69uyqcsgov9uHOCAjRwYJNrYR+rmHco2tFU5NkYQRBsrC99yCLi7xgsrvDJpWk9FLEyfpcbRC2aN5U5zZjUoytrpNyIdZ2L1sSggCcMpmfiR+8tU+vSD/YYD3O8J/Mz+tU5q4evJbylzoPQ1brdeLs+zCU4os94OfUTeAgkH+rtEQjtgEklR81IcAWMp7lV2nlZgEdWyMrebRxFS114SGLvhiD1hojCsGbFAdCqid7zJGUp18RhP6BA+OZOVpfbnoFzeWi6IM5z8VW0LGJnF5skCgYEA47XCMwonpRxuWJ6AjE8WZqCBZZ2lqrmoVrZRdFbkuS0OzzhWCA7XrN2DhUQQQQ6Y53j3sDzstjL9yG0cyUCS9o44yLuQRHaXoijDtZKzIl3ru86DDLrGnw3Q79D7D+eCx92VnoLiaH4uuJciXmbC9CdZ8r1P2OO5AJGDZhmyB1sCgYEA2WuP4LORdyUzhCTRX0vS7iPgC/OjWIAaXWfE+Asx4FUi+xpdTwn0baTfejwJHhUGJecnjkg1EcfIOrMX05ufghuhxhgCG0lAC/RvqsUPRDfN6T8uwTU80iU7MFXxOuC+LD+3HWV0ormw/leyFSx8K8joS6OUBl4O10ux2FyEqNsCgYAUMaZLSHdDmUXf7g6QYuMlrs5K6LQsc9KN/hUzMBGQqoJJtkFK7aYK2oozY1ndqyG8RWoR4xVLAzKmd6a54lQmMW6vvpKIv8uqwdq5W40vWwzw1tugYWAD8wG4gtwBCRL8DpaaPBlAExCpph2SKaLYbDT55fgSz/80IbN5doXnSQKBgGRQqpvEflN4yKIp0qijdXkG+HLLQQyuXnvGSy0fDS4yWed9rJuxPiPwtGPTm2lTzfhjBO8zBblkclATHeU2oowHb8bUgPRSAckCSRuDvJ1/GfSKgGoIxD05jGuq25iM5XHJHBXmH8CM6MsRb0sxsUEKe5Oar8uYTkSr2iy300WNAoGAFkrdYD4EcabJsOmnVgkpcJRmoqnTFMtMvPj2YpkFkIEGp8lfbgdGuNOXbrugPddycelNz+5Lx4WqSelicMHsRVq/yk/tu5Z+tGw0zMi+p0HfEivSsX40oZw2kXUyrfSA4OfKpqVd7N59Yq0OHea8nlDg1flGFs7Fj6ZZke5TEgg=";
	public static final String RSA_PRIVATE = "";
	
	private static final int SDK_PAY_FLAG = 1;  //sdk������������?
	private static final int SDK_AUTH_FLAG = 2;  //sdkȷ������?

	//���ظ�unity�ɹ�
	public  String  productid="SUCCES";
	


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case SDK_PAY_FLAG: {  //�����Ϣ��   sdk��������
                @SuppressWarnings("unchecked")
               //�������Ϣ������msg.objǿת��map�У������µ�payresult֧�����
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                /**
                 		����֧����������̻���������˵��첽֪ͨ�����ͬ��֪ͨ���������Ϊ֧��������֪ͨ��
                 */
                String resultInfo = payResult.getResult();// ͬ��������Ҫ��֤����Ϣ
                String resultStatus = payResult.getResultStatus();//�õ�resultstatus
                // �ж�resultStatus Ϊ9000�����֧���ɹ�
                if (TextUtils.equals(resultStatus, "9000")) 
                {
                    // �ñʶ����Ƿ���ʵ֧���ɹ�����Ҫ��������˵��첽֪ͨ��
                    Toast.makeText(UnityPlayerActivity.this, "֧���ɹ�", Toast.LENGTH_SHORT).show();
                    //����֧�����
                    PayResultToUnity(productid);
                } 
                else
                {
                    // �ñʶ�����ʵ��֧���������Ҫ��������˵��첽֪ͨ��
                    Toast.makeText(UnityPlayerActivity.this, "֧��ʧ��", Toast.LENGTH_SHORT).show();
                   
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
        
       //���ǻ���ɳ��app   ����ӵ�
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
    
        getWindow().setFormat(PixelFormat.RGBX_8888); // <--- This makes xperia play happy

        mUnityPlayer = new UnityPlayer(this);
        
        setContentView(mUnityPlayer);
        
        mUnityPlayer.requestFocus();
        
    }
    
    
    
    
    /**
     * ֧����֧��ҵ��
     * 
     * @param v
     */
    public String ToAliPay(String name,String price,String productid) 
    {
    	
    	Log.i("DLog", "unity���:  "+name);
    	Log.i("DLog", "unity���:  "+price);
    	Log.i("DLog", "unity���:  "+productid);
    	
        this.productid=productid;  
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);  //�ж���û������RSA2_PRIVATE
        //������  ����AppID��˽Կ  �õ�����map   ������֧��������Ϣ��map
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID,name,price,productid, rsa2);
        //��map������һ��string���͵�֧������
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
     
        //���˽Կ
        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;

        //�õ�sign
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        
        Log.i("DLog", "sign :   "+sign);
        //ƴ�ӿ�
        final String orderInfo = orderParam + "&" + sign;    
        //�¿�һ���̣߳���orderInfo�ִ����뵽PayTask������ȥ
        Runnable payRunnable = new Runnable() 
        {

            @Override
            public void run() 
            {
            	//�½�һ���������
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
    
    
    //���ظ�Unity
   public   void   PayResultToUnity(String productid)
   {   
                                 //�������֣�   ��������    �����Ĳ��� 
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
