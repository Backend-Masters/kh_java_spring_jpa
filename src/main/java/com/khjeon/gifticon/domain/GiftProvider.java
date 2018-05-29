package com.khjeon.gifticon.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="gp")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GiftProvider implements Serializable {

	@Id
	@GeneratedValue
	private int id;

	@Column
	private String companyName;
}
