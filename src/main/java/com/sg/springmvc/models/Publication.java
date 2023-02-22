package com.sg.springmvc.models;

import java.util.List;

public class Publication {
    private int journal_id;
    private String title;
    private String paperAuthor;
    private String paperDate;
    private String journalName;
    private String issue;
    private String volume;
    private String pmid;
    private String year;
    private String month;
    private String day;
    private String abst;
    private String citation;
    private String citation_date;
    private String doi;
    private String gsaAccession;
    private String gsaAccessions;
    private int isUnique;
    private int Prj_id;
    private int gsa_linked;

    public Publication(){}
    public Publication(Publication publication) {
        this.journal_id=publication.journal_id;
        this.title=publication.title;
        this.paperAuthor=publication.paperAuthor;
        this.paperDate=publication.paperDate;
        this.journalName=publication.journalName;
        this.issue=publication.issue;
        this.volume= publication.volume;
        this.pmid=publication.pmid;
        this.year=publication.year;
        this.month=publication.month;
        this.day=publication.day;
        this.abst=publication.abst;
        this.citation=publication.citation;
        this.citation_date=publication.citation_date;
        this.doi=publication.doi;
        this.gsaAccession=publication.gsaAccession;
        this.isUnique=publication.isUnique;
        this.Prj_id=publication.Prj_id;
        this.gsa_linked=publication.gsa_linked;
    }

    public int getJournal_id() {
        return journal_id;
    }

    public void setJournal_id(int journal_id) {
        this.journal_id = journal_id;
    }

    public int getPrj_id() {
        return Prj_id;
    }

    public void setPrj_id(int prj_Id) {
        Prj_id = prj_Id;
    }

    public int getGsa_linked() {
        return gsa_linked;
    }

    public void setGsa_linked(int gsa_linked) {
        this.gsa_linked = gsa_linked;
    }

    private boolean isAlreadyInDataBase;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaperAuthor() {
        return paperAuthor;
    }

    public void setPaperAuthor(String paperAuthor) {
        this.paperAuthor = paperAuthor;
    }

    public String getPaperDate() {
        return paperDate;
    }

    public void setPaperDate(String paperDate) {
        this.paperDate = paperDate;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAbst() {
        return abst;
    }

    public void setAbst(String abst) {
        this.abst = abst;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public String getCitation_date() {
        return citation_date;
    }

    public void setCitation_date(String citation_date) {
        this.citation_date = citation_date;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getGsaAccession() {
        return gsaAccession;
    }

    public void setGsaAccession(String gsaAccession) {
        this.gsaAccession = gsaAccession;
    }

    public int getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(int isUnique) {
        this.isUnique = isUnique;
    }

    public boolean isAlreadyInDataBase() {
        return isAlreadyInDataBase;
    }

    public void setAlreadyInDataBase(boolean alreadyInDataBase) {
        isAlreadyInDataBase = alreadyInDataBase;
    }

    public String getGsaAccessions() {
        return gsaAccessions;
    }

    public void setGsaAccessions(String gsaAccessions) {
        this.gsaAccessions = gsaAccessions;
    }
}
