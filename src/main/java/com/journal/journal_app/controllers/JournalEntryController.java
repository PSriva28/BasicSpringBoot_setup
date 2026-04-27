package com.journal.journal_app.controllers;

import com.journal.journal_app.entity.JournalEntity;
import com.journal.journal_app.services.JournalEntryServices;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController //--> Equivalent to express.Router() --> handling all http requests
@RequestMapping("/journal")
//@ResponseEntity //--->For Handling http codes and resp.
public class JournalEntryController {

    @Autowired //---> usage for dependency Injection mtlb hme objects create karne ki jarurt nhi, sb hme lake de
    private JournalEntryServices service;

    @GetMapping //---> router.get() in mern
    public ResponseEntity<List<JournalEntity>> getAllEntries(){
//        return service.getAllData();//getAllData method is coming from the logic written in services
        List<JournalEntity> entries = service.getAllData();
        if(entries == null || entries.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // OR ResponseEntity.no_content().build();
        }
        return new ResponseEntity<>(entries, HttpStatus.OK); // or ResponseEntity.ok(entries)

    }
    @PostMapping //---> router.post() & @RequestBody --> req.body
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity entry)
    {
        try{
            JournalEntity body = service.postData(entry);
            if(body != null){
                return new ResponseEntity<>(body, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping("/{id}") //---> router.get() through specific id, && @PathVariable --> req.params.id
    public ResponseEntity<JournalEntity> getEntry(@PathVariable ObjectId id) {
//        return service.getData(id).orElse(null);
        Optional<JournalEntity> entry = service.getData(id);
        if(entry.isPresent()){
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}") //---> router.put() updating any existing entry
    public ResponseEntity<JournalEntity> updateEntries(@PathVariable ObjectId id, @RequestBody JournalEntity newEntry){
//        return service.updateData(id, newEntry);
        Optional<JournalEntity> existing = service.getData(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();  // 404 — nothing to update
        }
        JournalEntity old = existing.get();
        // Only overwrite a field if the new request actually sent it
        // If the field is missing/null in the request → keep the old value
        if (newEntry.getTitle() != null && !newEntry.getTitle().isBlank()) {
            old.setTitle(newEntry.getTitle());
        }
        // else → title stays as old.getTitle() automatically ✅
        if (newEntry.getContent() != null && !newEntry.getContent().isBlank()) {
            old.setContent(newEntry.getContent());
        }

        JournalEntity updated = service.updateData(id, old);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}") //---> router.delete(), for deleting entry
    public ResponseEntity<Void> deleteEntry(@PathVariable ObjectId id){
//        return service.deleteData(id);
        boolean deleteData = service.deleteData(id);
        if(!deleteData){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);// WE ARE USING 404, BECAUSE IF IT IS NOT DELETED THEN IT WILL NOT BE PRESENT OF THAT ID.
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // WE ARE USING NO CONTENT, BECAUSE WHEN DELETING, IT SHOULD NOT RETURN ANY CONTENT.
    }

}
