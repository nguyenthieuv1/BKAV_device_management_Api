package org.example.backendapi.DAO.impl;

import jakarta.persistence.Entity;
import org.example.backendapi.DAO.DeviceDao;
import org.example.backendapi.Dto.Device;
import org.example.backendapi.Dto.DeviceBorrow;
import org.example.backendapi.Entity.AccountEntity;
import org.example.backendapi.Entity.DeviceEntity;
import org.example.backendapi.repository.AccountRepository;
import org.example.backendapi.repository.DeviceRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DeviceDaoImpl implements DeviceDao{

    private DeviceRepository deviceRepository;
    private AccountRepository accountRepository;

    public DeviceDaoImpl(DeviceRepository deviceRepository, AccountRepository accountRepository) {
        this.deviceRepository = deviceRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Device> get(long id) {
        Optional<DeviceEntity> deviceEntity = deviceRepository.findById(id);
        if(deviceEntity.isPresent()) {
            Device device = deviceEntity.get().toDto();
            return Optional.of(device);
        }
        throw new RuntimeException("device not found!!!!!!!!!!");
    }

    @Override
    public Optional<Device> get(String keyWord) {
        return Optional.empty();
    }

    @Override
    public Optional<Device> get(Device Dto) {
        return Optional.empty();
    }

    @Override
    public List<Device> getAll() {
        return List.of();
    }

    @Override
    public void save(Device device) {
        DeviceEntity deviceEntity = device.toEntity();
        deviceRepository.save(deviceEntity);
    }

    @Override
    public void update(Device device, String[] params) {

    }

    @Override
    public void delete(Device device) {
        Optional<DeviceEntity> deviceEntity = deviceRepository.findById(device.getId());
        if(deviceEntity.isPresent()) {
            deviceRepository.delete(deviceEntity.get());
        }
        else {
            throw new RuntimeException("device not found!!!!!!!!!!");
        }
    }

    @Override
    public void addDeviceBorrowed(DeviceBorrow deviceBorrow) {

        DeviceEntity deviceEntityToBorrow = getDeviceEntity(deviceBorrow.getDeviceId());
        AccountEntity accountEntityBorrow = getAccountEntity(deviceBorrow.getAccountId());
        deviceEntityToBorrow.setAccount(accountEntityBorrow);
        deviceEntityToBorrow.setStatus("0");
        deviceRepository.save(deviceEntityToBorrow);
    }

    public DeviceEntity getDeviceEntity(long id) {
        Optional<DeviceEntity> deviceEntity = deviceRepository.findById(id);
        if(deviceEntity.isPresent()) {
            return deviceEntity.get();
        }
        throw new RuntimeException("device not found!!!!!!!!!!!");
    }
    public AccountEntity getAccountEntity(long id) {
        Optional<AccountEntity> accountEntity = accountRepository.findById(id);
        if(accountEntity.isPresent()) {
            return accountEntity.get();
        }

        throw new RuntimeException("account not found!!!!!!!!!!!");
    }

    @Override
    public List<Device> getDevicesByName(String deviceName) {
        List<DeviceEntity> deviceEntitys = deviceRepository.findByNameLike("%"+deviceName+"%");
        List<Device> devices = new ArrayList<>();
        if(deviceEntitys != null) {
            for(DeviceEntity deviceEntity : deviceEntitys) {
                Device device = deviceEntity.toDto();
                devices.add(device);
            }
            return devices;
        }
        throw new RuntimeException("device not found!!!!!!!!!!");
    }

    public AccountEntity getAdminEntity() {
        return accountRepository.findByRole("ADMIN");
    }

    @Override
    public void DeleteDeviceBorrowed(DeviceBorrow deviceBorrow) {
        DeviceEntity deviceEntity = getDeviceEntity(deviceBorrow.getDeviceId());
        deviceEntity.setStatus("1");
        deviceEntity.setAccount(getAdminEntity());
        deviceRepository.save(deviceEntity);
    }
}
