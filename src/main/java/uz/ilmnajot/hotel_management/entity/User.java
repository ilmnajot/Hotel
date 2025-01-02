package uz.ilmnajot.hotel_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.ilmnajot.hotel_management.template.AbsEntity;

import java.util.Collection;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Builder
public class User extends AbsEntity  implements UserDetails { //this class can serve as guest or other users any time.

    private String fName;
    private String lName;
    @Email
    private String email;
    private String password;
    private String phone;
    private String emailCode;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private UserDetail userDetail;

    @OneToOne(cascade = CascadeType.ALL)
    private UserShift userShift;

    @ManyToOne
    private Role role;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
