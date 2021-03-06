
package com.where.prateekyadav.myapplication.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.where.prateekyadav.myapplication.database.VisitedLocationInformation;
import com.where.prateekyadav.myapplication.search.model.placesdetails.Result;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;


@Generated("org.jsonschema2pojo")
public class Places {

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = new ArrayList<Object>();
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * 
     * @return
     *     The htmlAttributions
     */
    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    /**
     * 
     * @param htmlAttributions
     *     The html_attributions
     */
    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    /**
     * 
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }





}
