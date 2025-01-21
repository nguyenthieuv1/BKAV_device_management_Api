package org.example.backendapi.repository;

import org.example.backendapi.Entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
    List<DeviceEntity> findByNameLike(String name);
}
