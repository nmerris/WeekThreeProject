package com.nmerris.roboresumedb.controllers;

import com.nmerris.roboresumedb.Utilities;
import com.nmerris.roboresumedb.models.EducationAchievement;
import com.nmerris.roboresumedb.models.Person;
import com.nmerris.roboresumedb.models.Skill;
import com.nmerris.roboresumedb.models.WorkExperience;
import com.nmerris.roboresumedb.repositories.EducationRepo;
import com.nmerris.roboresumedb.repositories.PersonRepo;
import com.nmerris.roboresumedb.repositories.SkillRepo;
import com.nmerris.roboresumedb.repositories.WorkExperienceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class MainController {

    @Autowired
    PersonRepo personRepo;
    @Autowired
    EducationRepo educationRepo;
    @Autowired
    SkillRepo skillRepo;
    @Autowired
    WorkExperienceRepo workExperienceRepo;


    // home page
    @GetMapping("/index")
    public String indexPageGet() {
        // need this so that the tables resets every time we go back to index
        // this is necessary so that the data is correct if the user chooses to 'start over'
        personRepo.deleteAll();
        educationRepo.deleteAll();
        skillRepo.deleteAll();
        workExperienceRepo.deleteAll();

        return "index";
    }



    @GetMapping("/addperson")
    public String addPersonGet(Model model) {
        System.out.println("++++++++++++++++++++++++++++++ JUST ENTERED /addperson GET route ++++++++++++++++++");


        model.addAttribute("newPerson", new Person());

        return "addperson";
    }

    @PostMapping("/addperson")
    public String addPersonPost(@Valid @ModelAttribute("newPerson") Person person, BindingResult bindingResult) {
        System.out.println("++++++++++++++++++++++++++++++ JUST ENTERED /addperson POST route ++++++++++++++++++");


        if(bindingResult.hasErrors()) {
            // return addperson after adding validation stuff

            System.out.println("********************* BINDING RESULT ERROR IN /addperson POST ****************************");
            return "addperson";
        }

        // set the resume creation date to right now
        person.setResumeCreationDate(new Date());

        // person should have first and last names, and email at this point
        // the collections in Person are null at this point, which shows up as a BLOB in the db!  ...blob is you uncle
        personRepo.save(person);

        return "redirect:/addeducation";
    }



    @GetMapping("/addeducation")
    public String addEdGet(Model model) {
        System.out.println("++++++++++++++++++++++++++++++ JUST ENTERED /addeducation GET route ++++++++++++++++++");

        Person p = personRepo.findAll().iterator().next();
//        System.out.println("################################################ inside /addecuation GET, person fn from db is: " + p.getNameFirst());

        model.addAttribute("currentNumRecords", educationRepo.count());
        model.addAttribute("newEdAchievement", new EducationAchievement());
        model.addAttribute("firstAndLastName", p.getNameFirst() + " " + p.getNameLast());

        return "addeducation";
    }

    @PostMapping("/addeducation")
    public String addEdPost(@Valid @ModelAttribute("newEdAchievement") EducationAchievement educationAchievement,
                            BindingResult bindingResult, Model model) {
        System.out.println("++++++++++++++++++++++++++++++ JUST ENTERED /addeducation POST route ++++++++++++++++++ ");

        // get the single person from the Person table
        Person p = personRepo.findAll().iterator().next();

        model.addAttribute("edAchievementJustAdded", educationAchievement);
        model.addAttribute("firstAndLastName", p.getNameFirst() + " " + p.getNameLast());

        if(bindingResult.hasErrors()) {
            // need to get the count even if an error, because we always show the count
            // it is also needed to know if we should allow the user to exit the education section, since they
            // must enter at least one educational achievement
            model.addAttribute("currentNumRecords", educationRepo.count());
            return "addeducation";
        }

        educationRepo.save(educationAchievement);

        // need to get the count AFTER successfully adding to db, so it is up to date
        model.addAttribute("currentNumRecords", educationRepo.count());

        return "addeducationconfirmation";
    }



    @GetMapping("/addworkexperience")
    public String addWorkGet(Model model) {
        System.out.println("++++++++++++++++++++++++++++++ JUST ENTERED /addworkexperience GET route ++++++++++++++++++");

        Person p = personRepo.findAll().iterator().next();
//        System.out.println("################################################ inside /addecuation GET, person fn from db is: " + p.getNameFirst());

        // it would be nice to show todays date as placeholder text for end date
        model.addAttribute("todaysDate", Utilities.getTodaysDateString());

        model.addAttribute("currentNumRecords", workExperienceRepo.count());
        model.addAttribute("newWorkExperience", new WorkExperience());
        model.addAttribute("firstAndLastName", p.getNameFirst() + " " + p.getNameLast());

        return "addworkexperience";
    }

    @PostMapping("/addworkexperience")
    public String addWorkPost(@Valid @ModelAttribute("newWorkExperience") WorkExperience workExperience,
                            BindingResult bindingResult, Model model) {
        System.out.println("++++++++++++++++++++++++++++++ JUST ENTERED /addworkexperience POST route ++++++++++++++++++ ");

        // get the single person from the Person table
        Person p = personRepo.findAll().iterator().next();

        model.addAttribute("workExperienceJustAdded", workExperience);
        model.addAttribute("firstAndLastName", p.getNameFirst() + " " + p.getNameLast());

        // TODO chop off the timestamp from the date, put it back in the model so it looks nice when page is redisplayed with errors
        // TODO check if end date is later than start date

        if(bindingResult.hasErrors()) {
            model.addAttribute("currentNumRecords", workExperienceRepo.count());

            return "addworkexperience";
        }

        workExperienceRepo.save(workExperience);
        model.addAttribute("currentNumRecords", workExperienceRepo.count());

        return "addworkexperienceconfirmation";
    }
    


    @GetMapping("/addskill")
    public String addSkillGet(Model model) {
        System.out.println("++++++++++++++++++++++++++++++ JUST ENTERED /addskill GET route ++++++++++++++++++");

        addPersonNameToModel(model);
        model.addAttribute("currentNumRecords", skillRepo.count());
        model.addAttribute("newSkill", new Skill());

        return "addskill";
    }

    @PostMapping("/addskill")
    public String addSkillPost(@Valid @ModelAttribute("newSkill") Skill skill,
                              BindingResult bindingResult, Model model) {
        System.out.println("++++++++++++++++++++++++++++++ JUST ENTERED /addskill POST route ++++++++++++++++++ ");

        addPersonNameToModel(model);
        model.addAttribute("skillJustAdded", skill);

        if(bindingResult.hasErrors()) {
            model.addAttribute("currentNumRecords", skillRepo.count());

            return "addskill";
        }

        skillRepo.save(skill);
        model.addAttribute("currentNumRecords", skillRepo.count());

        return "addskillconfirmation";
    }



    public void addPersonNameToModel(Model model) {
        try {
            // try to get the single Person from the db
            Person p = personRepo.findAll().iterator().next();
            // if there was a Person, add their full name to the model
            model.addAttribute("firstAndLastName", p.getNameFirst() + " " + p.getNameLast());
        } catch (Exception e) {
            // must not have found a Person in the db, so use a placeholder name
            // this really convenient for testing, but it also makes the app less likely to crash
            model.addAttribute("firstAndLastName", "Jane Java Doe");
        }
    }



}
