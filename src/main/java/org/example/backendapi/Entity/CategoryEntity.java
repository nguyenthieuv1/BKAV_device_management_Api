package org.example.backendapi.Entity;

import jakarta.persistence.*;
import org.example.backendapi.constants.NameTableConstant;

import java.util.List;

@Entity
@Table(name = NameTableConstant.CATEGORY_TABLE)
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String note;

    @OneToMany(mappedBy = "category")
    private List<DeviceEntity> devides;
}
