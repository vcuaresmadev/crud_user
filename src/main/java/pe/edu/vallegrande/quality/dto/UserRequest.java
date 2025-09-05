package pe.edu.vallegrande.quality.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

public class UserRequest {
    @NotBlank(message = "Name is required")
    private String name;
    
    @Email(message = "Email should be valid")
    private String email;
    
    @Min(value = 0, message = "Age must be positive")
    private Integer age;

    public UserRequest() {}

    public UserRequest(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
}