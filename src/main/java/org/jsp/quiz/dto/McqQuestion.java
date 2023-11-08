package org.jsp.quiz.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Data
@Entity
public class McqQuestion {
	@GeneratedValue(generator = "mcq")
	@SequenceGenerator(name = "mcq", initialValue = 500, allocationSize = 1)
	@Id
	int id;
	String question;
	int marks;
	String answer;
	String optionA;
	String optionB;
	String optionC;
	String optionD;
	String subject;
}