package com.ke.mytest.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Zhang Xudong
 * @date 2023/7/13 12:27
 */

@Slf4j
@Component
public class RegisterEventListener {

	@EventListener
	public void handleNotifyEvent(RegisterSuccessEvent event) {
		log.info("监听到用户注册成功事件：{}，你注册成功了哦。记得来玩儿~", event.getUserName());
	}

	@EventListener
	public void handleNotifyEvent2(GenericsEvent<String> event) {
		log.info("范型监听器监听成功：{}，handleNotifyEvent2。记得来玩儿~", event.toString());
	}
	@EventListener
	public void handleNotifyEvent3(GenericsEvent<Object> event) {
		log.info("范型监听器监听成功：{}，handleNotifyEvent3。记得来玩儿~", event.toString());
	}
}


