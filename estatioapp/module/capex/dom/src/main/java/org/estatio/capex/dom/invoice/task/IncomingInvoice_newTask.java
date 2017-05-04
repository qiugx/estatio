package org.estatio.capex.dom.invoice.task;

import javax.annotation.Nullable;
import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Mixin;

import org.estatio.capex.dom.EstatioCapexDomModule;
import org.estatio.capex.dom.invoice.IncomingInvoice;
import org.estatio.capex.dom.invoice.state.IncomingInvoiceState;
import org.estatio.capex.dom.invoice.state.IncomingInvoiceStateTransitionType;
import org.estatio.capex.dom.task.NewTaskMixin;
import org.estatio.capex.dom.task.Task;
import org.estatio.dom.roles.EstatioRole;

@Mixin
public class IncomingInvoice_newTask
        implements NewTaskMixin<IncomingInvoice, IncomingInvoiceStateTransition, IncomingInvoiceStateTransitionType, IncomingInvoiceState> {

    public static class ActionDomainEvent extends EstatioCapexDomModule.ActionDomainEvent<IncomingInvoice_newTask> { }

    private final IncomingInvoice incomingInvoice;

    public IncomingInvoice_newTask(IncomingInvoice incomingInvoice) {
        this.incomingInvoice = incomingInvoice;
    }

    @Action(
            domainEvent = ActionDomainEvent.class
    )
    @MemberOrder(name = "tasks", sequence = "1")
    @Override
    public Task newTask(
            final EstatioRole assignTo,
            final IncomingInvoiceStateTransitionType taskTransition,
            @Nullable
            final String description) {
        final IncomingInvoiceStateTransition stateTransition = repository
                .create(incomingInvoice, taskTransition, assignTo, description);
        return stateTransition.getTask();
    }

    @Inject
    IncomingInvoiceStateTransitionRepository repository;

}