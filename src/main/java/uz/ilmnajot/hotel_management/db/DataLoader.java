package uz.ilmnajot.hotel_management.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.ilmnajot.hotel_management.entity.Role;
import uz.ilmnajot.hotel_management.entity.User;
import uz.ilmnajot.hotel_management.entity.UserDetail;
import uz.ilmnajot.hotel_management.entity.UserShift;
import uz.ilmnajot.hotel_management.enums.DetailsStatus;
import uz.ilmnajot.hotel_management.enums.RoleType;
import uz.ilmnajot.hotel_management.repository.RoleRepository;
import uz.ilmnajot.hotel_management.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    @Value("${spring.sql.init.mode}")
    private String initMode;
    private static final String ALWAYS = "always";
    private static final String ADMIN = "ADMIN";
    private static final String MANAGER = "MANAGER";
    private static final String USER = "USER";

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals(ALWAYS)){
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
                    .phone("+998994107354")
                    .role(ownerRole)
                    .build();
            userRepository.save(owner);

            User admin = User
                    .builder()
                    .fName("admin")
                    .lName("admin")
                    .email("admin@gmail.com")
                    .phone("+998994107354")
                    .role(adminRole)
                    .build();
            userRepository.save(admin);

            User user = User
                    .builder()
                    .fName("user")
                    .lName("user")
                    .email("user@gmail.com")
                    .phone("+998994107354")
                    .role(userRole)
                    .build();
            userRepository.save(user);


            UserDetail
                    .builder()
                    .user(owner)
                    .detailsStatus(DetailsStatus.PASSPORT)
                    .passportNumber("aa7456885")
                    .expirationDate(null)
                    .details("nothing")
                    .build();

            UserShift
                    .builder()
                    .name("schedule")
                    .startDate(null)
                    .description("description")
                    .user(owner)
                    .build();

        }
    }
}
