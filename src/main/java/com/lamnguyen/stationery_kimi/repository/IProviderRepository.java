package com.lamnguyen.stationery_kimi.repository;

import com.lamnguyen.stationery_kimi.entity.Provider;
import com.lamnguyen.stationery_kimi.repository.custom.IProviderRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProviderRepository extends JpaRepository<Provider, Long>, IProviderRepositoryCustom {
}
