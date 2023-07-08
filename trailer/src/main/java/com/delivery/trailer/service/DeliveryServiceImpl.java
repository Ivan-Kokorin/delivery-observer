package com.delivery.trailer.service;

import com.delivery.grpc.DeliveryServiceGrpc;
import com.delivery.grpc.PositionAndSpeedRequest;
import com.delivery.grpc.PositionAndSpeedResponse;
import io.grpc.stub.StreamObserver;

public class DeliveryServiceImpl extends DeliveryServiceGrpc.DeliveryServiceImplBase {
    private Double currentPoint = 57.626933;
    @Override
    public void positionAndSpeed(PositionAndSpeedRequest request, StreamObserver<PositionAndSpeedResponse> responseObserver) {
        for (int i = 0; i < 100; i++) {
            currentPoint += 0.001;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            PositionAndSpeedResponse response = PositionAndSpeedResponse
                    .newBuilder()
//                    .setGreeting("Hello from server, " + request.getName())
                    .setLatitude(currentPoint)
                    .setLongitude(39.848964)
                    .setSpeed(50)
                    .build();
            responseObserver.onNext(response);
        }

        responseObserver.onCompleted();
    }
}
