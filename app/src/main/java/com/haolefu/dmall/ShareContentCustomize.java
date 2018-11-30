package com.haolefu.dmall;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.haolefu.dmall.utils.ArrayUtils;

import org.json.JSONArray;
import org.json.JSONException;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

import static com.mob.tools.utils.Strings.getString;

public class ShareContentCustomize implements ShareContentCustomizeCallback {

    private String  jsonImages;
    private Context mContext;

    public ShareContentCustomize(String jsonImages) {
        this.jsonImages = jsonImages;
    }

    public void onShare(Platform platform, Platform.ShareParams paramsToShare) {

    }

}