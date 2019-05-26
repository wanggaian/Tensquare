package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * SmsListener
 *
 * @Author wanggaian
 * @Date 2019/5/25 17:31
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    @Value("${aliyun.sms.sign_name}")
    private String sign_name;

    @Value("${aliyun.sms.template_code}")
    private String template_code;

    @RabbitHandler
    public void sendSms(Map<String, String> map) {
        String mobile = map.get("mobile");
        String checkcode = map.get("checkcode");
        System.out.println("手机号" + mobile);
        System.out.println("验证码" + checkcode);

        try {
            smsUtil.sendSms(mobile, template_code, sign_name, "{\"code\":\"" + checkcode + "\"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
