package com.setec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.setec.CoffeeShopTelegramBotApplication;
import com.setec.entities.Booked;
import com.setec.repos.BookedRepos;
import com.setec.services.MyTelegramBot;



@Controller
public class MyController {

    private final CoffeeShopTelegramBotApplication coffeeShopTelegramBotApplication;

    MyController(CoffeeShopTelegramBotApplication coffeeShopTelegramBotApplication) {
        this.coffeeShopTelegramBotApplication = coffeeShopTelegramBotApplication;
    }
	//http://localhost:8080/
	
	@GetMapping({"/","/home"})
	public String home(Model mod) {
		Booked booked = new Booked(
				1,
				"Srin Sreynich",
				"8487579384",
				"srrynich123@gmail.com",
				"11/25/2025",
				"5:17 PM",
				5
				);
		
		mod.addAttribute("booked",booked);		
		return "index";
	}
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	@GetMapping("/service")
	public String service() {
		return "service";
	}
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	@GetMapping("/reservation")
	public String reservation(Model mod) {
		Booked booked = new Booked(
				1,
				"Srin Sreynich",
				"8487579384",
				"srrynich123@gmail.com",
				"11/25/2025",
				"5:17 PM",
				5
				);
		
		mod.addAttribute("booked",booked);		
		return "reservation";
	}
	@GetMapping("/testimonial")
	public String testimonial() { 
		return "testimonial";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@Autowired
	private BookedRepos bookedRepos;
	
	@Autowired
	private MyTelegramBot bot;
	
//	@PostMapping("/success")
//	public String success(@ModelAttribute Booked booked) {
//		bookedRepos.save(booked);
//		bot.sendMessage(booked.toString());
//		return "success";
//	}
	
	@PostMapping("/success")
	public String success(@ModelAttribute Booked booked) {
	    bookedRepos.save(booked);

	    String msg = ""
	        + "☕ New Reservation Alert! ☕\n"
	        + "\n"
	        + "🆔 Booking ID:9 " + booked.getId() + "\n" 
	        + "👤 Name: " + booked.getName() + "\n"
	        + "📞 Phone: " + booked.getPhoneNumber() + "\n"
	        + "📧 Email: " + booked.getEmail() + "\n"
	        + "📅 Date: " + booked.getDate() + "\n"
	        + "⏰ Time: " + booked.getTime() + "\n"
	        + "👥 Person: " + booked.getPerson();

	    bot.sendMessage(msg);
	    return "success";
	}

}

