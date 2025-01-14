package com.digitalojt.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Hello World! 画面コントローラークラス
 * 
 * @author haruka matano
 *
 */
@RestController
public class HelloController 
{
	@RequestMapping("/hello")
	public String home()
	{
		return "Hello World!";
	}

}
