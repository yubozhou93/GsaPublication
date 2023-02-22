package com.sg.springmvc.models;

import java.util.Date;

public class Study {
    private int study_id;
    private int study_type_id;
    private int user_id;
    private int submitter_id;
    private int dac_id;
    private int is_controlled_access;
    private String accession;
    private String title;
    private int prj_id;
    private String prj_accession;
    private String description;
    private int package_id;
    private Date create_time;
    private Date release_time;
    private Date modify_time;
    private int status;
    private int release_state;
    private Date delete_time;
    private Date archived_time;
    private int archive_root;
    private String disease_name;
    private int is_release_immediately;
    private int data_added_status;
    private int longitudinal;
    private int parent;
    private String most_beian_number;
    private String most_beifen_number;
    private int samState;
    private int batch_add_flag;
    private int is_organization_confirm;
    private Date submit_time;
    private int sample_synchronization;
    private int is_most_registered;
    private int openaccess_data_type;
    private int is_commercial_cellline;
    private int is_foreign;
    private int is_file_public;
    private double rating;

    public int getStudy_id() {
        return study_id;
    }

    public void setStudy_id(int study_id) {
        this.study_id = study_id;
    }

    public int getStudy_type_id() {
        return study_type_id;
    }

    public void setStudy_type_id(int study_type_id) {
        this.study_type_id = study_type_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSubmitter_id() {
        return submitter_id;
    }

    public void setSubmitter_id(int submitter_id) {
        this.submitter_id = submitter_id;
    }

    public int getDac_id() {
        return dac_id;
    }

    public void setDac_id(int dac_id) {
        this.dac_id = dac_id;
    }

    public int getIs_controlled_access() {
        return is_controlled_access;
    }

    public void setIs_controlled_access(int is_controlled_access) {
        this.is_controlled_access = is_controlled_access;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrj_id() {
        return prj_id;
    }

    public void setPrj_id(int prj_id) {
        this.prj_id = prj_id;
    }

    public String getPrj_accession() {
        return prj_accession;
    }

    public void setPrj_accession(String prj_accession) {
        this.prj_accession = prj_accession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getRelease_time() {
        return release_time;
    }

    public void setRelease_time(Date release_time) {
        this.release_time = release_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRelease_state() {
        return release_state;
    }

    public void setRelease_state(int release_state) {
        this.release_state = release_state;
    }

    public Date getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Date delete_time) {
        this.delete_time = delete_time;
    }

    public Date getArchived_time() {
        return archived_time;
    }

    public void setArchived_time(Date archived_time) {
        this.archived_time = archived_time;
    }

    public int getArchive_root() {
        return archive_root;
    }

    public void setArchive_root(int archive_root) {
        this.archive_root = archive_root;
    }

    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public int getIs_release_immediately() {
        return is_release_immediately;
    }

    public void setIs_release_immediately(int is_release_immediately) {
        this.is_release_immediately = is_release_immediately;
    }

    public int getData_added_status() {
        return data_added_status;
    }

    public void setData_added_status(int data_added_status) {
        this.data_added_status = data_added_status;
    }

    public int getLongitudinal() {
        return longitudinal;
    }

    public void setLongitudinal(int longitudinal) {
        this.longitudinal = longitudinal;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getMost_beian_number() {
        return most_beian_number;
    }

    public void setMost_beian_number(String most_beian_number) {
        this.most_beian_number = most_beian_number;
    }

    public String getMost_beifen_number() {
        return most_beifen_number;
    }

    public void setMost_beifen_number(String most_beifen_number) {
        this.most_beifen_number = most_beifen_number;
    }

    public int getSamState() {
        return samState;
    }

    public void setSamState(int samState) {
        this.samState = samState;
    }

    public int getBatch_add_flag() {
        return batch_add_flag;
    }

    public void setBatch_add_flag(int batch_add_flag) {
        this.batch_add_flag = batch_add_flag;
    }

    public int getIs_organization_confirm() {
        return is_organization_confirm;
    }

    public void setIs_organization_confirm(int is_organization_confirm) {
        this.is_organization_confirm = is_organization_confirm;
    }

    public Date getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(Date submit_time) {
        this.submit_time = submit_time;
    }

    public int getSample_synchronization() {
        return sample_synchronization;
    }

    public void setSample_synchronization(int sample_synchronization) {
        this.sample_synchronization = sample_synchronization;
    }

    public int getIs_most_registered() {
        return is_most_registered;
    }

    public void setIs_most_registered(int is_most_registered) {
        this.is_most_registered = is_most_registered;
    }

    public int getOpenaccess_data_type() {
        return openaccess_data_type;
    }

    public void setOpenaccess_data_type(int openaccess_data_type) {
        this.openaccess_data_type = openaccess_data_type;
    }

    public int getIs_commercial_cellline() {
        return is_commercial_cellline;
    }

    public void setIs_commercial_cellline(int is_commercial_cellline) {
        this.is_commercial_cellline = is_commercial_cellline;
    }

    public int getIs_foreign() {
        return is_foreign;
    }

    public void setIs_foreign(int is_foreign) {
        this.is_foreign = is_foreign;
    }

    public int getIs_file_public() {
        return is_file_public;
    }

    public void setIs_file_public(int is_file_public) {
        this.is_file_public = is_file_public;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
