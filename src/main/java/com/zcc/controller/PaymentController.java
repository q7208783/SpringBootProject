package com.zcc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcc.model.dao.Payment;
import com.zcc.model.dao.User;
import com.zcc.model.repository.PaymentRepository;
import com.zcc.model.repository.UserRepository;

/**
 * Created by NCP-620 on 2017/7/11.
 */
@Controller
public class PaymentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
		private UserRepository userRepository;

	SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	@PostMapping("/addPayment")
	public String addPayment(Payment payment, Model model, @RequestParam Long id) {
		User user=userRepository.findUserById(id);
		Date date = new Date();
		payment.setDate(formatter.format(date));
		payment.setUsername(user.getUsername());
		paymentRepository.save(payment);
		return "redirect:/showPaymentList/" + id;
	}

	@GetMapping("/{username}")
	public String userPayments(@PathVariable("username") String username, Model model) {
		List<Payment> list = paymentRepository.findByUsername(username);
		if (!list.isEmpty())
			model.addAttribute("payments", list);
		return "paymentList";
	}

	@GetMapping("/test")
	@ResponseBody
	public String hello() {
		return "hello";
	}
}
