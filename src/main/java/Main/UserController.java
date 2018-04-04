package Main;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	private UserService userService;
		@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "registerForm";
	}
	@PostMapping("/register")
	public String addUser(@ModelAttribute @Valid User user,
			BindingResult bindResult) {
		if(bindResult.hasErrors())
			return "registerForm";
		else {
			userService.addWithDefaultRole(user);
			return "registerSuccess";
		}}
		 @RequestMapping("admin/delete/{id}")
		    public String delete(@PathVariable Integer id){
		        userService.deleteUser(id);
		        return "redirect:/admin/uzytkownicy";
		    }
	
	@GetMapping("/admin/uzytkownicy")
	public String uzytkownicy(Model m) {
		ArrayList<User> uzytkownicy = userService.findAll();
		m.addAttribute("uzytkownicy", uzytkownicy);
		return "admin/uzytkownicy";
	}
	@GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable Integer id, Model m) {
		 m.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
}
	@PostMapping("/update")
    public String update(User user) {
        userService.saveUser(user);

        return "redirect:/admin/uzytkownicy";
}
@GetMapping("user/aboutme/{id}")
public String about(@PathVariable Integer id, Model m) {
	User uzytkownicy= userService.getUserById(id);
	m.addAttribute("uzytkownicy", uzytkownicy);
	return "user/aboutme";
}
  
@GetMapping("user/adddetails/{id}")
public String addDetails(@PathVariable Integer id, Model m) {
	 m.addAttribute("user", userService.getUserById(id));
    return "user/details";
}

@PostMapping("/adding")
public String saveDetails(User user) {
    userService.saveUser(user);

    return "redirect:/";
}
}