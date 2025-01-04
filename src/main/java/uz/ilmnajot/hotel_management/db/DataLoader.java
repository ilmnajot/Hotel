package uz.ilmnajot.hotel_management.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.ilmnajot.hotel_management.entity.Role;
import uz.ilmnajot.hotel_management.entity.User;
import uz.ilmnajot.hotel_management.entity.UserDetail;
import uz.ilmnajot.hotel_management.entity.UserShift;
import uz.ilmnajot.hotel_management.enums.DetailsStatus;
import uz.ilmnajot.hotel_management.enums.RoleType;
import uz.ilmnajot.hotel_management.repository.RoleRepository;
import uz.ilmnajot.hotel_management.repository.UserRepository;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${spring.sql.init.mode}")
    private String initMode;
    private static final String ALWAYS = "always";
    private static final String ADMIN_PHONE = "+9989999999";
    private static final String OWNER_PHONE = "+9989999999";
    private static final String USER_PHONE = "+9989999999";

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals(ALWAYS)) {


            UserDetail userDetail = UserDetail
                    .builder()
                    .detailsStatus(DetailsStatus.PASSPORT)
                    .documentNo("user7456885")
                    .details("user")
                    .build();

            UserDetail adminDetail = UserDetail
                    .builder()
                    .detailsStatus(DetailsStatus.PASSPORT)
                    .documentNo("admin7456885")
                    .details("admin details")
                    .build();

            UserDetail ownerDetail = UserDetail
                    .builder()
                    .detailsStatus(DetailsStatus.PASSPORT)
                    .documentNo("owner7456885")
                    .details("owner details")
                    .build();


            Role ownerRole = Role
                    .builder()
                    .name(RoleType.OWNER.name())
                    .roleType(RoleType.OWNER)
                    .build();
            roleRepository.save(ownerRole);

            Role adminRole = Role
                    .builder()
                    .name(RoleType.ADMIN.name())
                    .roleType(RoleType.ADMIN)
                    .build();
            roleRepository.save(adminRole);

            Role userRole = Role
                    .builder()
                    .name(RoleType.USER.name())
                    .roleType(RoleType.USER)
                    .build();
            roleRepository.save(userRole);

            User owner = User
                    .builder()
                    .fName("owner")
                    .lName("owner")
                    .email("owner@gmail.com")
                    .password(passwordEncoder.encode("owner"))
                    .phone(OWNER_PHONE)
                    .role(ownerRole)
                    .userDetail(ownerDetail)
                    .enabled(true)
                    .build();
            userRepository.save(owner);

            User admin = User
                    .builder()
                    .fName("admin")
                    .lName("admin")
                    .email("admin@gmail.com")
                    .password(passwordEncoder.encode("admin"))
                    .phone(ADMIN_PHONE)
                    .role(adminRole)
                    .userDetail(adminDetail)
                    .enabled(true)
                    .build();
            userRepository.save(admin);

            User user = User
                    .builder()
                    .fName("user")
                    .lName("user")
                    .email("user@gmail.com")
                    .password(passwordEncoder.encode("user"))
                    .phone(USER_PHONE)
                    .role(userRole)
                    .enabled(true)
                    .userDetail(userDetail)
                    .build();
            userRepository.save(user);


        }
    }
}

