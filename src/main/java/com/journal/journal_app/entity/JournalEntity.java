package com.journal.journal_app.entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

//@Entity
@Document(collection = "journal_entries_sb")
public class JournalEntity {

    private ObjectId id;

    @Size(min = 5, max = 20, message = "The title should be between 5 - 20 characters.")
    @NotBlank(message="Please enter title of the journal.")
    private String title;

    @NotBlank(message= "Please enter content field.")
    private String content;
    private LocalDateTime date;

    @Id
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
