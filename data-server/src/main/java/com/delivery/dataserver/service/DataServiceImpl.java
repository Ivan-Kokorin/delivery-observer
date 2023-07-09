package com.delivery.dataserver.service;

import com.delivery.dataserver.model.DataTrailer;
import com.delivery.grpc.DeliveryServiceGrpc;
import com.delivery.grpc.PositionAndSpeedRequest;
import com.delivery.grpc.PositionAndSpeedResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class DataServiceImpl implements DataService{
    @Override
    public DataTrailer findDataById(Long id) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8081")
                .usePlaintext()
                .build();
        DeliveryServiceGrpc.DeliveryServiceBlockingStub stub = DeliveryServiceGrpc.newBlockingStub(channel);
        PositionAndSpeedRequest request = PositionAndSpeedRequest
                .newBuilder()
                .setId(id)
                .build();
        Iterator<PositionAndSpeedResponse> response = stub.positionAndSpeed(request);
        DataTrailer dataTrailer = null;
        while (response.hasNext()){
            PositionAndSpeedResponse resp = response.next();
            System.out.println(resp);
            dataTrailer = new DataTrailer(id, resp.getLatitude(), resp.getLongitude(), resp.getSpeed());
        }
        channel.shutdownNow();
        return dataTrailer;
    }
}
