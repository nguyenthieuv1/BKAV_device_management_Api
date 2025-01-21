package org.example.backendapi.Dto;

import org.example.backendapi.Entity.DeviceEntity;

public class Device {
    private Long id;
    private String name;
    private String note;
    private String description;
    private String status;
    private Long idAccount;


    public DeviceEntity toEntity(){
        DeviceEntity device = new DeviceEntity();
        device.setId(id);
        device.setName(name);
        device.setNote(note);
        device.setDescription(description);
        device.setStatus(status);
        return device;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", idAccount=" + idAccount +
                '}';
    }
}
