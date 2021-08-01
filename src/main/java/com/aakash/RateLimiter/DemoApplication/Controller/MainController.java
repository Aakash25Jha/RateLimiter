package com.aakash.RateLimiter.DemoApplication.Controller;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aakash.RateLimiter.DemoApplication.Model.Calculator;
import com.aakash.RateLimiter.DemoApplication.Operations.OperationType;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@RestController
public class MainController {
	@Value("${result.string}")
    private String resultString;
	
	private final Bucket bucket;
	private Calculator calculator;
	
	public MainController(Calculator calculator) {
		this.calculator=calculator;
		Bandwidth limit = Bandwidth.classic(100, Refill.greedy(100, Duration.ofHours(1)));
	    this.bucket = Bucket4j.builder()
	        .addLimit(limit)
	        .build();
		
	}

	    @GetMapping("/calculate/{operationType}/{arg1}/{arg2}")
	    public ResponseEntity<String> calculate(@PathVariable OperationType operationType,@PathVariable Double arg1,@PathVariable Double arg2) {
	    	if (bucket.tryConsume(1)) {
	    		 return ResponseEntity.ok(resultString + calculator.calculate(operationType, arg1, arg2));
	    }
	    	 else
	    		 return ResponseEntity.ok("You have exceeded the number of api calls in a minute");
	}
}
