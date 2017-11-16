/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
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
package org.estatio.module.base;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Sets;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import org.isisaddons.module.base.platform.applib.Module;
import org.isisaddons.module.base.platform.applib.ModuleAbstract;
import org.isisaddons.module.excel.IncodeLibExcelModule;
import org.isisaddons.module.pdfbox.IncodeLibPdfBoxModule;
import org.isisaddons.module.poly.IncodeLibPolyModule;
import org.isisaddons.module.security.IncodeSpiSecurityModule;
import org.isisaddons.module.servletapi.IncodeLibServletApiModule;
import org.isisaddons.module.settings.IncodeLibSettingsModule;
import org.isisaddons.module.stringinterpolator.IncodeLibStringInterpolatorModule;
import org.isisaddons.module.togglz.IncodeExtTogglzModule;
import org.isisaddons.wicket.excel.IncodeWktExcelModule;
import org.isisaddons.wicket.fullcalendar2.IncodeWktFullCalendar2Module;
import org.isisaddons.wicket.gmap3.IncodeWktGmap3Module;
import org.isisaddons.wicket.pdfjs.IncodeWktPdfJsModule;

import org.incode.module.fixturesupport.dom.scripts.TeardownFixtureAbstract;

import org.estatio.module.base.fixtures.clock.TickingClockFixture;
import org.estatio.module.base.fixtures.security.apptenancy.enums.ApplicationTenancy_enum;
import org.estatio.module.base.fixtures.security.perms.personas.EstatioRolesAndPermissions;
import org.estatio.module.base.fixtures.security.userrole.personas.EstatioAdmin_Has_EstatioSuperuserRole;
import org.estatio.module.base.fixtures.security.users.personas.EstatioAdmin;
import org.estatio.module.base.fixtures.security.users.personas.EstatioUser;
import org.estatio.module.base.fixtures.security.users.personas.EstatioUserInFrance;
import org.estatio.module.base.fixtures.security.users.personas.EstatioUserInGreatBritain;
import org.estatio.module.base.fixtures.security.users.personas.EstatioUserInItaly;
import org.estatio.module.base.fixtures.security.users.personas.EstatioUserInNetherlands;
import org.estatio.module.base.fixtures.security.users.personas.EstatioUserInSweden;

@XmlRootElement(name = "module")
public final class EstatioBaseModule extends ModuleAbstract {

    @Override public Set<Module> getDependencies() {
        return Sets.newHashSet(

                // lib
                // (nothing for incode-module-base-dom)
                // (nothing for incode-module-fixturesupport-dom)
                new IncodeLibExcelModule(),
                new IncodeLibPdfBoxModule(),
                new IncodeLibPolyModule(),
                new IncodeLibServletApiModule(),
                new IncodeLibSettingsModule(),
                new IncodeLibStringInterpolatorModule(),

                // spi
                new IncodeSpiSecurityModule(),

                // wkt
                new IncodeWktExcelModule(),
                new IncodeWktFullCalendar2Module(),
                new IncodeWktGmap3Module(),
                new IncodeWktPdfJsModule(),

                // ext
                new IncodeExtTogglzModule()

        );
    }

    @Override
    public FixtureScript getRefDataSetupFixture() {
        return new FixtureScript() {
            @Override
            protected void execute(final ExecutionContext executionContext) {
                executionContext.executeChild(this, new TickingClockFixture());
                executionContext.executeChild(this, new ApplicationTenancy_enum.PersistScript());

                // set up 3 estatio roles
                executionContext.executeChild(this, new EstatioRolesAndPermissions());

                // estatio-admin user with the estatio-admin role + superuser
                executionContext.executeChild(this, new EstatioAdmin());
                executionContext.executeChild(this, new EstatioAdmin_Has_EstatioSuperuserRole());

                // bunch of users with estatio-user role
                executionContext.executeChild(this, new EstatioUser());
                executionContext.executeChild(this, new EstatioUserInFrance());
                executionContext.executeChild(this, new EstatioUserInGreatBritain());
                executionContext.executeChild(this, new EstatioUserInItaly());
                executionContext.executeChild(this, new EstatioUserInNetherlands());
                executionContext.executeChild(this, new EstatioUserInSweden());

            }
        };
    }

    @Override
    public FixtureScript getTeardownFixture() {
        return new TeardownFixtureAbstract() {
            @Override
            protected void execute(final ExecutionContext executionContext) {
                // TODO: REVIEW, we're currently not bootstrapping audit or command or sessionlogger, only security
                // deleteFrom(CommandJdo.class);
            }
        };
    }



    public abstract static class ActionDomainEvent<S>
            extends org.apache.isis.applib.services.eventbus.ActionDomainEvent<S> { }

    public abstract static class CollectionDomainEvent<S,T>
            extends org.apache.isis.applib.services.eventbus.CollectionDomainEvent<S,T> { }

    public abstract static class PropertyDomainEvent<S,T>
            extends org.apache.isis.applib.services.eventbus.PropertyDomainEvent<S,T> { }


}
