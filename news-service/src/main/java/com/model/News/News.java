package com.model.News;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class News {

	private String title;
	private String description;
	private String url;
	private String content;
	
	public static final String JSON_STRING_TITLE = "title";
    public static final String  JSON_STRING_DESCRIPTION = "description";
    public static final String JSON_STRING_URL = "url";
    public static final String JSON_STRING_CONTENT = "content";
	
	public News(@JsonProperty(JSON_STRING_TITLE) String title,
			    @JsonProperty(JSON_STRING_DESCRIPTION) String description,
			    @JsonProperty(JSON_STRING_URL) String url,
			    @JsonProperty(JSON_STRING_CONTENT) String content) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public String getContent() {
		return content;
	}
	
	
}
