package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Entity.BiddingEntityClass;
import com.cdac.Entity.FarmersClass;
import com.cdac.Entity.FarmgoodsClass;
import com.cdac.Entity.UserdetailsClass;
import com.cdac.repository.BiddingRepo;
import com.cdac.repository.FarmersRepo;
import com.cdac.repository.FarmgoodsRepo;

@Service
@Transactional
public class FarmerService {

	@Autowired
	private FarmersRepo farmerrepo;

	@Autowired
	private FarmgoodsRepo farmgoodsrepo;

	@Autowired
	private BiddingRepo bidrepo;

	FarmersClass farmer = new FarmersClass();

	public void addfarmer(UserdetailsClass userdetailsclass) {

		farmer.setUserinfo(userdetailsclass);
		farmerrepo.save(farmer);
	}

	public FarmersClass getFarmer(String email) {
		return farmerrepo.getFarmerByEmail(email);
	}

	public void addFarmGoods(FarmgoodsClass farmgoods) {
		farmgoodsrepo.save(farmgoods);
	}

	public List<FarmgoodsClass> getGoods(int id) {
		List<FarmgoodsClass> list = farmgoodsrepo.GetGoodsByFId(id);
		return list;
	}

	public FarmgoodsClass getsinglegood(int id) {
		return farmgoodsrepo.getsingle(id);
	}

	public List<BiddingEntityClass> gethistory(int farmerId) {
		return bidrepo.getfarmerbidhistory(farmerId);
	}

	public void deldata(int goodsId) {
		farmgoodsrepo.deletegoods(goodsId);

	}

}
