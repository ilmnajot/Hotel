package uz.ilmnajot.hotel_management.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.hotel_management.enums.RoleType;
import uz.ilmnajot.hotel_management.template.AbsEntity;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Role extends AbsEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;


}
