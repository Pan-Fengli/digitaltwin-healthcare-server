package com.example.digitaltwinhealthcareserver.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.digitaltwinhealthcareserver.Util.HttpURLConnectionUtil;
import com.example.digitaltwinhealthcareserver.Util.TemplateData;
import com.example.digitaltwinhealthcareserver.Util.WeChatUtil;
import com.example.digitaltwinhealthcareserver.Util.WxMssVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class WechatController {
    @Autowired
    private WeChatUtil weChatUtil;
    @RequestMapping(value = "/testXcx", method = RequestMethod.POST)
    public void testXcx(String code) {
//        String token = weChatUtil.refreshAccessToken();
//        this.log.info("token" + token);
        String openid = weChatUtil.getOpenID(code);
        log.info("openid:" + openid);
        weChatUtil.sendMsg(openid, "临时停车消息", "1号线T1在福德站临时停车111");
    }
    public String getOpenID() {
        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> params=new HashMap<>();
        params.put("APPID","wxceba43dc9ff99071");
        params.put("APPSECRET","5a4a22a00b0401e6d6bd09b45609ad2b");
        //0a1meZZv3BDW533bDU3w3Vf8Bj3meZZZ
        //ogBrFfRyoInexg-IPw236Omd-e6YfoKLtck-UoqdPyk
        String code="0a1meZZv3BDW533bDU3w3Vf8Bj3meZZZ";
        params.put("CODE",code);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/sns/jscode2session?appid={APPID}&secret={APPSECRET}&js_code={CODE}&grant_type=authorization_code", String.class, params);
        String body = responseEntity.getBody();
        JSONObject jsonObj = JSON.parseObject(body);
        System.out.println("get openid:" + jsonObj.toJSONString());
        String openpid = null;
        if(jsonObj != null){
            openpid = jsonObj.getString("openid");
        }
        System.out.println("openpid:"+openpid);
        return openpid;
    }

    @GetMapping("/pushOneUser")
    public String pushOneUser() {
        String openid=getOpenID();
        openid="obZsa7R3GNxxO4mhrEEkQZxkBFtE";
        return push(openid);
    }
    public String push(String openid) {
        RestTemplate restTemplate = new RestTemplate();
        //这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + getAccessToken();
        //拼接推送的模版
        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setTouser(openid);//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
        wxMssVo.setTemplate_id("ogBrFfRyoInexg-IPw236Omd-e6YfoKLtck-UoqdPyk");//订阅消息模板id
        wxMssVo.setPage("page/API/index");

        Map<String, TemplateData> m = new HashMap<>(3);
        m.put("thing2", new TemplateData("A1098号"));
        m.put("thing3", new TemplateData("前面还有1桌"));
        wxMssVo.setData(m);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxMssVo, String.class);
        return responseEntity.getBody();
    }
    @GetMapping("/getAccessToken")
    public String getAccessToken() {
        RestTemplate restTemplate=new RestTemplate();
        Map<String,String> params=new HashMap<>();
        params.put("APPID","wxceba43dc9ff99071");
        params.put("APPSECRET","5a4a22a00b0401e6d6bd09b45609ad2b");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", String.class, params);
        String body = responseEntity.getBody();
        JSONObject object = JSON.parseObject(body);
        String Access_Token = object.getString("access_token");
        String expires_in = object.getString("expires_in");
        System.out.println("有效时长expires_in：" + expires_in);
        return Access_Token;
    }
}
