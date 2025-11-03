package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.UserEntry;
import net.engineeringdigest.journalApp.repository.JournalRepository;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JournalRepository journalRepository;

    public ResponseEntity<UserEntry> getUserById(ObjectId id){
        try {
            UserEntry userEntry = userRepo.findById(id).orElse(null);
            if(userEntry!=null){
                return new ResponseEntity<UserEntry>(userEntry, HttpStatus.FOUND);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<String> deleteById(ObjectId id){
        try {
            userRepo.deleteById(id);
            return new ResponseEntity<>("result deleted successfuly", HttpStatus.OK);

        }
        catch (Exception e){
            return  new ResponseEntity<>("error deleting user",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map <String,Object>> updateById(ObjectId id,UserEntry newEntry){
        Map<String,Object> response = new HashMap<>();
        try {

            if(newEntry.getUserName()==null||newEntry.getUserName().isEmpty()){
                response.put("message","username required");
                response.put("success",false);

                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }

            if(newEntry.getPassword()==null|| newEntry.getPassword().isEmpty()){
                response.put("message","username required");
                response.put("success",false);

                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
            UserEntry old = userRepo.findById(id).orElse(null);
            if(old!=null){
                old.setUserName(newEntry.getUserName());
                old.setPassword(newEntry.getPassword());
                response.put("message","user created");
                response.put("success",true);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            response.put("message","user not created");
            response.put("success",false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
        catch (Exception e){
            response.put("message","user not created");
            response.put("success",false);
            return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<UserEntry> saveEntry(UserEntry newEntry){
        try{

            if(newEntry.getUserName().isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            if(newEntry.getPassword().isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

           UserEntry userEntry = userRepo.save(newEntry);
            return new ResponseEntity<UserEntry>(userEntry,HttpStatus.CREATED);

        }
        catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Map<String, Object>> addJournalEntryForUser(
            ObjectId userId,
             JournalEntry newEntry) {
        Map<String, Object> response = new HashMap<>();

        try {

            UserEntry user = userRepo.findById(userId).orElse(null);
            if (user == null) {
                response.put("success", false);
                response.put("message", "User not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Save journal entry first
            JournalEntry savedEntry = journalRepository.save(newEntry);

            // Link journal entry to user
            user.getJournalEntry().add(savedEntry);
            userRepo.save(user);

            response.put("success", true);
            response.put("message", "Journal entry added successfully");
            response.put("data", savedEntry);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            response.put("success", false);
            response.put("message", "User not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }


    }

}
