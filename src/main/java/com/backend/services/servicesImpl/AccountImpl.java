package com.backend.services.servicesImpl;

import java.util.*;

import com.backend.dto.AccountInfoDTO;
import com.backend.dto.CustomerDto;
import com.backend.dto.PaginateDTO;
import com.backend.dto.mapper.AccountInfoMapper;
import com.backend.dto.mapper.AccountMapper;
import com.backend.model.AccountInfo;
import com.backend.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();

    }
    @Override
    public boolean authenticateAcc(String username, String password) {
        var acc = accountRepository.findByUsername(username);
        return acc.isPresent() && acc.get().getPassword().equals(password);
    }

    @Override
    public Account getInfoByUsername(String username) {
        return accountRepository.findByUsername(username).orElse(null);
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
        return accountRepository.findByUsername(username).isPresent();
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
            final String[] newPassword = new String[1];
            newPassword[0] = RandomUtil.generateRandomString(6);

            var acc = accountRepository.findByEmail(email);
            acc.ifPresentOrElse(account -> {
                account.setPassword(encoder.encode(newPassword[0]));
                accountRepository.save(account);
            }, () -> {
                newPassword[0] = "NOT_FOUND_EMAIL";
            });
            if("NOT_FOUND_EMAIL".equals(newPassword[0])) {
                return "NOT_FOUND_EMAIL";
            }

            helper.setTo(email);
            helper.setFrom("TEDDY-STORE");
            helper.setSubject("RESET PASSWORD TEDDY-STORE");
            helper.setText("Mật khẩu mới của bạn là: " + newPassword[0], true);
            mailSender.send(mimeMessage);
            return newPassword[0];
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email");
        }
    }

    @Override
    public PaginateDTO<List<CustomerDto>> getCustomerSearchAndPagination(String query, Boolean active, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Account> page = accountRepository.getAccountSearchAndPagination(query,active,pageable);
        List<Account> accounts = page.getContent();
        List<CustomerDto> customers = new ArrayList<>();

        for (Account account : accounts) {
            Iterator iterator1 = account.getAccountInfo().iterator();
            Iterator iterator2 = account.getAddress().iterator();
            AccountInfoDTO accountInfoDTO = null;
            if (iterator1.hasNext()) {
                AccountInfo accountInfo = (AccountInfo) iterator1.next();
                if (iterator2.hasNext()) {
                    Address address = (Address) iterator2.next();
                    accountInfoDTO = AccountInfoMapper.modelToDto(accountInfo,address.getSub_address());
                } else {
                    accountInfoDTO = AccountInfoMapper.modelToDto(accountInfo,"");
                }
            }
            customers.add(AccountMapper.toCustomerDto(account,accountInfoDTO));
        }

        PaginateDTO<List<CustomerDto>> dataResponse = new PaginateDTO<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                customers
        );

        return dataResponse;
    }

    @Override
    public boolean updateActiveCustomer(String id, Boolean active) {
        Account account = accountRepository.findById(id).orElseGet(null);
        if (account != null) {
            account.setActive(active);
            Account accountSave = accountRepository.save(account);
            return accountSave != null;
        }
        return false;
    }

    @Override
	public Object getInfoByUsernameV2(String username) {
		// TODO Auto-generated method stub
		return accountRepository.getByUsername(username).orElse(null);
	}

}
