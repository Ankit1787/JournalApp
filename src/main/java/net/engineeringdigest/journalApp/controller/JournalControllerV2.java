package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/journalv2") //

public class JournalControllerV2 {
//    private Map<Long, JournalEntry> journalEntry = new HashMap<>();

    @GetMapping
    public boolean getList(){

//return new ArrayList<>(journalEntry.values() );
        return  true;
    }
    @PostMapping("/add")
    public boolean  addList(@RequestBody JournalEntry myjournal){
//        journalEntry.put(myjournal.getId(),myjournal);
      return  false;
    }
    @GetMapping("/get/{id}")
    public boolean getListByIdt(@PathVariable String id){
//       return journalEntry.get(id);
        return  false;
    }
    @GetMapping("/get")
    public boolean getListById(@RequestParam String id){
//       return journalEntry.get(id);
        return false;
    }
}
