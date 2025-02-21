package com.crm2.dao;

import com.crm2.bean.Customer;
import com.crm2.util.Dbs;
import java.util.List;

public class CustomerDao {
    public List<Customer> list() {
        String sql = "SELECT name, age, height FROM customer";
        return Dbs.query(sql, (rs, row) -> {
            String name = rs.getString("name");
            Integer age = rs.getInt("age");
            Double height = rs.getDouble("height");
            Customer customer = new Customer(name, age, height);
            return customer;
        });
    }

    public boolean save(Customer customer) {
        String sql = "INSERT INTO customer (name, age, height) VALUES (?, ?, ?)";
        return Dbs.save(sql, customer.getName(), customer.getAge(), customer.getHeight());
    }
}
