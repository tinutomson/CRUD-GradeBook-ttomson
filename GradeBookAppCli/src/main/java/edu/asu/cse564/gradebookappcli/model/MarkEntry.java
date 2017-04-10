/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.cse564.gradebookappcli.model;

/**
 *
 * @author tinutomson
 */
public class MarkEntry {
    private double mark;
    private String feedBack;

    public MarkEntry() {
    }

    public MarkEntry(double mark, String feedBack) {
        this.mark = mark;
        this.feedBack = feedBack;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }
}
