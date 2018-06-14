package org.modelmapper.module.vavr.testdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Julian Stücker
 */
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Person {

	private int yearsOfExperience;
}
