package org.example.backendapi.DAO;

import org.example.backendapi.Dto.Device;

import java.util.List;
import java.util.Optional;

public class DeviceDao implements Dao<Device>{

    @Override
    public Optional<Device> get(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Device> get(String keyWord) {
        return Optional.empty();
    }

    @Override
    public List<Device> getAll() {
        return List.of();
    }

    @Override
    public void save(Device device) {

    }

    @Override
    public void update(Device device, String[] params) {

    }

    @Override
    public void delete(Device device) {

    }
}
