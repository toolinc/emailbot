// Copyright 2014 Tool Inc.

package com.tool.emailbot.application.command;

/**
 * Command that specifies the required information to determine the information of a Worker.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class WorkerInformationCommand implements Command {

    private String workerNumber;
    private boolean status;
    private String dependencyCode;
    private String getDependencyName;

    public WorkerInformationCommand(String workerNumber, boolean status, String dependencyCode,
                                    String getDependencyName) {
        this.workerNumber = workerNumber.toUpperCase();
        this.status = status;
        this.dependencyCode = dependencyCode.toUpperCase();
        this.getDependencyName = getDependencyName.toUpperCase();
    }

    public String getWorkerNumber() {
        return workerNumber;
    }

    public void setWorkerNumber(String workerNumber) {
        this.workerNumber = workerNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDependencyCode() {
        return dependencyCode;
    }

    public void setDependencyCode(String dependencyCode) {
        this.dependencyCode = dependencyCode;
    }

    public String getGetDependencyName() {
        return getDependencyName;
    }

    public void setGetDependencyName(String getDependencyName) {
        this.getDependencyName = getDependencyName;
    }
}
