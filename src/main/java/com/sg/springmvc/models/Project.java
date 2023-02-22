package com.sg.springmvc.models;

import java.util.Date;

public class Project {
    private int prj_id;
    private int sup_prj_id;
    private String accession;
    private int sample_scope_id;
    private int submitter_id;
    private int user_id;
    private Date create_time;
    private Date modify_time;
    private Date release_time;
    private int status;
    private Date delete_time;
    private String title;
    private String note;
    private String description;
    private String relevance;
    private String consertium_url;
    private int is_released;
    private int release_state;
    private int is_release_immediately;

    public int getPrj_id() {
        return prj_id;
    }

    public void setPrj_id(int prj_id) {
        this.prj_id = prj_id;
    }

    public int getSup_prj_id() {
        return sup_prj_id;
    }

    public void setSup_prj_id(int sup_prj_id) {
        this.sup_prj_id = sup_prj_id;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public int getSample_scope_id() {
        return sample_scope_id;
    }

    public void setSample_scope_id(int sample_scope_id) {
        this.sample_scope_id = sample_scope_id;
    }

    public int getSubmitter_id() {
        return submitter_id;
    }

    public void setSubmitter_id(int submitter_id) {
        this.submitter_id = submitter_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public Date getRelease_time() {
        return release_time;
    }

    public void setRelease_time(Date release_time) {
        this.release_time = release_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Date delete_time) {
        this.delete_time = delete_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelevance() {
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    public String getConsertium_url() {
        return consertium_url;
    }

    public void setConsertium_url(String consertium_url) {
        this.consertium_url = consertium_url;
    }

    public int getIs_released() {
        return is_released;
    }

    public void setIs_released(int is_released) {
        this.is_released = is_released;
    }

    public int getRelease_state() {
        return release_state;
    }

    public void setRelease_state(int release_state) {
        this.release_state = release_state;
    }

    public int getIs_release_immediately() {
        return is_release_immediately;
    }

    public void setIs_release_immediately(int is_release_immediately) {
        this.is_release_immediately = is_release_immediately;
    }
}
