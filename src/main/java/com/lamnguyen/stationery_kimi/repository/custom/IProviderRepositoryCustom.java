package com.lamnguyen.stationery_kimi.repository.custom;

import com.lamnguyen.stationery_kimi.entity.Provider;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProviderRepositoryCustom {

    @Modifying
    @Transactional
    @Query("UPDATE Provider p SET p.lock = :lock WHERE p.id = :id")
    Integer lockProvider(@Param("id") Long id, @Param("lock") Boolean lock);
}
