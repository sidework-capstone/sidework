package com.codeup.sidework.controllers;

import com.codeup.sidework.daos.UserRepository;
import com.codeup.sidework.models.User;
import com.codeup.sidework.services.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
public class UsersController {
    private final UserRepository usersDao;
    private PasswordEncoder passwordEncoder;


    public UsersController(UserRepository usersDao, PasswordEncoder passwordEncoder) {
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }






    @GetMapping("/users/register-worker")
    public String showWorkerRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register-worker";
    }



    @PostMapping("/users/register-worker")
    public String registerNewWorker(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersDao.save(user);
//        authenticate(user);
        return "redirect:/users/login-worker";
    }



    @GetMapping("/users/workspace-worker")
    public String showWorkerWorkspace() {

        return "/users/workspace-worker";
    }



    @PostMapping("/users/workspace-worker")
    public String showIndividualUsersWorkplace(@ModelAttribute User user) {
        return "/users/workspace-worker";
    }




    //View a user =/users/{id}= (worker profile page)
    @GetMapping("/users/profile-worker/{id}")
    public String showWorkerProfile(@PathVariable long id, Model viewAndModel) { // Add a long id parameter
        // use the repository to find a user by its ID
        // .findOne(id)
        User user = usersDao.findOne(id);
        // pass the user to the view, using a Model (viewmodel)
        viewAndModel.addAttribute("user", user);
        return "users/profile-worker";
    }

}







































//<--TASKS-->

//        - [ ] [0/10] Users (greg)
//        - [ ] Create a user =/users/create= (registration page)
//        - Rows are inserted into the `users` table
//        - uncommenting
//        - [ ] View a user =/users/{id}= (worker profile page)
//        - Currently there are syntax errors on this page
//        - [ ] View a list of users, i.e. all users in the database, with links to individual users =/users= (business dashboard)//



//        - [ ] Edit a user's information =/users/{id}/edit= (access control)
//        - information in the database is changed
//        - [ ] Attach a position_id to a user when they are created
//        - currently this is non-functional?
//        - [ ] Search users by position (blocked by this working on registration)



//        - [ ] Search (filter) users by availability, employment status (blocked by these working on the register page)
//        - [ ] Delete a user (access control)
//        - [ ] Add positions and availability/schedule to user registration
//        - [ ] Validation error messages






































//==========================================================================================================================================
//    @GetMapping("/users/")
//    public String showLandingPage() {
//        return "users/landing-page";
//    }
// ^ this mapping will not work for the landing page, needs to go through the Home Controller

//==========================================================================================================================================
// NOTES FOR SHOW WORKER WORKSPACE METHOD
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (user.getId() == 0) {
////            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return "redirect:/users/login-worker";
//        }

//        user = usersDao.findOne(user.getId());
//        model.addAttribute("user", user);

//==========================================================================================================================================
//// AUTHENTICATION OF USERNAME/PASSWORD
//        public String registerNewWorker(@Valid User user, Errors validation, Model model) {
//        User existingUser = usersDao.findByUsername(user.getUsername());
//        if (existingUser != null) {
//            validation.rejectValue("username", "username", "this username is unavailable"); }
//        if (user.getPassword().equals("")) {
//            validation.rejectValue("password", "password", "password must be at least 5 characters"); }
//// do we need to add another column to confirm password for authentication?
//        //        if (!user.getPassword().equals(user.getConfirmPassword())) {
//        //            validation.rejectValue("confirmPassword", "confirmPassword", "passwords must match");
//        //        }
//        if (validation.hasErrors()) {
//            model.addAttribute("errors", validation);
//            model.addAttribute("user", user);
//            return "users/register"; }

//==========================================================================================================================================
//// AUTHENTICATION OF USER WITH USERDETAILSLOADER
//    private void authenticate(User authenticatedUser) {
//        UserDetailsLoader userDetailsLoader = new UserWithRoles(authenticatedUser, Collections.emptyList());
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetailsLoader; userDetailsLoader.getPassword(), userDetailsLoader.getAuthorities());
//        SecurityContext context = SecurityContextHolder.getContext();
//        context.setAuthentication(authentication);
//    }

//==========================================================================================================================================
////  ADD VIEW ALL JOB LISTINGS TO WORKER WORKSPACE ^
//        Iterable<Listings> listings = listingsService.findAll();
//        model.addAttribute("listings", listings);
//
////  RESEARCH HOW TO BIND MULTIPLE CHECKBOXES TO ENTITIES
//    ModelAndView class documentation
//    @InitBinder
//    public void initBind(final WebDataBinder binder) {
//        binder.registerCustomEditor(Positions.class, new Positions());
//    }
