package edu.ada.library.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class BookModel implements Serializable
{
	private String name;
	private String author;
	private String description;
	private Date publishedAt;
}
