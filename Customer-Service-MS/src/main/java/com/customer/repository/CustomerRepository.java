package com.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	 @Query("SELECT c FROM Customer c WHERE c.email LIKE %:domain")
	  List<Customer> findCustomersByEmailDomain(@Param("domain") String domain);
	 
	  List<Customer> findByName(String name);

}
