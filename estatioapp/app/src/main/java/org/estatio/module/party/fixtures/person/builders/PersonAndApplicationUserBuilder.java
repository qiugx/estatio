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
package org.estatio.module.party.fixtures.person.builders;

import org.isisaddons.module.security.dom.user.ApplicationUser;

import org.estatio.module.base.platform.fixturesupport.BuilderScriptAbstract;
import org.estatio.module.party.dom.Person;
import org.estatio.module.party.dom.PersonGenderType;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class PersonAndApplicationUserBuilder
        extends BuilderScriptAbstract<PersonAndApplicationUserBuilder> {

    @Getter @Setter
    private String atPath;

    @Getter @Setter
    private String reference;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String initials;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private PersonGenderType personGenderType;

    @Getter @Setter
    private String securityUsername;

    @Getter @Setter
    private String securityUserAccountCloneFrom;

    @Override
    protected void execute(ExecutionContext executionContext) {

        PersonBuilder personBuilder = new PersonBuilder();
        ApplicationUserBuilder applicationUserBuilder = new ApplicationUserBuilder();

        // person
        person = personBuilder.setAtPath(atPath)
                .setReference(reference)
                .setFirstName(firstName)
                .setInitials(initials)
                .setLastName(lastName)
                .setPersonGenderType(personGenderType)
                .build(this, executionContext)
                .getPerson();


        // application user
        if(securityUsername != null) {
            applicationUser = applicationUserBuilder
                    .setSecurityUsername(securityUsername)
                    .setSecurityUserAccountCloneFrom(securityUserAccountCloneFrom)
                    .build(this, executionContext)
                    .getApplicationUser();
        }
    }

    @Getter
    private Person person;

    @Getter
    private ApplicationUser applicationUser;


}

