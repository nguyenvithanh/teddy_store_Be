package com.backend.services.servicesImpl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.Account;
import com.backend.repository.AccountRepository;
import com.backend.services.AccountService;
import com.backend.util.RandomUtil;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage; 

@Service 
public class AccountImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JavaMailSender mailSender;
    private Random random = new Random();

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();

    }
    @Override
    public boolean authenticateAcc(String username, String password) {
        Account acc = accountRepository.findByUsername(username);
        return acc != null && acc.getPassword().equals(password);
    }

    @Override
    public Account getInfoByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
    

    @Override
    public Account loginWithFacebook(Account acc) {
        return accountRepository.save(acc);
    }
    @Override
    public boolean isExistId(String id) {
        return accountRepository
                .findById(id)
                .isPresent();
    }

    @Override
    public boolean isExistUsername(String username) {
        return accountRepository.findByUsername(username) != null;
    }

    @Override
    @Transactional
    public boolean registerAccount(Account acc) {
        var lastAcc = accountRepository.findLastAccount();
        if(lastAcc.isPresent()){
            acc.setId(RandomUtil.getNextId(lastAcc.get().getId(), "AC"));
        }else {
            acc.setId(RandomUtil.getNextId(null, "AC"));
        }
        accountRepository.save(acc);
        return true;
    }

    @Override
    public String resetPassword(String email) {
        // TODO Auto-generated method stub
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            var newPassword = RandomUtil.generateRandomString(6);
            helper.setFrom("thienhuy232003@gmail.com");
            helper.setTo(email);
            helper.setSubject("RESET PASSWORD TEDDY-STORE");
            helper.setText("Mật khẩu mới của bạn là: " + newPassword, true);
            mailSender.send(mimeMessage);
            var acc = accountRepository.findByEmail(email);
            acc.ifPresent(account -> {
                account.setPassword(newPassword);
                accountRepository.save(account);
            });
            return newPassword;
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email");
        }
    }

}
