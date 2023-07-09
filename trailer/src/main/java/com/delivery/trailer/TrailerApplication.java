package com.delivery.trailer;

import com.delivery.trailer.model.Trailer;
import com.delivery.trailer.service.DeliveryServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TrailerApplication {
	public static Map<Long, Trailer> trailerMap;
	private static Double startPointLatitude = 57.626933;
	private static Double startPointLongitude = 39.848964;

	private static class MovingTrucks implements Runnable {
		@Override
		public void run() {
			while (true) {
				for (Trailer trailer : trailerMap.values()) {
					trailer.setSpeed(trailer.getSpeed() + 1);
					trailer.setLongitude(trailer.getLongitude() + Math.random());
					System.out.println(trailer);
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		//simulation of trailer movement
		initTrailers();
		goToTheWay();
		Server server = ServerBuilder.forPort(8081)
				.addService(new DeliveryServiceImpl())
				.build();
		server.start();
		System.out.println("Server started");
		server.awaitTermination();
	}

	private static void initTrailers() {
		trailerMap = new HashMap<>();
		for (int i = 1; i <10; i++) {
			double random = Math.random() * 10;
			double currentPointLatitude = startPointLatitude + random;
			double currentPointLongitude = startPointLongitude + random;
			trailerMap.put((long) i, new Trailer((long) i, currentPointLatitude, currentPointLongitude));
		}
	}

	private static void goToTheWay() {
		new Thread(new MovingTrucks()).start();
	}
}
