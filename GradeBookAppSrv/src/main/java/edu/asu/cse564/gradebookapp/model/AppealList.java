/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tinutomson
 */
public class AppealList {
    private List<Appeal> appealList;

    public AppealList() {
        appealList = new ArrayList<>();
    }

    public List<Appeal> getAppealList() {
        return appealList;
    }

    public AppealList(List<Appeal> appealList) {
        this.appealList = appealList;
    }

    public void setAppealList(List<Appeal> appealList) {
        this.appealList = appealList;
    }

    Appeal addAppeal(StudentRecord record, Appeal newAppeal) {
        newAppeal.generateId();
        appealList.add(newAppeal);
        return newAppeal;
    }

    List<Appeal> getAppealList(String username) {
        List<Appeal> filteredList = new ArrayList<>();
        for(Appeal appeal: appealList) {
            if(appeal.getStudentUserName().equals(username)) {
                filteredList.add(appeal);
            }
        }
        return filteredList;
    }

    Appeal getAppeal(String username, String appealId) {
        int intAppealId = Integer.parseInt(appealId);
        for(Appeal appeal: appealList) {
            if(appeal.getAppealId() == intAppealId && appeal.getStudentUserName().equals(username)) {
                return appeal;
            }
        }
        return null;
    }

    void deleteAppeal(Appeal appeal) {
        appealList.remove(appeal);
    }
    
}