package org.example.backendapi.repository;

import org.example.backendapi.Entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
}
