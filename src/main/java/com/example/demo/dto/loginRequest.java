package com.example.demo.dto;


//dto is a simple object used to transfer data between service layer and model 
// it only carries data 
//it does not process data
//it controls what data is sent or received
// dto sends only needed fields
// changes is database won't break api responses because sto stays stable
//for security it is used  as  our database contains password so
import lombok.Data;

@Data
public class loginRequest {
    private String email;
    private String password;
}