package com.dascom.product.entity;

import java.util.Date;

public class Solves {
    

	private Integer id;

    private String title;

    private String coverUrl;

    private String context;

    private Integer topper;

    private Date time;

    private Integer prof;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public Integer getTopper() {
        return topper;
    }

    public void setTopper(Integer topper) {
    	this.topper = topper;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getProf() {
        return prof;
    }

    public void setProf(Integer prof) {
        this.prof = prof;
    }
    
    @Override
	public String toString() {
		return "Solves [id=" + id + ", title=" + title + ", thumbnail="
				+ coverUrl + ", context=" + context + ", topper=" + topper
				+ ", time=" + time + ", prof=" + prof + "]";
	}
}