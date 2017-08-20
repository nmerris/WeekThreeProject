package com.nmerris.roboresumedb.models;

public class PageLinkState {
    
    private boolean highlightPersonNav;
    private boolean highlightEdNav;
    private boolean highlightWorkNav;
    private boolean highlightSkillNav;
    private boolean highlightEditNav;
    private boolean highlightFinalNav;
    
    private boolean disablePersonLink;
    private boolean disableAddEdLink;
    private boolean disableAddSkillLink;
    private boolean disableAddWorkExpLink;
    private boolean disableEditDetailsLink;
    private boolean disableShowFinalLink;
    
    
    public PageLinkState() {
//        highlightPersonNav = false;
//        highlightEdNav = false;
//        highlightWorkNav = false;
//        highlightSkillNav = false;
//        highlightEditNav = false;
//        highlightFinalNav = false;

//        disablePersonLink = false;
//        disableAddEdLink = false;
//        disableAddSkillLink = false;
//        disableAddWorkExpLink = false;
//        disableEditDetailsLink = false;
//        disableShowFinalLink = false;
    }

    public boolean getHighlightPersonNav() {
        return highlightPersonNav;
    }

    public void setHighlightPersonNav(boolean highlightPersonNav) {
        this.highlightPersonNav = highlightPersonNav;
    }

    public boolean getHighlightEdNav() {
        return highlightEdNav;
    }

    public void setHighlightEdNav(boolean highlightEdNav) {
        this.highlightEdNav = highlightEdNav;
    }

    public boolean getHighlightWorkNav() {
        return highlightWorkNav;
    }

    public void setHighlightWorkNav(boolean highlightWorkNav) {
        this.highlightWorkNav = highlightWorkNav;
    }

    public boolean getHighlightSkillNav() {
        return highlightSkillNav;
    }

    public void setHighlightSkillNav(boolean highlightSkillNav) {
        this.highlightSkillNav = highlightSkillNav;
    }

    public boolean getHighlightEditNav() {
        return highlightEditNav;
    }

    public void setHighlightEditNav(boolean highlightEditNav) {
        this.highlightEditNav = highlightEditNav;
    }

    public boolean getHighlightFinalNav() {
        return highlightFinalNav;
    }

    public void setHighlightFinalNav(boolean highlightFinalNav) {
        this.highlightFinalNav = highlightFinalNav;
    }

    public boolean isDisablePersonLink() {
        return disablePersonLink;
    }

    public void setDisablePersonLink(boolean disablePersonLink) {
        this.disablePersonLink = disablePersonLink;
    }

    public boolean isDisableAddEdLink() {
        return disableAddEdLink;
    }

    public void setDisableAddEdLink(boolean disableAddEdLink) {
        this.disableAddEdLink = disableAddEdLink;
    }

    public boolean isDisableAddSkillLink() {
        return disableAddSkillLink;
    }

    public void setDisableAddSkillLink(boolean disableAddSkillLink) {
        this.disableAddSkillLink = disableAddSkillLink;
    }

    public boolean isDisableAddWorkExpLink() {
        return disableAddWorkExpLink;
    }

    public void setDisableAddWorkExpLink(boolean disableAddWorkExpLink) {
        this.disableAddWorkExpLink = disableAddWorkExpLink;
    }

    public boolean isDisableEditDetailsLink() {
        return disableEditDetailsLink;
    }

    public void setDisableEditDetailsLink(boolean disableEditDetailsLink) {
        this.disableEditDetailsLink = disableEditDetailsLink;
    }

    public boolean isDisableShowFinalLink() {
        return disableShowFinalLink;
    }

    public void setDisableShowFinalLink(boolean disableShowFinalLink) {
        this.disableShowFinalLink = disableShowFinalLink;
    }

}