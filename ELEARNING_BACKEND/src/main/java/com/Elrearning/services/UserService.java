package com.Elrearning.services;

import com.Elrearning.models.RegistrationDTO;
import com.Elrearning.models.Role;
import com.Elrearning.models.User;
import com.Elrearning.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Elrearning.repository.UserRepository;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
       private RoleRepository roleRepository ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {



        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
    }
public List<User> getallusers() {

return userRepository.findAll();
}
public boolean existbyusername (String username) {

        return userRepository.existsByUsername(username);
}
public  void deleteuser (Integer id ){

        userRepository.deleteById(id);
}

public void addteacher (RegistrationDTO user) {
    Role userRole = roleRepository.findByAuthority("USER").get();

    Set<Role> authorities = new HashSet<>();

    authorities.add(userRole);
    User teacher = new User(0, user.getUsername(),encoder.encode(user.getPassword()) ,user.getEmail(),"TEACHER",authorities);
       userRepository.save(teacher);

}

    public boolean exist(Integer id) {

        return userRepository.existsById(id);
    }
    public List<User> getallteachers () {

        return userRepository.findAllByUsertype("TEACHER") ;
    }
    public String updateuser (User user , int id ){
        Optional<User> currentuser = userRepository.findById(id) ;
        if( currentuser.isPresent()) {
           if (!Objects.equals(user.getEmail(), currentuser.get().getEmail())){
               if (!userRepository.existsByEmail(user.getEmail())){

                   currentuser.get().setEmail(user.getEmail());
               } else return  ("EMAIL ALREADY TAKEN");

           } else if (!Objects.equals(user.getUsername(), currentuser.get().getUsername())) {
               if (!userRepository.existsByUsername(user.getUsername())){

                   currentuser.get().setUsername(user.getUsername());
               } else return  ("USERNAME ALREADY TAKEN");
           }

            userRepository.saveAndFlush(currentuser.get());
        }else return ("USER NOT FOUND") ;

        return ("UPDATED");
    }
}
