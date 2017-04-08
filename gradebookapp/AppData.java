/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp;

import edu.asu.cse564.gradebookapp.model.GradeBook;
import edu.asu.cse564.gradebookapp.model.GradeBook;

public class AppData {
    private static GradeBook classGradeBook;

    public static GradeBook getGradeBook() {
        if(classGradeBook == null) {
           classGradeBook = new GradeBook();
        }
        return classGradeBook;
    }
    
}
