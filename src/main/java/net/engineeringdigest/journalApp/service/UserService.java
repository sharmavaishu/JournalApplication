package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

     @Autowired
     private UserRepository userRepository;

     private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


     public void saveUser(User user){
         userRepository.save(user);
     }


    public void saveNewUser(User user){
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         user.setRoles(Arrays.asList("USER"));
         userRepository.save(user);
    }


     public List<User> getAllUsers(){
         return userRepository.findAll();
     }


    public void deleteUserById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findUserByName(String username){
         return userRepository.findByUserName(username);
    }

    public void saveAdminUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }
}
