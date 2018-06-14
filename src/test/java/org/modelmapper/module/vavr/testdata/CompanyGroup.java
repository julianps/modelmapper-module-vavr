package org.modelmapper.module.vavr.testdata;

import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Julian Stücker
 */
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class CompanyGroup {

	public List<Employee> persons;
}
