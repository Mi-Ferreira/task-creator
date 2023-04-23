package taskcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskcreator.entites.UserAccount;
import taskcreator.service.UserAccountService;


@RestController
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @RequestMapping(value = "/taskcreator/useraccounts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        UserAccount existingUser = userAccountService.findById(id);

        if (existingUser.equals(null)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Account no exists");
        }
        userAccountService.delete(existingUser);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Account delete successfully");
    }


    @RequestMapping(value = "/taskcreator/useraccounts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") String id,@RequestBody UserAccount newUserAccount) {
        UserAccount existingUser = userAccountService.findById(id);

        if (existingUser.equals(null)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email Account no exists");
        }
        userAccountService.update(existingUser,newUserAccount);
        return ResponseEntity.ok().body("User Account update successfully");
    }


}
