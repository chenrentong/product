package com.dascom.product.entity;

import java.util.Date;

public class WorkOrder {
    private Integer id;

    private String theme;

    private String attachmentPath;

    private String attachmentSuffix;

    private String name;

    private String eMail;

    private String phone;

    private Integer partnerId;

    private Date startTime;

    private String content;
    
    private Integer isSolve;
    
    private String solveProject;
    

    public Integer getIsSolve() {
		return isSolve;
	}

	public void setIsSolve(Integer isSolve) {
		this.isSolve = isSolve;
	}

	public String getSolveProject() {
		return solveProject;
	}

	public void setSolveProject(String solveProject) {
		this.solveProject = solveProject;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath == null ? null : attachmentPath.trim();
    }

    public String getAttachmentSuffix() {
        return attachmentSuffix;
    }

    public void setAttachmentSuffix(String attachmentSuffix) {
        this.attachmentSuffix = attachmentSuffix == null ? null : attachmentSuffix.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail == null ? null : eMail.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}