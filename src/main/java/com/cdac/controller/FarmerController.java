package com.cdac.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Entity.BiddingEntityClass;
import com.cdac.Entity.FarmersClass;
import com.cdac.Entity.FarmgoodsClass;
import com.cdac.Entity.UserdetailsClass;
import com.cdac.dto.EndBidDto;
import com.cdac.dto.FarmgoodsDto;
import com.cdac.service.BiddingService;
import com.cdac.service.EmailService;
import com.cdac.service.FarmerService;
import com.cdac.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FarmerController {

	@Autowired
	private FarmerService farmerservice;

	@Autowired
	private BiddingService bidservice;

	@Autowired
	private UserService userservice;

	@Autowired
	private EmailService emailService;

	@PostMapping("/addgoods")
	public String AddGoods(@Valid @RequestBody FarmgoodsDto farmgoodsdto, HttpSession session) {

		FarmgoodsClass farmsgood = new FarmgoodsClass();

		try {
			farmsgood.setGoodsName(farmgoodsdto.getGoodsName());
			farmsgood.setExpectedPrice(farmgoodsdto.getExpectedPrice());
			farmsgood.setQuantity(farmgoodsdto.getQuantity());
			farmsgood.setFarmer(farmerservice.getFarmer(farmgoodsdto.getUserEmailId()));
			farmerservice.addFarmGoods(farmsgood);

		} catch (Exception e) {
		}

		return "Good Added Successfully";
	}

	@GetMapping("/viewgoods/{userEmailId}")
	public ResponseEntity<List<FarmgoodsClass>> ViewGoods(@PathVariable String userEmailId, HttpSession session) {

		FarmersClass signedfarmer = farmerservice.getFarmer(userEmailId);
		List<FarmgoodsClass> list = farmerservice.getGoods(signedfarmer.getFarmerId());
		return new ResponseEntity<List<FarmgoodsClass>>(list, HttpStatus.OK);

	}

	@GetMapping("/getsinglegood/{goodsId}")
	public ResponseEntity<FarmgoodsClass> getSingleGood(@PathVariable String goodsId) {

		FarmgoodsClass singlegood = farmerservice.getsinglegood(Integer.parseInt(goodsId));
		return new ResponseEntity<FarmgoodsClass>(singlegood, HttpStatus.OK);
	}

	@GetMapping("/endbid/{goodsId}")
	public ResponseEntity<BiddingEntityClass> endBidbyfarmer(@PathVariable String goodsId) {

		BiddingEntityClass bidsum = new BiddingEntityClass();
		FarmgoodsClass soldgood = farmerservice.getsinglegood(Integer.parseInt(goodsId));

		bidsum.setFarmerId(soldgood.getFarmer().getFarmerId());
		bidsum.setFarmerName(soldgood.getFarmer().getUserinfo().getUserName());
		bidsum.setFarmerContact(soldgood.getFarmer().getUserinfo().getUserMobileNo());
		bidsum.setBuyerId(Integer.parseInt(soldgood.getBuyerId()));
		bidsum.setBuyerName(userservice.getBuyer(Integer.parseInt(soldgood.getBuyerId())).getUserName());
		bidsum.setBuyerContact(userservice.getBuyer(Integer.parseInt(soldgood.getBuyerId())).getUserMobileNo());
		bidsum.setGoods_id(soldgood.getGoodsId());
		bidsum.setGoodsName(soldgood.getGoodsName());
		bidsum.setFinalprice(soldgood.getFinalPrice());
		bidsum.setQuantity(soldgood.getQuantity());
		bidsum.setTotalAmount(soldgood.getFinalPrice() * soldgood.getQuantity());
		bidsum.setDate(LocalDate.now());
		soldgood.setStatus("SOLD");
		farmerservice.addFarmGoods(soldgood);
		bidservice.addbid(bidsum);

		return new ResponseEntity<BiddingEntityClass>(bidsum, HttpStatus.OK);

	}

	@GetMapping("/farmerhistory/{userEmailId}")
	public ResponseEntity<List<BiddingEntityClass>> getmyhistory(@PathVariable String userEmailId,
			HttpSession session) {

		System.out.println(userEmailId);
		FarmersClass signedfarmer = farmerservice.getFarmer(userEmailId);
		List<BiddingEntityClass> list = farmerservice.gethistory(signedfarmer.getFarmerId());
		return new ResponseEntity<List<BiddingEntityClass>>(list, HttpStatus.OK);
	}

	@DeleteMapping("/deletegoods/{goodsId}")
	public String deleteGoods(@PathVariable String goodsId, HttpSession session) {
		farmerservice.deldata(Integer.parseInt(goodsId));
		System.out.println("deleting");
		return "Success";
	}

	@PostMapping("/endbidsendemail")
	public ResponseEntity<?> EndBidsendemail(@RequestBody EndBidDto endBidDto) {
		System.out.println("hit");
		System.out.println(endBidDto.getFarmeremail() + "after his");
		String farmeremail = endBidDto.getFarmeremail();
		boolean result = emailService.sendEmail(endBidDto.getSubject(), endBidDto.getMessage(), farmeremail);
		String buyeremail = (userservice.getBuyer(endBidDto.getBuyerid())).getUserEmailId();
		boolean result1 = emailService.sendEmail(endBidDto.getSubject(), endBidDto.getMessage(), buyeremail);
		if (result && result1) {
			return ResponseEntity.ok("Email sent");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent !!");
		}
	}

	public FarmersClass getmyself(HttpSession session) {
		return farmerservice.getFarmer(((UserdetailsClass) session.getAttribute("currentuser")).getUserEmailId());
	}
}
