package com.ke.mytest.model.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO Description
 *
 * @author Zhang Xudong
 * @since 2023/7/22 23:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeddyDog extends Dog{
	private String name;
}
