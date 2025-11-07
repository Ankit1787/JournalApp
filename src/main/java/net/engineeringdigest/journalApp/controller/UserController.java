package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.UserEntry;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntry> getUser(@PathVariable ObjectId id){
        return userService.getUserById(id);
    }
    @PostMapping("/add-journal/{id}")
    public ResponseEntity<Map<String,Object>> addEntryToUser(@PathVariable ObjectId id,@RequestBody JournalEntry journalEntry){
        return userService.addJournalEntryForUser(id,journalEntry);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createUser(@RequestBody UserEntry userEntry){
        return userService.saveEntry(userEntry);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String,Object>> updateUser(@PathVariable ObjectId id, @RequestBody UserEntry userEntry){
        return userService.updateById(id,userEntry);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable ObjectId id){
        return userService.deleteById(id);
    }

}
