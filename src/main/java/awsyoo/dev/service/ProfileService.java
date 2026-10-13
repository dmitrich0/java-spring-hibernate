package awsyoo.dev.service;

import awsyoo.dev.Profile;
import awsyoo.dev.TransactionHelper;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final TransactionHelper transactionHelper;

    public ProfileService(TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;
    }

    public Profile saveProfile(Profile profile) {
        return this.transactionHelper.executeInTransaction(session -> {
            session.persist(profile);
            return profile;
        });
    }
}
