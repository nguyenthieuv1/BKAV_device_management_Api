package org.example.backendapi.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_device")
@Data
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String note;
    private String description;
    private String status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

}
