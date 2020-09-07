package com.lifang123.push;

import com.heytap.msp.push.HeytapPushManager;
import com.heytap.msp.push.callback.ICallBackResultService;
import org.apache.cordova.LOG;
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.json.JSONObject;

public class OppoPush extends CordovaPlugin {
    private static final String TAG = "OppoPushTag";
    public static OppoPush instance;
    public OppoPush() {
        instance = this;
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("register")) {
            HeytapPushManager.init(this.cordova.getContext(), true);
            HeytapPushManager.register(this.cordova.getContext(),"appKey","appSecret",mPushCallback );
            HeytapPushManager.requestNotificationPermission();
            return true;
        }
        return false;
    }
    private ICallBackResultService mPushCallback = new ICallBackResultService() {
        @Override
        public void onRegister(int code, String s) {
            if (code == 0) {
                LOG.i("注册成功", "registerId:" + s);
            } else {
                LOG.i("注册失败", "code=" + code + ",msg=" + s);
            }
        }

        @Override
        public void onUnRegister(int code) {
            if (code == 0) {
                LOG.i("注销成功", "code=" + code);
            } else {
                LOG.i("注销失败", "code=" + code);
            }
        }

        @Override
        public void onGetPushStatus(final int code, int status) {
            if (code == 0 && status == 0) {
                LOG.i("Push状态正常", "code=" + code + ",status=" + status);
            } else {
                LOG.i("Push状态错误", "code=" + code + ",status=" + status);
            }
        }

        @Override
        public void onGetNotificationStatus(final int code, final int status) {
            if (code == 0 && status == 0) {
                LOG.i("通知状态正常", "code=" + code + ",status=" + status);
            } else {
                LOG.i("通知状态错误", "code=" + code + ",status=" + status);
            }
        }

        @Override
        public void onSetPushTime(final int code, final String s) {
            LOG.i("SetPushTime", "code=" + code + ",result:" + s);
        }

    };


}
