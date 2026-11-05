package awsyoo.dev.service;

import awsyoo.dev.Group;
import awsyoo.dev.TransactionHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final TransactionHelper transactionHelper;

    public GroupService(TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;
    }

    public Group saveGroup(String number, Long graduationYear) {
        return transactionHelper.executeInTransaction(session -> {
            var group = new Group(number, graduationYear);
            session.persist(group);
            return group;
        });
    }

    public List<Group> getAll() {
        return transactionHelper.executeInTransaction(session -> {
            return session.createQuery("""
                    select g from Group g 
                    left join fetch g.studentList s
                    left join fetch s.profile
                    """, Group.class).list();
        });
    }
}
