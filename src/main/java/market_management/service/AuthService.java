package market_management.service;

import market_management.dto.LoginDTO;
import market_management.dto.UserDTO;
import market_management.entity.User;
import market_management.enums.UserRole;
import market_management.repository.UserRepository;
import market_management.utils.Util;

import java.util.Optional;
import java.util.UUID;

public class AuthService {

    private  final  UserRepository userRepository=UserRepository.getInstance();

    public static AuthService authService;
    private  AuthService(){}
    public static AuthService getInstance(){
        if (authService==null){
            authService=new AuthService();
        }
        return authService;
    }

    public boolean registration(UserDTO dto) {
        Optional<User> optional = userRepository.getUserByEmail(dto.email());
        if (optional.isPresent()) return false;

        User user =new User(UUID.randomUUID().toString(), dto.fullName(), dto.email(), dto.password(), UserRole.CUSTOMER);
        userRepository.saveUser(user);
        return  true;
    }


    public Optional<UserRole> loogin(LoginDTO dto) {
        Optional<User> optional = userRepository.getUserByEmail(dto.email());
        if (optional.isEmpty() ) return Optional.empty();
        User user = optional.get();
        if ( !(user.getPassword().equals(dto.password())) ) return Optional.empty();

        Util.currentUserId=user.getId();
        return Optional.of(user.getRole());
    }
}
