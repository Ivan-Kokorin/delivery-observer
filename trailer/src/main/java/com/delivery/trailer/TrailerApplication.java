package com.delivery.trailer;

import com.delivery.trailer.service.DeliveryServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TrailerApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(8080)
				.addService(new DeliveryServiceImpl())
				.build();
		server.start();
		System.out.println("Server started");
		server.awaitTermination();
		SpringApplication.run(TrailerApplication.class, args);
	}

}
