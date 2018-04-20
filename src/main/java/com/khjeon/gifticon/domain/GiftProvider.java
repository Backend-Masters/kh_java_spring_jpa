package com.khjeon.gifticon.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="gp")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GiftProvider {

	@Id
	@GeneratedValue
	private int id;

	@Column
	private String companyName;
}
