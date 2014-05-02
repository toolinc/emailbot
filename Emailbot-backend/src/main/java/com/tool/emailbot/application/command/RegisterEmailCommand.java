// Copyright 2014 Tool Inc.

package com.tool.emailbot.application.command;

import com.tool.emailbot.common.application.command.Command;

import java.util.Date;

/**
 * Command that specifies that a new email request should be created.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class RegisterEmailCommand implements Command {

    private String name;
    private String fatherLastName;
    private String motherLastName;
    private Date dateOfBirth;
    private String homoclave;
    private String workerNumber;
    private String email;
    private String telephone;
    private String username;
    

    public RegisterEmailCommand(String name, String fatherLastName, String motherLastName,
                                Date dateOfBirth, String homoclave, String workerNumber,
				String email,String telephone, String username) {
        this.name = name;
        this.fatherLastName = fatherLastName;
        this.motherLastName = motherLastName;
        this.dateOfBirth = dateOfBirth;
        this.homoclave = homoclave;
        this.workerNumber = workerNumber;
        this.email = email;
        this.telephone = telephone;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHomoclave() {
        return homoclave;
    }

    public void setHomoclave(String homoclave) {
        this.homoclave = homoclave;
    }

    public String getWorkerNumber() {
        return workerNumber;
    }

    public void setWorkerNumber(String workerNumber) {
        this.workerNumber = workerNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
