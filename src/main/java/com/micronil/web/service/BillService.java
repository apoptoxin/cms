package com.micronil.web.service;

import com.micronil.web.entity.Bill;
import com.micronil.web.entity.User;
import com.micronil.web.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by apoptoxin on 2018/3/29.
 */
@Service
public class BillService {

    private static final int pageSize = 10;
    @Autowired
    private BillRepository billRepository;

    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    public List<Bill> findAllByUser(User user) {
        return billRepository.findAllByUser(user);
    }
    public List<Bill> findAllByUserAndTime(User user,Date start, Date end) {
        return billRepository.findAllByUserAndCreateTimeBetween(user, start, end);
    }

    public List<Bill> findAllBillsByPage(int page) {
        return billRepository.findAllBillsByIdBetween(new Long(page * pageSize),new Long((page+1) * pageSize));
    }

    public List<Bill> findAllBillsByPageAndTime(int page, Date startTime, Date endTime) {
        return billRepository.findAllBillsByIdBetweenAndCreateTimeBetween(new Long(page * pageSize),new Long((page + 1) * pageSize),startTime, endTime);
    }

    public List<Bill> findAllBillsByUserIdAndPage(Long userId, int page) {
        return billRepository.findAllBillsByUserIdAndIdBetween(userId,new Long(page * pageSize), new Long((page + 1) * pageSize));
    }

    public List<Bill> findAllBillsByUserIdAndPageAndTime(Long userId, int page, Date startTime, Date endTime) {
        return billRepository.findAllBillsByUserIdAndIdBetweenAndCreateTimeBetween(userId,new Long(page * pageSize) , new Long((page + 1) * pageSize), startTime, endTime);
    }
}
