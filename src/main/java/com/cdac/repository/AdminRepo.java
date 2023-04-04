package com.cdac.repository;

import org.springframework.data.repository.CrudRepository;

import com.cdac.Entity.AdminClass;

public interface AdminRepo extends CrudRepository<AdminClass, Integer>{

}
