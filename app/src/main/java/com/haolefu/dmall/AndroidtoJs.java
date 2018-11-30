package com.haolefu.dmall;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.haolefu.dmall.utils.ArrayUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class AndroidtoJs extends Object {

    private Context mContext;

    public AndroidtoJs(Context context) {
        this.mContext = context;
    }

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void share(String title, String url, String imageUrl, String desc) {
        OnekeyShare oks = new OnekeyShare();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(title);

//        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
//        oks.setText(" ");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(imageUrl);//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl(url);
        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(title);
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl(url);

        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                platform.getName();
                Log.e("MainAcitivty","成功");
                platform.isClientValid();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.e("MainAcitivty  失败 ", throwable.getStackTrace().toString());
                Log.e("MainAcitivty  失败 ", throwable.getMessage());
                throwable.getMessage();
                throwable.printStackTrace();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.e("MainAcitivty  分享 ", "取消了   ");
            }
        });

        oks.show(mContext);
    }

    @JavascriptInterface
    public void shareGoods(String desc, final String jsonImages) throws JSONException {
        OnekeyShare oks = new OnekeyShare();
        if (!TextUtils.isEmpty(desc)){
            oks.setText(desc);
        }else {
//            Log.e("MainAcitivty",jsonImages);
//            JSONArray imgsArr = new JSONArray(jsonImages);
//
////            oks.setTitle("美智甄品");
//            oks.setText("美智甄品");
//            oks.setImageArray(ArrayUtils.toArrays(imgsArr));

            oks.setShareContentCustomizeCallback(new ShareContentCustomize(jsonImages){
                @Override
                public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                    try {
                        if (Wechat.NAME.equals(platform.getName()) || QQ.NAME.equals(platform.getName()) || SinaWeibo.NAME.equals(platform.getName())) {
                            paramsToShare.setText("美智甄品");
//                Log.e("ShareContentCustomize", getString(R.string.app_name));
                            JSONArray imgsArr = null;
                            imgsArr = new JSONArray(jsonImages);
                            paramsToShare.setImageArray(ArrayUtils.toArrays(imgsArr));
                        } else if (WechatMoments.NAME.equals(platform.getName())) {
//                paramsToShare.setText("美智甄品");
                            paramsToShare.setShareType(Platform.SHARE_IMAGE);
                            JSONArray imgsArr = new JSONArray(jsonImages);
                            Log.e("ShareContentCustomize", ArrayUtils.toArrays(imgsArr)[0]);
                            paramsToShare.setImageUrl(ArrayUtils.toArrays(imgsArr)[0]);
                            Toast.makeText(mContext, "微信朋友圈不支持多图分享，本次分享选取为多张图片中的第一张", Toast.LENGTH_SHORT);
                        }
                    } catch (JSONException e) {
                        Log.d("ShareContentCustomize", "JSON Error");
                    }
                }
            });
        }

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(title);

//        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段

        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImageUrl(imageUrl);//确保SDcard下面存在此张图片
//        JSONArray imgsArr = new JSONArray(jsonImages);
//        oks.setImageArray(ArrayUtils.toArrays(imgsArr));//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl(url);

        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                platform.getName();
                Log.e("MainAcitivty","成功");
                platform.isClientValid();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.e("MainAcitivty  失败 ", throwable.getStackTrace().toString());
                Log.e("MainAcitivty  失败 ", throwable.getMessage());
                throwable.getMessage();
                throwable.printStackTrace();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.e("MainAcitivty  分享 ", "取消了   ");
            }
        });

        oks.show(mContext);




//        Wechat.ShareParams sp = new Wechat.ShareParams();
//        //微信分享网页的参数严格对照列表中微信分享网页的参数要求
//        sp.setTitle("标题");
//        sp.setText("我是共用的参数，这几个平台都有text参数要求，提取出来啦");
//        sp.setUrl("http://sharesdk.cn");
//        sp.setImageUrl("https://hmls.hfbank.com.cn/hfapp-api/9.png");
//        sp.setShareType(Platform.SHARE_WEBPAGE);
//        Platform weChat = ShareSDK.getPlatform(Wechat.NAME);
//// 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
//        weChat.setPlatformActionListener(new PlatformActionListener() {
//            @Override
//            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//                Log.d("ShareSDK", "onComplete ---->  分享成功");
//
//            }
//
//            @Override
//            public void onError(Platform platform, int i, Throwable throwable) {
//                Log.d("ShareSDK", "onError ---->  分享失败" + throwable.getStackTrace().toString());
//                Log.d("ShareSDK", "onError ---->  分享失败" + throwable.getMessage());
//                throwable.getMessage();
//                throwable.printStackTrace();
//            }
//
//            @Override
//            public void onCancel(Platform platform, int i) {
//                Log.d("ShareSDK", "onCancel ---->  分享取消");
//            }
//        });
//// 执行图文分享
//        weChat.share(sp);

    }
}