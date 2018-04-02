package com.micronil.web.repository;

import com.micronil.web.entity.Bill;
import com.micronil.web.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by apoptoxin on 2018/3/29.
 */
public interface BillRepository extends CrudRepository<Bill,Long> {
//    public List<Bill> findAllByCreateTimeBetween(Date start,Date end);
//    public List<Bill> findAllByUserIdCreateTimeBetween(Long userId,Date start,Date end);
    public List<Bill> findAllByUser(User user);
    public List<Bill> findAllByUserAndCreateTimeBetween(User user,Date start,Date end);
    public List<Bill> findAllBillsByIdBetween(Long start, Long end);

    public List<Bill> findAllBillsByIdBetweenAndCreateTimeBetween(Long start, Long end, Date startTime, Date endTime);

    public List<Bill> findAllBillsByUserIdAndIdBetween(Long userId,Long start, Long end);

    public List<Bill> findAllBillsByUserIdAndIdBetweenAndCreateTimeBetween(Long userId, Long start, Long end, Date startTime, Date endTime);
}
