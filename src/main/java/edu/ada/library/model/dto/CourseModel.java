package edu.ada.library.model.dto;

import edu.ada.library.model.entity.CourseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseModel
{
	private Long id;
	private String name;
	
	public CourseModel(CourseEntity courseEntity)
	{
		this.id = courseEntity.getId();
		this.name = courseEntity.getName();
	}
}
