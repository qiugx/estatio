package org.incode.module.communications.dom.contracttests.with;

import com.google.common.collect.ImmutableMap;

import org.incode.module.base.dom.with.ComparableByDescriptionContractTestAbstract_compareTo;
import org.incode.module.base.dom.with.WithDescriptionComparable;

/**
 * Automatically tests all domain objects implementing {@link WithDescriptionComparable}.
 */
public class WithDescriptionComparableContractForIncodeModuleTest_compareTo extends
        ComparableByDescriptionContractTestAbstract_compareTo {

    public WithDescriptionComparableContractForIncodeModuleTest_compareTo() {
        super("org.incode.module.communications", ImmutableMap.<Class<?>,Class<?>>of());
    }

}