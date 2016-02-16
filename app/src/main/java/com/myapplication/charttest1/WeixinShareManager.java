package com.myapplication.charttest1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;


import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 实现微信分享功能的核心类
 * @author Administrator
 *
 */
public class WeixinShareManager{

    private static final int THUMB_SIZE = 150;
    /**
     * 文字
     */
    public static final int WEIXIN_SHARE_WAY_TEXT = 1;
    /**
     * 圖片
     */
    public static final int WEIXIN_SHARE_WAY_PIC = 2;
    /**
     * link
     */
    public static final int WEIXIN_SHARE_WAY_WEBPAGE = 3;




    /**Share Type
    /**
     *Talk
     */
    public static final int WEIXIN_SHARE_TYPE_TALK = SendMessageToWX.Req.WXSceneSession;
    /**
     * FriendCircle Timeline
     */
    public static final int WEIXIN_SHARE_TYPE_FRENDS = SendMessageToWX.Req.WXSceneTimeline;
    private static WeixinShareManager instance;
    private static String weixinAppId;
    private IWXAPI wxApi;
    private Context context;

    private WeixinShareManager(Context context){
        this.context = context;
        //get APP_ID
        weixinAppId = WeixiShareUtil.getWeixinAppId(context);
        //inital wechat connect
        if(weixinAppId != null){
            initWeixinShare(context);
        }
    }

    /**

     * 線程會有問題。請在UI線程中操作
     * @return
     */
    public static WeixinShareManager getInstance(Context context){
        if(instance == null){
            instance = new WeixinShareManager(context);
        }
        return instance;
    }

    private void initWeixinShare(Context context){
        wxApi = WXAPIFactory.createWXAPI(context, weixinAppId, true);
        wxApi.registerApp(weixinAppId);
    }

    /**
     * 通過微信分享
     * @param shareWay 分享的方式（文本、圖片、鏈結）
     * @param shareType 分享的类型（朋友圈，TALK）
     */
    public void shareByWeixin(ShareContent shareContent, int shareType){
        switch (shareContent.getShareWay()) {
            case WEIXIN_SHARE_WAY_TEXT:
                shareText(shareType, shareContent);
                break;
            case WEIXIN_SHARE_WAY_PIC:
                sharePicture(shareType, shareContent);
                break;
            case WEIXIN_SHARE_WAY_WEBPAGE:
                shareWebPage(shareType, shareContent);
                break;
        }
    }
    //抽象類別，GET 分享類型
    private abstract class ShareContent{
        protected abstract int getShareWay();
        protected abstract String getContent();
        protected abstract String getTitle();
        protected abstract String getURL();
        protected abstract int getPicResource();

    }

    /**
     * Set分享文字的内容
     * @author Administrator
     *
     */
    public class ShareContentText extends ShareContent{
        private String content;

        /**
         * 构造分享文字类
         * @param text 分享的文字内容
         */
        public ShareContentText(String content){
            this.content = content;
        }

        @Override
        protected String getContent() {

            return content;
        }

        @Override
        protected String getTitle() {
            return null;
        }

        @Override
        protected String getURL() {
            return null;
        }

        @Override
        protected int getPicResource() {
            return -1;
        }

        @Override
        protected int getShareWay() {
            return WEIXIN_SHARE_WAY_TEXT;
        }

    }

    /**
     * Set分享圖片的内容
     * @author Administrator
     *
     */
    public class ShareContentPic extends ShareContent{
        private int picResource;
        public ShareContentPic(int picResource){
            this.picResource = picResource;
        }

        @Override
        protected String getContent() {
            return null;
        }

        @Override
        protected String getTitle() {
            return null;
        }

        @Override
        protected String getURL() {
            return null;
        }

        @Override
        protected int getPicResource() {
            return picResource;
        }

        @Override
        protected int getShareWay() {
            return WEIXIN_SHARE_WAY_PIC;
        }
    }

    /**
     * Set分享URL的内容
     * @author Administrator
     *
     */
    public class ShareContentWebpage extends ShareContent{
        private String title;
        private String content;
        private String url;
        private int picResource;
        public ShareContentWebpage(String title, String content,
                                   String url, int picResource){
            this.title = title;
            this.content = content;
            this.url = url;
            this.picResource = picResource;
        }

        @Override
        protected String getContent() {
            return content;
        }

        @Override
        protected String getTitle() {
            return title;
        }

        @Override
        protected String getURL() {
            return url;
        }

        @Override
        protected int getPicResource() {
            return picResource;
        }

        @Override
        protected int getShareWay() {
            return WEIXIN_SHARE_WAY_WEBPAGE;
        }

    }
   /* SHARE FUNCTION */
    /*
     * 分享文字
     */
    private void shareText(int shareType, ShareContent shareContent) {
        String text = shareContent.getContent();
        //Initial WXTextObject
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;
        //用WXTextObject initial  WXMediaMessage
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = text;
        //struct Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        //transaction
        req.transaction = buildTransaction("textshare");
        req.message = msg;
        //  WXSceneSession or 朋友圈 WXSceneTimeline。
        // Default WXSceneSession。
        req.scene = shareType;
        wxApi.sendReq(req);
    }

    /*
     * 分享圖片
     */
    private void sharePicture(int shareType, ShareContent shareContent) {
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), shareContent.getPicResource());
        WXImageObject imgObj = new WXImageObject(bmp);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;

        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = WeixiShareUtil.bmpToByteArray(thumbBmp, true);  //set pic screen

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("imgshareappdata");
        req.message = msg;
        req.scene = shareType;
        wxApi.sendReq(req);
    }

    /*
     * 分享URL
     */
    private void shareWebPage(int shareType, ShareContent shareContent) {
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = shareContent.getURL();
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = shareContent.getTitle();
        msg.description = shareContent.getContent();

        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), shareContent.getPicResource());
        if(thumb == null){
            Toast.makeText(context, "Picture null ", Toast.LENGTH_SHORT).show();
        }else{
            msg.thumbData = WeixiShareUtil.bmpToByteArray(thumb, true);
        }

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = shareType;
        wxApi.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}