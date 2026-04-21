package com.journal.journal_app.repository;

import com.journal.journal_app.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

// MongoRepository<YourModel, IDType> — gives you CRUD for free!
// Like mongoose's Model.find(), Model.findById(), Model.save() etc.
public interface JournalEntryRepo extends MongoRepository<JournalEntity, ObjectId>{
}
