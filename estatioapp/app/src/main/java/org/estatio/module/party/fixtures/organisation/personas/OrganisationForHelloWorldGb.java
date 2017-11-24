/*
 *
 *  Copyright 2012-2014 Eurocommercial Properties NV
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.estatio.module.party.fixtures.organisation.personas;

import org.estatio.module.base.platform.fixturesupport.PersonaScriptAbstract;
import org.estatio.module.party.dom.Organisation;
import org.estatio.module.party.fixtures.organisation.builders.OrganisationAndCommsBuilder;
import org.estatio.module.party.fixtures.organisation.builders.OrganisationCommsBuilder;

import lombok.Getter;

public class OrganisationForHelloWorldGb extends PersonaScriptAbstract {

    public static final Organisation_enum data = Organisation_enum.HelloWorldGb;

    public static final String REF = data.getRef();
    public static final String AT_PATH = data.getApplicationTenancy().getPath();

    @Getter
    private Organisation organisation;

    @Override
    protected void execute(ExecutionContext executionContext) {

        final OrganisationAndCommsBuilder organisationAndCommsBuilder = new OrganisationAndCommsBuilder();
        this.organisation = organisationAndCommsBuilder
                    .setAtPath(AT_PATH)
                    .setPartyName(data.name)
                    .setPartyReference(REF)
                    .setAddress1("5 Covent Garden")
                    .setAddress2(null)
                    .setPostalCode("W1A1AA")
                    .setCity("London")
                    .setStateReference(null)
                    .setCountryReference("GBR")
                    .setPhone("+44202211333")
                    .setFax("+442022211399")
                    .setEmailAddress("info@hello.example.com")
                    .build(this, executionContext)
                    .getOrganisation();


        final OrganisationCommsBuilder organisationCommsBuilder =
                new OrganisationCommsBuilder();
        organisationCommsBuilder
                .setOrganisation(organisation)
                .setAddress1("1 Circle Square")
                .setPostalCode("W2AXXX")
                .setCity("London")
                .setCountryReference("GBR")
                .build(this, executionContext);
    }

}
