package com.cdac.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Entity.BiddingEntityClass;
import com.cdac.Entity.FarmgoodsClass;
import com.cdac.Entity.GoodsbuyerClass;
import com.cdac.Entity.UserdetailsClass;
import com.cdac.dto.PlaceBidDTO;
import com.cdac.service.GoodsBuyerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GoodsBuyerController {

	GoodsbuyerClass signedbuyer= null;
	
	//FarmgoodsClass singlegood=null;
	
	@Autowired
	private GoodsBuyerService buyerservice;
	
	
	
	  @GetMapping("/fetchfarmgoods/{userEmailId}") 
	  public ResponseEntity<List<FarmgoodsClass>> fetchFarmgoods(@PathVariable String userEmailId,HttpSession session) {
	  
	  //if(signedbuyer==null) { signedbuyer=getmyself(session);}
		  
		  GoodsbuyerClass signedbuyer =buyerservice.getBuyer(userEmailId);
		  List<FarmgoodsClass> list =buyerservice.getgoods(signedbuyer.getUserinfo().getState());
	  
		  return new ResponseEntity<List<FarmgoodsClass>>(list, HttpStatus.OK); 
	  
	  }
	 
	  @GetMapping("/fetchsinglegood/{goodsId}")
		public ResponseEntity<FarmgoodsClass> getSingleGood(@PathVariable String goodsId){
			
		  FarmgoodsClass singlegood=buyerservice.getsinglegood(Integer.parseInt(goodsId));
			return new ResponseEntity<FarmgoodsClass>(singlegood,HttpStatus.OK);
	  }
	  
	  @PostMapping("/placebid")
	  public String placeBid (@RequestBody PlaceBidDTO placebidto) {
		  System.out.println(placebidto.getGoodsId()+"pppp");
		  FarmgoodsClass singlegood = buyerservice.getsinglegood(placebidto.getGoodsId());
		 		  
		  if(singlegood.getFinalPrice()<placebidto.getFinalPrice()) {
			  buyerservice.placeIt(placebidto.getFinalPrice(),singlegood.getGoodsId(),buyerservice.getBuyer(placebidto.getUserEmailId()).getBuyerId(),singlegood);
			  //System.out.println("rrr");
			  return "Bid Placed";
			  }else {
				  System.out.println("final price should be more than previous one");
			  }
		  return "Bid Not placed";		
	  }
	  
	  @GetMapping("/viewmybids/{userEmailId}")
	  public ResponseEntity<List<FarmgoodsClass>> viewMyactivebids(@PathVariable String userEmailId, HttpSession session){
		  //if(signedbuyer==null) { signedbuyer=getmyself(session);}
		  
		  GoodsbuyerClass signedbuyer = buyerservice.getBuyer(userEmailId);
		  List<FarmgoodsClass> list = buyerservice.getactivebids(signedbuyer.getBuyerId());
		  
		  return new ResponseEntity<List<FarmgoodsClass>>(list,HttpStatus.OK);
	  }
	  
	  @GetMapping("/buyerhistory/{userEmailId}")
	  public ResponseEntity<List<BiddingEntityClass>> getmyhistory(@PathVariable String userEmailId,HttpSession session){
		  //if(signedbuyer==null) { signedbuyer=getmyself(session);}
		  GoodsbuyerClass signedbuyer = buyerservice.getBuyer(userEmailId);

		  List<BiddingEntityClass> list = buyerservice.gethistory(signedbuyer.getBuyerId());
		  return new ResponseEntity<List<BiddingEntityClass>>(list,HttpStatus.OK);
	  }
	  
	
	  @GetMapping("/cancelmybid/{goodsId}")
	  public String CancelMyBid(@PathVariable String goodsId) {
		 FarmgoodsClass singlegood= buyerservice.getsinglegood(Integer.parseInt(goodsId));
		  return buyerservice.cancelbid(singlegood);
	  }
	  
	  
	  
	public GoodsbuyerClass getmyself(HttpSession session) {
		return buyerservice.getBuyer(((UserdetailsClass)session.getAttribute("currentuser")).getUserEmailId());
	}
}








