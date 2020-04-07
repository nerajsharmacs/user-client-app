package com.st.springwebflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.st.springwebflux.model.Users;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
	
	@GetMapping("/flux/allusers")
	public Flux<Users> getAllUsers(){
		try {
			WebClient webClient = WebClient.create("http://localhost:8080");
			return webClient.get()
			        .uri("/allusers")
			        .retrieve()
			        .bodyToFlux(Users.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	@GetMapping("/flux/user/{id}")
	public Mono<Users> getUser(@PathVariable String id){
		try {
			WebClient webClient = WebClient.create("http://localhost:8080");
			return webClient.get()
			        .uri("/user/{id}",id)
			        .retrieve()
			        .bodyToMono(Users.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
