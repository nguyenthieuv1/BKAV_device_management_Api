package org.example.backendapi.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String note;

    @OneToMany(mappedBy = "category")
    private List<DeviceEntity> devides;
}
