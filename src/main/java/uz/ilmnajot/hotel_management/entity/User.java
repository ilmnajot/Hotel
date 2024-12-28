package uz.ilmnajot.hotel_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import uz.ilmnajot.hotel_management.template.AbsEntity;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User extends AbsEntity { //this class can serve as guest or other users any time.

    private String fName;
    private String lName;
    @Email
    private String email;
    private String phone;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private UserDetails userDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private UserShift userShift;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


}
