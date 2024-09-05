package com.useo.demo.dtos.page_content;

public class PageContentSaveDTO {

    private String pageName;
    private String sectionName;
    private String content;

    public PageContentSaveDTO() {
    }

    public PageContentSaveDTO(String pageName, String sectionName, String content) {
        this.pageName = pageName;
        this.sectionName = sectionName;
        this.content = content;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

