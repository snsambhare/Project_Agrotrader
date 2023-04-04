package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.Entity.BiddingEntityClass;
import com.cdac.Entity.FarmgoodsClass;
import com.cdac.Entity.GoodsbuyerClass;
import com.cdac.Entity.UserdetailsClass;
import com.cdac.repository.BiddingRepo;
import com.cdac.repository.FarmgoodsRepo;
import com.cdac.repository.GoodsbuyerRepo;

@Service
@Transactional
public class GoodsBuyerService {
	GoodsbuyerClass buyer = new GoodsbuyerClass();

	@Autowired
	private GoodsbuyerRepo buyerrepo;
	
	@Autowired
	private FarmgoodsRepo goodsrepo;
	
	@Autowired
	private BiddingRepo bidrepo;
	
	public void addbuyer(UserdetailsClass userdetailsclass) {
	
		buyer.setUserinfo(userdetailsclass);
		buyerrepo.save(buyer);
	}

	public GoodsbuyerClass getBuyer(String email) {
		return buyerrepo.getBuyerByEmail(email);
	}

	
	public List<FarmgoodsClass> getgoods(String state) {
		return	goodsrepo.getGoodsByState(state); 
	}

	public FarmgoodsClass getsinglegood(int goodsId) {
		return goodsrepo.getsingle(goodsId);
	}

	public void placeIt(double finalPrice,int goodsId,int buyerId, FarmgoodsClass singlegood) {
		singlegood.setPrevbuyerId(singlegood.getBuyerId());
		singlegood.setPrevFinalPrice(singlegood.getFinalPrice());
		
		singlegood.setFinalPrice(finalPrice);
		singlegood.setBuyerId(Integer.toString(buyerId));
		goodsrepo.save(singlegood);
	}

	public List<FarmgoodsClass> getactivebids(int buyerId) {	
		return goodsrepo.getbids(buyerId);
	}

	public List<BiddingEntityClass> gethistory(int buyerId) {
		return bidrepo.getmybidhistory(buyerId);
	}

	public String cancelbid(FarmgoodsClass singlegood) {
		singlegood.setBuyerId(singlegood.getPrevbuyerId());
		singlegood.setFinalPrice(singlegood.getPrevFinalPrice());
		singlegood.setPrevbuyerId("None");
		singlegood.setPrevFinalPrice(0.0);
		goodsrepo.save(singlegood);
		return "Bid Canceled";
	}


	

	
}
