package org.estatio.app.mixins.commchannels;

import org.apache.isis.applib.annotation.Mixin;

import org.estatio.dom.communicationchannel.CommunicationChannelOwner_emailAddressTitles;
import org.estatio.dom.party.Party;

@Mixin
public class Party_emailAddresses extends CommunicationChannelOwner_emailAddressTitles {
    public Party_emailAddresses(final Party party) {
        super(party, " | ");
    }
}
