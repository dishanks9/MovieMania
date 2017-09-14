package com.music.job.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.music.job.pojo.Jobs;


@Component
public class JobValidator implements Validator {
	
	public boolean supports(Class aClass) {
		return Jobs.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Jobs newCategory = (Jobs) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobTitle", "error.invalid.job", "Job title Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobDescription", "error.invalid.job", "Job Description Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobRequirements", "error.invalid.job", "Job Requirements Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "skillsRequired", "error.invalid.job", "Skills requirement Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hourlySalary", "error.invalid.job", "Hourly salary Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "additionalRequirement", "error.invalid.job", "Additional requirement Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "minimumHours", "error.invalid.job", "Minimum hours per week Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "duration", "error.invalid.job", "Total duration of job Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "noOfPositions", "error.invalid.job", "No of positions Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "error.invalid.job", "Job location Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "error.invalid.job", "Company Name Required");
		if (errors.hasErrors()) {
            return;//Skip the rest of the validation rules
        }
     
	}
}
