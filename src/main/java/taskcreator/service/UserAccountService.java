package taskcreator.service;

import taskcreator.entites.Task;
import taskcreator.entites.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import taskcreator.repository.UserAccountRepository;
import taskcreator.util.HashUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public void save(UserAccount userAccount) {
        HashUtils hashUtils = new HashUtils();

        //Encrypt password
        userAccount.setPassword(hashUtils.sha256(userAccount.getPassword()));

        userAccountRepository.save(userAccount);
    }

    public UserAccount findById(String id) {
        Optional<UserAccount> userAccount = userAccountRepository.findById(id);
        return userAccount.orElse(null);
    }

    public void delete(UserAccount existingUser) {
        userAccountRepository.delete(existingUser);
    }

    public void update(UserAccount existingUser, UserAccount newUserAccount) {
        updateData(existingUser,newUserAccount);
        userAccountRepository.save(existingUser);
    }

    private void updateData(UserAccount existingUser, UserAccount newUserAccount) {
        existingUser.setName(newUserAccount.getName());
        existingUser.setLastname(newUserAccount.getLastname());
        existingUser.setPassword(newUserAccount.getPassword());
    }


}

