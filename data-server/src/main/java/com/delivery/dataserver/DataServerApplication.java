package com.delivery.dataserver;

import com.delivery.grpc.DeliveryServiceGrpc;
import com.delivery.grpc.PositionAndSpeedRequest;
import com.delivery.grpc.PositionAndSpeedResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;

@SpringBootApplication
public class DataServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataServerApplication.class, args);
	}

}
