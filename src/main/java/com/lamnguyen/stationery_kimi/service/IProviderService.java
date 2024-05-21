package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.ProviderDTO;
import com.lamnguyen.stationery_kimi.entity.Provider;

import java.util.List;

public interface IProviderService {
    ProviderDTO addProvider(Provider provider);

    ProviderDTO updateProvider(Provider provider);

    ProviderDTO lockProvider(Provider provider);

    List<ProviderDTO> findAll(Integer page);

    List<ProviderDTO> findAllByLockFalse(Integer page);
}
