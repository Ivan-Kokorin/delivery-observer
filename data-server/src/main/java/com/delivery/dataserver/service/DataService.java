package com.delivery.dataserver.service;

import com.delivery.dataserver.model.DataTrailer;

public interface DataService {
    public DataTrailer findDataById(Long id);
}
