package com.zcc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zcc.common.ParamConstance;
import com.zcc.model.dao.Payment;
import com.zcc.model.dao.User;
import com.zcc.model.repository.PaymentRepository;
import com.zcc.model.repository.UserRepository;
import io.swagger.annotations.ApiOperation;

/**
 * Created by NCP-620 on 2017/7/17.
 */
@Controller
public class StartController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PaymentRepository paymentRepository;

	private static Logger logger = LogManager.getLogger(StartController.class.getName());

	@RequestMapping("/register")
	@ApiOperation(value = "Register Controller", response = String.class)
	public String register() {
		return "register";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/register/saveRegister", method = RequestMethod.POST)
	@ApiOperation(value = "Save the Register info", response = String.class)
	public String registerUser(HttpServletRequest request, Model model) {

		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		if (!password.equals(password2)) {
			model.addAttribute(ParamConstance.REQUEST_MSG, "Password is inconsistent");
			return "register";
		}

		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			user = new User();
			user.setUsername(username);
			user.setFullname(fullname);
			user.setPassword(password);
			model.addAttribute(ParamConstance.REQUEST_MSG, "Register Success!");
			model.addAttribute("user", user);
			userRepository.save(user);
			return "login";
		} else {
			model.addAttribute(ParamConstance.REQUEST_MSG, "Username already existed！re-input plz！");
			return "register";
		}
	}

	@RequestMapping(value = "/validateLogin", method = RequestMethod.POST)
	@ApiOperation(value = "Validate Login", response = String.class)
	public String validateLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userRepository.findUserByUsernameAndPassword(username, password);
		List<Payment> payments = paymentRepository.findByUsername(username);
		model.addAttribute("user", user);
		return "redirect:/showPaymentList/"+user.getId();
	}

	@RequestMapping(value = "/showPaymentList/{id}")
	public String showPaymentList(Model model, @PathVariable("id") Long id) {
		User user = userRepository.findUserById(id);
		List<Payment> payments = paymentRepository.findByUsername(user.getUsername());
		model.addAttribute("user", user);
		model.addAttribute("payments", payments);
		return "paymentList";
	}
}
