/**
  * @filename SomeDTO.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.dto;

import lombok.Data;

/**
 * @type SomeDTO
 * @description 
 * @author qianye.zheng
 */
public class SomeDTO {
	
	private String id;
	
	private String name;

	/**
	* @return the id
	*/
	public String getId() {
		return id;
	}

	/**
	* @param id the id to set
	*/
	public void setId(String id) {
		this.id = id;
	}

	/**
	* @return the name
	*/
	public String getName() {
		return name;
	}

	/**
	* @param name the name to set
	*/
	public void setName(String name) {
		this.name = name;
	}
	
}
