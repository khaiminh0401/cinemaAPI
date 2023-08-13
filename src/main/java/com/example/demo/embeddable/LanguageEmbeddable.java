package com.example.demo.embeddable;

import org.seasar.doma.Column;
import org.seasar.doma.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class LanguageEmbeddable {
	@Column(name = "LANGUAGES")
	final String languagesName;
}