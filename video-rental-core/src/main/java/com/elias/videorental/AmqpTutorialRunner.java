package com.elias.videorental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;

import com.elias.videorental.tut.Tut1Sender;


//public class AmqpTutorialRunner implements CommandLineRunner {
//
//	    @Value("${tutorial.client.duration:0}")
//	    private int duration;
//
//	    @Autowired
//	    private ConfigurableApplicationContext ctx;
//
//	    @Override
//	    public void run(String... arg0) throws Exception {
//	        System.out.println("Ready ... running for " + duration + "ms");
//	        Thread.sleep(10000);
//	        ctx.close();
//	}
//}
