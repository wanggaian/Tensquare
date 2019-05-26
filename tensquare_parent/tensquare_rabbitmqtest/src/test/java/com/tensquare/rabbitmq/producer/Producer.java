package com.tensquare.rabbitmq.producer;

import com.tensquare.rabbitmq.RabbitMQApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Producer
 *
 * @Author wanggaian
 * @Date 2019/5/24 23:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitMQApplication.class)
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直接模式
     */
    @Test
    public void sendDirect() {
        rabbitTemplate.convertAndSend("itcast", "直接模式");
    }

    /**
     * 分裂模式
     */
    @Test
    public void sendFanout() {
        rabbitTemplate.convertAndSend("chuanzhi","", "分裂模式");
    }

    /**
     * 主题模式
     */
    @Test
    public void sendTopic() {
        rabbitTemplate.convertAndSend("topic1","good.log", "主题模式");
        System.out.println(" ");
    }
}
