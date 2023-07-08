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
		ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
				.usePlaintext()
				.build();
		DeliveryServiceGrpc.DeliveryServiceBlockingStub stub = DeliveryServiceGrpc.newBlockingStub(channel);
		PositionAndSpeedRequest request = PositionAndSpeedRequest
				.newBuilder()
				.setId(12345)
				.build();
		Iterator<PositionAndSpeedResponse> response = stub.positionAndSpeed(request);
		while (response.hasNext())
			System.out.println(response.next());

		channel.shutdownNow();
		SpringApplication.run(DataServerApplication.class, args);
	}

}
