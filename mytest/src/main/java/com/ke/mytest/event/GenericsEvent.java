package com.ke.mytest.event;


import lombok.Data;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;
import org.springframework.stereotype.Component;

/**
 *
 * @author zhangxudong
 * @since 2024/1/18 14:38
 */
@Component
@Data
public class GenericsEvent<T> implements ResolvableTypeProvider {
	private T data;
	private String name;

	public GenericsEvent(T data, String name) {
		this.data = data;
		this.name = name;
	}
	public GenericsEvent() {
	}

	@Override
	public ResolvableType getResolvableType() {
		return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(getData()));
	}
}
