package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cdac.Entity.FertilizerdealerClass;
import com.cdac.Entity.UserdetailsClass;
import com.cdac.repository.FdealerRepo;

@Service
@Transactional
public class FdealerService {

	@Autowired
	private FdealerRepo fdealerrepo;
	
	FertilizerdealerClass Fdealer = new FertilizerdealerClass();

	public void addfdealer(UserdetailsClass userdetailsclass) {
		Fdealer.setUserinfo(userdetailsclass);
		fdealerrepo.save(Fdealer);
		
	}
	


	
}
