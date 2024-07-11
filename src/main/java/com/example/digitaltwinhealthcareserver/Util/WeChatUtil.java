package com.example.digitaltwinhealthcareserver.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.casco.base.constant.common.RedisKey;
//import com.casco.base.util.redis.RedisUtils;
//import com.casco.sdk.jrebus.utils.StringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "com.casco.istation.xcx", ignoreUnknownFields = true)
public class WeChatUtil {

    private String accessToken="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${appid}&secret=${secret}";

    private String appid="wxceba43dc9ff99071";

    private String secret="5a4a22a00b0401e6d6bd09b45609ad2b";

    private String templateid="ogBrFfRyoInexg-IPw236Omd-e6YfoKLtck-UoqdPyk";

    private String sendURL="https://api.weixin.qq.com/cgi-bin/message/subscribe/send";

    private String openIdURL="https://api.weixin.qq.com/sns/jscode2session?appid=${appid}&secret=${secret}&js_code=${code}&grant_type=authorization_code";

//    @Autowired
//    private RedisUtils redisCache;



    private RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(30000);
        requestFactory.setReadTimeout(30000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }

    public String refreshAccessToken(){
//        if(StringUtils.isEmpty(appid) || StringUtils.isEmpty(secret)){
//            log.error("刷新微信AccessToken时，缺少appid或secret参数！");
//            throw new RuntimeException("刷新微信AccessToken时，缺少appid或secret参数！");
//        }
        Map<String, Object> param = new HashMap<>();
        param.put("appid",appid);
        param.put("secret",secret);
        String url = accessToken.replace("${appid}", appid).replace("${secret}", secret);
        String resp = HttpUtils.sendGet(url, "");
//        ResponseEntity<JSONObject> resp = restTemplate.getForEntity(GetAccessToken, JSONObject.class,param);
//        JSONObject jsonObj = resp.getBody();
        JSONObject jsonObj = (JSONObject)JSON.parse(resp);
        System.out.println(jsonObj);
        String accessToken = null;
        if(jsonObj != null){
            accessToken = jsonObj.getString("access_token");
        }

//        redisCache.set(RedisKey.ISTATION_WECHATTOKEN, accessToken, 7000);
        return accessToken;
    }

    private String getToken() {
//        String token = (String)redisCache.get(RedisKey.ISTATION_WECHATTOKEN);
        String token =null;
        if (token == null) {
            token = refreshAccessToken();
//            token = (String)redisCache.get(RedisKey.ISTATION_WECHATTOKEN);
        }

        return token;
    }

    public String sendMsg(String openid, String title, String msg){
        //String token = request.getParameter("token");
//        String openid = "接收消息的微信用户的openId";
        // 构建订阅消息内容的JSON对象
        // 构建订阅消息内容的JSON对象
        JSONObject messageData = new JSONObject();
        messageData.put("thing2", createDataItem("标题", title));
        messageData.put("thing3", createDataItem("内容", msg));
        // 将订阅消息内容转换为JSON字符串
        String jsonData = messageData.toJSONString();
        sendSubscribeMessage(openid,templateid, "page/API/index",jsonData);
        return null;
    }
    private static Map<String, Object> createDataItem(String name, String value) {
        Map<String, Object> item = new HashMap<>();
        item.put("value", value);
        return item;
    }
    public void sendSubscribeMessage(String openid, String templateId, String page, String data) {
        try {
            String url = sendURL + "?access_token=" + getToken();
            // 构建请求体
            String body = buildRequestBody(openid, templateId, page, data);
            String result = HttpUtils.sendPost(url, body);
            log.info("send msg result:" + result);
        } catch (Exception e) {
            // 异常处理逻辑
            e.printStackTrace();
        }
    }

    private String buildRequestBody(String openid, String templateId, String page, String data) {
        // 构建请求体的JSON字符串
        return String.format(
                "{\"touser\":\"%s\",\"template_id\":\"%s\",\"page\":\"%s\",\"data\":%s}",
                openid, templateId, page, data);
    }

    public String getOpenID(String code) {
        String url = openIdURL.replace("${appid}", appid).replace("${secret}", secret).replace("${code}", code);

        String resp = HttpUtils.sendGet(url, "");
        JSONObject jsonObj = (JSONObject)JSON.parse(resp);
        log.info("get openid:" + jsonObj.toJSONString());
        String openpid = null;
        if(jsonObj != null){
            openpid = jsonObj.getString("openid");
        }

        return openpid;
    }
}
