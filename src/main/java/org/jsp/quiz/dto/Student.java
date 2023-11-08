package org.jsp.quiz.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Component
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
	
   @Size(min = 3, message = "*enter atleast 3 charecters")
   @Size(max = 15, message = "*At max 15 charecters allowed")
   private String name;
   
   @Email(message = "Enter proper Email")  
   @NotEmpty(message = "Enter proper Email")  //it is used to not allow the null it should not be leave
   private String email;
   
   @DecimalMin(value = "6000000000", message = "Enter proper Mobile Number")
   @DecimalMax(value = "9999999999", message = "Enter proper Mobile Number")
   @NotNull(message = "*Enter Proper Mobile Number")
   private long mobile;
   
   @Pattern(regexp ="^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "*Enter atleast one uppercase,lowercase,digit and special charecter and minimum 8 charecters")
   private String password;
   
   @NotEmpty(message = "*This Field Can not be Empty")
   private String gender;
   
   @Lob                //it is used to upload the image/file
   @Column(columnDefinition = "MEDIUMBLOB")
   private byte[] picture;
   private int otp;
   private boolean verified;
   private boolean approved;
   
   @ManyToMany(fetch = FetchType.EAGER)
	List<Batch> batchs;


   
}
