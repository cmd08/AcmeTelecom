package com.acmetelecom.billingdsl;

import com.acmetelecom.CallParticipant;

public interface Callee {

	HasStartTime callTo(CallParticipant callee);

}
