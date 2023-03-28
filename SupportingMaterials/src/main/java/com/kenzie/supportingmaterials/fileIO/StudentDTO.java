package com.kenzie.supportingmaterials.fileIO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "currentlyEnrolledClassName", "totalGrade"})
public class StudentDTO {
    private String name;
    private String currentlyEnrolledClassName;
    private int totalGrade;

    public StudentDTO() {

    }

    // TODO: write getter and setter methods for each property!


    @Override
    public String toString() {
        return "StudentDTO{" +
                "Name='" + this.name + '\'' +
                ", currentlyEnrolledClassName='" + this.currentlyEnrolledClassName + '\'' +
                ", totalGrade=" + this.totalGrade +
                '}';
    }
}
