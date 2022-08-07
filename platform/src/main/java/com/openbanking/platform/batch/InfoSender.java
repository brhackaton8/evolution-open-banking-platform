package com.openbanking.platform.batch;


import com.openbanking.platform.mail.JavaMailSender;
import com.openbanking.platform.model.AuthEntity;
import com.openbanking.platform.repo.AuthRepo;
import com.openbanking.platform.storage.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class InfoSender {

    private final JavaMailSender javaMailSender;
    private final AuthRepo authRepo;
    static String text1 = "Hörmətli ";
    static String text2 = " bildirmək istərdik ki, sizin kimliyiniz doğrulanıb. Artıq hesabınıza daxil olub əməliyyatlarını asanlıqla edə bilərsiniz. Ümidvarıq ki bizim xidmətimizdən razı qalacaqsınız" ;
    @Scheduled(fixedDelay = 30000)
    public void sendInfo() throws MessagingException, IOException {
        List<AuthEntity> byStatusAndInformationSent = authRepo.findByStatusAndInformationSent(Status.VERIFIED, false);
        for(AuthEntity authEntity : byStatusAndInformationSent){
            javaMailSender.sendmail("shamistan.huseynov@gmail.com","AGILLI BANKÇILIQ", text1 +authEntity.getFullName() + text2);
            log.info("Mail is sent for "+ authEntity.getVideoURL());
            // Flag set to true for info sent
            authEntity.setInformationSent(true);
            authRepo.save(authEntity);
        }
    }

}
