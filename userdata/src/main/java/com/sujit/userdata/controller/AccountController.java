package com.sujit.userdata.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sujit.userdata.dao.AccountRepository;
import com.sujit.userdata.model.Account;

@RestController
@RequestMapping("/info")
public class AccountController {
	@Autowired
	private AccountRepository repository;
	
	@PostMapping(value="/createAccount")
	public String create(@RequestBody Account account) {
		
		repository.save(account);
		return "Account Created with id :"+ account.getId();
	
		
	}
	@GetMapping("/getAccount/{id}/{owner}")
	public Account getAccount(@PathVariable int id,@PathVariable int owner){
		Optional<Account> account = repository.findById(id);
		if(account.isPresent() && account.get().getOwner()==owner) {
			return account.get();
		}
		else {
			throw new RuntimeException("Account not Found for the account id :"+id+" and owner :"+owner);
		}
	}
	@PutMapping("/updateAccount/{id}")
	public String updateAccount(@PathVariable int id ,@RequestBody Account account ) {
		repository.save(account);
		return "Data Updated for id :"+id;
		
	}
	
	  @DeleteMapping("/deleteAccount/{id}") 
	  public String delete(@PathVariable int id) {
		  Optional<Account> account= repository.findById(id);
		  if(account.isPresent()) {
			   repository.delete(account.get());
			   return "Account is deleted with id :"+id;
		  }
		  else {
			  throw new RuntimeException("Account Not Found with id "+id);
		  }}
	 
	
}


