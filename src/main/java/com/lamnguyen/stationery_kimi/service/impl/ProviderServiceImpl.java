package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.dto.ProviderDTO;
import com.lamnguyen.stationery_kimi.entity.Provider;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.repository.IProviderRepository;
import com.lamnguyen.stationery_kimi.service.IProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements IProviderService {
    @Autowired
    private IProviderRepository providerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProviderDTO addProvider(Provider provider) {
        Provider result = providerRepository.saveAndFlush(provider);
        return convertToDTO(result);
    }

    @Override
    public ProviderDTO updateProvider(Provider provider) {
        if (provider.getId() == null) throw new ApplicationException(ErrorCode.NULL_ID_PROVIDER);
        Provider result = providerRepository.saveAndFlush(provider);
        return convertToDTO(result);
    }

    @Override
    public ProviderDTO lockProvider(Provider provider) {
        if (provider.getId() == null) throw new ApplicationException(ErrorCode.NULL_ID_PROVIDER);
        if (provider.getLock() == null)
            provider.setLock(true);
        providerRepository.lockProvider(provider.getId(), provider.getLock());
        return convertToDTO(provider);
    }

    @Override
    public List<ProviderDTO> findAll(Integer page) {
        List<Provider> result = providerRepository.findAll(Pageable.ofSize(20).withPage(page)).getContent();
        if (result.isEmpty()) throw new ApplicationException(ErrorCode.PROVIDER_NOT_FOUND);
        return convertToDTOList(result);
    }

    @Override
    public List<ProviderDTO> findAllByLockFalse(Integer page) {
        List<Provider> result = providerRepository.findAllByLockFalse(Pageable.ofSize(20).withPage(page)).getContent();
        if (result.isEmpty()) throw new ApplicationException(ErrorCode.PROVIDER_NOT_FOUND);
        return convertToDTOList(result);
    }

    private ProviderDTO convertToDTO(Provider provider) {
        return modelMapper.map(provider, ProviderDTO.class);
    }

    private List<ProviderDTO> convertToDTOList(List<Provider> providers) {
        return providers.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
