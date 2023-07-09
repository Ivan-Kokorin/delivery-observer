package com.delivery.trailer.service;

import com.delivery.grpc.DeliveryServiceGrpc;
import com.delivery.grpc.PositionAndSpeedRequest;
import com.delivery.grpc.PositionAndSpeedResponse;
import com.delivery.trailer.TrailerApplication;
import com.delivery.trailer.model.Trailer;
import io.grpc.stub.StreamObserver;

public class DeliveryServiceImpl extends DeliveryServiceGrpc.DeliveryServiceImplBase {

    @Override
    public void positionAndSpeed(PositionAndSpeedRequest request, StreamObserver<PositionAndSpeedResponse> responseObserver) {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            long id = request.getId();
            Trailer trailer = TrailerApplication.trailerMap.get(id);
            PositionAndSpeedResponse response = PositionAndSpeedResponse
                    .newBuilder()
                    .setLatitude(trailer.getLatitude())
                    .setLongitude(trailer.getLongitude())
                    .setSpeed(trailer.getSpeed())
                    .build();
            responseObserver.onNext(response);
        }

        responseObserver.onCompleted();
    }
}
